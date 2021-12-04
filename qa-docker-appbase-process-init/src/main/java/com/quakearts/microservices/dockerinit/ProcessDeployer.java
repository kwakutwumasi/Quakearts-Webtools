package com.quakearts.microservices.dockerinit;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quakearts.microservices.dockerinit.exception.InitException;
import com.quakearts.microservices.dockerinit.model.ProcessMetadata;
import com.quakearts.microservices.dockerinit.model.ProcessesFile;

public class ProcessDeployer {
	
	private static final Logger log = LoggerFactory.getLogger(ProcessDeployer.class);
	private static final ObjectMapper MAPPER = new ObjectMapper();
	public static final String PROCESSES_FILENAME = System.getProperty("processes.filename", "processes.json");
	
	private Map<String, Process> processMap = new ConcurrentHashMap<>();
	
	private Timer processCheckTimer = new Timer(true);
	private int count;
	
	public static ObjectMapper getMapper() {
		return MAPPER;
	}
	
	public int getMetadataCount() {
		return count;
	}
	
	public void init() {
		try(var stream = new FileInputStream(PROCESSES_FILENAME)) {
			var processesFile = MAPPER.readValue(stream, ProcessesFile.class);
			count = processesFile.getProperties().size();
			processesFile.getProperties().entrySet()
				.stream().parallel().map(this::validate)
				.forEach(this::deploy);
		} catch (IOException e) {
			throw new InitException("Unable to read "+PROCESSES_FILENAME, e);
		}
		
		processCheckTimer.schedule(new CheckProcesses(), 0l, Long.parseLong(System.getProperty("check.process.interval", "60"))*1000l);
		Runtime.getRuntime().addShutdownHook(new Thread(processCheckTimer::cancel));
	}
	
	public Entry<String, ProcessMetadata> validate(Entry<String, ProcessMetadata> entry) {
		if(entry.getValue() == null) {
			throw new InitException("Invalid entry: "+entry.getKey()+" is missing ProcessMetadata");
		}
		
		var metadata = entry.getValue();
		if(metadata.getJarFile() == null) {
			throw new InitException("Invalid entry: "+entry.getKey()+" ProcessMetadata is missing jar file");
		}
		
		return entry;
	}
	
	public void deploy(Entry<String, ProcessMetadata> entry) {
		var commands = new ArrayList<String>();
		var metadata = entry.getValue();
		log.info("Initializing process {}", entry.getKey());
		
		Path workingDirectory = null;
		if(metadata.getWorkingDirectory() != null && !
				metadata.getWorkingDirectory().trim().isEmpty()) {
			workingDirectory = Paths.get(metadata.getWorkingDirectory());
			workingDirectory = workingDirectory.toAbsolutePath();
		}
		
		initialize(commands);

		setSystemProperties(commands, metadata);
		
		addJar(commands, metadata);
		
		addArguments(commands, metadata);
		
		buildAndStartProcess(commands, workingDirectory, entry.getKey());
	}

	private void initialize(ArrayList<String> commands) {
		commands.add("java");
	}

	private void setSystemProperties(ArrayList<String> commands, ProcessMetadata metadata) {
		if(isNotEmpty(metadata.getProperties())) {
			metadata.getProperties().stream()
			.map(entry->"-D"+entry).forEach(commands::add);
		}
	}

	private void addJar(ArrayList<String> commands, ProcessMetadata metadata) {
		commands.add("-jar");
		commands.add(metadata.getJarFile());
	}

	private void addArguments(ArrayList<String> commands, ProcessMetadata metadata) {
		if(isNotEmpty(metadata.getArguments())) {
			commands.addAll(metadata.getArguments());
		}
	}

	private void buildAndStartProcess(ArrayList<String> commands, Path workingDirectory, String key) {
		if(log.isTraceEnabled()) {
			log.trace("Running Process {} with command: {}", key, commands.stream().collect(Collectors.joining(" ")));
		}
		
		var processBuilder = new ProcessBuilder(commands);
		if(workingDirectory!=null) {
			log.trace("Working path for Process {}: {}", key, workingDirectory);
			processBuilder.directory(workingDirectory.toFile());
		}
		
		try {
			var process = processBuilder.inheritIO().start();
			Runtime.getRuntime().addShutdownHook(new Thread(()-> {
				log.trace("Shutting down Process {}", key);
				process.destroy();
				
				do {
					sleep();
				} while(process.isAlive() && !Thread.interrupted());

				if(process.isAlive()) {
					log.trace("Shutting down Process {} by force", key);
					process.destroyForcibly();
				}
			}));
			
			processMap.put(key, process);
		} catch (IOException e) {
			throw new InitException("Unable to start process", e);
		}
	}

	private boolean isNotEmpty(List<String> list) {
		return list != null && !list.isEmpty();
	}

	private void sleep() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
	private class CheckProcesses extends TimerTask {
		@Override
		public void run() {
			processMap.entrySet().stream()
				.parallel().forEach(this::validateStillRunning);
		}
		
		private void validateStillRunning(Entry<String, Process> entry) {
			if(!entry.getValue().isAlive()) {
				log.error("Process {} has exited. Shutting down all others.", entry.getKey());
				try {
					if(!Main.getCommandQueue().offer("exit", Main.getCommandTimeout(), TimeUnit.SECONDS)) {
						log.trace("Exit command was ignored.");
					}
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			} else {
				log.trace("Process {} has been checked and is still running", entry.getKey());
			}
		}
	}
			
}

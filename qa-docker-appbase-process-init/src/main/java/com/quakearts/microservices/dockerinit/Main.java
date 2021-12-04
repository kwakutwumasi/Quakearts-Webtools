package com.quakearts.microservices.dockerinit;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.quakearts.microservices.dockerinit.exception.InitException;

public class Main {
	
	private static ArrayBlockingQueue<String>  commandQueue;
	private static final Logger log = LoggerFactory.getLogger(Main.class);
	private static long commandTimeout;
	
	public static ArrayBlockingQueue<String> getCommandQueue() {
		return commandQueue;
	}
	
	public static long getCommandTimeout() {
		return commandTimeout;
	}
	
	public static void main(String[] args) {
		log.trace("Initiating processes from file {}", ProcessDeployer.PROCESSES_FILENAME);
		var deployer = new ProcessDeployer();
		try { 
			deployer.init();
		} catch (InitException e) {
			System.exit(1);
		}

		commandQueue = new ArrayBlockingQueue<>(deployer.getMetadataCount()+1);
		
		log.info("All processes have been started successfully");

		try {
			commandTimeout = Integer.parseInt(System.getProperty("main.command.poll.timeout", "60"));			
		} catch (NumberFormatException e) {
			commandTimeout = 60;
		}
		
		if(args.length>0 && ("-interactive".equals(args[0]) || "-i".equals(args[0]))) {
			CompletableFuture.runAsync(Main::listenForExit);
		}
		
		try {
			while(!"exit".equalsIgnoreCase(commandQueue.poll(commandTimeout, TimeUnit.SECONDS))) {
				log.trace("Waiting for exit command....");
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		
		System.exit(0);
	}
	
	private static void listenForExit() {
		log.info("Type any key and 'Enter' to shutdown all deployed services");
		try{
			System.in.read();
			if(!commandQueue.offer("exit", commandTimeout, TimeUnit.SECONDS)) {
				log.trace("Exit command was ignored.");
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		} catch (IOException e) {
			//Do nothing
		}
	}
}

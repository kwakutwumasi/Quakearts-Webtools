/*******************************************************************************
* Copyright (C) 2016 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.webapp.logging;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.quakearts.webapp.logging.abstraction.WebListenerLevel;
import com.quakearts.webapp.logging.abstraction.WebListenerLoggingEvent;
import com.quakearts.webapp.logging.finder.RegistrarFinder;
import com.quakearts.webapp.logging.monitor.MonitorTarget;
import com.quakearts.webapp.logging.monitor.SystemMonitor;
import com.quakearts.webapp.logging.monitor.SystemMonitor.State;
import com.quakearts.webapp.logging.monitor.SystemMonitor.Type;
import com.quakearts.webapp.logging.monitor.impl.GenericMonitor;
import com.quakearts.webapp.logging.parser.LogPrinter;
import com.quakearts.webapp.logging.parser.impl.DefaultPrinter;

public class WebLogServlet extends HttpServlet {

	private static final long serialVersionUID = 6506483390318221684L;
	private static final String LOGGERNAME = WebLogServlet.class.getName();
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	private static final Pattern MONITORTARGET_PATTERN = Pattern.compile("\"[^\"\\|]+\"\\|\"[^\"\\|]+\"\\|\"[^\"\\|]+\"");
	private WebListenerRegistrar appender;
	private LogPrinter logPrinter;
	private SystemMonitor monitor;
	private List<MonitorTarget> targets = new ArrayList<MonitorTarget>();
	private String logFile,logBase;
	
	@Override
	public void init() throws ServletException {
		ServletConfig config = getServletConfig();
		logFile = config.getInitParameter("log.file");
		logBase = config.getInitParameter("log.base");
		
		try{
			logPrinter = (LogPrinter) Class.forName(config.getInitParameter("log.printer")).newInstance();
			logPrinter.setupParser(config);
		}catch (Exception e) {
			logPrinter = new DefaultPrinter();
		}

		try{
			monitor = (SystemMonitor) Class.forName(config.getInitParameter("system.monitor")).newInstance();
			monitor.setProperties(config);
		}catch (Exception e) {
			monitor = new GenericMonitor() {
				
				@Override
				protected State getServiceState(String name) {
					return State.QUERYERROR;
				}
			};
		}
		
		String targetString;
		if((targetString=config.getInitParameter("monitor.targets"))!=null){
			Matcher matcher = MONITORTARGET_PATTERN.matcher(targetString);
			String splitTargetString;
			while(matcher.find()){
				splitTargetString = matcher.group();
				String[] monitorParts = splitTargetString.split("[\"|]+");
				if(monitorParts.length<3)
					throw new ServletException("Invalid monitor target:"+splitTargetString);
				
				String type=null,identifier=null,displayName=null;
				for(String monitorPart:monitorParts){
					if(monitorPart.trim().isEmpty())
						continue;
					
					if(displayName==null)
						displayName=monitorPart;
					else if(identifier==null)
						identifier = monitorPart;
					else if(type==null)
						type = monitorPart;
				}
				if(type==null||identifier==null||displayName==null)
					throw new ServletException("Invalid monitor target:"+splitTargetString);					
				
				if(type.equalsIgnoreCase("webapp"))
					targets.add(new MonitorTarget(identifier, Type.WEBAPP, displayName));
				else if(type.equalsIgnoreCase("webservice"))
					targets.add(new MonitorTarget(identifier, Type.WEBSERVICE, displayName));
				else if(type.equalsIgnoreCase("serverservice"))
					targets.add(new MonitorTarget(identifier, Type.SERVERSERVICE, displayName));
				else
					throw new ServletException("Invalid monitor target:"+splitTargetString);
			}
		}		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			
		String path=req.getPathInfo();
		String response;
		OutputStream out = resp.getOutputStream();
		try{
			if(path !=null && path.startsWith("/events")){
				resp.reset();
				resp.setContentType("application/json");
				HttpSession session = req.getSession(true);
				if(session == null)
					throw new ServletException("Sessions must be active to use the logging servlet.");
				
				WebListener listener;
				Object listenerObject;
				if((listenerObject=session.getAttribute("com.quakearts.webapp.logging.LISTENER")) != null){
					listener = (WebListener) listenerObject;
					
					String levelParam = req.getParameter("level");
					if(levelParam!=null)
						listener.setThreshold(getLevel(levelParam));
					String bufferSize = req.getParameter("buff");
					try {
						listener.setBufferSize(Integer.parseInt(bufferSize));
					} catch (Exception e) {
					}
					List<WebListenerLoggingEvent> events = listener.getEvents();
					StringBuilder responseBuffer = new StringBuilder().append("{\"data\":[");
					boolean doneFirst = false;
					for(WebListenerLoggingEvent event:events){
						if(doneFirst)
							responseBuffer.append(",");
						else
							doneFirst = true;
						WebListenerLevel level = event.getLevel();
						responseBuffer.append(generateEventJson(level==null?"UNKNOWN":level.toString(),  event.getLoggerName(), event.getRenderedMessage(), event.getTimeStamp()>0?DATE_FORMAT.format(new Date(event.getTimeStamp())):DATE_FORMAT.format(new Date()), event.getThreadName()));
					}
					responseBuffer.append("]}");
					response = responseBuffer.toString();
				} else {
					listener = new WebListener(getAppender());			
					session.setAttribute("com.quakearts.webapp.logging.LISTENER", listener);
					response = "{\"data\":["+generateEventJson("INFO",LOGGERNAME, "Connected. Logging initiated", DATE_FORMAT.format(new Date()), "MAIN")+ "]}";
					String level = req.getParameter("level");
					if(level!=null)
						listener.setThreshold(getLevel(level));
					String bufferSize = req.getParameter("buff");
					try {
						listener.setBufferSize(Integer.parseInt(bufferSize));
					} catch (Exception e) {
					}
				}
		
				resp.setContentLength(response.length());
				out.write(response.getBytes());
			} else if(path !=null && path.startsWith("/serverlog")){
				resp.reset();
				String contentType=req.getParameter("type");
				if(contentType==null)
					contentType="text/html";
				
				resp.setContentType(contentType);
				byte[] contents;
				
				String fileLocation;
				
				String file=req.getParameter("file");
				if(file==null)
					fileLocation = logFile;
				else{
					file = file.replace(File.separatorChar, '\0');
					fileLocation = logBase+File.separator+file;
				}
				
				String filter = req.getParameter("filter");
				
				FileInputStream stream;
				try {
					stream = new FileInputStream(fileLocation);
					contents = new byte[stream.available()];
					stream.read(contents);
				} catch (IOException e) {
					contents = ("No such file: "+logFile).getBytes();
				}
				out.write(logPrinter.format(contents, contentType, filter));
			} else if(path !=null && path.startsWith("/loglist")){
				resp.reset();
				resp.setContentType("application/json");
				File logFiles = new File(logBase);
				if(logFiles.isDirectory()){
					StringBuilder logFilesBuilder = new StringBuilder();
					logFilesBuilder.append("{\"files\":[");
					boolean firstLine=true;
					for(File file:logFiles.listFiles()){
						if(file.isFile()){
							if(firstLine)
								firstLine=false;
							else
								logFilesBuilder.append(",");
							logFilesBuilder.append("\"").append(escapeJSON(file.getName())).append("\"");
						}
					}
					logFilesBuilder.append("]}");
					out.write(logFilesBuilder.toString().getBytes());
				} else {
					out.write("{\"files\":[]}".getBytes());
				}
			} else if(path!=null && path.startsWith("/monitor")){
				resp.reset();
				resp.setContentType("application/json");
				State state;
				out.write("{\"states\":[".getBytes());
				boolean firstLine = true;
				for(MonitorTarget target:targets){
					if(firstLine)
						firstLine=false;
					else
						out.write(',');
					state = monitor.getState(target);
					out.write(("{\"name\":\""+target.getDisplayName()+"\",\"state\":\""+state.toString()+"\"}").getBytes());
				}
				out.write("]}".getBytes());
			} else if(path!=null && (path.startsWith("/log-console") || path.startsWith("/resources/"))){
				resp.reset();
				InputStream stream;
				if(path.startsWith("/log-console")){
					stream = this.getClass().getResourceAsStream("html/log-console.html");
					resp.setContentType("text/html");
				} else {
					stream = this.getClass().getResourceAsStream(path.substring(1));
					if(path.endsWith("js"))
						resp.setContentType("application/javascript");
					else if(path.endsWith("css"))
						resp.setContentType("text/css");
					else if(path.endsWith("jpg"))
						resp.setContentType("image/jpeg");
					else if(path.endsWith("gif"))
						resp.setContentType("image/gif");
					else
						resp.setContentType("image/png");
				}
				int read;
				while ((read = stream.read())>-1) {					
					out.write(read);
				}
			}
			out.flush();
		} finally {
			out.close();
		}
	}
	
	private String generateEventJson(String level, String loggerName, String message, String timesStamp, String threadName){
		return "{ \"level\":\""+level+"\"," +
		"\"loggerName\":\""+loggerName+"\"," +
		"\"message\":\""+escapeJSON(message)+"\"," +
		"\"timeStamp\":\""+escapeJSON(timesStamp)+"\"," +
		"\"threadName\":\""+threadName+"\"}";
	}

	private String escapeJSON(String string){
		return string.replace("\\", "\\\\").replace("\"","\\\"").replace("\b","\\b").replace("\f", "\\f").replace("\n","\\n").replace("\r","\\r").replace("\t","\\t").replace("/", "\\/");
	}
	
	private WebListenerLevel getLevel(String level){
		if(level==null)
			return WebListenerLevel.INFO;
		else if(level.equalsIgnoreCase("TRACE"))
			return WebListenerLevel.TRACE;
		else if(level.equalsIgnoreCase("WARN"))
			return WebListenerLevel.WARN;
		else if(level.equalsIgnoreCase("FATAL"))
			return WebListenerLevel.FATAL;
		else if(level.equalsIgnoreCase("DEBUG"))
			return WebListenerLevel.DEBUG;
		else
			return WebListenerLevel.INFO;
	}

	private WebListenerRegistrar getAppender() throws ServletException {
		if(appender==null){
			try {
				RegistrarFinder finder = (RegistrarFinder) Class.forName(getServletConfig().getInitParameter("finder.class")).newInstance();
				appender = finder.locateWebListenerRegistrar(getServletConfig());
			} catch (Exception e) {
				throw new ServletException("Cannot load appender",e);
			}
		}
		return appender; 
	}
}

/*******************************************************************************
 * Copyright (C) 2017 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.webapp.logging.parser.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletConfig;
import com.quakearts.webapp.logging.parser.LogPrinter;

public class JBossLogPrinter implements LogPrinter {

	private static final String APPLICATION_CSV = "application/csv";
	private static final String TEXT_HTML = "text/html";
	private Pattern linePattern, timePattern, typePattern, sourcePattern;
	private static final String TEMPLATE="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?>\n"+
			"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n"+
			"<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"+
			"<head>\n"+
			"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\" />\n"+
			"<title>Log Files</title>\n"+
			"<style>\n"+
			"body {\n"+
			"	font-family: Verdana,Helvetica,sans-serif;\n"+
			"	font-size: 10px;\n"+
			"	color: black;\n"+
			"}\n"+
			"table {\n"+
			"	font-family:sans-serif;\n"+
			"	font-size:11px;\n"+
			"	border-spacing:0px;\n"+
			"}\n"+
			"table tr th, table tr td{\n"+
			"	border-bottom:1px solid black;\n"+
			"	vertical-align:top;"+
			"	padding-left:5px;"+
			"}\n"+
			"</style>\n"+
			"</head>\n"+
			"<body>\n"+
			"${content}"+
			"</body>\n"+
			"</html>\n";
	
	@Override
	public void setupParser(ServletConfig config) {
		String linePatternString;
		if((linePatternString =config.getInitParameter("jboss.line.pattern"))==null)
			linePatternString = "(\\d\\d\\d\\d-\\d\\d-\\d\\d\\s+\\d\\d:\\d\\d:\\d\\d,\\d\\d\\d\\s+((INFO)|(DEBUG)|(TRACE)|(WARN)|(ERROR)|(WARNING))\\s+\\[[a-zA-Z0-9\\.\\_$]+\\]\\s+.+)+";
		linePattern = Pattern.compile(linePatternString);
		
		String timePatternString;
		if((timePatternString=config.getInitParameter("jboss.time.pattern"))==null)
			timePatternString = "\\d\\d\\d\\d-\\d\\d-\\d\\d\\s+\\d\\d:\\d\\d:\\d\\d,\\d\\d\\d";
		timePattern = Pattern.compile(timePatternString);
		
		typePattern = Pattern.compile("(INFO)|(DEBUG)|(TRACE)|(WARN)|(ERROR)");
		
		String sourcePatternString;
		if((sourcePatternString=config.getInitParameter("jboss.source.pattern"))==null)
			sourcePatternString ="\\[[a-zA-Z0-9\\.\\_$]+\\]";
		sourcePattern = Pattern.compile(sourcePatternString);
	}

	@Override
	public String format(String log, String fileType, String filter) throws IllegalArgumentException, UnsupportedOperationException {
		if(fileType==null||(!fileType.equals(TEXT_HTML) && !fileType.equals(APPLICATION_CSV)))
			throw new UnsupportedOperationException("FileType "+fileType+" not recognized");
		
		StringBuilder formattedLogs = new StringBuilder();
		String[] lines = log.split("[\r\n]+");
		boolean doHeader=true, firstline=true;
		for(String logString:lines){			
			if(doHeader){
				if(fileType.equals(TEXT_HTML))
					formattedLogs.append("\t<table>\r\n\t\t<tr><th>TIME</th><th>TYPE</th><th>SOURCE</th><th>EVENT</th></tr>\r\n");
				else
					formattedLogs.append("TIME,TYPE,SOURCE,EVENT\r\n");
				
				doHeader=false;
			}
						
			String time,type,source, event;
			if(linePattern.matcher(logString).find()){	
				if(filter!=null && !logString.matches(filter))
					continue;

				Matcher timeMatcher = timePattern.matcher(logString);
				if(timeMatcher.find()){
					time = timeMatcher.group();
				} else
					throw new IllegalArgumentException("Cannot parse time in : "+logString);
				Matcher typeMatcher = typePattern.matcher(logString);
				if(typeMatcher.find()){
					type = typeMatcher.group();
				} else
					throw new IllegalArgumentException("Cannot parse type in : "+logString);
				Matcher sourceMatcher = sourcePattern.matcher(logString);
				if(sourceMatcher.find())
					source = sourceMatcher.group();
				else
					throw new IllegalArgumentException("Cannot parse source in : "+logString);
				
				event = logString.substring(logString.indexOf(source)+source.length());
				if(fileType.equals(TEXT_HTML)){
					formattedLogs.append(!firstline?"</td></tr>\r\n":"").append("\t\t<tr valign=\"top\"><td nowrap=\"nowrap\">").append(time).append("</td><td><span style=\"color:").append(getColor(type)).append("\">")
					.append(type).append("</span></td><td>").append(source).append("</td><td>").append(event);
				} else {
					formattedLogs.append(!firstline?"\r\n":"").append(time).append(",").append(type).append(",").append(source).append(",").append(event);
				}
				
				if(firstline)
					firstline=false;				
				
			} else {
				if(fileType.equals(TEXT_HTML)){
					if(formattedLogs.lastIndexOf("</th></tr>\r\n") == formattedLogs.length()-12)
						formattedLogs.append("\t\t<tr><td nowrap=\"nowrap\">");
						
					formattedLogs.append("<br />").append(logString);
				}else
					formattedLogs.append("|").append(logString);
			}
		}
		
		if(fileType.equals(TEXT_HTML)){			
			if(formattedLogs.lastIndexOf("</th></tr>\r\n") == formattedLogs.length()-12){
				formattedLogs.append("\t</table>");
				return TEMPLATE.replace("${content}", (filter!=null?formattedLogs.toString():log.replace("\n", "<br />")));
			} else {				
				formattedLogs.append("</td></tr>\r\n\t</table>");
				return TEMPLATE.replace("${content}", formattedLogs.toString());
			}
		} else {
			if(formattedLogs.length()==0)
				return log;
			else{
				formattedLogs.append("\r\n");
				return formattedLogs.toString();
			}
		}
	}

	private String getColor(String type) {
		if(type.equals("INFO"))
			return "green";
		else if(type.equals("WARN"))
			return "orange";
		else if(type.equals("DEBUG"))
			return "blue";
		else if(type.equals("TRACE"))
			return "purple";
		else if(type.equals("ERROR"))
			return "red";
		else if(type.equals("WARNING"))
			return "orange";
		
		return "black";
	}

	@Override
	public byte[] format(byte[] log, String contentType, String filter) throws IllegalArgumentException, UnsupportedOperationException {
		return format(new String(log),contentType,filter).getBytes();
	}
	
}

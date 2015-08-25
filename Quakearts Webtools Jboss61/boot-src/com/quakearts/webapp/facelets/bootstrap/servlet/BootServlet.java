package com.quakearts.webapp.facelets.bootstrap.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import com.quakearts.webapp.facelets.bootstrap.utils.BootFileUpload;

public class BootServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2594807694129920780L;
	private final ConcurrentHashMap<String, byte[]> bootResources = new ConcurrentHashMap<String, byte[]>();
	private final ConcurrentHashMap<Integer, String> imageMap = new ConcurrentHashMap<>();
	private Integer[] widthRange;
	private final HashMap<String, String> forbidden = new HashMap<String, String>();
	private static final Logger log = Logger.getLogger(BootServlet.class);
	private String fileForm;
	private int maxFiles;
	public static final String FILELOADCOUNT = "com.quakearts.bootstrap.FILELOADCOUNT";
	private String defaultImage;
	
	@Override
	public void init() throws ServletException {
		bootResources.put("/css/bootstrap-theme.min.css",null);
		bootResources.put("/css/bootstrap.min.css",null);
		bootResources.put("/css/qaboot.min.css",null);
		bootResources.put("/fonts/glyphicons-halflings-regular.eot",null);
		bootResources.put("/fonts/glyphicons-halflings-regular.svg",null);
		bootResources.put("/fonts/glyphicons-halflings-regular.ttf",null);
		bootResources.put("/fonts/glyphicons-halflings-regular.woff",null);
		bootResources.put("/fonts/glyphicons-halflings-regular.woff2",null);
		bootResources.put("/js/bootstrap.js",null);
		bootResources.put("/js/bootstrap.min.js",null);
		bootResources.put("/js/qaboot.min.js",null);
		bootResources.put("/js/jquery-1.11.2.min.js",null);
		bootResources.put("/js/npm.js",null); 
		bootResources.put("/js/respond.min.js",null); 
		bootResources.put("/css/images/loading.gif",null); 
		bootResources.put("/css/images/loading-error.png",null); 
		bootResources.put("/css/images/loaded.png",null); 
		
		for(String forbiddenExt:getServletConfig().getInitParameter("com.quakearts.bootstrap.FORBIDDEN").split(",")){
			forbidden.put(forbiddenExt.trim(), "");
		}
		String exempt;
		if((exempt=getServletContext().getInitParameter("com.quakearts.bootstrap.EXEMPT"))!=null){
			for(String exemptExt:exempt.split(",")){
				forbidden.remove(exemptExt);
			}
		}
		try {
			maxFiles = Integer.parseInt(getServletConfig().getServletContext().getInitParameter("com.quakearts.bootstrap.MAXFILELOAD"));
		} catch (Exception e) {
			maxFiles = 5;
		}
		
		String imageMapString = getServletContext().getInitParameter("com.quakearts.bootstrap.IMAGEMAP");
		if(imageMapString !=null){
			String[] imageEntries = imageMapString.split(";");
			SortedSet<Integer> widthRangeSet = new TreeSet<>();
			for(String imageEntry:imageEntries){
				if(!imageEntry.trim().isEmpty()){
					String[] imageTuple = imageEntry.split(":",2);
					if(imageTuple.length!=2)
						continue;
					
					try {
						int widthEntry = Integer.parseInt(imageTuple[0].trim());
						widthRangeSet.add(widthEntry);
						imageMap.put(widthEntry, imageTuple[1].trim());						
					} catch (Exception e) {
						log.error("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
								+ ". Exception occured whiles loading image entry "+imageEntry);
					}
				}
			}
			widthRange = widthRangeSet.toArray(new Integer[widthRangeSet.size()]);
			defaultImage = getServletContext().getInitParameter("com.quakearts.bootstrap.IMAGEMAPDEFAULT");
		} else {
			widthRange=new Integer[0];
		}
	}

	public synchronized String getFileForm() {
		if(fileForm==null){
			InputStream fis = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("com/quakearts/webapp/facelets/bootstrap/html/file-form.html");
			if(fis==null){
				log.error("Cannot find resource com/quakearts/webapp/facelets/bootstrap/html/file-form.html in class path.");
				return null;
			}else{
				try {
					byte[] data = new byte[fis.available()];
					fis.read(data);
					fileForm = new String(data);
				} catch (IOException e) {
					log.error("Cannot load resource com/quakearts/webapp/facelets/bootstrap/html/file-form.html from classpath. Exception of type " + e.getClass().getName()
							+ " was thrown. Message is " + e.getMessage());
					return null;
				} finally {
					try {fis.close();} catch (Exception e) {}
				}
			}
		}
		return fileForm;
	}
	
	@Override
	protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws javax.servlet.ServletException ,IOException {
		String resource;
		OutputStream os = null;
		byte[] data;
		if(bootResources.containsKey(resource=req.getPathInfo())){
			synchronized (this) {
				data = bootResources.get(resource);
				if(data==null){
					InputStream fis = Thread.currentThread().getContextClassLoader()
							.getResourceAsStream("com/quakearts/webapp/facelets/bootstrap"+resource);
					if(fis==null){
						log.error("Cannot find resource '"+resource+"' in class path.");
						resp.sendError(404);
						return;
					} else {
						try {
							byte[] buffer = new byte[1024];
							ByteArrayOutputStream bos = new ByteArrayOutputStream();
							int read;
							while((read=fis.read(buffer))!=-1){
								bos.write(buffer,0,read);
							}
							data = bos.toByteArray();
							bootResources.put(resource, data);
						} catch (IOException e) {
							log.error("Cannot load resource "+resource+" from classpath. Exception of type " + e.getClass().getName()
									+ " was thrown. Message is " + e.getMessage());
							return;
						} finally {
							try {fis.close();} catch (Exception e) {}
						}
					}
				}
			}
			
			try {
				String contentType="";
				if(resource.endsWith(".css"))
					contentType="text/css";
				else if(resource.endsWith(".js")){
					contentType="text/javascript";
					//contentType="application/javascript";
				} else if(resource.endsWith("eot"))
					contentType="application/vnd.ms-fontobject";
				else if(resource.endsWith("svg"))
					contentType="image/svg+xml";
				else if(resource.endsWith("ttf"))
					contentType="application/font-sfnt";
				else if(resource.endsWith("woff"))
					contentType="application/font-woff";
				else if(resource.endsWith("woff2"))
					contentType="application/font-woff";	
				else if(resource.endsWith("gif"))
					contentType="image/gif";	
				else if(resource.endsWith("png"))
					contentType="image/png";
				
				resp.setContentType(contentType);
				resp.setContentLength(data.length);
				os = resp.getOutputStream();
				os.write(data);
				os.flush();
			} catch (IOException e) {
				log.error("Exception of type " + e.getClass().getName()
						+ " was thrown. Message is " + e.getMessage()
						+ ". Exception occured whiles pulling resource "+resource);
			} finally {
				try {os.close();} catch (Exception e) {}
			}
		} else if("/upload".equalsIgnoreCase(req.getPathInfo())) {
			String id = req.getParameter("id");
			if(id==null)
				resp.sendError(400);
			
			String ticket = req.getParameter("ticket");
			if(ticket==null)
				resp.sendError(400);
			
			String accept = (String) req.getSession().getAttribute(ticket+"-accept");
			if(accept==null)
				accept="";
				
			byte[] response = getFileForm()
					.replace("@root", req.getContextPath())
					.replace("@divId", "div_"+id)
					.replace("@ldgId", "img_ldg_"+id)
					.replace("@lddId", "img_ldd_"+id)
					.replace("@ldeId", "img_lde_"+id)
					.replace("@btnId", "btn_"+id)
					.replace("@spanId", "span_"+id)
					.replace("@accept", accept)
					.replace("@ticket", ticket)
					.replace("@id", id)
					.replace("@message", "")
					.replace("@complete", "")
					.getBytes();
			
			os = resp.getOutputStream();
			try{
				os.write(response);
				os.flush();
			} finally {
				os.close();
			}
		} else if("/image".equals(req.getPathInfo())
				&& req.getParameter("w")!=null 
				&& widthRange.length>0) {
			String image = null;
			int width = 0;
			int sizeMatch = 0;
			try {
				width = Integer.parseInt(req.getParameter("w"));
				for(int size:widthRange){
					sizeMatch = size;
					
					if(width<=size)
						break;
					
				}
			} catch (Exception e) {
				log.error("Exception of type " + e.getClass().getName() 
						+ " was thrown. Message is " + e.getMessage()
						+ ". Exception occured whiles parsing w parameter");
				sizeMatch = 0;
			}
			
			image = imageMap.get(sizeMatch);
			if(image==null) {
				image = defaultImage;
			}
			
			synchronized (this) {
				data = bootResources.get(image);
				if(data==null){
					InputStream fis = getServletContext().getResourceAsStream(image);
					
					if(fis==null) {
						fis = Thread.currentThread().getContextClassLoader()
								.getResourceAsStream(image);
					}
					
					if(fis==null) {
						log.error("Cannot find resource '"+image+"' in class path.");
						resp.sendError(404);
						return;
					} else {
						try {
							byte[] buffer = new byte[1024];
							ByteArrayOutputStream bos = new ByteArrayOutputStream();
							int read;
							while((read=fis.read(buffer))!=-1){
								bos.write(buffer,0,read);
							}
							data = bos.toByteArray();
							bootResources.put(image, data);
						} catch (IOException e) {
							log.error("Cannot load resource "+image+" from classpath. Exception of type " + e.getClass().getName()
									+ " was thrown. Message is " + e.getMessage());
							resp.sendError(404);
							return;
						} finally {
							try {fis.close();} catch (Exception e) {}
						}
					}
				}
			}

			try {
				String contentType="";
				if(image.endsWith("gif"))
					contentType="image/gif";	
				else if(image.endsWith("png"))
					contentType="image/png";
				else if(image.endsWith("jpeg")||resource.endsWith("jpg"))
					contentType="image/jpeg";
				else if(image.endsWith("bmp"))
					contentType="image/bmp";
				
				resp.setContentType(contentType);
				resp.setContentLength(data.length);
				os = resp.getOutputStream();
				os.write(data);
				os.flush();
			} catch (IOException e) {
				log.error("Exception of type " + e.getClass().getName()
						+ " was thrown. Message is " + e.getMessage()
						+ ". Exception occured whiles pulling resource "+image);
			} finally {
				try {os.close();} catch (Exception e) {}
			}
		}
	};
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if(req.getContentType().contains("multipart/form-data")){
			if("/upload".equalsIgnoreCase(req.getPathInfo())){
				Integer count = (Integer) req.getSession().getAttribute(FILELOADCOUNT);
				if(count==null)
					req.getSession().setAttribute(FILELOADCOUNT, 1);
				else {
					if(maxFiles>count)
						req.getSession().setAttribute(FILELOADCOUNT, count++);
					else {
						sendError(req, resp, "File load limit exceeded");
						return;
					}
				}
				
				try {
					
					Part filePart = req.getPart("upload-file");
					if(filePart==null){
						sendError(req, resp, ". Error: No file was recieved");
						return;
					}
					
					String fileName = getFileName(filePart);
					if(fileName==null){
						sendError(req, resp, ". Error: No filename indicated.");
						return;
					}
					
					if(fileName.lastIndexOf('.')>-1){
						String ext = fileName.substring(fileName.lastIndexOf('.'));
						if(forbidden.containsKey(ext.toLowerCase())){
							sendError(req, resp, ". Error: The file has an extension that is forbidden: "+ext);
						}
					}
					
					String ticket = req.getParameter("ticket");
					if(ticket==null){
						sendError(req, resp, ". Error: No ticket indicated.");
						return;
					}
					
					BootFileUpload upload = new BootFileUpload(fileName, filePart);
					req.getSession().setAttribute(ticket, upload);

					String id = req.getParameter("id");
					String message="";
					if(id==null)
						message =". Error: Application state is in error";
					
					String accept = (String) req.getSession().getAttribute(ticket+"-accept");
					if(accept==null)
						accept="";
					
					boolean allowMultiple = req.getSession().getAttribute(ticket+"-multiple")!=null;
					
					byte[] response = getFileForm()
							.replace("@root", req.getContextPath())
							.replace("@divId", "div_"+id)
							.replace("@ldgId", "img_ldg_"+id)
							.replace("@lddId", "img_ldd_"+id)
							.replace("@ldeId", "img_lde_"+id)
							.replace("@btnId", "btn_"+id)
							.replace("@spanId", "span_"+id)
							.replace("@accept", accept)
							.replace("@ticket", ticket)
							.replace("@id", id)
							.replace("@message", message)
							.replace("@complete",
									(allowMultiple? "var in_btn = $(window.parent.document.getElementById('btn_"+id+"'));\n"+
									"\nin_btn.change();\nin_btn.removeClass('collapse');":
									"$(window.parent.document.getElementById('btn_"+id+"')).change();")+
									" $(window.parent.document.getElementById('img_ldg_"+id+"')).addClass('collapse'); "
									+ "$(window.parent.document.getElementById('img_ldd_"+id+"')).removeClass('collapse'); "
									+ "$(window.parent.document.getElementById('img_lde_"+id+"')).addClass('collapse'); ")
							.getBytes();
					
					OutputStream os = null;
					os = resp.getOutputStream();
					try{
						os.write(response);
						os.flush();
					} finally {
						os.close();
					}
					
				} catch (IOException e) {
					sendError(req, resp, ". Error: File could not be stored.");
				} catch (IllegalStateException e) {
					sendError(req, resp, ". Error: File exceeded size limit.");
				}
			}
		}
	}
	
	private void sendError(HttpServletRequest req, HttpServletResponse resp, String message) throws IOException{
		String id = req.getParameter("id");
		if(id==null)
			message = message+". Application state is in error";
		
		String ticket = req.getParameter("ticket");
		if(ticket==null)
			ticket = "";
		
		String accept = (String) req.getSession().getAttribute(ticket+"-accept");
		if(accept==null)
			accept="";

		byte[] response = getFileForm()
				.replace("@root", req.getContextPath())
				.replace("@divId", "div_"+id)
				.replace("@ldgId", "img_ldg_"+id)
				.replace("@lddId", "img_ldd_"+id)
				.replace("@ldeId", "img_lde_"+id)
				.replace("@btnId", "btn_"+id)
				.replace("@spanId", "span_"+id)
				.replace("@accept", accept)
				.replace("@ticket", ticket)
				.replace("@id", id)
				.replace("@message", message)
				.replace("@complete", "$(window.parent.document.getElementById('img_ldg_"+id+"')).addClass('collapse'); "
						+ "$(window.parent.document.getElementById('img_ldd_"+id+"')).addClass('collapse'); "
						+ "$(window.parent.document.getElementById('img_lde_"+id+"')).removeClass('collapse'); "
						+ "$(window.parent.document.getElementById('btn_"+id+"')).removeClass('collapse');")
				.getBytes();
		
		OutputStream os = null;
		os = resp.getOutputStream();
		try{
			os.write(response);
			os.flush();
		} finally {
			os.close();
		}
	}
	
	private String getFileName(final Part part) {
	    final String partHeader = part.getHeader("content-disposition");
	    log.trace("Part Header = "+partHeader);
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
}

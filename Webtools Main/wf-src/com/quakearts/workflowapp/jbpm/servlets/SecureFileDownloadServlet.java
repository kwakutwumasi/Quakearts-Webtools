package com.quakearts.workflowapp.jbpm.servlets;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.taskmgmt.exe.TaskInstance;

import com.quakearts.workflowapp.jbpm.util.QuakeArtsjBPMFunctions;

public class SecureFileDownloadServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5572944925782362843L;
	private static Logger log = Logger.getLogger(SecureFileDownloadServlet.class);
    private Properties props;

    private String errorMsgTemplate="<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" + 
                                    "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" + 
                                    "<head>\n" + 
                                    "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />\n" + 
                                    "<title>Download Error</title>\n" + 
                                    "<link href=\"ua/jbpm.css\" rel=\"stylesheet\" type=\"text/css\" />\n" + 
                                    "</head>\n" + 
                                    "\n" + 
                                    "<body>\n" + 
                                    "<table class=\"msgs error\">\n" + 
                                    "	<thead>\n" + 
                                    "		<tr>\n" + 
                                    "			<th>Download Error</th>\n" + 
                                    "		</tr>\n" + 
                                    "	</thead>\n" + 
                                    "	<tbody>\n" + 
                                    "		<tr>\n" + 
                                    "			<td>${message}</td>\n" + 
                                    "		</tr>\n" + 
                                    "	</tbody>\n" + 
                                    "</table>\n" + 
                                    "</body>\n" + 
                                    "</html>";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        props=new Properties();
        ServletConfig conf = getServletConfig();
        String propsFile = conf.getInitParameter("mapping-file");
        FileInputStream fis;
        try {
            fis = new FileInputStream(propsFile);
            props.load(fis);
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void doGet(HttpServletRequest request,HttpServletResponse response){
        doPost(request,response);
    }
    
    public void doPost(HttpServletRequest request,HttpServletResponse response){
        response.reset();
        response.setContentType("text/html");
        response.setHeader("Expires","0");
        response.setHeader("Cache-Control","nostore");
        OutputStream out;
        try {
            out = response.getOutputStream();
        } catch (IOException e) {
            log.error("Cannot get response stream.",e);
            return;
        }
        try{
            if (request.getRemoteUser()!=null){
                String idString = request.getParameter("id");
                String ticket = request.getParameter("ticket");
                long id;
                try{
                    id = Long.valueOf(idString).longValue();
                }catch(NumberFormatException e){
                    log.error("Invalid Id passed in");
                    out.write(errorMsgTemplate.replace("${message}","The id supplied is invalid").getBytes());
                    out.flush();
                    out.close();
                    return;
                }
                JbpmContext cont = JbpmConfiguration.getInstance().createJbpmContext();
                TaskInstance task=null;
                if(cont != null){
                    task = cont.getTaskInstance(id);
                }else{
                    log.error("Cannot get JbpmContext.");
                    out.write(errorMsgTemplate.replace("${message}","Task cannot be loaded. Please try again").getBytes());
                    out.flush();
                    out.close();
                    return;
                }
                if(task!=null){
                    String actor = task.getActorId();
                    if(actor==null){
                        log.error("Actor is null. Cannot do security check");
                        cont.close();
                        out.write(errorMsgTemplate.replace("${message}","Cannot verify identity").getBytes());
                        out.flush();
                        out.close();
                        return;
                    }else{
                        if(!(actor.trim().equals(request.getRemoteUser().trim()))){
                            log.error("Actor is not assigned to task. Cannot download");
                            out.write(errorMsgTemplate.replace("${message}","Access to this file has been denied").getBytes());
                            out.flush();
                            out.close();
                            cont.close();
                            return;                    
                        }
                    }
                    String tempfilelocation = JbpmConfiguration.Configs.getString("com.quakearts.file.temp.location");
                    log.trace("tempfilelocation ='"+tempfilelocation+"'");
                    String storelocation = tempfilelocation+"\\jpbm_task_files";
                    log.trace("storelocation ='"+storelocation+"'");
                    String saveFileName;
                    MessageDigest digest;
                    try {
                        digest = MessageDigest.getInstance("MD5");
                    } catch (NoSuchAlgorithmException e) {
                        log.error("Cannot validate ticket");
                        out.write(errorMsgTemplate.replace("${message}","Cannot validate ticket").getBytes());
                        out.flush();
                        out.close();
                        return;
                    }
                    ProcessInstance pi;
                    pi = task.getProcessInstance();
                    saveFileName = pi.getProcessDefinition().getName()+" "+pi.getProcessDefinition().getVersion()+" "+task.getProcessInstance().getId();
                    if(digest!=null){
                        digest.update(saveFileName.getBytes());
                        byte[] hash = digest.digest();
                        saveFileName = QuakeArtsjBPMFunctions.toHexString(hash);
                    }
                    log.trace("saveFileName ='"+saveFileName+"'");
                    
                    String ext=null,content_type=null;
                    int idpoint = ticket!=null?ticket.lastIndexOf("."):-1;
                    if(idpoint>0 && idpoint<ticket.length()){
                        ext =ticket.substring(idpoint+1,ticket.length());
                    }
                    if(ext!=null)
                        content_type=props.getProperty(ext);
                    
                    FileInputStream fis;
            
                    try {
                        fis = new FileInputStream(storelocation+"\\"+saveFileName+ticket);
                        if(content_type!=null)
                            response.setContentType(content_type);
                        else
                            response.setContentType("application/octet-stream");
                        
                        response.setContentLength(fis.available());
                        response.setHeader( "Content-disposition", "value='attachment; filename=\""+saveFileName+ticket+"\"'");
                        int i = fis.read();
                        while(i!=-1){
                            out.write(i);
                            i=fis.read();
                        }
                        fis.close();
                        out.flush();
                        out.close();
                    } catch (FileNotFoundException e) {
                        log.error(e);
                        out.write(errorMsgTemplate.replace("${message}","File cannot be found").getBytes());
                        out.flush();
                        out.close();                        
                    } 
                }
                cont.close();
            }
        }catch (IOException e) {
            log.error(e);
        } catch(Exception e){
            log.error(e);                    
        }
    }
}

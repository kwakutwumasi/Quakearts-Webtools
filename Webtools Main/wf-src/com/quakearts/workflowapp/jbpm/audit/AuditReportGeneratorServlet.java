package com.quakearts.workflowapp.jbpm.audit;

import java.io.IOException;
import java.io.OutputStream;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jboss.logging.Logger;
import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.graph.def.ProcessDefinition;

public class AuditReportGeneratorServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3593871077152917280L;
	private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
    private String reportconfig;
    private static Logger log = Logger.getLogger(AuditReportGeneratorServlet.class);
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
                                    "   <thead>\n" + 
                                    "           <tr>\n" + 
                                    "                   <th>Download Error</th>\n" + 
                                    "           </tr>\n" + 
                                    "   </thead>\n" + 
                                    "   <tbody>\n" + 
                                    "           <tr>\n" + 
                                    "                   <td>${message}</td>\n" + 
                                    "           </tr>\n" + 
                                    "   </tbody>\n" + 
                                    "</table>\n" + 
                                    "</body>\n" + 
                                    "</html>";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        reportconfig = config.getInitParameter("report.config.file");
        if(reportconfig == null)
            throw new ServletException("No configuration file for reports.");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType(CONTENT_TYPE);
        doPost(request,response);
    }

    @SuppressWarnings("rawtypes")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        log.trace("Starting....");
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
            String idString = request.getParameter("id");
            String datefrom_str_start = request.getParameter("start_date_from");
            String dateto_str_start = request.getParameter("start_date_to");
            String datefrom_str_end = request.getParameter("end_date_from");
            String dateto_str_end = request.getParameter("end_date_to");
            String format = request.getParameter("format");
            String[] filters = request.getParameterValues("filter");
            
            if(log.isTraceEnabled()){
                String trace = "Parameters....\n\t\tid = "+idString
                +"\n\t\tstart_date_from = "+datefrom_str_start
                +"\n\t\tstart_date_to = "+dateto_str_start
                +"\n\t\tend_date_from = "+datefrom_str_end
                +"\n\t\tend_date_to = "+dateto_str_end
                +"\n\t\tformat = "+format+"\n\t\tfilters: ";
                if(filters!=null)
                    for(String filter: filters)
                        trace+="\n\t\t"+filter;
                log.trace(trace);                    
            }
            
            long id;
            try{
                id = Long.valueOf(idString).longValue();
            }catch(NumberFormatException e){
                log.error("Invalid id passed in");
                out.write(errorMsgTemplate.replace("${message}","The id supplied is invalid").getBytes());
                out.flush();
                out.close();
                return;
            }
                            
            Date startdatefrom=null, startdateto=null,enddatefrom=null,enddateto=null;
            try {
                startdatefrom = (datefrom_str_start!=null&&datefrom_str_start.trim().length()>0)?AuditReport.parseDate(datefrom_str_start):null;
            } catch (AuditReportException e) {
                 log.error("Invalid datefrom passed in");
                 out.write(errorMsgTemplate.replace("${message}",e.getLocalizedMessage()).getBytes());
                 out.flush();
                 out.close();
                 return;
            }

            try {
                startdateto = (dateto_str_start!=null&&dateto_str_start.trim().length()>0)?AuditReport.parseDate(dateto_str_start):null;
            } catch (AuditReportException e) {
                log.error("Invalid dateto passed in");
                out.write(errorMsgTemplate.replace("${message}",e.getLocalizedMessage()).getBytes());
                out.flush();
                out.close();
                return;
            }
            try {
                enddatefrom = (datefrom_str_end!=null&&datefrom_str_end.trim().length()>0)?AuditReport.parseDate(datefrom_str_end):null;
            } catch (AuditReportException e) {
                 log.error("Invalid datefrom passed in");
                 out.write(errorMsgTemplate.replace("${message}",e.getLocalizedMessage()).getBytes());
                 out.flush();
                 out.close();
                 return;
            }

            try {
                enddateto = (dateto_str_end!=null&&dateto_str_end.trim().length()>0)?AuditReport.parseDate(dateto_str_end):null;
            } catch (AuditReportException e) {
                log.error("Invalid dateto passed in");
                out.write(errorMsgTemplate.replace("${message}",e.getLocalizedMessage()).getBytes());
                out.flush();
                out.close();
                return;
            }

            

            JbpmContext context = JbpmConfiguration.getInstance().createJbpmContext();
            if(context != null){
                log.trace("Obtained context....");                    
                ProcessDefinition pd = context.getGraphSession().getProcessDefinition(id);
                Session session = context.getSession();
                List processList;
                Query query;
                if(startdatefrom!=null && startdateto !=null){
                    if(log.isTraceEnabled())
                        log.trace("Finding processes started between "+startdatefrom+" and "+startdateto+".....");
                    query = session.createQuery("select distinct pi from org.jbpm.graph.exe.ProcessInstance as pi join pi.processDefinition as pd where pd.id = :id and pi.start >= :startfrom and pi.start <= :startto");
                    query.setLong("id",id);
                    query.setDate("startfrom",startdatefrom);
                    query.setDate("startto",startdateto);
                }else if(enddatefrom!=null && enddateto !=null){
                    if(log.isTraceEnabled())
                        log.trace("Finding processes ended between "+enddatefrom+" and "+enddateto+".....");
                    query = session.createQuery("select distinct pi from org.jbpm.graph.exe.ProcessInstance as pi join pi.processDefinition as pd where pd.id = :id and pi.end >= :startfrom and pi.end <= :startto");
                    query.setLong("id",id);
                    query.setDate("startfrom",enddatefrom);
                    query.setDate("startto",enddateto);
                }else if(startdatefrom!=null && enddateto !=null){
                    if(log.isTraceEnabled())
                        log.trace("Finding processes started on "+startdatefrom+" and ended on "+enddateto+".....");
                    query = session.createQuery("select distinct pi from org.jbpm.graph.exe.ProcessInstance as pi join pi.processDefinition as pd where pd.id = :id and pi.start >= :startfrom and pi.end <= :endto");
                    query.setLong("id",id);
                    query.setDate("startfrom",startdatefrom);
                    query.setDate("endto",enddateto);
                }else {
                    log.trace("Finding all processes....");
                    query = session.createQuery("select distinct pi from org.jbpm.graph.exe.ProcessInstance as pi join pi.processDefinition as pd where pd.id = :id");
                    query.setLong("id",id);
                }
                log.trace("Running query....");                    
                processList = query.list();
                
                if(format==null||format.trim().length()<=0)
                    format = pd.getName();
                    
                AuditReport auditreport = new AuditReport(reportconfig);
                String report;
                try {
                    log.trace("Generating report....");                    
                    report = auditreport.generateReport(format,processList,filters);
                } catch (AuditReportException e) {
                     session.close();
                     log.error("Error generating report",e);
                     out.write(errorMsgTemplate.replace("${message}",e.getLocalizedMessage()).getBytes());
                     out.flush();
                     out.close();
                     return;
                }
                response.setContentType("application/vnd.ms-excel");
                response.setContentLength(report.length());
                response.setHeader( "Content-disposition", "value='attachment; filename=\"AuditReport"+new SimpleDateFormat("dd-MM-yyyy").format(new Date())+".xls\"'");
                out.write(report.getBytes());
                out.flush();
                out.close();
                session.close();
            }else{
                log.error("Cannot get JbpmContext.");
                out.write(errorMsgTemplate.replace("${message}","Task cannot be loaded. Please try again").getBytes());
                out.flush();
                out.close();
                return;
            }            
            log.trace("Report Generation Complete.");
        }catch (IOException e) {
            log.error(e);
        }
    }
     
}

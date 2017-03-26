package com.quakearts.workflowapp.jbpm.audit;

import com.quakearts.workflowapp.jbpm.audit.converter.DataConverter;
import com.quakearts.workflowapp.jbpm.audit.converter.DataConverterFactory;
import com.quakearts.workflowapp.jbpm.util.QuakeArtsjBPMFunctions;




import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jboss.logging.Logger;
import org.jbpm.context.exe.ContextInstance;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.taskmgmt.exe.TaskInstance;

public class AuditReport {
    private Logger log = Logger.getLogger(AuditReport.class);
    private Properties props=null;
    private StringBuffer report = new StringBuffer();
    private String htmlheader = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" + 
    "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" + 
    "<head>\n" + 
    "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />\n" + 
    "<title>Audit Report</title>\n" + 
    "<link href=\"${stylesheet}\" rel=\"stylesheet\" type=\"text/css\" />\n" + 
    "</head>\n" +
    "<body>";
    private String htmltableopen="<table class=\"auditreport\">\n";
    private String htmltableclose="</table>";
    private String htmltheadopen="<thead>\n";
    private String htmltheadclose="\n</thead>";
    private String htmlbodyopen="<tbody>\n";
    private String htmlbodyclose="\n</tbody>";
    private String htmlfooter ="</body>\n" +
    "</html>";
    private Pattern variablepattern = Pattern.compile("\\$\\{[a-zA-Z0-9_\\-\\. ]+\\}");

    public AuditReport(Properties props) {
        this.props = props;
    }
    
    public AuditReport(String propsfile) throws FileNotFoundException, IOException 
    {
        FileInputStream fis;
        fis = new FileInputStream(propsfile);
        props = new Properties();
        props.load(fis);
        fis.close();
    }
    
    @SuppressWarnings("rawtypes")
	public String generateReport(String processName, Collection processInstances, String[] filterStrings) throws AuditReportException {
        if(log.isTraceEnabled())
            log.trace("Generating report using format "+processName+"....");

        if(props==null)
            throw new IllegalStateException("Parameter props is null.");
        if(processName == null)
            throw new IllegalStateException("Parameter processName is null.");
        if(processInstances == null)
            throw new IllegalStateException("Parameter processInstances is null.");
           
        
        String headerString = props.getProperty(processName+".headers");
        String[] headers = headerString!=null?headerString.split(",+"):null;
        
        String valuesString = props.getProperty(processName+".values");
        if(valuesString==null)
            throw new AuditReportException("No format can be found of type "+processName+". Parameter "+processName+".values in props is null or does not exist.");
        
        String[] values = valuesString.split(",+");
        for(int i=0;i<values.length;i++)
            values[i] = values[i].trim();
            
        HashMap<String, DataConverter> converters = new HashMap<String, DataConverter>();
        
        for(String value:values){
            String converter = props.getProperty(processName+"."+strip(value)+".converter");
            try {                
                converters.put(value,converter ==null?DataConverterFactory.getInstance("com.quakearts.workflowapp.jbpm.audit.converters.DefaultConverter"):DataConverterFactory.getInstance(converter,props));
            } catch (IllegalAccessException e) {
                throw new AuditReportException(value+".converter "+converter+" - IllegalAccessException");
            } catch (ClassNotFoundException e) {
                throw new AuditReportException(value+".converter "+converter+" - ClassNotFoundException");
            } catch (InstantiationException e) {
                throw new AuditReportException(value+".converter "+converter+" - InstantiationException");
            }
        }
        
        
        Filter[] filters;
        if(filterStrings!= null){
            ArrayList<Filter> filterlist = new ArrayList<Filter>();
            for(String filter:filterStrings)
                if(filter!=null&&filter.trim().length()>0)
                    filterlist.add(new Filter(filter));
    
            filters = new Filter[filterlist.size()];
            filters = filterlist.toArray(filters);
        }else{
            filters = null;
        }
        String stylesheet = props.getProperty("stylesheet");
        
        if(log.isTraceEnabled()){
            StringBuffer trace = new StringBuffer();
            trace.append(headers!=null?"Headers: ":"");
            if(headers!=null)
                for(String header:headers)
                    trace.append("\n").append(header);
            trace.append("\nValues: ");
                for(String value:values)
                    trace.append("\n").append(value).append(" converter: ") .append(converters.get(value).getClass().getName());
            trace.append("\nStylesheet: ").append(stylesheet);
            log.trace(trace);
        }
        
        htmlheader=stylesheet!=null?htmlheader.replace("${stylesheet}",stylesheet):htmlheader.replace("${stylesheet}","");
        report.append(htmlheader).append(htmltableopen).append(htmltheadopen).append("<tr>");
        if(headers!=null)
            for(String header:headers)
                report.append("<th>").append(header).append("</th>");
        report.append("</tr>").append(htmltheadclose).append(htmlbodyopen);
        
        for(Object obj:processInstances){
            ProcessInstance pi;
            boolean print = true;
            if(obj instanceof ProcessInstance){
                pi = (ProcessInstance) obj;
                ContextInstance ci = pi.getContextInstance();
                if(filters!=null){
                   for(Filter filter:filters){
                       print = filter.passes(pi);
                       if(!print)
                        break;
                   }
                }
                if(print){
                    report.append("<tr>");
                    for(String value:values){
                        Object valObj;
                        if(isProcessVariable(value)){
                            valObj = getProcessVar(pi,value);
                        }else{
                            if(isVariable(value)){
                                valObj = ci.getVariable(strip(value));
                            }else{
                                valObj = value;
                            }
                        }
                        DataConverter dc = converters.get(value);
                        report.append("<td nowrap=\"nowrap\">");
                        try{
                            report.append(dc.convert(valObj));
                        }catch(Exception e){
                            report.append(valObj==null?"":valObj);
                        }
                        report.append("</td>");
                    }
                    report.append("</tr>\n");
                }
            }
        }
        report.append(htmlbodyclose).append(htmltableclose).append(htmlfooter);
        if(log.isTraceEnabled())
            log.trace("Generated report: \n\n"+report+"\n");
        return report.toString();
    }

    private String strip(String string){
        try{
            if(isVariable(string)&&string.length()>3)
                return string.substring(2,string.length()-1);
        }catch(NullPointerException e){
            return "";
        }
        return string;
    }
    
    private boolean isVariable(String string){
        return variablepattern.matcher(string).matches();
    }

    private boolean isProcessVariable(String var){
        return var.indexOf("${process.") > -1;
    }
  
    private int hash(String string){
        int h = 0;
        char val[] = string.toCharArray();

        for (int i = 0; i < val.length; i++) {
            h = 31*h + val[i];
        }
        return h;
    }

    @SuppressWarnings("rawtypes")
	private Object getProcessVar(ProcessInstance pi, String var){
        if(pi==null)
            return var;
       
        switch(hash(strip(var))){
            case 1947943594: //process.name
                return pi.getProcessDefinition().getName();
            case 261881891: //process.start
                return pi.getStart();
            case 201375964: //process.end
                return pi.getEnd();
            case 1405367664: //process.initiator
                return QuakeArtsjBPMFunctions.getFullName(QuakeArtsjBPMFunctions.getInitiator(pi));
            case -1002057347: //process.actors
                Collection tis = pi.getTaskMgmtInstance().getTaskInstances();
                StringBuffer actors = new StringBuffer();
                for(Object obj:tis){
                    if(obj instanceof TaskInstance){
                    	String actor = ((TaskInstance)obj).getActorId();
                    	String fullName = QuakeArtsjBPMFunctions.getFullName(actor);
                        actors.append(fullName==null||fullName.trim().length()==0?actor:fullName).append("<br />");
                    }
                }
                return actors.toString();
            case 261881535: //process.stage
                return QuakeArtsjBPMFunctions.getCurrentTask(pi).getName();
            case 262256463: //process.tasks
                return pi.getTaskMgmtInstance().getTaskInstances();
            case -471593903: //process.status
                if(pi.hasEnded())
                    return "Ended";
                else if(pi.isSuspended())
                    return "Suspended";
                else if(pi.isTerminatedImplicitly())
                    return "Implicitly Terminated";
                return "Running";
            default:
                return var;
        }
    }
    
    public static Date parseDate(String dateString) throws AuditReportException {
        dateString = dateString.replaceAll("'","");
        if(dateString == null)
            throw new AuditReportException("Null value passed in for parsing.");
            
        if(!Pattern.matches("[0-9]{1,2}[/-][0-9]{1,2}[/-]([0-9]{4,4}|[0-9]{2,2})",dateString))
            throw new AuditReportException(dateString +" is not a valid date string.");
        SimpleDateFormat dateFrmt;
        Date date;
        String comp = dateString.toString().replace("-","/");
        int slashindex = comp.lastIndexOf("/");
        dateFrmt = comp.substring(slashindex,comp.length()).length()==4? new SimpleDateFormat("dd/MM/yyyy"):new SimpleDateFormat("dd/MM/yy");
        try {
             date = dateFrmt.parse(comp);
             return date;
        } catch (ParseException e) {
            throw new AuditReportException(comp +" is not a valid date string. "+e.getLocalizedMessage());
        }
    }

    
    private static enum Operator {
        EQL,
        NEQ,
        GRT,
        GRE,
        LST,
        LSE;
        
        public static Operator parseOperator(String operatorString) throws AuditReportException {
            if(operatorString.equals("="))
                return EQL;
            else if(operatorString.equals(">"))
                return GRT;
            else if(operatorString.equals(">="))
                return GRE;
            else if(operatorString.equals("<"))
                return LST;
            else if(operatorString.equals("<="))
                return LSE;
            else if(operatorString.equals("!="))
                return NEQ;
            else
                throw new AuditReportException("No such operator: "+operatorString);
        }
    }
    
    private class Filter {
        private String comparator=null;
        @SuppressWarnings("rawtypes")
		private Comparable comperand=null;
        private Operator operator=null;
        private boolean comperandIsVariable = false;
        private Pattern expression = Pattern.compile("(([a-zA-Z0-9_\\-\\.]+)|(\\{[a-zA-Z0-9_\\-\\. ]+\\}))((>(=?)|<(=?))|(!?=))([a-zA-Z0-9_\\-\\.]+|('.+'))");
        private String variable="([a-zA-Z0-9_\\-\\.]+)|(\\{[a-zA-Z0-9_\\-\\. ]+\\})", date="'[0-9]{1,2}[/-][0-9]{1,2}[/-]([0-9]{4,4}|[0-9]{2,2})'", doble ="[0-9]+\\.[0-9]+[d]?", lng="[0-9]+[l]?";
        
        Filter(String filter) throws AuditReportException {
            Matcher matcher = expression.matcher(filter);
            String operator=null;
            if(matcher.find()){            
                if(log.isTraceEnabled())
                    log.trace("Tokenizing "+matcher.group());
                StringTokenizer tokenizer = new StringTokenizer(matcher.group(),"><=!",true);
                comparator = tokenizer.nextToken();
                operator = 
                        tokenizer.countTokens()>=3? tokenizer.nextToken()+tokenizer.nextToken():tokenizer.nextToken();
                StringBuffer comperandbuf = new StringBuffer();
                while(tokenizer.hasMoreTokens()){
                    comperandbuf.append(tokenizer.nextToken());
                }
                comperand = comperandbuf.toString();
                if(log.isTraceEnabled())
                    log.trace("comparator = ["+comparator+"]\noperator = ["+operator+"]\ncomperandstr = ["+comperand+"]");
            }else{
                throw new AuditReportException(filter +" is not a valid filter.");
            }
            comparator = comparator.replaceAll("(\\{)|(\\})","");                

            this.operator = Operator.parseOperator(operator);
            comperandIsVariable = Pattern.matches(variable, (CharSequence)comperand)&&!Pattern.matches(doble, (CharSequence)comperand)&&!Pattern.matches(lng, (CharSequence)comperand);
            if(log.isTraceEnabled())
                log.trace("comperandIsVariable = "+comperandIsVariable);
                
            if(!comperandIsVariable){
                if(Pattern.matches(doble, comperand.toString())){
                    log.trace("Comperand is of type Double.");
                    comperand = new Double(Double.parseDouble(comperand.toString()));
                } else if(Pattern.matches(lng, comperand.toString())){
                    log.trace("Comperand is of type Long.");
                    comperand = new Long(Long.parseLong(comperand.toString()));
                } else if(Pattern.matches(date, comperand.toString())){
                    comperand = parseDate(comperand.toString());
                } else{
                    comperand = comperand.toString().replaceAll("'","");
                }
            }else{
                comperand = comperand.toString().replaceAll("(\\{)|(\\})","");                
            }
        }
        
        @SuppressWarnings({ "rawtypes", "unchecked" })
		boolean passes(ProcessInstance pi) throws AuditReportException {
            log.trace("Comparing....");
            Object obj = pi.getContextInstance().getVariable(comparator.toString());
            if(log.isTraceEnabled())
                log.trace("Obtained ["+obj+"] of type "+(obj==null?"null":obj.getClass().getName()));

            Comparable lhs=null, rhs=null;
            if(obj == null)
                return comperand.equals("null");
            
            if(obj instanceof Comparable)
                lhs = (Comparable) obj;
            else
                throw new AuditReportException("Object "+obj.getClass().getCanonicalName()+" is not comparable.");
                
            if(comperandIsVariable){
                Object rhsObj = pi.getContextInstance().getVariable(comperand.toString());
                if(rhsObj == null)
                    return false;
                    
                if(rhsObj instanceof Comparable)
                    rhs = (Comparable) rhsObj;
                else
                    throw new AuditReportException("Object "+rhsObj.getClass().getCanonicalName()+" is not comparable.");
            }else{
                rhs = comperand;
            }
            if(log.isTraceEnabled())
                log.trace("LHS: "+lhs+" RHS: "+rhs);
                
            try{
                switch(operator){
                    case EQL:
                        return lhs.compareTo(rhs)==0;                  
                    case NEQ:
                        return lhs.compareTo(rhs)!=0;                  
                    case GRE:
                        return lhs.compareTo(rhs)==0||lhs.compareTo(rhs)>=0;                   
                    case GRT:
                        return lhs.compareTo(rhs)>=0;                    
                    case LSE:
                        return lhs.compareTo(rhs)<=0||lhs.compareTo(rhs)==0;                   
                    case LST:
                        return lhs.compareTo(rhs)<=0;                    
                    default:
                        return true;
                }
            }catch(ClassCastException e){
                throw new AuditReportException("Incomparable types: "+lhs.getClass().getCanonicalName()+" and "+rhs.getClass().getCanonicalName());
            }
        }
    }

}

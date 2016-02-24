package com.quakearts.workflowapp.jbpm.mail;

import com.quakearts.workflowapp.jbpm.util.QuakeArtsjBPMFunctions;
import com.quakearts.workflowapp.jbpm.util.filter.Filter;
import com.quakearts.workflowapp.jbpm.util.filter.FilterException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jboss.logging.Logger;
import org.jbpm.graph.exe.Comment;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.taskmgmt.exe.PooledActor;

public class MailVariableEvaluator {
    private Pattern variablepattern = Pattern.compile("\\@\\{.+\\}");
    private String var = Filter.Patterns.getPattern(Filter.Patterns.VARIABLE);
    private static Logger log = Logger.getLogger(MailVariableEvaluator.class);
    private HashMap<String,Object> vars;

    public MailVariableEvaluator() {
    }
    
    public String evaluate(String expression, ExecutionContext ex){
        vars = new HashMap<String,Object>();
        vars.put("ex",ex);
        vars.put("pi",ex.getProcessInstance());
        vars.put("ti",ex.getTaskInstance());
        vars.put("ci",ex.getContextInstance());
        vars.put("tm",ex.getTaskMgmtInstance());

        String[] variables = extractVariables(expression);
        Object obj;
        for(String variable:variables){
            obj = get(variable);
            if(obj==null)
                obj = ex.getVariable(variable);
            expression = expression.replace("@{"+variable+"}",obj!=null?toString(obj):"");
        }
        
        return expression;
    }

    private Object get(String variable){
        Object obj;
        if(variable.matches(var)){
            if(variable.indexOf(".")!=-1){
                obj = vars.get(leftMost(variable));
                StringTokenizer tokenizer = new StringTokenizer(variable,".");
                tokenizer.nextToken(); //pop the first variable 
                while(tokenizer.hasMoreTokens()){
                    try {
                        obj = get(tokenizer.nextToken(),obj);
                    } catch (Exception e) {
                        log.error("Could not resolve @{"+variable+"}. Exception "+e.getClass().getName()+" was thrown with message:"+e.getMessage(),e);
                    }
                }
                return obj;
            } else{
                return vars.get(variable);
            }
        } else {
            ArrayList<String> tokens = new ArrayList<String>();
            StringTokenizer tokenizer = new StringTokenizer(variable,"?:");
            while(tokenizer.hasMoreElements()){
                String token = tokenizer.nextToken();
                StringBuffer buf = new StringBuffer();
                if(token.indexOf("'")!=-1&&token.lastIndexOf("'")!=token.length()-1){
                    buf.append(token);
                    if(tokenizer.hasMoreElements()){
                        token = tokenizer.nextToken();
                        while(tokenizer.hasMoreElements()&&((token.lastIndexOf("''")==token.length()-2)||token.lastIndexOf("'")!=token.length()-1)){
                            buf.append(token);
                            token = tokenizer.nextToken();
                        }
                        buf.append(token);
                        token = buf.toString();
                    }
                }
                tokens.add(token);
            }
            if(tokens.size()!=3)
                return "";
            else{
                Filter filter = new Filter(vars);
                try {
                    if(filter.evaluate(tokens.get(0))){
                        if(tokens.get(1).matches(var))
                            return get(tokens.get(1));
                        else
                            return tokens.get(1);
                    }else{
                        if(tokens.get(2).matches(var))
                            return get(tokens.get(2));
                        else
                            return tokens.get(2);                        
                    }
                } catch (FilterException e) {
                    return "";
                }
            }
        }
    }
    
    private String[] extractVariables(String statment){
        Matcher matcher = variablepattern.matcher(statment);
        ArrayList<String> variable_list = new ArrayList<String>();
        while(matcher.find()){
            variable_list.add(extractVariable(matcher.group()));
        }
        return variable_list.toArray(new String[variable_list.size()]);
    }
    
    private String extractVariable(String variableString){
        return variableString!=null&&variableString.length()>3?variableString.substring(2,variableString.length()-1):"";
    }

    private Object get(String property, Object obj) throws Exception {
        if(obj==null){
            return null;
        }
        Method method;
        Object retObj;
        try {
            method = obj.getClass().getDeclaredMethod(beanize(property),new Class[0]);
            retObj = method.invoke(obj,new Object[0]);
        } catch (NoSuchMethodException e) {
            throw new Exception(e);
        } catch (IllegalAccessException e) {
            throw new Exception(e);
        } catch (InvocationTargetException e) {
            throw new Exception(e);
        }
        return retObj;     
    }
    
    private String beanize(String property){
        return "get"+property.substring(0,1).toUpperCase()+property.substring(1,property.length());
    }
    
    private String leftMost(String variable){
        return variable.substring(0,variable.indexOf("."));
    }

    @SuppressWarnings("rawtypes")
	public String toString(Object obj) {
        if(obj instanceof Comment){
            StringBuffer values = new StringBuffer();
            Comment comment = (Comment) obj;
            values.append("Comment by ").append(QuakeArtsjBPMFunctions.getFullName(comment.getActorId()));
            try{
                values.append(" concerning ").append(comment.getTaskInstance().getTask().getName()).append(":\r\n");
            }catch(Exception e){
                values.append(":\r\n");
            }
            values.append(comment.getMessage()).append("\r\n");
            return values.toString();
        }else if(obj instanceof Collection){
            StringBuffer values = new StringBuffer();
            Iterator iter = ((Collection) obj).iterator();
            while(iter.hasNext()){
                values.append(toString(iter.next()));
            }
            return values.toString();
        }else if(obj instanceof PooledActor){
            return (((PooledActor)obj).getActorId())+";";
        }
        return obj!=null?obj.toString():"";
    }
}

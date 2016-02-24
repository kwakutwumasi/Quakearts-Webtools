package com.quakearts.workflowapp.jbpm.util.filter;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class Filter {
    private static  String variable="(([a-zA-Z][a-zA-Z0-9_\\-\\.]*)|(\\{[a-zA-Z0-9_\\-\\. ]+\\}))", 
                    date="'[0-9]{1,2}[/-][0-9]{1,2}[/-]([0-9]{4,4}|[0-9]{2,2})'", 
                    doble ="([0-9]+\\.[0-9]+[d]?)", 
                    lng="([0-9]+[l])",
                    integ = "([0-9]+)",
                    operator="((>(=?)|<(=?))|(!?=))", 
                    stringLiteral = "'(([.[^']])+('')*)+'",
                    logicalOperator ="[\\&\\|]?", 
                    expression = "("+variable+operator+"("+variable+"|"+stringLiteral+"|"+date+"|"+doble+"|"+lng+"|"+integ+"))", 
                    logicalExpression = (new StringBuilder("(\\(*(")).append(expression).append(logicalOperator).append(")+\\)*").append(logicalOperator).append(")+").toString();
    @SuppressWarnings("rawtypes")
	private HashMap comparatorSource;
   
    public static enum Patterns {
        VARIABLE,
        DATE,
        DOUBLE,
        LONG,
        INTEGER,
        OPERATOR,
        STRING_LITERAL,
        LOGICAL_OPERATOR,
        EXPRESSION,
        LOGICAL_EXPRESSION;
        
        public static String getPattern(Patterns pat){
            switch(pat){
                case VARIABLE:
                    return variable;
                case DATE:
                    return date;
                case DOUBLE:
                    return doble;
                case LONG:
                    return lng;
                case INTEGER:
                    return integ;
                case OPERATOR:
                    return operator;
                case STRING_LITERAL:
                    return stringLiteral;
                case LOGICAL_OPERATOR:
                    return logicalOperator;
                case EXPRESSION:
                    return expression;
                case LOGICAL_EXPRESSION:
                    return logicalExpression;
                default:
                    return null;
            }
        }
    }
   
    @SuppressWarnings("rawtypes")
	public Filter(HashMap comparatorSource){
        this.comparatorSource=comparatorSource;
    }
   
    @SuppressWarnings("rawtypes")
	public HashMap getComparatorSource(){
        return comparatorSource;
    }
    
    @SuppressWarnings("rawtypes")
	public void setComparatorSource(HashMap comparatorSource){
        this.comparatorSource=comparatorSource;
    }

    private ArrayList<String> parseFilter(String filter) throws FilterException {
        ArrayList<String> tokens = new ArrayList<String>();
        if(Pattern.matches(logicalExpression,filter)){
            StringTokenizer tokenizer = new StringTokenizer(filter,"&|()",true);
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
        }
        return tokens;
    }
    
    public boolean evaluate(String filter) throws FilterException {
        Stack<Boolean> outputStack = new Stack<Boolean>();
        Stack<String> operatorStack = new Stack<String>();
        ArrayList<String> tokens = parseFilter(filter);
        for(String part:tokens){
            if(part.equals("(")){
              operatorStack.push(part);
            }else if (part.equals(")")){
                while(!operatorStack.peek().equals("(")){
                    String op = operatorStack.pop();
                    if(op.equals("&")){
                        Boolean rhs = outputStack.pop();
                        Boolean lhs = outputStack.pop();
                        outputStack.push(lhs&&rhs);
                    }else if(op.equals("|")){
                        Boolean rhs = outputStack.pop();
                        Boolean lhs = outputStack.pop();
                        outputStack.push(lhs||rhs);
                    }
                }
                operatorStack.pop();
            }else if(Pattern.matches(expression,part)){
                Condition condition = parseCondition(part);
                outputStack.push(Boolean.valueOf(condition.passes()));
            }else{
                if(part.equals("&")){
                  operatorStack.push(part);
                }else{
                    if(operatorStack.size()<=0)
                        operatorStack.push(part);
                    else if(operatorStack.peek().equals("&")){
                        operatorStack.pop();
                        operatorStack.push(part);
                        Boolean rhs = outputStack.pop();
                        Boolean lhs = outputStack.pop();
                        outputStack.push(lhs&&rhs);
                    }else 
                      operatorStack.push(part);                      
                }
            }
        }

        if(operatorStack.size()!=(outputStack.size()-1))
            throw new FilterException("Invalid formula");                

        while(operatorStack.size()>0){
            String op = operatorStack.pop();
            if(op.equals("&")){
                Boolean rhs = outputStack.pop();
                Boolean lhs = outputStack.pop();
                outputStack.push(lhs&&rhs);
            }else if(op.equals("|")){
                Boolean rhs = outputStack.pop();
                Boolean lhs = outputStack.pop();
                outputStack.push(lhs||rhs);
            }else
                throw new FilterException("Invalid formula");                
        }
        return outputStack.pop();
    }
    
    private Condition parseCondition(String conditionStr)throws FilterException{
        Condition condition = new Condition();
        String operator=null;
        StringTokenizer tokenizer = new StringTokenizer(conditionStr,"><=!",true);
        condition.comparator = tokenizer.nextToken();
        operator = tokenizer.countTokens()>=3? tokenizer.nextToken()+tokenizer.nextToken():tokenizer.nextToken();
        StringBuffer comperandbuf = new StringBuffer();
        while(tokenizer.hasMoreTokens()){
            comperandbuf.append(tokenizer.nextToken());
        }
        condition.comperand = comperandbuf.toString();           
        condition.comparator = condition.comparator.replaceAll("(\\{)|(\\})","");                
        condition.operator = Operator.parseOperator(operator);
        
        condition.comperandIsVariable = Pattern.matches(variable, (CharSequence)condition.comperand)&&!Pattern.matches(doble, (CharSequence)condition.comperand)&&!Pattern.matches(lng, (CharSequence)condition.comperand);
            
        if(!condition.comperandIsVariable){
            if(Pattern.matches(doble, condition.comperand.toString())){
                condition.comperand = new Double(Double.parseDouble(condition.comperand.toString()));
            } else if(Pattern.matches(lng, condition.comperand.toString())){
                condition.comperand = new Long(Long.parseLong(condition.comperand.toString()));
            } else if(Pattern.matches(date, condition.comperand.toString())){
                condition.comperand = parseDate(condition.comperand.toString());
            } else if(Pattern.matches(integ, condition.comperand.toString())){
                condition.comperand = new Integer(Integer.parseInt(condition.comperand.toString()));
            } else{
                condition.comperand = condition.comperand.toString().replaceAll("'","");
            }
        }else{
            condition.comperand = condition.comperand.toString().replaceAll("(\\{)|(\\})","");                
        }
        return condition;
    }
    
    private static Date parseDate(String dateString) throws FilterException {
        dateString = dateString.replaceAll("'","");
        if(dateString == null)
            throw new FilterException("Null value passed in for parsing.");
            
        if(!Pattern.matches("[0-9]{1,2}[/-][0-9]{1,2}[/-]([0-9]{4,4}|[0-9]{2,2})",dateString))
            throw new FilterException(dateString +" is not a valid date string.");
        SimpleDateFormat dateFrmt;
        Date date;
        String comp = dateString.toString().replace("-","/");
        int slashindex = comp.lastIndexOf("/");
        dateFrmt = comp.substring(slashindex,comp.length()).length()==4? new SimpleDateFormat("dd/MM/yyyy"):new SimpleDateFormat("dd/MM/yy");
        try {
             date = dateFrmt.parse(comp);
             return date;
        } catch (ParseException e) {
            throw new FilterException(comp +" is not a valid date string. "+e.getLocalizedMessage());
        }
    }
    
    private Object get(String variable) throws FilterException {
        if(variable.indexOf(".")!=-1){
            Object obj = comparatorSource.get(leftMost(variable));
            StringTokenizer tokenizer = new StringTokenizer(variable,".");
            tokenizer.nextToken();//pop the next tocken off
            while(tokenizer.hasMoreTokens()){
                try {
                    obj = get(tokenizer.nextToken(),obj);
                } catch (Exception e) {
                    throw new FilterException("Could not resolve @{"+variable+"}. Exception "+e.getClass().getName()+" was thrown with message:"+e.getMessage(),e);
                }
            }
            return obj;
        }else{
            return comparatorSource.get(variable);
        }
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
    
    private static enum Operator {
        EQL,
        NEQ,
        GRT,
        GRE,
        LST,
        LSE;
        
        private static Operator parseOperator(String operatorString) throws FilterException {
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
                throw new FilterException("No such operator: "+operatorString);
        }
    }
    
//    private static enum LogicalOperator{
//        AND,
//        OR;
//        
//        public static LogicalOperator parseLogicalOperator(String operatorString) throws FilterException {
//            if(operatorString.equalsIgnoreCase("&"))
//                return AND;
//            else if(operatorString.equalsIgnoreCase("|"))
//                return OR;
//            else
//                throw new FilterException("No such operator: "+operatorString);
//        }
//    }
    
    @SuppressWarnings("rawtypes")
    private class Condition{
        String comparator=null;
		Comparable comperand=null;
        Operator operator=null;
        boolean comperandIsVariable = false;
        
        @SuppressWarnings("unchecked")
		boolean passes() throws FilterException{
            Object obj = get(comparator.toString());

            Comparable lhs=null, rhs=null;
            if(obj == null)
                return comperand.equals("null");
            
            if(obj instanceof Comparable)
                lhs = (Comparable) obj;
            else
                lhs = obj.toString();
                
            if(comperandIsVariable){
                Object rhsObj = get(comperand.toString());
                if(rhsObj == null)
                    return false;
                    
                if(rhsObj instanceof Comparable)
                    rhs = (Comparable) rhsObj;
                else
                    rhs = obj.toString();
            }else{
                rhs = comperand;
            }
                
            try{
                switch(operator){
                    case EQL:
                        return lhs.compareTo(rhs)==0;                  
                    case NEQ:
                        return lhs.compareTo(rhs)!=0;                  
                    case GRE:
                        return lhs.compareTo(rhs)==0||lhs.compareTo(rhs)>=0;                   
                    case GRT:
                        return lhs.compareTo(rhs)>0;                    
                    case LSE:
                        return lhs.compareTo(rhs)<=0||lhs.compareTo(rhs)==0;                   
                    case LST:
                        return lhs.compareTo(rhs)<0;                    
                    default:
                        return true;
                }
            }catch(ClassCastException e){
                throw new FilterException("Incomparable types: "+lhs.getClass().getCanonicalName()+" and "+rhs.getClass().getCanonicalName());
            }
        }
    }
    
}

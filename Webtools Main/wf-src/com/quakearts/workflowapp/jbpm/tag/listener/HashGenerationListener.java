package com.quakearts.workflowapp.jbpm.tag.listener;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.jbpm.jsf.JbpmActionListener;
import org.jbpm.jsf.JbpmJsfContext;

import com.quakearts.webapp.security.util.HashPassword;

public class HashGenerationListener implements JbpmActionListener {

	private final ValueExpression valueExpression,inputExpression,saltExpression,algorithmExpression,iterationsExpression;
	
	public HashGenerationListener(ValueExpression valueExpression,ValueExpression inputExpression,ValueExpression saltExpression, ValueExpression algorithmExpression,ValueExpression iterationsExpression){
		this.valueExpression = valueExpression;
		this.inputExpression = inputExpression;
		this.saltExpression = saltExpression;
		this.algorithmExpression = algorithmExpression;
		this.iterationsExpression = iterationsExpression;
	}
	
	@Override
	public String getName() {
		return "generateHash";
	}

	@Override
	public void handleAction(JbpmJsfContext context, ActionEvent event) {
        ELContext elcontext;
        FacesContext facescontext;
        String input, salt, algorithm;
        Object inputValue, saltValue, algorithmValue;
        try{
            facescontext = FacesContext.getCurrentInstance();
            elcontext = facescontext.getELContext();
            
            inputValue = inputExpression.getValue(elcontext);
            if(inputValue == null){
                context.setError("Cannot hash. Input is null.");
                return;
            }else{
                if(inputValue instanceof String){
                    input = (String) inputValue;
                }else{
                	input = inputValue.toString();
                }
            }

            saltValue = saltExpression.getValue(elcontext);
            if(saltValue == null){
            	salt= HashPassword.DEFAULT_SALT;
            }else{
    	        if(inputValue instanceof String){
    	            salt = (String) saltValue;
    	        }else{
    	        	salt = saltValue.toString();
    	        }
            }
            
            algorithmValue = algorithmExpression.getValue(elcontext);
            if(algorithmValue == null){
            	algorithm="MD5";
            }else{
    	        if(algorithmValue instanceof String){
    	            algorithm = (String) algorithmValue;
    	        }else{
    	        	algorithm = algorithmValue.toString();
    	        }
            }
            
            Object iterationsValue = iterationsExpression.getValue(elcontext);
            
            int iterations;
            if(iterationsValue!=null){
            	try{
            		if(iterationsValue instanceof Number)
            			iterations = ((Number)iterationsValue).intValue();
            		else
            			iterations = Integer.parseInt(iterationsValue.toString());
            	}catch(Exception ex){
            		iterations = 10;
            	}
            }else
            	iterations = 10;
            
            HashPassword hashString = new HashPassword();
            hashString.setAlgorithm(algorithm);
            hashString.setPlainText(input);
            hashString.setSalt(salt);
            hashString.setIterations(iterations);
            
            valueExpression.setValue(elcontext, hashString.toString());
        }catch(Exception ex){
            context.setError("Cannot hash.",ex);
            return;
        }        
        return;
	}

}

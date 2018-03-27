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
package com.quakearts.webapp.facelets.bootstrap.components;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.el.ValueExpression;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;

import com.quakearts.webapp.facelets.util.ObjectExtractor;

public class BootDateButton extends HtmlInputText {
	public static final String COMPONENT_FAMILY = "com.quakearts.bootstrap.date";
    public static final String RENDERER_TYPE = "com.quakearts.bootstrap.date.renderer";
	private static final String DMY = "dmy";
    private DateFormat formatVal;
    private int minAsInt=-1,maxAsInt=-1, hourStep = -1, minStep = -1, secondStep = -1;
    private boolean checkedNullable = false, nullable = false, checked24hr = false, is24hr = false;
    
    private static class FormatStringProducer {
    	private String separator;
    	private String format;
		
    	FormatStringProducer(String separator, String format) {
			this.separator = separator;
			this.format = format;
		}
    	
    	String getFormatString(boolean isFirst){
    		return (isFirst?"":separator)+format;
    	}
    }
    
    public static class DateFormat {
    	private static final String checkRegex = "(m|y|my|(dm)y?)?(h|(hn)s?)?";
    	private Set<Character> formatChars = new HashSet<>();
    	private String dateFormatString, format;
    	
    	private static final Map<Character, FormatStringProducer> formatProducerMap = new HashMap<>(); 
    	
    	static {
    		formatProducerMap.put('d', new FormatStringProducer("", "dd"));
    		formatProducerMap.put('m', new FormatStringProducer("/", "MM"));
    		formatProducerMap.put('y', new FormatStringProducer("/", "yyyy"));
    		formatProducerMap.put('h', new FormatStringProducer(" ", "HH"));
    		formatProducerMap.put('n', new FormatStringProducer(":", "mm"));
    		formatProducerMap.put('s', new FormatStringProducer(":", "ss"));
    	}
    	
    	public DateFormat(String formatString) {
    		if(!formatString.matches(checkRegex)){
    			throw new IllegalArgumentException(formatString+" is not a valid format string.");
    		}

    		format = formatString;    		
    		char[] parts = formatString.toCharArray();
    		StringBuilder builder = new StringBuilder();
    		for(char part:parts){
    			formatChars.add(part);
    			builder.append(formatProducerMap.get(part).getFormatString(builder.length() == 0));
    		}
    		dateFormatString = builder.toString();
	}
    	
    	public boolean hasDay(){
    		return formatChars.contains('d');
    	}

    	public boolean hasMonth(){
    		return formatChars.contains('m');
    	}
    	
    	public boolean hasYear(){
    		return formatChars.contains('y');
    	}
    	
    	public boolean hasHour(){
    		return formatChars.contains('h');
    	}

    	public boolean hasMinute(){
    		return formatChars.contains('n');
    	}
    	
    	public boolean hasSeconds(){
    		return formatChars.contains('s');
    	}

    	public String getDateFormatString(){
    		return dateFormatString;
    	}
    	
    	public String getFormat() {
			return format;
		}
    }
    
    public String getFamily()
    {
        return COMPONENT_FAMILY;
    }


	public int minAsInt() {
		if(minAsInt==-1){
			ValueExpression minExpression = getValueExpression("min");
			String min;
			if(minExpression !=null){
				try {
					Object obj = minExpression.getValue(FacesContext.getCurrentInstance().getELContext());
					minAsInt = obj instanceof Number?((Number)obj).intValue():Integer.parseInt(obj.toString());
					if(minAsInt>0)
						minAsInt *=-1;
				} catch (Exception e) {
				}
			}else if((min=(String)getAttributes().get("min"))!=null){
				try {
					minAsInt = Integer.parseInt(min);
					if(minAsInt>0)
						minAsInt *=-1;
				} catch (Exception e) {
				}
			} else {
				minAsInt = 0;
			}
		}
		return minAsInt;
	}

	public int maxAsInt() {
		if(maxAsInt==-1){
			ValueExpression maxExpression = getValueExpression("max");
			String max;
			if(maxExpression !=null){
				try {
					Object obj = maxExpression.getValue(FacesContext.getCurrentInstance().getELContext());
					maxAsInt = obj instanceof Number?((Number)obj).intValue():Integer.parseInt(obj.toString());
				} catch (Exception e) {
				}
			} else if((max=(String)getAttributes().get("max"))!=null){
				try {
					maxAsInt = Integer.parseInt(max);
				} catch (Exception e) {
				}
			} else {
				maxAsInt = 0;
			}
		}
		return maxAsInt;
	}

	public DateFormat formatVal() {
		if(formatVal==null){
			ValueExpression formatExpression = getValueExpression("format");
			String format;
			if(formatExpression !=null){
				try {
					formatVal = new DateFormat(formatExpression.getValue(FacesContext.getCurrentInstance().getELContext()).toString());
				} catch (Exception e) {
				}
			} else if((format=(String)getAttributes().get("format")) !=null){
				formatVal = new DateFormat(format);
			} else{
				formatVal = new DateFormat(DMY);
			}
		}
		return formatVal;
	}
	
	public int hourStepVal(){
		if(hourStep == -1){
			ValueExpression expression = getValueExpression("hourStep");
			if(expression != null)
				hourStep = ObjectExtractor.extractInteger(expression, FacesContext.getCurrentInstance().getELContext());
			else if(getAttributes().containsKey("hourStep"))
				try {
					hourStep = Integer.parseInt(getAttributes().get("hourStep").toString());
				} catch (Exception e) {
					hourStep = 1;
				}
			else
				hourStep = 1;
		}
		return hourStep;
	}
	
	public int minuteStepVal(){
		if(minStep == -1){
			ValueExpression expression = getValueExpression("minuteStep");
			if(expression != null)
				minStep = ObjectExtractor.extractInteger(expression, FacesContext.getCurrentInstance().getELContext());
			else if(getAttributes().containsKey("minuteStep"))
				try {
					minStep = Integer.parseInt(getAttributes().get("minuteStep").toString());
				} catch (Exception e) {
					minStep = 1;
				}
			else
				minStep = 1;
		}
		return minStep;
	}
	
	public int secondStepVal(){
		if(secondStep == -1){
			ValueExpression expression = getValueExpression("secondStep");
			if(expression != null)
				secondStep = ObjectExtractor.extractInteger(expression, FacesContext.getCurrentInstance().getELContext());
			else if(getAttributes().containsKey("secondStep"))
				try {
					secondStep = Integer.parseInt(getAttributes().get("secondStep").toString());
				} catch (Exception e) {
					secondStep = 1;
				}
			else
				secondStep = 1;
		}
		return secondStep;
	}
	
	@Override
	public String getDefaultEventName() {
		return "change";
	}
	
	@Override
	public Collection<String> getEventNames() {
		return Collections.singletonList("change");
	}
	
    @Override
	public void restoreState(FacesContext context, Object obj) {
		Object[] state = (Object[])obj;
		formatVal = (DateFormat) state[0];
		minAsInt = (Integer) state[1];
		maxAsInt = (Integer) state[2];
		nullable = (Boolean) state[3];
		super.restoreState(context, state[4]);
	}

	@Override
	public Object saveState(FacesContext context) {
		return new Object[]{formatVal(),minAsInt(),maxAsInt(), nullable(),super.saveState(context)};
	}

	public boolean nullable(){
		if(!checkedNullable){
			ValueExpression nullableExpression = getValueExpression("nullable");
			if(nullableExpression !=null){
				nullable = ObjectExtractor.extractBoolean(nullableExpression, FacesContext.getCurrentInstance().getELContext());
			} else {
				nullable = Boolean.parseBoolean((String) getAttributes().get("nullable"));
			}
			checkedNullable = true;
		}		
		return nullable;
	}
	
	public boolean timeIs24Hours(){
		if(!checked24hr){
			ValueExpression nullableExpression = getValueExpression("timeIs24hour");
			if(nullableExpression !=null){
				is24hr = ObjectExtractor.extractBoolean(nullableExpression, FacesContext.getCurrentInstance().getELContext());
			} else {
				if(getAttributes().containsKey("timeIs24hour"))
					is24hr = Boolean.parseBoolean((String) getAttributes().get("timeIs24hour"));
				else
					is24hr = true;
			}
			checked24hr = true;
		}
		return is24hr;
	}
	
	public String get(String attribute) {
		String attributeValue = ObjectExtractor
				.extractString(getValueExpression(attribute), getFacesContext()
						.getELContext());
		if (attributeValue == null)
			attributeValue = (String) getAttributes().get(attribute);

		return attributeValue;
	}
	
	@Override
	public String getRendererType() {
		return RENDERER_TYPE;
	}
	
	@Override
	public void setRendererType(String rendererType) {
	}
}

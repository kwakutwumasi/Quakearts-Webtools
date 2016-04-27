package com.quakearts.webapp.facelets.bootstrap.components;

import java.util.Collection;
import java.util.Collections;

import javax.el.ValueExpression;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;

import com.quakearts.webapp.facelets.util.ObjectExtractor;

public class BootDateButton extends HtmlInputText {
	public static final String COMPONENT_FAMILY = "com.quakearts.bootstrap.date";
    public static final String RENDERER_TYPE = "com.quakearts.bootstrap.date.renderer";
    private DateFormat formatVal;
    private int minAsInt=-1,maxAsInt=-1;
    private boolean checkedNullable = false, nullable = false;
    
    public static enum DateFormat{
    	DAYMONTH("dd/MM","dm"),
    	MONTH("MM","m"),
    	MONTHYEAR("MM/yyyy","my"),
    	YEAR("yyyy","y"),
    	DAYMONTHYEAR("dd/MM/yyyy","dmy");
    	
    	private String formatString, formatValue;
    	private DateFormat(String formatString, String formatValue) {
			this.formatString = formatString;
			this.formatValue = formatValue;
		}
    	
    	public static DateFormat getFormat(String format){
    		if(format == null)
    			return DAYMONTHYEAR;
    		
    		if(format.equals("dm")){
    			return DAYMONTH;
    		} else if(format.equals("m")){
    			return MONTH;
    		} else if(format.equals("my")){
    			return MONTHYEAR;
    		} else if(format.equals("y")){
    			return YEAR;
    		}  else {
    			return DAYMONTHYEAR;
    		}
    	}
    	
    	public String getFormatString(){
    		return formatString;
    	}
    	
    	public String getFormatValue() {
			return formatValue;
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
					formatVal = DateFormat.getFormat(formatExpression.getValue(FacesContext.getCurrentInstance().getELContext()).toString());
				} catch (Exception e) {
				}
			} else if((format=(String)getAttributes().get("format")) !=null){
				formatVal = DateFormat.getFormat(format);
			} else{
				formatVal = DateFormat.DAYMONTHYEAR;
			}
		}
		return formatVal;
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

package com.quakearts.webapp.facelets.tag.input;

import java.util.Collection;
import java.util.Collections;

import javax.el.ValueExpression;
import javax.faces.component.UIInput;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.context.FacesContext;

import com.quakearts.webapp.facelets.util.ObjectExtractor;

public class DateComponent extends UIInput implements ClientBehaviorHolder
{

    public static final String COMPONENT_FAMILY = "com.quakearts.facelets.input.date";
    public static final String COMPONENT_TYPE = "com.quakearts.facelets.input.date";
    private DateFormat formatVal = DateFormat.DAYMONTHYEAR;
    private int minAsInt,maxAsInt;
    private String yearStyle, dayStyle, monthStyle;
    private boolean checkedNullable = false, nullable = false;
    
    public static enum DateFormat{
    	DAYMONTH,
    	MONTH,
    	MONTHYEAR,
    	YEAR,
    	DAYMONTHYEAR;
    	
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
    		switch(this){
	    		case DAYMONTH:
	    			return "dd/MM";
	    		case DAYMONTHYEAR:
	    			return "dd/MM/yyyy";
	    		case MONTH:
	    			return "MM";
	    		case MONTHYEAR:
	    			return "MM/yyyy";
	    		default:
	    			return "yyyy";
    		}
    	}
    }
       
    public String getFamily()
    {
        return COMPONENT_FAMILY;
    }


	public int minAsInt() {
		if(minAsInt==0){
			String min;
			ValueExpression minExpression;
			if((minExpression=getValueExpression("min"))!=null){
				try {
					Object obj = minExpression.getValue(FacesContext.getCurrentInstance().getELContext());
					minAsInt = obj instanceof Number?((Number)obj).intValue():Integer.parseInt(obj.toString());
					if(minAsInt>0)
						minAsInt *=-1;
				} catch (Exception e) {
				}
			}else if((min=(String) getAttributes().get("min"))!=null){
				try {
					minAsInt = Integer.parseInt(min);
					if(minAsInt>0)
						minAsInt *=-1;
				} catch (Exception e) {
				}
			}
		}
		return minAsInt;
	}

	public int maxAsInt() {
		if(maxAsInt==0){
			ValueExpression maxExpression;
			String max;
			if((maxExpression=getValueExpression("max"))!=null){
				try {
					Object obj = maxExpression.getValue(FacesContext.getCurrentInstance().getELContext());
					maxAsInt = obj instanceof Number?((Number)obj).intValue():Integer.parseInt(obj.toString());
				} catch (Exception e) {
				}
			} else if((max=(String) getAttributes().get("max"))!=null){
				try {
					maxAsInt = Integer.parseInt(max);
				} catch (Exception e) {
				}
			}
		}
		return maxAsInt;
	}

	public String yearStyle() {
		if(yearStyle==null){
			ValueExpression yearStyleExpression;
			if((yearStyleExpression=getValueExpression("yearStyle"))!=null){
				try {
					yearStyle = ObjectExtractor.extractString(yearStyleExpression, getFacesContext().getELContext());
				} catch (Exception e) {
				}
			} else {
				yearStyle = (String) getAttributes().get("yearStyle");
			}			
		}
		return yearStyle;
	}
	
	public String dayStyle() {
		if(dayStyle==null){
			ValueExpression dayStyleExpression;
			if((dayStyleExpression=getValueExpression("dayStyle"))!=null){
				try {
					dayStyle = ObjectExtractor.extractString(dayStyleExpression, getFacesContext().getELContext());
				} catch (Exception e) {
				}
			} else {
				dayStyle = (String) getAttributes().get("dayStyle");
			}
		}
		return dayStyle;
	}
	
	public String monthStyle() {
		if(monthStyle==null){
			ValueExpression monthStyleExpression;
			if((monthStyleExpression=getValueExpression("monthStyle"))!=null){
				try {
					monthStyle = ObjectExtractor.extractString(monthStyleExpression, getFacesContext().getELContext());
				} catch (Exception e) {
				}
			} else {
				monthStyle = (String) getAttributes().get("monthStyle");
			}
		}
		return monthStyle;
	}
	
	public DateFormat formatVal() {
		if(formatVal==null){
			ValueExpression formatExpression;
			String format;
			if((formatExpression=getValueExpression("format"))!=null){
				try {
					formatVal = DateFormat.getFormat(formatExpression.getValue(FacesContext.getCurrentInstance().getELContext()).toString());
				} catch (Exception e) {
				}
			} else if((format=(String) getAttributes().get("format")) !=null){
				formatVal = DateFormat.getFormat(format);
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
		Object oldState = state[3];
		
		super.restoreState(context, oldState);
	}


	@Override
	public Object saveState(FacesContext context) {
		Object superState= super.saveState(context);
		Object[] state= new Object[4];
		state[0] = formatVal;
		state[1] = minAsInt;
		state[2] = maxAsInt;
		state[3] = superState;
		return state;
	}

	public boolean nullable(){
		if(!checkedNullable){
			ValueExpression nullableExpression;
			Object nullableObject;
			if((nullableExpression=getValueExpression("nullable"))!=null){
				nullableObject = nullableExpression.getValue(FacesContext.getCurrentInstance().getELContext());
			} else {
				nullableObject = getAttributes().get("nullable");
			}
			if(nullableObject instanceof Boolean)
				nullable = ((Boolean)nullableObject);
			else if(nullableObject !=null)
				nullable = Boolean.parseBoolean(nullableObject.toString());
			checkedNullable = true;
		}		
		return nullable;
	}
	
}

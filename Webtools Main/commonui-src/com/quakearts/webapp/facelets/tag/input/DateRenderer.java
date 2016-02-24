package com.quakearts.webapp.facelets.tag.input;

import com.quakearts.webapp.facelets.tag.input.DateComponent.DateFormat;
import com.sun.faces.renderkit.Attribute;
import com.sun.faces.renderkit.AttributeManager;
import com.sun.faces.renderkit.RenderKitUtils;
import com.sun.faces.renderkit.html_basic.HtmlBasicInputRenderer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

public class DateRenderer extends HtmlBasicInputRenderer
{

    public static final String RENDERER_TYPE = "com.quakearts.facelets.input.date.renderer";
    public static final int[] MONTHDAYS = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final Map<Integer,String> months = new TreeMap<Integer, String>();
    private static final Attribute[] INPUT_ATTRIBUTES =
            AttributeManager.getAttributes(AttributeManager.Key.INPUTTEXT);
    
    static{
    	months.put(1,"Jan");
    	months.put(2,"Feb");
    	months.put(3,"Mar");
    	months.put(4,"Apr");
    	months.put(5,"May");
    	months.put(6,"Jun");
    	months.put(7,"Jul");
    	months.put(8,"Aug");
    	months.put(9,"Sept");
    	months.put(10,"Oct");
    	months.put(11,"Nov");
    	months.put(12,"Dec");
    }
    
    int dayInt,monthInt,yearInt;
 
    @Override
    protected void getEndTextToRender(FacesContext context, UIComponent component, String currentValue)
        throws IOException
    {
        DateComponent dateComponent;
        if(component instanceof DateComponent)
        {
            dateComponent = (DateComponent)component;
        } else
        {
            throw new IOException("Component must be of type "+DateComponent.class.getName());
        }
        Calendar date;
        date = new GregorianCalendar();
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat(((DateComponent)component).formatVal().getFormatString());
        if(currentValue!=null){
			try {
				date.setTime(formatter.parse(currentValue));
			} catch (ParseException e) {
			}
        } else {
        	if(!dateComponent.nullable())
        		currentValue = formatter.format(date.getTime());
        }
        
        if(dateComponent.formatVal()==DateFormat.DAYMONTH||dateComponent.formatVal()==DateFormat.DAYMONTHYEAR)
        	dayInt = date.get(Calendar.DAY_OF_MONTH);
        else
        	dayInt = 1;
        
        if(dateComponent.formatVal()!=DateFormat.YEAR)
        	monthInt = (date.get(Calendar.MONTH)+1);
        else
        	monthInt = 1;

        yearInt = date.get(Calendar.YEAR);
		
		String id=component.getClientId(context), idJs = id.replace(":", "_");
        
        ResponseWriter writer = context.getResponseWriter();
        if(dateComponent.formatVal()== DateFormat.DAYMONTH || dateComponent.formatVal()== DateFormat.DAYMONTHYEAR){
        	generateSelect("day", id, idJs, getDaysMap(date.get(Calendar.MONTH)), writer, dateComponent, currentValue==null);
        }
        if(!(dateComponent.formatVal()== DateFormat.YEAR)){
        	generateSelect("month", id, idJs, months, writer, dateComponent, currentValue==null);
        }
        if((dateComponent.formatVal()!= DateFormat.DAYMONTH) && (dateComponent.formatVal()!= DateFormat.MONTH)){
        	generateSelect("year", id, idJs, getYearsMap(dateComponent.maxAsInt(), dateComponent.minAsInt()), writer, dateComponent, currentValue==null);
        }
        
        writer.startElement("input", dateComponent);
        writer.writeAttribute("id", id, null);
        writer.writeAttribute("name",id, null);
        writer.writeAttribute("type", "hidden", null);
        if (currentValue != null) {
            writer.writeAttribute("value", currentValue, "value");
        }
        
        RenderKitUtils.renderPassThruAttributes(context,
                writer,
                component,
                INPUT_ATTRIBUTES,
                getNonOnChangeBehaviors(component));
        RenderKitUtils.renderXHTMLStyleBooleanAttributes(writer, component);

        RenderKitUtils.renderOnchange(context, component, false);
        
        writer.endElement("input");        
    }
    
	private void generateSelect(String part, String id, String idJs,
			Map<Integer, String> options, ResponseWriter writer,
			DateComponent component, boolean selectNullable) throws IOException {
		
		String style;
    	String onChangeEvent;
    	if(part.equals("day")){
    		onChangeEvent = "dateComponent_"+idJs+".updateDay(this);";
    		style = component.dayStyle();
    	} else if(part.equals("month")){
    		onChangeEvent = "dateComponent_"+idJs+".updateMonth(this);"
    				+(component.formatVal()==DateFormat.DAYMONTH||component.formatVal()==DateFormat.DAYMONTHYEAR?"dateComponent_"+idJs+".showDays(this);":"");   		
    		style = component.monthStyle();
    	} else {
    		onChangeEvent = "dateComponent_"+idJs+".updateYear(this);";    		
    		style = component.yearStyle();
    	}

    	writer.startElement("div", component);
        writer.writeAttribute("class", "styled-select", null);
        writer.writeAttribute("style", style==null?"float:left;":style, null);
    	writer.startElement("select", component);
    	writer.writeAttribute("id", id+"_"+part,null);
        writer.writeAttribute("class", "select-box", null);    	
    	writer.writeAttribute("onchange", onChangeEvent, null);
    	
    	if(component.nullable()){
    		writer.startElement("option", component);
    		writer.writeAttribute("value", "", null);
    		if(selectNullable)
    			writer.writeAttribute("selected", "selected=\"selected\"", null);
    		writer.writeText("", null);
    		writer.endElement("option");    		
    	}
    	
    	for(Integer option:options.keySet()){
    		writer.startElement("option", component);
    		writer.writeAttribute("value", option, null);
    		if(optionSelected(part, option, component) && !selectNullable){
    			writer.writeAttribute("selected", "selected=\"selected\"", null);
    		}
    		writer.writeText(options.get(option), null);
    		writer.endElement("option");
    	}
    	writer.endElement("select");
    	writer.endElement("div");
    }   
    
    private Map<Integer, String> getYearsMap(int max,int min){
    	Map<Integer, String> yearsMap = new TreeMap<Integer, String>();
    	Calendar cal = new GregorianCalendar();
    	int minYear = cal.get(Calendar.YEAR)+min;
    	int range = max - min;
    	String year;
    	for(int i=0;i<=range;i++){
    		year = ""+(minYear+i);
    		yearsMap.put(minYear+i,year);
    	}
    	if(!yearsMap.containsKey(yearInt))
    		yearsMap.put(yearInt, ""+yearInt);
    	
    	return yearsMap;
    }

    private Map<Integer, String> getDaysMap(int month){
    	Map<Integer, String> daysMap = new TreeMap<Integer, String>();  	
    	for(int i=1;i<=MONTHDAYS[month];i++){
    		daysMap.put(i, ""+i);
    	}
    	return daysMap;
    }
    
    private boolean optionSelected(String part,int value, DateComponent component){
    	if(part.equals("day")){
    		return value==dayInt;
    	} else if(part.equals("month")){
    		return value==monthInt;
    	} else {
    		return value==yearInt;
    	}
    }
    
    @Override
    public boolean getRendersChildren()
    {
        return true;
    }

    @Override
    public void encodeBegin(FacesContext context, UIComponent component)
        throws IOException
    {
        if(context == null)
        {
            throw new NullPointerException("Context is null");
        }
        if(component == null)
        {
            throw new NullPointerException("Component is null");
        } else
        {
            return;
        }
    }

}

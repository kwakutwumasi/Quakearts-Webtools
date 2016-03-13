package com.quakearts.webapp.facelets.bootstrap.renderers;

import com.quakearts.webapp.facelets.bootstrap.components.BootDateButton;
import com.quakearts.webapp.facelets.bootstrap.components.BootDateButton.DateFormat;
import com.quakearts.webapp.facelets.bootstrap.renderkit.Attribute;
import com.quakearts.webapp.facelets.bootstrap.renderkit.AttributeManager;
import com.quakearts.webapp.facelets.bootstrap.renderkit.AttributeManager.Key;
import com.quakearts.webapp.facelets.bootstrap.renderkit.RenderKitUtils;
import com.quakearts.webapp.facelets.bootstrap.renderkit.html_basic.HtmlBasicInputRenderer;

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
import static com.quakearts.webapp.facelets.util.UtilityMethods.*;

public class BootDateButtonRenderer extends HtmlBasicInputRenderer
{

    public static final String RENDERER_TYPE = "com.quakearts.bootstrap.date.renderer";
    public static final int[] MONTHDAYS = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final Map<Integer,String> months = new TreeMap<Integer, String>();
    private static final Attribute[] ATTRIBUTES = AttributeManager.getAttributes(Key.DATEBUTTON);
    
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
     
    @Override
    protected void getEndTextToRender(FacesContext context, UIComponent component, String currentValue)
        throws IOException
    {
        int dayInt,monthInt,yearInt;
        BootDateButton button;
        if(component instanceof BootDateButton)
        {
            button = (BootDateButton)component;
        } else
        {
            throw new IOException("Component must be of type "+BootDateButton.class.getName());
        }
        
        boolean componentDisabled = componentIsDisabled(component);
        
        Calendar date;
        date = new GregorianCalendar();
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat(button.formatVal().getFormatString());
        if(currentValue!=null){
			try {
				date.setTime(formatter.parse(currentValue));
			} catch (ParseException e) {
			}
        } else {
        	if(!button.nullable())
        		currentValue = formatter.format(date.getTime());
        }
        
        if(button.formatVal()==DateFormat.DAYMONTH||button.formatVal()==DateFormat.DAYMONTHYEAR)
        	dayInt = date.get(Calendar.DAY_OF_MONTH);
        else
        	dayInt = 1;
        
        if(button.formatVal()!=DateFormat.YEAR)
        	monthInt = (date.get(Calendar.MONTH)+1);
        else
        	monthInt = 1;

        yearInt = date.get(Calendar.YEAR);
		
		String id=component.getClientId(context), idJs = id.replace("-", "_");
        
        ResponseWriter writer = context.getResponseWriter();
        
    	writer.startElement("div", component);
        writeIdAttributeIfNecessary(context, writer, component);
        RenderKitUtils.renderPassThruAttributes(context, writer, component, ATTRIBUTES);
        RenderKitUtils.renderXHTMLStyleBooleanAttributes(writer, component);
        RenderKitUtils.renderOnchange(context, component);
        
    	String styleClass= button.get("styleClass");
    	if(styleClass!=null)
    		writer.writeAttribute("class", styleClass, null);
    	String style= button.get("style");
    	if(style!=null)
    		writer.writeAttribute("style", style, null);
    	writeIdAttributeIfNecessary(context, writer, component);
        writer.write("\n");
        int offset = 0;
        
    	GregorianCalendar firstDay = new GregorianCalendar(date.get(Calendar.YEAR), date.get(Calendar.MONTH), 1);
    	offset = firstDay.get(Calendar.DAY_OF_WEEK)-1;
        
        if(button.formatVal()== DateFormat.DAYMONTH || button.formatVal()== DateFormat.DAYMONTHYEAR){
        	String dayClass = button.get("dayClass");
        	
			generateSelectDay(idJs, dayInt,
					MONTHDAYS[date.get(Calendar.MONTH)], offset, writer, button,
					currentValue==null,
					getDisplayType(button, context, "dayType"), dayClass, componentDisabled);
        }
       writer.write("\n");
       if(!(button.formatVal()== DateFormat.YEAR)){
        	String monthClass = button.get("monthClass");

        	generateSelect("month", idJs, monthInt, months, writer, button,
        			currentValue==null,
					getDisplayType(button, context, "monthType"),monthClass, componentDisabled);
       }
       writer.write("\n");
        if((button.formatVal()!= DateFormat.DAYMONTH) && (button.formatVal()!= DateFormat.MONTH)){
        	String yearClass = button.get("yearClass");

        	generateSelect("year", idJs, yearInt,
					getYearsMap(button.maxAsInt(), button.minAsInt(), yearInt),
					writer, button, currentValue==null,
					getDisplayType(button, context, "yearType"),yearClass, componentDisabled);
        }
        writer.write("\n");
        if(button.nullable()){
 			writer.startElement("a", component);
 	        writer.writeAttribute("class", "btn-group day", null);
 	        writer.writeAttribute("title", "Clear date", null);
 			writer.writeAttribute("onclick","dateComponent_"+idJs
 					+".clearComponent();", null);
 			writer.write("&times;");
 			writer.endElement("a");
 	        writer.write("\n");    	
 		}
        writer.write("\n");
        writer.startElement("input", button);
        writer.writeAttribute("name", id, null);
        writer.writeAttribute("id", idJs+"_input", null);
        writer.writeAttribute("type", "hidden", null);
        writer.writeAttribute("value", currentValue!=null?currentValue:"", "value");        
        writer.endElement("input");
        writer.write("\n");
        writer.endElement("div");
        writer.write("\n");    	
    }
    
    private void generateSelectDay(String idJs, int value,
			int days, int offset, ResponseWriter writer,
			BootDateButton component, boolean isnull,
			String type, String styleClass, boolean componentDisabled) throws IOException {
    	
    	writer.startElement("div", component);
        writer.writeAttribute("class", "btn-group", null);

        writer.write("\n");
    	writer.startElement("button", component);
    	writer.writeAttribute("id", idJs+"_btn_day",null);
        writer.writeAttribute("class", "btn btn-"+type+(styleClass!=null?" "+styleClass:"")+" dropdown-toggle", null);
        writer.writeAttribute("data-toggle", "dropdown", null);
        writer.writeAttribute("aria-expanded", "false", null);
        if(componentDisabled)
        	writer.writeAttribute("disabled", "disabled", "disabled");
        
        writer.write("\n");
        writer.startElement("span", component);
    	writer.writeAttribute("id", idJs+"_day",null);
        writer.write(isnull?"&nbsp;":value+"");
        writer.endElement("span");
        writer.write("\n");
        writer.startElement("span", component);
    	writer.writeAttribute("class","caret",null);
        writer.write("\n");
        writer.endElement("span");
        writer.write("\n");
        writer.endElement("button");
        writer.write("\n");       
        writer.startElement("div", component);
        writer.writeAttribute("class", "dropdown-menu day-container", null);
        writer.writeAttribute("role", "menu", null);
        
        if(!componentDisabled){
	    	String onChangeEvent = "dateComponent_"+idJs+".updateDay(this);";	    	
	    	writer.write("<span class=\"day-header\">S</span>\r\n" + 
	    			"<span class=\"day-header\">M</span>\r\n" + 
	    			"<span class=\"day-header\">T</span>\r\n" + 
	    			"<span class=\"day-header\">W</span>\r\n" + 
	    			"<span class=\"day-header\">T</span>\r\n" + 
	    			"<span class=\"day-header\">F</span>\r\n" + 
	    			"<span class=\"day-header\">S</span>");
	    	writer.write("<span class=\"day-header buffer\" style=\"width:"+(39*offset)+"px;\"></span>");
	    	for(int i=1;i<=31;i++){
	    		writer.startElement("a", component);
	            writer.writeAttribute("class", i<=days?"day":"collapse", null);
	    		writer.writeAttribute("onclick",onChangeEvent, null);
	    		writer.writeText(i, null);
	    		writer.endElement("a");
	            writer.write("\n");    	
	    	}
	    	
        }
    	writer.endElement("div");
        writer.write("\n");    	
    	writer.endElement("div");
	}

	private void generateSelect(String part, String idJs, int value,
			Map<Integer, String> options, ResponseWriter writer,
			BootDateButton component, boolean isnull, String type, String styleClass, boolean componentDisabled)
			throws IOException {
    	writer.startElement("div", component);
        writer.writeAttribute("class", "btn-group", null);

        writer.write("\n");
    	writer.startElement("button", component);
    	writer.writeAttribute("id", idJs+"_btn_"+part,null);
        writer.writeAttribute("class", "btn btn-"+type+(styleClass!=null?" "+styleClass:"")+" dropdown-toggle", null);
        writer.writeAttribute("data-toggle", "dropdown", null);
        writer.writeAttribute("aria-expanded", "false", null);
        if(componentDisabled)
        	writer.writeAttribute("disabled", "disabled", "disabled");

        writer.write("\n");
        writer.startElement("span", component);
    	writer.writeAttribute("id", idJs+"_"+part,null);
        writer.write(isnull?"&nbsp;":options.get(value)+"");
        writer.endElement("span");
        writer.write("\n");
        writer.startElement("span", component);
    	writer.writeAttribute("class","caret",null);
        writer.write("\n");
        writer.endElement("span");
        writer.write("\n");
        writer.endElement("button");
        writer.write("\n");       
        writer.startElement("div", component);
        writer.writeAttribute("class", "dropdown-menu date-container", null);
        writer.writeAttribute("role", "menu", null);
         	
        if(!componentDisabled){
	    	String onChangeEvent;
	    	if(part.equals("month")){
	    		onChangeEvent = "dateComponent_"+idJs+".updateMonth(this,val);"
	    				+(component.formatVal()==DateFormat.DAYMONTH||component.formatVal()==DateFormat.DAYMONTHYEAR?"dateComponent_"+idJs+".showDays(val);":"");   		
	    	} else {
	    		onChangeEvent = "dateComponent_"+idJs+".updateYear(this);";    		
	    	}
	    	
	    	for(Integer option:options.keySet()){
	    		writer.startElement("a", component);
	    		writer.writeAttribute("class","other-date",null);
	    		writer.writeAttribute("onclick",part.equals("month")?onChangeEvent.replace("val",option.toString()):onChangeEvent, null);
	    		writer.writeText(options.get(option), null);
	    		writer.endElement("a");
	            writer.write("\n");    	
	    	}
        }
    	writer.endElement("div");
        writer.write("\n");    	
    	writer.endElement("div");
    }   
    
    private Map<Integer, String> getYearsMap(int max,int min, int yearInt){
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
    
    private static String getDisplayType(BootDateButton button, FacesContext context, String partType){
    	String displayType =button.get(partType);

    	if(displayType==null || (!displayType.equals("default"))
    		&& (!displayType.equals("primary")) && (!displayType.equals("success"))
    		&& (!displayType.equals("info")) && (!displayType.equals("warning"))
    		&& (!displayType.equals("danger")))
    		displayType = "default";
    	
    	return displayType;
    }
    
    @Override
    public boolean getRendersChildren()
    {
        return true;
    }
}

package com.quakearts.webapp.facelets.bootstrap.renderers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import com.quakearts.webapp.facelets.bootstrap.components.BootDateButton;
import com.quakearts.webapp.facelets.bootstrap.components.BootDateButton.DateFormat;
import com.quakearts.webapp.facelets.bootstrap.components.BootDateScriptComponent;
import com.quakearts.webapp.facelets.bootstrap.renderkit.html_basic.HtmlBasicRenderer;
import static com.quakearts.webapp.facelets.util.UtilityMethods.*;

public class BootDateScriptRenderer extends HtmlBasicRenderer {

    public static final String RENDERER_TYPE = "com.quakearts.facelets.input.datescript.renderer";
	public static final String DATE_SCRIPT_TOP = "var MonthDays = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);";
    public static final String DATE_SCRIPT_FUNCTION = "var dateComponent_idVal = qab.dc('#dayVal',#monthVal,'#yearVal',"
    		+ "'#idVal','#type');";
    	
	@Override
	protected void getEndTextToRender(FacesContext context,
			UIComponent component, String currentValue) throws IOException {
		
		if(!(component instanceof BootDateScriptComponent))
			throw new IOException("Component must be of type "+BootDateScriptComponent.class.getName());

		BootDateScriptComponent dateScriptComponent = (BootDateScriptComponent) component;
		if(!componentIsDisabled(dateScriptComponent.getDateComponent())){
			ResponseWriter writer = context.getResponseWriter();
			context.getAttributes().get("com.quakearts.bootstrap.DATE_COMMON");
			if(context.getAttributes().get("com.quakearts.bootstrap.DATE_COMMON")==null){
		        writer.startElement("script", dateScriptComponent);
				writer.writeAttribute("type", "text/javascript", null);
		        writer.write("\n");    	
				writer.writeText(DATE_SCRIPT_TOP, null);
		        writer.write("\n");    	
				writer.endElement("script");
		        writer.write("\n");    	
				context.getAttributes().put("com.quakearts.bootstrap.DATE_COMMON","");
			}
			
			writer.startElement("script", dateScriptComponent);
			writer.writeAttribute("type", "text/javascript", null);
	        writer.write("\n");    	
			writer.writeText(getContents(dateScriptComponent.getDateComponent(),context), null);	
	        writer.write("\n");    	
			writer.endElement("script");
	        writer.write("\n");   
		}
	}

	private String getContents(BootDateButton dateComponent, FacesContext context) {
		String idJs=dateComponent.getClientId(context).replace("-", "_");
	    int dayInt,monthInt,yearInt;
	    Calendar date;
        date = new GregorianCalendar();
		Object object = dateComponent.getValue();
		boolean isNull = false;
		
        if(object instanceof String){
    		SimpleDateFormat formatter;
    		formatter = new SimpleDateFormat(dateComponent.formatVal().getFormatString());
			try {
				date.setTime(formatter.parse((String) object));
			} catch (ParseException e) {
			}
        } else if(object instanceof Date){
        	date.setTime((Date)object);
        } else if(object==null){
        	isNull = dateComponent.nullable();
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
		
		
		return DATE_SCRIPT_FUNCTION
				.replace("idVal", idJs)
				.replace("#dayVal",isNull?"":(dayInt<10?"0"+dayInt:""+dayInt))
				.replace("#monthVal",isNull?"0":(monthInt+""))
				.replace("#yearVal", isNull?"":(""+yearInt))
				.replace("#type", dateComponent.formatVal()
						.getFormatValue());
	}
	
	@Override
	protected Object getValue(UIComponent component) {
		return "";
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
    
    @Override
    public boolean getRendersChildren()
    {
        return false;
    }

}

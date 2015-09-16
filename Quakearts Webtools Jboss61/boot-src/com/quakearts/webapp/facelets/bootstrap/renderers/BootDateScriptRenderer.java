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
    public static final String DATE_SCRIPT_FUNCTION = "var dateComponent_idVal = {"
											+"\n	\"required\":true,";
    public static final String DATE_SCRIPT_DAY = "\n	\"day\":\"dayVal\",";
    public static final String DATE_SCRIPT_MONTH = "\n	\"month\":\"monthVal\",";
    public static final String DATE_SCRIPT_YEAR = "\n	\"year\":\"yearVal\",";
    public static final String DATE_SCRIPT_BODY = "\n	\"updateDay\": function(obj){"
											+"\n		this.day=obj.innerHTML;"
											+"\n		this.updateComponent();"
											+"\n		$('#dayId').html($(obj).html());"
											+"\n	},"
											+"\n	\"updateMonth\": function(obj,val){"
											+"\n		this.month=val;"
											+"\n		var totalDays = MonthDays[val-1];"
											+"\n		if(this.day>totalDays){"
											+"\n			this.day=totalDays;"
											+"\n			$('#dayId').html(totalDays+'');"
											+"\n		}"
											+"\n		this.updateComponent();"
											+"\n		$('#monId').html($(obj).html());"
											+"\n	},"
											+"\n	\"updateYear\": function(obj){"
											+"\n		this.year=obj.innerHTML;"
											+"\n		this.updateComponent();"
											+"\n		$('#yrId').html($(obj).html());"
											+"\n	},"
											+"\n	\"showDays\": function(month){"
											+"\n		var totalDays = MonthDays[month-1];"
											+"\n		var count=#off;"
											+"\n		$('#dayBtnId').parent().find('div.dropdown-menu').children().each(function()"
											+"\n		{"
											+"\n			count++;"
											+"\n			if(totalDays<count){"
											+"\n				$(this).addClass('collapse');"
											+"\n			} else {"
											+"\n				if($(this).hasClass('collapse'))"
											+"\n					$(this).removeClass('collapse');"
											+"\n			}"
											+"\n		});"
											+"\n	},"
											+"\n	\"clearComponent\": function(){" 
											+"\n		$('#idVal').val('');"
											+"\n		$('#dayId').html('&nbsp;');"
											+"\n		$('#monId').html('&nbsp;');"
											+"\n		$('#yrId').html('&nbsp;');"
											+"\n	},"
											+"\n	\"updateComponent\": function(){";
    public static final String DATE_SCRIPT_FORMAT = "\n		$(\"#idVal\").val([d][m][y]);"
											+"\n		$(\"#idVal\").change();"
											+"\n	}"
											+"\n};";
    public static final String DAY = "(this.day.length==1?\"0\"+this.day:this.day)";
    public static final String MONTH = "(this.month.length==1?\"0\"+this.month:this.month)";
    public static final String YEAR = "this.year";
    	
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
		String ids= dateComponent.getClientId(context), idJs = ids.replace(":", "_");
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
		
		String tail = DATE_SCRIPT_FORMAT.replace("idVal", idJs+"_input");
		if(dateComponent.formatVal() == DateFormat.DAYMONTH || dateComponent.formatVal() == DateFormat.DAYMONTHYEAR){
			tail = tail.replace("[d]", DAY);
		}else{
			tail = tail.replace("[d]", "");
		}
		if(dateComponent.formatVal() != DateFormat.YEAR){
			tail = tail.replace("[m]", (dateComponent.formatVal()==DateFormat.DAYMONTH||dateComponent.formatVal() == DateFormat.DAYMONTHYEAR?"+\"/\"+":"")+MONTH);
		}else{
			tail = tail.replace("[m]", "");
		}
		if(dateComponent.formatVal() != DateFormat.DAYMONTH && dateComponent.formatVal() != DateFormat.MONTH){
			tail = tail.replace("[y]", (dateComponent.formatVal()!=DateFormat.YEAR?"+\"/\"+":"")+YEAR);
		} else {
			tail = tail.replace("[y]", "");
		}
		
		String script = DATE_SCRIPT_FUNCTION.replace("idVal", idJs)+
				DATE_SCRIPT_DAY.replace("dayVal",isNull?"":(dayInt<10?"0"+dayInt:""+dayInt))+
				DATE_SCRIPT_MONTH.replace("monthVal",isNull?"":(monthInt<10?"0"+monthInt:""+monthInt))+
				DATE_SCRIPT_YEAR.replace("yearVal", isNull?"":(""+yearInt))+
				DATE_SCRIPT_BODY.replace("idVal", idJs+"_input")
					.replace("dayId", idJs+"_day")
					.replace("dayBtnId", idJs+"_btn_day")
					.replace("monId", idJs+"_month")
					.replace("yrId", idJs+"_year")
					.replace("#off", dateComponent.nullable()?"-1":"0")
					+tail;
		return script;
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

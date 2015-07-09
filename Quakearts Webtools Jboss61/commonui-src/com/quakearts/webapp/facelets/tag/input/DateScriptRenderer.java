package com.quakearts.webapp.facelets.tag.input;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import com.quakearts.webapp.facelets.tag.input.DateComponent.DateFormat;
import com.sun.faces.renderkit.html_basic.HtmlBasicRenderer;

public class DateScriptRenderer extends HtmlBasicRenderer {

    public static final String RENDERER_TYPE = "com.quakearts.facelets.input.datescript.renderer";
	public static final String DATE_SCRIPT_TOP = "var MonthDays = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);";
    public static final String DATE_SCRIPT_FUNCTION = "var dateComponent_idVal = {"
											+"\n	\"required\":true,";
    public static final String DATE_SCRIPT_DAY = "\n	\"day\":\"dayVal\",";
    public static final String DATE_SCRIPT_MONTH = "\n	\"month\":\"monthVal\",";
    public static final String DATE_SCRIPT_YEAR = "\n	\"year\":\"yearVal\",";
    public static final String DATE_SCRIPT_BODY = "\n	\"updateDay\": function(obj){"
    										+"\n		if(obj.value.length>0){"
											+"\n			this.day=obj.value;"
											+"\n			this.updateComponent();"
											+"\n		} else {"
											+"\n			this.clearComponent();"
											+"\n		}"
											+"\n	},"
											+"\n	\"updateMonth\": function(obj){"
											+"\n		if(obj.value.length>0){"
											+"\n			this.month=obj.value;"
											+"\n			this.updateComponent();"
											+"\n		} else {"
											+"\n			this.clearComponent();"
											+"\n		}"
											+"\n	},"
											+"\n	\"updateYear\": function(obj){"
											+"\n		if(obj.value.length>0){"
											+"\n			this.year=obj.value;"
											+"\n			this.updateComponent();"
											+"\n		} else {"
											+"\n			this.clearComponent();"
											+"\n		}"
											+"\n	},"
											+"\n	\"showDays\": function(obj){"
											+"\n		var daysSelect = document.getElementById(\"idVal_day\");"
											+"\n		var TotalDays = MonthDays[parseInt(obj.value)-1];"
											+"\n		if(daysSelect.options.length > TotalDays + offset){"
											+"\n			var iter= daysSelect.options.length - (TotalDays + offset);"
											+"\n			for(i=0;i<iter;i++){"
											+"\n				daysSelect.remove(daysSelect.options.length-1);"
											+"\n			}"
											+"\n		}else if(daysSelect.options.length < TotalDays + offset){"
											+"\n			var iter= TotalDays + offset - daysSelect.options.length;"
											+"\n			for(i=iter;i>0;i--){"
											+"\n				var NewOption = document.createElement(\"option\");"
											+"\n				NewOption.text = \"\"+(TotalDays - i + 1);"
											+"\n				try{"
											+"\n					daysSelect.add(NewOption,null);"
											+"\n				}catch(ex){"
											+"\n					daysSelect.add(NewOption);"
											+"\n				}"
											+"\n			}"
											+"\n		}"
											+"\n	},"
											+"\n  \"clearComponent\": function(){" 
											+"\n document.getElementById(\"idVal\").value=\"\";"
											+"\n},"
											+"\n	\"updateComponent\": function(){";
    public static final String DATE_SCRIPT_FORMAT = "\ndocument.getElementById(\"idVal\").value=[d][m][y];"
											+"\n	}"
											+"\n};";
    public static final String DAY = "(this.day.length==1?\"0\"+this.day:this.day)";
    public static final String MONTH = "(this.month.length==1?\"0\"+this.month:this.month)";
    public static final String YEAR = "this.year";
    	
	@Override
	protected void getEndTextToRender(FacesContext context,
			UIComponent component, String currentValue) throws IOException {
		
		if(!(component instanceof DateScriptComponent))
			throw new IOException("Component must be of type "+DateScriptComponent.class.getName());

		DateScriptComponent dateScriptComponent = (DateScriptComponent) component;
		ResponseWriter writer = context.getResponseWriter();

		context.getAttributes().get("com.quakearts.DATE_COMMON");
		if(context.getAttributes().get("com.quakearts.DATE_COMMON")==null){
			writer.startElement("script", dateScriptComponent);
			writer.writeAttribute("type", "text/javascript", null);
			writer.writeText(DATE_SCRIPT_TOP, null);
			writer.endElement("script");
			context.getAttributes().put("com.quakearts.DATE_COMMON","");
		}
		
		writer.startElement("script", dateScriptComponent);
		writer.writeAttribute("type", "text/javascript", null);
		writer.writeText(getContents(dateScriptComponent.getDateComponent(),context), null);
		
		writer.endElement("script");
	}

	private String getContents(DateComponent dateComponent, FacesContext context) {
		String id= dateComponent.getClientId(context), idJs = id.replace(":", "_");
	    int dayInt,monthInt,yearInt;
	    Calendar date;
        date = new GregorianCalendar();
		Object object = dateComponent.getSubmittedValue();
		if(object==null)
			object=dateComponent.getValue();
		
        if(object instanceof String){
    		SimpleDateFormat formatter;
    		formatter = new SimpleDateFormat(dateComponent.formatVal().getFormatString());
			try {
				date.setTime(formatter.parse((String) object));
			} catch (ParseException e) {
			}
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

		
		String tail = DATE_SCRIPT_FORMAT.replace("idVal", id);
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
		}else{
			tail = tail.replace("[y]", "");
		}
		
		String script = DATE_SCRIPT_FUNCTION.replaceAll("idVal", idJs)+
				DATE_SCRIPT_DAY.replace("dayVal", dayInt<10?"0"+dayInt:""+dayInt)+
				DATE_SCRIPT_MONTH.replace("monthVal", monthInt<10?"0"+monthInt:""+monthInt)+
				DATE_SCRIPT_YEAR.replace("yearVal", ""+yearInt)+
				DATE_SCRIPT_BODY.replaceAll("idVal", id).replaceAll("offset",dateComponent.nullable()?"1":"0")+tail;
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

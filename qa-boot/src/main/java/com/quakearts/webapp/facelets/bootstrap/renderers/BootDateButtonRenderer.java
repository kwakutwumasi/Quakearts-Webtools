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
package com.quakearts.webapp.facelets.bootstrap.renderers;

import com.quakearts.webapp.facelets.bootstrap.components.BootDateButton;
import com.quakearts.webapp.facelets.bootstrap.renderkit.Attribute;
import com.quakearts.webapp.facelets.bootstrap.renderkit.AttributeManager;
import com.quakearts.webapp.facelets.bootstrap.renderkit.AttributeManager.Key;
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

import static com.quakearts.webapp.facelets.bootstrap.renderkit.RenderKitUtils.*;
import static com.quakearts.webapp.facelets.util.UtilityMethods.*;

public class BootDateButtonRenderer extends HtmlBasicInputRenderer {

	public static final String RENDERER_TYPE = "com.quakearts.bootstrap.date.renderer";
	public static final int[] MONTHDAYS = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private static final Map<Integer, String> months = new TreeMap<Integer, String>();
	private static final Attribute[] ATTRIBUTES = AttributeManager.getAttributes(Key.DATEBUTTON);

	static {
		months.put(1, "Jan");
		months.put(2, "Feb");
		months.put(3, "Mar");
		months.put(4, "Apr");
		months.put(5, "May");
		months.put(6, "Jun");
		months.put(7, "Jul");
		months.put(8, "Aug");
		months.put(9, "Sept");
		months.put(10, "Oct");
		months.put(11, "Nov");
		months.put(12, "Dec");
	}
	
	@Override
	protected void getEndTextToRender(FacesContext context, UIComponent component, String currentValue)
			throws IOException {
		BootDateButton button;
		if (component instanceof BootDateButton) {
			button = (BootDateButton) component;
		} else {
			throw new IOException("Component must be of type " + BootDateButton.class.getName());
		}
		
		Calendar date;
		date = new GregorianCalendar();
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat(button.formatVal().getDateFormatString());
		if (currentValue != null) {
			try {
				date.setTime(formatter.parse(currentValue));
			} catch (ParseException e) {
			}
		} else {
			if (!button.nullable())
				currentValue = formatter.format(date.getTime());
		}

		int dayInt;
		if (button.formatVal().hasDay())
			dayInt = date.get(Calendar.DAY_OF_MONTH);
		else
			dayInt = 1;

		int monthInt;
		if (button.formatVal().hasMonth())
			monthInt = (date.get(Calendar.MONTH) + 1);
		else
			monthInt = 1;

		int yearInt;
		yearInt = date.get(Calendar.YEAR);

		int hourInt;
		if (button.formatVal().hasHour()) {
			hourInt = date.get(button.timeIs24Hours() ? Calendar.HOUR_OF_DAY : Calendar.HOUR);
			if (hourInt == 0 && !button.timeIs24Hours())
				hourInt = 12;
		} else {
			hourInt = button.timeIs24Hours() ? 0 : 12;
		}

		int minuteInt; 
		if (button.formatVal().hasMinute())
			minuteInt = date.get(Calendar.MINUTE);
		else
			minuteInt = 0;

		int secondInt;
		if (button.formatVal().hasSeconds())
			secondInt = date.get(Calendar.SECOND);
		else
			secondInt = 0;

		boolean isAM = button.timeIs24Hours() ? false : date.get(Calendar.AM_PM) == Calendar.AM;

		ResponseWriter writer = context.getResponseWriter();
		String id = component.getClientId(context);
		writer.startElement("div", component);
        writer.writeAttribute("id", id, "id");
		writer.writeAttribute("data-day", currentValue == null ? "" : (dayInt < 10 ? "0" + dayInt : "" + dayInt), null);
		writer.writeAttribute("data-month", currentValue == null ? "0" : (monthInt + ""), null);
		writer.writeAttribute("data-year", currentValue == null ? "" : ("" + yearInt), null);
		writer.writeAttribute("data-hour", currentValue == null ? "0" : hourInt + "", null);
		writer.writeAttribute("data-min", currentValue == null ? "0" : minuteInt + "", null);
		writer.writeAttribute("data-sec", currentValue == null ? "0" : secondInt + "", null);
		writer.writeAttribute("data-hrstep", button.hourStepVal() + "", null);
		writer.writeAttribute("data-mnstep", button.minuteStepVal() + "", null);
		writer.writeAttribute("data-scstep", button.secondStepVal() + "", null);
		writer.writeAttribute("data-is24hr", button.timeIs24Hours() + "", null);
		writer.writeAttribute("data-isam", isAM + "",null);
		writer.writeAttribute("data-datetype", button.formatVal().getFormat(), null);;

		renderPassThruAttributes(context, writer, component, ATTRIBUTES);
		renderXHTMLStyleBooleanAttributes(writer, component);
		
		renderHTML5DataAttributes(context, component);
		
		String styleClass = button.get("styleClass");
		if (styleClass != null)
			writer.writeAttribute("class", styleClass, null);
		String style = button.get("style");
		if (style != null)
			writer.writeAttribute("style", style, null);
		writer.write("\n");
		int offset = 0;

		if(button.formatVal().hasMonth()) {
			GregorianCalendar firstDay = new GregorianCalendar(date.get(Calendar.YEAR), date.get(Calendar.MONTH), 1);
			offset = firstDay.get(Calendar.DAY_OF_WEEK) - 1;
		}
		
		String idJs = id.replace("-", "_");
		boolean componentDisabled = componentIsDisabled(component);
		if (button.formatVal().hasDay()) {
			String dayClass = button.get("dayClass");
			int days;
			if(button.formatVal().hasMonth())
				days = MONTHDAYS[date.get(Calendar.MONTH)];
			else
				days = 31;
				
			generateSelectDay(idJs, dayInt, days, offset, writer, button,
					currentValue == null, getDisplayType(button, context, "dayType"), dayClass, componentDisabled,
					button.formatVal().hasMonth());
		}
		
		if (button.formatVal().hasMonth()) {
			writer.write("\n");
			String monthClass = button.get("monthClass");

			generateSelect("month", idJs, monthInt, months, writer, button, currentValue == null,
					getDisplayType(button, context, "monthType"), monthClass, componentDisabled);
		}

		if (button.formatVal().hasYear()) {
			writer.write("\n");
			String yearClass = button.get("yearClass");

			generateSelect("year", idJs, yearInt, getYearsMap(button.maxAsInt(), button.minAsInt(), yearInt), writer,
					button, currentValue == null, getDisplayType(button, context, "yearType"), yearClass,
					componentDisabled);
		}

		if (button.formatVal().hasDay() && button.formatVal().hasHour()) {
			writer.write("\n");
			writer.startElement("span", component);
			writer.writeAttribute("class", "time-text", null);
			writer.write("@");
			writer.endElement("span");
		}

		if (button.formatVal().hasHour()
				|| button.formatVal().hasMinute()
				|| button.formatVal().hasSeconds()) {
			writer.write("\n");
			writer.startElement("div", component);
			String timeClass = button.get("timeClass");
			if(timeClass == null)
				timeClass = "time-md";
			
			writer.writeAttribute("class", "time-control-group "+timeClass, null);
			if(button.formatVal().hasHour()) {
				generateTimeControl(writer, component, idJs, "hour", "vhr", "hrup", "hrdown", currentValue == null, hourInt,
					getDisplayType(button, context, "hourType"), componentDisabled);
			}
			
			if (button.formatVal().hasMinute()) {
				generateTimeControl(writer, component, idJs, "min", "vmn", "mnup", "mndown", currentValue == null,
						minuteInt, getDisplayType(button, context, "minuteType"), componentDisabled);
			}
			
			if (button.formatVal().hasSeconds()) {
				generateTimeControl(writer, component, idJs, "sec", "vsc", "scup", "scdown", currentValue == null,
						secondInt, getDisplayType(button, context, "secondType"), componentDisabled);
			}
			writer.endElement("div");
			
			if (!button.timeIs24Hours()) {
				String ampmClass = button.get("ampmClass");
				writer.write("\n");
				writer.startElement("div", component);
				writer.writeAttribute("class", "btn-group time-ampm-group", null);
				writer.write("\n");
				generateAMPMButton(writer, component, idJs, isAM, true, ampmClass);
				generateAMPMButton(writer, component, idJs, isAM, false, ampmClass);
				writer.endElement("div");
			}
		}

		writer.write("\n");
		if (button.nullable() && !componentDisabled) {
			writer.startElement("a", component);
			writer.writeAttribute("class", "btn-group day", null);
			writer.writeAttribute("title", "Clear date", null);
			writer.writeAttribute("onclick", "dc_" + idJs + ".cc();", null);
			writer.write("&times;");
			writer.endElement("a");
			writer.write("\n");
		}
		writer.write("\n");
		writer.startElement("input", button);
		writer.writeAttribute("name", id, null);
		writer.writeAttribute("id", idJs + "_input", null);
		writer.writeAttribute("type", "hidden", null);
		writer.writeAttribute("value", currentValue != null ? currentValue : "", "value");
		writer.endElement("input");
		if(!componentDisabled) {
			writer.write("\n");
			writer.startElement("input", button);
			writer.writeAttribute("id", idJs + "_change", null);
			writer.writeAttribute("type", "hidden", null);
			renderOnchange(context, component, component.getClientId());
			writer.endElement("input");
			writer.write("\n");
		}
		writer.endElement("div");
		writer.write("\n");
	}

	private void generateTimeControl(ResponseWriter writer, UIComponent component, String idJs, String id,
			String validateFunction, String upFunction, String downFunction, boolean isNull, int value, String type,
			boolean componentDisabled) throws IOException {
		writer.write("\n");
		writer.startElement("input", component);
		writer.writeAttribute("class", "time-form-control", null);
		writer.writeAttribute("onblur", "dc_" + idJs + "." + validateFunction + "(this)", null);
		writer.writeAttribute("id", idJs + "_" + id, null);
		writer.writeAttribute("value", isNull ? "" : value + "", null);
		if (componentDisabled)
			writer.writeAttribute("disabled", "disabled", "disabled");

		writer.endElement("input");
		writer.write("\n");
		writer.startElement("div", component);
		writer.writeAttribute("class", "time-btn-group", null);
		generateTimeButtons(writer, component, idJs, upFunction, "up", type, componentDisabled);
		generateTimeButtons(writer, component, idJs, downFunction, "down", type, componentDisabled);
		writer.write("\n");
		writer.endElement("div");
	}

	private void generateTimeButtons(ResponseWriter writer, UIComponent component, String idJs, String function,
			String chevron, String type, boolean componentDisabled) throws IOException {
		writer.write("\n");
		writer.startElement("button", component);
		writer.writeAttribute("class", "btn btn-" + type + " time-btn-" + chevron, null);
		writer.writeAttribute("type", "button", null);
		writer.writeAttribute("onclick", "dc_" + idJs + "." + function + "(this)", null);
		if (componentDisabled)
			writer.writeAttribute("disabled", "disabled", "disabled");

		writer.write("\n");
		writer.startElement("span", component);
		writer.writeAttribute("class", "glyphicon glyphicon-chevron-" + chevron, null);
		writer.endElement("span");
		writer.write("\n");
		writer.endElement("button");
	}

	private void generateAMPMButton(ResponseWriter writer, UIComponent component, String idJs, boolean isAM,
			boolean generateAM, String ampmClass) throws IOException {
		writer.startElement("button", component);
		writer.writeAttribute("class",
				"btn btn-default"+(ampmClass != null?" "+ampmClass:"") 
				+ ((isAM && generateAM) || (!isAM && !generateAM) ? " active" : ""), null);
		writer.writeAttribute("onclick", "dc_" + idJs + ".tglampm(this," + generateAM + ")", null);
		writer.writeAttribute("type", "button", null);
		writer.write(generateAM ? "AM" : "PM");
		writer.endElement("button");
		writer.write("\n");
	}

	private void generateSelectDay(String idJs, int value, int days, int offset, ResponseWriter writer,
			BootDateButton component, boolean isnull, String type, 
			String styleClass, boolean componentDisabled, boolean hasMonth)
			throws IOException {

		writer.startElement("div", component);
		writer.writeAttribute("class", "btn-group", null);

		writer.write("\n");
		writer.startElement("button", component);
		writer.writeAttribute("id", idJs + "_btn_day", null);
		writer.writeAttribute("class",
				"btn btn-" + type + (styleClass != null ? " " + styleClass : "") + " dropdown-toggle", null);
		writer.writeAttribute("data-toggle", "dropdown", null);
		writer.writeAttribute("aria-expanded", "false", null);
		if (componentDisabled)
			writer.writeAttribute("disabled", "disabled", "disabled");

		writer.write("\n");
		writer.startElement("span", component);
		writer.writeAttribute("id", idJs + "_day", null);
		writer.write(isnull ? "&nbsp;" : value + "");
		writer.endElement("span");
		writer.write("\n");
		writer.startElement("span", component);
		writer.writeAttribute("class", "caret", null);
		writer.write("\n");
		writer.endElement("span");
		writer.write("\n");
		writer.endElement("button");
		writer.write("\n");
		writer.startElement("div", component);
		writer.writeAttribute("class", "dropdown-menu day-container", null);
		writer.writeAttribute("role", "menu", null);

		if (!componentDisabled) {
			String onChangeEvent = "dc_" + idJs + ".ud(this);";
			if(hasMonth) {
				writer.write("<span class=\"day-header\">S</span>\r\n" + "<span class=\"day-header\">M</span>\r\n"
						+ "<span class=\"day-header\">T</span>\r\n" + "<span class=\"day-header\">W</span>\r\n"
						+ "<span class=\"day-header\">T</span>\r\n" + "<span class=\"day-header\">F</span>\r\n"
						+ "<span class=\"day-header\">S</span>");
				writer.write("<span class=\"day-header buffer\" style=\"width:" + (39 * offset) + "px;\"></span>");
			}
			
			for (int i = 1; i <= 31; i++) {
				writer.startElement("a", component);
				writer.writeAttribute("class", i <= days ? "day" : "collapse", null);
				writer.writeAttribute("onclick", onChangeEvent, null);
				writer.writeText(i, null);
				writer.endElement("a");
				writer.write("\n");
			}

		}
		writer.endElement("div");
		writer.write("\n");
		writer.endElement("div");
	}

	private void generateSelect(String part, String idJs, int value, Map<Integer, String> options,
			ResponseWriter writer, BootDateButton component, boolean isnull, String type, String styleClass,
			boolean componentDisabled) throws IOException {
		writer.startElement("div", component);
		writer.writeAttribute("class", "btn-group", null);

		writer.write("\n");
		writer.startElement("button", component);
		writer.writeAttribute("id", idJs + "_btn_" + part, null);
		writer.writeAttribute("class",
				"btn btn-" + type + (styleClass != null ? " " + styleClass : "") + " dropdown-toggle", null);
		writer.writeAttribute("data-toggle", "dropdown", null);
		writer.writeAttribute("aria-expanded", "false", null);
		if (componentDisabled)
			writer.writeAttribute("disabled", "disabled", "disabled");

		writer.write("\n");
		writer.startElement("span", component);
		writer.writeAttribute("id", idJs + "_" + part, null);
		writer.write(isnull ? "&nbsp;" : options.get(value) + "");
		writer.endElement("span");
		writer.write("\n");
		writer.startElement("span", component);
		writer.writeAttribute("class", "caret", null);
		writer.write("\n");
		writer.endElement("span");
		writer.write("\n");
		writer.endElement("button");
		writer.write("\n");
		writer.startElement("div", component);
		writer.writeAttribute("class", "dropdown-menu date-container", null);
		writer.writeAttribute("role", "menu", null);

		if (!componentDisabled) {
			String onChangeEvent;
			if (part.equals("month")) {
				onChangeEvent = "dc_" + idJs + ".um(this,val);"
						+ (component.formatVal().hasDay() ? "dc_" + idJs + ".sd(val);" : "");
			} else {
				onChangeEvent = "dc_" + idJs + ".uy(this);";
			}

			for (Integer option : options.keySet()) {
				if (option == 9999 && part.equals("year")) {
					writer.startElement("input", component);
					writer.writeAttribute("class", "form-control year-inp", null);
					writer.writeAttribute("maxlength", "4", null);
					writer.writeAttribute("onkeyup", "dc_" + idJs + ".yi(this);", null);
					writer.endElement("input");
					writer.write("\n");
				} else {
					writer.startElement("a", component);
					writer.writeAttribute("class", "other-date", null);
					writer.writeAttribute("onclick",
							part.equals("month") ? onChangeEvent.replace("val", option.toString()) : onChangeEvent,
							null);
					writer.writeText(options.get(option), null);
					writer.endElement("a");
					writer.write("\n");
				}
			}
		}
		writer.endElement("div");
		writer.write("\n");
		writer.endElement("div");
	}

	private Map<Integer, String> getYearsMap(int max, int min, int yearInt) {
		Map<Integer, String> yearsMap = new TreeMap<Integer, String>();
		Calendar cal = new GregorianCalendar();
		int minYear = cal.get(Calendar.YEAR) + min;
		int range = max - min;
		boolean addVarInput = range > 9;
		if (addVarInput) {
			range = 9;
		}

		if (yearInt < minYear || yearInt > minYear + range) {
			yearsMap.put(yearInt, "" + yearInt);
			if (range == 9)
				range = 8;
		}

		String year;
		for (int i = 0; i <= range; i++) {
			year = "" + (minYear + i);
			yearsMap.put(minYear + i, year);
		}
		if (addVarInput)
			yearsMap.put(9999, "");

		return yearsMap;
	}

	private static String getDisplayType(BootDateButton button, FacesContext context, String partType) {
		String displayType = button.get(partType);

		if (displayType == null || (!displayType.equals("default")) && (!displayType.equals("primary"))
				&& (!displayType.equals("success")) && (!displayType.equals("info")) && (!displayType.equals("warning"))
				&& (!displayType.equals("danger")))
			displayType = "default";

		return displayType;
	}

	@Override
	public boolean getRendersChildren() {
		return true;
	}
}

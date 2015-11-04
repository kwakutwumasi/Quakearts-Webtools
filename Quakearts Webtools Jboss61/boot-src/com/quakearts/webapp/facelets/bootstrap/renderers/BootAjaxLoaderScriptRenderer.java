package com.quakearts.webapp.facelets.bootstrap.renderers;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import com.quakearts.webapp.facelets.bootstrap.components.BootAjaxLoaderComponent;
import com.quakearts.webapp.facelets.bootstrap.components.BootAjaxLoaderScriptComponent;
import com.sun.faces.renderkit.html_basic.HtmlBasicRenderer;


public class BootAjaxLoaderScriptRenderer extends HtmlBasicRenderer {
	private static final String LOADERSCRIPT = "qaboot.mainLoaderHandler = function(data){\r\n" + 
			"    switch(data.status){\r\n" + 
			"    case \"begin\":\r\n" + 
			"        var obj = $(qaboot.escape(\"#$ajaxdiv\"));\r\n" +
			"		 obj.removeClass(\"collapse\").addClass(\"overlay\");\r\n" + 
			"        obj.animate({\r\n" + 
			"            opacity: 0.8\r\n" + 
			"            }, $stime, function() {\r\n" + 
			"               \r\n" + 
			"            });\r\n" + 
			"        break;\r\n" + 
			"    case \"complete\":\r\n" + 
			"		$(qaboot.escape(\"#$ajaxdiv\")).animate({\r\n" + 
			"			opacity: 0.0\r\n" + 
			"			}, $etime, function() {\r\n" + 
			"			 	$(qaboot.escape(\"#$ajaxdiv\")).removeClass(\"overlay\").addClass(\"collapse\");\r\n" + 
			"			});\r\n" + 
			"        break;\r\n" + 
			"    case \"success\":\r\n" + 
			"        break;\r\n" + 
			"    default:\r\n" + 
			"    }\r\n" + 
			"}\r\n" + 
			"\r\n" + 
			"qaboot.overlayHandler = function(data){\r\n" + 
			"    switch (data.status) {\r\n" + 
			"		case \"begin\":\r\n" + 
			"			var idobj = $(qaboot.escape(\"#\" + data.source.id));\r\n" + 
			"			var idparent = idobj.parent();\r\n" + 
			"			while (idparent.parent().length > 0) {\r\n" + 
			"				if (idparent.hasClass(\"ajax-container\")) {\r\n" + 
			"					$('body').append(\"<div id='\"+data.source.id+\"_overlay' class='collapse'>\"+\r\n" + 
			"							\"<img src='$overlayimg' border='0' $overlayimagestyle/>\"+\r\n" + 
			"								\"</div>\"); \r\n" + 
			"					var obj= $(qaboot.escape(\"#\"+data.source.id+\"_overlay\"));\r\n" + 
			"					obj.css({\r\n" + 
			"						position : 'absolute',\r\n" + 
			"						top : idparent.offset().top,\r\n" + 
			"						left : idparent.offset().left,\r\n" + 
			"						width : idparent.css('width'),\r\n" + 
			"						height : idparent.css('height')\r\n" + 
			"					});\r\n" + 
			"					obj.removeClass(\"collapse\").addClass(\"overlay\");\r\n" + 
			"					obj.animate({\r\n" + 
			"						opacity : 0.8\r\n" + 
			"					}, $stime, function() {\r\n" + 
			"\r\n" + 
			"					});\r\n" + 
			"\r\n" + 
			"					break;\r\n" + 
			"				}\r\n" + 
			"				idparent = idparent.parent();\r\n" + 
			"			}\r\n" + 
			"			break;\r\n" + 
			"		case \"complete\":\r\n" + 
			"			var obj= $(qaboot.escape(\"#\"+data.source.id+\"_overlay\"));\r\n" + 
			"			if(obj.length>0){					\r\n" + 
			"				obj.animate(\r\n" + 
			"					{\r\n" + 
			"						opacity : 0.0\r\n" + 
			"					},\r\n" + 
			"					$etime,\r\n" + 
			"					function() {\r\n" + 
			"						var obj= $(qaboot.escape(\"#\"+data.source.id+\"_overlay\"));\r\n" + 
			"						obj.removeClass(\"overlay\")\r\n" + 
			"								.addClass(\"collapse\");\r\n" + 
			"						obj.remove();\r\n" + 
			"					});\r\n" + 
			"			}\r\n" + 
			"			break;\r\n" + 
			"		case \"success\":\r\n" + 
			"			break;\r\n" + 
			"		default:\r\n" + 
			"		}\r\n" + 
			"	}\r\n"+
			"\r\n" + 
			"qaboot.miniLoaderHandler = function(data){\r\n" + 
			"    switch(data.status){\r\n" + 
			"        case \"begin\":\r\n" + 
			"            var parent = $(qaboot.escape(\"#\"+data.source.id)).parent();\r\n" + 
			"            parent.append(\"<img src='$miniimg' id='\"+data.source.id+\"_img' $miniimagestyle/>\");\r\n" + 
			"            break;\r\n" + 
			"        case \"complete\":\r\n" + 
			"            $(qaboot.escape(\"#\"+data.source.id+\"_img\")).remove();\r\n" + 
			"            break;\r\n" + 
			"        case \"success\":\r\n" + 
			"            break;\r\n" + 
			"        default:\r\n" + 
			"    }\r\n" + 
			"}\r\n";
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent component)
			throws IOException {
	}
	
	public void encodeChildren(FacesContext context, UIComponent component) throws IOException {	
	};
	
	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException{
		if(context==null)
			throw new NullPointerException();
		
		if(!(component instanceof BootAjaxLoaderScriptComponent))
			throw new IOException("Component must be of type "+BootAjaxLoaderScriptComponent.class.getName());
		
		BootAjaxLoaderComponent loaderComponent = ((BootAjaxLoaderScriptComponent)component).getLoaderComponent();
		if(loaderComponent!=null){
			ResponseWriter writer = context.getResponseWriter();
			writer.startElement("script", component);
			
			String miniimagestyle = loaderComponent.get("miniimagestyle");
			String startTimeout = loaderComponent.get("startTimeout");
			String endTimeout = loaderComponent.get("endTimeout");
			String overlayimagestyle = loaderComponent.get("overlayimagestyle");
			
			writer.write(LOADERSCRIPT.replace("$ajaxdiv", loaderComponent.getClientId(context))
					.replace("$miniimg", loaderComponent.get("miniloaderimage"))
					.replace("$overlayimg", loaderComponent.get("overlayloaderimage"))
					.replace("$miniimagestyle", miniimagestyle!=null?("style=\""+miniimagestyle+"\" "):"")
					.replace("$overlayimagestyle", overlayimagestyle!=null?("style=\""+overlayimagestyle+"\" "):"")
					.replace("$stime", startTimeout)
					.replace("$etime", endTimeout));
			writer.endElement("script");
		}
	}
		
	@Override
	public boolean getRendersChildren() {
		return true;
	}

}

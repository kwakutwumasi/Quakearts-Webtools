package com.quakearts.webapp.facelets.tag.input.converter;

import java.io.IOException;

import javax.el.ELException;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;

import com.quakearts.webapp.facelets.ComponentSupport;
import com.quakearts.webapp.facelets.util.ObjectExtractor;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.FaceletException;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagHandler;

public class TimeStampTagHandler extends TagHandler {
	private TagAttribute patternTagAttribute = getAttribute("pattern"),
			typeTagAttribute = getAttribute("type"),dateStyleTagAttribute = getAttribute("datestyle"),
			timeStyleTagAttribute = getAttribute("timestyle"),transientTagAttribute = getAttribute("transient");
	
	public TimeStampTagHandler(TagConfig config) {
		super(config);
	}

	@Override
	public void apply(FaceletContext context, UIComponent parent)
			throws IOException, FacesException, FaceletException, ELException {
		if(parent instanceof UIOutput){
			if(ComponentSupport.isNew(parent)){
				String pattern=null,type=null, dateStyle=null,timeStyle=null,isTransientStr=null;
				TimeStampConverter converter = new TimeStampConverter();
				if(patternTagAttribute !=null){
					ValueExpression patternExpression = patternTagAttribute.getValueExpression(context, String.class);
					pattern = ObjectExtractor.extractString(patternExpression, context);
					if(pattern!=null)
						converter.setPattern(pattern);
				}
				if(typeTagAttribute !=null){
					ValueExpression typeExpression = typeTagAttribute.getValueExpression(context, String.class);
					type = ObjectExtractor.extractString(typeExpression, context);
					if(type!=null)
						converter.setType(type);
				}
				if(dateStyleTagAttribute !=null){
					ValueExpression dateStyleExpression = dateStyleTagAttribute.getValueExpression(context, String.class);
					dateStyle = ObjectExtractor.extractString(dateStyleExpression, context);
					if(dateStyle!=null)
						converter.setDateStyle(dateStyle);
				}
				if(timeStyleTagAttribute !=null){
					ValueExpression timeStyleExpression = timeStyleTagAttribute.getValueExpression(context, String.class);
					timeStyle = ObjectExtractor.extractString(timeStyleExpression, context);
					if(timeStyle!=null)
						converter.setTimeStyle(timeStyle);
				}
				if(transientTagAttribute !=null){
					ValueExpression transientExpression = transientTagAttribute.getValueExpression(context, String.class);
					isTransientStr = ObjectExtractor.extractString(transientExpression, context);
					if(isTransientStr!=null)
						converter.setTransient(Boolean.parseBoolean(isTransientStr));
				}
				((UIOutput)parent).setConverter(converter);
			}
		} else {
			throw new FaceletException("Cannot add "+TimeStampConverter.class.getName()+" to component. Component must be of type "+UIOutput.class.getName());
		}
	}

}

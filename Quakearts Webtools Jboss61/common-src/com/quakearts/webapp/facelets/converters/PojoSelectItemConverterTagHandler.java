package com.quakearts.webapp.facelets.converters;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.component.ValueHolder;
import javax.faces.view.facelets.ComponentHandler;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagHandler;

public class PojoSelectItemConverterTagHandler extends TagHandler {
	
	private TagAttribute collectionAttribute = getRequiredAttribute("collection");
	
	public PojoSelectItemConverterTagHandler(TagConfig config) {
		super(config);
	}

	@Override
	public void apply(FaceletContext context, UIComponent component) throws IOException {
		if(component instanceof ValueHolder){
			if(ComponentHandler.isNew(component)){
				PojoSelectItemConverter converter = new PojoSelectItemConverter();
				converter.setCollectionExpression(collectionAttribute.getValueExpression(context, Object.class));
				((ValueHolder)component).setConverter(converter);
			}
		}
	}

}

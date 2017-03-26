package com.quakearts.webapp.facelets.converters;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.component.ValueHolder;
import javax.faces.view.facelets.ComponentHandler;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagHandler;

public class PojoConverterTagHandler extends TagHandler {
	
	private TagAttribute collectionAttribute = getAttribute("collection");
	
	public PojoConverterTagHandler(TagConfig config) {
		super(config);
	}

	@Override
	public void apply(FaceletContext context, UIComponent component) throws IOException {
		if(component instanceof ValueHolder){
			if(ComponentHandler.isNew(component)){
				PojoConverter converter = new PojoConverter();
				if(collectionAttribute!=null)
					converter.setCollectionExpression(collectionAttribute.getValueExpression(context, Object.class));
				((ValueHolder)component).setConverter(converter);
			}
		}
	}

}

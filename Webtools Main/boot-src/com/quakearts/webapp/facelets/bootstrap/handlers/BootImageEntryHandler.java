package com.quakearts.webapp.facelets.bootstrap.handlers;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagHandler;

import com.quakearts.webapp.facelets.bootstrap.components.BootResponsiveImage;
import com.quakearts.webapp.facelets.bootstrap.components.BootResponsiveImage.ImageEntry;

public class BootImageEntryHandler extends TagHandler {

	private TagAttribute sizeAttribute = getRequiredAttribute("size"), imagePathAttribute = getRequiredAttribute("imagePath");
	public BootImageEntryHandler(TagConfig config) {
		super(config);
	}

	@Override
	public void apply(FaceletContext ctx, UIComponent parent) throws IOException {
		if(!(parent instanceof BootResponsiveImage))
			throw new IOException("Compoenent must be of type "+BootResponsiveImage.class.getName());

		((BootResponsiveImage)parent).addImageEntry(new ImageEntry(sizeAttribute.getInt(ctx), imagePathAttribute.getValue(ctx)));
	}
}

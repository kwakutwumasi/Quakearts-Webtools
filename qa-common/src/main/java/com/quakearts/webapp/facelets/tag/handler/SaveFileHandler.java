package com.quakearts.webapp.facelets.tag.handler;

import java.io.InputStream;

import javax.faces.event.ActionListener;
import com.quakearts.webapp.facelets.tag.listener.SaveFileListener;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

public class SaveFileHandler extends AbstractFileTagHandler {

	private TagAttribute streamAttribute = getRequiredAttribute("stream"),
			fileNameAttribute = getRequiredAttribute("fileName"), 
			overwriteAttribute = getAttribute("overwrite");
	
	public SaveFileHandler(TagConfig config) {
		super(config);
	}

	@Override
	protected ActionListener getActionListener(FaceletContext ctx) {
		return new SaveFileListener(getValueExpression(streamAttribute, ctx, InputStream.class),
				getValueExpression(fileNameAttribute, ctx, String.class),
				getValueExpression(overwriteAttribute, ctx, Object.class),getRoot(ctx));
	}

}

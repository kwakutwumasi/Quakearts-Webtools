package com.quakearts.workflowapp.jbpm.tag.handler;

import java.io.IOException;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;
import java.util.Map;
import com.quakearts.webapp.facelets.bootstrap.components.BootPagination;
import com.quakearts.webapp.facelets.bootstrap.handlers.BootPaginationListenerHandler;
import com.quakearts.workflowapp.jbpm.tag.listener.PaginationMergeListener;

public class PaginationMergeHandler extends BootPaginationListenerHandler {
	
	private TagAttribute metadataAttribute = getRequiredAttribute("metadata");

	public PaginationMergeHandler(TagConfig config) {
		super(config);
	}

	@Override
	protected void addListener(FaceletContext ctx, BootPagination parent)
			throws IOException {
		parent.addPaginationListener(new PaginationMergeListener(metadataAttribute.getValueExpression(ctx, Map.class)));
	}
}

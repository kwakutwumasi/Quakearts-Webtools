package com.quakearts.webapp.facelets.bootstrap.components;

import java.util.HashMap;

import javax.faces.component.UIOutput;

import com.quakearts.webapp.facelets.util.ObjectExtractor;

public class BootFlaticon extends UIOutput {
	private static final HashMap<String, String> VALIDVALUESMAP = new HashMap<String, String>();
	private static final String EMPTY = "";
	private static final String[] VALIDVALUES = new String[] { "app", "analysis-chart", "analytics", "bank-building",
			"bar-chart-reload", "bar-graph-with-dollar-sign", "boss", "building", "buildings", "business", "business-1",
			"business-2", "business-3", "business-affiliate-network", "business-agreement", "business-plan",
			"business-presentation", "businessman-success", "businessmen", "calculator", "cellphone-with-speech-boxes",
			"chess-horse", "coins", "collaboration", "collaboration-1", "commerce", "computer", "computer-1",
			"computer-2", "credit-cards", "cube-divisions", "decision-making", "decision-making-1",
			"different-currencies", "directional-wooden-signs", "document-with-paperclip",
			"dollar-sign-and-piles-of-coins", "first-prize", "folder", "global-conference-call", "goal",
			"graph-analysis", "group", "group-1", "group-2", "group-of-businessmen", "handshake", "hierarchy-levels",
			"hot-cup-of-coffee", "hourglass", "id-card", "idea", "idea-1", "identity-card", "increased-revenue",
			"interface", "interface-1", "interface-2", "interface-3", "interface-4", "interface-5",
			"international-connection", "key-to-success", "labyrinth-and-arrow", "lightbulb-idea", "location", "logo",
			"magnifying-glass-with-two-way-arrows", "management", "manager", "manager-1", "manager-2", "manager-3",
			"manager-4", "manager-5", "manager-6", "managers", "meeting", "meeting-1", "meeting-deadlines", "money",
			"money-1", "money-2", "money-3", "money-4", "multimedia", "noisy-megaphone", "objective-searching",
			"office-block", "office-block", "office-briefcase", "office-chair", "organization", "passage-of-time",
			"people", "people-1", "phone", "photo", "pie-chart", "pie-chart", "presentation", "presentation-1",
			"presentation-2", "presentation-3", "presentation-4", "presentation-board-with-graph", "promoting",
			"promotion", "promotion-1", "rank", "resume", "ribbon-badge-award", "rocket-hitting-target", "rubber-stamp",
			"screen", "search", "selection", "settings", "shape", "shield", "signing-the-contract", "social",
			"social-1", "social-media", "social-media-1", "social-media-2", "social-media-3", "social-media-4",
			"speech", "speech-1", "squares", "stapler", "strategic", "strategy", "symbols", "system", "target",
			"task-complete", "teamwork-in-the-office", "technology", "technology-1", "thumbs-up", "time", "tool",
			"tool-1", "video-conference", "web", "web-1", "web-2", "workspace", };
	static{
		for(String validValue:VALIDVALUES){
			VALIDVALUESMAP.put(validValue, EMPTY);
		}
	}
	
	public static final String COMPONENT_FAMILY="com.quakearts.bootstrap.flaticon";
	public static final String RENDERER_TYPE="com.quakearts.bootstrap.flaticon.renderer";

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	public boolean isValid(String type){
		return VALIDVALUESMAP.get(type)!=null;
	}

	@Override
	public String getRendererType() {
		return RENDERER_TYPE;
	}

	@Override
	public void setRendererType(String rendererType) {
	}
	
	public String get(String attribute) {
		String attributeValue = ObjectExtractor
				.extractString(getValueExpression(attribute), getFacesContext()
						.getELContext());
		if (attributeValue == null)
			attributeValue = (String) getAttributes().get(attribute);

		return attributeValue;
	}
}

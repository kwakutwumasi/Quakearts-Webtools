/*******************************************************************************
 * Copyright (C) 2017 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.webapp.facelets.bootstrap.components;

import java.util.HashMap;

import javax.faces.component.UIOutput;

import com.quakearts.webapp.facelets.util.ObjectExtractor;

public class BootFlaticon extends UIOutput {
	private static final HashMap<String, String> VALIDVALUESMAP = new HashMap<String, String>();	
	public static final String COMPONENT_FAMILY="com.quakearts.bootstrap.flaticon";
	public static final String RENDERER_TYPE="com.quakearts.bootstrap.flaticon.renderer";

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	public boolean isValid(String type){
		return VALIDVALUESMAP.containsKey(type);
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

	public String getTypeName(String type) {
		return VALIDVALUESMAP.get(type);
	}
	
	static{
		VALIDVALUESMAP.put("analytics","management-analytics");
		VALIDVALUESMAP.put("boss","management-boss");
		VALIDVALUESMAP.put("businessmen","management-businessmen");
		VALIDVALUESMAP.put("collaboration","management-collaboration");
		VALIDVALUESMAP.put("collaboration-1","management-collaboration-1");
		VALIDVALUESMAP.put("decision-making","management-decision-making");
		VALIDVALUESMAP.put("decision-making-1","management-decision-making-1");
		VALIDVALUESMAP.put("goal","management-goal");
		VALIDVALUESMAP.put("group","management-group");
		VALIDVALUESMAP.put("group-1","management-group-1");
		VALIDVALUESMAP.put("group-2","management-group-2");
		VALIDVALUESMAP.put("id-card","management-id-card");
		VALIDVALUESMAP.put("idea","management-idea");
		VALIDVALUESMAP.put("idea-1","management-idea-1");
		VALIDVALUESMAP.put("location","management-location");
		VALIDVALUESMAP.put("management","management-management");
		VALIDVALUESMAP.put("manager","management-manager");
		VALIDVALUESMAP.put("manager-1","management-manager-1");
		VALIDVALUESMAP.put("manager-2","management-manager-2");
		VALIDVALUESMAP.put("manager-3","management-manager-3");
		VALIDVALUESMAP.put("manager-4","management-manager-4");
		VALIDVALUESMAP.put("manager-5","management-manager-5");
		VALIDVALUESMAP.put("manager-6","management-manager-6");
		VALIDVALUESMAP.put("managers","management-managers");
		VALIDVALUESMAP.put("meeting","management-meeting");
		VALIDVALUESMAP.put("meeting-1","management-meeting-1");
		VALIDVALUESMAP.put("office-block","management-office-block");
		VALIDVALUESMAP.put("organization","management-organization");
		VALIDVALUESMAP.put("pie-chart","management-pie-chart");
		VALIDVALUESMAP.put("presentation","management-presentation");
		VALIDVALUESMAP.put("presentation-1","management-presentation-1");
		VALIDVALUESMAP.put("presentation-2","management-presentation-2");
		VALIDVALUESMAP.put("presentation-3","management-presentation-3");
		VALIDVALUESMAP.put("presentation-4","management-presentation-4");
		VALIDVALUESMAP.put("promoting","management-promoting");
		VALIDVALUESMAP.put("promotion","management-promotion");
		VALIDVALUESMAP.put("promotion-1","management-promotion-1");
		VALIDVALUESMAP.put("rank","management-rank");
		VALIDVALUESMAP.put("resume","management-resume");
		VALIDVALUESMAP.put("search","management-search");
		VALIDVALUESMAP.put("selection","management-selection");
		VALIDVALUESMAP.put("settings","management-settings");
		VALIDVALUESMAP.put("speech","management-speech");
		VALIDVALUESMAP.put("speech-1","management-speech-1");
		VALIDVALUESMAP.put("strategic","management-strategic");
		VALIDVALUESMAP.put("strategy","management-strategy");
		VALIDVALUESMAP.put("target","management-target");
		VALIDVALUESMAP.put("time","management-time");
		VALIDVALUESMAP.put("video-conference","management-video-conference");
		VALIDVALUESMAP.put("workspace","management-workspace");
		VALIDVALUESMAP.put("analysis-chart","office-analysis-chart");
		VALIDVALUESMAP.put("bank-building","office-bank-building");
		VALIDVALUESMAP.put("bar-chart-reload","office-bar-chart-reload");
		VALIDVALUESMAP.put("bar-graph-with-dollar-sign","office-bar-graph-with-dollar-sign");
		VALIDVALUESMAP.put("business-affiliate-network","office-business-affiliate-network");
		VALIDVALUESMAP.put("business-agreement","office-business-agreement");
		VALIDVALUESMAP.put("business-plan","office-business-plan");
		VALIDVALUESMAP.put("business-presentation","office-business-presentation");
		VALIDVALUESMAP.put("businessman-success","office-businessman-success");
		VALIDVALUESMAP.put("calculator","office-calculator");
		VALIDVALUESMAP.put("cellphone-with-speech-boxes","office-cellphone-with-speech-boxes");
		VALIDVALUESMAP.put("chess-horse","office-chess-horse");
		VALIDVALUESMAP.put("credit-cards","office-credit-cards");
		VALIDVALUESMAP.put("cube-divisions","office-cube-divisions");
		VALIDVALUESMAP.put("different-currencies","office-different-currencies");
		VALIDVALUESMAP.put("directional-wooden-signs","office-directional-wooden-signs");
		VALIDVALUESMAP.put("document-with-paperclip","office-document-with-paperclip");
		VALIDVALUESMAP.put("dollar-sign-and-piles-of-coins","office-dollar-sign-and-piles-of-coins");
		VALIDVALUESMAP.put("first-prize","office-first-prize");
		VALIDVALUESMAP.put("global-conference-call","office-global-conference-call");
		VALIDVALUESMAP.put("graph-analysis","office-graph-analysis");
		VALIDVALUESMAP.put("group-of-businessmen","office-group-of-businessmen");
		VALIDVALUESMAP.put("handshake","office-handshake");
		VALIDVALUESMAP.put("hierarchy-levels","office-hierarchy-levels");
		VALIDVALUESMAP.put("hot-cup-of-coffee","office-hot-cup-of-coffee");
		VALIDVALUESMAP.put("hourglass","office-hourglass");
		VALIDVALUESMAP.put("identity-card","office-identity-card");
		VALIDVALUESMAP.put("increased-revenue","office-increased-revenue");
		VALIDVALUESMAP.put("international-connection","office-international-connection");
		VALIDVALUESMAP.put("key-to-success","office-key-to-success");
		VALIDVALUESMAP.put("labyrinth-and-arrow","office-labyrinth-and-arrow");
		VALIDVALUESMAP.put("lightbulb-idea","office-lightbulb-idea");
		VALIDVALUESMAP.put("magnifying-glass-with-two-way-arrows","office-magnifying-glass-with-two-way-arrows");
		VALIDVALUESMAP.put("meeting-deadlines","office-meeting-deadlines");
		VALIDVALUESMAP.put("noisy-megaphone","office-noisy-megaphone");
		VALIDVALUESMAP.put("objective-searching","office-objective-searching");
		VALIDVALUESMAP.put("office-block","office-office-block");
		VALIDVALUESMAP.put("office-briefcase","office-office-briefcase");
		VALIDVALUESMAP.put("office-chair","office-office-chair");
		VALIDVALUESMAP.put("passage-of-time","office-passage-of-time");
		VALIDVALUESMAP.put("pie-chart","office-pie-chart");
		VALIDVALUESMAP.put("presentation-board-with-graph","office-presentation-board-with-graph");
		VALIDVALUESMAP.put("ribbon-badge-award","office-ribbon-badge-award");
		VALIDVALUESMAP.put("rocket-hitting-target","office-rocket-hitting-target");
		VALIDVALUESMAP.put("rubber-stamp","office-rubber-stamp");
		VALIDVALUESMAP.put("signing-the-contract","office-signing-the-contract");
		VALIDVALUESMAP.put("stapler","office-stapler");
		VALIDVALUESMAP.put("task-complete","office-task-complete");
		VALIDVALUESMAP.put("teamwork-in-the-office","office-teamwork-in-the-office");
		VALIDVALUESMAP.put("thumbs-up","office-thumbs-up");
		VALIDVALUESMAP.put("app","collection-app");
		VALIDVALUESMAP.put("building","collection-building");
		VALIDVALUESMAP.put("buildings","collection-buildings");
		VALIDVALUESMAP.put("business","collection-business");
		VALIDVALUESMAP.put("business-1","collection-business-1");
		VALIDVALUESMAP.put("business-2","collection-business-2");
		VALIDVALUESMAP.put("business-3","collection-business-3");
		VALIDVALUESMAP.put("coins","collection-coins");
		VALIDVALUESMAP.put("commerce","collection-commerce");
		VALIDVALUESMAP.put("computer","collection-computer");
		VALIDVALUESMAP.put("computer-1","collection-computer-1");
		VALIDVALUESMAP.put("computer-2","collection-computer-2");
		VALIDVALUESMAP.put("folder","collection-folder");
		VALIDVALUESMAP.put("interface","collection-interface");
		VALIDVALUESMAP.put("interface-1","collection-interface-1");
		VALIDVALUESMAP.put("interface-2","collection-interface-2");
		VALIDVALUESMAP.put("interface-3","collection-interface-3");
		VALIDVALUESMAP.put("interface-4","collection-interface-4");
		VALIDVALUESMAP.put("interface-5","collection-interface-5");
		VALIDVALUESMAP.put("logo","collection-logo");
		VALIDVALUESMAP.put("money","collection-money");
		VALIDVALUESMAP.put("money-1","collection-money-1");
		VALIDVALUESMAP.put("money-2","collection-money-2");
		VALIDVALUESMAP.put("money-3","collection-money-3");
		VALIDVALUESMAP.put("money-4","collection-money-4");
		VALIDVALUESMAP.put("multimedia","collection-multimedia");
		VALIDVALUESMAP.put("people","collection-people");
		VALIDVALUESMAP.put("people-1","collection-people-1");
		VALIDVALUESMAP.put("phone","collection-phone");
		VALIDVALUESMAP.put("photo","collection-photo");
		VALIDVALUESMAP.put("screen","collection-screen");
		VALIDVALUESMAP.put("shape","collection-shape");
		VALIDVALUESMAP.put("shield","collection-shield");
		VALIDVALUESMAP.put("social","collection-social");
		VALIDVALUESMAP.put("social-1","collection-social-1");
		VALIDVALUESMAP.put("social-media","collection-social-media");
		VALIDVALUESMAP.put("social-media-1","collection-social-media-1");
		VALIDVALUESMAP.put("social-media-2","collection-social-media-2");
		VALIDVALUESMAP.put("social-media-3","collection-social-media-3");
		VALIDVALUESMAP.put("social-media-4","collection-social-media-4");
		VALIDVALUESMAP.put("squares","collection-squares");
		VALIDVALUESMAP.put("symbols","collection-symbols");
		VALIDVALUESMAP.put("system","collection-system");
		VALIDVALUESMAP.put("technology","collection-technology");
		VALIDVALUESMAP.put("technology-1","collection-technology-1");
		VALIDVALUESMAP.put("tool","collection-tool");
		VALIDVALUESMAP.put("tool-1","collection-tool-1");
		VALIDVALUESMAP.put("web","collection-web");
		VALIDVALUESMAP.put("web-1","collection-web-1");
		VALIDVALUESMAP.put("web-2","collection-web-2");
	}
}

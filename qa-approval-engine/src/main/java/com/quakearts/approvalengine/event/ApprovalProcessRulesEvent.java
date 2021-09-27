package com.quakearts.approvalengine.event;

import java.util.List;

import com.quakearts.approvalengine.model.ApprovalProcessRules;

public class ApprovalProcessRulesEvent {
	private final List<ApprovalProcessRules> approvalProcessRules;
	private final ApprovalProcessRulesEventType eventType;
	
	public ApprovalProcessRulesEvent(List<ApprovalProcessRules> approvalProcessRules,
			ApprovalProcessRulesEventType eventType) {
		this.approvalProcessRules = approvalProcessRules;
		this.eventType = eventType;
	}
	
	public List<ApprovalProcessRules> getApprovalProcessRules() {
		return approvalProcessRules;
	}
	
	public ApprovalProcessRulesEventType getEventType() {
		return eventType;
	}
}

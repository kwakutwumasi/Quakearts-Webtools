package com.quakearts.approvalengine.event;

import java.util.Optional;

import com.quakearts.approvalengine.context.ApprovalContext;
import com.quakearts.approvalengine.model.ApprovalProcess;

public class ApprovalProcessEvent {
	private final ApprovalProcess approvalProcess;
	private final Optional<ApprovalContext> contextOptional;
	private final ApprovalEventType eventType;
	
	public ApprovalProcessEvent(ApprovalProcess approvalProcess, ApprovalEventType eventType) {
		this(approvalProcess, null, eventType);
	}
	
	public ApprovalProcessEvent(ApprovalProcess approvalProcess, ApprovalContext context,
			ApprovalEventType eventType) {
		this.approvalProcess = approvalProcess;
		this.contextOptional = context == null? Optional.empty():Optional.of(context);
		this.eventType = eventType;
	}
	
	public ApprovalProcess getApprovalProcess() {
		return approvalProcess;
	}
	
	public Optional<ApprovalContext> getContext() {
		return contextOptional;
	}
	
	public ApprovalEventType getEventType() {
		return eventType;
	}
}

package com.quakearts.approvalengine.services.impl;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import com.quakearts.approvalengine.context.ApprovalContext;
import com.quakearts.approvalengine.context.ApprovalContextBuilder;
import com.quakearts.approvalengine.exception.ApprovalCompleteException;
import com.quakearts.approvalengine.exception.ApprovalProcessingException;
import com.quakearts.approvalengine.exception.InvalidApprovalException;
import com.quakearts.approvalengine.exception.InvalidApprovalGroupException;
import com.quakearts.approvalengine.exception.InvalidApprovalProcessException;
import com.quakearts.approvalengine.exception.InvalidApproverException;
import com.quakearts.approvalengine.exception.MissingFieldException;
import com.quakearts.approvalengine.exception.NotFoundException;
import com.quakearts.approvalengine.model.Approval;
import com.quakearts.approvalengine.model.ApprovalProcess;
import com.quakearts.approvalengine.model.Approver;
import com.quakearts.approvalengine.model.Approval.ApprovalAction;
import com.quakearts.webapp.orm.DataStore;

public class ApprovalContextBuilderImpl implements ApprovalContextBuilder {
		
	private ApprovalProcess approvalProcess;
	private Approval approval = new Approval();
	private DataStore dataStore;
	
	public ApprovalContextBuilderImpl(DataStore dataStore) {
		this.dataStore = dataStore;
	}
	
	@Override
	public ApprovalContextBuilder setApproverAs(String externalId, String groupName) 
			throws ApprovalProcessingException {
		if(externalId == null || groupName == null)
			throw new MissingFieldException("Field {0} is missing and is required", 
					externalId == null? "externalId":"groupName");
		
		Optional<Approver> optionalApprover = dataStore
				.find(Approver.class).filterBy("externalId")
				.withAValueEqualTo(externalId)
				.filterBy("group.name").withAValueEqualTo(groupName)
				.thenGetFirst();
		
		if(!optionalApprover.isPresent())
			throw new NotFoundException("The approver with externalId {0} belonging to ApprovalGroup {1} cannot be found",
					externalId, groupName);
		
		if(!optionalApprover.get().isValid())
			throw new InvalidApproverException();
		
		approval.setApprover(optionalApprover.get());	

		return this;
	}
	
	@Override
	public ApprovalContextBuilder setActionAs(ApprovalAction action){
		approval.setAction(action);
		return this;
	}
			
	@Override
	public ApprovalContextBuilder setApprovalProcessAs(ApprovalProcess approvalProcess) {
		this.approvalProcess = approvalProcess;
		return this;
	}
	
	@Override
	public ApprovalContext thenBuild() 
			throws MissingFieldException, InvalidApprovalException, 
			ApprovalCompleteException, NotFoundException, InvalidApprovalProcessException, 
			InvalidApprovalGroupException {
		if(approvalProcess == null)
			throw new MissingFieldException("approvalProcess is required");

		if(approvalProcess.getId()<=0)
			throw new MissingFieldException("approvalProcess id is required");
		
		if(approval.getApprover() == null) {
			throw new MissingFieldException("approval approver is required");
		}

		if(approval.getAction() == null) {
			throw new MissingFieldException("approval action is required");
		}

		long approvalProcessId = approvalProcess.getId();
		approvalProcess = dataStore.get(ApprovalProcess.class, approvalProcessId);
		
		if(approvalProcess == null)
			throw new NotFoundException("approvalProccess with id {0} was not found", approvalProcessId);
		
		if(approvalProcess.isComplete())
			throw new ApprovalCompleteException("The given approvalProcess has already been completed on {0}",
				approvalProcess.getCompleteDate());

		if(!approvalProcess.isValid())
			throw new InvalidApprovalProcessException();

		if(!approvalProcess.getGroup().isValid())
			throw new InvalidApprovalGroupException();

		if(approval.getApprover().getGroup().getId() != approvalProcess.getGroup().getId())
			throw new InvalidApprovalException("approver {0} is not in the same approvalGroup"
					+ " as approvalProcess {1}: {2} != {3}", approval
					.getApprover().getExternalId(), 
					approvalProcess.getId(),
					approval.getApprover().getGroup().getName(),
					approvalProcess.getGroup().getName());				

		approval.setApprovalProcess(approvalProcess);
		approval.setApprovalDate(Timestamp.from(Instant.now()));
		approval.setValid(true);
		
		return new ApprovalContextImpl(approval, approvalProcess, dataStore);
	}
}
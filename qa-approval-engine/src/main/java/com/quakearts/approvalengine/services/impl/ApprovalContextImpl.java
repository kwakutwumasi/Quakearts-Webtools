package com.quakearts.approvalengine.services.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.quakearts.approvalengine.context.ApprovalContext;
import com.quakearts.approvalengine.model.Approval;
import com.quakearts.approvalengine.model.ApprovalProcess;
import com.quakearts.webapp.orm.DataStore;

public final class ApprovalContextImpl implements ApprovalContext {
	private Approval approval;
	private ApprovalProcess approvalProcess;
	private Map<Integer, Integer> levelCount;
	private Set<String> currentApprovers;
	private boolean complete;
	private boolean levelFound;
	private DataStore dataStore;

	ApprovalContextImpl(Approval approval, ApprovalProcess approvalProcess, DataStore dataStore) {
		this.approval = approval;
		this.approvalProcess = approvalProcess;
		this.dataStore = dataStore;
	}

	@Override
	public Approval getApproval() {
		return approval;
	}

	@Override
	public ApprovalProcess getApprovalProcess() {
		return approvalProcess;
	}
		
	@Override
	public int getApprovalCount(int level) {
		loadLevelCountAndApprovers();
		if(levelCount.containsKey(level)){
			return levelCount.get(level);
		}
		
		return 0;
	}

	private void loadLevelCountAndApprovers() {
		if(levelCount == null) {
			levelCount = new HashMap<>();
			List<Approval> approvalList = dataStore.find(Approval.class)
					.filterBy("approvalProcess")
					.withAValueEqualTo(approvalProcess)
					.filterBy("valid").withAValueEqualTo(true).thenList();
		
			currentApprovers = new HashSet<>();
			for(Approval completedApproval:approvalList){
				currentApprovers.add(completedApproval.getApprover().getExternalId());
				if(levelCount.containsKey(completedApproval.getApprover().getLevel())){
					int currentCount = levelCount.get(completedApproval.getApprover().getLevel()) + 1;
					levelCount.put(completedApproval.getApprover().getLevel(), currentCount);
				} else {
					levelCount.put(completedApproval.getApprover().getLevel(), 1);
				}
			}
		}
	}

	@Override
	public boolean isComplete() {
		return complete;
	}

	void complete() {
		this.complete = true;
	}

	@Override
	public boolean levelWasFound() {
		return levelFound;
	}

	void levelFound() {
		this.levelFound = true;
	}

	@Override
	public Set<String> getCurrentApprovers() {
		loadLevelCountAndApprovers();
		return currentApprovers;
	}
}

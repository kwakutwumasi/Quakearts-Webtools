package com.quakearts.approvalengine.services.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.quakearts.approvalengine.context.ApprovalContext;
import com.quakearts.approvalengine.exception.ApprovalCompleteException;
import com.quakearts.approvalengine.exception.ApprovalProcessingException;
import com.quakearts.approvalengine.exception.DuplicateRuleException;
import com.quakearts.approvalengine.exception.InvalidApprovalException;
import com.quakearts.approvalengine.exception.InvalidApprovalGroupException;
import com.quakearts.approvalengine.exception.InvalidApprovalProcessException;
import com.quakearts.approvalengine.exception.InvalidApprovalRulesException;
import com.quakearts.approvalengine.exception.MissingFieldException;
import com.quakearts.approvalengine.exception.NotFoundException;
import com.quakearts.approvalengine.model.Approval;
import com.quakearts.approvalengine.model.Approval.ApprovalAction;
import com.quakearts.approvalengine.services.ApprovalService;
import com.quakearts.approvalengine.model.ApprovalGroup;
import com.quakearts.approvalengine.model.ApprovalProcess;
import com.quakearts.approvalengine.model.ApprovalProcessRules;
import com.quakearts.approvalengine.model.ApprovalRule;
import com.quakearts.approvalengine.model.ApprovalRules;
import com.quakearts.approvalengine.model.Approver;
import com.quakearts.webapp.orm.DataStore;
import com.quakearts.webapp.orm.DataStoreFactory;
import com.quakearts.webapp.orm.cdi.annotation.DataStoreFactoryHandle;

@Singleton
public class ApprovalServiceImpl implements ApprovalService {
	
	private static final String GROUP_NAME = "groupName";

	@Inject @DataStoreFactoryHandle
	private DataStoreFactory dataStoreFactory;
	
	private Map<Long, Object> approvalSynchronization = new ConcurrentHashMap<>();

	@Override
	public ApprovalProcess initiateApproval(String groupName, String ruleName) throws ApprovalProcessingException {
		if(groupName == null || ruleName == null)
			throw new MissingFieldException("Field {0} is missing and is required",
					groupName == null? GROUP_NAME:"ruleName");

		Set<String> approvalRuleSet = new HashSet<>();
		approvalRuleSet.add(ruleName);
		return initiateApproval(groupName, approvalRuleSet);
	}
	
	@Override
	public ApprovalProcess initiateApproval(String groupName, Set<String> rulesSet) throws ApprovalProcessingException {
		if(groupName == null || rulesSet == null || rulesSet.isEmpty())
			throw new MissingFieldException(MessageFormat.format("Field {0} is missing or empty and is required", 
					groupName == null?GROUP_NAME:"rulesSet"));
		
		DataStore dataStore = dataStoreFactory.getDataStore();
		ApprovalGroup approvalGroup = findApprovalGroup(groupName, dataStore);
		ApprovalProcess approvalProcess = storeApprovalProcess(dataStore, approvalGroup);		
		storeApprovalRules(rulesSet, dataStore, approvalProcess, true);
		
		return approvalProcess;
	}

	private ApprovalGroup findApprovalGroup(String groupName, DataStore dataStore)
			throws NotFoundException, InvalidApprovalGroupException {
		Optional<ApprovalGroup> optionalGroup = dataStore.find(ApprovalGroup.class)
				.filterBy("name").withAValueEqualTo(groupName)
				.thenGetFirst();
		
		if(!optionalGroup.isPresent())
			throw new NotFoundException("The ApprovalGroup with groupName {0} cannot be found", groupName);

		if(!optionalGroup.get().isValid())
			throw new InvalidApprovalGroupException();
		
		return optionalGroup.get();
	}

	@Override
	public ApprovalProcess initiateApproval(String groupName, Set<String> activeRuleNamesSet,
			Set<String> inActiveRuleNamesSet) throws ApprovalProcessingException {
		validateParameters(groupName, activeRuleNamesSet, inActiveRuleNamesSet);
		DataStore dataStore = dataStoreFactory.getDataStore();
		ApprovalGroup approvalGroup = findApprovalGroup(groupName, dataStore);
		ApprovalProcess approvalProcess = storeApprovalProcess(dataStore, approvalGroup);		
		storeApprovalRules(activeRuleNamesSet, dataStore, approvalProcess, true);
		storeApprovalRules(inActiveRuleNamesSet, dataStore, approvalProcess, false);
		
		return approvalProcess;
	}

	private void validateParameters(String groupName, Set<String> activeRuleNamesSet, Set<String> inActiveRuleNamesSet)
			throws MissingFieldException, DuplicateRuleException {
		if(groupName == null || activeRuleNamesSet == null 
				|| activeRuleNamesSet.isEmpty() 
				|| inActiveRuleNamesSet == null
				|| inActiveRuleNamesSet.isEmpty()) {
			String field;
			if(groupName == null) {
				field = GROUP_NAME;
			} else if(activeRuleNamesSet == null 
				|| activeRuleNamesSet.isEmpty()) {
				field = "activeRuleNamesSet";
			} else {
				field = "inActiveRuleNamesSet";
			}
			throw new MissingFieldException(MessageFormat.format("Field {0} is missing or empty and is required", 
					field));
		}
		
		Set<String> duplicates = activeRuleNamesSet.stream()
				.filter(inActiveRuleNamesSet::contains)
				.collect(Collectors.toSet()); 
		if(!duplicates.isEmpty())
			throw new DuplicateRuleException("The set of activeRuleNamesSet and inActiveRuleNamesSet contains duplicates: {0}", duplicates);
	}

	private ApprovalProcess storeApprovalProcess(DataStore dataStore, ApprovalGroup group) {
		ApprovalProcess approvalProcess = new ApprovalProcess();
		approvalProcess.setValid(true);
		approvalProcess.setStartDate(Timestamp.from(Instant.now()));
		approvalProcess.setGroup(group);
		
		dataStore.save(approvalProcess);
		dataStore.flushBuffers();
		return approvalProcess;
	}

	private void storeApprovalRules(Set<String> rulesSet, DataStore dataStore,
			ApprovalProcess approvalProcess, boolean activated) throws NotFoundException, InvalidApprovalRulesException {
		List<ApprovalRules> approvalRules = dataStore
				.find(ApprovalRules.class).filterBy("name")
				.withAValueEqualToOneOf(rulesSet.toArray(new Serializable[rulesSet.size()]))
				.filterBy("group").withAValueEqualTo(approvalProcess.getGroup()).thenList();
		
		if(approvalRules.size() != rulesSet.size()){
			List<String> found = approvalRules.stream()
					.map(ApprovalRules::getName).collect(Collectors.toList());
			List<String> notFound = rulesSet.stream().filter(name->!found.contains(name))
					.collect(Collectors.toList());
			throw new NotFoundException("One or more ApprovalRules were not found: {0}",
					notFound);
		}
		
		for(ApprovalRules rules:approvalRules) {
			if(!rules.isActive())
				throw new InvalidApprovalRulesException("The provided rulesName {0} is not valid", rules.getName());
			
			ApprovalProcessRules approvalProcessRules = new ApprovalProcessRules();
			approvalProcessRules.setApprovalRules(rules);
			approvalProcessRules.setApprovalProcess(approvalProcess);
			approvalProcessRules.setActive(activated);
			dataStore.save(approvalProcessRules);
			approvalProcess.getApprovalProcessRulesSet().add(approvalProcessRules);
		}
	}

	@Override
	public void processApproval(ApprovalContext context) throws ApprovalProcessingException {
		ApprovalContextImpl contextImpl = (ApprovalContextImpl) context;
		Approval approval = context.getApproval();
		Approver approver = approval.getApprover();
		
		Object approvalSync = getApprovalSynchronizer(context.getApprovalProcess().getId());
		
		synchronized (approvalSync) {
			if(context.getCurrentApprovers().contains(approval
					.getApprover().getExternalId()))
				throw new InvalidApprovalException("Approver {0} has already approved this ApprovalProcess", approval
					.getApprover().getExternalId());
			
			if(approval.getAction() == ApprovalAction.APPROVED)
				testApprovalCompleteness(contextImpl, approver);
			
			completeApprovalProcess(context, approval);			
		}
	}

	private Object getApprovalSynchronizer(long id) {
		return approvalSynchronization.computeIfAbsent(id, key-> key);
	}

	private void testApprovalCompleteness(ApprovalContextImpl context, Approver approver)
			throws InvalidApprovalException {
		testApprovalRules(context, approver);
	}

	private void testApprovalRules(ApprovalContextImpl context, Approver approver)
			throws InvalidApprovalException {
		
		for(ApprovalProcessRules approvalProcessRules:context.getApprovalProcess()
				.getApprovalProcessRulesSet()) {
			testApprovalRuleElements(context, approver, approvalProcessRules);
			
			if(context.isComplete())
				break;
		}
		
		if(!context.levelWasFound())
			throw new InvalidApprovalException("Approver {0} does not have the authorization to approve", approver.getExternalId());
	}

	private void testApprovalRuleElements(ApprovalContextImpl context, Approver approver, ApprovalProcessRules approvalProcessRules)
			throws InvalidApprovalException {
		if(!approvalProcessRules.isActive())
			return;
		
		boolean ruleComplete = true;
		for(ApprovalRule rule:approvalProcessRules.getApprovalRules()
				.getRuleElements()) {
			if(rule.isActive())
				ruleComplete = testApprovalRuleElement(context, approver, ruleComplete, rule);
			if(context.levelWasFound() && !ruleComplete)
				break;
		}

		if(ruleComplete)
			context.complete();
	}

	private boolean testApprovalRuleElement(ApprovalContextImpl context, Approver approver, boolean ruleComplete,
			ApprovalRule rule) throws InvalidApprovalException {
		if(rule.getLevel() == approver.getLevel()) {
			int currentCount = context.getApprovalCount(approver.getLevel())+1;

			if(currentCount > rule.getCount())
				throw new InvalidApprovalException("The approval exceeds the count required for approval process rules");
			
			ruleComplete = ruleComplete && currentCount == rule.getCount();
			
			context.levelFound();
		} else {
			ruleComplete = ruleComplete && context.getApprovalCount(rule.getLevel()) == rule.getCount();
		}
		return ruleComplete;
	}

	private void completeApprovalProcess(ApprovalContext context, Approval approval) {
		DataStore dataStore = dataStoreFactory.getDataStore();
		dataStore.save(approval);
		ApprovalProcess approvalProcess = context.getApprovalProcess();
		if(approval.getAction() == ApprovalAction.APPROVED && context.isComplete()){
			approvalProcess.setComplete(true);
			approvalProcess.setApproved(true);
			approvalProcess.setCompleteDate(Timestamp.from(Instant.now()));
			dataStore.update(approvalProcess);
		} else if(approval.getAction() == ApprovalAction.REJECTED) {
			approvalProcess.setComplete(true);
			approvalProcess.setCompleteDate(Timestamp.from(Instant.now()));
			dataStore.update(approvalProcess);
		}
	}

	@Override
	public void activateApprovalProcessRules(Set<String> ruleNames, ApprovalProcess approvalProcess)
			throws ApprovalProcessingException {
		updateApprovalProcessRulesValidity(ruleNames, approvalProcess, true);
	}

	@Override
	public void deactivateApprovalProcessRules(Set<String> ruleNames, ApprovalProcess approvalProcess)
			throws ApprovalProcessingException {
		updateApprovalProcessRulesValidity(ruleNames, approvalProcess, false);
	}

	private void updateApprovalProcessRulesValidity(Set<String> ruleNames, ApprovalProcess approvalProcess,
			boolean activate) throws MissingFieldException, InvalidApprovalGroupException, NotFoundException, 
			InvalidApprovalProcessException, ApprovalCompleteException {
		checkParameters(ruleNames, approvalProcess);		
		DataStore dataStore = dataStoreFactory.getDataStore();
		List<ApprovalProcessRules> approvalProcessRules = 
				findApprovalProcessRules(ruleNames, approvalProcess,dataStore);		
		validateApprovalGroupAndProcess(approvalProcessRules);
		for(ApprovalProcessRules foundApprovalProcessRules: approvalProcessRules) {
			foundApprovalProcessRules.setActive(activate);
			dataStore.update(foundApprovalProcessRules);
		}
	}

	private void checkParameters(Set<String> ruleNames, ApprovalProcess approvalProcess) throws MissingFieldException {
		if(ruleNames == null || ruleNames.isEmpty() || approvalProcess == null 
				|| approvalProcess.getId() <= 0){
			throw new MissingFieldException("Field {0} is missing and is required", 
					ruleNames == null || ruleNames.isEmpty()? "ruleName":"approvalProcess");
		}
	}

	private List<ApprovalProcessRules> findApprovalProcessRules(Set<String> ruleNames, ApprovalProcess approvalProcess,
			DataStore dataStore) throws NotFoundException {
		List<ApprovalProcessRules> approvalProcessRules = dataStore.find(ApprovalProcessRules.class)
				.filterBy("approvalRules.name").withAValueEqualToOneOf(ruleNames.toArray(new Serializable[ruleNames.size()]))
				.filterBy("approvalProcess").withAValueEqualTo(approvalProcess)
				.thenList();
		if(approvalProcessRules.size() != ruleNames.size()) {
			Set<String> found = approvalProcessRules.stream()
					.map(ApprovalProcessRules::getApprovalRules)
					.map(ApprovalRules::getName)
					.collect(Collectors.toSet());
			Set<String> notFound = ruleNames.stream()
					.filter(name->!found.contains(name))
					.collect(Collectors.toSet());
			
			throw new NotFoundException("One or more ApprovalProcessRules were not found: {0}",
					notFound);
		}
		return approvalProcessRules;
	}

	private void validateApprovalGroupAndProcess(List<ApprovalProcessRules> approvalProcessRules)
			throws InvalidApprovalGroupException, InvalidApprovalProcessException, ApprovalCompleteException {
		ApprovalProcess approvalProcess = approvalProcessRules
				.iterator().next().getApprovalProcess();
		
		if(approvalProcess.isComplete())
			throw new ApprovalCompleteException("ApprovalRule with id {0} has been completed", approvalProcess
					.getId());
		
		if(!approvalProcess.isValid())
			throw new InvalidApprovalProcessException();
		
		if(!approvalProcess.getGroup().isValid())
			throw new InvalidApprovalGroupException();
	}

}

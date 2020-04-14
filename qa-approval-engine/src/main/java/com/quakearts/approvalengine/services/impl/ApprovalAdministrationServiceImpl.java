package com.quakearts.approvalengine.services.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.quakearts.approvalengine.exception.DuplicateApproverIdentityException;
import com.quakearts.approvalengine.exception.DuplicateGroupNameException;
import com.quakearts.approvalengine.exception.DuplicateLevelException;
import com.quakearts.approvalengine.exception.DuplicateRuleException;
import com.quakearts.approvalengine.exception.InvalidApprovalGroupException;
import com.quakearts.approvalengine.exception.InvalidApprovalRulesException;
import com.quakearts.approvalengine.exception.InvalidCountException;
import com.quakearts.approvalengine.exception.MissingFieldException;
import com.quakearts.approvalengine.exception.NotFoundException;
import com.quakearts.approvalengine.model.ApprovalGroup;
import com.quakearts.approvalengine.model.ApprovalProcess;
import com.quakearts.approvalengine.model.ApprovalRule;
import com.quakearts.approvalengine.model.ApprovalRules;
import com.quakearts.approvalengine.model.Approver;
import com.quakearts.approvalengine.services.ApprovalAdministrationService;
import com.quakearts.webapp.orm.DataStore;
import com.quakearts.webapp.orm.DataStoreFactory;
import com.quakearts.webapp.orm.cdi.annotation.DataStoreFactoryHandle;

@Singleton
public class ApprovalAdministrationServiceImpl implements ApprovalAdministrationService {

	private static final String NAME = "name";
	private static final String RULES_NAME = "rulesName";
	private static final String FIELD_REQUIRED = "Field {0} is required";
	private static final String EXTERNAL_ID = "externalId";
	private static final String GROUP_NAME_PARAMETER = "groupName";
	private static final String GROUP_NAME = "group.name";

	@Inject @DataStoreFactoryHandle
	private DataStoreFactory factory;
	
	@Override
	public ApprovalGroup createApprovalGroup(String name) throws DuplicateGroupNameException {
		if(name == null)
			throw new MissingFieldException("Field name is missing and is required");
		
		DataStore dataStore = factory.getDataStore();
		
		Optional<ApprovalGroup> optionalApprovalGroup = 
				dataStore.find(ApprovalGroup.class)
				.filterBy(NAME).withAValueEqualTo(name)
				.useAResultLimitOf(1)
				.thenGetFirst();
		
		if(optionalApprovalGroup.isPresent())
			throw new DuplicateGroupNameException();
		
		ApprovalGroup approvalGroup = new ApprovalGroup();
		approvalGroup.setName(name);
		approvalGroup.setValid(true);
		
		dataStore.save(approvalGroup);
		dataStore.flushBuffers();
		
		return approvalGroup;
	}
	
	@Override
	public ApprovalRulesBuilder createApprovalRules(String name, String groupName) throws NotFoundException, 
		InvalidApprovalGroupException, DuplicateRuleException {
		return new ApprovalRuleBuilderImpl(name, groupName, factory.getDataStore());
	}

	private class ApprovalRuleBuilderImpl implements ApprovalRulesBuilder {

		private ApprovalRules approvalRules;
		private Map<Integer, ApprovalRule> approvalRulesSet;
		private DataStore dataStore;
		
		private ApprovalRuleBuilderImpl(String name, String groupName, DataStore dataStore) 
				throws NotFoundException, InvalidApprovalGroupException, DuplicateRuleException {
			checkNullStrings(name, groupName, NAME, GROUP_NAME_PARAMETER);
			
			this.dataStore = dataStore;
			
			Optional<ApprovalRules> optionalApprovalRules = dataStore
					.find(ApprovalRules.class)
					.filterBy(NAME).withAValueEqualTo(name)
					.filterBy(GROUP_NAME).withAValueEqualTo(groupName)
					.useAResultLimitOf(1)
					.thenGetFirst();
			
			if(optionalApprovalRules.isPresent())
				throw new DuplicateRuleException("The ApprovalRules name {0} has already been created for ApprovalGroup with name {1}",
						name, groupName);
			
			getApprovalRules().setName(name);			
			getApprovalRules().setGroup(findGroupByName(groupName, dataStore));
		}

		@Override
		public ApprovalRulesBuilder setPriorityAs(int priority) {
			getApprovalRules().setPriority(priority);
			return this;
		}
		
		@Override
		public ApprovalRulesBuilder addRule(int level, int approvalCount)
				throws DuplicateLevelException, InvalidCountException {
			if(approvalCount <= 0)
				throw new InvalidCountException();
			
			if(getApprovalRulesSet().containsKey(level))
				throw new DuplicateLevelException();
			
			ApprovalRule approvalRule = new ApprovalRule();
			approvalRule.setCount(approvalCount);
			approvalRule.setLevel(level);
			approvalRule.setActive(true);
			approvalRule.setRules(getApprovalRules());
			getApprovalRulesSet().put(level, approvalRule);
			return this;
		}

		@Override
		public ApprovalRules thenBuild() {
			if(getApprovalRules().getPriority() == 0)
				getApprovalRules().setPriority(1);
			
			dataStore.save(getApprovalRules());
			dataStore.flushBuffers();

			if(!getApprovalRulesSet().isEmpty()){					
				Collection<ApprovalRule> approvalRuleCollection 
					= getApprovalRulesSet().values();
				approvalRuleCollection.forEach(dataStore::save);
				dataStore.flushBuffers();
				getApprovalRules().getRuleElements().addAll(approvalRuleCollection);
			}
			
			return getApprovalRules();
		}

		private ApprovalRules getApprovalRules() {
			if(approvalRules == null)
				approvalRules = new ApprovalRules();
			
			return approvalRules;
		}

		private Map<Integer, ApprovalRule> getApprovalRulesSet() {
			if(approvalRulesSet == null)
				approvalRulesSet = new HashMap<>();
			
			return approvalRulesSet;
		}
	}
	
	@Override
	public ApprovalRule createApprovalRule(int level, int approvalCount, String rulesName, String groupName) 
			throws InvalidCountException, NotFoundException, InvalidApprovalGroupException, InvalidApprovalRulesException, DuplicateLevelException {
		checkNullStrings(groupName, rulesName, GROUP_NAME_PARAMETER, RULES_NAME);
		if(approvalCount <= 0)
			throw new InvalidCountException();

		DataStore dataStore = factory.getDataStore();
		
		ApprovalRules approvalRules = findApprovalRules(rulesName, groupName, dataStore);
		if(!approvalRules.isActive())
			throw new InvalidApprovalRulesException("The ApprovalRules set named {0} belonging to ApprovalGroup named {1} is not valid",
					rulesName, groupName);
		
		if(approvalRules.getRuleElements().stream().anyMatch(ruleElement->ruleElement.getLevel()==level))
			throw new DuplicateLevelException();
		
		ApprovalRule approvalRule = new ApprovalRule();
		approvalRule.setActive(true);
		approvalRule.setLevel(level);
		approvalRule.setCount(approvalCount);
		approvalRule.setRules(approvalRules);
		
		dataStore.save(approvalRule);
		
		return approvalRule;
	}
	
	@Override
	public Approver createApprover(String externalId, int level, String groupName) 
			throws NotFoundException, DuplicateApproverIdentityException, InvalidApprovalGroupException {
		checkNullStrings(externalId, groupName, EXTERNAL_ID, GROUP_NAME_PARAMETER);

		DataStore dataStore = factory.getDataStore();
		
		Optional<Approver> optionalApprover = dataStore
				.find(Approver.class)
				.filterBy(EXTERNAL_ID).withAValueEqualTo(externalId)
				.filterBy(GROUP_NAME).withAValueEqualTo(groupName)
				.thenGetFirst();

		if(optionalApprover.isPresent())
			throw new DuplicateApproverIdentityException();
		
		Approver approver = new Approver();
		
		approver.setGroup(findGroupByName(groupName, dataStore));
		approver.setExternalId(externalId);
		approver.setLevel(level);
		approver.setValid(true);
		
		dataStore.save(approver);
		return approver;
	}

	private ApprovalGroup findGroupByName(String groupName, DataStore dataStore) throws NotFoundException, InvalidApprovalGroupException {
		Optional<ApprovalGroup> optionalApprovalGroup = 
				dataStore.find(ApprovalGroup.class)
				.filterBy(NAME).withAValueEqualTo(groupName)
				.thenGetFirst();
		
		if(!optionalApprovalGroup.isPresent())
			throw new NotFoundException("The ApprovalGroup with groupName {0} cannot be found",
					groupName);

		if(!optionalApprovalGroup.get().isValid())
			throw new InvalidApprovalGroupException();
		
		return optionalApprovalGroup.get();
	}

	@Override
	public Optional<ApprovalProcess> findApprovalProcess(long id, String groupName) {
		checkSingleString(groupName, GROUP_NAME_PARAMETER);
		return factory.getDataStore()
				.find(ApprovalProcess.class)
				.filterBy("id").withAValueEqualTo(id)
				.filterBy(GROUP_NAME).withAValueEqualTo(groupName)
				.thenGetFirst();
	}
	
	@Override
	public Optional<ApprovalGroup> findApprovalGroupByName(String name) {
		checkSingleString(name,NAME);
		return factory.getDataStore()
				.find(ApprovalGroup.class)
				.filterBy(NAME).withAValueEqualTo(name)
				.thenGetFirst();
	}

	private void checkSingleString(String string, String stringName) {
		if(string == null) {
			throw new MissingFieldException(FIELD_REQUIRED, stringName);
		}
	}

	@Override
	public Optional<ApprovalRules> findApprovalRulesByName(String groupName, String rulesName) {
		checkNullStrings(rulesName, groupName, RULES_NAME, GROUP_NAME_PARAMETER);
		return factory.getDataStore()
				.find(ApprovalRules.class)
				.filterBy(GROUP_NAME).withAValueEqualTo(groupName)
				.filterBy(NAME).withAValueEqualTo(rulesName)
				.thenGetFirst();
	}

	@Override
	public List<ApprovalRules> findApprovalRulesByPriority(String groupName, int priority) {
		checkSingleString(groupName, GROUP_NAME_PARAMETER);
		return factory.getDataStore().find(ApprovalRules.class)
				.filterBy(GROUP_NAME).withAValueEqualTo(groupName)
				.filterBy("priority").withAValueEqualTo(priority)
				.thenList();
	}
	
	@Override
	public List<ApprovalRule> findRulesByRuleName(String rulesName, String groupName) {
		checkNullStrings(groupName, rulesName, GROUP_NAME_PARAMETER, RULES_NAME);
		return factory.getDataStore().find(ApprovalRule.class)
				.filterBy("rules.group.name").withAValueEqualTo(groupName)
				.filterBy("rules.name").withAValueEqualTo(rulesName)
				.thenList();
	}
	
	@Override
	public Optional<Approver> findApproverByIdentity(String groupName, String externalId) {
		checkNullStrings(externalId, groupName, EXTERNAL_ID, GROUP_NAME_PARAMETER);
		return factory.getDataStore()
				.find(Approver.class)
				.filterBy(GROUP_NAME).withAValueEqualTo(groupName)
				.filterBy(EXTERNAL_ID).withAValueEqualTo(externalId)
				.thenGetFirst();
	}

	@Override
	public List<Approver> findApproversByLevel(String groupName, int level) {
		checkSingleString(groupName, GROUP_NAME_PARAMETER);
		return factory.getDataStore()
				.find(Approver.class)
				.filterBy(GROUP_NAME).withAValueEqualTo(groupName)
				.filterBy("level").withAValueEqualTo(level)
				.thenList();
	}

	@Override
	public List<Approver> findApproversByExternalId(String externalId) {
		checkSingleString(externalId, EXTERNAL_ID);
		return factory.getDataStore().find(Approver.class)
				.filterBy(EXTERNAL_ID).withAValueEqualTo(externalId)
				.thenList();
	}
	
	@Override
	public void activateApprovalRules(String rulesName, String groupName) 
			throws NotFoundException, InvalidApprovalGroupException {
		updateApprovalRuleValidity(rulesName, groupName, true);
	}

	@Override
	public void deactivateApprovalRules(String rulesName, String groupName) 
			throws NotFoundException, InvalidApprovalGroupException {
		updateApprovalRuleValidity(rulesName, groupName, false);
	}

	private void updateApprovalRuleValidity(String rulesName, String groupName, boolean activate)
			throws NotFoundException, InvalidApprovalGroupException {
		checkNullStrings(rulesName, groupName, RULES_NAME, GROUP_NAME_PARAMETER);
		DataStore dataStore = factory.getDataStore();
		ApprovalRules approvalRules = findApprovalRules(rulesName, groupName, dataStore);
		approvalRules.setActive(activate);
		dataStore.update(approvalRules);
	}

	private ApprovalRules findApprovalRules(String rulesName, String groupName, DataStore dataStore)
			throws NotFoundException, InvalidApprovalGroupException {
		Optional<ApprovalRules> optionalApprovalRules = dataStore.find(ApprovalRules.class)
				.filterBy(NAME).withAValueEqualTo(rulesName)
				.filterBy(GROUP_NAME).withAValueEqualTo(groupName)
				.thenGetFirst();
		
		if(!optionalApprovalRules.isPresent())
			throw new NotFoundException("The ApprovalRules with name {0} "
					+ "belonging to ApprovalGroup {1} cannot be found",
					rulesName, groupName);
		
		ApprovalRules approvalRules = optionalApprovalRules.get();
		
		if(!approvalRules.getGroup().isValid())
			throw new InvalidApprovalGroupException();
		
		return approvalRules;
	}

	@Override
	public void activateApprovalRule(int level, String rulesName, String groupName) 
			throws NotFoundException, InvalidApprovalRulesException, InvalidApprovalGroupException {
		updateApprovalRuleValidity(level, rulesName, groupName, true);
	}
	
	@Override
	public void deactivateApprovalRule(int level, String rulesName, String groupName) 
			throws NotFoundException, InvalidApprovalRulesException, InvalidApprovalGroupException {
		updateApprovalRuleValidity(level, rulesName, groupName, false);
	}

	private void updateApprovalRuleValidity(int level, String rulesName, String groupName, boolean activated)
			throws NotFoundException, InvalidApprovalRulesException, InvalidApprovalGroupException {
		checkNullStrings(rulesName, groupName, RULES_NAME, GROUP_NAME_PARAMETER);
		DataStore dataStore = factory.getDataStore();
		ApprovalRule approvalRule = findApprovalRule(level, rulesName, groupName, dataStore);
		approvalRule.setActive(activated);
		dataStore.save(approvalRule);
	}

	private ApprovalRule findApprovalRule(int level, String rulesName, String groupName, DataStore dataStore)
			throws NotFoundException, InvalidApprovalRulesException, InvalidApprovalGroupException {
		Optional<ApprovalRule> optionalApprovalRule = dataStore.find(ApprovalRule.class)
				.filterBy("level").withAValueEqualTo(level)
				.filterBy("rules.name").withAValueEqualTo(rulesName)
				.filterBy("rules.group.name").withAValueEqualTo(groupName)
				.thenGetFirst();
		
		if(!optionalApprovalRule.isPresent())
			throw new NotFoundException("The ApprovalRule with level {0} of ApprovalRules named {1} "
					+ "belonging to ApprovalGroup {2} cannot be found",
					level, rulesName, groupName);
		
		ApprovalRule approvalRule = optionalApprovalRule.get();
		
		if(!approvalRule.getRules().isActive())
			throw new InvalidApprovalRulesException("The provided rulesName {0} is not valid", rulesName);
		
		if(!approvalRule.getRules().getGroup().isValid())
			throw new InvalidApprovalGroupException();

		return approvalRule;
	}
	
	private void checkNullStrings(String string1, String string2, String string1Name, 
			String string2Name) {
		if(string1 == null || string2 == null){
			throw new MissingFieldException(FIELD_REQUIRED, 
					string1 == null?string1:string2);
		}
	}
}

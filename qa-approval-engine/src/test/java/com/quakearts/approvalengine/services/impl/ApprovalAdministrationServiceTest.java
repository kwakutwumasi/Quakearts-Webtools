package com.quakearts.approvalengine.services.impl;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import com.quakearts.appbase.cdi.annotation.Transactional;
import com.quakearts.appbase.cdi.annotation.Transactional.TransactionType;
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
import com.quakearts.approvalengine.utils.AEUtils;
import com.quakearts.webapp.orm.DataStore;
import com.quakearts.webapp.orm.cdi.annotation.DataStoreHandle;
import com.quakearts.webapp.orm.cdi.annotation.RequiresTransaction;
import com.quakearts.webtools.test.AllServicesRunner;

@RunWith(AllServicesRunner.class)
public class ApprovalAdministrationServiceTest {

	@Inject
	private ApprovalAdministrationService approvalAdministrationService;

	@Inject
	@DataStoreHandle
	@RequiresTransaction
	private DataStore dataStore;

	@Inject
	private DataCreator dataCreator;

	@Before
	public void createData() {
		dataCreator.createTestData();
	}

	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testCreateApprovalGroup() throws Exception {
		ApprovalGroup group = approvalAdministrationService.createApprovalGroup("testCreateApprovalGroup");
		dataStore.flushBuffers();

		assertThat(group.getName(), is("testCreateApprovalGroup"));
		assertThat(group.getId(), is(notNullValue()));
		assertThat(group.isValid(), is(true));
	}

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testCreateApprovalGroupWithNullName() throws Exception {
		expectedException.expect(MissingFieldException.class);
		approvalAdministrationService.createApprovalGroup(null);
	}

	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testCreateApprovalGroupWithDuplicateName() throws Exception {
		expectedException.expect(DuplicateGroupNameException.class);
		approvalAdministrationService.createApprovalGroup("testGroup1");
		dataStore.flushBuffers();
	}

	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testCreateApprovalRules() throws Exception {
		ApprovalRules approvalRules = approvalAdministrationService
				.createApprovalRules("testCreateApprovalRule1", "testGroup1")
				.setPriorityAs(1).addRule(1, 2).addRule(2, 3).thenBuild();
		dataStore.flushBuffers();

		assertTrue(approvalRules.getId() > 0);
		assertThat(approvalRules.getName(), is("testCreateApprovalRule1"));
		assertThat(approvalRules.getGroup(), is(notNullValue()));
		assertThat(approvalRules.getGroup().getId(), is(dataCreator.getApprovalGroup1().getId()));
		assertThat(approvalRules.getPriority(), is(1));
		assertThat(approvalRules.getRuleElements(), is(notNullValue()));
		assertThat(approvalRules.getRuleElements().size(), is(2));
		assertTrue(approvalRules.isActive());

		Iterator<ApprovalRule> rulesIterator = approvalRules.getRuleElements().iterator();

		while (rulesIterator.hasNext()) {
			ApprovalRule approvalRule = rulesIterator.next();
			assertTrue(approvalRule.getId() > 0 && approvalRule.getRules() != null
					&& approvalRule.getRules().getId() == approvalRules.getId()
					&& approvalRule.isActive() && ((approvalRule.getCount() == 3 && approvalRule.getLevel() == 2)
							|| (approvalRule.getCount() == 2 && approvalRule.getLevel() == 1)));
		}
		
		approvalRules = approvalAdministrationService
				.createApprovalRules("testCreateApprovalRule2", "testGroup1")
				.addRule(1, 3).thenBuild();
		dataStore.flushBuffers();

		assertTrue(approvalRules.getId() > 0);
		assertThat(approvalRules.getName(), is("testCreateApprovalRule2"));
		assertThat(approvalRules.getGroup(), is(notNullValue()));
		assertThat(approvalRules.getGroup().getId(), is(dataCreator.getApprovalGroup1().getId()));
		assertThat(approvalRules.getPriority(), is(1));
		assertThat(approvalRules.getRuleElements(), is(notNullValue()));
		assertThat(approvalRules.getRuleElements().size(), is(1));
		assertTrue(approvalRules.isActive());

		rulesIterator = approvalRules.getRuleElements().iterator();

		ApprovalRule approvalRule = rulesIterator.next();
		assertTrue(approvalRule.getId() > 0 && approvalRule.getRules() != null
				&& approvalRule.getRules().getId() == approvalRules.getId()
				&& approvalRule.isActive()
				&& approvalRule.getCount() == 3 && approvalRule.getLevel() == 1);
		
		approvalRules = approvalAdministrationService
				.createApprovalRules("testCreateApprovalRule3", "testGroup1")
				.setPriorityAs(2).addRule(2, 4).thenBuild();
		dataStore.flushBuffers();

		assertTrue(approvalRules.getId() > 0);
		assertThat(approvalRules.getName(), is("testCreateApprovalRule3"));
		assertThat(approvalRules.getGroup(), is(notNullValue()));
		assertThat(approvalRules.getGroup().getId(), is(dataCreator.getApprovalGroup1().getId()));
		assertThat(approvalRules.getPriority(), is(2));
		assertThat(approvalRules.getRuleElements(), is(notNullValue()));
		assertThat(approvalRules.getRuleElements().size(), is(1));
		assertTrue(approvalRules.isActive());

		rulesIterator = approvalRules.getRuleElements().iterator();

		approvalRule = rulesIterator.next();
		assertTrue(approvalRule.getId() > 0 && approvalRule.getRules() != null
				&& approvalRule.getRules().getId() == approvalRules.getId()
				&& approvalRule.isActive()
				&& approvalRule.getCount() == 4 && approvalRule.getLevel() == 2);
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testCreateApprovalRulesWithNoRules() throws Exception {
		ApprovalRules approvalRules = approvalAdministrationService
			.createApprovalRules("testCreateApprovalRuleNoRuleElements", "testGroup1")
			.setPriorityAs(5).thenBuild();
		
		dataStore.flushBuffers();

		assertTrue(approvalRules.getId() > 0);
		assertThat(approvalRules.getName(), is("testCreateApprovalRuleNoRuleElements"));
		assertThat(approvalRules.getGroup(), is(notNullValue()));
		assertThat(approvalRules.getGroup().getId(), is(dataCreator.getApprovalGroup1().getId()));
		assertThat(approvalRules.getPriority(), is(5));
		assertThat(approvalRules.getRuleElements(), is(notNullValue()));
		assertThat(approvalRules.getRuleElements().isEmpty(), is(true));
		assertTrue(approvalRules.isActive());
	}

	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testCreateApprovalRulesWithDuplicateRule() throws Exception {
		expectedException.expect(DuplicateLevelException.class);
		approvalAdministrationService
			.createApprovalRules("testCreateApprovalRule", "testGroup1")
			.setPriorityAs(1).addRule(1, 2).addRule(1, 3).thenBuild();
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testCreateApprovalRulesWithMissingRuleName() throws Exception {
		expectedException.expect(MissingFieldException.class);
		approvalAdministrationService
			.createApprovalRules(null, "testGroup1")
			.setPriorityAs(1).addRule(1, 2).addRule(1, 3).thenBuild();
	}


	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testCreateApprovalRulesWithDuplicateRuleName() throws Exception {
		expectedException.expect(DuplicateRuleException.class);
		approvalAdministrationService
			.createApprovalRules("testApprovalRules1", 
					"testGroup1")
			.setPriorityAs(1).addRule(1, 2).addRule(2, 3).thenBuild();
	}

	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testCreateApprovalRulesWithInvalidCount() throws Exception {
		expectedException.expect(InvalidCountException.class);
		approvalAdministrationService
			.createApprovalRules("testCreateApprovalRule", 
					"testGroup1")
			.setPriorityAs(1).addRule(1, 2).addRule(2, -3).thenBuild();
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testCreateApprovalRulesWithMissingGroupName() throws Exception {
		expectedException.expect(MissingFieldException.class);
		approvalAdministrationService
			.createApprovalRules("testCreateApprovalRule", null)
			.setPriorityAs(1).addRule(1, 2).addRule(2, 3).thenBuild();
	}

	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testCreateApprovalRulesWithNonExistentGroupName() throws Exception {
		expectedException.expect(NotFoundException.class);
		approvalAdministrationService
			.createApprovalRules("testCreateApprovalRule", "invalidGroupName")
			.setPriorityAs(1).addRule(1, 2).addRule(2, 3).thenBuild();
	}

	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testCreateApprovalRulesWithInvalidGroupName() throws Exception {
		expectedException.expect(InvalidApprovalGroupException.class);
		approvalAdministrationService
			.createApprovalRules("testCreateWithInvalidRule", "testGroup3")
			.addRule(1, 3).thenBuild();
	}

	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testCreateApprover() throws Exception {
		Approver approver = approvalAdministrationService.createApprover("testCreateApprover", 1,
				"testGroup1");

		assertThat(approver.getGroup(), is(notNullValue()));
		assertThat(approver.getGroup().getId(), is(dataCreator.getApprovalGroup1().getId()));
		assertTrue(approver.getId() > 0);
		assertThat(approver.getExternalId(), is("testCreateApprover"));
		assertThat(approver.getLevel(), is(1));
		assertTrue(approver.isValid());
	}

	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testCreateApproverWithDuplicateName() throws Exception {
		expectedException.expect(DuplicateApproverIdentityException.class);
		approvalAdministrationService.createApprover("testApprover1", 1,
				"testGroup1");
	}

	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testCreateApproverWithMissingGroupName() throws Exception {
		expectedException.expect(MissingFieldException.class);
		approvalAdministrationService.createApprover("testCreateApproverMissingGroupName", 1, null);
		dataStore.flushBuffers();
	}

	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testCreateApproverWithMissingIdentityName() throws Exception {
		expectedException.expect(MissingFieldException.class);
		approvalAdministrationService.createApprover(null, 1, 
				"testGroup1");
		dataStore.flushBuffers();
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testCreateApproverWithNonExistentGroupName() throws Exception {
		expectedException.expect(NotFoundException.class);
		approvalAdministrationService.createApprover("testCreateApproverNotFound", 1, "invalidGroupName");
	}

	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testCreateApproverWithInvalidGroupName() throws Exception {
		expectedException.expect(InvalidApprovalGroupException.class);
		approvalAdministrationService.createApprover("testCreateApproverInvalidGroupName", 1, "testGroup3");
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testCreateApprovalRule() throws Exception {
		ApprovalRule approvalRule = approvalAdministrationService
				.createApprovalRule(1, 2, "testApprovalRules16", "testGroup2");
		assertTrue(approvalRule.getId() > 0); 
		assertTrue(approvalRule.getRules() != null);
		assertThat(approvalRule.getRules().getId(), is(dataCreator.getApprovalRules16().getId()));
		assertTrue(approvalRule.isActive());
		assertThat(approvalRule.getCount(), is(2));
		assertThat(approvalRule.getLevel(), is(1));
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testCreateApprovalRuleWithInvalidApprovalCount() throws Exception {
		expectedException.expect(InvalidCountException.class);
		approvalAdministrationService.createApprovalRule(3, -3, "testApprovalRules16", "testGroup2");
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testCreateApprovalRuleWithDuplicateApprovalRule() throws Exception {
		expectedException.expect(DuplicateLevelException.class);
		approvalAdministrationService.createApprovalRule(2, 1, "testApprovalRules6", "testGroup2");
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testCreateApprovalRuleWithMissingApprovalRulesName() throws Exception {
		expectedException.expect(MissingFieldException.class);
		approvalAdministrationService.createApprovalRule(3, 2, null, "testGroup2");
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testCreateApprovalRuleWithNonExistentApprovalRulesName() throws Exception {
		expectedException.expect(NotFoundException.class);
		approvalAdministrationService.createApprovalRule(3, 2, "testInvalidApprovalRules", "testGroup2");
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testCreateApprovalRuleWithMissingApprovalGroupName() throws Exception {
		expectedException.expect(MissingFieldException.class);
		approvalAdministrationService.createApprovalRule(3, 2, "testApprovalRules16", null);
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testCreateApprovalRuleWithNonExistentApprovalGroupName() throws Exception {
		expectedException.expect(NotFoundException.class);
		approvalAdministrationService.createApprovalRule(3, 2, "testApprovalRules16", "testInvalidGroup");
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testCreateApprovalRuleWithInvalidApprovalGroupName() throws Exception {
		expectedException.expect(InvalidApprovalGroupException.class);
		approvalAdministrationService.createApprovalRule(3, 2, "testApprovalRules15", "testGroup3");
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testCreateApprovalRuleWithInvalidApprovalRulesName() throws Exception {
		expectedException.expect(InvalidApprovalRulesException.class);
		approvalAdministrationService.createApprovalRule(3, 2, "testApprovalRules13", "testGroup2");
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testFindApprovalProcess() throws Exception {
		Optional<ApprovalProcess> optionalApprovalProcess = approvalAdministrationService
				.findApprovalProcess(dataCreator.getApprovalProcess1().getId(), 
						"testGroup1");
		
		assertTrue(optionalApprovalProcess.isPresent());

		optionalApprovalProcess = approvalAdministrationService
				.findApprovalProcess(-1, "testGroup1");

		assertTrue(!optionalApprovalProcess.isPresent());

		optionalApprovalProcess = approvalAdministrationService
				.findApprovalProcess(dataCreator.getApprovalProcess1().getId(), 
						"testInvalidGroup");

		assertTrue(!optionalApprovalProcess.isPresent());
		
		optionalApprovalProcess = approvalAdministrationService
				.findApprovalProcess(-1, "testInvalidGroup");

		assertTrue(!optionalApprovalProcess.isPresent());
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testFindApprovalProcessWithMissingGroupName() throws Exception {
		expectedException.expect(MissingFieldException.class);
		approvalAdministrationService.findApprovalProcess(1, null);
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testFindApprovalGroupByName() throws Exception {
		Optional<ApprovalGroup> optionalApprovalGroup = approvalAdministrationService
				.findApprovalGroupByName("testGroup1");

		assertTrue(optionalApprovalGroup.isPresent());
		ApprovalGroup group = optionalApprovalGroup.get();
		assertThat(group.getId(), is(dataCreator.getApprovalGroup1().getId()));

		optionalApprovalGroup = approvalAdministrationService.findApprovalGroupByName("nonExistentGroupName");

		assertTrue(!optionalApprovalGroup.isPresent());
	}

	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testFindApprovalGroupByNameWithMissingGroupName() throws Exception {
		expectedException.expect(MissingFieldException.class);
		approvalAdministrationService.findApprovalGroupByName(null);
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testFindApprovalRulesByName() throws Exception {
		Optional<ApprovalRules> optionalApprovalRules = approvalAdministrationService.findApprovalRulesByName(
				"testGroup1", "testApprovalRules1");

		assertTrue(optionalApprovalRules.isPresent());
		ApprovalRules approvalRules = optionalApprovalRules.get();
		assertThat(approvalRules.getId(), is(dataCreator.getApprovalRules1().getId()));

		optionalApprovalRules = approvalAdministrationService
				.findApprovalRulesByName("testGroup1", "nonExistentRuleName");

		assertTrue(!optionalApprovalRules.isPresent());

		optionalApprovalRules = approvalAdministrationService.findApprovalRulesByName("nonExistentGroupName",
				"testApprovalRules1");

		assertTrue(!optionalApprovalRules.isPresent());

		optionalApprovalRules = approvalAdministrationService.findApprovalRulesByName("nonExistentGroupName",
				"nonExistentRuleName");

		assertTrue(!optionalApprovalRules.isPresent());
	}

	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testFindApprovalRulesByNameWithMissingGroupName() throws Exception {
		expectedException.expect(MissingFieldException.class);
		approvalAdministrationService.findApprovalRulesByName(null, "testApprovalRules1");
	}

	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testFindApprovalRulesByNameWithMissingRuleName() throws Exception {
		expectedException.expect(MissingFieldException.class);
		approvalAdministrationService.findApprovalRulesByName("testGroup1", null);
	}

	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testFindApproverByIdentity() throws Exception {
		Optional<Approver> optionalApprover = approvalAdministrationService.findApproverByIdentity(
				"testGroup1", "testApprover1");

		assertTrue(optionalApprover.isPresent());
		Approver approver = optionalApprover.get();
		assertThat(approver.getId(), is(dataCreator.getApprover1().getId()));

		optionalApprover = approvalAdministrationService.findApproverByIdentity("nonExistentGroupName",
				"testApprover1");

		assertTrue(!optionalApprover.isPresent());

		optionalApprover = approvalAdministrationService
				.findApproverByIdentity("testGroup1", "nonExistentIdentity");

		assertTrue(!optionalApprover.isPresent());

		optionalApprover = approvalAdministrationService.findApproverByIdentity("nonExistentGroupName",
				"nonExistentIdentity");

		assertTrue(!optionalApprover.isPresent());
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testFindApproverByIdentityWithMissingGroupName() throws Exception {
		expectedException.expect(MissingFieldException.class);
		approvalAdministrationService.findApproverByIdentity("testGroup1", null);
	}

	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testFindApproverByIdentityWithMissingIdentity() throws Exception {
		expectedException.expect(MissingFieldException.class);
		approvalAdministrationService.findApproverByIdentity(null, "testApprover1");
	}

	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testFindApproversByLevel() throws Exception {
		List<Approver> approvers = approvalAdministrationService
				.findApproversByLevel("testGroup1", 2);
		
		assertThat(approvers.size(), is(2));
		for(Approver approver:approvers){
			assertTrue(approver.getId() == dataCreator.getApprover2().getId()
					||approver.getId() == dataCreator.getApprover3().getId());
		}
		
		approvers = approvalAdministrationService
				.findApproversByLevel("nonExistentGroupName", 2);
		assertTrue(approvers.isEmpty());
		
		approvers = approvalAdministrationService
				.findApproversByLevel("testGroup1", -1);

		assertTrue(approvers.isEmpty());

		approvers = approvalAdministrationService
				.findApproversByLevel("nonExistentGroupName", -1);
		assertTrue(approvers.isEmpty());
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testFindApproversByLevelWithMissingGroupName() throws Exception {
		expectedException.expect(MissingFieldException.class);
		approvalAdministrationService.findApproversByLevel(null, 1);
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testFindApproverRulesByPriority() throws Exception {
		List<ApprovalRules> approvalRulesList = approvalAdministrationService
				.findApprovalRulesByPriority("testGroup2", 2);
		
		assertThat(approvalRulesList.size(), is(3));
		Set<String> names = AEUtils.asSet("testApprovalRules11",
			"testApprovalRules12",
			"testApprovalRules13");
		for(ApprovalRules approvalRules:approvalRulesList){
			assertTrue(names.contains(approvalRules.getName()));
		}

		approvalRulesList = approvalAdministrationService
				.findApprovalRulesByPriority("testGroup2", 3);
		
		assertTrue(approvalRulesList.isEmpty());
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testFindApproverRulesByPriorityWithMissingGroupName() throws Exception {
		expectedException.expect(MissingFieldException.class);
		approvalAdministrationService.findApprovalRulesByPriority(null, 1);
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void findApproversByExternalId() throws Exception {
		List<Approver> approvers = approvalAdministrationService
				.findApproversByExternalId("testApprover11");
		
		assertThat(approvers, is(notNullValue()));
		assertThat(approvers.size(), is(3));
		assertThat(approvers.get(0).getExternalId(), is("testApprover11"));
		assertThat(approvers.get(0).getGroup().getId() != approvers.get(1).getGroup().getId(),is(true));
		
		assertThat(approvers.get(1).getExternalId(), is("testApprover11"));
		assertThat(approvers.get(1).getGroup().getId() != approvers.get(2).getGroup().getId(),is(true));
		assertThat(approvers.get(2).getExternalId(), is("testApprover11"));
		assertThat(approvers.get(2).getGroup().getId() != approvers.get(0).getGroup().getId(),is(true));
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void findApproversByExternalIdWithMissingExternalId() throws Exception {
		expectedException.expect(MissingFieldException.class);
		approvalAdministrationService.findApproversByExternalId(null);
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void findRulesByRuleName() throws Exception {
		List<ApprovalRule> approvalRuleElements = approvalAdministrationService
				.findRulesByRuleName("testApprovalRules1", "testGroup1");
		
		assertThat(approvalRuleElements, is(notNullValue()));
		assertThat(approvalRuleElements.size(), is(2));
		
		Iterator<ApprovalRule> rulesIterator = approvalRuleElements.iterator();

		while (rulesIterator.hasNext()) {
			ApprovalRule approvalRule = rulesIterator.next();
			assertTrue(approvalRule.getId() > 0 && approvalRule.getRules() != null
					&& approvalRule.getRules().getId() == dataCreator.getApprovalRules1().getId()
					&& approvalRule.isActive() && ((approvalRule.getCount() == 2 && approvalRule.getLevel() == 2)
							|| (approvalRule.getCount() == 1 && approvalRule.getLevel() == 1)));
		}
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void findRulesByRuleNameWithMissingApprovalRulesName() throws Exception {
		expectedException.expect(MissingFieldException.class);
		approvalAdministrationService.findRulesByRuleName(null, "testGroup1");
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void findRulesByRuleNameWithMissingApprovalGroupName() throws Exception {
		expectedException.expect(MissingFieldException.class);
		approvalAdministrationService.findRulesByRuleName("testApprovalRules1", null);
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testActivateApprovalRulesSuccessful() throws Exception {
		approvalAdministrationService.activateApprovalRules("testApprovalRules7", "testGroup2");
		dataStore.flushBuffers();
		
		ApprovalRules approvalRules = dataStore.get(ApprovalRules.class, 
				dataCreator.getApprovalRules7().getId());
		
		assertTrue(approvalRules.isActive());
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testActivateApprovalRulesWithMissingRuleName() throws Exception {
		expectedException.expect(MissingFieldException.class);
		approvalAdministrationService.activateApprovalRules(null, "testGroup2");
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testActivateApprovalRulesWithNonExistentRuleName() throws Exception {
		expectedException.expect(NotFoundException.class);
		approvalAdministrationService.activateApprovalRules("testInvalidRules", "testGroup2");
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testActivateApprovalRulesWithMissingGroupName() throws Exception {
		expectedException.expect(MissingFieldException.class);
		approvalAdministrationService.activateApprovalRules("testApprovalRules7", null);
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testActivateApprovalRulesWithNonExistentGroupName() throws Exception {
		expectedException.expect(NotFoundException.class);
		approvalAdministrationService.activateApprovalRules("testApprovalRules7", "testInvalidGroup");
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testActivateApprovalRulesWithInvalidGroupName() throws Exception {
		expectedException.expect(InvalidApprovalGroupException.class);
		approvalAdministrationService.activateApprovalRules("testApprovalRules9", "testGroup3");
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testActivateApprovalRulesWithDifferentGroupName() throws Exception {
		expectedException.expect(NotFoundException.class);
		approvalAdministrationService.activateApprovalRules("testApprovalRules10", "testGroup2");
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testDeactivateRuleSuccessful() throws Exception {
		approvalAdministrationService.deactivateApprovalRules("testApprovalRules11", "testGroup2");
		dataStore.flushBuffers();
		
		ApprovalRules approvalRules = dataStore.get(ApprovalRules.class, 
				dataCreator.getApprovalRules11().getId());
		
		assertFalse(approvalRules.isActive());
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testDeactivateApprovalRulesWithMissingRuleName() throws Exception {
		expectedException.expect(MissingFieldException.class);
		approvalAdministrationService.deactivateApprovalRules(null, "testGroup2");
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testDeactivateApprovalRulesWithNonExistentRuleName() throws Exception {
		expectedException.expect(NotFoundException.class);
		approvalAdministrationService.deactivateApprovalRules("testInvalidRules", "testGroup2");
	}
		
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testDeactivateApprovalRulesWithMissingGroupName() throws Exception {
		expectedException.expect(MissingFieldException.class);
		approvalAdministrationService.deactivateApprovalRules("testApprovalRules11", null);
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testDeactivateApprovalRulesWithNonExistentGroupName() throws Exception {
		expectedException.expect(NotFoundException.class);
		approvalAdministrationService.deactivateApprovalRules("testApprovalRules11", "testInvalidGroup");
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testDeactivateApprovalRulesWithInvalidGroupName() throws Exception {
		expectedException.expect(InvalidApprovalGroupException.class);
		approvalAdministrationService.deactivateApprovalRules("testApprovalRules9", "testGroup3");
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testDeactivateApprovalRulesWithDifferentGroupName() throws Exception {
		expectedException.expect(NotFoundException.class);
		approvalAdministrationService.deactivateApprovalRules("testApprovalRules10", "testGroup2");
	}

	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testActivateApprovalRuleElementSuccessful() throws Exception {
		approvalAdministrationService.activateApprovalRule(1, "testApprovalRules12", "testGroup2");
		dataStore.flushBuffers();
		
		ApprovalRule approvalRule = dataStore.get(ApprovalRule.class, 
				dataCreator.getApprovalRule3().getId());
		
		assertTrue(approvalRule.isActive());
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testActivateRuleElementWithNonExistentLevel() throws Exception {
		expectedException.expect(NotFoundException.class);
		approvalAdministrationService.activateApprovalRule(3, "testApprovalRules12", "testGroup2");
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testActivateRuleElementWithMissingApprovalRulesName() throws Exception {
		expectedException.expect(MissingFieldException.class);
		approvalAdministrationService.activateApprovalRule(1, null, "testGroup2");
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testActivateRuleElementWithNonExistentRulesName() throws Exception {
		expectedException.expect(NotFoundException.class);
		approvalAdministrationService.activateApprovalRule(1, "testInvalidRuleName", "testGroup2");
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testActivateRuleElementWithMissingGroupName() throws Exception {
		expectedException.expect(MissingFieldException.class);
		approvalAdministrationService.activateApprovalRule(1, "testApprovalRules12", null);
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testActivateRuleElementWithNonExistentGroupName() throws Exception {
		expectedException.expect(NotFoundException.class);
		approvalAdministrationService.activateApprovalRule(1, "testApprovalRules12", "testInvalidGroup");
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testActivateRuleElementWithInvalidGroupName() throws Exception {
		expectedException.expect(InvalidApprovalGroupException.class);
		approvalAdministrationService.activateApprovalRule(2, "testApprovalRules9", "testGroup3");
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testActivateRuleElementWithDifferentGroupName() throws Exception {
		expectedException.expect(NotFoundException.class);
		approvalAdministrationService.activateApprovalRule(1, "testApprovalRules9", "testGroup2");
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testActivateRuleElementWithInvalidApprovalRulesName() throws Exception {
		expectedException.expect(InvalidApprovalRulesException.class);
		approvalAdministrationService.activateApprovalRule(1, "testApprovalRules13", "testGroup2");
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testDeactivateRuleElementSuccessful() throws Exception {
		approvalAdministrationService.deactivateApprovalRule(2, "testApprovalRules12", "testGroup2");
		dataStore.flushBuffers();
		
		ApprovalRule approvalRule = dataStore.get(ApprovalRule.class, 
				dataCreator.getApprovalRule4().getId());
		
		assertFalse(approvalRule.isActive());
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testDeactivateRuleElementWithInvalidLevel() throws Exception {
		expectedException.expect(NotFoundException.class);
		approvalAdministrationService.deactivateApprovalRule(3, "testApprovalRules12", "testGroup2");
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testDeactivateRuleElementWithMissingRuleName() throws Exception {
		expectedException.expect(MissingFieldException.class);
		approvalAdministrationService.deactivateApprovalRule(2, null, "testGroup2");
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testDeactivateRuleElementWithNonExistentRuleName() throws Exception {
		expectedException.expect(NotFoundException.class);
		approvalAdministrationService.deactivateApprovalRule(2, "testInvalidApprovalRules", "testGroup2");
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testDeactivateRuleElementWithMissingGroupName() throws Exception {
		expectedException.expect(MissingFieldException.class);
		approvalAdministrationService.deactivateApprovalRule(2, "testApprovalRules12", null);
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testDeactivateRuleElementWithNonExistentGroupName() throws Exception {
		expectedException.expect(NotFoundException.class);
		approvalAdministrationService.deactivateApprovalRule(2, "testApprovalRules12", "testInvalidGroup");
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testDeactivateRuleElementWithInvalidGroupName() throws Exception {
		expectedException.expect(InvalidApprovalGroupException.class);
		approvalAdministrationService.deactivateApprovalRule(2, "testApprovalRules9", "testGroup3");
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testDeactivateRuleElementWithDifferentGroupName() throws Exception {
		expectedException.expect(NotFoundException.class);
		approvalAdministrationService.deactivateApprovalRule(2, "testApprovalRules9", "testGroup2");
	}
	
	@Test
	@Transactional(TransactionType.SINGLETON)
	public void testDeactivateRuleElementWithInvalidApprovalRulesName() throws Exception {
		expectedException.expect(InvalidApprovalRulesException.class);
		approvalAdministrationService.deactivateApprovalRule(2, "testApprovalRules13", "testGroup2");
	}

}

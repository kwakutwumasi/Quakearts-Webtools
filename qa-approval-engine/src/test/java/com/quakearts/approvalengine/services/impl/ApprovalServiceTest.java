package com.quakearts.approvalengine.services.impl;

import static org.junit.Assert.*;
import static org.awaitility.Awaitility.*;
import static com.quakearts.approvalengine.utils.AEUtils.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;

import javax.inject.Inject;
import javax.transaction.UserTransaction;

import org.awaitility.Duration;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import com.quakearts.appbase.cdi.annotation.Transactional;
import com.quakearts.appbase.cdi.annotation.Transactional.TransactionType;
import com.quakearts.appbase.spi.factory.JavaTransactionManagerSpiFactory;
import com.quakearts.approvalengine.context.ApprovalContext;
import com.quakearts.approvalengine.context.ApprovalContextBuilderFactory;
import com.quakearts.approvalengine.exception.ApprovalCompleteException;
import com.quakearts.approvalengine.exception.DuplicateRuleException;
import com.quakearts.approvalengine.exception.InvalidApprovalException;
import com.quakearts.approvalengine.exception.InvalidApprovalGroupException;
import com.quakearts.approvalengine.exception.InvalidApprovalProcessException;
import com.quakearts.approvalengine.exception.InvalidApprovalRulesException;
import com.quakearts.approvalengine.exception.InvalidApproverException;
import com.quakearts.approvalengine.exception.MissingFieldException;
import com.quakearts.approvalengine.exception.NotFoundException;
import com.quakearts.approvalengine.model.ApprovalProcess;
import com.quakearts.approvalengine.model.ApprovalProcessRules;
import com.quakearts.approvalengine.model.Approval.ApprovalAction;
import com.quakearts.approvalengine.services.ApprovalService;
import com.quakearts.webapp.orm.DataStore;
import com.quakearts.webapp.orm.cdi.annotation.DataStoreHandle;
import com.quakearts.webapp.orm.cdi.annotation.RequiresTransaction;
import com.quakearts.webtools.test.AllServicesRunner;

@RunWith(AllServicesRunner.class)
public class ApprovalServiceTest {
	
	@Inject
	private DataCreator dataCreator;

	@Inject
	private ApprovalService approvalService;
	
	@Inject @DataStoreHandle @RequiresTransaction
	private DataStore dataStore;
	
	@Inject
	private ApprovalContextBuilderFactory contextBuilder;
	
	@Before
	public void createTestData(){
		dataCreator.createTestData();
	}
	
	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithApprovalGroupNameAndApprovalRuleName() throws Exception {
		ApprovalProcess approvalProcess = approvalService
				.initiateApproval("testGroup1", "testApprovalRules1");
		
		assertTrue(approvalProcess.getId()>0);
		assertThat(approvalProcess.getApprovalProcessRulesSet(), is(notNullValue()));
		assertThat(approvalProcess.getApprovalProcessRulesSet().size(), is(1));
		assertThat(approvalProcess.getGroup().getId(), is(dataCreator.getApprovalGroup1().getId()));
		assertThat(approvalProcess.getStartDate(), is(notNullValue()));
		assertThat(approvalProcess.getCompleteDate(), is(nullValue()));
		assertThat(approvalProcess.isValid(), is(true));
		assertThat(approvalProcess.isComplete(), is(false));
		assertThat(approvalProcess.isApproved(), is(false));
		assertThat(approvalProcess.hasBeenApproved(), is(false));
		assertThat(approvalProcess.hasBeenRejected(), is(false));
		ApprovalProcessRules approvalProcessRules = approvalProcess.getApprovalProcessRulesSet().iterator().next();
		assertThat(approvalProcessRules.getApprovalProcess(), is(notNullValue()));
		assertThat(approvalProcessRules.getApprovalProcess().getId(), is(approvalProcess.getId()));
		assertThat(approvalProcessRules.getApprovalRules(), is(notNullValue()));
		assertThat(approvalProcessRules.getApprovalRules().getName(), is("testApprovalRules1"));
		assertTrue(approvalProcessRules.isActive());
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithASetOfApprovalRules() throws Exception {
		Set<String> approvalRulesSet = new HashSet<>();
		approvalRulesSet.add("testApprovalRules1");
		ApprovalProcess approvalProcess = approvalService.initiateApproval("testGroup1", approvalRulesSet);

		assertThat(approvalProcess.getApprovalProcessRulesSet(), is(notNullValue()));
		assertThat(approvalProcess.getApprovalProcessRulesSet().size(), is(1));
		assertThat(approvalProcess.getGroup().getId(), is(dataCreator.getApprovalGroup1().getId()));
		assertThat(approvalProcess.getStartDate(), is(notNullValue()));
		assertThat(approvalProcess.getCompleteDate(), is(nullValue()));
		assertThat(approvalProcess.isValid(), is(true));
		assertThat(approvalProcess.isComplete(), is(false));
		assertThat(approvalProcess.isApproved(), is(false));
		assertThat(approvalProcess.hasBeenApproved(), is(false));
		assertThat(approvalProcess.hasBeenRejected(), is(false));
		ApprovalProcessRules approvalProcessRules = approvalProcess.getApprovalProcessRulesSet().iterator().next();
		assertThat(approvalProcessRules.getApprovalProcess(), is(notNullValue()));
		assertThat(approvalProcessRules.getApprovalProcess().getId(), is(approvalProcess.getId()));
		assertThat(approvalProcessRules.getApprovalRules(), is(notNullValue()));
		assertThat(approvalProcessRules.getApprovalRules().getName(), is("testApprovalRules1"));
		assertTrue(approvalProcessRules.isActive());

		approvalRulesSet.add("testApprovalRules2");
		approvalProcess = approvalService.initiateApproval("testGroup1", approvalRulesSet);

		assertThat(approvalProcess.getApprovalProcessRulesSet(), is(notNullValue()));
		assertThat(approvalProcess.getApprovalProcessRulesSet().size(), is(2));
		assertThat(approvalProcess.getGroup().getId(), is(dataCreator.getApprovalGroup1().getId()));
		assertThat(approvalProcess.getStartDate(), is(notNullValue()));
		assertThat(approvalProcess.getCompleteDate(), is(nullValue()));
		assertThat(approvalProcess.isValid(), is(true));
		assertThat(approvalProcess.isComplete(), is(false));
		assertThat(approvalProcess.isApproved(), is(false));
		Iterator<ApprovalProcessRules> iterator = approvalProcess.getApprovalProcessRulesSet().iterator();
		while (iterator.hasNext()) {
			approvalProcessRules = iterator.next();
			assertThat(approvalProcessRules.getApprovalProcess(), is(notNullValue()));
			assertThat(approvalProcessRules.getApprovalProcess().getId(), is(approvalProcess.getId()));
			assertThat(approvalProcessRules.getApprovalRules(), is(notNullValue()));
			assertTrue(approvalProcessRules.getApprovalRules().getName().equals("testApprovalRules1")
					||approvalProcessRules.getApprovalRules().getName().equals("testApprovalRules2"));
			assertTrue(approvalProcessRules.isActive());
		}
	}

	@Test @Transactional(TransactionType.SINGLETON) 
	public void testInitiateApprovalWithASetOfActiveApprovalRulesAndSetOfInactiveApprovalRules() throws Exception {
		Set<String> activeApprovalRulesSet = new HashSet<>();
		Set<String> inactiveApprovalRulesSet = new HashSet<>();
		activeApprovalRulesSet.add("testApprovalRules1");
		inactiveApprovalRulesSet.add("testApprovalRules3");
		ApprovalProcess approvalProcess = approvalService.initiateApproval("testGroup1", activeApprovalRulesSet,
				inactiveApprovalRulesSet);

		assertThat(approvalProcess.getApprovalProcessRulesSet(), is(notNullValue()));
		assertThat(approvalProcess.getApprovalProcessRulesSet().size(), is(2));
		assertThat(approvalProcess.getGroup().getId(), is(dataCreator.getApprovalGroup1().getId()));
		assertThat(approvalProcess.getStartDate(), is(notNullValue()));
		assertThat(approvalProcess.getCompleteDate(), is(nullValue()));
		assertThat(approvalProcess.isValid(), is(true));
		assertThat(approvalProcess.isComplete(), is(false));
		assertThat(approvalProcess.isApproved(), is(false));
		assertThat(approvalProcess.hasBeenApproved(), is(false));
		assertThat(approvalProcess.hasBeenRejected(), is(false));
		ApprovalProcessRules approvalProcessRules;
		Iterator<ApprovalProcessRules> iterator = approvalProcess.getApprovalProcessRulesSet().iterator();
		while (iterator.hasNext()) {
			approvalProcessRules = iterator.next();
			assertThat(approvalProcessRules.getApprovalProcess(), is(notNullValue()));
			assertThat(approvalProcessRules.getApprovalProcess().getId(), is(approvalProcess.getId()));
			assertThat(approvalProcessRules.getApprovalRules(), is(notNullValue()));
			assertTrue((approvalProcessRules.getApprovalRules().getName().equals("testApprovalRules1")
					&& approvalProcessRules.isActive())
					|| (approvalProcessRules.getApprovalRules().getName().equals("testApprovalRules3")
						&& !approvalProcessRules.isActive()));
		}
		
		activeApprovalRulesSet.add("testApprovalRules2");
		inactiveApprovalRulesSet.add("testApprovalRules4");
		approvalProcess = approvalService.initiateApproval("testGroup1", activeApprovalRulesSet);

		assertThat(approvalProcess.getApprovalProcessRulesSet(), is(notNullValue()));
		assertThat(approvalProcess.getApprovalProcessRulesSet().size(), is(2));
		assertThat(approvalProcess.getGroup().getId(), is(dataCreator.getApprovalGroup1().getId()));
		assertThat(approvalProcess.getStartDate(), is(notNullValue()));
		assertThat(approvalProcess.getCompleteDate(), is(nullValue()));
		assertThat(approvalProcess.isValid(), is(true));
		assertThat(approvalProcess.isComplete(), is(false));
		assertThat(approvalProcess.isApproved(), is(false));
		assertThat(approvalProcess.hasBeenApproved(), is(false));
		assertThat(approvalProcess.hasBeenRejected(), is(false));
		iterator = approvalProcess.getApprovalProcessRulesSet().iterator();
		while (iterator.hasNext()) {
			approvalProcessRules = iterator.next();
			assertThat(approvalProcessRules.getApprovalProcess(), is(notNullValue()));
			assertThat(approvalProcessRules.getApprovalProcess().getId(), is(approvalProcess.getId()));
			assertThat(approvalProcessRules.getApprovalRules(), is(notNullValue()));
			assertTrue((approvalProcessRules.getApprovalRules().getName().equals("testApprovalRules1")
					&& approvalProcessRules.isActive())
					|| (approvalProcessRules.getApprovalRules().getName().equals("testApprovalRules3")
						&& !approvalProcessRules.isActive())
					|| (approvalProcessRules.getApprovalRules().getName().equals("testApprovalRules2")
							&& approvalProcessRules.isActive())
					|| (approvalProcessRules.getApprovalRules().getName().equals("testApprovalRules4")
							&& !approvalProcessRules.isActive()));
		}
	}
	
	@Test @Transactional(TransactionType.SINGLETON) 
	public void testActivateASingleApprovalProcessRulesInstanceInASet() throws Exception {
		approvalService.activateApprovalProcessRules(Collections.singleton("testApprovalRules1"),
				dataCreator.getApprovalProcess11());
		dataStore.flushBuffers();
		ApprovalProcessRules approvalProcessRules = dataCreator
				.getApprovalProcessRules13();
		assertTrue(dataStore
				.find(ApprovalProcessRules.class)
				.filterBy("approvalProcessId").withAValueEqualTo(approvalProcessRules
						.getApprovalProcessId())
				.filterBy("approvalRulesId").withAValueEqualTo(approvalProcessRules
						.getApprovalRulesId())
				.filterBy("active").withAValueEqualTo(true)
				.thenGetFirst().isPresent());
	}

	@Test @Transactional(TransactionType.SINGLETON) 
	public void testActivateAMultipleApprovalProcessRulesInstancesInASet() throws Exception {
		approvalService.activateApprovalProcessRules(asSet("testApprovalRules1","testApprovalRules2"),
				dataCreator.getApprovalProcess12());
		dataStore.flushBuffers();
		ApprovalProcessRules approvalProcessRules = dataCreator
				.getApprovalProcessRules15();
		assertTrue(dataStore
				.find(ApprovalProcessRules.class)
				.filterBy("approvalProcessId").withAValueEqualTo(approvalProcessRules
						.getApprovalProcessId())
				.filterBy("approvalRulesId").withAValueEqualTo(approvalProcessRules
						.getApprovalRulesId())
				.filterBy("active").withAValueEqualTo(true)
				.thenGetFirst().isPresent());
		
		approvalProcessRules = dataCreator
				.getApprovalProcessRules16();
		assertTrue(dataStore
				.find(ApprovalProcessRules.class)
				.filterBy("approvalProcessId").withAValueEqualTo(approvalProcessRules
						.getApprovalProcessId())
				.filterBy("approvalRulesId").withAValueEqualTo(approvalProcessRules
						.getApprovalRulesId())
				.filterBy("active").withAValueEqualTo(true)
				.thenGetFirst().isPresent());
	}
	
	@Test @Transactional(TransactionType.SINGLETON) 
	public void testDeactivateASingleApprovalProcessRulesInstanceInASet() throws Exception {
		approvalService.deactivateApprovalProcessRules(Collections.singleton("testApprovalRules2"),
				dataCreator.getApprovalProcess11());
		ApprovalProcessRules approvalProcessRules = dataCreator
				.getApprovalProcessRules14();
		assertTrue(dataStore
				.find(ApprovalProcessRules.class)
				.filterBy("approvalProcessId").withAValueEqualTo(approvalProcessRules
						.getApprovalProcessId())
				.filterBy("approvalRulesId").withAValueEqualTo(approvalProcessRules
						.getApprovalRulesId())
				.filterBy("active").withAValueEqualTo(false)
				.thenGetFirst().isPresent());
	}
	
	@Test @Transactional(TransactionType.SINGLETON) 
	public void testDeactivateMultipleApprovalProcessRulesInstancesInASet() throws Exception {
		approvalService.deactivateApprovalProcessRules(asSet("testApprovalRules3","testApprovalRules4"),
				dataCreator.getApprovalProcess12());
		ApprovalProcessRules approvalProcessRules = dataCreator
				.getApprovalProcessRules17();
		assertTrue(dataStore
				.find(ApprovalProcessRules.class)
				.filterBy("approvalProcessId").withAValueEqualTo(approvalProcessRules
						.getApprovalProcessId())
				.filterBy("approvalRulesId").withAValueEqualTo(approvalProcessRules
						.getApprovalRulesId())
				.filterBy("active").withAValueEqualTo(false)
				.thenGetFirst().isPresent());
		
		approvalProcessRules = dataCreator
				.getApprovalProcessRules18();
		assertTrue(dataStore
				.find(ApprovalProcessRules.class)
				.filterBy("approvalProcessId").withAValueEqualTo(approvalProcessRules
						.getApprovalProcessId())
				.filterBy("approvalRulesId").withAValueEqualTo(approvalProcessRules
						.getApprovalRulesId())
				.filterBy("active").withAValueEqualTo(false)
				.thenGetFirst().isPresent());
	}
	
	@Test @Transactional(TransactionType.SINGLETON) 
	public void testProcessApprovalAcceptedWithOneRuleAscendingOrder() throws Exception {
		ApprovalContext approvalContext = contextBuilder.createApprovalContext().
				setApproverAs("testApprover1", 
						"testGroup1")
				.setActionAs(ApprovalAction.APPROVED)
				.setApprovalProcessAs(dataCreator.getApprovalProcess1()).thenBuild();
		approvalService.processApproval(approvalContext);
		
		dataStore.flushBuffers();
		
		assertTrue(approvalContext.getApproval().getId()>0);
		assertThat(approvalContext.getApproval().getApprovalDate(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess().getId(), is(dataCreator.getApprovalProcess1().getId()));
		assertThat(approvalContext.getApproval().getApprovalProcess().isComplete(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().isApproved(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenApproved(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenRejected(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().getCompleteDate(), is(nullValue()));
		assertThat(approvalContext.getApproval().getApprover(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprover().getId(), is(dataCreator.getApprover1().getId()));
		assertThat(approvalContext.getApproval().getAction(), is(ApprovalAction.APPROVED));
		
		approvalContext = contextBuilder.createApprovalContext().
				setApproverAs("testApprover2", 
						"testGroup1")
				.setActionAs(ApprovalAction.APPROVED)
				.setApprovalProcessAs(dataCreator.getApprovalProcess1()).thenBuild();
		approvalService.processApproval(approvalContext);
		
		dataStore.flushBuffers();
		
		assertTrue(approvalContext.getApproval().getId()>0);
		assertThat(approvalContext.getApproval().getApprovalDate(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess().getId(), is(dataCreator.getApprovalProcess1().getId()));
		assertThat(approvalContext.getApproval().getApprovalProcess().isComplete(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().isApproved(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenApproved(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenRejected(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().getCompleteDate(), is(nullValue()));
		assertThat(approvalContext.getApproval().getApprover(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprover().getId(), is(dataCreator.getApprover2().getId()));
		assertThat(approvalContext.getApproval().getAction(), is(ApprovalAction.APPROVED));
		
		approvalContext = contextBuilder.createApprovalContext().
				setApproverAs("testApprover3", 
						"testGroup1")
				.setActionAs(ApprovalAction.APPROVED)
				.setApprovalProcessAs(dataCreator.getApprovalProcess1()).thenBuild();
		approvalService.processApproval(approvalContext);
		
		dataStore.flushBuffers();
		
		assertTrue(approvalContext.getApproval().getId()>0);
		assertThat(approvalContext.getApproval().getApprovalDate(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess().getId(), is(dataCreator.getApprovalProcess1().getId()));
		assertThat(approvalContext.getApproval().getApprovalProcess().isComplete(), is(true));
		assertThat(approvalContext.getApproval().getApprovalProcess().isApproved(), is(true));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenApproved(), is(true));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenRejected(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().getCompleteDate(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprover(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprover().getId(), is(dataCreator.getApprover3().getId()));
		assertThat(approvalContext.getApproval().getAction(), is(ApprovalAction.APPROVED));
	}

	@Test
	public void testProcessApprovalAcceptedWithMultipleApprovalsAtOnce() throws Exception {
		
		Runnable approver1Runnnable = ()->{
			UserTransaction transaction = JavaTransactionManagerSpiFactory
					.getInstance().getJavaTransactionManagerSpi()
					.getUserTransaction();
			try {
				transaction.begin();
				ApprovalContext approvalContext = contextBuilder.createApprovalContext().
						setApproverAs("testApprover1", 
								"testGroup1")
						.setActionAs(ApprovalAction.APPROVED)
						.setApprovalProcessAs(dataCreator.getApprovalProcess10()).thenBuild();
				approvalService.processApproval(approvalContext);
				transaction.commit();
			} catch (Exception e) {
			}
		};
		
		Runnable approver2Runnnable = ()->{
			UserTransaction transaction = JavaTransactionManagerSpiFactory
					.getInstance().getJavaTransactionManagerSpi()
					.getUserTransaction();
			try {
				transaction.begin();
				ApprovalContext approvalContext = contextBuilder.createApprovalContext().
						setApproverAs("testApprover2", 
								"testGroup1")
						.setActionAs(ApprovalAction.APPROVED)
						.setApprovalProcessAs(dataCreator.getApprovalProcess10()).thenBuild();
				approvalService.processApproval(approvalContext);
				transaction.commit();
			} catch (Exception e) {
			}
		};
		
		Runnable approver3Runnnable = ()->{
			UserTransaction transaction = JavaTransactionManagerSpiFactory
					.getInstance().getJavaTransactionManagerSpi()
					.getUserTransaction();
			try {
				transaction.begin();
				ApprovalContext approvalContext = contextBuilder.createApprovalContext().
						setApproverAs("testApprover3", 
								"testGroup1")
						.setActionAs(ApprovalAction.APPROVED)
						.setApprovalProcessAs(dataCreator.getApprovalProcess10()).thenBuild();
				approvalService.processApproval(approvalContext);
				transaction.commit();
			} catch (Exception e) {
			}
		};
		
		new Thread(approver1Runnnable).start();
		new Thread(approver2Runnnable).start();
		new Thread(approver3Runnnable).start();
		
		await().atMost(Duration.ONE_SECOND).until(()->{
			UserTransaction transaction = JavaTransactionManagerSpiFactory
					.getInstance().getJavaTransactionManagerSpi()
					.getUserTransaction();
			transaction.begin();
			ApprovalProcess approvalProcess = dataStore
					.get(ApprovalProcess.class, dataCreator.getApprovalProcess10().getId());
			assertThat(approvalProcess, is(notNullValue()));
			assertThat(approvalProcess.isComplete(), is(true));
			assertThat(approvalProcess.isApproved(), is(true));
			assertThat(approvalProcess.getCompleteDate(), is(notNullValue()));
			transaction.commit();
			return true;
		});
	}
	
	@Test @Transactional(TransactionType.SINGLETON) 
	public void testProcessApprovalAcceptedWithOneRuleDescendingOrder() throws Exception {
		ApprovalContext approvalContext = contextBuilder.createApprovalContext().
				setApproverAs("testApprover3", 
						"testGroup1")
				.setActionAs(ApprovalAction.APPROVED)
				.setApprovalProcessAs(dataCreator.getApprovalProcess3()).thenBuild();
		approvalService.processApproval(approvalContext);
		
		dataStore.flushBuffers();
		
		assertTrue(approvalContext.getApproval().getId()>0);
		assertThat(approvalContext.getApproval().getApprovalDate(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess().getId(), is(dataCreator.getApprovalProcess3().getId()));
		assertThat(approvalContext.getApproval().getApprovalProcess().isComplete(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().isApproved(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenApproved(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenRejected(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().getCompleteDate(), is(nullValue()));
		assertThat(approvalContext.getApproval().getApprover(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprover().getId(), is(dataCreator.getApprover3().getId()));
		assertThat(approvalContext.getApproval().getAction(), is(ApprovalAction.APPROVED));
		
		approvalContext = contextBuilder.createApprovalContext().
				setApproverAs("testApprover2", 
						"testGroup1")
				.setActionAs(ApprovalAction.APPROVED)
				.setApprovalProcessAs(dataCreator.getApprovalProcess3()).thenBuild();
		approvalService.processApproval(approvalContext);
		
		dataStore.flushBuffers();
		
		assertTrue(approvalContext.getApproval().getId()>0);
		assertThat(approvalContext.getApproval().getApprovalDate(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess().getId(), is(dataCreator.getApprovalProcess3().getId()));
		assertThat(approvalContext.getApproval().getApprovalProcess().isComplete(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().isApproved(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenApproved(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenRejected(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().getCompleteDate(), is(nullValue()));
		assertThat(approvalContext.getApproval().getApprover(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprover().getId(), is(dataCreator.getApprover2().getId()));
		assertThat(approvalContext.getApproval().getAction(), is(ApprovalAction.APPROVED));
		
		approvalContext = contextBuilder.createApprovalContext().
				setApproverAs("testApprover1", 
						"testGroup1")
				.setActionAs(ApprovalAction.APPROVED)
				.setApprovalProcessAs(dataCreator.getApprovalProcess3()).thenBuild();
		approvalService.processApproval(approvalContext);
		
		dataStore.flushBuffers();
		
		assertTrue(approvalContext.getApproval().getId()>0);
		assertThat(approvalContext.getApproval().getApprovalDate(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess().getId(), is(dataCreator.getApprovalProcess3().getId()));
		assertThat(approvalContext.getApproval().getApprovalProcess().isComplete(), is(true));
		assertThat(approvalContext.getApproval().getApprovalProcess().isApproved(), is(true));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenApproved(), is(true));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenRejected(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().getCompleteDate(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprover(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprover().getId(), is(dataCreator.getApprover1().getId()));
		assertThat(approvalContext.getApproval().getAction(), is(ApprovalAction.APPROVED));
	}
	
	@Test @Transactional(TransactionType.SINGLETON) 
	public void testProcessApprovalWithAnInvalidApproval() throws Exception {
		try {
			ApprovalContext approvalContext = contextBuilder.createApprovalContext().
					setApproverAs("testApprover1", 
							"testGroup1")
					.setActionAs(ApprovalAction.APPROVED)
					.setApprovalProcessAs(dataCreator.getApprovalProcess17()).thenBuild();
			approvalService.processApproval(approvalContext);
		} catch (Exception e) {
			fail("Unable to process approval with existing invalid approval: "+e.getMessage());
		}
	}
	
	@Test @Transactional(TransactionType.SINGLETON) 
	public void testProcessApprovalAcceptedWithMultipleRulesAscendingOrder() throws Exception {
		ApprovalContext approvalContext = contextBuilder.createApprovalContext().
				setApproverAs("testApprover2", 
						"testGroup1")
				.setActionAs(ApprovalAction.APPROVED)
				.setApprovalProcessAs(dataCreator.getApprovalProcess2()).thenBuild();
		approvalService.processApproval(approvalContext);
		
		dataStore.flushBuffers();
		
		assertTrue(approvalContext.getApproval().getId()>0);
		assertThat(approvalContext.getApproval().getApprovalDate(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess().getId(), is(dataCreator.getApprovalProcess2().getId()));
		assertThat(approvalContext.getApproval().getApprovalProcess().isComplete(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().isApproved(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenApproved(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenRejected(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().getCompleteDate(), is(nullValue()));
		assertThat(approvalContext.getApproval().getApprover(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprover().getId(), is(dataCreator.getApprover2().getId()));
		assertThat(approvalContext.getApproval().getAction(), is(ApprovalAction.APPROVED));
		
		approvalContext = contextBuilder.createApprovalContext().
				setApproverAs("testApprover3", 
						"testGroup1")
				.setActionAs(ApprovalAction.APPROVED)
				.setApprovalProcessAs(dataCreator.getApprovalProcess2()).thenBuild();
		approvalService.processApproval(approvalContext);
		
		dataStore.flushBuffers();
		
		assertTrue(approvalContext.getApproval().getId()>0);
		assertThat(approvalContext.getApproval().getApprovalDate(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess().getId(), is(dataCreator.getApprovalProcess2().getId()));
		assertThat(approvalContext.getApproval().getApprovalProcess().isComplete(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().isApproved(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenApproved(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenRejected(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().getCompleteDate(), is(nullValue()));
		assertThat(approvalContext.getApproval().getApprover(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprover().getId(), is(dataCreator.getApprover3().getId()));
		assertThat(approvalContext.getApproval().getAction(), is(ApprovalAction.APPROVED));

		approvalContext = contextBuilder.createApprovalContext().
				setApproverAs("testApprover4", 
						"testGroup1")
				.setActionAs(ApprovalAction.APPROVED)
				.setApprovalProcessAs(dataCreator.getApprovalProcess2()).thenBuild();
		approvalService.processApproval(approvalContext);
		
		dataStore.flushBuffers();
		
		assertTrue(approvalContext.getApproval().getId()>0);
		assertThat(approvalContext.getApproval().getApprovalDate(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess().getId(), is(dataCreator.getApprovalProcess2().getId()));
		assertThat(approvalContext.getApproval().getApprovalProcess().isComplete(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().isApproved(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenApproved(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenRejected(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().getCompleteDate(), is(nullValue()));
		assertThat(approvalContext.getApproval().getApprover(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprover().getId(), is(dataCreator.getApprover4().getId()));
		assertThat(approvalContext.getApproval().getAction(), is(ApprovalAction.APPROVED));

		approvalContext = contextBuilder.createApprovalContext().
				setApproverAs("testApprover5", 
						"testGroup1")
				.setActionAs(ApprovalAction.APPROVED)
				.setApprovalProcessAs(dataCreator.getApprovalProcess2()).thenBuild();
		approvalService.processApproval(approvalContext);
		
		dataStore.flushBuffers();
		
		assertTrue(approvalContext.getApproval().getId()>0);
		assertThat(approvalContext.getApproval().getApprovalDate(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess().getId(), is(dataCreator.getApprovalProcess2().getId()));
		assertThat(approvalContext.getApproval().getApprovalProcess().isComplete(), is(true));
		assertThat(approvalContext.getApproval().getApprovalProcess().isApproved(), is(true));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenApproved(), is(true));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenRejected(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().getCompleteDate(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprover(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprover().getId(), is(dataCreator.getApprover5().getId()));
		assertThat(approvalContext.getApproval().getAction(), is(ApprovalAction.APPROVED));
	}

	@Test @Transactional(TransactionType.SINGLETON) 
	public void testProcessApprovalAcceptedWithMultipleRulesDescendingOrder() throws Exception {
		ApprovalContext approvalContext = contextBuilder.createApprovalContext().
				setApproverAs("testApprover5", 
						"testGroup1")
				.setActionAs(ApprovalAction.APPROVED)
				.setApprovalProcessAs(dataCreator.getApprovalProcess4()).thenBuild();
		approvalService.processApproval(approvalContext);
		
		dataStore.flushBuffers();
		
		assertTrue(approvalContext.getApproval().getId()>0);
		assertThat(approvalContext.getApproval().getApprovalDate(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess().getId(), is(dataCreator.getApprovalProcess4().getId()));
		assertThat(approvalContext.getApproval().getApprovalProcess().isComplete(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().isApproved(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenApproved(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenRejected(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().getCompleteDate(), is(nullValue()));
		assertThat(approvalContext.getApproval().getApprover(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprover().getId(), is(dataCreator.getApprover5().getId()));
		assertThat(approvalContext.getApproval().getAction(), is(ApprovalAction.APPROVED));
		
		approvalContext = contextBuilder.createApprovalContext().
				setApproverAs("testApprover4", 
						"testGroup1")
				.setActionAs(ApprovalAction.APPROVED)
				.setApprovalProcessAs(dataCreator.getApprovalProcess4()).thenBuild();
		approvalService.processApproval(approvalContext);
		
		dataStore.flushBuffers();
		
		assertTrue(approvalContext.getApproval().getId()>0);
		assertThat(approvalContext.getApproval().getApprovalDate(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess().getId(), is(dataCreator.getApprovalProcess4().getId()));
		assertThat(approvalContext.getApproval().getApprovalProcess().isComplete(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().isApproved(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenApproved(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenRejected(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().getCompleteDate(), is(nullValue()));
		assertThat(approvalContext.getApproval().getApprover(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprover().getId(), is(dataCreator.getApprover4().getId()));
		assertThat(approvalContext.getApproval().getAction(), is(ApprovalAction.APPROVED));

		approvalContext = contextBuilder.createApprovalContext().
				setApproverAs("testApprover3", 
						"testGroup1")
				.setActionAs(ApprovalAction.APPROVED)
				.setApprovalProcessAs(dataCreator.getApprovalProcess4()).thenBuild();
		approvalService.processApproval(approvalContext);
		
		dataStore.flushBuffers();
		
		assertTrue(approvalContext.getApproval().getId()>0);
		assertThat(approvalContext.getApproval().getApprovalDate(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess().getId(), is(dataCreator.getApprovalProcess4().getId()));
		assertThat(approvalContext.getApproval().getApprovalProcess().isComplete(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().isApproved(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenApproved(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenRejected(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().getCompleteDate(), is(nullValue()));
		assertThat(approvalContext.getApproval().getApprover(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprover().getId(), is(dataCreator.getApprover3().getId()));
		assertThat(approvalContext.getApproval().getAction(), is(ApprovalAction.APPROVED));

		approvalContext = contextBuilder.createApprovalContext().
				setApproverAs("testApprover2", 
						"testGroup1")
				.setActionAs(ApprovalAction.APPROVED)
				.setApprovalProcessAs(dataCreator.getApprovalProcess4()).thenBuild();
		approvalService.processApproval(approvalContext);
		
		dataStore.flushBuffers();
		
		assertTrue(approvalContext.getApproval().getId()>0);
		assertThat(approvalContext.getApproval().getApprovalDate(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess().getId(), is(dataCreator.getApprovalProcess4().getId()));
		assertThat(approvalContext.getApproval().getApprovalProcess().isComplete(), is(true));
		assertThat(approvalContext.getApproval().getApprovalProcess().isApproved(), is(true));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenApproved(), is(true));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenRejected(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().getCompleteDate(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprover(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprover().getId(), is(dataCreator.getApprover2().getId()));
		assertThat(approvalContext.getApproval().getAction(), is(ApprovalAction.APPROVED));
	}

	@Test @Transactional(TransactionType.SINGLETON) 
	public void testProcessApprovalAcceptedWithActiveAndInactiveRules() throws Exception {
		ApprovalContext approvalContext = contextBuilder.createApprovalContext().
				setApproverAs("testApprover5", 
						"testGroup1")
				.setActionAs(ApprovalAction.APPROVED)
				.setApprovalProcessAs(dataCreator.getApprovalProcess13()).thenBuild();
		approvalService.processApproval(approvalContext);
		
		dataStore.flushBuffers();
		
		assertTrue(approvalContext.getApproval().getId()>0);
		assertThat(approvalContext.getApproval().getApprovalDate(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess().getId(), is(dataCreator.getApprovalProcess13().getId()));
		assertThat(approvalContext.getApproval().getApprovalProcess().isComplete(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().isApproved(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenApproved(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenRejected(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().getCompleteDate(), is(nullValue()));
		assertThat(approvalContext.getApproval().getApprover(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprover().getId(), is(dataCreator.getApprover5().getId()));
		assertThat(approvalContext.getApproval().getAction(), is(ApprovalAction.APPROVED));
		
		approvalContext = contextBuilder.createApprovalContext().
				setApproverAs("testApprover4", 
						"testGroup1")
				.setActionAs(ApprovalAction.APPROVED)
				.setApprovalProcessAs(dataCreator.getApprovalProcess13()).thenBuild();
		approvalService.processApproval(approvalContext);
		
		dataStore.flushBuffers();
		
		assertTrue(approvalContext.getApproval().getId()>0);
		assertThat(approvalContext.getApproval().getApprovalDate(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess().getId(), is(dataCreator.getApprovalProcess13().getId()));
		assertThat(approvalContext.getApproval().getApprovalProcess().isComplete(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().isApproved(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenApproved(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenRejected(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().getCompleteDate(), is(nullValue()));
		assertThat(approvalContext.getApproval().getApprover(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprover().getId(), is(dataCreator.getApprover4().getId()));
		assertThat(approvalContext.getApproval().getAction(), is(ApprovalAction.APPROVED));

		approvalContext = contextBuilder.createApprovalContext().
				setApproverAs("testApprover3", 
						"testGroup1")
				.setActionAs(ApprovalAction.APPROVED)
				.setApprovalProcessAs(dataCreator.getApprovalProcess13()).thenBuild();
		approvalService.processApproval(approvalContext);
		
		dataStore.flushBuffers();
		
		assertTrue(approvalContext.getApproval().getId()>0);
		assertThat(approvalContext.getApproval().getApprovalDate(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess().getId(), is(dataCreator.getApprovalProcess13().getId()));
		assertThat(approvalContext.getApproval().getApprovalProcess().isComplete(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().isApproved(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenApproved(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenRejected(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().getCompleteDate(), is(nullValue()));
		assertThat(approvalContext.getApproval().getApprover(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprover().getId(), is(dataCreator.getApprover3().getId()));
		assertThat(approvalContext.getApproval().getAction(), is(ApprovalAction.APPROVED));

		approvalContext = contextBuilder.createApprovalContext().
				setApproverAs("testApprover2", 
						"testGroup1")
				.setActionAs(ApprovalAction.APPROVED)
				.setApprovalProcessAs(dataCreator.getApprovalProcess13()).thenBuild();
		approvalService.processApproval(approvalContext);
		
		dataStore.flushBuffers();
		
		assertTrue(approvalContext.getApproval().getId()>0);
		assertThat(approvalContext.getApproval().getApprovalDate(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess().getId(), is(dataCreator.getApprovalProcess13().getId()));
		assertThat(approvalContext.getApproval().getApprovalProcess().isComplete(), is(true));
		assertThat(approvalContext.getApproval().getApprovalProcess().isApproved(), is(true));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenApproved(), is(true));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenRejected(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().getCompleteDate(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprover(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprover().getId(), is(dataCreator.getApprover2().getId()));
		assertThat(approvalContext.getApproval().getAction(), is(ApprovalAction.APPROVED));
	}
	
	@Test @Transactional(TransactionType.SINGLETON) 
	public void testProcessApprovalWithRejected1() throws Exception {
		ApprovalContext approvalContext = contextBuilder.createApprovalContext().
				setApproverAs("testApprover1", 
						"testGroup1")
				.setActionAs(ApprovalAction.REJECTED)
				.setApprovalProcessAs(dataCreator.getApprovalProcess5()).thenBuild();
		approvalService.processApproval(approvalContext);
		
		dataStore.flushBuffers();
		
		assertTrue(approvalContext.getApproval().getId()>0);
		assertThat(approvalContext.getApproval().getApprovalDate(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess().getId(), is(dataCreator.getApprovalProcess5().getId()));
		assertThat(approvalContext.getApproval().getApprovalProcess().isComplete(), is(true));
		assertThat(approvalContext.getApproval().getApprovalProcess().isApproved(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenApproved(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenRejected(), is(true));
		assertThat(approvalContext.getApproval().getApprovalProcess().getCompleteDate(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprover(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprover().getId(), is(dataCreator.getApprover1().getId()));
		assertThat(approvalContext.getApproval().getAction(), is(ApprovalAction.REJECTED));
	}

	@Test @Transactional(TransactionType.SINGLETON) 
	public void testProcessApprovalWithRejected2() throws Exception {
		ApprovalContext approvalContext = contextBuilder.createApprovalContext().
				setApproverAs("testApprover1", 
						"testGroup1")
				.setActionAs(ApprovalAction.APPROVED)
				.setApprovalProcessAs(dataCreator.getApprovalProcess6()).thenBuild();
		approvalService.processApproval(approvalContext);
		
		dataStore.flushBuffers();
		
		assertTrue(approvalContext.getApproval().getId()>0);
		assertThat(approvalContext.getApproval().getApprovalDate(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess().getId(), is(dataCreator.getApprovalProcess6().getId()));
		assertThat(approvalContext.getApproval().getApprovalProcess().isComplete(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().isApproved(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenApproved(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenRejected(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().getCompleteDate(), is(nullValue()));
		assertThat(approvalContext.getApproval().getApprover(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprover().getId(), is(dataCreator.getApprover1().getId()));
		assertThat(approvalContext.getApproval().getAction(), is(ApprovalAction.APPROVED));
		
		approvalContext = contextBuilder.createApprovalContext().
				setApproverAs("testApprover2", 
						"testGroup1")
				.setActionAs(ApprovalAction.APPROVED)
				.setApprovalProcessAs(dataCreator.getApprovalProcess6()).thenBuild();
		approvalService.processApproval(approvalContext);
		
		dataStore.flushBuffers();
		
		assertTrue(approvalContext.getApproval().getId()>0);
		assertThat(approvalContext.getApproval().getApprovalDate(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess().getId(), is(dataCreator.getApprovalProcess6().getId()));
		assertThat(approvalContext.getApproval().getApprovalProcess().isComplete(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().isApproved(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenApproved(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenRejected(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().getCompleteDate(), is(nullValue()));
		assertThat(approvalContext.getApproval().getApprover(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprover().getId(), is(dataCreator.getApprover2().getId()));
		assertThat(approvalContext.getApproval().getAction(), is(ApprovalAction.APPROVED));
		
		approvalContext = contextBuilder.createApprovalContext().
				setApproverAs("testApprover3", 
						"testGroup1")
				.setActionAs(ApprovalAction.REJECTED)
				.setApprovalProcessAs(dataCreator.getApprovalProcess6()).thenBuild();
		approvalService.processApproval(approvalContext);
		
		dataStore.flushBuffers();
		
		assertTrue(approvalContext.getApproval().getId()>0);
		assertThat(approvalContext.getApproval().getApprovalDate(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprovalProcess().getId(), is(dataCreator.getApprovalProcess6().getId()));
		assertThat(approvalContext.getApproval().getApprovalProcess().isComplete(), is(true));
		assertThat(approvalContext.getApproval().getApprovalProcess().isApproved(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenApproved(), is(false));
		assertThat(approvalContext.getApproval().getApprovalProcess().hasBeenRejected(), is(true));
		assertThat(approvalContext.getApproval().getApprovalProcess().getCompleteDate(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprover(), is(notNullValue()));
		assertThat(approvalContext.getApproval().getApprover().getId(), is(dataCreator.getApprover3().getId()));
		assertThat(approvalContext.getApproval().getAction(), is(ApprovalAction.REJECTED));
	}
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithApprovalRuleNameAndMissingApprovalGroupName() throws Exception {
		expectedException.expect(MissingFieldException.class);
		approvalService.initiateApproval(null, "testApprovalRules1");
	}
	
	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithApprovalGroupNameAndMissingApprovalRuleName() throws Exception {
		expectedException.expect(MissingFieldException.class);
		String ruleName = null;
		approvalService.initiateApproval("testGroup1", ruleName);
	}
	
	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithApprovalRuleNameAndNonExistentApprovalGroupName() throws Exception {
		expectedException.expect(NotFoundException.class);
		approvalService.initiateApproval("testInvalidGroup", "testApprovalRules1");
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithApprovalRuleNameAndInvalidApprovalGroupName() throws Exception {
		expectedException.expect(InvalidApprovalGroupException.class);
		approvalService.initiateApproval("testGroup3", "testApprovalRules9");
	}

	@Test @Transactional(TransactionType.SINGLETON) 
	public void testInitiateApprovalWithApprovalRuleNameAndADifferentApprovalGroupName () throws Exception {
		expectedException.expect(NotFoundException.class);
		approvalService.initiateApproval("testGroup2", "testApprovalRules1");
	}
	
	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithApprovalGroupNameAndNonExistentApprovalRuleName() throws Exception {
		expectedException.expect(NotFoundException.class);
		approvalService.initiateApproval("testGroup1", "testInvalidApprovalRules");
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithApprovalGroupNameAndInvalidApprovalRuleName() throws Exception {
		expectedException.expect(InvalidApprovalRulesException.class);
		approvalService.initiateApproval("testGroup2", "testApprovalRules13");
	}
	
	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithMissingApprovalRulesSet() throws Exception {
		expectedException.expect(MissingFieldException.class);
		Set<String> nullApprovalRulesSet = null;
		approvalService.initiateApproval("testGroup1", nullApprovalRulesSet);
	}
	
	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithEmptyApprovalRulesSet() throws Exception {
		expectedException.expect(MissingFieldException.class);
		Set<String> emptyApprovalRulesSet = Collections.emptySet();
		approvalService.initiateApproval("testGroup1", emptyApprovalRulesSet);
	}
	
	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithApprovalRulesSetAndNoneExistentApprovalRule() throws Exception {
		expectedException.expect(NotFoundException.class);
		approvalService.initiateApproval("testGroup1", Collections.singleton("testInvalidApprovalRules"));
	}
	
	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithApprovalRulesSetAndInvalidApprovalRules() throws Exception {
		expectedException.expect(InvalidApprovalRulesException.class);
		approvalService.initiateApproval("testGroup2", Collections.singleton("testApprovalRules13"));
	}

	@Test @Transactional(TransactionType.SINGLETON) 
	public void testInitiateApprovalWithApprovalRulesSetAndADifferentApprovalGroupName() throws Exception {
		expectedException.expect(NotFoundException.class);
		approvalService.initiateApproval("testGroup1", Collections.singleton("testApprovalRules5"));		
	}

	@Test @Transactional(TransactionType.SINGLETON) 
	public void testInitiateApprovalWithApprovalRulesSetAndADifferentApprovalGroupName2() throws Exception {
		expectedException.expect(NotFoundException.class);
		Set<String> approvalRulesSet = asSet("testApprovalRules1","testApprovalRules5");
		approvalService.initiateApproval("testGroup1", approvalRulesSet);		
	}
	
	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithApprovalRulesSetAndMissingGroupName() throws Exception {
		expectedException.expect(MissingFieldException.class);
		approvalService.initiateApproval(null, Collections.singleton("testApprovalRules1"));
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithSingleApprovalRulesInstanceAndMissingGroupName() throws Exception {
		expectedException.expect(MissingFieldException.class);
		approvalService.initiateApproval(null, "testApprovalRules1");
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithApprovalRulesSetAndNonExistentGroupName() throws Exception {
		expectedException.expect(NotFoundException.class);
		approvalService.initiateApproval("testInvalidGroupName", Collections.singleton("testApprovalRules1"));
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithApprovalRulesSetAndInvalidGroupName() throws Exception {
		expectedException.expect(InvalidApprovalGroupException.class);
		approvalService.initiateApproval("testGroup3", Collections.singleton("testApprovalRules9"));
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithSingleApprovalRulesInstanceAndNonExistentGroupName() throws Exception {
		expectedException.expect(NotFoundException.class);
		approvalService.initiateApproval("testInvalidGroupName", "testApprovalRules1");
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithSingleApprovalRulesInstanceAndInvalidGroupName() throws Exception {
		expectedException.expect(InvalidApprovalGroupException.class);
		approvalService.initiateApproval("testGroup3", "testApprovalRules9");
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithApprovalRuleSetAndNonExistentRule() throws Exception {
		expectedException.expect(NotFoundException.class);
		Set<String> approvalRulesSet = asSet("testApprovalRules1", "testUknownApprovalRules");
		approvalService.initiateApproval("testGroup1", approvalRulesSet);
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithApprovalRuleSetAndInvalidRule() throws Exception {
		expectedException.expect(InvalidApprovalRulesException.class);
		Set<String> approvalRulesSet = asSet("testApprovalRules12","testApprovalRules13");
		approvalService.initiateApproval("testGroup2", approvalRulesSet);
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithASingleApprovalRuleAndADifferentGroup() throws Exception {
		expectedException.expect(NotFoundException.class);
		approvalService.initiateApproval("testGroup1", "testApprovalRules5");
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithTheSameSetOfSingleRuleName() throws Exception {
		expectedException.expect(DuplicateRuleException.class);
		Set<String> approvalRulesSet = Collections.singleton("testApprovalRules1");
		approvalService.initiateApproval("testGroup1", approvalRulesSet, approvalRulesSet);
	}
	
	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithTheSameSetOfMultipleRuleNames() throws Exception {
		expectedException.expect(DuplicateRuleException.class);
		Set<String> approvalRulesSet = asSet("testApprovalRules1","testApprovalRules2");
		approvalService.initiateApproval("testGroup1", approvalRulesSet, approvalRulesSet);
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithASetsOfApprovalRulesContainingTheSameRule() throws Exception {
		expectedException.expect(DuplicateRuleException.class);
		Set<String> activeApprovalRulesSet = asSet("testApprovalRules1","testApprovalRules2");
		Set<String> inactiveApprovalRulesSet = asSet("testApprovalRules3","testApprovalRules2");
		approvalService.initiateApproval("testGroup1", activeApprovalRulesSet, inactiveApprovalRulesSet);
	}
	
	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithAnActiveSetOfRulesFromDifferentApprovalGroups() throws Exception {
		expectedException.expect(NotFoundException.class);
		Set<String> activeApprovalRulesSet = asSet("testApprovalRules1","testApprovalRules5");
		Set<String> inactiveApprovalRulesSet = asSet("testApprovalRules3","testApprovalRules2");
		approvalService.initiateApproval("testGroup1", activeApprovalRulesSet, inactiveApprovalRulesSet);
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithAnInactiveSetOfRulesFromDifferentApprovalGroups() throws Exception {
		expectedException.expect(NotFoundException.class);
		Set<String> activeApprovalRulesSet = asSet("testApprovalRules1","testApprovalRules2");
		Set<String> inactiveApprovalRulesSet = asSet("testApprovalRules3","testApprovalRules5");
		approvalService.initiateApproval("testGroup1", activeApprovalRulesSet, inactiveApprovalRulesSet);
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithASetOfApprovalRulesContainingANullItem() throws Exception {
		expectedException.expect(NotFoundException.class);
		Set<String> approvalRulesSet = new HashSet<>();
		approvalRulesSet.add("testApprovalRules1");
		approvalRulesSet.add(null);
		approvalService.initiateApproval("testGroup1", approvalRulesSet);
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithASetOfActiveApprovalRulesContainingANullItem() throws Exception {
		expectedException.expect(NotFoundException.class);
		Set<String> activeApprovalRulesSet = new HashSet<>();
		activeApprovalRulesSet.add("testApprovalRules1");
		activeApprovalRulesSet.add(null);
		Set<String> inactiveApprovalRulesSet = asSet("testApprovalRules3","testApprovalRules4");
		approvalService.initiateApproval("testGroup1", activeApprovalRulesSet, inactiveApprovalRulesSet);
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithASetOfInactiveApprovalRulesContainingANullItem() throws Exception {
		expectedException.expect(NotFoundException.class);
		Set<String> activeApprovalRulesSet = asSet("testApprovalRules3","testApprovalRules4");
		Set<String> inactiveApprovalRulesSet = new HashSet<>();
		inactiveApprovalRulesSet.add("testApprovalRules1");
		inactiveApprovalRulesSet.add(null);
		approvalService.initiateApproval("testGroup1", activeApprovalRulesSet, inactiveApprovalRulesSet);
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithASetOfApprovalRulesFromDifferentGroups() throws Exception {
		expectedException.expect(NotFoundException.class);
		Set<String> approvalRulesSet = asSet("testApprovalRules1", "testApprovalRules5");
		approvalService.initiateApproval("testGroup1", approvalRulesSet);
	}
		
	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithASetInactiveApprovalRulesAndAnEmptySetOfActiveApprovalRules() throws Exception {
		expectedException.expect(MissingFieldException.class);
		Set<String> activeApprovalRulesSet = Collections.emptySet();
		Set<String> inactiveApprovalRulesSet = asSet("testApprovalRules3","testApprovalRules2");
		approvalService.initiateApproval("testGroup1", activeApprovalRulesSet, inactiveApprovalRulesSet);
	}
	
	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithASetOfActiveApprovalRulesAndAnEmptySetInactiveApprovalRules() throws Exception {
		expectedException.expect(MissingFieldException.class);
		Set<String> activeApprovalRulesSet = asSet("testApprovalRules3","testApprovalRules2");
		Set<String> inactiveApprovalRulesSet = Collections.emptySet();
		approvalService.initiateApproval("testGroup1", activeApprovalRulesSet, inactiveApprovalRulesSet);
	}
	
	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithASetInactiveApprovalRulesAndAMissingSetOfActiveApprovalRules() throws Exception {
		expectedException.expect(MissingFieldException.class);
		Set<String> activeApprovalRulesSet = null;
		Set<String> inactiveApprovalRulesSet = asSet("testApprovalRules3","testApprovalRules2");
		approvalService.initiateApproval("testGroup1", activeApprovalRulesSet, inactiveApprovalRulesSet);
	}
	
	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithASetOfActiveApprovalRulesAndAMissingSetOfInactiveApprovalRules() throws Exception {
		expectedException.expect(MissingFieldException.class);
		Set<String> activeApprovalRulesSet = asSet("testApprovalRules3","testApprovalRules2");
		Set<String> inactiveApprovalRulesSet = null;
		approvalService.initiateApproval("testGroup1", activeApprovalRulesSet, inactiveApprovalRulesSet);
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithASetOfActiveApprovalRulesWithNonExistentRuleNames() throws Exception {
		expectedException.expect(NotFoundException.class);
		Set<String> activeApprovalRulesSet = asSet("testApprovalRules1","testInvalidApprovalRule");
		Set<String> inactiveApprovalRulesSet = Collections.singleton("testApprovalRules3");
		approvalService.initiateApproval("testGroup1", activeApprovalRulesSet, inactiveApprovalRulesSet);
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithASetOfInactiveApprovalRulesWithNonExistentRuleNames() throws Exception {
		expectedException.expect(NotFoundException.class);
		Set<String> activeApprovalRulesSet = Collections.singleton("testApprovalRules5");
		Set<String> inactiveApprovalRulesSet =  asSet("testApprovalRules3","testInvalidApprovalRule");
		approvalService.initiateApproval("testGroup1", activeApprovalRulesSet, inactiveApprovalRulesSet);
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithASetOfActiveApprovalRulesWithInvalidRuleNames() throws Exception {
		expectedException.expect(NotFoundException.class);
		Set<String> activeApprovalRulesSet =  asSet("testApprovalRules6","testApprovalRules13");
		Set<String> inactiveApprovalRulesSet = Collections.singleton("testApprovalRules5");
		approvalService.initiateApproval("testGroup1", activeApprovalRulesSet, inactiveApprovalRulesSet);
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithASetOfInactiveApprovalRulesWithInvalidRuleNames() throws Exception {
		expectedException.expect(NotFoundException.class);
		Set<String> activeApprovalRulesSet = Collections.singleton("testApprovalRules5");
		Set<String> inactiveApprovalRulesSet =  asSet("testApprovalRules6","testApprovalRules13");
		approvalService.initiateApproval("testGroup1", activeApprovalRulesSet, inactiveApprovalRulesSet);
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithASetOfActiveApprovalRulesAndASetOfInactiveRulesAndMissingGroupName() throws Exception {
		expectedException.expect(MissingFieldException.class);
		Set<String> activeApprovalRulesSet = Collections.singleton("testApprovalRules3");
		Set<String> inactiveApprovalRulesSet = Collections.singleton("testApprovalRules2");
		approvalService.initiateApproval(null, activeApprovalRulesSet, inactiveApprovalRulesSet);
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testInitiateApprovalWithASetOfActiveApprovalRulesAndASetOfInactiveRulesAndInvalidGroupName() throws Exception {
		expectedException.expect(InvalidApprovalGroupException.class);
		Set<String> activeApprovalRulesSet = Collections.singleton("testApprovalRules9");
		Set<String> inactiveApprovalRulesSet = Collections.singleton("testApprovalRules14");
		approvalService.initiateApproval("testGroup3", activeApprovalRulesSet, inactiveApprovalRulesSet);
	}
	
	@Test @Transactional(TransactionType.SINGLETON)
	public void testActivateApprovalProcessRulesWithMissingRuleNamesSet() throws Exception {
		expectedException.expect(MissingFieldException.class);
		approvalService.activateApprovalProcessRules(null, dataCreator.getApprovalProcess1());
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testActivateApprovalProcessRulesWithEmptyRuleNamesSet() throws Exception {
		expectedException.expect(MissingFieldException.class);
		approvalService.activateApprovalProcessRules(Collections.emptySet(), dataCreator.getApprovalProcess1());
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testActivateApprovalProcessRulesWithNonExistentRuleName() throws Exception {
		expectedException.expect(NotFoundException.class);
		approvalService.activateApprovalProcessRules(asSet("invalidRuleName","testApprovalRules1","testApprovalRules2"), dataCreator.getApprovalProcess11());
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testActivateApprovalProcessRulesWithNonExistentRuleName2() throws Exception {
		expectedException.expect(NotFoundException.class);
		approvalService.activateApprovalProcessRules(asSet("invalidRuleName"), dataCreator.getApprovalProcess11());
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testActivateApprovalProcessRulesWithInvalidGroupName() throws Exception {
		expectedException.expect(InvalidApprovalGroupException.class);
		approvalService.activateApprovalProcessRules(asSet("testApprovalRules9"), dataCreator.getApprovalProcess15());
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testActivateApprovalProcessRulesWithUnattachedRule() throws Exception {
		expectedException.expect(NotFoundException.class);
		approvalService.activateApprovalProcessRules(asSet("testApprovalRules3"), dataCreator.getApprovalProcess11());
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testActivateApprovalProcessRulesWithMissingApprovalProcess() throws Exception {
		expectedException.expect(MissingFieldException.class);
		approvalService.activateApprovalProcessRules(asSet("testApprovalRules1"), null);
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testActivateApprovalProcessRulesWithApprovalProcessWithoutApprovalGroup() throws Exception {
		expectedException.expect(MissingFieldException.class);
		approvalService.activateApprovalProcessRules(Collections.singleton("testApprovalRules1"), new ApprovalProcess());
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testActivateApprovalProcessRulesWithApprovalProcessAndDifferentApprovalGroup() throws Exception {
		expectedException.expect(NotFoundException.class);
		ApprovalProcess approvalProcess = new ApprovalProcess();
		approvalProcess.setId(dataCreator.getApprovalProcess11().getId());
		approvalProcess.setGroup(dataCreator.getApprovalGroup2());
		approvalService.activateApprovalProcessRules(Collections.singleton("testApprovalRules5"), approvalProcess);
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testActivateApprovalProcessRulesWithNonExistentApprovalProcess() throws Exception {
		expectedException.expect(NotFoundException.class);
		ApprovalProcess approvalProcess = new ApprovalProcess();
		approvalProcess.setId(99);
		approvalProcess.setGroup(dataCreator.getApprovalGroup1());
		approvalService.activateApprovalProcessRules(Collections.singleton("testApprovalRules1"), approvalProcess);
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testActivateApprovalProcessRulesWithInvalidApprovalProcess() throws Exception {
		expectedException.expect(InvalidApprovalProcessException.class);
		approvalService.activateApprovalProcessRules(Collections.singleton("testApprovalRules5"), dataCreator.getApprovalProcess16());
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testActivateApprovalProcessRulesWithCompletedApprovalProcess() throws Exception {
		expectedException.expect(ApprovalCompleteException.class);
		approvalService.activateApprovalProcessRules(Collections.singleton("testApprovalRules6"), 
				dataCreator.getApprovalProcess9());
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testDeactivateApprovalProcessRulesWithMissingRuleNamesSet() throws Exception {
		expectedException.expect(MissingFieldException.class);
		approvalService.deactivateApprovalProcessRules(null, dataCreator.getApprovalProcess1());
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testDeactivateApprovalProcessRulesWithEmptyRuleNamesSet() throws Exception {
		expectedException.expect(MissingFieldException.class);
		approvalService.deactivateApprovalProcessRules(Collections.emptySet(), dataCreator.getApprovalProcess1());
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testDeactivateApprovalProcessRulesWithNonExistentRuleName() throws Exception {
		expectedException.expect(NotFoundException.class);
		approvalService.deactivateApprovalProcessRules(asSet("invalidRuleName","testApprovalRules1","testApprovalRules2"), dataCreator.getApprovalProcess11());
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testDeactivateApprovalProcessRulesWithNonExistentRuleName2() throws Exception {
		expectedException.expect(NotFoundException.class);
		approvalService.deactivateApprovalProcessRules(asSet("invalidRuleName"), dataCreator.getApprovalProcess11());
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testDeactivateApprovalProcessRulesWithInvalidGroupName() throws Exception {
		expectedException.expect(InvalidApprovalGroupException.class);
		approvalService.deactivateApprovalProcessRules(asSet("testApprovalRules14"), dataCreator.getApprovalProcess15());
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testDeactivateApprovalProcessRulesWithUnattachedRule() throws Exception {
		expectedException.expect(NotFoundException.class);
		approvalService.deactivateApprovalProcessRules(asSet("testApprovalRules3"), dataCreator.getApprovalProcess11());
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testDeactivateApprovalProcessRulesWithMissingApprovalProcess() throws Exception {
		expectedException.expect(MissingFieldException.class);
		approvalService.deactivateApprovalProcessRules(asSet("testApprovalRules2"), null);
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testDeactivateApprovalProcessRulesWithApprovalProcessWithoutApprovalGroup() throws Exception {
		expectedException.expect(MissingFieldException.class);
		approvalService.deactivateApprovalProcessRules(Collections.singleton("testApprovalRules1"), new ApprovalProcess());
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testDeactivateApprovalProcessRulesWithApprovalProcessAndDifferentApprovalGroup() throws Exception {
		expectedException.expect(NotFoundException.class);
		ApprovalProcess approvalProcess = new ApprovalProcess();
		approvalProcess.setId(dataCreator.getApprovalProcess11().getId());
		approvalProcess.setGroup(dataCreator.getApprovalGroup2());
		approvalService.deactivateApprovalProcessRules(Collections.singleton("testApprovalRules9"), approvalProcess);
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testDeactivateApprovalProcessRulesWithNonExistentApprovalProcess() throws Exception {
		expectedException.expect(NotFoundException.class);
		ApprovalProcess approvalProcess = new ApprovalProcess();
		approvalProcess.setId(99);
		approvalProcess.setGroup(dataCreator.getApprovalGroup1());
		approvalService.deactivateApprovalProcessRules(Collections.singleton("testApprovalRules1"), approvalProcess);
	}
	
	@Test @Transactional(TransactionType.SINGLETON)
	public void testDeactivateApprovalProcessRulesWithInvalidApprovalProcess() throws Exception {
		expectedException.expect(InvalidApprovalProcessException.class);
		approvalService.deactivateApprovalProcessRules(Collections.singleton("testApprovalRules6"), dataCreator.getApprovalProcess16());
	}
	
	@Test @Transactional(TransactionType.SINGLETON)
	public void testDeactivateApprovalProcessRulesWithCompletedApprovalProcess() throws Exception {
		expectedException.expect(ApprovalCompleteException.class);
		approvalService.deactivateApprovalProcessRules(Collections.singleton("testApprovalRules5"), 
				dataCreator.getApprovalProcess9());
	}

	@Test @Transactional(TransactionType.SINGLETON) 
	public void testProcessApprovalWithNonMatchingLevel() throws Exception {
		expectedException.expect(InvalidApprovalException.class);
		ApprovalContext approvalContext = contextBuilder.createApprovalContext().
				setApproverAs("testApprover6", 
						"testGroup1")
				.setActionAs(ApprovalAction.APPROVED)
				.setApprovalProcessAs(dataCreator.getApprovalProcess7()).thenBuild();
		approvalService.processApproval(approvalContext);
	}
	
	@Test @Transactional(TransactionType.SINGLETON) 
	public void testProcessApprovalWithNonMatchingGroup() throws Exception {
		expectedException.expect(InvalidApprovalException.class);
		
		ApprovalContext approvalContext = contextBuilder.createApprovalContext().
				setApproverAs("testApprover7", 
						"testGroup2")
				.setActionAs(ApprovalAction.APPROVED)
				.setApprovalProcessAs(dataCreator.getApprovalProcess7()).thenBuild();
		approvalService.processApproval(approvalContext);
	}

	@Test @Transactional(TransactionType.SINGLETON) 
	public void testProcessApprovalWithLevelCountExceeded() throws Exception {
		expectedException.expect(InvalidApprovalException.class);		
		
		ApprovalContext approvalContext = contextBuilder.createApprovalContext().
				setApproverAs("testApprover10", 
						"testGroup2")
				.setActionAs(ApprovalAction.APPROVED)
				.setApprovalProcessAs(dataCreator.getApprovalProcess8()).thenBuild();
		approvalService.processApproval(approvalContext);
	}

	@Test @Transactional(TransactionType.SINGLETON) 
	public void testProcessApprovalWithTwoTimeApprover() throws Exception {
		expectedException.expect(InvalidApprovalException.class);		
		
		ApprovalContext approvalContext = contextBuilder.createApprovalContext().
				setApproverAs("testApprover7", 
						"testGroup2")
				.setActionAs(ApprovalAction.APPROVED)
				.setApprovalProcessAs(dataCreator.getApprovalProcess8()).thenBuild();
		approvalService.processApproval(approvalContext);
	}

	@Test @Transactional(TransactionType.SINGLETON) 
	public void testProcessApprovalWithCompletedApprovalProcess() throws Exception {
		expectedException.expect(ApprovalCompleteException.class);		
		
		ApprovalContext approvalContext = contextBuilder.createApprovalContext().
				setApproverAs("testApprover8", 
						"testGroup2")
				.setActionAs(ApprovalAction.APPROVED)
				.setApprovalProcessAs(dataCreator.getApprovalProcess9()).thenBuild();
		approvalService.processApproval(approvalContext);
	}

	@Test @Transactional(TransactionType.SINGLETON) 
	public void testProcessApprovalWithApproverLevelInInactiveRuleElements() throws Exception {
		expectedException.expect(InvalidApprovalException.class);		
		
		ApprovalContext approvalContext = contextBuilder.createApprovalContext().
				setApproverAs("testApprover7", 
						"testGroup2")
				.setActionAs(ApprovalAction.APPROVED)
				.setApprovalProcessAs(dataCreator.getApprovalProcess14()).thenBuild();
		approvalService.processApproval(approvalContext);
	}
	
	@Test @Transactional(TransactionType.SINGLETON) 
	public void testApproveInvalidApprovalProcess() throws Exception {
		expectedException.expect(InvalidApprovalProcessException.class);		
		
		ApprovalContext approvalContext = contextBuilder.createApprovalContext().
				setApproverAs("testApprover7", 
						"testGroup2")
				.setActionAs(ApprovalAction.APPROVED)
				.setApprovalProcessAs(dataCreator.getApprovalProcess16()).thenBuild();
		approvalService.processApproval(approvalContext);
	}
	
	@Test @Transactional(TransactionType.SINGLETON) 
	public void testApproveWithInvalidApprover() throws Exception {
		expectedException.expect(InvalidApproverException.class);		
		
		ApprovalContext approvalContext = contextBuilder.createApprovalContext().
				setApproverAs("testApprover9", 
						"testGroup2")
				.setActionAs(ApprovalAction.APPROVED)
				.setApprovalProcessAs(dataCreator.getApprovalProcess14()).thenBuild();
		approvalService.processApproval(approvalContext);
	}
}

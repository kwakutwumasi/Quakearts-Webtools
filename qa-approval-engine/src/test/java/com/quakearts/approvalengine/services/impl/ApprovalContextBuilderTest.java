package com.quakearts.approvalengine.services.impl;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.time.Instant;

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
import com.quakearts.approvalengine.context.ApprovalContext;
import com.quakearts.approvalengine.context.ApprovalContextBuilderFactory;
import com.quakearts.approvalengine.exception.InvalidApprovalException;
import com.quakearts.approvalengine.exception.InvalidApprovalGroupException;
import com.quakearts.approvalengine.exception.InvalidApprovalProcessException;
import com.quakearts.approvalengine.exception.InvalidApproverException;
import com.quakearts.approvalengine.exception.MissingFieldException;
import com.quakearts.approvalengine.exception.NotFoundException;
import com.quakearts.approvalengine.model.Approval.ApprovalAction;
import com.quakearts.approvalengine.model.ApprovalProcess;
import com.quakearts.webtools.test.AllServicesRunner;

@RunWith(AllServicesRunner.class)
public class ApprovalContextBuilderTest {

	@Inject
	private ApprovalContextBuilderFactory builder;

	@Inject
	private DataCreator dataCreator;
	
	@Before
	public void createData() {
		dataCreator.createTestData();
	}
	
	@Test @Transactional(TransactionType.SINGLETON)
	public void testCreateApprovalContextWithNoError() {
		try {
			ApprovalContext approvalContext = builder.createApprovalContext()
					.setApproverAs("testApprover1", 
							"testGroup1")
					.setActionAs(ApprovalAction.APPROVED)
					.setApprovalProcessAs(dataCreator.getApprovalProcess1())
					.thenBuild();
			
			assertThat(approvalContext.getApproval(), is(notNullValue()));
			assertThat(approvalContext.getApproval().getAction(), is(ApprovalAction.APPROVED));
			assertThat(approvalContext.getApproval().getApprovalDate(), is(notNullValue()));
			assertThat(approvalContext.getApproval().getApprover(), is(notNullValue()));
			assertThat(approvalContext.getApproval().getApprover().getId(), 
					is(dataCreator.getApprover1().getId()));
			assertThat(approvalContext.getApproval().isValid(), is(true));			
			assertThat(approvalContext.getApprovalProcess().getId(), 
					is(dataCreator.getApprovalProcess1().getId()));	
		} catch (Exception e) {
			fail("Unable to create context "+e.getMessage());
		}
	}
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test @Transactional(TransactionType.SINGLETON)
	public void testCreateApprovalContextApproverWithMissingIdentity() throws Exception {
		expectedException.expect(MissingFieldException.class);
		builder.createApprovalContext()
			.setApproverAs(null, "testGroup1")
			.setActionAs(ApprovalAction.APPROVED)
			.setApprovalProcessAs(dataCreator.getApprovalProcess1())
			.thenBuild();
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testCreateApprovalContextApproverWithMissingGroupName() throws Exception {
		expectedException.expect(MissingFieldException.class);
		builder.createApprovalContext()
			.setApproverAs("testApprover1", null)
			.setActionAs(ApprovalAction.APPROVED)
			.setApprovalProcessAs(dataCreator.getApprovalProcess1())
			.thenBuild();
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testCreateApprovalContextApproverWithNonExistentIdentity() throws Exception {
		expectedException.expect(NotFoundException.class);
		builder.createApprovalContext()
			.setApproverAs("invalidIdentity",
					"testGroup1")
			.setActionAs(ApprovalAction.APPROVED)
			.setApprovalProcessAs(dataCreator.getApprovalProcess1())
			.thenBuild();
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testCreateApprovalContextApproverWithInvalidIdentity() throws Exception {
		expectedException.expect(InvalidApproverException.class);
		builder.createApprovalContext().
		setApproverAs("testApprover9", 
				"testGroup2")
		.setActionAs(ApprovalAction.APPROVED)
		.setApprovalProcessAs(dataCreator.getApprovalProcess14()).thenBuild();	
	}
	
	@Test @Transactional(TransactionType.SINGLETON)
	public void testCreateApprovalContextApproverWithNonExistentGroupName() throws Exception {
		expectedException.expect(NotFoundException.class);
		builder.createApprovalContext()
			.setApproverAs("testApprover1",
					"invalidGroupName")
			.setActionAs(ApprovalAction.APPROVED)
			.setApprovalProcessAs(dataCreator.getApprovalProcess1())
			.thenBuild();
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testCreateApprovalContextApproverWithInvalidGroupName() throws Exception {
		expectedException.expect(InvalidApprovalGroupException.class);
		builder.createApprovalContext()
			.setApproverAs("testApprover11",
					"testGroup3")
			.setActionAs(ApprovalAction.APPROVED)
			.setApprovalProcessAs(dataCreator.getApprovalProcess15())
			.thenBuild();
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testCreateApprovalContextWithMissingApprovalAction() throws Exception {
		expectedException.expect(MissingFieldException.class);
		builder.createApprovalContext()
				.setApproverAs("testApprover1", 
						"testGroup1")
				.setActionAs(null)
				.setApprovalProcessAs(dataCreator.getApprovalProcess1())
				.thenBuild();
	}
	
	@Test @Transactional(TransactionType.SINGLETON)
	public void testCreateApprovalContextWithMissingApprovalProcess() throws Exception {
		expectedException.expect(MissingFieldException.class);
		builder.createApprovalContext()
				.setApproverAs("testApprover1", 
						"testGroup1")
				.setActionAs(ApprovalAction.APPROVED)
				.setApprovalProcessAs(null)
				.thenBuild();
	}
	
	@Test @Transactional(TransactionType.SINGLETON)
	public void testCreateApprovalContextWithNonExistentApprovalProcess() throws Exception {
		expectedException.expect(MissingFieldException.class);
		builder.createApprovalContext()
				.setApproverAs("testApprover1", 
						"testGroup1")
				.setActionAs(ApprovalAction.APPROVED)
				.setApprovalProcessAs(new ApprovalProcess())
				.thenBuild();
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testCreateApprovalContextWithInvalidApprovalProcess() throws Exception {
		expectedException.expect(InvalidApprovalProcessException.class);
		builder.createApprovalContext()
				.setApproverAs("testApprover7", 
						"testGroup2")
				.setActionAs(ApprovalAction.APPROVED)
				.setApprovalProcessAs(dataCreator.getApprovalProcess16())
				.thenBuild();
	}
	
	@Test @Transactional(TransactionType.SINGLETON)
	public void testCreateApprovalContextWithoutSettingApprover() throws Exception {
		expectedException.expect(MissingFieldException.class);
		builder.createApprovalContext()
				.setActionAs(ApprovalAction.APPROVED)
				.setApprovalProcessAs(dataCreator.getApprovalProcess1())
				.thenBuild();
	}
	
	@Test @Transactional(TransactionType.SINGLETON)
	public void testCreateApprovalContextWithoutSettingApprovalAction() throws Exception {
		expectedException.expect(MissingFieldException.class);
		builder.createApprovalContext()
				.setApproverAs("testApprover1", 
						"testGroup1")
				.setApprovalProcessAs(dataCreator.getApprovalProcess1())
				.thenBuild();
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testCreateApprovalContextWithoutSettingApprovalProcess() throws Exception {
		expectedException.expect(MissingFieldException.class);
		builder.createApprovalContext()
				.setApproverAs("testApprover1", 
						"testGroup1")
				.setActionAs(ApprovalAction.APPROVED)
				.thenBuild();
	}

	@Test @Transactional(TransactionType.SINGLETON)
	public void testCreateApprovalContextWithDifferentGroups() throws Exception {
		expectedException.expect(InvalidApprovalException.class);
		builder.createApprovalContext()
				.setApproverAs("testApprover10", 
						"testGroup2")
				.setActionAs(ApprovalAction.APPROVED)
				.setApprovalProcessAs(dataCreator.getApprovalProcess1())
				.thenBuild();
	}
	
	@Test @Transactional(TransactionType.SINGLETON)
	public void testCreateApprovalContextWithNonExistentApprovalProcess2() throws Exception {
		expectedException.expect(NotFoundException.class);
		ApprovalProcess approvalProcess = new ApprovalProcess();
		approvalProcess.setStartDate(Timestamp.from(Instant.now()));
		approvalProcess.setGroup(dataCreator.getApprovalGroup2());
		approvalProcess.setId(99);
		approvalProcess.setValid(true);
		builder.createApprovalContext()
				.setApproverAs("testApprover10", 
						"testGroup2")
				.setActionAs(ApprovalAction.APPROVED)
				.setApprovalProcessAs(approvalProcess)
				.thenBuild();
	}
}

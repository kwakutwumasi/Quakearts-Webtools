package com.quakearts.approvalengine.services.impl;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;

import org.junit.Test;

import com.quakearts.approvalengine.model.Approval;
import com.quakearts.approvalengine.model.ApprovalGroup;
import com.quakearts.approvalengine.model.Approval.ApprovalAction;
import com.quakearts.approvalengine.model.ApprovalProcess;
import com.quakearts.approvalengine.model.ApprovalProcessRules;
import com.quakearts.approvalengine.model.ApprovalRule;
import com.quakearts.approvalengine.model.ApprovalRules;
import com.quakearts.approvalengine.model.Approver;
import com.quakearts.approvalengine.model.ids.ApprovalProcessRulesId;
import com.quakearts.approvalengine.utils.AEUtils;

public class ModelTests {

	@Test
	public void testModels() {
		Approval approval = new Approval();
		ApprovalGroup approvalGroup = new ApprovalGroup();
		ApprovalProcess approvalProcess = new ApprovalProcess();
		Approver approver = new Approver();
		ApprovalProcessRules approvalProcessRules = new ApprovalProcessRules();
		ApprovalRules approvalRules = new ApprovalRules();
		ApprovalRule approvalRule = new ApprovalRule();
		Timestamp timestamp = Timestamp.from(Instant.now());

		assertThat(approval.getAction(), is(nullValue()));
		approval.setAction(ApprovalAction.REJECTED);
		assertThat(approval.getAction(), is(ApprovalAction.REJECTED));
		assertThat(approval.getApprovalDate(), is(nullValue()));
		approval.setApprovalDate(timestamp);
		assertThat(approval.getApprovalDate(), is(timestamp));
		assertThat(approval.getApprovalProcess(), is(nullValue()));
		approval.setApprovalProcess(approvalProcess);
		assertThat(approval.getApprovalProcess(), is(approvalProcess));
		assertThat(approval.getApprover(), is(nullValue()));
		approval.setApprover(approver);
		assertThat(approval.getApprover(), is(approver));
		assertThat(approval.getId(), is(0l));
		approval.setId(1);
		assertThat(approval.getId(), is(1l));
		assertThat(approval.isValid(), is(false));
		approval.setValid(true);
		assertThat(approval.isValid(), is(true));
		assertFalse(approval.toString().contains("Approval"));
		
		assertThat(approvalGroup.getId(), is(0l));
		approvalGroup.setId(1);
		assertThat(approvalGroup.getId(), is(1l));
		assertThat(approvalGroup.getName(), is(nullValue()));
		approvalGroup.setName("Name");
		assertThat(approvalGroup.getName(), is("Name"));
		assertThat(approvalGroup.isValid(), is(false));
		approvalGroup.setValid(true);
		assertThat(approvalGroup.isValid(), is(true));
		assertFalse(approvalGroup.toString().contains("ApprovalGroup"));

		assertThat(approvalProcess.getApprovalProcessRulesSet(), is(notNullValue()));
		HashSet<ApprovalProcessRules> set = new HashSet<>();
		set.add(new ApprovalProcessRules());
		approvalProcess.setApprovalProcessRulesSet(set);
		assertThat(approvalProcess.getApprovalProcessRulesSet(), is(set));
		assertThat(approvalProcess.isApproved(), is(false));
		approvalProcess.setApproved(true);
		assertThat(approvalProcess.isApproved(), is(true));
		assertThat(approvalProcess.isComplete(), is(false));
		approvalProcess.setComplete(true);
		assertThat(approvalProcess.isComplete(), is(true));
		assertThat(approvalProcess.getCompleteDate(), is(nullValue()));
		approvalProcess.setCompleteDate(timestamp);
		assertThat(approvalProcess.getCompleteDate(), is(timestamp));
		assertThat(approvalProcess.getStartDate(), is(nullValue()));
		approvalProcess.setStartDate(timestamp);
		assertThat(approvalProcess.getStartDate(), is(timestamp));
		assertThat(approvalProcess.getGroup(), is(nullValue()));
		approvalProcess.setGroup(approvalGroup);
		assertThat(approvalProcess.getGroup(), is(approvalGroup));
		assertThat(approvalProcess.getId(), is(0l));
		approvalProcess.setId(1);
		assertThat(approvalProcess.getId(), is(1l));
		assertThat(approvalProcess.isValid(), is(false));
		approvalProcess.setValid(true);
		assertThat(approvalProcess.isValid(), is(true));
		assertFalse(approvalProcess.toString().contains("ApprovalProcess"));

		assertThat(approvalProcessRules.getApprovalProcess(), is(nullValue()));
		approvalProcessRules.setApprovalProcess(approvalProcess);
		assertThat(approvalProcessRules.getApprovalProcess(), is(approvalProcess));
		assertThat(approvalProcessRules.getApprovalProcessId(), is(1l));
		approvalProcessRules.setApprovalProcessId(2);
		assertThat(approvalProcessRules.getApprovalProcessId(), is(2l));
		assertThat(approvalProcessRules.getApprovalRules(), is(nullValue()));
		approvalProcessRules.setApprovalRules(approvalRules);
		assertThat(approvalProcessRules.getApprovalRules(), is(approvalRules));
		assertThat(approvalProcessRules.getApprovalRulesId(), is(0));
		approvalProcessRules.setApprovalRulesId(2);
		assertThat(approvalProcessRules.getApprovalRulesId(), is(2));
		assertFalse(approvalProcessRules.toString().contains("ApprovalProcessRules"));

		assertThat(approvalRule.getCount(), is(0));
		approvalRule.setCount(1);
		assertThat(approvalRule.getCount(), is(1));
		assertThat(approvalRule.getId(), is(0));
		approvalRule.setId(2);
		assertThat(approvalRule.getId(), is(2));
		assertThat(approvalRule.getLevel(), is(0));
		approvalRule.setLevel(3);
		assertThat(approvalRule.getLevel(), is(3));
		assertThat(approvalRule.getRules(), is(nullValue()));
		approvalRule.setRules(approvalRules);
		assertThat(approvalRule.getRules(), is(approvalRules));
		assertThat(approvalRule.isActive(), is(false));
		approvalRule.setActive(true);
		assertThat(approvalRule.isActive(), is(true));
		assertFalse(approvalRule.toString().contains("ApprovalRule"));

		assertThat(approvalRules.getGroup(), is(nullValue()));
		approvalRules.setGroup(approvalGroup);
		assertThat(approvalRules.getGroup(), is(approvalGroup));
		assertThat(approvalRules.getId(), is(0));
		approvalRules.setId(1);
		assertThat(approvalRules.getId(), is(1));
		assertThat(approvalRules.getName(), is(nullValue()));
		approvalRules.setName("Name");
		assertThat(approvalRules.getName(), is("Name"));
		assertThat(approvalRules.getPriority(), is(0));
		approvalRules.setPriority(2);
		assertThat(approvalRules.getPriority(), is(2));
		assertThat(approvalRules.getRuleElements(), is(notNullValue()));
		Set<ApprovalRule> ruleElements = new HashSet<>();
		approvalRules.setRuleElements(ruleElements);
		assertThat(approvalRules.getRuleElements(), is(ruleElements));
		assertThat(approvalRules.isActive(), is(false));
		approvalRules.setActive(true);
		assertThat(approvalRules.isActive(), is(true));
		assertFalse(approvalRules.toString().contains("ApprovalRules"));

		assertThat(approver.getExternalId(), is(nullValue()));
		approver.setExternalId("ExternalId");
		assertThat(approver.getExternalId(), is("ExternalId"));
		assertThat(approver.getGroup(), is(nullValue()));
		approver.setGroup(approvalGroup);
		assertThat(approver.getGroup(), is(approvalGroup));
		assertThat(approver.getId(), is(0l));
		approver.setId(1);
		assertThat(approver.getId(), is(1l));
		assertThat(approver.getLevel(), is(0));
		approver.setLevel(2);
		assertThat(approver.getLevel(), is(2));
		assertThat(approver.isValid(), is(false));
		approver.setValid(true);
		assertThat(approver.isValid(), is(true));
		assertFalse(approver.toString().contains("Approver"));
	}

	@Test
	public void testApprovalProcessRulesId() throws Exception {
		ApprovalProcessRulesId approvalProcessRulesId = new ApprovalProcessRulesId();
		
		assertThat(approvalProcessRulesId.getApprovalProcessId(), is(0l));
		approvalProcessRulesId.setApprovalProcessId(1);
		assertThat(approvalProcessRulesId.getApprovalProcessId(), is(1l));
		assertThat(approvalProcessRulesId.getApprovalRulesId(), is(0));
		approvalProcessRulesId.setApprovalRulesId(2);
		assertThat(approvalProcessRulesId.getApprovalRulesId(), is(2));

		assertTrue(approvalProcessRulesId.equals(approvalProcessRulesId));
		assertTrue(approvalProcessRulesId.equals(new ApprovalProcessRulesId(1, 2)));
		assertFalse(approvalProcessRulesId.equals(new ApprovalProcessRulesId(1, 3)));
		assertFalse(approvalProcessRulesId.equals(new ApprovalProcessRulesId(2, 2)));
		assertFalse(approvalProcessRulesId.equals(new ApprovalProcessRulesId()));
		assertFalse(approvalProcessRulesId.equals(null));
		assertFalse(((Object)approvalProcessRulesId).equals(""));
		
		assertTrue(approvalProcessRulesId.hashCode() == new ApprovalProcessRulesId(1, 2).hashCode());		
	}
	
	@Test
	public void testAEUtilstoString() throws Exception {
		assertThat(AEUtils.toEncodedString(), is(""));
		assertThat(AEUtils.toEncodedString(1l), is("01"));
		assertThat(AEUtils.toEncodedString(10l), is("0A"));
		assertThat(AEUtils.toEncodedString(16l), is("10"));
		assertThat(AEUtils.toEncodedString(10l, 16l), is("0A10"));
		assertThat(AEUtils.toEncodedString(20l, 4587l), is("1411EB"));
		assertThat(AEUtils.toEncodedString((long)0xEF0FF, (long)0xAF234, (long)0x3DAF), is("0EF0FF0AF2343DAF"));
	}
}

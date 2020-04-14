package com.quakearts.approvalengine.services.impl;

import java.sql.Timestamp;
import java.time.Instant;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.quakearts.appbase.cdi.annotation.Transactional;
import com.quakearts.appbase.cdi.annotation.Transactional.TransactionType;
import com.quakearts.approvalengine.model.Approval;
import com.quakearts.approvalengine.model.Approval.ApprovalAction;
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
public class DataCreator {

	private volatile boolean created;

	@Inject
	@DataStoreFactoryHandle
	private DataStoreFactory factory;
	private ApprovalProcess approvalProcess1;
	private ApprovalProcess approvalProcess2;
	private ApprovalProcess approvalProcess3;
	private ApprovalProcess approvalProcess4;
	private ApprovalProcess approvalProcess5;
	private ApprovalProcess approvalProcess6;
	private ApprovalProcess approvalProcess7;
	private ApprovalProcess approvalProcess8;
	private ApprovalProcess approvalProcess9;
	private ApprovalProcess approvalProcess10;
	private ApprovalProcess approvalProcess11;
	private ApprovalProcess approvalProcess12;
	private ApprovalProcess approvalProcess13;
	private ApprovalProcess approvalProcess14;
	private ApprovalProcess approvalProcess15;
	private ApprovalProcess approvalProcess16;
	private ApprovalProcess approvalProcess17;
	private Approver approver1;
	private Approver approver2;
	private Approver approver3;
	private Approver approver4;
	private Approver approver5;
	private ApprovalGroup approvalGroup1;
	private ApprovalGroup approvalGroup2;
	private ApprovalRules approvalRules1;
	private ApprovalRules approvalRules7;
	private ApprovalRules approvalRules11;
	private ApprovalRules approvalRules16;
	private ApprovalRule approvalRule3;
	private ApprovalRule approvalRule4;
	private ApprovalProcessRules approvalProcessRules13;
	private ApprovalProcessRules approvalProcessRules14;
	private ApprovalProcessRules approvalProcessRules15;
	private ApprovalProcessRules approvalProcessRules16;
	private ApprovalProcessRules approvalProcessRules17;
	private ApprovalProcessRules approvalProcessRules18;


	@Transactional(TransactionType.SINGLETON)
	public void createTestData() {
		if (!created) {
			synchronized (this) {
				if (!created) {
					DataStore dataStore = factory.getDataStore();
					approvalGroup1 = new ApprovalGroup();
					approvalGroup1.setName("testGroup1");
					approvalGroup1.setValid(true);
					dataStore.save(approvalGroup1);
				
					approver1 = new Approver();
					approver1.setGroup(approvalGroup1);
					approver1.setExternalId("testApprover1");
					approver1.setLevel(1);
					approver1.setValid(true);
					dataStore.save(approver1);
				
					approver2 = new Approver();
					approver2.setGroup(approvalGroup1);
					approver2.setExternalId("testApprover2");
					approver2.setLevel(2);
					approver2.setValid(true);
					dataStore.save(approver2);
				
					approver3 = new Approver();
					approver3.setGroup(approvalGroup1);
					approver3.setExternalId("testApprover3");
					approver3.setLevel(2);
					approver3.setValid(true);
					dataStore.save(approver3);
				
					approver4 = new Approver();
					approver4.setGroup(approvalGroup1);
					approver4.setExternalId("testApprover4");
					approver4.setLevel(3);
					approver4.setValid(true);
					dataStore.save(approver4);
				
					approver5 = new Approver();
					approver5.setGroup(approvalGroup1);
					approver5.setExternalId("testApprover5");
					approver5.setLevel(3);
					approver5.setValid(true);
					dataStore.save(approver5);
					
					Approver approver6 = new Approver();
					approver6.setGroup(approvalGroup1);
					approver6.setExternalId("testApprover6");
					approver6.setLevel(4);
					approver6.setValid(true);
					dataStore.save(approver6);
					
					approvalGroup2 = new ApprovalGroup();
					approvalGroup2.setName("testGroup2");
					approvalGroup2.setValid(true);
					dataStore.save(approvalGroup2);
					
					ApprovalGroup approvalGroup3 = new ApprovalGroup();
					approvalGroup3.setName("testGroup3");
					approvalGroup3.setValid(false);
					dataStore.save(approvalGroup3);
					
					Approver approver7 = new Approver();
					approver7.setGroup(approvalGroup2);
					approver7.setExternalId("testApprover7");
					approver7.setLevel(1);
					approver7.setValid(true);
					dataStore.save(approver7);
				
					Approver approver8 = new Approver();
					approver8.setGroup(approvalGroup2);
					approver8.setExternalId("testApprover8");
					approver8.setLevel(2);
					approver8.setValid(true);
					dataStore.save(approver8);

					Approver approver9 = new Approver();
					approver9.setGroup(approvalGroup2);
					approver9.setExternalId("testApprover9");
					approver9.setLevel(1);
					approver9.setValid(false);
					dataStore.save(approver9);
					
					Approver approver10 = new Approver();
					approver10.setGroup(approvalGroup2);
					approver10.setExternalId("testApprover10");
					approver10.setLevel(2);
					approver10.setValid(true);
					dataStore.save(approver10);
					
					Approver approver11 = new Approver();
					approver11.setGroup(approvalGroup1);
					approver11.setExternalId("testApprover11");
					approver11.setLevel(1);
					approver11.setValid(true);
					dataStore.save(approver11);

					Approver approver12 = new Approver();
					approver12.setGroup(approvalGroup2);
					approver12.setExternalId("testApprover11");
					approver12.setLevel(2);
					approver12.setValid(true);
					dataStore.save(approver12);

					Approver approver13 = new Approver();
					approver13.setGroup(approvalGroup3);
					approver13.setExternalId("testApprover11");
					approver13.setLevel(3);
					approver13.setValid(true);
					dataStore.save(approver13);

					approvalRules1 = new ApprovalRules();
					approvalRules1.setGroup(approvalGroup1);
					approvalRules1.setName("testApprovalRules1");
					approvalRules1.setPriority(1);
					approvalRules1.setActive(true);
					dataStore.save(approvalRules1);
					dataStore.flushBuffers();
				
					ApprovalRule approvalRule1 = new ApprovalRule();
					approvalRule1.setCount(1);
					approvalRule1.setLevel(1);
					approvalRule1.setRules(approvalRules1);
					approvalRule1.setActive(true);
					dataStore.save(approvalRule1);
				
					ApprovalRule approvalRule2 = new ApprovalRule();
					approvalRule2.setCount(2);
					approvalRule2.setLevel(2);
					approvalRule2.setRules(approvalRules1);
					approvalRule2.setActive(true);
					dataStore.save(approvalRule2);
				
					ApprovalRules approvalRules2 = new ApprovalRules();
					approvalRules2.setGroup(approvalGroup1);
					approvalRules2.setName("testApprovalRules2");
					approvalRules2.setPriority(1);
					approvalRules2.setActive(true);
					dataStore.save(approvalRules2);
					dataStore.flushBuffers();
				
					approvalRule1 = new ApprovalRule();
					approvalRule1.setCount(2);
					approvalRule1.setLevel(2);
					approvalRule1.setRules(approvalRules2);
					approvalRule1.setActive(true);
					dataStore.save(approvalRule1);
				
					approvalRule2 = new ApprovalRule();
					approvalRule2.setCount(2);
					approvalRule2.setLevel(3);
					approvalRule2.setRules(approvalRules2);
					approvalRule2.setActive(true);
					dataStore.save(approvalRule2);
					
					ApprovalRules approvalRules3 = new ApprovalRules();
					approvalRules3.setGroup(approvalGroup1);
					approvalRules3.setName("testApprovalRules3");
					approvalRules3.setPriority(1);
					approvalRules3.setActive(true);
					dataStore.save(approvalRules3);
					dataStore.flushBuffers();
				
					approvalRule1 = new ApprovalRule();
					approvalRule1.setCount(1);
					approvalRule1.setLevel(2);
					approvalRule1.setRules(approvalRules3);
					approvalRule1.setActive(true);
					dataStore.save(approvalRule1);
				
					approvalRule2 = new ApprovalRule();
					approvalRule2.setCount(1);
					approvalRule2.setLevel(4);
					approvalRule2.setRules(approvalRules3);
					approvalRule2.setActive(true);
					dataStore.save(approvalRule2);
					
					ApprovalRules approvalRules4 = new ApprovalRules();
					approvalRules4.setGroup(approvalGroup1);
					approvalRules4.setName("testApprovalRules4");
					approvalRules4.setPriority(1);
					approvalRules4.setActive(true);
					dataStore.save(approvalRules4);
					dataStore.flushBuffers();
				
					approvalRule1 = new ApprovalRule();
					approvalRule1.setCount(3);
					approvalRule1.setLevel(2);
					approvalRule1.setRules(approvalRules4);
					approvalRule1.setActive(true);
					dataStore.save(approvalRule1);

					ApprovalRules approvalRules5 = new ApprovalRules();
					approvalRules5.setGroup(approvalGroup2);
					approvalRules5.setName("testApprovalRules5");
					approvalRules5.setPriority(1);
					approvalRules5.setActive(true);
					dataStore.save(approvalRules5);
					dataStore.flushBuffers();
				
					approvalRule1 = new ApprovalRule();
					approvalRule1.setCount(1);
					approvalRule1.setLevel(2);
					approvalRule1.setRules(approvalRules5);
					approvalRule1.setActive(true);
					dataStore.save(approvalRule1);
				
					approvalRule2 = new ApprovalRule();
					approvalRule2.setCount(2);
					approvalRule2.setLevel(1);
					approvalRule2.setRules(approvalRules5);
					approvalRule2.setActive(true);
					dataStore.save(approvalRule2);

					ApprovalRules approvalRules6 = new ApprovalRules();
					approvalRules6.setGroup(approvalGroup2);
					approvalRules6.setName("testApprovalRules6");
					approvalRules6.setPriority(1);
					approvalRules6.setActive(true);
					dataStore.save(approvalRules6);
					dataStore.flushBuffers();
				
					approvalRule1 = new ApprovalRule();
					approvalRule1.setCount(1);
					approvalRule1.setLevel(2);
					approvalRule1.setRules(approvalRules6);
					approvalRule1.setActive(true);
					dataStore.save(approvalRule1);
				
					approvalRule2 = new ApprovalRule();
					approvalRule2.setCount(1);
					approvalRule2.setLevel(1);
					approvalRule2.setRules(approvalRules6);
					approvalRule2.setActive(false);
					dataStore.save(approvalRule2);
					
					approvalRules7 = new ApprovalRules();
					approvalRules7.setGroup(approvalGroup2);
					approvalRules7.setName("testApprovalRules7");
					approvalRules7.setPriority(1);
					approvalRules7.setActive(false);
					dataStore.save(approvalRules7);
					dataStore.flushBuffers();
					
					approvalRule1 = new ApprovalRule();
					approvalRule1.setCount(1);
					approvalRule1.setLevel(2);
					approvalRule1.setRules(approvalRules7);
					approvalRule1.setActive(true);
					dataStore.save(approvalRule1);
				
					approvalRule2 = new ApprovalRule();
					approvalRule2.setCount(1);
					approvalRule2.setLevel(1);
					approvalRule2.setRules(approvalRules7);
					approvalRule2.setActive(false);
					dataStore.save(approvalRule2);

					ApprovalRules approvalRules8 = new ApprovalRules();
					approvalRules8.setGroup(approvalGroup2);
					approvalRules8.setName("testApprovalRules8");
					approvalRules8.setPriority(1);
					approvalRules8.setActive(false);
					dataStore.save(approvalRules8);
					dataStore.flushBuffers();
					
					approvalRule1 = new ApprovalRule();
					approvalRule1.setCount(1);
					approvalRule1.setLevel(2);
					approvalRule1.setRules(approvalRules8);
					approvalRule1.setActive(true);
					dataStore.save(approvalRule1);
				
					approvalRule2 = new ApprovalRule();
					approvalRule2.setCount(1);
					approvalRule2.setLevel(1);
					approvalRule2.setRules(approvalRules8);
					approvalRule2.setActive(false);
					dataStore.save(approvalRule2);
					
					ApprovalRules approvalRules9 = new ApprovalRules();
					approvalRules9.setGroup(approvalGroup3);
					approvalRules9.setName("testApprovalRules9");
					approvalRules9.setPriority(1);
					approvalRules9.setActive(true);
					dataStore.save(approvalRules9);
					dataStore.flushBuffers();
					
					approvalRule1 = new ApprovalRule();
					approvalRule1.setCount(1);
					approvalRule1.setLevel(2);
					approvalRule1.setRules(approvalRules9);
					approvalRule1.setActive(true);
					dataStore.save(approvalRule1);
				
					ApprovalRules approvalRules10 = new ApprovalRules();
					approvalRules10.setGroup(approvalGroup1);
					approvalRules10.setName("testApprovalRules10");
					approvalRules10.setPriority(1);
					approvalRules10.setActive(true);
					dataStore.save(approvalRules10);
					dataStore.flushBuffers();
					
					approvalRule1 = new ApprovalRule();
					approvalRule1.setCount(1);
					approvalRule1.setLevel(2);
					approvalRule1.setRules(approvalRules10);
					approvalRule1.setActive(true);
					dataStore.save(approvalRule1);

					approvalRules11 = new ApprovalRules();
					approvalRules11.setGroup(approvalGroup2);
					approvalRules11.setName("testApprovalRules11");
					approvalRules11.setPriority(2);
					approvalRules11.setActive(true);
					dataStore.save(approvalRules11);
					dataStore.flushBuffers();
					
					approvalRule1 = new ApprovalRule();
					approvalRule1.setCount(1);
					approvalRule1.setLevel(2);
					approvalRule1.setRules(approvalRules11);
					approvalRule1.setActive(true);
					dataStore.save(approvalRule1);

					ApprovalRules approvalRules12 = new ApprovalRules();
					approvalRules12.setGroup(approvalGroup2);
					approvalRules12.setName("testApprovalRules12");
					approvalRules12.setPriority(2);
					approvalRules12.setActive(true);
					dataStore.save(approvalRules12);
					dataStore.flushBuffers();
					
					approvalRule3 = new ApprovalRule();
					approvalRule3.setCount(1);
					approvalRule3.setLevel(1);
					approvalRule3.setRules(approvalRules12);
					approvalRule3.setActive(false);
					dataStore.save(approvalRule3);

					approvalRule4 = new ApprovalRule();
					approvalRule4.setCount(1);
					approvalRule4.setLevel(2);
					approvalRule4.setRules(approvalRules12);
					approvalRule4.setActive(true);
					dataStore.save(approvalRule4);

					approvalProcess1 = new ApprovalProcess();
					approvalProcess1.setStartDate(Timestamp.from(Instant.now()));
					approvalProcess1.setGroup(approvalGroup1);
					approvalProcess1.setValid(true);
				
					dataStore.save(approvalProcess1);
					dataStore.flushBuffers();

					ApprovalRules approvalRules13 = new ApprovalRules();
					approvalRules13.setGroup(approvalGroup2);
					approvalRules13.setName("testApprovalRules13");
					approvalRules13.setPriority(2);
					approvalRules13.setActive(false);
					dataStore.save(approvalRules13);
					dataStore.flushBuffers();
					
					approvalRule1 = new ApprovalRule();
					approvalRule1.setCount(1);
					approvalRule1.setLevel(1);
					approvalRule1.setRules(approvalRules13);
					approvalRule1.setActive(false);
					dataStore.save(approvalRule1);

					approvalRule2 = new ApprovalRule();
					approvalRule2.setCount(1);
					approvalRule2.setLevel(2);
					approvalRule2.setRules(approvalRules13);
					approvalRule2.setActive(true);
					dataStore.save(approvalRule2);

					ApprovalRules approvalRules14 = new ApprovalRules();
					approvalRules14.setGroup(approvalGroup3);
					approvalRules14.setName("testApprovalRules14");
					approvalRules14.setPriority(1);
					approvalRules14.setActive(true);
					dataStore.save(approvalRules14);
					dataStore.flushBuffers();
					
					approvalRule1 = new ApprovalRule();
					approvalRule1.setCount(1);
					approvalRule1.setLevel(2);
					approvalRule1.setRules(approvalRules14);
					approvalRule1.setActive(true);
					dataStore.save(approvalRule1);
				
					ApprovalRules approvalRules15 = new ApprovalRules();
					approvalRules15.setGroup(approvalGroup3);
					approvalRules15.setName("testApprovalRules15");
					approvalRules15.setPriority(1);
					approvalRules15.setActive(false);
					dataStore.save(approvalRules15);
					dataStore.flushBuffers();
					
					approvalRule1 = new ApprovalRule();
					approvalRule1.setCount(1);
					approvalRule1.setLevel(2);
					approvalRule1.setRules(approvalRules15);
					approvalRule1.setActive(true);
					dataStore.save(approvalRule1);
				
					ApprovalProcessRules approvalProcessRules1 = new ApprovalProcessRules();
					approvalProcessRules1.setApprovalProcess(approvalProcess1);
					approvalProcessRules1.setApprovalRules(approvalRules1);
					approvalProcessRules1.setActive(true);
				
					dataStore.save(approvalProcessRules1);
				
					approvalRules16 = new ApprovalRules();
					approvalRules16.setGroup(approvalGroup2);
					approvalRules16.setName("testApprovalRules16");
					approvalRules16.setPriority(1);
					approvalRules16.setActive(true);
					dataStore.save(approvalRules16);
					
					approvalProcess2 = new ApprovalProcess();
					approvalProcess2.setStartDate(Timestamp.from(Instant.now()));
					approvalProcess2.setGroup(approvalGroup1);
					approvalProcess2.setValid(true);
				
					dataStore.save(approvalProcess2);
					dataStore.flushBuffers();
				
					ApprovalProcessRules approvalProcessRules2 = new ApprovalProcessRules();
					approvalProcessRules2.setApprovalProcess(approvalProcess2);
					approvalProcessRules2.setApprovalRules(approvalRules1);
					approvalProcessRules2.setActive(true);
				
					dataStore.save(approvalProcessRules2);
				
					ApprovalProcessRules approvalProcessRules3 = new ApprovalProcessRules();
					approvalProcessRules3.setApprovalProcess(approvalProcess2);
					approvalProcessRules3.setApprovalRules(approvalRules2);
					approvalProcessRules3.setActive(true);

					dataStore.save(approvalProcessRules3);
				
					approvalProcess3 = new ApprovalProcess();
					approvalProcess3.setStartDate(Timestamp.from(Instant.now()));
					approvalProcess3.setGroup(approvalGroup1);
					approvalProcess3.setValid(true);
				
					dataStore.save(approvalProcess3);
					dataStore.flushBuffers();
				
					ApprovalProcessRules approvalProcessRules4 = new ApprovalProcessRules();
					approvalProcessRules4.setApprovalProcess(approvalProcess3);
					approvalProcessRules4.setApprovalRules(approvalRules1);
					approvalProcessRules4.setActive(true);
				
					dataStore.save(approvalProcessRules4);
				
					approvalProcess4 = new ApprovalProcess();
					approvalProcess4.setStartDate(Timestamp.from(Instant.now()));
					approvalProcess4.setGroup(approvalGroup1);
					approvalProcess4.setValid(true);
				
					dataStore.save(approvalProcess4);
					dataStore.flushBuffers();
				
					ApprovalProcessRules approvalProcessRules5 = new ApprovalProcessRules();
					approvalProcessRules5.setApprovalProcess(approvalProcess4);
					approvalProcessRules5.setApprovalRules(approvalRules1);
					approvalProcessRules5.setActive(true);
				
					dataStore.save(approvalProcessRules5);
				
					ApprovalProcessRules approvalProcessRules6 = new ApprovalProcessRules();
					approvalProcessRules6.setApprovalProcess(approvalProcess4);
					approvalProcessRules6.setApprovalRules(approvalRules2);
					approvalProcessRules6.setActive(true);

					dataStore.save(approvalProcessRules6);
				
					approvalProcess5 = new ApprovalProcess();
					approvalProcess5.setStartDate(Timestamp.from(Instant.now()));
					approvalProcess5.setGroup(approvalGroup1);
					approvalProcess5.setValid(true);
				
					dataStore.save(approvalProcess5);
					dataStore.flushBuffers();
				
					ApprovalProcessRules approvalProcessRules7 = new ApprovalProcessRules();
					approvalProcessRules7.setApprovalProcess(approvalProcess5);
					approvalProcessRules7.setApprovalRules(approvalRules1);
					approvalProcessRules7.setActive(true);
				
					dataStore.save(approvalProcessRules7);
				
					approvalProcess6 = new ApprovalProcess();
					approvalProcess6.setStartDate(Timestamp.from(Instant.now()));
					approvalProcess6.setGroup(approvalGroup1);
					approvalProcess6.setValid(true);
				
					dataStore.save(approvalProcess6);
					dataStore.flushBuffers();
				
					approvalProcessRules7 = new ApprovalProcessRules();
					approvalProcessRules7.setApprovalProcess(approvalProcess6);
					approvalProcessRules7.setApprovalRules(approvalRules1);
					approvalProcessRules7.setActive(true);
				
					dataStore.save(approvalProcessRules7);
				
					approvalProcess7 = new ApprovalProcess();
					approvalProcess7.setStartDate(Timestamp.from(Instant.now()));
					approvalProcess7.setGroup(approvalGroup1);
					approvalProcess7.setValid(true);
				
					dataStore.save(approvalProcess7);
					dataStore.flushBuffers();
				
					ApprovalProcessRules approvalProcessRules8 = new ApprovalProcessRules();
					approvalProcessRules8.setApprovalProcess(approvalProcess7);
					approvalProcessRules8.setApprovalRules(approvalRules1);
					approvalProcessRules8.setActive(true);
				
					dataStore.save(approvalProcessRules8);
				
					approvalProcess8 = new ApprovalProcess();
					approvalProcess8.setStartDate(Timestamp.from(Instant.now()));
					approvalProcess8.setGroup(approvalGroup2);
					approvalProcess8.setValid(true);
				
					dataStore.save(approvalProcess8);
					dataStore.flushBuffers();
				
					ApprovalProcessRules approvalProcessRules9 = new ApprovalProcessRules();
					approvalProcessRules9.setApprovalProcess(approvalProcess8);
					approvalProcessRules9.setApprovalRules(approvalRules5);
					approvalProcessRules9.setActive(true);
				
					dataStore.save(approvalProcessRules9);
					
					Approval approval = new Approval();
					approval.setAction(ApprovalAction.APPROVED);
					approval.setApprovalDate(Timestamp.from(Instant.now()));
					approval.setApprovalProcess(approvalProcess8);
					approval.setApprover(approver7);
					approval.setValid(true);
				
					dataStore.save(approval);
					
					approval = new Approval();
					approval.setAction(ApprovalAction.APPROVED);
					approval.setApprovalDate(Timestamp.from(Instant.now()));
					approval.setApprovalProcess(approvalProcess8);
					approval.setApprover(approver8);
					approval.setValid(true);
				
					dataStore.save(approval);
					
					approvalProcess9 = new ApprovalProcess();
					approvalProcess9.setStartDate(Timestamp.from(Instant.now()));
					approvalProcess9.setGroup(approvalGroup2);
					approvalProcess9.setValid(true);
					approvalProcess9.setComplete(true);
					approvalProcess9.setCompleteDate(Timestamp.from(Instant.now()));
					approvalProcess9.setApproved(false);
				
					dataStore.save(approvalProcess9);
					dataStore.flushBuffers();
				
					ApprovalProcessRules approvalProcessRules10 = new ApprovalProcessRules();
					approvalProcessRules10.setApprovalProcess(approvalProcess9);
					approvalProcessRules10.setApprovalRules(approvalRules5);
					approvalProcessRules10.setActive(true);
				
					dataStore.save(approvalProcessRules10);
					
					ApprovalProcessRules approvalProcessRules11 = new ApprovalProcessRules();
					approvalProcessRules11.setApprovalProcess(approvalProcess9);
					approvalProcessRules11.setApprovalRules(approvalRules6);
					approvalProcessRules11.setActive(false);
				
					dataStore.save(approvalProcessRules11);

					approval = new Approval();
					approval.setAction(ApprovalAction.REJECTED);
					approval.setApprovalDate(Timestamp.from(Instant.now()));
					approval.setApprovalProcess(approvalProcess9);
					approval.setApprover(approver7);
					approval.setValid(true);
				
					dataStore.save(approval);
				
					approvalProcess10 = new ApprovalProcess();
					approvalProcess10.setStartDate(Timestamp.from(Instant.now()));
					approvalProcess10.setGroup(approvalGroup1);
					approvalProcess10.setValid(true);
				
					dataStore.save(approvalProcess10);
					dataStore.flushBuffers();
				
					ApprovalProcessRules approvalProcessRules12 = new ApprovalProcessRules();
					approvalProcessRules12.setApprovalProcess(approvalProcess10);
					approvalProcessRules12.setApprovalRules(approvalRules1);
					approvalProcessRules12.setActive(true);
				
					dataStore.save(approvalProcessRules12);

					approvalProcess11 = new ApprovalProcess();
					approvalProcess11.setStartDate(Timestamp.from(Instant.now()));
					approvalProcess11.setGroup(approvalGroup1);
					approvalProcess11.setValid(true);
				
					dataStore.save(approvalProcess11);
					dataStore.flushBuffers();
				
					approvalProcessRules13 = new ApprovalProcessRules();
					approvalProcessRules13.setApprovalProcess(approvalProcess11);
					approvalProcessRules13.setApprovalRules(approvalRules1);
					approvalProcessRules13.setActive(false);
				
					dataStore.save(approvalProcessRules13);

					approvalProcessRules14 = new ApprovalProcessRules();
					approvalProcessRules14.setApprovalProcess(approvalProcess11);
					approvalProcessRules14.setApprovalRules(approvalRules2);
					approvalProcessRules14.setActive(true);
					
					dataStore.save(approvalProcessRules14);
					
					approvalProcess12 = new ApprovalProcess();
					approvalProcess12.setStartDate(Timestamp.from(Instant.now()));
					approvalProcess12.setGroup(approvalGroup1);
					approvalProcess12.setValid(true);
					
					dataStore.save(approvalProcess12);
					dataStore.flushBuffers();

					approvalProcessRules15 = new ApprovalProcessRules();
					approvalProcessRules15.setApprovalProcess(approvalProcess12);
					approvalProcessRules15.setApprovalRules(approvalRules1);
					approvalProcessRules15.setActive(false);
					
					dataStore.save(approvalProcessRules15);

					approvalProcessRules16 = new ApprovalProcessRules();
					approvalProcessRules16.setApprovalProcess(approvalProcess12);
					approvalProcessRules16.setApprovalRules(approvalRules2);
					approvalProcessRules16.setActive(false);

					dataStore.save(approvalProcessRules16);

					approvalProcessRules17 = new ApprovalProcessRules();
					approvalProcessRules17.setApprovalProcess(approvalProcess12);
					approvalProcessRules17.setApprovalRules(approvalRules3);
					approvalProcessRules17.setActive(true);
					
					dataStore.save(approvalProcessRules17);

					approvalProcessRules18 = new ApprovalProcessRules();
					approvalProcessRules18.setApprovalProcess(approvalProcess12);
					approvalProcessRules18.setApprovalRules(approvalRules4);
					approvalProcessRules18.setActive(true);

					dataStore.save(approvalProcessRules18);

					approvalProcess13 = new ApprovalProcess();
					approvalProcess13.setStartDate(Timestamp.from(Instant.now()));
					approvalProcess13.setGroup(approvalGroup1);
					approvalProcess13.setValid(true);
				
					dataStore.save(approvalProcess13);
					dataStore.flushBuffers();
				
					ApprovalProcessRules approvalProcessRules19 = new ApprovalProcessRules();
					approvalProcessRules19.setApprovalProcess(approvalProcess13);
					approvalProcessRules19.setApprovalRules(approvalRules1);
					approvalProcessRules19.setActive(false);
					
					dataStore.save(approvalProcessRules19);

					ApprovalProcessRules approvalProcessRules20 = new ApprovalProcessRules();
					approvalProcessRules20.setApprovalProcess(approvalProcess13);
					approvalProcessRules20.setApprovalRules(approvalRules2);
					approvalProcessRules20.setActive(true);

					dataStore.save(approvalProcessRules20);
					
					approvalProcess14 = new ApprovalProcess();
					approvalProcess14.setStartDate(Timestamp.from(Instant.now()));
					approvalProcess14.setGroup(approvalGroup2);
					approvalProcess14.setValid(true);
				
					dataStore.save(approvalProcess14);
					dataStore.flushBuffers();
				
					ApprovalProcessRules approvalProcessRules21 = new ApprovalProcessRules();
					approvalProcessRules21.setApprovalProcess(approvalProcess14);
					approvalProcessRules21.setApprovalRules(approvalRules6);
					approvalProcessRules21.setActive(true);
					
					dataStore.save(approvalProcessRules21);
					
					approvalProcess15 = new ApprovalProcess();
					approvalProcess15.setStartDate(Timestamp.from(Instant.now()));
					approvalProcess15.setGroup(approvalGroup3);
					approvalProcess15.setValid(true);
				
					dataStore.save(approvalProcess15);
					dataStore.flushBuffers();
				
					ApprovalProcessRules approvalProcessRules22 = new ApprovalProcessRules();
					approvalProcessRules22.setApprovalProcess(approvalProcess15);
					approvalProcessRules22.setApprovalRules(approvalRules9);
					approvalProcessRules22.setActive(false);
					
					dataStore.save(approvalProcessRules22);

					ApprovalProcessRules approvalProcessRules23 = new ApprovalProcessRules();
					approvalProcessRules23.setApprovalProcess(approvalProcess15);
					approvalProcessRules23.setApprovalRules(approvalRules14);
					approvalProcessRules23.setActive(true);
					
					dataStore.save(approvalProcessRules23);

					approvalProcess16 = new ApprovalProcess();
					approvalProcess16.setStartDate(Timestamp.from(Instant.now()));
					approvalProcess16.setGroup(approvalGroup2);
					approvalProcess16.setValid(false);
				
					dataStore.save(approvalProcess16);
					dataStore.flushBuffers();
				
					ApprovalProcessRules approvalProcessRules24 = new ApprovalProcessRules();
					approvalProcessRules24.setApprovalProcess(approvalProcess16);
					approvalProcessRules24.setApprovalRules(approvalRules5);
					approvalProcessRules24.setActive(false);
					
					dataStore.save(approvalProcessRules24);

					ApprovalProcessRules approvalProcessRules25 = new ApprovalProcessRules();
					approvalProcessRules25.setApprovalProcess(approvalProcess16);
					approvalProcessRules25.setApprovalRules(approvalRules6);
					approvalProcessRules25.setActive(true);
					
					dataStore.save(approvalProcessRules25);

					approvalProcess17 = new ApprovalProcess();
					approvalProcess17.setStartDate(Timestamp.from(Instant.now()));
					approvalProcess17.setGroup(approvalGroup1);
					approvalProcess17.setValid(true);
				
					dataStore.save(approvalProcess17);
					dataStore.flushBuffers();
				
					ApprovalProcessRules approvalProcessRules26 = new ApprovalProcessRules();
					approvalProcessRules26.setApprovalProcess(approvalProcess17);
					approvalProcessRules26.setApprovalRules(approvalRules1);
					approvalProcessRules26.setActive(true);
					
					dataStore.save(approvalProcessRules26);

					approval = new Approval();
					approval.setAction(ApprovalAction.APPROVED);
					approval.setApprovalDate(Timestamp.from(Instant.now()));
					approval.setApprovalProcess(approvalProcess17);
					approval.setApprover(approver1);
					approval.setValid(false);
					
					dataStore.save(approval);
					
					created = true;
				}
			}
		}
	}

	public ApprovalProcess getApprovalProcess1() {
		return approvalProcess1;
	}

	public ApprovalProcess getApprovalProcess2() {
		return approvalProcess2;
	}

	public ApprovalProcess getApprovalProcess3() {
		return approvalProcess3;
	}

	public ApprovalProcess getApprovalProcess4() {
		return approvalProcess4;
	}

	public ApprovalProcess getApprovalProcess5() {
		return approvalProcess5;
	}

	public ApprovalProcess getApprovalProcess6() {
		return approvalProcess6;
	}
	
	public ApprovalProcess getApprovalProcess7() {
		return approvalProcess7;
	}
	
	public ApprovalProcess getApprovalProcess8() {
		return approvalProcess8;
	}

	public ApprovalProcess getApprovalProcess9() {
		return approvalProcess9;
	}
	
	public ApprovalProcess getApprovalProcess10() {
		return approvalProcess10;
	}
	
	public ApprovalProcess getApprovalProcess11() {
		return approvalProcess11;
	}
	
	public ApprovalProcess getApprovalProcess12() {
		return approvalProcess12;
	}
	
	public ApprovalProcess getApprovalProcess13() {
		return approvalProcess13;
	}
	
	public ApprovalProcess getApprovalProcess14() {
		return approvalProcess14;
	}
	
	public ApprovalProcess getApprovalProcess15() {
		return approvalProcess15;
	}
	
	public ApprovalProcess getApprovalProcess16() {
		return approvalProcess16;
	}
	
	public ApprovalProcess getApprovalProcess17() {
		return approvalProcess17;
	}
	
	public Approver getApprover1() {
		return approver1;
	}

	public Approver getApprover2() {
		return approver2;
	}

	public Approver getApprover3() {
		return approver3;
	}

	public Approver getApprover4() {
		return approver4;
	}

	public Approver getApprover5() {
		return approver5;
	}

	public ApprovalGroup getApprovalGroup1() {
		return approvalGroup1;
	}
	
	public ApprovalGroup getApprovalGroup2() {
		return approvalGroup2;
	}
	
	public ApprovalRules getApprovalRules1() {
		return approvalRules1;
	}
	
	public ApprovalRules getApprovalRules7() {
		return approvalRules7;
	}
	
	public ApprovalRules getApprovalRules11() {
		return approvalRules11;
	}
	
	public ApprovalRules getApprovalRules16() {
		return approvalRules16;
	}
	
	public ApprovalProcessRules getApprovalProcessRules13() {
		return approvalProcessRules13;
	}

	public ApprovalProcessRules getApprovalProcessRules14() {
		return approvalProcessRules14;
	}

	public ApprovalProcessRules getApprovalProcessRules15() {
		return approvalProcessRules15;
	}

	public ApprovalProcessRules getApprovalProcessRules16() {
		return approvalProcessRules16;
	}

	public ApprovalProcessRules getApprovalProcessRules17() {
		return approvalProcessRules17;
	}
	
	public ApprovalProcessRules getApprovalProcessRules18() {
		return approvalProcessRules18;
	}
	
	public ApprovalRule getApprovalRule3() {
		return approvalRule3;
	}
	
	public ApprovalRule getApprovalRule4() {
		return approvalRule4;
	}
}

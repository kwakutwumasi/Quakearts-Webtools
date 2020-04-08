package com.quakearts.approvalengine.services.impl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ApprovalAdministrationServiceTest.class, ApprovalContextBuilderTest.class, 
	ApprovalServiceTest.class, ModelTests.class })
public class AllApprovalTests {}

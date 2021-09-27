package com.quakearts.approvalengine.services.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.event.ObservesAsync;
import javax.inject.Singleton;

import com.quakearts.approvalengine.event.ApprovalProcessEvent;
import com.quakearts.approvalengine.event.ApprovalProcessRulesEvent;

@Singleton
public class EventNotificationHelper {
	
	private Set<TestEventHandle> testHandles = Collections.synchronizedSet(new HashSet<>());
	
	public void registerTestHandle(TestEventHandle testHandle) {
		testHandles.add(testHandle);
	}
	
	public void handleApprovalEvent(@ObservesAsync ApprovalProcessEvent event) {
		testHandles.stream().parallel().forEach(t->t.handleEvent(event));
	}
	
	public void handleApprovalEvent(@ObservesAsync ApprovalProcessRulesEvent event) {
		testHandles.stream().parallel().forEach(t->t.handleEvent(event));
	}
	
	public static class TestEventHandle {
		private TestEventHandleFunction function;
		private Object eventObject;
		
		public TestEventHandle(TestEventHandleFunction function) {
			this.function = function;
		}

		public void handleEvent(Object eventObject) {
			if(function.test(eventObject)) {
				this.eventObject = eventObject;
			}
		}
		
		public boolean hasEventObject() {
			return eventObject != null;
		}
	}
	
	@FunctionalInterface
	public static interface TestEventHandleFunction {
		boolean test(Object value);
	}

	public void unRegisterTestHandle(TestEventHandle testHandle) {
		testHandles.remove(testHandle);
	}
}

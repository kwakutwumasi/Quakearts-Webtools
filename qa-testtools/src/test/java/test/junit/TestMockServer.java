package test.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import com.quakearts.tools.test.mockserver.exception.MockServerRuntimeException;

public class TestMockServer {

	@Test
	public void testStartAndStopWithAllConfigurationsSet() throws Exception {
	}

	@Test
	public void testTestStartAndStopWithMinimumConfigurationsSet() throws Exception {
		
	}
	
	@Test
	public void testStartTwice() throws Exception {
		
	}
	
	@Test(expected=MockServerRuntimeException.class)
	public void testMockingModeWithoutMockActions() throws Exception {
		
	}

	@Test
	public void testMockingMode() throws Exception {
		//Test unmatched request
		//Test matched request
		//Test MockAction with default matcher and response action
	}

	@Test
	public void testMockingModeWithDefaultActions() throws Exception {
		
	}
	
	@Test
	public void testRecordMode() throws Exception {
		
	}
	
	@Test
	public void testRecordModeWithDefaultActionSendingError() throws Exception {
		
	}

	@Test
	public void testRecordModeWithDefaultActionSendingErrorWithMessage() throws Exception {
		
	}

	@Test
	public void testRecordModeWithDefaultActionSendingCustomStatusAndOutput() throws Exception {
		
	}

	@Test
	public void testRecordModeWithLocalhostHttpsEndpoint() throws Exception {
		
	}
	
	@Test
	public void testRecordModeWith127_0_0_1HttpsEndpoint() throws Exception {
		
	}
	
	@Test
	public void testRecordWithRequestWithContentAndResponseBody() throws Exception {
		
	}
	
	@Test
	public void testRecordWithRequestWithoutContentAndResponseBody() throws Exception {
		
	}

	@Test
	public void testRecordWithRequestWithContentAndNoResponseBody() throws Exception {
		
	}
	
	@Test
	public void testRecordWithRequestRequiringContentButWithoutContent() throws Exception {
		
	}

	@Test
	public void testRecordWithRequestRequiringNoContentButWithContent() throws Exception {
		
	}
	
}

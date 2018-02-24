package test.junit;

import java.util.Arrays;

import org.junit.Test;

import com.quakearts.tools.test.mockserver.exception.BuilderException;
import com.quakearts.tools.test.mockserver.model.impl.HttpHeaderImpl;
import com.quakearts.tools.test.mockserver.model.impl.HttpMessageBuilder;
import com.quakearts.tools.test.mockserver.model.impl.MockActionBuilder;

public class TestMockActionBuilder {

	@Test
	public void testGetMockAction() throws Exception {
		MockActionBuilder.createNewMockAction()
				.addRequest(HttpMessageBuilder.createNewHttpRequest()
						.setId("testId1")
						.setMethodAs("POST")
						.setResourceAs("/test/resource")
						.setContentBytes("{\"test1\":\"value1\", \"test2\",\"value2\"}".getBytes())
						.setResponseAs(HttpMessageBuilder
								.createNewHttpResponse()
								.setResponseCodeAs(400)
								.setContentBytes("{\"error\":\"No such item\"}".getBytes())
								.addHeaders(new HttpHeaderImpl("Content-Type", "application/json; charset=iso-8859-1"))
								.thenBuild())
						.addHeaders(new HttpHeaderImpl("Content-Type", "application/json; charset=iso-8859-1"),
								new HttpHeaderImpl("Authorization", "Basic dGVzdHVzZXI6dGVzdHBhc3N3b3Jk"),
								new HttpHeaderImpl("X-Application-Role", Arrays.asList("User","Admin")))
						.thenBuild())
				.thenBuild();
		
		MockActionBuilder.createNewMockAction()
		.addRequest(HttpMessageBuilder.createNewHttpRequest()
				.setId("testId2")
				.setMethodAs("POST")
				.setResourceAs("/test/resource")
				.setContentBytes("{\"test1\":\"value1\", \"test2\",\"value2\"}".getBytes())
				.setResponseAs(HttpMessageBuilder
						.createNewHttpResponse()
						.setResponseCodeAs(400)
						.setContentBytes("{\"error\":\"No such item\"}".getBytes())
						.addHeaders(new HttpHeaderImpl("Content-Type", "application/json; charset=iso-8859-1"))
						.thenBuild())
				.addHeaders(new HttpHeaderImpl("Content-Type", "application/json; charset=iso-8859-1"),
						new HttpHeaderImpl("Authorization", "Basic dGVzdHVzZXI6dGVzdHBhc3N3b3Jk"),
						new HttpHeaderImpl("X-Application-Role", Arrays.asList("User","Admin")))
				.thenBuild())
		.addMatcher((httpRequest1,httpRequest2)->{
			return true;
		})
		.addResponseAction((response)->{return HttpMessageBuilder
					.createNewHttpResponse()
					.setResponseCodeAs(201).thenBuild();})
		.thenBuild();
	}

	@Test(expected=BuilderException.class)
	public void testGetMockActionWithoutRequest() throws Exception {
		MockActionBuilder.createNewMockAction().thenBuild();
	}

	@Test(expected=BuilderException.class)
	public void testGetMockActionWithoutResponseInRequest() throws Exception {
		MockActionBuilder.createNewMockAction()
		.addRequest(HttpMessageBuilder.createNewHttpRequest()
				.setId("testId1")
				.setMethodAs("POST")
				.setResourceAs("/test/resource")
				.setContentBytes("{\"test1\":\"value1\", \"test2\",\"value2\"}".getBytes())
				.addHeaders(new HttpHeaderImpl("Content-Type", "application/json; charset=iso-8859-1"),
						new HttpHeaderImpl("Authorization", "Basic dGVzdHVzZXI6dGVzdHBhc3N3b3Jk"),
						new HttpHeaderImpl("X-Application-Role", Arrays.asList("User","Admin")))
				.thenBuild())
		.thenBuild();
	}

}

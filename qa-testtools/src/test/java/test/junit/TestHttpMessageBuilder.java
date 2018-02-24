package test.junit;

import static org.junit.Assert.*;
import static org.hamcrest.core.IsNot.*;

import java.util.Arrays;

import static org.hamcrest.core.Is.*;

import org.junit.Test;

import com.quakearts.tools.test.mockserver.exception.BuilderException;
import com.quakearts.tools.test.mockserver.model.HttpHeader;
import com.quakearts.tools.test.mockserver.model.HttpRequest;
import com.quakearts.tools.test.mockserver.model.HttpResponse;
import com.quakearts.tools.test.mockserver.model.impl.HttpHeaderImpl;
import com.quakearts.tools.test.mockserver.model.impl.HttpMessageBuilder;

public class TestHttpMessageBuilder {

	@Test(expected=BuilderException.class)
	public void testWithNullMethod() throws Exception {
		HttpMessageBuilder.createNewHttpRequest().setMethodAs(null);
	}

	@Test(expected=BuilderException.class)
	public void testWithInvalidMethod() throws Exception {
		HttpMessageBuilder.createNewHttpRequest().setMethodAs("In Valid");
	}
	
	public void testWithLowercaseMethod() throws Exception {
		HttpMessageBuilder.createNewHttpRequest().setMethodAs("get");
	}
	
	@Test(expected=BuilderException.class)
	public void testWithInvalidResource() throws Exception {
		HttpMessageBuilder.createNewHttpRequest().setResourceAs(null);
	}
	
	@Test(expected=BuilderException.class)
	public void testBuildWIthoutMethodResourceAndId() throws Exception {
		HttpMessageBuilder.createNewHttpRequest().thenBuild();
	}
	
	@Test
	public void testBuildRequestWithMinimumRequiredFields() throws Exception {
		HttpRequest httpRequest = HttpMessageBuilder.createNewHttpRequest()
				.setId("testId")
				.setMethodAs("POST")
				.setResourceAs("/test/resource")
				.thenBuild();
		
		assertThat(httpRequest.getId(), is("testId"));
		assertThat(httpRequest.getMethod(), is("POST"));
		assertThat(httpRequest.getResource(), is("/test/resource"));
		assertThat(httpRequest.getContentEncoding() == null, is(true));
	}

	@Test
	public void testBuildRequestWithAllFieldsExceptResponse() throws Exception {
		HttpHeader httpHeader = new HttpHeaderImpl("Content-Type","application/x-form-urlencoded");
		HttpRequest httpRequest = HttpMessageBuilder.createNewHttpRequest()
				.setId("testId")
				.setMethodAs("POST")
				.setResourceAs("/test/resource")
				.setContentBytes("test=test&result=true".getBytes())
				.addHeaders(httpHeader)
				.thenBuild();
		
		assertThat(httpRequest.getId(), is("testId"));
		assertThat(httpRequest.getMethod(), is("POST"));
		assertThat(httpRequest.getResource(), is("/test/resource"));
		assertThat(httpRequest.getContentBytes(), is("test=test&result=true".getBytes()));
		assertThat(httpRequest.getHeaders(), is(Arrays.asList(httpHeader)));
	}

	@Test
	public void testAddContentTypeHeaderWithCharset() throws Exception {
		HttpRequest httpRequest = HttpMessageBuilder.createNewHttpRequest()
				.setId("testId")
				.setMethodAs("POST")
				.setResourceAs("/test/resource")
				.setContentBytes("test=test&result=true".getBytes())
				.addHeaders(new HttpHeaderImpl("Content-type","application/x-form-urlencoded; charset=utf-8"))
				.thenBuild();
		
		assertThat(httpRequest.getContentEncoding(), is("UTF-8"));	
	}
	
	@Test
	public void testAddContentTypeHeaderWithOutCharset() throws Exception {
		HttpRequest httpRequest = HttpMessageBuilder.createNewHttpRequest()
				.setId("testId")
				.setMethodAs("POST")
				.setResourceAs("/test/resource")
				.setContentBytes("test=test&result=true".getBytes())
				.addHeaders(new HttpHeaderImpl("Content-type","application/x-form-urlencoded; charset"))
				.thenBuild();
		
		assertThat(httpRequest.getContentEncoding() == null, is(true));	
	}
	
	@Test
	public void testRequestEqualsAndHashCode() throws Exception {
		HttpRequest httpRequest1 = HttpMessageBuilder.createNewHttpRequest()
				.setId("testId1")
				.setMethodAs("POST")
				.setResourceAs("/test/resource")
				.setContentBytes("test=test&result=true".getBytes())
				.addHeaders(new HttpHeaderImpl("Content-Type","application/x-form-urlencoded"))
				.addHeaders(new HttpHeaderImpl("Authorization", "Basic dGVzdHVzZXI6dGVzdHBhc3N3b3Jk"))
				.addHeaders(new HttpHeaderImpl("X-Application-Role", Arrays.asList("Role1", "Role2", "Role3")))
				.thenBuild(),
				
				httpRequest2 = HttpMessageBuilder.createNewHttpRequest()
				.setId("testId1")
				.setMethodAs("POST")
				.setResourceAs("/test/resource")
				.thenBuild(),
				
				httpRequest3 = HttpMessageBuilder.createNewHttpRequest()
				.setId("testId1")
				.setMethodAs("POST")
				.setResourceAs("/test/resource")
				.setContentBytes("test=test&result=true".getBytes())
				.addHeaders(new HttpHeaderImpl("Content-Type","application/x-form-urlencoded"))
				.addHeaders(new HttpHeaderImpl("Authorization", "Basic dGVzdHVzZXI6dGVzdHBhc3N3b3Jk"))
				.addHeaders(new HttpHeaderImpl("X-Application-Role", Arrays.asList("Role1", "Role2", "Role3")))
				.thenBuild(),
				
				httpRequest4 = HttpMessageBuilder.createNewHttpRequest()
				.setId("testId2")
				.setMethodAs("POST")
				.setResourceAs("/test/resource")
				.thenBuild();
		
		assertThat(httpRequest1, is(httpRequest3));
		assertThat(httpRequest1.hashCode(), is(httpRequest3.hashCode()));
		assertThat(httpRequest2, is(not(httpRequest4)));
		assertThat(httpRequest2, is(not(httpRequest3)));
		assertThat(httpRequest1.hashCode(), is(not(httpRequest4.hashCode())));
		
		HttpRequest httpRequest5 = HttpMessageBuilder.createNewHttpRequest()
				.setId("testId1")
				.setMethodAs("POST")
				.setResourceAs("/test/resource")
				.setContentBytes("test=test&result=true".getBytes())
				.thenBuild(),
				httpRequest6 = HttpMessageBuilder.createNewHttpRequest()
				.setId("testId1")
				.setMethodAs("POST")
				.setResourceAs("/test/resource")
				.setContentBytes("test=test&result=true".getBytes())
				.thenBuild(),
				httpRequest7 = HttpMessageBuilder.createNewHttpRequest()
				.setId("testId1")
				.setMethodAs("POST")
				.setResourceAs("/test/resource")
				.addHeaders(new HttpHeaderImpl("X-Application-Role", Arrays.asList("Role1", "Role2", "Role3")))
				.thenBuild(),
				httpRequest8 = HttpMessageBuilder.createNewHttpRequest()
				.setId("testId1")
				.setMethodAs("POST")
				.setResourceAs("/test/resource")
				.addHeaders(new HttpHeaderImpl("X-Application-Role", Arrays.asList("Role1", "Role2", "Role3")))
				.thenBuild();
		
		assertThat(httpRequest5, is(httpRequest6));
		assertThat(httpRequest7, is(httpRequest8));

	}
	
	@Test
	public void testAddingResponse() throws Exception {
		HttpRequest httpRequest = HttpMessageBuilder.createNewHttpRequest()
				.setId("testId")
				.setMethodAs("POST")
				.setResourceAs("/test/resource")
				.setContentBytes("{\"test1\":\"value1\", \"test2\",\"value2\"}".getBytes())
				.setResponseAs(HttpMessageBuilder
						.createNewHttpResponse()
						.setResponseCodeAs(400)
						.setContentBytes("{\"error\":\"No such item\"}".getBytes())
						.addHeaders(new HttpHeaderImpl("Content-Type", "application/json; charset=iso-8859-1"))
						.thenBuild())
				.addHeaders(new HttpHeaderImpl("Content-Type", "application/json; charset=iso-8859-1"))
				.addHeaders(new HttpHeaderImpl("Authorization", "Basic dGVzdHVzZXI6dGVzdHBhc3N3b3Jk"))
				.addHeaders(new HttpHeaderImpl("X-Application-Role", Arrays.asList("User","Admin")))
				.thenBuild();
		
		HttpResponse response = HttpMessageBuilder
				.createNewHttpResponse()
				.setResponseCodeAs(400)
				.setContentBytes("{\"error\":\"No such item\"}".getBytes())
				.addHeaders(new HttpHeaderImpl("Content-Type", "application/json; charset=iso-8859-1"))
				.thenBuild();
		
		assertThat(httpRequest.getResponse(), is(response));
	}
	
	@Test
	public void testEqualityWithResponse() throws Exception {
		HttpRequest httpRequest1 = HttpMessageBuilder.createNewHttpRequest()
				.setId("testId")
				.setMethodAs("POST")
				.setResourceAs("/test/resource")
				.setContentBytes("{\"test1\":\"value1\", \"test2\",\"value2\"}".getBytes())
				.setResponseAs(HttpMessageBuilder
						.createNewHttpResponse()
						.setResponseCodeAs(400)
						.setContentBytes("{\"error\":\"No such item\"}".getBytes())
						.addHeaders(new HttpHeaderImpl("Content-Type", "application/json; charset=iso-8859-1"))
						.thenBuild())
				.addHeaders(new HttpHeaderImpl("Content-Type", "application/json; charset=iso-8859-1"))
				.addHeaders(new HttpHeaderImpl("Authorization", "Basic dGVzdHVzZXI6dGVzdHBhc3N3b3Jk"))
				.addHeaders(new HttpHeaderImpl("X-Application-Role", Arrays.asList("User","Admin")))
				.thenBuild(), 
				httpRequest2 = HttpMessageBuilder.createNewHttpRequest()
				.setId("testId")
				.setMethodAs("POST")
				.setResourceAs("/test/resource")
				.setContentBytes("{\"test1\":\"value1\", \"test2\",\"value2\"}".getBytes())
				.addHeaders(new HttpHeaderImpl("Content-Type", "application/json; charset=iso-8859-1"))
				.addHeaders(new HttpHeaderImpl("Authorization", "Basic dGVzdHVzZXI6dGVzdHBhc3N3b3Jk"))
				.addHeaders(new HttpHeaderImpl("X-Application-Role", Arrays.asList("User","Admin")))
				.thenBuild();
		
		assertThat(httpRequest1, is(httpRequest2));
	}
	
	@Test
	public void testGetUriParameters() throws Exception {
		HttpRequest httpRequest1 = HttpMessageBuilder.createNewHttpRequest()
				.setId("testId1")
				.setMethodAs("POST")
				.setResourceAs("/test/resource?test1=value11&test1=value12&test2&test3=value3")
				.thenBuild(),
				httpRequest2 = HttpMessageBuilder.createNewHttpRequest()
				.setId("testId2")
				.setMethodAs("POST")
				.setResourceAs("/?test1=value11&test2=value12&test2&test3=value3")
				.thenBuild(),
				httpRequest3 = HttpMessageBuilder.createNewHttpRequest()
				.setId("testId3")
				.setMethodAs("POST")
				.setResourceAs("/test?")
				.thenBuild(),
				httpRequest4 = HttpMessageBuilder.createNewHttpRequest()
				.setId("testId4")
				.setMethodAs("POST")
				.setResourceAs("/test?")
				.thenBuild();

		
		assertThat(httpRequest1.getParameterValue("test1"), is(Arrays.asList("value11","value12")));
		assertThat(httpRequest1.hasParameter("test2"), is(Arrays.asList("value11","value12")));
		assertThat(httpRequest1.getParameterValue("test3"), is(Arrays.asList("value3")));

		assertThat(httpRequest2.getParameterValue("test1"), is(Arrays.asList("value11","value12")));
		assertThat(httpRequest2.hasParameter("test2"), is(Arrays.asList("value11","value12")));
		assertThat(httpRequest2.getParameterValue("test3"), is(Arrays.asList("value3")));

		assertThat(httpRequest3.getParameterValue("test") == null, is(true));
		assertThat(httpRequest3.hasParameter("test"), is("false"));

		assertThat(httpRequest4.getParameterValue("test") == null, is(true));
		assertThat(httpRequest4.hasParameter("test"), is("false"));
	}
	
	@Test(expected=BuilderException.class)
	public void testBuildResponseWithoutHttpCode() throws Exception {
		HttpMessageBuilder.createNewHttpResponse().thenBuild();
	}
	
	@Test(expected=BuilderException.class)
	public void testBuildResponseWithoutHttpCodeBelow200() throws Exception {
		HttpMessageBuilder.createNewHttpResponse().setResponseCodeAs(199);
	}

	@Test(expected=BuilderException.class)
	public void testBuildResponseWithoutHttpCodeAbove599() throws Exception {
		HttpMessageBuilder.createNewHttpResponse().setResponseCodeAs(600);
	}

	@Test
	public void testBuildResponseWithMinimumRequiredFields() throws Exception {
		HttpResponse httpResponse = HttpMessageBuilder.createNewHttpResponse()
				.setResponseCodeAs(200)
				.thenBuild();
		
		assertThat(httpResponse.getResponseCode(), is(200));
	}

	@Test
	public void testBuildResponseWithAllFields() throws Exception {
		HttpResponse httpResponse = HttpMessageBuilder.createNewHttpResponse()
				.setResponseCodeAs(400)
				.setContentBytes("{\"error\":\"No such item\"}".getBytes())
				.addHeaders(new HttpHeaderImpl("Content-Type", "application/json; charset=iso-8859-1"),
						new HttpHeaderImpl("Authorization", "Basic dGVzdHVzZXI6dGVzdHBhc3N3b3Jk"),
						new HttpHeaderImpl("X-Application-Role", Arrays.asList("User","Admin")))
				.thenBuild();
		
		assertThat(httpResponse.getResponseCode(), is(400));
		assertThat(httpResponse.getContentBytes(), is("{\"error\":\"No such item\"}".getBytes()));
		assertThat(httpResponse.getHeaders().size(), is(3));
	}

	@Test
	public void testCombineRequestAndResponse() throws Exception {
		HttpRequest httpRequest1 = HttpMessageBuilder.createNewHttpRequest()
				.setId("testId")
				.setMethodAs("POST")
				.setResourceAs("/test/resource")
				.setContentBytes("{\"test1\":\"value1\", \"test2\",\"value2\"}".getBytes())
				.addHeaders(new HttpHeaderImpl("Content-Type", "application/json; charset=iso-8859-1"))
				.addHeaders(new HttpHeaderImpl("Authorization", "Basic dGVzdHVzZXI6dGVzdHBhc3N3b3Jk"))
				.addHeaders(new HttpHeaderImpl("X-Application-Role", Arrays.asList("User","Admin")))
				.thenBuild(),
				httpRequest2 = HttpMessageBuilder.use(httpRequest1)
				.setResponseAs(HttpMessageBuilder.createNewHttpResponse()
				.setResponseCodeAs(201)
				.thenBuild())
				.thenBuild();
		
		assertThat(httpRequest1, is(httpRequest2));
		assertThat(httpRequest2.getResponse(), is(HttpMessageBuilder.createNewHttpResponse()
				.setResponseCodeAs(201)
				.thenBuild()));
	}
	
	@Test
	public void testResponseEqualsAndHashCode() throws Exception {
		HttpResponse httpResponse1 = HttpMessageBuilder.createNewHttpResponse()
				.setResponseCodeAs(400)
				.setContentBytes("{\"error\":\"No such item\"}".getBytes())
				.addHeaders(new HttpHeaderImpl("Content-Type", "application/json; charset=iso-8859-1"),
						new HttpHeaderImpl("Authorization", "Basic dGVzdHVzZXI6dGVzdHBhc3N3b3Jk"),
						new HttpHeaderImpl("X-Application-Role", Arrays.asList("User","Admin")))
				.thenBuild(),
				httpResponse2 = HttpMessageBuilder.createNewHttpResponse()
				.setResponseCodeAs(200)
				.thenBuild(),
				httpResponse3 = HttpMessageBuilder.createNewHttpResponse()
				.setResponseCodeAs(400)
				.setContentBytes("{\"error\":\"No such item\"}".getBytes())
				.addHeaders(new HttpHeaderImpl("Content-Type", "application/json; charset=iso-8859-1"),
						new HttpHeaderImpl("Authorization", "Basic dGVzdHVzZXI6dGVzdHBhc3N3b3Jk"),
						new HttpHeaderImpl("X-Application-Role", Arrays.asList("User","Admin")))
				.thenBuild(),
				httpResponse4 = HttpMessageBuilder.createNewHttpResponse()
						.setResponseCodeAs(200)
						.thenBuild();
		
		assertThat(httpResponse1, is(httpResponse3));
		assertThat(httpResponse1.hashCode(), is(httpResponse3.hashCode()));
		assertThat(httpResponse2, is(httpResponse4));
		assertThat(httpResponse1, is(not(httpResponse4)));
		assertThat(httpResponse1.hashCode(), is(not(httpResponse4.hashCode())));
		assertThat(httpResponse2, is(not(httpResponse3)));
		
	}
}

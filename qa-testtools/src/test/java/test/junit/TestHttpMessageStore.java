package test.junit;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.quakearts.tools.test.mockserver.model.HttpHeader;
import com.quakearts.tools.test.mockserver.model.HttpRequest;
import com.quakearts.tools.test.mockserver.model.HttpResponse;
import com.quakearts.tools.test.mockserver.model.impl.HttpHeaderImpl;
import com.quakearts.tools.test.mockserver.model.impl.HttpMessageBuilder;
import com.quakearts.tools.test.mockserver.store.exception.HttpMessageStoreException;
import com.quakearts.tools.test.mockserver.store.impl.MockServletHttpMessageStore;

public class TestHttpMessageStore {

	@BeforeClass
	public static void setup() throws Exception {
		File file = new File("http-messages");
		if(file.exists()) {
			if(file.listFiles() != null 
					&& file.listFiles().length > 0)
				for(File file2:file.listFiles())
					file2.delete();
			file.delete();
		}

		MockServletHttpMessageStore.getInstance();
		
		HttpRequest httpRequest1 = HttpMessageBuilder.createNewHttpRequest()
				.setId("testId1")
				.setMethodAs("POST")
				.setResourceAs("/test/resource/2")
				.setContentBytes("test=test&result=true".getBytes())
				.addHeaders(new HttpHeaderImpl("Content-Type","application/x-form-urlencoded"),
						new HttpHeaderImpl("Authorization", "Basic dGVzdHVzZXI6dGVzdHBhc3N3b3Jk"),
						new HttpHeaderImpl("X-Application-Role", Arrays.asList("Role1", "Role2", "Role3")))
				.thenBuild(),
				httpRequest2 = HttpMessageBuilder.createNewHttpRequest()
				.setId("testId2")
				.setMethodAs("PUT")
				.setResourceAs("/test/resource/1")
				.setContentBytes("test=test&result=true".getBytes())
				.addHeaders(new HttpHeaderImpl("Content-Type","application/x-form-urlencoded"),
						new HttpHeaderImpl("Authorization", "Basic dGVzdHVzZXI6dGVzdHBhc3N3b3Jk"),
						new HttpHeaderImpl("X-Application-Role", Arrays.asList("Role1", "Role2", "Role3")))
				.thenBuild(),
				httpRequest3 = HttpMessageBuilder.createNewHttpRequest()
				.setId("testId3")
				.setMethodAs("GET")
				.setResourceAs("/test/resource?test=test&result=true")
				.addHeaders(new HttpHeaderImpl("Content-Type","application/x-form-urlencoded"),
						new HttpHeaderImpl("Authorization", "Basic dGVzdHVzZXI6dGVzdHBhc3N3b3Jk"),
						new HttpHeaderImpl("X-Application-Role", Arrays.asList("Role1", "Role2", "Role3")))
				.thenBuild();
		
		saveObject(httpRequest1);
		saveObject(httpRequest2);
		saveObject(httpRequest3);
	}
		
	private static void saveObject(HttpRequest httpRequest) throws Exception {
		try(FileOutputStream fos = new FileOutputStream("http-messages/"+httpRequest.getId()+".mock");){
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(httpRequest);
		}
	}
	
	@Test
	public void testIterator() {
		List<String> savedIds = Arrays.asList("testId1","testId2","testId3");
		int count = 0;
		
		Iterator<HttpRequest> requests = MockServletHttpMessageStore.getInstance().iterator();
		while (requests.hasNext()) {
			HttpRequest httpRequest = (HttpRequest) requests.next();
			if(savedIds.contains(httpRequest.getId()))
				count++;
		}
		
		assertThat(count, is(3));
	}

	@Test(expected=HttpMessageStoreException.class)
	public void testStorageOfNonSerializableRequest() throws Exception {
		MockServletHttpMessageStore.getInstance().storeRequest(new HttpRequest() {
			
			@Override
			public Collection<HttpHeader> getHeaders() {
				return null;
			}
			
			@Override
			public String getHeaderValue(String name) {
				return null;
			}
			
			@Override
			public String getContentEncoding() {
				return null;
			}
			
			@Override
			public String getContent() throws UnsupportedEncodingException {
				return null;
			}
			
			@Override
			public boolean hasParameter(String name) {
				return false;
			}
			
			@Override
			public HttpResponse getResponse() {
				return null;
			}
			
			@Override
			public String getResource() {
				return null;
			}
			
			@Override
			public List<String> getParameterValue(String name) {
				return null;
			}
			
			@Override
			public String getMethod() {
				return null;
			}
			
			@Override
			public String getId() {
				return null;
			}
			
			@Override
			public byte[] getContentBytes() {
				return null;
			}
		});
	}
	
	@Test
	public void testStorageOfRequest() throws Exception {
		HttpRequest httpRequest1 = HttpMessageBuilder.createNewHttpRequest()
				.setId("testId4")
				.setMethodAs("OPTIONS")
				.setResourceAs("/test/resource/2")
				.setContentBytes("test=test&result=true".getBytes())
				.addHeaders(new HttpHeaderImpl("Content-Type","application/x-form-urlencoded"),
						new HttpHeaderImpl("Authorization", "Basic dGVzdHVzZXI6dGVzdHBhc3N3b3Jk"),
						new HttpHeaderImpl("X-Application-Role", Arrays.asList("Role1", "Role2", "Role3")))
				.thenBuild();
		
		MockServletHttpMessageStore.getInstance().storeRequest(httpRequest1);
		
		assertThat(new File("http-messages/testId4.mock").exists(), is(true));
	}
	
	@Test
	public void testFindRequestById() throws Exception {
		HttpRequest httpRequest1 = MockServletHttpMessageStore.getInstance().findRequestIdentifiedBy("testId1"),
				httpRequest2 = HttpMessageBuilder.createNewHttpRequest()
				.setId("testId1")
				.setMethodAs("POST")
				.setResourceAs("/test/resource/2")
				.setContentBytes("test=test&result=true".getBytes())
				.addHeaders(new HttpHeaderImpl("Content-Type","application/x-form-urlencoded"),
						new HttpHeaderImpl("Authorization", "Basic dGVzdHVzZXI6dGVzdHBhc3N3b3Jk"),
						new HttpHeaderImpl("X-Application-Role", Arrays.asList("Role1", "Role2", "Role3")))
				.thenBuild();
		
		assertThat(httpRequest1, is(httpRequest2));	
	}
	
	@Test
	public void testUsingQuery() throws Exception {
		HttpRequest[] httpRequests = MockServletHttpMessageStore.getInstance()
				.findRequestsMatching((httpRequest) -> {
					return httpRequest.getMethod().equals("GET") || httpRequest.getMethod().equals("POST");
				}).thenList();
		
		assertThat(httpRequests!=null, is(true));
		assertThat(httpRequests.length, is(2));
		
		for(HttpRequest httpRequest:httpRequests) {
			if(!httpRequest.getId().equals("testId1")
					&&!httpRequest.getId().equals("testId3"))
				fail("Did not return an appropriate value: "+httpRequest.getId());
		}
		
		HttpRequest foundHttpRequest = MockServletHttpMessageStore.getInstance()
				.findRequestsMatching((httpRequest) -> {
					return httpRequest.getMethod().equals("PUT");
				}).thenFetchOne();
		
		assertThat(foundHttpRequest!=null, is(true));
		assertThat(foundHttpRequest.getId(), is("testId2"));
		
		foundHttpRequest = MockServletHttpMessageStore.getInstance()
				.findRequestsMatching((httpRequest) -> {
					return httpRequest.getMethod().equals("TRACE");
				}).thenFetchOne();
		
		assertThat(foundHttpRequest==null, is(true));
		
		 httpRequests = MockServletHttpMessageStore.getInstance()
					.findRequestsMatching((httpRequest) -> {
						return httpRequest.getMethod().equals("HEAD");
					}).thenList();
			
			assertThat(httpRequests!=null, is(true));
			assertThat(httpRequests.length, is(0));
	}
}

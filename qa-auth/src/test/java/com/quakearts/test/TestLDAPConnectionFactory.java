package com.quakearts.test;

import static org.junit.Assert.*;

import java.util.Arrays;

import static org.hamcrest.core.Is.*;

import com.novell.ldap.LDAPException;
import com.quakearts.webapp.security.ldap.LDAPConnection;
import com.quakearts.webapp.security.ldap.LDAPConnectionFactory;
import com.quakearts.webapp.security.ldap.LDAPObject;

public class TestLDAPConnectionFactory implements LDAPConnectionFactory<LDAPException> {

	private boolean throwErrorOnConnect;
	private boolean throwErrorOnDisconnect;
	private String password;
	private String dn;
	private String searchDn;
	private LDAPObject results;
	private String host;
	private int port;
	private String base;
	private String filter;
	private String[] attrs;
	
	public enum Mode {
		LOAD,
		AUTHENTICATE;
	}
	
	private Mode mode = Mode.LOAD;
	private String searchDnPassWord;
	private boolean throwErrorOnCompare;
		
	public TestLDAPConnectionFactory setThrowErrorOnConnectAs(boolean throwErrorOnConnect) {
		this.throwErrorOnConnect = throwErrorOnConnect;
		return this;
	}

	public TestLDAPConnectionFactory setThrowErrorOnDisconnectAs(boolean throwErrorOnDisconnect) {
		this.throwErrorOnDisconnect = throwErrorOnDisconnect;
		return this;
	}

	public TestLDAPConnectionFactory setThrowErrorOnCompareAs(boolean throwErrorOnCompare) {
		this.throwErrorOnCompare = throwErrorOnCompare;
		return this;
	}
	
	public TestLDAPConnectionFactory setDNAs(String dn) {
		this.dn = dn;
		return this;
	}

	public TestLDAPConnectionFactory setSearchDNAs(String baseDN) {
		this.searchDn = baseDN;
		return this;
	}
	
	public TestLDAPConnectionFactory setSearchDNPasswordAs(String searchDnPassWord) {
		this.searchDnPassWord = searchDnPassWord;
		return this;
	}

	
	public TestLDAPConnectionFactory setPasswdAs(String passwd) {
		this.password = passwd;
		return this;
	}

	public TestLDAPConnectionFactory setResultsAs(LDAPObject results) {
		this.results = results;
		return this;
	}

	public TestLDAPConnectionFactory setHostAs(String host) {
		this.host = host;
		return this;
	}

	public TestLDAPConnectionFactory setPortAs(int port) {
		this.port = port;
		return this;
	}

	public TestLDAPConnectionFactory setModeAs(Mode mode) {
		this.mode = mode;
		return this;
	}
	
	public TestLDAPConnectionFactory setBaseAs(String base) {
		this.base = base;
		return this;
	}
	
	public TestLDAPConnectionFactory setFilterAs(String filter) {
		this.filter = filter;
		return this;
	}
	
	public TestLDAPConnectionFactory setAttrsAs(String[] attrs) {
		this.attrs = attrs;
		return this;
	}
	
	@Override
	public LDAPConnection<LDAPException> createConnection(boolean usessl) {
		return new LDAPConnection<LDAPException>() {
			@Override
			public void connect(String host, int port) throws LDAPException {
				assertThat(host, is(TestLDAPConnectionFactory.this.host));
				assertThat(port, is(TestLDAPConnectionFactory.this.port));
				if(throwErrorOnConnect)
					throw new LDAPException();
			}
			
			@Override
			public void bind(String dn, byte[] passwd) throws LDAPException {
				if(mode == Mode.AUTHENTICATE) {
					assertThat(dn, is(TestLDAPConnectionFactory.this.dn));
					if(!Arrays.equals(passwd, TestLDAPConnectionFactory.this.password.getBytes()))
						throw new LDAPException();
				} else {
					assertThat(dn, is(TestLDAPConnectionFactory.this.searchDn));
					assertThat(passwd, is(TestLDAPConnectionFactory.this.searchDnPassWord.getBytes()));
				}
			}
			
			@Override
			public LDAPObject search(String base, String filter, String[] attrs, boolean typesOnly)
					throws LDAPException {
				assertThat(base, is(TestLDAPConnectionFactory.this.base));
				assertThat(filter, is(TestLDAPConnectionFactory.this.filter));
				assertThat(attrs, is(TestLDAPConnectionFactory.this.attrs));
				return results;
			}
			
			@Override
			public boolean compare(String dn, String[] attr) throws LDAPException {
				if(throwErrorOnCompare)
					throw new LDAPException();
				
				assertThat(dn, is(TestLDAPConnectionFactory.this.dn));
				return attr[1].equals(TestLDAPConnectionFactory.this.password);
			}
			
			@Override
			public void disconnect() throws LDAPException {
				if(mode==Mode.LOAD)
					mode = Mode.AUTHENTICATE;
				else if(throwErrorOnDisconnect)
					throw new LDAPException();
			}
		};
	}
}

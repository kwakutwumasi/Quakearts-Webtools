package com.quakearts.webapp.security.rest.callback;

import javax.security.auth.callback.Callback;

public class TokenCallback implements Callback {

	public TokenCallback() {
	}

	protected byte[] tokenData;

	public byte[] getTokenData() {
		return tokenData;
	}

	public void setTokenData(byte[] tokenData) {
		this.tokenData = tokenData;
	}
}

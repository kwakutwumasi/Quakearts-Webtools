package com.quakearts.webapp.security.jwt.impl;

import java.util.Date;
import java.util.Iterator;

import com.quakearts.webapp.security.jwt.JWTClaims;
import com.quakearts.webapp.security.jwt.internal.json.JsonObject.Member;

public class JWTClaimsImpl extends JWTJsonObjectBase implements JWTClaims {

	public JWTClaimsImpl() {
		jsonObject.set(IAT, new Date().getTime() / 1000);
	}

	@Override
	public JWTClaims setSubject(String subject) {
		jsonObject.set(SUB, subject);
		return this;
	}

	@Override
	public String getSubject() {
		try {
			if (jsonObject.get(SUB) != null)
				return jsonObject.get(SUB).asString();
		} catch (UnsupportedOperationException e) {
		}
	
		return null;
	}

	@Override
	public JWTClaims setIssuer(String issuer) {
		jsonObject.set(ISS, issuer);
		return this;
	}

	@Override
	public String getIssuer() {
		try {
			if (jsonObject.get(ISS) != null)
				return jsonObject.get(ISS).asString();
		} catch (UnsupportedOperationException e) {
		}
	
		return null;
	}

	@Override
	public JWTClaims setAudience(String audience) {
		jsonObject.set(AUD, audience);
		return this;
	}

	@Override
	public String getAudience() {
		try {
			if (jsonObject.get(AUD) != null)
				return jsonObject.get(AUD).asString();
		} catch (UnsupportedOperationException e) {
		}
	
		return null;
	}

	@Override
	public JWTClaims setExpiry(long time) {
		jsonObject.add(EXP, time);
		return this;
	}

	@Override
	public long getExpiry() {
		try {
			if (jsonObject.get(EXP) != null)
				return jsonObject.get(EXP).asLong();
			
		} catch (UnsupportedOperationException e) {
		}
	
		return 0;
	}

	@Override
	public JWTClaims setNotBefore(long time) {
		jsonObject.add(NBF, time);
		return this;
	}

	@Override
	public long getNotBefore() {
		try {
			if (jsonObject.get(NBF) != null)
				return jsonObject.get(NBF).asLong();
		} catch (UnsupportedOperationException e) {
		}
	
		return 0;
	}

	@Override
	public long getIssuedAt() {
		try {
			if (jsonObject.get(IAT) != null)
				return jsonObject.get(IAT).asLong();
		} catch (UnsupportedOperationException e) {
		}
	
		return 0;
	}

	@Override
	public JWTClaims addPrivateClaim(String name, String value) {
		if (name.equals(SUB) || name.equals(AUD) || name.equals(EXP) || name.equals(IAT) || name.equals(ISS)
				|| name.equals(NBF))
			throw new UnsupportedOperationException("Invalid private claim:" + name);

		jsonObject.set(name, value);
		return this;
	}

	@Override
	public String getPrivateClaim(String name) {
		try {
			return jsonObject.get(name).asString();
		} catch (UnsupportedOperationException e) {
		}
		return null;
	}

	@Override
	public Iterator<JWTClaims.Claim> iterator() {
		return new ClaimsIterator(jsonObject.iterator());
	}
}

class ClaimsIterator implements Iterator<JWTClaims.Claim> {

	Iterator<Member> wrappedIterator;
	
	public ClaimsIterator(Iterator<Member> wrappedIterator) {
		this.wrappedIterator = wrappedIterator;
	}

	@Override
	public boolean hasNext() {
		return wrappedIterator.hasNext();
	}

	@Override
	public JWTClaims.Claim next() {
		Member next = wrappedIterator.next();;
		try {
			return new JWTClaims.Claim(next.getName(), next.getValue().asString());
		} catch (UnsupportedOperationException e) {
			return new JWTClaims.Claim(next.getName(), next.getValue().toString());
		}
	}
	
}
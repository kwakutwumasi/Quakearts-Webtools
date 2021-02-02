/*******************************************************************************
* Copyright (C) 2016 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.webapp.security.jwt.impl;

import java.util.Iterator;

import com.quakearts.webapp.security.jwt.JWTClaims;
import com.quakearts.webapp.security.jwt.internal.json.JsonObject.Member;
import static com.quakearts.webapp.security.jwt.RegisteredNames.*;

public class JWTClaimsImpl extends JWTJsonObjectBase implements JWTClaims {

	public JWTClaimsImpl() {
		jsonObject.set(IAT, System.currentTimeMillis() / 1000);
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
			return null;
		} catch (UnsupportedOperationException e) {
			return null;
		}	
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
			return null;
		} catch (UnsupportedOperationException e) {
			return null;
		}	
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
			return null;
		} catch (UnsupportedOperationException e) {
			return null;
		}	
	}

	@Override
	public JWTClaims setExpiry(long time) {
		jsonObject.set(EXP, time);
		return this;
	}

	@Override
	public long getExpiry() {
		try {
			if (jsonObject.get(EXP) != null)
				return jsonObject.get(EXP).asLong();
			return 0;
		} catch (UnsupportedOperationException e) {
			return 0;
		}	
	}

	@Override
	public JWTClaims setNotBefore(long time) {
		jsonObject.set(NBF, time);
		return this;
	}

	@Override
	public long getNotBefore() {
		try {
			if (jsonObject.get(NBF) != null)
				return jsonObject.get(NBF).asLong();
			return 0;
		} catch (UnsupportedOperationException e) {
			return 0;
		}	
	}

	@Override
	public long getIssuedAt() {
		try {
			if (jsonObject.get(IAT) != null)
				return jsonObject.get(IAT).asLong();
			return 0;
		} catch (UnsupportedOperationException e) {
			return 0;
		}	
	}

	@Override
	public JWTClaims setIssuedAt(long time) {
		jsonObject.set(IAT, time);
		return this;
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
		if (name.equals(SUB) || name.equals(AUD) || name.equals(EXP) || name.equals(IAT) || name.equals(ISS)
				|| name.equals(NBF))
			throw new UnsupportedOperationException("Invalid private claim:" + name);

		try {
			if(jsonObject.get(name) != null)
				return jsonObject.get(name).asString();
			return null;
		} catch (UnsupportedOperationException e) {
			return null;
		}
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
		Member next = wrappedIterator.next();
		try {
			return new JWTClaims.Claim(next.getName(), next.getValue().asString());
		} catch (UnsupportedOperationException e) {
			return new JWTClaims.Claim(next.getName(), next.getValue().toString());
		}
	}
	
}
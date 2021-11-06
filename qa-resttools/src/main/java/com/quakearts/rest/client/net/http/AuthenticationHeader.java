/*******************************************************************************
* Copyright (C) 2021 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.rest.client.net.http;

import java.util.Collections;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Set;

import com.quakearts.rest.client.net.HeaderParser;
import com.quakearts.rest.client.net.MessageHeader;

public class AuthenticationHeader {

	MessageHeader response;
	HeaderParser preferredParser;
	String preferredRaw;

	public String toString() {
		return "AuthenticationHeader: prefer " + preferredRaw;
	}

	String headerName;

	public AuthenticationHeader(String headerName, MessageHeader response) {
		this(headerName, response, Collections.emptySet());
	}

	public AuthenticationHeader(String headerName, MessageHeader response, Set<String> disabledSchemes) {
		this.response = response;
		this.headerName = headerName;
		this.schemes = new HashMap<>();
		parse(disabledSchemes);
	}

	static class SchemeMapValue {
		SchemeMapValue(HeaderParser headerParser, String rawIn) {
			raw = rawIn;
			parser = headerParser;
		}

		String raw;
		HeaderParser parser;
	}

	HashMap<String, SchemeMapValue> schemes;

	private void parse(Set<String> disabledSchemes) {
		Iterator<String> iter = response.multiValueIterator(headerName);
		findSchemes(disabledSchemes, iter);

		SchemeMapValue schemaValue = schemes.get("digest");
		if (schemaValue == null) {
			schemaValue = schemes.get("basic");
		}

		if (schemaValue != null) {
			preferredParser = schemaValue.parser;
			preferredRaw = schemaValue.raw;
		}
	}

	private void findSchemes(Set<String> disabledSchemes, Iterator<String> iter) {
		while (iter.hasNext()) {
			String raw = iter.next();
			HeaderParser headerParser = new HeaderParser(raw);
			Iterator<String> keys = headerParser.keys();
			int index;
			int lastSchemeIndex;
			for (index = 0, lastSchemeIndex = -1; keys.hasNext(); index++) {
				keys.next();
				if (headerParser.findValue(index) == null) {
					findNextScheme(disabledSchemes, raw, headerParser, index, lastSchemeIndex);
					lastSchemeIndex = index;
				}
			}
			
			if (index > lastSchemeIndex) {
				HeaderParser subHeaderParser = headerParser.subsequence(lastSchemeIndex, index);
				String scheme = subHeaderParser.findKey(0);
				if (!disabledSchemes.contains(scheme))
					schemes.put(scheme, new SchemeMapValue(subHeaderParser, raw));
			}
		}
	}

	private void findNextScheme(Set<String> disabledSchemes, String raw, HeaderParser headerParser, int index,
			int lastSchemeIndex) {
		if (lastSchemeIndex != -1) {
			HeaderParser subHeaderParser = headerParser.subsequence(lastSchemeIndex, index);
			String scheme = subHeaderParser.findKey(0);
			if (!disabledSchemes.contains(scheme))
				schemes.put(scheme, new SchemeMapValue(subHeaderParser, raw));
		}
	}

	public HeaderParser headerParser() {
		return preferredParser;
	}

	public String scheme() {
		if (preferredParser != null) {
			return preferredParser.findKey(0);
		} else {
			return null;
		}
	}

	public String raw() {
		return preferredRaw;
	}

	public boolean hasPreferredParserPresent() {
		return preferredParser != null;
	}
}

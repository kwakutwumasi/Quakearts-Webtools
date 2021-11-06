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
package com.quakearts.rest.client.net.https;

import java.net.InetAddress;
import java.net.UnknownHostException;

import java.security.Principal;
import java.security.cert.*;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.StringTokenizer;

import org.slf4j.Logger;

import com.quakearts.rest.client.net.IPAddressUtil;
import com.quakearts.rest.client.net.RegisteredDomainProducer.RegisteredDomain;
import com.quakearts.rest.client.net.http.HttpURLConnectionImpl;

import javax.naming.InvalidNameException;
import javax.naming.ldap.LdapName;
import javax.net.ssl.SNIHostName;

public class HostnameChecker {

	private static final Logger logger = HttpURLConnectionImpl.getHttpLogger();

	public static final byte TYPE_TLS = 1;
	private static final HostnameChecker INSTANCE_TLS = new HostnameChecker(TYPE_TLS);

	public static final byte TYPE_LDAP = 2;
	private static final HostnameChecker INSTANCE_LDAP = new HostnameChecker(TYPE_LDAP);

	private static final int ALTNAME_DNS = 2;
	private static final int ALTNAME_IP = 7;

	private final byte checkType;

	private HostnameChecker(byte checkType) {
		this.checkType = checkType;
	}

	public static HostnameChecker getInstance(byte checkType) {
		if (checkType == TYPE_TLS) {
			return INSTANCE_TLS;
		} else if (checkType == TYPE_LDAP) {
			return INSTANCE_LDAP;
		}
		throw new IllegalArgumentException("Unknown check type: " + checkType);
	}

	public void match(String expectedName, X509Certificate cert, boolean chainsToPublicCA) throws CertificateException {
		if (isIpAddress(expectedName)) {
			matchIP(expectedName, cert);
		} else {
			matchDNS(expectedName, cert, chainsToPublicCA);
		}
	}

	public void match(String expectedName, X509Certificate cert) throws CertificateException {
		match(expectedName, cert, false);
	}

	public static boolean match(String expectedName, Principal principal) {
		String hostName = getServerName(principal);
		return (expectedName.equalsIgnoreCase(hostName));
	}

	public static String getServerName(Principal principal) {
		if (principal == null) {
			return null;
		}
		String hostName = null;
		try {
			PrincipalName princName = new PrincipalName(principal.getName());
			String[] nameParts = princName.getNameStrings();
			if (nameParts.length >= 2) {
				hostName = nameParts[1];
			}
		} catch (Exception e) {
			// Do nothing
		}
		return hostName;
	}

	private static boolean isIpAddress(String name) {
		return (IPAddressUtil.isIPv4LiteralAddress(name) || IPAddressUtil.isIPv6LiteralAddress(name));
	}

	private static void matchIP(String expectedIP, X509Certificate cert) throws CertificateException {
		Collection<List<?>> subjAltNames = cert.getSubjectAlternativeNames();
		if (subjAltNames == null) {
			throw new CertificateException("No subject alternative names present");
		}
		for (List<?> next : subjAltNames) {
			if (((Integer) next.get(0)).intValue() == ALTNAME_IP) {
				String ipAddress = (String) next.get(1);
				if (expectedIP.equalsIgnoreCase(ipAddress)) {
					return;
				} else {
					try {
						if (InetAddress.getByName(expectedIP).equals(InetAddress.getByName(ipAddress))) {
							return;
						}
					} catch (UnknownHostException | SecurityException e) {
						// Do nothing
					}
				}
			}
		}
		throw new CertificateException(
				"No subject alternative " + "names matching " + "IP address " + expectedIP + " found");
	}

	private void matchDNS(String expectedName, X509Certificate cert, boolean chainsToPublicCA)
			throws CertificateException {
		try {
			new SNIHostName(expectedName);
		} catch (IllegalArgumentException iae) {
			throw new CertificateException("Illegal given domain name: " + expectedName, iae);
		}

		Collection<List<?>> subjAltNames = cert.getSubjectAlternativeNames();
		if (subjAltNames != null) {
			boolean foundDNS = false;
			for (List<?> next : subjAltNames) {
				if (((Integer) next.get(0)).intValue() == ALTNAME_DNS) {
					foundDNS = true;
					String dnsName = (String) next.get(1);
					if (isMatched(expectedName, dnsName, chainsToPublicCA)) {
						return;
					}
				}
			}
			if (foundDNS) {
				throw new CertificateException(
						"No subject alternative DNS name matching " + expectedName + " found.");
			}
		}

		Optional<String> commonName;
		try {
			commonName = new LdapName(cert.getSubjectX500Principal().getName()).getRdns().stream()
					.filter(rdn -> rdn.getType().equalsIgnoreCase("cn")).map(rdn -> rdn.getValue().toString())
					.findFirst();
			if (commonName.isPresent()) {
				String cname = commonName.get();
				if (!Normalizer.isNormalized(cname, Normalizer.Form.NFKC)) {
					throw new CertificateException("Not a formal name " + cname);
				}
				if (isMatched(expectedName, cname, chainsToPublicCA)) {
					return;
				}
			}
		} catch (InvalidNameException e) {
			// Do nothing
		}
		String msg = "No name matching " + expectedName + " found";
		throw new CertificateException(msg);
	}

	private boolean isMatched(String name, String template, boolean chainsToPublicCA) {
		if (hasIllegalWildcard(name, template, chainsToPublicCA)) {
			return false;
		}

		try {
			new SNIHostName(template.replace('*', 'z'));
		} catch (IllegalArgumentException iae) {
			return false;
		}

		if (checkType == TYPE_TLS) {
			return matchAllWildcards(name, template);
		} else if (checkType == TYPE_LDAP) {
			return matchLeftmostWildcard(name, template);
		} else {
			return false;
		}
	}

	private static boolean hasIllegalWildcard(String domain, String template, boolean chainsToPublicCA) {
		if (template.equals("*") || template.equals("*.")) {
			if (logger.isDebugEnabled()) {
				logger.debug("Certificate domain name has illegal single " + "wildcard character: {}", template);
			}
			return true;
		}

		int lastWildcardIndex = template.lastIndexOf("*");

		if (lastWildcardIndex == -1) {
			return false;
		}

		String afterWildcard = template.substring(lastWildcardIndex);
		int firstDotIndex = afterWildcard.indexOf(".");

		if (firstDotIndex == -1) {
			if (logger.isDebugEnabled()) {
				logger.debug("Certificate domain name has illegal wildcard, " + "no dot after wildcard character: {}",
						template);
			}
			return true;
		}

		if (!chainsToPublicCA) {
			return false;
		}
		
		Optional<RegisteredDomain> registeredDomainOptional = RegisteredDomain.from(domain)
				.filter(d -> d.type() == RegisteredDomain.Type.ICANN);

		if (registeredDomainOptional.isPresent()) {
			String wDomain = afterWildcard.substring(firstDotIndex + 1);
			if (registeredDomainOptional.get().publicSuffix().equalsIgnoreCase(wDomain)) {
				if (logger.isDebugEnabled()) {
					logger.debug("Certificate domain name has illegal " + "wildcard for public suffix: {}", template);
				}
				return true;
			}
		}

		return false;
	}

	private static boolean matchAllWildcards(String name, String template) {
		name = name.toLowerCase(Locale.ENGLISH);
		template = template.toLowerCase(Locale.ENGLISH);
		StringTokenizer nameSt = new StringTokenizer(name, ".");
		StringTokenizer templateSt = new StringTokenizer(template, ".");

		if (nameSt.countTokens() != templateSt.countTokens()) {
			return false;
		}

		while (nameSt.hasMoreTokens()) {
			if (!matchWildCards(nameSt.nextToken(), templateSt.nextToken())) {
				return false;
			}
		}
		return true;
	}

	private static boolean matchLeftmostWildcard(String name, String template) {
		name = name.toLowerCase(Locale.ENGLISH);
		template = template.toLowerCase(Locale.ENGLISH);

		int templateIdx = template.indexOf(".");
		int nameIdx = name.indexOf(".");

		if (templateIdx == -1)
			templateIdx = template.length();
		if (nameIdx == -1)
			nameIdx = name.length();

		if (matchWildCards(name.substring(0, nameIdx), template.substring(0, templateIdx))) {

			return template.substring(templateIdx).equals(name.substring(nameIdx));
		} else {
			return false;
		}
	}

	private static boolean matchWildCards(String name, String template) {

		int wildcardIdx = template.indexOf("*");
		if (wildcardIdx == -1)
			return name.equals(template);

		boolean isBeginning = true;
		String beforeWildcard = "";
		String afterWildcard = template;

		while (wildcardIdx != -1) {
			beforeWildcard = afterWildcard.substring(0, wildcardIdx);
			afterWildcard = afterWildcard.substring(wildcardIdx + 1);

			int beforeStartIdx = name.indexOf(beforeWildcard);
			if ((beforeStartIdx == -1) || (isBeginning && beforeStartIdx != 0)) {
				return false;
			}
			isBeginning = false;

			name = name.substring(beforeStartIdx + beforeWildcard.length());
			wildcardIdx = afterWildcard.indexOf("*");
		}
		return name.endsWith(afterWildcard);
	}
}

class PrincipalName {

	public static final char NAME_COMPONENT_SEPARATOR = '/';
	public static final char NAME_REALM_SEPARATOR = '@';
	private String[] nameStrings;

	public PrincipalName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Null name not allowed");
		}
		String[] nameParts = parseName(name);
		validateNameStrings(nameParts);

		if (nameParts.length >= 2) {
			String hostName = nameParts[1];
			try {
				String canonicalized = (InetAddress.getByName(hostName)).getCanonicalHostName();
				if (canonicalized.toLowerCase(Locale.ENGLISH).startsWith(hostName.toLowerCase(Locale.ENGLISH) + ".")) {
					hostName = canonicalized;
				}
			} catch (UnknownHostException | SecurityException e) {
				// Do nothing
			}
			if (hostName.endsWith(".")) {
				hostName = hostName.substring(0, hostName.length() - 1);
			}
			nameParts[1] = hostName.toLowerCase(Locale.ENGLISH);
		}
		nameStrings = nameParts;
	}

	public String[] getNameStrings() {
		return nameStrings;
	}

	private static String[] parseName(String name) {

		List<String> tempStrings = new ArrayList<>();
		String temp = name;
		int i = 0;
		int componentStart = 0;
		String component;

		while (i < temp.length()) {
			if (temp.charAt(i) == NAME_COMPONENT_SEPARATOR) {
				if (i > 0 && temp.charAt(i - 1) == '\\') {
					temp = temp.substring(0, i - 1) + temp.substring(i, temp.length());
					continue;
				} else {
					if (componentStart <= i) {
						component = temp.substring(componentStart, i);
						tempStrings.add(component);
					}
					componentStart = i + 1;
				}
			} else {
				if (temp.charAt(i) == NAME_REALM_SEPARATOR) {
					if (i > 0 && temp.charAt(i - 1) == '\\') {
						temp = temp.substring(0, i - 1) + temp.substring(i, temp.length());
						continue;
					} else {
						if (componentStart < i) {
							component = temp.substring(componentStart, i);
							tempStrings.add(component);
						}
						componentStart = i + 1;
						break;
					}
				}
			}
			i++;
		}

		if (i == temp.length()) {
			component = temp.substring(componentStart, i);
			tempStrings.add(component);
		}

		String[] result = new String[tempStrings.size()];
		tempStrings.toArray(result);
		return result;
	}

	private static void validateNameStrings(String[] ns) {
		if (ns == null) {
			throw new IllegalArgumentException("Null nameStrings not allowed");
		}
		if (ns.length == 0) {
			throw new IllegalArgumentException("Empty nameStrings not allowed");
		}
		for (String s : ns) {
			if (s == null) {
				throw new IllegalArgumentException("Null nameString not allowed");
			}
			if (s.isEmpty()) {
				throw new IllegalArgumentException("Empty nameString not allowed");
			}
		}
	}
}
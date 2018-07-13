package com.quakearts.webapp.security.rest.cache;

import javax.security.auth.Subject;

/**Interface for providing a caching mechanism for authenticated subject
 * Caching significantly speeds up the time required to authenticate users
 * especially when the underlying mechanism is slow, or in instances where the
 * Subject needs to be authenticated for each call (Web Bearer and Basic Authentication)
 * @author kwakutwumasi-afriyie
 *
 */
public interface AuthenticationCacheService {
	/**Save the authenticated subject with the key
	 * @param key the key to use in retrieving the subject
	 * @param subject the authenticated subject
	 */
	void saveSubject(String key, Subject subject);
	/**Retrieve the {@linkplain Subject} identified by the key, if any
	 * @param key the key to use in retrieving the subject
	 * @return the Subject, if any
	 */
	Subject retrieveSubject(String key);
	/**Invalidate the {@linkplain Subject} identified by this key, if any
	 * @param key the key to use in retrieving the subject
	 */
	void invalidateSubject(String key);
	/**Convert authentication data such as a password or authentication signature into
	 * a key for retrieval
	 * @param identity the subject identifier, such as a username
	 * @param authenticationData the data used to authenticate the user, such as a token or a password
	 * @param contextName the name if the authentication context.
	 * @return the generated key
	 */
	String getKey(String identity, String authenticationData, String contextName);
}

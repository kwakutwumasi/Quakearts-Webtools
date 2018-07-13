package com.quakearts.security.cryptography.factory;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.crypto.NoSuchPaddingException;

import com.quakearts.security.cryptography.CryptoResource;
import com.quakearts.security.cryptography.exception.KeyProviderException;

public interface CryptoService {

	/**Obtain a cryptographic resource using the supplied parameters
	 * @param instance the cryptographic algorithm
	 * @param keyProviderClass the key provider class
	 * @param properties properties to pass to the key provider class
	 * @param name the name to give the resource
	 * @return a cryptographic resource
	 * @throws ClassNotFoundException the key provider class could not be found
	 * @throws InstantiationException the key provider class could not be instantiated
	 * @throws IllegalAccessException the key provider class could not be instantiated
	 * @throws KeyProviderException the key provider class could not be instantiated
	 * @throws NoSuchAlgorithmException the cryptographic algorithm is invalid
	 * @throws NoSuchPaddingException the cryptographic algorithm is invalid
	 */
	CryptoResource getCryptoResource(String instance, String keyProviderClass, Map<Object, Object> properties,
			String name) throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			KeyProviderException, NoSuchAlgorithmException, NoSuchPaddingException;

	/**Obtain a cryptographic resource with the given name
	 * @param name the name to give the resource
	 * @return a cryptographic resource
	 * @throws IOException the key provider properties could not be loaded
	 * @throws ClassNotFoundException the key provider class could not be found
	 * @throws InstantiationException the key provider class could not be instantiated
	 * @throws IllegalAccessException the key provider class could not be instantiated
	 * @throws KeyProviderException the key provider class could not be instantiated
	 * @throws NoSuchAlgorithmException the cryptographic algorithm is invalid
	 * @throws NoSuchPaddingException the cryptographic algorithm is invalid
	 */
	CryptoResource getCryptoResource(String name) throws IOException, ClassNotFoundException, InstantiationException,
			IllegalAccessException, NoSuchAlgorithmException, NoSuchPaddingException, KeyProviderException;

}
package com.quakearts.appbase.internal.properties;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public interface AppBasePropertiesLoader {

	/**Retrieve a list of {@linkplain ConfigurationPropertyMap} objects within the given folder
	 * @param fileLocation the name of the search folder relative to the application root
	 * @param fileSuffix the suffix of files to scan and load
	 * @param appName the name of the module to be displayed when displaying any processing error
	 * @return a list of {@linkplain ConfigurationPropertyMap} objects
	 */
	List<ConfigurationPropertyMap> getAllConfigurationProperties(String fileLocation, String fileSuffix,
			String appName);

	/**Retrieve a list of {@linkplain File} objects within the given folder
	 * @param fileLocation the name of the search folder relative to the application root
	 * @param fileSuffix the suffix of files to scan and load
	 * @param appName the name of the module to be displayed when displaying any processing error
	 * @return a list of {@linkplain File} objects
	 */
	List<File> listConfigurationFiles(String fileLocation, String fileSuffix, String appName);

	/**Convert environment variables into a {@linkplain ConfigurationPropertyMap}.
	 * <br /><br />
	 * <b>Note</b>: Windows OS's and some other environments are not capable of storing variables in 
	 * a case sensitive manner. Implementations may return the variables in upper case format. This 
	 * leads to a situation where CamelCase variables cannot be represented. The solution is to split
	 * CamelCase variables by the '_' character. Ex.: camelCase becomes CAMEL_CASE. Such variables 
	 * are auto detected and changed to CamelCase.
	 * @param prefix the variable prefix
	 * @return a {@linkplain ConfigurationPropertyMap} object
	 */
	ConfigurationPropertyMap loadParametersFromEnvironment(String prefix);

	/**Load a {@linkplain ConfigurationPropertyMap} from a file
	 * @param configurationFile the {@linkplain File} to return
	 * @return a {@linkplain ConfigurationPropertyMap} object
	 */
	ConfigurationPropertyMap loadParametersFromFile(File configurationFile);

	/**Load a {@linkplain ConfigurationPropertyMap} from a {@linkplain Reader}
	 * @param filePath the name of the file to be displayed when displaying any processing error
	 * @param reader a {@linkplain Reader}
	 * @return a {@linkplain ConfigurationPropertyMap} object
	 * @throws IOException
	 */
	ConfigurationPropertyMap loadParametersFromReader(String filePath, Reader reader) throws IOException;

}
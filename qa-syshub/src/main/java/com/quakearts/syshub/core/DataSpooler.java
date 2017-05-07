package com.quakearts.syshub.core;

import com.quakearts.syshub.core.metadata.AgentModuleValidator;
import com.quakearts.syshub.exception.ProcessingException;

public interface DataSpooler extends AgentConfigurationModule, AgentModuleValidator {
	/** Initial method call for dataspooler. Implementations should 
	 * initialise data source for data retrieval 
	 * @throws ProcessingException
	 */
	void prepare() throws ProcessingException;
	
	/**Method to check if the call to getData() will not return any data. A return of true does not
	 * guarantee that data will be returned due to the multithreaded nature of application.
	 * For better performance implementers are encouraged adopt a strategy to guarantee data is returned
	 * with the next call to getData()
	 * @return true if the call to get data will likely result in data being returned. False iff
	 * next call to get data will not result in data being returned.
	 */
	boolean hasMoreData();

	/**Data retrieval call. Implementations should return a com.zenithbank.notification.core.Result
	 * object containing data for message creation. Must not return null if no data
	 * is available. An empty Result object should be returned.
	 * @return A result object which may or may not contain data.
	 * @throws ProcessingException
	 */
	Result getData() throws ProcessingException;
	
	/**Method to mark data as being successfully retrieved and transformed into a message.
	 * Called after message creation. Implementations should provide a mechanism for data
	 * to be marked to prevent re-generation of messages
	 * @param result {@link Result} object from which message has been generated
     * @param messages The messages that were generated and sent. useful for spoolers that also need data 
     * from the messaging destination. Message properties can hold this data
	 * @throws ProcessingException
	 */
	void updateData(Result result, Message<?> message) throws ProcessingException;
    
	/**Method to clean up and release data source resources. This method will be called after hasMoreData()
     * returns false. Implementations should ensure that no more data retrieval can occur before
     * this method returns. 
     */
    void close();
}

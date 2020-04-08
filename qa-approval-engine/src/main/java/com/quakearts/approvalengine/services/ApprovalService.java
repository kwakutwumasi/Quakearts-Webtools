package com.quakearts.approvalengine.services;

import java.util.Set;

import com.quakearts.approvalengine.context.ApprovalContext;
import com.quakearts.approvalengine.exception.ApprovalProcessingException;
import com.quakearts.approvalengine.model.ApprovalProcess;

/**Central service for initiating and managing {@linkplain ApprovalProcess} instances
 * @author kwaku
 *
 */
public interface ApprovalService {
	/**Initiate an {@linkplain ApprovalProcess} that will use the {@link com.quakearts.approvalengine.model.ApprovalRules ApprovalRules}
	 * instance identified by rulesName to determine approval process completion. <br />
	 * <br />
	 * The returned instance should be stored (serialized or in an ORM/No-SQL data store) for later retrieval. It is needed to process
	 * an approval
	 * @param groupName the name of the {@link com.quakearts.approvalengine.model.ApprovalGroup ApprovalGroup} the 
	 * ApprovalRules belongs to
	 * @param rulesName the name of ApprovalRules instance
	 * @return the instantiated Approval process
	 * @throws ApprovalProcessingException if the groupName or rulesName is invalid
	 */
	ApprovalProcess initiateApproval(String groupName, String rulesName) throws ApprovalProcessingException;
	/**Initiate an {@linkplain ApprovalProcess} that will use {@link com.quakearts.approvalengine.model.ApprovalRules ApprovalRules}
	 * instances identified by the names in ruleNamesSet to determine approval process completion. <br />
	 * <br />
	 * The returned instance should be stored (serialized or in an ORM/No-SQL data store) for later retrieval. It is needed to process
	 * an approval
	 * @param groupName the name of the {@link com.quakearts.approvalengine.model.ApprovalGroup ApprovalGroup} the 
	 * ApprovalRules belongs to
	 * @param rulesNameSet the set of names of ApprovalRules instances
	 * @return the instantiated Approval process
	 * @throws ApprovalProcessingException if the groupName is invalid or rulesNameSet contain invalid names
	 */
	ApprovalProcess initiateApproval(String groupName, Set<String> rulesNameSet) throws ApprovalProcessingException;
	/**Initiate an {@linkplain ApprovalProcess} that will use {@link com.quakearts.approvalengine.model.ApprovalRules ApprovalRules}
	 * instances identified by the names in activeRulesNameSet and inactiveRulesNameSet to determine approval process completion. <br />
	 * Rules in inactiveRulesNameSet will be ignored unless activated by a call to {@link #activateApprovalProcessRules(Set, ApprovalProcess)}.
	 * This makes it possible to have different approval rules that are active or inactive depending on certain conditions
	 * <br />
	 * The returned instance should be stored (serialized or in an ORM/No-SQL data store) for later retrieval. It is needed to process
	 * an approval
	 * @param groupName the name of the {@link com.quakearts.approvalengine.model.ApprovalGroup ApprovalGroup} the 
	 * ApprovalRules belongs to
	 * @param activeRulesNameSet the set of names of active ApprovalRules instances
	 * @param inactiveRulesNameSet the set of names of inactive ApprovalRules instances
	 * @return the instantiated Approval process
	 * @throws ApprovalProcessingException if the groupName is invalid or activeRulesNameSet or inactiveRulesNameSet contain invalid names
	 */
	ApprovalProcess initiateApproval(String groupName, Set<String> activeRulesNameSet, Set<String> inactiveRulesNameSet) throws ApprovalProcessingException;
	/**Activate the {@link com.quakearts.approvalengine.model.ApprovalRules ApprovalRules} instances named in the rulesNameSet
	 * @param rulesNameSet a set of names of the ApprovalRules
	 * @param approvalProcess the {@linkplain ApprovalProcess} tied to the instances of ApprovalRules
	 * @throws ApprovalProcessingException if the rulesNameSet contains invalid names or the approvalProcess is invalid
	 */
	void activateApprovalProcessRules(Set<String> rulesNameSet, ApprovalProcess approvalProcess) throws ApprovalProcessingException;
	/**Deactivate the {@link com.quakearts.approvalengine.model.ApprovalRules ApprovalRules} instances named in the rulesNameSet
	 * @param rulesNameSet a set of names of the ApprovalRules
	 * @param approvalProcess the {@linkplain ApprovalProcess} tied to the instances of ApprovalRules
	 * @throws ApprovalProcessingException if the rulesNameSet contains invalid names or the approvalProcess is invalid
	 */
	void deactivateApprovalProcessRules(Set<String> rulesNameSet, ApprovalProcess approvalProcess) throws ApprovalProcessingException;
	/**Process and approval. The {@linkplain ApprovalContext} can be obtained by calls to an
	 * {@link com.quakearts.approvalengine.context.ApprovalContextBuilder ApprovalContextBuilder} instance
	 * @param context the context object containing all required information for processing an approval
	 * @throws ApprovalProcessingException if there is a problem processing the approval
	 */
	void processApproval(ApprovalContext context) throws ApprovalProcessingException;
}
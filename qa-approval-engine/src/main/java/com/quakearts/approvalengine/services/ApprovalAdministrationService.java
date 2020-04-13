package com.quakearts.approvalengine.services;

import java.util.List;
import java.util.Optional;

import com.quakearts.approvalengine.exception.ApprovalProcessingException;
import com.quakearts.approvalengine.exception.MissingFieldException;
import com.quakearts.approvalengine.model.ApprovalGroup;
import com.quakearts.approvalengine.model.ApprovalProcess;
import com.quakearts.approvalengine.model.ApprovalRules;
import com.quakearts.approvalengine.model.Approver;

/**Central administrative service for the Approval Engine.
 * Use it to create or find {@linkplain ApprovalGroup}, {@linkplain ApprovalRules},
 * {@link com.quakearts.approvalengine.model.ApprovalRule ApprovalRule} and {@linkplain Approver},
 * and to find {@linkplain ApprovalProcess} instances.
 * 
 * @author kwaku
 *
 */
public interface ApprovalAdministrationService {
	/**Create an {@linkplain ApprovalGroup}
	 * @param name the name of the group
	 * @return the created group
	 * @throws ApprovalProcessingException if the group name is a duplicate
	 */
	ApprovalGroup createApprovalGroup(String name) throws ApprovalProcessingException;
	/**Returns a builder for creating {@linkplain ApprovalRules} instances
	 * @param name the name of the approval rule
	 * @param groupName the name of the ApprovalGroup of the new approval rule
	 * @return the builder
	 * @throws ApprovalProcessingException if the name or groupName is invalid
	 */
	ApprovalRulesBuilder createApprovalRules(String name, String groupName) throws ApprovalProcessingException;
	public interface ApprovalRulesBuilder {
		/**Set the rule priority.
		 * @param priority
		 * @return this instance for method chaining
		 */
		ApprovalRulesBuilder setPriorityAs(int priority);
		/**Add an {@link com.quakearts.approvalengine.model.ApprovalRule ApprovalRule} to the {@linkplain ApprovalRules} instance
		 * @param level the {@linkplain Approver} level this rule applies to. Level is determined by {@linkplain Approver#getLevel()}
		 * @param approvalCount the number of approvals required to pass this rule
		 * @return this instance for method chaining
		 * @throws ApprovalProcessingException if the level or count is invalid
		 */
		ApprovalRulesBuilder addRule(int level, int approvalCount) throws ApprovalProcessingException;
		/**Return the {@linkplain ApprovalRules} created instance
		 * @return the created ApprovalRules instance
		 * @throws ApprovalProcessingException if there was a problem creating the rule
		 */
		ApprovalRules thenBuild() throws ApprovalProcessingException;
	}
	/**Activate an {@linkplain ApprovalRules} instance
	 * @param rulesName the name of the approval rules instance
	 * @param groupName the group name of the approval rules instance
	 * @throws ApprovalProcessingException if the rulesName or groupName is invalid, or the rule was not found
	 */
	void activateApprovalRules(String rulesName, String groupName) throws ApprovalProcessingException;
	/**Deactivate an {@linkplain ApprovalRules} instance
	 * @param rulesName the name of the approval rules instance
	 * @param groupName the group name of the approval rules instance
	 * @throws ApprovalProcessingException if the rulesName or groupName is invalid, or the rule was not found
	 */
	void deactivateApprovalRules(String rulesName, String groupName) throws ApprovalProcessingException;
	/**Activate an {@link com.quakearts.approvalengine.model.ApprovalRule ApprovalRule}
	 * @param level the {@link Approver} level the rule applies to
	 * @param rulesName the name of the approval rules instance
	 * @param groupName the group name of the approval rules instance
	 * @throws ApprovalProcessingException if the rulesName or groupName is invalid, or the rule was not found
	 */
	void activateApprovalRule(int level, String rulesName, String groupName) throws ApprovalProcessingException;
	/**Deactivate an {@link com.quakearts.approvalengine.model.ApprovalRule ApprovalRule}
	 * @param level the {@link Approver} level the rule applies to
	 * @param rulesName the name of the approval rules instance
	 * @param groupName the group name of the approval rules instance
	 * @throws ApprovalProcessingException if the rulesName or groupName is invalid, or the rule was not found
	 */
	void deactivateApprovalRule(int level, String rulesName, String groupName) throws ApprovalProcessingException;
	/**Create an {@linkplain Approver}
	 * @param externalId the external ID used to pick this approver profile. Usually the system username or identity
	 * @param level the approver level of the Approver
	 * @param groupName the name of the {@linkplain ApprovalGroup} the Approver belongs to
	 * @return the created Approver instance
	 * @throws ApprovalProcessingException id the externalId, level or groupName is invalid
	 */
	Approver createApprover(String externalId, int level, String groupName) throws ApprovalProcessingException;
	/**Retrieve the {@linkplain ApprovalProcess} identified by the ID
	 * @param id the ID of the approval process 
	 * @param groupName the name of the {@linkplain ApprovalGroup} the ApprovalProcess belongs to
	 * @return an {@linkplain Optional} instance that may or may not contain the ApprovalProcess
	 * @throws MissingFieldException if id, level, or groupName is invalid
	 */
	Optional<ApprovalProcess> findApprovalProcess(long id, String groupName);
	/**Retrieve the {@linkplain ApprovalGroup} using its name
	 * @param name the name of the ApprovalGroup
	 * @return an {@linkplain Optional} instance that may or may not contain the ApprovalGroup
	 * @throws MissingFieldException if name is invalid
	 */
	Optional<ApprovalGroup> findApprovalGroupByName(String name);
	/**Retrieve the {@linkplain ApprovalRules} instance using its name and approval group name
	 * @param groupName the name of the ApprovalGroup the ApprovalRules instance belongs to
	 * @param rulesName the name of the ApprovalRules instance
	 * @return an {@linkplain Optional} instance that may or may not contain the ApprovalRules instance
	 * @throws MissingFieldException if groupName or rulesName is invalid
	 */
	Optional<ApprovalRules> findApprovalRulesByName(String groupName, String rulesName);
	/**Retrieve all {@linkplain ApprovalRules} instances by priority and approval group name
	 * @param groupName the name of the ApprovalGroup the ApprovalRules instance belongs to
	 * @param priority the priority of the ApprovalRules instance
	 * @return a {@linkplain List} instance that may or may not contain the ApprovalRules instances
	 * @throws MissingFieldException if groupName or rulesName is invalid
	 */
	List<ApprovalRules> findApprovalRulesByPriority(String groupName, int priority);
	/**Retrieve the {@linkplain Approver} using its externalId and approval group name
	 * @param groupName the name of the ApprovalGroup the Approver belongs to
	 * @param externalId the name of the ApprovalRules instance
	 * @return an {@linkplain Optional} instance that may or may not contain the ApprovalRules instance
	 * @throws MissingFieldException if groupName or rulesName is invalid
	 */
	Optional<Approver> findApproverByIdentity(String groupName, String externalId);
	/**Retrieve all {@linkplain Approver} instances by level and approval group name
	 * @param groupName the name of the ApprovalGroup the Approver instance belongs to
	 * @param level the level of the Approvers
	 * @return a {@linkplain List} instance that may or may not contain the Approvers
	 * @throws MissingFieldException if groupName or rulesName is invalid
	 */
	List<Approver> findApproversByLevel(String groupName, int level);
}

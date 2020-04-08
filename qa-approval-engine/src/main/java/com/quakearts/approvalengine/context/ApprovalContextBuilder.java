package com.quakearts.approvalengine.context;

import com.quakearts.approvalengine.exception.ApprovalProcessingException;
import com.quakearts.approvalengine.model.ApprovalProcess;
import com.quakearts.approvalengine.model.Approval.ApprovalAction;

/**Builder for {@linkplain ApprovalContext} instances. Instances of this object
 * can be obtained by calls to {@link com.quakearts.approvalengine.context.ApprovalContextBuilderFactory#createApprovalContext() ApprovalContextBuilderFactory.createApprovalContext()}
 * @author kwaku
 *
 */
public interface ApprovalContextBuilder {

	/**Set the approver details
	 * @param externalId the externalId used to reference the approver
	 * @param groupName the group name of the approval group the approver belongs to
	 * @return This instance for method chaining
	 * @throws ApprovalProcessingException if the approver or group is not valid
	 */
	ApprovalContextBuilder setApproverAs(String externalId, String groupName) throws ApprovalProcessingException;
	/**The action the approver wishes to take
	 * @param action The action
	 * @return This instance for method chaining
	 */
	ApprovalContextBuilder setActionAs(ApprovalAction action);
	/**The active, incomplete {@linkplain ApprovalProcess} to process
	 * @param approvalProcess
	 * @return This instance for method chaining
	 */
	ApprovalContextBuilder setApprovalProcessAs(ApprovalProcess approvalProcess);
	/**Terminal method. Return the {@linkplain ApprovalContext}
	 * @return the ApprovalProcess
	 * @throws ApprovalProcessingException if there is a problem constructing the ApprovalContextImpl
	 */
	ApprovalContext thenBuild() throws ApprovalProcessingException;

}
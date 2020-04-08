package com.quakearts.approvalengine.context;

import java.util.Set;

import com.quakearts.approvalengine.model.Approval;
import com.quakearts.approvalengine.model.ApprovalProcess;

/**Interface for instances holding context information required for processing an approval
 * @author kwaku
 *
 */
public interface ApprovalContext {

	/**Getter for the {@linkplain Approval} instance
	 * @return the approval
	 */
	Approval getApproval();

	/**Getter for the {@linkplain ApprovalProcess} instance
	 * @return the approval process
	 */
	ApprovalProcess getApprovalProcess();

	/**Return the number of approvals received for the stated level
	 * @param level
	 * @return
	 */
	int getApprovalCount(int level);

	/**Getter for approval completion
	 * @return the completion state
	 */
	boolean isComplete();

	/**Return true if the approver's approval level rule was found in the approval rules
	 * @return
	 */
	boolean levelWasFound();

	/**Return a set of all current approver externalIds
	 * @return the set of approver externalIds
	 */
	Set<String> getCurrentApprovers();

}
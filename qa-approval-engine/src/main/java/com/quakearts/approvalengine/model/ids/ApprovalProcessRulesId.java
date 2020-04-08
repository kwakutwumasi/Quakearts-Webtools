package com.quakearts.approvalengine.model.ids;

import java.io.Serializable;
import java.util.Objects;

/**Convenience object for retrieving instances
 *  {@link com.quakearts.approvalengine.model.ApprovalProcessRules ApprovalProcessRules}
 *  from a JPA backed QA-ORM store 
 * @author kwaku
 *
 */
public class ApprovalProcessRulesId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2147355565860374L;
	private long approvalProcessId;
	private int approvalRulesId;

	public ApprovalProcessRulesId() {}
	
	public ApprovalProcessRulesId(long approvalProcessId, int approvalRulesId) {
		this.approvalProcessId = approvalProcessId;
		this.approvalRulesId = approvalRulesId;
	}

	public long getApprovalProcessId() {
		return approvalProcessId;
	}

	public void setApprovalProcessId(long approvalProcessId) {
		this.approvalProcessId = approvalProcessId;
	}
	
	public int getApprovalRulesId() {
		return approvalRulesId;
	}

	public void setApprovalRulesId(int approvalRulesId) {
		this.approvalRulesId = approvalRulesId;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(approvalProcessId, approvalRulesId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ApprovalProcessRulesId other = (ApprovalProcessRulesId) obj;
		return approvalProcessId == other.approvalProcessId && approvalRulesId == other.approvalRulesId;
	}
	
}

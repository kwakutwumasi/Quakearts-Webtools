package com.quakearts.approvalengine.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.quakearts.approvalengine.model.ids.ApprovalProcessRulesId;
import com.quakearts.approvalengine.utils.AEUtils;

/**Represents a mapping of {@linkplain ApprovalProcess} instances to
 * {@linkplain ApprovalRules} instances
 * @author kwaku
 *
 */
@Entity
@Table(name = "ae_approval_process_rules")
@IdClass(ApprovalProcessRulesId.class)
public class ApprovalProcessRules implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5682745622562002427L;
	@Id
	@Column(name = "approval_process_id")
	private long approvalProcessId;
	@Id
	@Column(name = "approval_rules_id")
	private int approvalRulesId;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "approval_process_id", insertable = false, updatable = false)
	private ApprovalProcess approvalProcess;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "approval_rules_id", insertable = false, updatable = false)
	private ApprovalRules approvalRules;
	@Column(nullable = false)
	private boolean active;

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

	public ApprovalProcess getApprovalProcess() {
		return approvalProcess;
	}

	/**Convenience setter. Automatically maps the {@linkplain ApprovalProcess#getId()}
	 * to the approvalProcessId
	 * @param approvalProcess the ApprovalProcess instance for this mapping
	 */
	public void setApprovalProcess(ApprovalProcess approvalProcess) {
		this.approvalProcess = approvalProcess;
		if(approvalProcess != null)
			this.approvalProcessId = approvalProcess.getId();
	}

	public ApprovalRules getApprovalRules() {
		return approvalRules;
	}

	/**Convenience setter. Automatically maps the {@linkplain ApprovalRules#getId()}
	 * to the approvalRules
	 * @param approvalRules the ApprovalRules instance for this mapping
	 */
	public void setApprovalRules(ApprovalRules approvalRules) {
		this.approvalRules = approvalRules;
		if(approvalRules != null)
			this.approvalRulesId = approvalRules.getId();
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	@Override
	public String toString() {
		return AEUtils.toEncodedString(approvalProcessId, (long)approvalRulesId, (long)hashCode());
	}
}

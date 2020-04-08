package com.quakearts.approvalengine.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.quakearts.approvalengine.utils.AEUtils;

/**Represents an approval given by an {@linkplain Approver}
 * @author kwaku
 *
 */
@Entity
@Table(name = "ae_approval")
public class Approval implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7552716527845514351L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "approver")
	private Approver approver;
	@Column(name = "approval_date", nullable = false)
	private Timestamp approvalDate;
	public enum ApprovalAction {
		APPROVED, REJECTED;
	}
	@Column(nullable = false)
	private ApprovalAction action;
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "approval_process")
	private ApprovalProcess approvalProcess;
	@Column(nullable = false)
	private boolean valid;
	
	/**Getter for the unique system ID
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**Setter for the unique system ID
	 * @param id the unique ID
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**Getter for the {@linkplain Approver}
	 * @return the Approver instance
	 */
	public Approver getApprover() {
		return approver;
	}

	/**Getter for the {@linkplain Approver}
	 * @param approver the Approver Instance
	 */
	public void setApprover(Approver approver) {
		this.approver = approver;
	}

	/**Getter for the {@linkplain Timestamp} that indicates the date/time this 
	 * approval was given
	 * @return the Timestamp
	 */
	public Timestamp getApprovalDate() {
		return approvalDate;
	}

	/**Setter for the date/time this approval was given
	 * @param approvalDate
	 */
	public void setApprovalDate(Timestamp approvalDate) {
		this.approvalDate = approvalDate;
	}

	/**Getter for the action undertaken. One of {@linkplain ApprovalAction} enum values
	 * @return the action
	 */
	public ApprovalAction getAction() {
		return action;
	}

	/**Setter for the action undertaken
	 * @param action One of {@linkplain ApprovalAction} enum values
	 */
	public void setAction(ApprovalAction action) {
		this.action = action;
	}

	/**Getter for the {@linkplain ApprovalProcess} this Approval is part of
	 * @return the ApprovalProcess
	 */
	public ApprovalProcess getApprovalProcess() {
		return approvalProcess;
	}

	/**Setter for the {@linkplain ApprovalProcess}
	 * @param approvalProcess the ApprovalProcess
	 */
	public void setApprovalProcess(ApprovalProcess approvalProcess) {
		this.approvalProcess = approvalProcess;
	}

	/**Getter for the validity state of this approval. If false,
	 * the approval will not be considered when determining approval process completion
	 * @return true if valid. False if not
	 */
	public boolean isValid() {
		return valid;
	}

	/**Setter for the approval validity state
	 * @param valid the validity state
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	@Override
	public String toString() {
		return AEUtils.toEncodedString(id, (long)hashCode());
	}
}

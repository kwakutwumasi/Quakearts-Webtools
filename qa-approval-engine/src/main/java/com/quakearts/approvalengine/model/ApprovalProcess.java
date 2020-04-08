package com.quakearts.approvalengine.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**Represents an approval process
 * @author kwaku
 *
 */
@Entity
@Table(name = "ae_approval_process")
public class ApprovalProcess implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6912963561429752421L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "group_id")
	private ApprovalGroup group;
	@Column(name = "create_date", nullable = false)
	private Timestamp startDate;
	@Column(name = "complete_date")
	private Timestamp  completeDate;
	@Column(nullable = false)
	private boolean complete;
	@Column(nullable = false)
	private boolean approved;
	@Column(nullable = false)
	private boolean valid;
	@OneToMany(mappedBy = "approvalProcess")
	private Set<ApprovalProcessRules> approvalProcessRulesSet = new HashSet<>();

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

	/**Getter for the {@linkplain ApprovalGroup} this approval process is part of
	 * @return the ApprovalGroup
	 */
	public ApprovalGroup getGroup() {
		return group;
	}

	/**Setter for the {@linkplain ApprovalGroup} this approval process is part of
	 * @param group the ApprovalGroup
	 */
	public void setGroup(ApprovalGroup group) {
		this.group = group;
	}

	/**Getter for the {@linkplain Timestamp} that indicates the date/time
	 * this process was started
	 * @return the Timestamp
	 */
	public Timestamp getStartDate() {
		return startDate;
	}

	/**Setter for the the date/time this process was started
	 * @param startDate the Timestamp
	 */
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	/**Getter for the {@linkplain Timestamp} that indicates the date/time
	 * this process was completed
	 * @return the Timestamp
	 */
	public Timestamp getCompleteDate() {
		return completeDate;
	}

	/**Setter for the the date/time this process was started
	 * @param completeDate the Timestamp
	 */
	public void setCompleteDate(Timestamp completeDate) {
		this.completeDate = completeDate;
	}

	/**Getter for the completion state of the approval process
	 * @return true if complete. False if otherwise
	 */
	public boolean isComplete() {
		return complete;
	}

	/**Setter the completion state of the approval process
	 * @param complete the completion state
	 */
	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	/**Getter for the approval state of the approval process
	 * @return true if approved. False if otherwise
	 */
	public boolean isApproved() {
		return approved;
	}

	/**Setter the approval state of the approval process
	 * @param approved the approval state
	 */
	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	/**Getter for the validity state of this approval process. If false,
	 * the approval process cannot be completed
	 * @return true if valid. False if not
	 */
	public boolean isValid() {
		return valid;
	}

	/**Setter for the approval process validity state. If false,
	 * the approval process cannot be completed
	 * @param valid the validity state
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	/**Getter for the {@linkplain ApprovalProcessRules} instances mapping
	 * the approval process to {@link com.quakearts.approvalengine.model.ApprovalRules ApprovalRules} 
	 * instances
	 * @return the {@linkplain Set} of ApprovalProcessRules instances
	 */
	public Set<ApprovalProcessRules> getApprovalProcessRulesSet() {
		return approvalProcessRulesSet;
	}

	/**Setter for the {@linkplain ApprovalProcessRules} instances mapping the approval process 
	 * to {@link com.quakearts.approvalengine.model.ApprovalRules ApprovalRules}
	 * @param approvalProcessRulesSet the {@linkplain Set} of ApprovalProcessRules instances
	 */
	public void setApprovalProcessRulesSet(Set<ApprovalProcessRules> approvalProcessRulesSet) {
		this.approvalProcessRulesSet = approvalProcessRulesSet;
	}

	/**Convenience method for checking approval
	 * @return true if approved. False if otherwise
	 */
	public boolean hasBeenApproved(){
		return approved && complete;
	}
	
	/**Convenience method for checking rejection
	 * @return true if rejected. False if otherwise
	 */
	public boolean hasBeenRejected(){
		return !approved && complete;
	}

}

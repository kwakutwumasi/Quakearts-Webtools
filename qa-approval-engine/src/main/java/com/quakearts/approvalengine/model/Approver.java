package com.quakearts.approvalengine.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue; 
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.quakearts.approvalengine.utils.AEUtils;

/**Represents an approver within an approval group
 * @author kwaku
 *
 */
@Entity
@Table(name = "ae_approver", 
	uniqueConstraints = @UniqueConstraint(columnNames = {"externalId", "group_id"}))
public class Approver implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4631490627731901342L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(length = 100, nullable = false)
	private String externalId;
	@Column(nullable = false)
	private int level;
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "group_id")
	private ApprovalGroup group;
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

	/**Getter for the group wide unique external id for retrieving this
	 * instance
	 * @return the group wide unique external id
	 */
	public String getExternalId() {
		return externalId;
	}

	/**Setter for the group wide unique external id 
	 * @param externalId the group wide unique external id 
	 */
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	/**Getter for the level of this approver.
	 * It has no meaning within the context of approval processing. Any valid
	 * approval by any approver of any level will be processed at any time
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**Setter for the level of this approver.
	 * It has no meaning within the context of approval processing. Any valid
	 * approval by any approver of any level will be processed at any time
	 * @param level the level
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**Getter for the {@linkplain ApprovalGroup} this approver is part of
	 * @return the ApprovalGroup
	 */
	public ApprovalGroup getGroup() {
		return group;
	}

	/**Setter for the {@linkplain ApprovalGroup} this approver is part of
	 * @param group the ApprovalGroup
	 */
	public void setGroup(ApprovalGroup group) {
		this.group = group;
	}

	/**Getter for the validity state of this approver. If false,
	 * the approver cannot participate in the approval process
	 * @return true if valid. False if not
	 */
	public boolean isValid() {
		return valid;
	}

	/**Setter for validity state of this approver. If false,
	 * the approver cannot participate in the approval process
	 * @param valid the validity state of this approver
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	@Override
	public String toString() {
		return AEUtils.toEncodedString(id, (long)hashCode());
	}
}

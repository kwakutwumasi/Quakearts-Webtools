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

/**Represents a single rule in an instance of {@linkplain ApprovalRules}
 * @author kwaku
 *
 */
@Entity
@Table(name = "ae_approval_rule", uniqueConstraints = @UniqueConstraint(columnNames = {
		"rules", "level"
}))
public class ApprovalRule implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1204569027481372020L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "rules")
	private ApprovalRules rules;
	@Column(nullable = false)
	private int level;
	@Column(nullable = false)
	private int count;
	@Column(nullable = false)
	private boolean active;

	/**Getter for the unique system ID
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**Setter for the unique system ID
	 * @param id the unique ID
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**Getter for the {@linkplain ApprovalRules} instance this rule is a part of
	 * @return the ApprovalRules
	 */
	public ApprovalRules getRules() {
		return rules;
	}

	/**Setter the {@linkplain ApprovalRules} instance this rule is a part of
	 * @param rules the ApprovalRules
	 */
	public void setRules(ApprovalRules rules) {
		this.rules = rules;
	}

	/**Getter for the approver level this rule applies to
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**Setter for the approver level this rule applies to
	 * @param level the level
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**Getter for the number of {@link com.quakearts.approvalengine.model.Approval Approval}
	 * 's required to complete the {@link com.quakearts.approvalengine.model.ApprovalProcess}
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**Setter for the number of {@link com.quakearts.approvalengine.model.Approval Approval}
	 * 's required to complete the {@link com.quakearts.approvalengine.model.ApprovalProcess}
	 * @param count the count
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**Getter for the active state of this approval rule. If false,
	 * the approval rule will not be considered when determining approval process completion
	 * @return true if active. False if not
	 */
	public boolean isActive() {
		return active;
	}

	/**Setter for the active state of this approval rule. If false,
	 * the approval rule will not be considered when determining approval process completion
	 * @param valid the active state
	 */
	public void setActive(boolean valid) {
		this.active = valid;
	}

	@Override
	public String toString() {
		return AEUtils.toEncodedString((long)id, (long)hashCode());
	}
}

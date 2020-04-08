package com.quakearts.approvalengine.model;

import java.io.Serializable;
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
import javax.persistence.UniqueConstraint;

/**Represents the set of {@linkplain ApprovalRule} instances that make up
 * the approval rules to be used to determine approval process completion
 * @author kwaku
 *
 */
@Entity
@Table(name = "ae_approval_rules", 
	uniqueConstraints = @UniqueConstraint(columnNames = {"name", "group_id"}))
public class ApprovalRules implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3828289161777694123L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, length = 50)
	private String name;
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "group_id")
	private ApprovalGroup group;
	@Column(nullable = false)
	private int priority;
	@Column(nullable = false)
	private boolean active;
	@OneToMany(mappedBy = "rules")
	private Set<ApprovalRule> ruleElements = new HashSet<>();

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

	/**Getter for the group wide unique name
	 * @return the unique name
	 */
	public String getName() {
		return name;
	}

	/**Setter for the group wide unique name
	 * @param name the group wide unique name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**Getter for the {@linkplain ApprovalGroup} this approval rule set is part of
	 * @return the ApprovalGroup
	 */
	public ApprovalGroup getGroup() {
		return group;
	}

	/**Setter for the {@linkplain ApprovalGroup} this approval rule set is part of
	 * @param group the ApprovalGroup
	 */
	public void setGroup(ApprovalGroup group) {
		this.group = group;
	}

	/**Getter for the priority of this approval rule set.
	 * It has no meaning within the context of approval processing. All active
	 * approval rules are used when determining approval process completion. The library
	 * user can use this to group approval rules, to make it simpler to activate or deactivate them
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}

	/**Setter for the priority of this approval rule set.
	 * It has no meaning within the context of approval processing. All active
	 * approval rules are used when determining approval process completion. The library
	 * user can use this to group approval rules, to make it simpler to activate or deactivate them
	 * @param priority the priority
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**Getter for the active state of this approval rule set. If false,
	 * the approval rule set will not be considered when determining approval process completion
	 * @return true if active. False if not
	 */
	public boolean isActive() {
		return active;
	}

	/**Setter for the active state of this approval rule set. If false,
	 * the approval rule set will not be considered when determining approval process completion
	 * @param valid the active state
	 */
	public void setActive(boolean valid) {
		this.active = valid;
	}

	/**Getter for the {@linkplain ApprovalRule} instances  
	 * that form a part of this approval rules set
	 * @return the {@linkplain Set} of ApprovalRule instances
	 */
	public Set<ApprovalRule> getRuleElements() {
		return ruleElements;
	}

	/**Setter for the {@linkplain ApprovalRule} instances  
	 * that form a part of this approval rules set
	 * @param ruleElements the {@linkplain Set} of ApprovalRule instances
	 */
	public void setRuleElements(Set<ApprovalRule> ruleElements) {
		this.ruleElements = ruleElements;
	}

}
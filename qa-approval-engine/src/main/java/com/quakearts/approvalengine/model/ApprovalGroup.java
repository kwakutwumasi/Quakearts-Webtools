package com.quakearts.approvalengine.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.quakearts.approvalengine.utils.AEUtils;

/**Represents the approval group. Used to group all other instances
 * @author kwaku
 *
 */
@Entity
@Table(name = "ae_approval_group")
public class ApprovalGroup implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4180923157910892776L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false, unique = true)
	private String name;
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

	/**Getter for the unique approval group name
	 * @return the unique approval group name
	 */
	public String getName() {
		return name;
	}

	/**Setter for the unique approval group name
	 * @param name the unique approval group name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**Getter for the validity state of this approval group. If false,
	 * the approval group, and all member object instances will be invalidated.
	 * This means attempts to perform non-read operations on members of the group will throw
	 * {@link com.quakearts.approvalengine.exception.InvalidApprovalGroupException InvalidApprovalGroupException}
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

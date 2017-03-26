package com.quakearts.workflowapp.jbpm.util;

public class ProfileBean {
	
    public static final String ATTRIBUTES[] = new String[]{"department","businessCategory","physicalDeliveryOfficeName","title","employeetype","employeenumber","cn"};


	private String objectName,name="",staffNumber="",grade="",department="",unit="",branch="",title="";
	private boolean newStaffNumber = false,newGrade = false,newDepartment = false,newUnit = false,newBranch = false,newTitle = false;
	
	@SuppressWarnings("unused")
	private ProfileBean(){
	}
	
	public ProfileBean(String objectName){
		this.objectName = objectName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean isNewStaffNumber() {
		return newStaffNumber;
	}

	public void setNewStaffNumber(boolean newStaffNumber) {
		this.newStaffNumber = newStaffNumber;
	}

	public boolean isNewGrade() {
		return newGrade;
	}

	public void setNewGrade(boolean newGrade) {
		this.newGrade = newGrade;
	}

	public boolean isNewDepartment() {
		return newDepartment;
	}

	public void setNewDepartment(boolean newDepartment) {
		this.newDepartment = newDepartment;
	}

	public boolean isNewUnit() {
		return newUnit;
	}

	public void setNewUnit(boolean newUnit) {
		this.newUnit = newUnit;
	}

	public boolean isNewBranch() {
		return newBranch;
	}

	public void setNewBranch(boolean newBranch) {
		this.newBranch = newBranch;
	}

	public boolean isNewTitle() {
		return newTitle;
	}

	public void setNewTitle(boolean newTitle) {
		this.newTitle = newTitle;
	}

	public String getStaffNumber() {
		return staffNumber;
	}

	public void setStaffNumber(String staffNumber) {
		this.staffNumber = staffNumber;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getObjectName() {
		return objectName;
	}
}

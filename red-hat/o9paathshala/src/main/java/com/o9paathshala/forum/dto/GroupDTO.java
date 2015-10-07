package com.o9paathshala.forum.dto;

public class GroupDTO {

	private String groupName;
	private Integer groupId;
	private Integer adminId;
	private String adminName;
	private Integer reputation;
	private Integer noOfMembers;
	
	public Integer getNoOfMembers() {
		return noOfMembers;
	}
	public void setNoOfMembers(Integer noOfMembers) {
		this.noOfMembers = noOfMembers;
	}
	public Integer getReputation() {
		return reputation;
	}
	public void setReputation(Integer reputation) {
		this.reputation = reputation;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
	
}

package operation.pojo.group;

import java.util.List;

import operation.pojo.user.OpenFireUser;

public class OpenFireGroup {
	private List<String> serverList;
	private String groupService;
	private String groupName;
	private String groupDesc;
	private String groupSubject;
	private OpenFireUser groupCreater;
	private Object groupData;
	

	public OpenFireGroup() {
		// TODO Auto-generated constructor stub
	}


	public List<String> getServerList() {
		return serverList;
	}


	public void setServerList(List<String> serverList) {
		this.serverList = serverList;
	}


	public String getGroupService() {
		return groupService;
	}


	public void setGroupService(String groupService) {
		this.groupService = groupService;
	}


	public String getGroupName() {
		return groupName;
	}


	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	public String getGroupDesc() {
		return groupDesc;
	}


	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}


	public String getGroupSubject() {
		return groupSubject;
	}


	public void setGroupSubject(String groupSubject) {
		this.groupSubject = groupSubject;
	}


	public Object getGroupData() {
		return groupData;
	}


	public void setGroupData(Object groupData) {
		this.groupData = groupData;
	}


	public OpenFireUser getGroupCreater() {
		return groupCreater;
	}


	public void setGroupCreater(OpenFireUser groupCreater) {
		this.groupCreater = groupCreater;
	}

}

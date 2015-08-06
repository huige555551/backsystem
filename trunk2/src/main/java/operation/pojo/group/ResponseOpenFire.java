package operation.pojo.group;

import java.util.List;

public class ResponseOpenFire {
	private List<String> serverList;
	private String groupService;
	private String groupName;
	private String groupDesc;
	private String groupSubject;
	
	public ResponseOpenFire(OpenFireGroup group){
		this.setGroupDesc(group.getGroupDesc());
		this.setGroupName(group.getGroupName());
		this.setGroupService(group.getGroupService());
		this.setGroupSubject(group.getGroupSubject());
		this.setServerList(group.getServerList());
	}
	public ResponseOpenFire() {
		super();
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
}

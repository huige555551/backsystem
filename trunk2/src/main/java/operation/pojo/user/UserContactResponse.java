package operation.pojo.user;


import java.util.List;

public class UserContactResponse {
	
	private List<ContactAdress>  userNoRegist;
	private List<ContactAdress>  userRegist;
	private List<ContactAdress>  userJoinGroup;
	
	
	public UserContactResponse(List<ContactAdress> userNoRegist,List<ContactAdress> userRegist,List<ContactAdress> userJoinGroup) {
		super();
		this.userNoRegist = userNoRegist;
		this.userRegist = userRegist;
		this.userJoinGroup = userJoinGroup;
	}
	public List<ContactAdress> getUserNoRegist() {
		return userNoRegist;
	}
	public void setUserNoRegist(List<ContactAdress> userNoRegist) {
		this.userNoRegist = userNoRegist;
	}
	public List<ContactAdress> getUserRegist() {
		return userRegist;
	}
	public void setUserRegist(List<ContactAdress> userRegist) {
		this.userRegist = userRegist;
	}
	public List<ContactAdress> getUserJoinGroup() {
		return userJoinGroup;
	}
	public void setUserJoinGroup(List<ContactAdress> userJoinGroup) {
		this.userJoinGroup = userJoinGroup;
	}

	

}

package operation.pojo.praise;

public class UserPraiseResponse {
	
	private String userId;
	
	private String userName;
	
	private String userLogoUrl;
	
	private int contactStatus;
	
	private String nickName;

	public UserPraiseResponse(String userId, String userName, String userLogoUrl,String nickName) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userLogoUrl = userLogoUrl;
		this.nickName = nickName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserLogoUrl() {
		return userLogoUrl;
	}

	public void setUserLogoUrl(String userLogoUrl) {
		this.userLogoUrl = userLogoUrl;
	}

	public int getContactStatus() {
		return contactStatus;
	}

	public void setContactStatus(int contactStatus) {
		this.contactStatus = contactStatus;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}


	

}

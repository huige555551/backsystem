package operation.pojo.user;


import org.springframework.data.annotation.Id;

public class ContactAdress {
	@Id
	private String id;
	private String userId;
	private String userName;//上传者手机号
	private String name;  //好友名称
	private String phoneNumber; //好友电话
	private int type; //状态 0 为非注册用户 1已注册用户
	private String friendUserId;//好友ID
	private long ctime;
	private long utime;
	public ContactAdress(String userId,String userName,String name,String phoneNumber){
		this.setCtime();
		this.setUtime();
		this.userId = userId;
		this.userName = userName;
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getFriendUserId() {
		return friendUserId;
	}

	public void setFriendUserId(String friendUserId) {
		this.friendUserId = friendUserId;
	}

	public long getCtime() {
		return ctime;
	}
	public void setCtime() {
		this.ctime = System.currentTimeMillis();
	}
	public long getUtime() {
		return utime;
	}
	public void setUtime() {
		this.utime = System.currentTimeMillis();
	}
	
	

	
	
	
	
}

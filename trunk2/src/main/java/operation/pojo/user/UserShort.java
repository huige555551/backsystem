package operation.pojo.user;

/** 
* @ClassName: UserShort
* @Description: 短用户信息，通常前端业务只需要用户名和用户id 用户名称可根据业务探讨是否需要更新
* @author yangquanliang
* @date 2014年12月10日 下午12:10:29
* 
*/ 
public class UserShort {

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
	private String userId;
	private String userName;
}

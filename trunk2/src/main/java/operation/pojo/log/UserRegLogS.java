package operation.pojo.log;

import org.springframework.data.annotation.Id;

/**
* 
* @ClassName: UserRegLogS
* @Description:用于邀请用户统计
* @author tangli
* @date 2015年3月19日 上午9:54:55
*
*/
public class UserRegLogS {
	@Id
	private String id;
	private String vUserId;//邀请人Id	
	private String vUserNick;//邀请人昵称
	private String vUserEmail;//邀请人邮箱
	private String vUserPhone;//邀请人电话
	private int total;//已邀请人数量
	private String userNick;//上级邀请人的昵称
	private String userId;//上级邀请人的Id
	private long ctime;
	public UserRegLogS(){
		ctime=System.currentTimeMillis();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getvUserId() {
		return vUserId;
	}
	public void setvUserId(String vUserId) {
		this.vUserId = vUserId;
	}
	public String getvUserNick() {
		return vUserNick;
	}
	public void setvUserNick(String vUserNick) {
		this.vUserNick = vUserNick;
	}
	public String getvUserEmail() {
		return vUserEmail;
	}
	public void setvUserEmail(String vUserEmail) {
		this.vUserEmail = vUserEmail;
	}
	public String getvUserPhone() {
		return vUserPhone;
	}
	public void setvUserPhone(String vUserPhone) {
		this.vUserPhone = vUserPhone;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getUserNick() {
		return userNick;
	}
	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public long getCtime() {
		return ctime;
	}
	public void setCtime(long ctime) {
		this.ctime = ctime;
	}

	
	
	
}

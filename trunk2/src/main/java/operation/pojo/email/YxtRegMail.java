package operation.pojo.email;

import org.springframework.data.annotation.Id;

/**
 * 
* @ClassName: YxtRegMail
* @Description: 小纸条邮件注册邮件
* @author Jack Tang
* @date 2015年1月28日 下午2:51:54
*
 */
public class YxtRegMail {
	@Id
	private String id;
	/**
	 * 邀请人昵称
	 */
    private String userNick;
    //邀请群组
    private String groupName;
    //接收地址
    private String toEmailAddress;
    //注册跳转地址
    private String toUserRegUrl;
    //好友用户名
    private String userName;
    //邀请群主页地址
    private String groupUrl;
    //验证码
    private String code;
    /**
     * 模板名称
     */
    private String tempName;
    
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGroupUrl() {
		return groupUrl;
	}
	public void setGroupUrl(String groupUrl) {
		this.groupUrl = groupUrl;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserNick() {
		return userNick;
	}
	public void setUserNick(String toUserName) {
		this.userNick = toUserName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getToEmailAddress() {
		return toEmailAddress;
	}
	public void setToEmailAddress(String toEmailAddress) {
		this.toEmailAddress = toEmailAddress;
	}
	public String getToUserRegUrl() {
		return toUserRegUrl;
	}
	public void setToUserRegUrl(String toUserRegUrl) {
		this.toUserRegUrl = toUserRegUrl;
	}
	public String getTempName() {
		return tempName;
	}
	public void setTempName(String tempName) {
		this.tempName = tempName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	

}

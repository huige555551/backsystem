package operation.pojo.sms;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="sms")
public class Sms {

	@Id
	private String id;
	@Indexed
	private String phoneNum;
	private String code;
	private long ctime;
	@Indexed
	private long utime;
	@Indexed
	private int checked=0;  //0：未验证 1：以验证 2:因验证次数超过5次无效 3.因重复发送而无效
	@Indexed
	private String type;  //类型  1.申请注册  2.密码修改  3.邀请
	
	private int checkTime=0;  //验证次数
	
	private String toPhoneNum;
	
	private String nickName;
	
	private int  lastNum;//此条短信发送之前的短信条数
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public long getCtime() {
		return ctime;
	}
	public void setCtime(long ctime) {
		this.ctime = ctime;
	}
	public long getUtime() {
		return utime;
	}
	public void setUtime(long utime) {
		this.utime = utime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(int checkTime) {
		this.checkTime = checkTime;
	}
	public int getChecked() {
		return checked;
	}
	public void setChecked(int checked) {
		this.checked = checked;
	}
	public String getToPhoneNum() {
		return toPhoneNum;
	}
	public void setToPhoneNum(String toPhoneNum) {
		this.toPhoneNum = toPhoneNum;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getLastNum() {
		return lastNum;
	}
	public void setLastNum(int lastNum) {
		this.lastNum = lastNum;
	}
}

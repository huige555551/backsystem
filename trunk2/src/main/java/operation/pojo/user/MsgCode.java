package operation.pojo.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="msgCode")
public class MsgCode {

	@Id
	private String id;
	@Indexed
	private String phoneNum;
	private String code;
	private long ctime;
	@Indexed
	private long utime;
	private int checkTime;
	public MsgCode(){
		this.ctime = System.currentTimeMillis();
		this.utime = System.currentTimeMillis();
	}
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
	public void setCtime() {
		this.ctime = System.currentTimeMillis();
	}
	public long getUtime() {
		return utime;
	}
	public void setUtime() {
		this.utime = System.currentTimeMillis();
	}
	public int getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(int checkTime) {
		this.checkTime = checkTime;
	}
	
	

}

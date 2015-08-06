package operation.pojo.user;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="userContact")
public class UserContact {

	@Id
	private String id;
	@Indexed
	private String userId;
	private String userName;//上传者手机号
	private List<Contact> contacts;//通讯录
	private int type; //0 未删除 1 已删除
	private long ctime;
	private long utime;
	

	public UserContact(String userId,String userName) {
		this.setCtime();
		this.setUtime();
		this.userId = userId;
		this.userName = userName;
	
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	
	

}

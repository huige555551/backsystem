package operation.pojo.user;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="userContactList")
public class UserContactList {

	@Id
	private String id;
	@Indexed
	private String userId;
	private String userPhone;
	private Map<String, String> contactList;
	private long ctime;
	private long utime;

	public UserContactList() {
		this.setCtime();
		this.setUtime();
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

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public Map<String, String> getContactList() {
		return contactList;
	}

	public void setContactList(Map<String, String> contactList) {
		this.contactList = contactList;
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

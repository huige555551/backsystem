package operation.pojo.black;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="black")
public class Black {
	@Id
	private String id;
	
	@Indexed
	private String user;  //用户标识，手机号 邮箱 第三方登录号
	
	@Indexed
	private String appKey;  //来源 ，如 ios，android，pc,oss
	
	@Indexed
	private String content;
	
	@Indexed
	private long ctime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getCtime() {
		return ctime;
	}

	public void setCtime(long ctime) {
		this.ctime = ctime;
	}

}

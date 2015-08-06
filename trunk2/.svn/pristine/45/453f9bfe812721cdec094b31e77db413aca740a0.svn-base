package operation.pojo.user;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 用户岗位
 * @author hjn
 *
 */
@Document(collection="userJobs")
public class UserJobs {
	@Id
	private String id;
	@Indexed
	private Object user;
	
//	private Object jobs;
	@Indexed
	private Object nowJobs;
	@Indexed
	private Object favJobs;
	
	
	private Map<Long,String> nowHistory;
	
	private Map<Long,String> favHistory;
	
	
//	private String type;//0:now 1:fav 2:nowHistory 3favHistroy
	
	private long ctime;
	
	private long utime;
	
	public UserJobs(){
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Object getUser() {
		return user;
	}

	public void setUser(Object user) {
		this.user = user;
	}
	public Object getNowJobs() {
		return nowJobs;
	}

	public void setNowJobs(Object nowJobs) {
		this.nowJobs = nowJobs;
	}

	public Object getFavJobs() {
		return favJobs;
	}

	public void setFavJobs(Object favJobs) {
		this.favJobs = favJobs;
	}

	public Map<Long, String> getNowHistory() {
		return nowHistory;
	}

	public void setNowHistory(Map<Long, String> nowHistory) {
		this.nowHistory = nowHistory;
	}

	public Map<Long, String> getFavHistory() {
		return favHistory;
	}

	public void setFavHistory(Map<Long, String> favHistory) {
		this.favHistory = favHistory;
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
	
	
}

package operation.pojo.user;

import java.util.Map;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="userCourseChapter")
public class UserCourseChapter {
	@org.springframework.data.annotation.Id
	private String Id;
	
	@Indexed
	private String userId;
	@Indexed
	private String courseId;
	@Indexed
	private Object chapterId;
	
	private Map<Long,Double> proessid; //学习记录   <时间点,进度>
	
	private double maxProessid;//最后一次学习进度
	
	private int lastTimer; // 最后一次观看时间点
	@Indexed
	private long ctime;//创建时间
	
	private long utime;//更新时间
	
	public UserCourseChapter(){
		super();
	}
	
	public UserCourseChapter(String userId,String courseId,String chapterId){
		this.userId = userId;
		this.courseId = courseId;
		this.chapterId = chapterId;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public Object getChapterId() {
		return chapterId;
	}

	public void setChapterId(Object chapterId) {
		this.chapterId = chapterId;
	}

	public Map<Long, Double> getProessid() {
		return proessid;
	}

	public void setProessid(Map<Long, Double> proessid) {
		this.proessid = proessid;
	}

	public double getMaxProessid() {
		return maxProessid;
	}

	public void setMaxProessid(double maxProessid) {
		this.maxProessid = maxProessid;
	}
	

	public int getLastTimer() {
		return lastTimer;
	}

	public void setLastTimer(int lastTimer) {
		this.lastTimer = lastTimer;
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

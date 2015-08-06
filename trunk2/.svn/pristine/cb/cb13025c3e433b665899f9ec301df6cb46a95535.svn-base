package operation.pojo.course;

import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="userCourse")
public class UserCourse {
	@org.springframework.data.annotation.Id
	private String Id;  
	
	@Indexed
	private Object user;//用户
	@Indexed
	private Object course;//课程
	
	private List<Object> skillId;
	@Indexed
	private int isFav = 0;//是否收藏
	@Indexed
	private int isStudy = 0;//是否学习
	@Indexed
	private int proessid;//最后一次学习进度
	
	private int chapteNum;//课程数量
	@Indexed
	private int isStudyed;//是否学习完成 0 未完成 1 已学完
	
	private long ctime;//创建时间
	
	private long utime;//更新时间
	

	public UserCourse(){
		super();
	}
	
	public UserCourse(String userId,String courseId){
		this.user=userId;
		this.course=courseId;
		long time=System.currentTimeMillis();
		this.ctime=time;
		this.utime=time;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public Object getUser() {
		return user;
	}

	public void setUser(Object user) {
		this.user = user;
	}

	public Object getCourse() {
		return course;
	}

	public void setCourse(Object course) {
		this.course = course;
	}
	
	public List<Object> getSkillId() {
		return skillId;
	}

	public void setSkillId(List<Object> skillId) {
		this.skillId = skillId;
	}

	public int getIsFav() {
		return isFav;
	}

	public void setIsFav(int isFav) {
		this.isFav = isFav;
	}

	public int getIsStudy() {
		return isStudy;
	}

	public void setIsStudy(int isStudy) {
		this.isStudy = isStudy;
	}

	public int getProessid() {
		return proessid;
	}

	public void setProessid(int proessid) {
		this.proessid = proessid;
	}

	public int getChapteNum() {
		return chapteNum;
	}

	public void setChapteNum(int chapteNum) {
		this.chapteNum = chapteNum;
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

	public int getIsStudyed() {
		return isStudyed;
	}

	public void setIsStudyed(int isStudyed) {
		this.isStudyed = isStudyed;
	}
	
	
}

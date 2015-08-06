package operation.pojo.user;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 用户技能
 * @author hjn
 *
 */
@Document(collection="userSkills")
public class UserSkills {

	@Id
	private String id;
	@Indexed
	private String userId;
	@Indexed
	private String jobTitleId;
	@Indexed
	private Object skillId;
	private String status;
	private int progress;
	private String grade;
	private int courseNum; 
	
//	private Map<String,Long> learnedSkills; //掌握
//	
//	private Map<String,Long> favSkills; //感兴趣
//	
//	private Map<String,Long> learningSkills; //正在学习
	
	private long ctime;
	private long utime;
	
	private long skillsVersion;
	
	public void init(){
		
	}
	public void initBySkill(){
		
	}
	
	public UserSkills(){
		this.setCtime();
		this.setUtime();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	public String getJobTitleId() {
		return jobTitleId;
	}

	public void setJobTitleId(String jobTitleId) {
		this.jobTitleId = jobTitleId;
	}

	public Object getSkillId() {
		return skillId;
	}
	public void setSkillId(Object skillId) {
		this.skillId = skillId;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getProgress() {
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
	}
	public int getCourseNum() {
		return courseNum;
	}
	public void setCourseNum(int courseNum) {
		this.courseNum = courseNum;
	}
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public long getSkillsVersion() {
		return skillsVersion;
	}

	public void setSkillsVersion(long skillsVersion) {
		this.skillsVersion = skillsVersion;
	}

	

	
}

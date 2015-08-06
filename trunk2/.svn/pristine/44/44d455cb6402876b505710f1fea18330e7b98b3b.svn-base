package operation.pojo.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="userStudyResult")
public class UserStudyResult {

	@Id
	private String id;
	@Indexed
	private String userId;
	
	private long studyTime;
	
	private int studyCourseNum;
	
	private int ranking;
	
	private long ctime;
	
	private long utime;

	public UserStudyResult(){
		super();
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

	public long getStudyTime() {
		return studyTime;
	}

	public void setStudyTime(long studyTime) {
		this.studyTime = studyTime;
	}

	public int getStudyCourseNum() {
		return studyCourseNum;
	}

	public void setStudyCourseNum(int studyCourseNum) {
		this.studyCourseNum = studyCourseNum;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
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

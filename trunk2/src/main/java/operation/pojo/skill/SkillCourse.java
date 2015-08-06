package operation.pojo.skill;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="skillCourse")
public class SkillCourse {

	@Id
	private String id;
	@Indexed
	private Object skill;
	@Indexed
	private Object course;
	
	private long ctime;
	
	private long utime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Object getSkill() {
		return skill;
	}

	public void setSkill(Object skill) {
		this.skill = skill;
	}
	
	public Object getCourse() {
		return course;
	}

	public void setCourse(Object course) {
		this.course = course;
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

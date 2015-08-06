package operation.pojo.skill;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 岗位技能
 * @author hjn
 *
 */
@Document(collection="jobSkills")
public class JobSkills {

	@Id
	private String id;
	@Indexed
	private Object jobs;
	
	private List<Object> skills;
	@Indexed
	private String weight;
	
	private long ctime;
	
	private long utime;

	private long version;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Object getJobs() {
		return jobs;
	}

	public void setJobs(Object jobs) {
		this.jobs = jobs;
	}

	public List<Object> getSkills() {
		return skills;
	}

	public void setSkills(List<Object> skills) {
		this.skills = skills;
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

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
	
}

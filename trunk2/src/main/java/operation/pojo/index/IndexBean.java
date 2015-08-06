package operation.pojo.index;

import java.util.List;

import operation.pojo.ad.ZtiaoAd;

import org.springframework.data.annotation.Id;

public class IndexBean {
	@Id
	private String id;
	
	private List<Object> group;
	
	private List<Object> topic;
	
	private List<Object> drycargo;
	
	private List<Object> course;
	
	private List<ZtiaoAd> ztiaoAd;
	
	private long ctime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Object> getGroup() {
		return group;
	}

	public void setGroup(List<Object> group) {
		this.group = group;
	}

	public List<Object> getTopic() {
		return topic;
	}

	public void setTopic(List<Object> topic) {
		this.topic = topic;
	}

	public List<Object> getDrycargo() {
		return drycargo;
	}

	public void setDrycargo(List<Object> drycargo) {
		this.drycargo = drycargo;
	}

	public List<Object> getCourse() {
		return course;
	}

	public void setCourse(List<Object> course) {
		this.course = course;
	}

	public long getCtime() {
		return ctime;
	}

	public void setCtime(long ctime) {
		this.ctime = ctime;
	}

	public List<ZtiaoAd> getZtiaoAd() {
		return ztiaoAd;
	}

	public void setZtiaoAd(List<ZtiaoAd> ztiaoAd) {
		this.ztiaoAd = ztiaoAd;
	}

}

package operation.pojo.course;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="course")
public class Course {

	
	@Id
	private String id;
	@Indexed
	private String title;//课程名称

	private String intro;//课程描述
	
	private List<Object> chapters;
	
	private List<Object> tags;//课程标签（预留）
	
	private Map<String,Long> whoFav;//收藏
	
	private int favCount;//收藏数量
	
	private Map<String,Long> whoShare;//分享
	
	private int shareCount;//分享数量
	
	private Map<String,Long> whoStudy;//学习
	
	private int studyCount;//学习数量
	
	private long ctime;//创建时间
	
	private long utime;//更新时间
	
	private String logoUrl;

	public Course(){
		super();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public List<Object> getTags() {
		return tags;
	}

	public void setTags(List<Object> tags) {
		this.tags = tags;
	}
	
	

	public int getFavCount() {
		return favCount;
	}

	public void setFavCount(int favCount) {
		this.favCount = favCount;
	}

	public int getShareCount() {
		return shareCount;
	}

	public void setShareCount(int shareCount) {
		this.shareCount = shareCount;
	}

	public int getStudyCount() {
		return studyCount;
	}

	public void setStudyCount(int studyCount) {
		this.studyCount = studyCount;
	}
	
	public Map<String, Long> getWhoFav() {
		return whoFav;
	}

	public void setWhoFav(Map<String, Long> whoFav) {
		this.whoFav = whoFav;
	}

	public Map<String, Long> getWhoShare() {
		return whoShare;
	}

	public void setWhoShare(Map<String, Long> whoShare) {
		this.whoShare = whoShare;
	}

	public Map<String, Long> getWhoStudy() {
		return whoStudy;
	}

	public void setWhoStudy(Map<String, Long> whoStudy) {
		this.whoStudy = whoStudy;
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

	public List<Object> getChapters() {
		return chapters;
	}

	public void setChapters(List<Object> chapters) {
		this.chapters = chapters;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

}

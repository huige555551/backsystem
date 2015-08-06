package operation.pojo.course;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="groupCourse")
public class GroupCourse  {
	@Id
	private String Id;
	@Indexed
	private Object course;  //课程(数据库存储课程ID)
	@Indexed
	private Object group;//群组（数据库存储群组ID）
	private Map<String,Long> whoImport; //把课程分享至此小组课堂的人员列表
	private int importCount; //把课程分享至此小组课堂的人员统计
	private Map<String,Long> whoFav;//小组课堂收藏人员列表
	private int favCount;//收藏人数统计
	private Map<String,Long> whoStudy;//小组课堂学习人员列表
	private int studyCount;//学习人员统计
	private Map<String,Long> whoShare;//小组课堂分享至别的群组或目的地址的人员列表
	private int shareCount;//分享人员统计
	private long ctime;//创建时间
	private long utime;//修改时间
	private String sharePerson;// 分享人（暂用）
	private int disPlay;//0：公开课，10：私有课 20：小组删除
	public GroupCourse(){
		super();
		
	}
	
	public GroupCourse(String groupId,String courseId,String userId){
		this.group=groupId;
		this.course=courseId;
		long time=System.currentTimeMillis();
		Map<String,Long> whoImport=new HashMap<String, Long>();
		whoImport.put(userId, time);
		this.whoImport=whoImport;
		this.importCount=1;
		this.favCount=0;
		this.studyCount=0;
		this.shareCount=0;
		this.ctime=time;
		this.utime=time;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public Object getCourse() {
		return course;
	}

	public void setCourse(Object course) {
		this.course = course;
	}

	public Object getGroup() {
		return group;
	}

	public void setGroup(Object group) {
		this.group = group;
	}

	public Map<String, Long> getWhoImport() {
		return whoImport;
	}

	public void setWhoImport(Map<String, Long> whoImport) {
		this.whoImport = whoImport;
	}

	public int getImportCount() {
		return importCount;
	}

	public void setImportCount(int importCount) {
		this.importCount = importCount;
	}

	public Map<String, Long> getWhoFav() {
		return whoFav;
	}

	public void setWhoFav(Map<String, Long> whoFav) {
		this.whoFav = whoFav;
	}

	public int getFavCount() {
		return favCount;
	}

	public void setFavCount(int favCount) {
		this.favCount = favCount;
	}

	public Map<String, Long> getWhoStudy() {
		return whoStudy;
	}

	public void setWhoStudy(Map<String, Long> whoStudy) {
		this.whoStudy = whoStudy;
	}

	public int getStudyCount() {
		return studyCount;
	}

	public void setStudyCount(int studyCount) {
		this.studyCount = studyCount;
	}

	public Map<String, Long> getWhoShare() {
		return whoShare;
	}

	public void setWhoShare(Map<String, Long> whoShare) {
		this.whoShare = whoShare;
	}

	public int getShareCount() {
		return shareCount;
	}

	public void setShareCount(int shareCount) {
		this.shareCount = shareCount;
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

	public String getSharePerson() {
		return sharePerson;
	}

	public void setSharePerson(String sharePerson) {
		this.sharePerson = sharePerson;
	}

	public int getDisPlay() {
		return disPlay;
	}

	public void setDisPlay(int disPlay) {
		this.disPlay = disPlay;
	}
}

package operation.pojo.topics;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="subPost")
public class SubPost {

	@Id
	
	private String post_id;  //帖子PID
	private String topicId; // 归属主题ID
	private String appKey;//
	
	private int layer;//所在楼层		
	private String authorId; // 创建人id
	private String authorName; // 创建人名字
	private String authorLogoUrl;//创建者图片
	private long ctime; // 创建时间
	private long utime; // 更新时间
	private String message;//内容
//	private boolean hasAudio =false;//是否有语音
//	private boolean hasPic =false;//是否有语音
	private String type;//类型  0:文件 1：语音 2：图片带有图片
//	private List<Integer> file_ids;//文件id集合 云存储生成
	private String fileUrl;//文件id集合 云存储生成
	
	private boolean isDeleted = false; // 是否已删除
	@Indexed
	private String parentId; //父帖id
    private String toId;//父类帖子发帖人id
    private String toName;//父类帖子发帖人名字
    
    private double lat;// 纬度
	private double [] position;//位置
	private double lng;// 经度
	public String getPost_id() {
		return post_id;
	}
	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}
	public String getTopicId() {
		return topicId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public int getLayer() {
		return layer;
	}
	public void setLayer(int layer) {
		this.layer = layer;
	}
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getAuthorLogoUrl() {
		return authorLogoUrl;
	}
	public void setAuthorLogoUrl(String authorLogoUrl) {
		this.authorLogoUrl = authorLogoUrl;
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getToId() {
		return toId;
	}
	public void setToId(String toId) {
		this.toId = toId;
	}
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double[] getPosition() {
		return position;
	}
	public void setPosition(double[] position) {
		this.position = position;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}

}

package operation.pojo.xuanye;

import java.util.List;
import java.util.Map;

import operation.pojo.user.UserShort;

import org.springframework.data.annotation.Id;

public class Xuanye {
	@Id
	private String id;
	private Object group;//群组（数据库存储群组ID）
	private String url; //干货的源地址
	private String fileUrl; //干货的关键图片或路径
	private String message;//干货的内容
	private String description;//干货信息
	private long ctime;//创建时间
	private long utime;//修改时间
	private List<UserShort> sharePerList;// 分享人列表
	public List<Object> shareids;// 分享人id列表查询使用
	private Map<String,Long> whoView;//浏览人
	private int viewCount; //浏览量
	private Object XuanyeTagName;
	
	public Xuanye() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Object getGroup() {
		return group;
	}

	public void setGroup(Object group) {
		this.group = group;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public List<UserShort> getSharePerList() {
		return sharePerList;
	}

	public void setSharePerList(List<UserShort> sharePerList) {
		this.sharePerList = sharePerList;
	}

	public List<Object> getShareids() {
		return shareids;
	}

	public void setShareids(List<Object> shareids) {
		this.shareids = shareids;
	}

	public Map<String, Long> getWhoView() {
		return whoView;
	}

	public void setWhoView(Map<String, Long> whoView) {
		this.whoView = whoView;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public Object getXuanyeTagName() {
		return XuanyeTagName;
	}

	public void setXuanyeTagName(Object xuanyeTagName) {
		XuanyeTagName = xuanyeTagName;
	}



	
}

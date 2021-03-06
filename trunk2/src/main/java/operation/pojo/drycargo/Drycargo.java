package operation.pojo.drycargo;

import java.util.List;

import operation.pojo.group.XueWenGroup;
import operation.pojo.topics.Images;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

public class Drycargo {
	@Id
	private String id;
	@Indexed
	private Object group;//群组（数据库存储群组ID）
	private String groupName;//群名称
	private String groupLogoUrl;//群图片
	@Indexed
	private String url; //干货的源地址
	private String fileUrl; //干货的关键图片或路径
	@Indexed
	private String message;//干货的title
	private String description;//干货描述信息
	private long ctime;//创建时间
	private long utime;//修改时间	
	private String authorId; // 创建人id
	private String authorName; // 创建人名称
	private String authorLogoUrl;//创建者图片

	private int viewCount; //浏览量
	
	private String drycargoTagName;
	
	private int replyCount; // 回复数
	private int likesCount; // 被点赞的次数
	
	private int unLikeCount;//不赞数量
	
	private int shareCount;
	
	private List<String> whoImport; //把干货分享至此小组课堂的人员列表
	private int importCount; //把干货分享至此小组课堂的人员统计
	
	private int favCount;//收藏人数统计
	
	private int weightSort;//权重
	@Indexed
	private int dryFlag;//干货0炫页1
	
	private String categoryId;//一级分类id;
	private String childCategoryId;//二级分类Id
	
	@Indexed
	private boolean review;//审核标示
	
	private float picWidth;//图片宽度
	private float picHeight;//图片高度
	
	private int isOriginal;//是否原创 0 转摘 1 原创
	
	private int displayOrder; // 1 置顶 0 取消置顶
	
	private long displayTime; // 置顶时间	
	
	private List<Images> images;//图片
	
	private String context;//干货内容
	
	public Drycargo(){
		super();
		
	}
	
	public Drycargo(XueWenGroup group,String userId){
		this.group = group.getId();
		this.groupName=group.getGroupName();
		long time=System.currentTimeMillis();
		this.viewCount = 0;
		this.replyCount = 0;
		this.likesCount = 0;
		this.unLikeCount = 0;
		this.shareCount=0;
		this.ctime=time;
		this.utime=time;
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

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public String getDrycargoTagName() {
		return drycargoTagName;
	}

	public void setDrycargoTagName(String drycargoTagName) {
		this.drycargoTagName = drycargoTagName;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public int getLikesCount() {
		return likesCount;
	}

	public void setLikesCount(int likesCount) {
		this.likesCount = likesCount;
	}

	public int getUnLikeCount() {
		return unLikeCount;
	}

	public void setUnLikeCount(int unLikeCount) {
		this.unLikeCount = unLikeCount;
	}

	public int getShareCount() {
		return shareCount;
	}

	public void setShareCount(int shareCount) {
		this.shareCount = shareCount;
	}

	public int getFavCount() {
		return favCount;
	}

	public void setFavCount(int favCount) {
		this.favCount = favCount;
	}

	public int getWeightSort() {
		return weightSort;
	}

	public void setWeightSort(int weightSort) {
		this.weightSort = weightSort;
	}

	public int getDryFlag() {
		return dryFlag;
	}

	public void setDryFlag(int dryFlag) {
		this.dryFlag = dryFlag;
	}

	public List<String> getWhoImport() {
		return whoImport;
	}

	public void setWhoImport(List<String> whoImport) {
		this.whoImport = whoImport;
	}

	public int getImportCount() {
		return importCount;
	}

	public void setImportCount(int importCount) {
		this.importCount = importCount;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getChildCategoryId() {
		return childCategoryId;
	}

	public void setChildCategoryId(String childCategoryId) {
		this.childCategoryId = childCategoryId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public boolean isReview() {
		return review;
	}

	public void setReview(boolean review) {
		this.review = review;
	}

	public float getPicWidth() {
		return picWidth;
	}

	public void setPicWidth(float picWidth) {
		this.picWidth = picWidth;
	}

	public float getPicHeight() {
		return picHeight;
	}

	public void setPicHeight(float picHeight) {
		this.picHeight = picHeight;
	}

	public int getIsOriginal() {
		return isOriginal;
	}

	public void setIsOriginal(int isOriginal) {
		this.isOriginal = isOriginal;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public long getDisplayTime() {
		return displayTime;
	}

	public void setDisplayTime(long displayTime) {
		this.displayTime = displayTime;
	}

	public String getGroupLogoUrl() {
		return groupLogoUrl;
	}

	public void setGroupLogoUrl(String groupLogoUrl) {
		this.groupLogoUrl = groupLogoUrl;
	}

	public List<Images> getImages() {
		return images;
	}

	public void setImages(List<Images> images) {
		this.images = images;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}


}

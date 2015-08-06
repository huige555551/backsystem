package operation.pojo.file;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="fileOperationInfo")
public class FileOperationInfo {

	@Id
	private String id;
	private Object fileMD5;//文件MD5
	@Indexed
	private Object uploadUser;//上传者
	private long uploadTime;//上传
	private String fileName;//文件在空间中的显示名称
	private String intro;//文件描述
	private List<Object> tags;//文件标签
	private List<Object> wholiked;//赞
	private int likeCount;//赞数量
	private List<Object> whounliked;//不攒
	private int unLikeCount;//不攒数量
	private List<Object> whoShared;//分享
	private int shareCount;//分享数量
	private List<Object> whoAttention;  //谁关注了
	private int attentionCount;  //关注的数量
	private List<Object> whoRead;
	private int readCount;//阅读次数
	private List<Object> whoDownload;
	private int downloadCount;//下载次数
	
	//收藏
	private List<Object> whoCollect;
	private int collectCount;
	//学习
	private List<Object> whoStudy;
	private int studyCount;
	
	public List<Object> getWhoCollect() {
		return whoCollect;
	}
	public void setWhoCollect(List<Object> whoCollect) {
		this.whoCollect = whoCollect;
	}
	public int getCollectCount() {
		return collectCount;
	}
	public void setCollectCount(int collectCount) {
		this.collectCount = collectCount;
	}
	public List<Object> getWhoStudy() {
		return whoStudy;
	}
	public void setWhoStudy(List<Object> whoStudy) {
		this.whoStudy = whoStudy;
	}
	public int getStudyCount() {
		return studyCount;
	}
	public void setStudyCount(int studyCount) {
		this.studyCount = studyCount;
	}
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Object getFileMD5() {
		return fileMD5;
	}
	public void setFileMD5(Object fileMD5) {
		this.fileMD5 = fileMD5;
	}
	public Object getUploadUser() {
		return uploadUser;
	}
	public void setUploadUser(Object uploadUser) {
		this.uploadUser = uploadUser;
	}
	public long getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(long uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
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
	public List<Object> getWholiked() {
		return wholiked;
	}
	public void setWholiked(List<Object> wholiked) {
		this.wholiked = wholiked;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public List<Object> getWhounliked() {
		return whounliked;
	}
	public void setWhounliked(List<Object> whounliked) {
		this.whounliked = whounliked;
	}
	public int getUnLikeCount() {
		return unLikeCount;
	}
	public void setUnLikeCount(int unLikeCount) {
		this.unLikeCount = unLikeCount;
	}
	public List<Object> getWhoShared() {
		return whoShared;
	}
	public void setWhoShared(List<Object> whoShared) {
		this.whoShared = whoShared;
	}
	public int getShareCount() {
		return shareCount;
	}
	public void setShareCount(int shareCount) {
		this.shareCount = shareCount;
	}
	public List<Object> getWhoAttention() {
		return whoAttention;
	}
	public void setWhoAttention(List<Object> whoAttention) {
		this.whoAttention = whoAttention;
	}
	public int getAttentionCount() {
		return attentionCount;
	}
	public void setAttentionCount(int attentionCount) {
		this.attentionCount = attentionCount;
	}
	public List<Object> getWhoRead() {
		return whoRead;
	}
	public void setWhoRead(List<Object> whoRead) {
		this.whoRead = whoRead;
	}
	public List<Object> getWhoDownload() {
		return whoDownload;
	}
	public void setWhoDownload(List<Object> whoDownload) {
		this.whoDownload = whoDownload;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public int getDownloadCount() {
		return downloadCount;
	}
	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}
}

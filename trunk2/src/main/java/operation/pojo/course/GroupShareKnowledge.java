package operation.pojo.course;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

/**
 * 
 * @ClassName: GroupShareKnowledge
 * @Description: 群分享知识
 * @author
 * @date 2014年12月24日 上午11:00:49
 *
 */
public class GroupShareKnowledge {
	@Id
	private String id;
	@Indexed
	private String knowledge;
	@Indexed
	private String groupId;// 群id
	@Indexed
	private String shareUserId;// 分享人id
	private long shareTime;// 分享时间
    @Indexed
	private String kngName;
	private String groupName;
	private String shareUserName;
	private String userLogo;
	private String groupLogoUrl;
	private int kngType ;
	private String kngTagName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKnowledge() {
		return knowledge;
	}
	public void setKnowledge(String knowledge) {
		this.knowledge = knowledge;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getShareUserId() {
		return shareUserId;
	}
	public void setShareUserId(String shareUserId) {
		this.shareUserId = shareUserId;
	}
	public long getShareTime() {
		return shareTime;
	}
	public void setShareTime(long shareTime) {
		this.shareTime = shareTime;
	}
	public String getKngName() {
		return kngName;
	}
	public void setKngName(String kngName) {
		this.kngName = kngName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getShareUserName() {
		return shareUserName;
	}
	public void setShareUserName(String shareUserName) {
		this.shareUserName = shareUserName;
	}
	public String getUserLogo() {
		return userLogo;
	}
	public void setUserLogo(String userLogo) {
		this.userLogo = userLogo;
	}
	public String getGroupLogoUrl() {
		return groupLogoUrl;
	}
	public void setGroupLogoUrl(String groupLogoUrl) {
		this.groupLogoUrl = groupLogoUrl;
	}
	public int getKngType() {
		return kngType;
	}
	public void setKngType(int kngType) {
		this.kngType = kngType;
	}
	public String getKngTagName() {
		return kngTagName;
	}
	public void setKngTagName(String kngTagName) {
		this.kngTagName = kngTagName;
	}
    
	
}

package operation.pojo.tags;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 记录用户打标签行为，每一次用户打标签都记录一次，不考虑重复
 * @author yangquanliang
 *
 */
@Document(collection="usertag")
public class UserTagBean {

	@Id
	public String id;
	
	/**
	 * tag名称
	 */
	@Indexed
	public String tagName;
	/**
	 * 用户id
	 */
	public String userId;
	/**
	 * 用户名
	 */
	public String userName;
	/**
	 * 打标签对象的的
	 * 给用户打标签就是用户的id 给课程打标签就是课程id 给小组打标签就是小组id，话题 分享相同
	 */
	public String itemId;
	
	/**
	 * id 1.用户 2.课程 3.小组 4.话题 5分享  6干货  7附件
	 */
	public String itemType;
	
	/**
	 * 创建时间
	 */
	public String ctime;
	
	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

}

package operation.pojo.tags;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 基础的TagBean
 * 基础库中来源用户添加和从相似网站抓取
 * @author yangquanliang
 *
 */
@Document(collection="tagbase")
public class TagBean {

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getTagType() {
		return tagType;
	}

	public void setTagType(String tagType) {
		this.tagType = tagType;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Id
	String id;

	/**
	 * tag名称
	 */
	@Indexed
	public String tagName;
	
	/**
	 * tag对象类型 1.用户 2.课程 3.小组 4.话题 5分享 6干货  7附件
	 */
	public String tagType;
	
	/**
	 * 状态 1可用 -1不可用 默认可用
	 */
	public int status =1;

	/**
	 * 热度分值
	 */
	public int score;
}

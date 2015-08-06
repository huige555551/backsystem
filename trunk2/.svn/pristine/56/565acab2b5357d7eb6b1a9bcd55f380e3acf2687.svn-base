package operation.pojo.news;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
* @ClassName: NewsBean
* @Description: 新闻对象
* @author yangquanliang
* @date 2015年2月4日 下午3:22:15
*
*/
@Document(collection="news")
public class NewsBean implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/ 
	private static final long serialVersionUID = 1L;
	
	@Id
	public String id;


	/**
	 * 资讯类型 具体见MessageConstant 推荐为1
	 */
	public String item_type;
	
	/**
	 * 资讯的标题
	 */
	public String title;
	
	/**
	 * 资讯详细内容的jsonArray串
	 */
	public String content;
		
	/**
	 * 资讯来源
	 */
	public String from;
	
	/**
	 * 消息更新时间 时间戳 毫秒 来源于抓取网站的时间
	 */
	public long publish_time;
	
	
	/**
	 * 预览图片地址 可能是 多个图片地址 用|分隔
	 */
	public List<String> prePicUrl=new ArrayList<String>();
	
	
	/**
	 * 资讯本地创建时间
	 */
	public long create_time;
	
	/**
	 * 被浏览次数 
	 */
	public long views;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItem_type() {
		return item_type;
	}

	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public long getPublish_time() {
		return publish_time;
	}

	public void setPublish_time(long publish_time) {
		this.publish_time = publish_time;
	}

	public List<String> getPrePicUrl() {
		return prePicUrl;
	}

	public void setPrePicUrl(List<String> prePicUrl) {
		this.prePicUrl = prePicUrl;
	}

	public long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(long create_time) {
		this.create_time = create_time;
	}

	public long getViews() {
		return views;
	}

	public void setViews(long views) {
		this.views = views;
	}

}


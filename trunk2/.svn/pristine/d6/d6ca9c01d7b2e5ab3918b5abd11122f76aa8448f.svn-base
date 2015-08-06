package operation.pojo.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
* @ClassName: UserHead
* @Description: 用户头像
* @author yangquanliang
* @date 2015年1月28日 下午5:26:24
*
 */
@Document(collection="userhead")
public class UserHead {

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Id
	private String id;
	
	private String url;
}

package operation.pojo.common;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
* @ClassName: ConfigChange
* @Description: 配置修改baseurl
* @author yaoj
* @date 2015年1月5日 下午3:17:28
*
 */
@Document(collection="configChange")
public class ConfigChange {
	
	@Id
	private String id;
	
	private String url;			//云配置里删除的baseurl中的一个
	
	private String configId;	//对应云配置的id

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

	public String getConfigId() {
		return configId;
	}

	public void setConfigId(String configId) {
		this.configId = configId;
	}

}

package operation.pojo.callpolice;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @ClassName: Ad
 * @Description: 广告位
 * @author tangli
 * @date 2015年3月17日 下午1:35:16
 *
 */
@Document(collection="callpolice")
public class Callpolice {
	@Id
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getCtime() {
		return ctime;
	}
	public void setCtime(long ctime) {
		this.ctime = ctime;
	}
	private String sourceId;//来源id
	private String type;//类型  dry  topic course 
	private long ctime;
	


}

/**   
* @Title: ColudConfig.java
* @Package operation.repo.common
* @Description:
* @author yaoj
* @date 2014年12月17日 下午4:27:57
* @version V1.0
*/ 
package operation.pojo.common;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/** 
 * @ClassName: ColudConfig
 * @Description: 云存储服务器的配置信息
 * @author yaoj
 * @date 2014年12月17日 下午4:27:57
 * 
 */
@Document(collection="coludConfig")
public class ColudConfig {

	@Id
	private String id;
	
	private String ckey;	//文件配置key，唯一
	
	private String category;//文件配置分类，按业务分类
	
	private String desc;	//配置描述
	
	private String bucket;	//空间名称
	
	private String filters;	//文件上传时的filter配置，允许的格式和大小
	
	private String fileParam;	//文件参数配置
	
	private String pathrule;//文件路径规则
	
	private List<String> baseUrls;
	
	private String pops;	//视频或文档转码参数
	
	private String cburl;	//文件处理后的回调api
	
	private long ctime;
	
	private long utime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCkey() {
		return ckey;
	}

	public void setCkey(String ckey) {
		this.ckey = ckey;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	public String getFilters() {
		return filters;
	}

	public void setFilters(String filters) {
		this.filters = filters;
	}

	public String getPathrule() {
		return pathrule;
	}

	public void setPathrule(String pathrule) {
		this.pathrule = pathrule;
	}

	public List<String> getBaseUrls() {
		return baseUrls;
	}

	public void setBaseUrls(List<String> baseUrls) {
		this.baseUrls = baseUrls;
	}

	public String getPops() {
		return pops;
	}

	public void setPops(String pops) {
		this.pops = pops;
	}

	public String getCburl() {
		return cburl;
	}

	public void setCburl(String cburl) {
		this.cburl = cburl;
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

	public String getFileParam() {
		return fileParam;
	}

	public void setFileParam(String fileParam) {
		this.fileParam = fileParam;
	}
	
}

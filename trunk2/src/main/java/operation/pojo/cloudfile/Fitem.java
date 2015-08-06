/**   
* @Title: Fitem.java
* @Package test
* @Description: TODO(用一句话描述该文件做什么)
* @author yaoj
* @date 2014年12月17日 上午9:17:22
* @version V1.0
*/ 
package operation.pojo.cloudfile;

/** 
 * @ClassName: Fitem
 * @Description: 前段使用，只存不做操作
 * @author yaoj
 * @date 2014年12月17日 上午9:17:22
 * 
 */
public class Fitem {
	
	private String code;
	
	private String error;

	private String hash;
	
	private String key;
	
	private String itemHttpUrl;	//访问绝对地址

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getItemHttpUrl() {
		return itemHttpUrl;
	}

	public void setItemHttpUrl(String itemHttpUrl) {
		this.itemHttpUrl = itemHttpUrl;
	}
	
}

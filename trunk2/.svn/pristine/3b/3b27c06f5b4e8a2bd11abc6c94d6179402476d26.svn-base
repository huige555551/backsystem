/**   
* @Title: AttachFile.java
* @Package test
* @Description: 上传的文件信息
* @author yaoj
* @date 2014年12月16日 下午4:07:37
* @version V1.0
*/ 
package operation.pojo.cloudfile;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/** 
 * @ClassName: AttachFile
 * @Description: 上传的文件信息
 * @author yaoj
 * @date 2014年12月16日 下午4:07:37
 * 
 */
@Document(collection="attachFile")
public class AttachFile {

	@Id
	private String id;	
	
	private int arc;	//引用次数
	
	private String ckey;	//文件配置FileSeting
	
	private String furl;	//文件全路径，唯一，用于图片引用时外链键
	
	private String ftype;	//文件类型：1-图片 2-文档 3-视频 4-zip 5-附件100-其他
	
	private String fkey;	//文件相对路径（ex：/12/name.pdf）
	
	private String fname;	//用户上传时的文件名称（ex：name.pdf）
	
	private String mimetype;
	
	private String fhash;	//文件在七牛存储的hash值唯一
	
	private long fsize;	//文件大小bit
	
	private long putTime;	//上传时间
	
	private long ctime;     //创建时间
	
	private long utime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getArc() {
		return arc;
	}

	public void setArc(int arc) {
		this.arc = arc;
	}

	public String getCkey() {
		return ckey;
	}

	public void setCkey(String ckey) {
		this.ckey = ckey;
	}

	public String getFurl() {
		return furl;
	}

	public void setFurl(String furl) {
		this.furl = furl;
	}

	public String getFtype() {
		return ftype;
	}

	public void setFtype(String ftype) {
		this.ftype = ftype;
	}

	public String getFkey() {
		return fkey;
	}

	public void setFkey(String fkey) {
		this.fkey = fkey;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getMimetype() {
		return mimetype;
	}

	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}

	public String getFhash() {
		return fhash;
	}

	public void setFhash(String fhash) {
		this.fhash = fhash;
	}

	public long getFsize() {
		return fsize;
	}

	public void setFsize(long fsize) {
		this.fsize = fsize;
	}

	public long getPutTime() {
		return putTime;
	}

	public void setPutTime(long putTime) {
		this.putTime = putTime;
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

}

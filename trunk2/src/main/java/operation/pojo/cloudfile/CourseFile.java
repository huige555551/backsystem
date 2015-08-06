/**   
* @Title: CourseFile.java
* @Package test
* @Description: 课程文件
* @author yaoj
* @date 2014年12月17日 上午9:06:22
* @version V1.0
*/ 
package operation.pojo.cloudfile;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/** 
 * @ClassName: CourseFile
 * @Description: 课程文件
 * @author yaoj
 * @date 2014年12月17日 上午9:06:22
 * 
 */
@Document(collection="courseFile")
public class CourseFile {
	@Id
	private String id;	
	
	private int refCount;	//引用次数
	
	private String ckey;	//配置信息id
	
	private String httpUrl;	//访问原文件在云存储的绝对地址
	
	private String ftype;	//附件类型
	
	private String fkey;	//文件地址（ex：/12/name.pdf）
	
	private String fname;	//文件名（ex：name.pdf）
	
	private long fsize;	//文件大小bit
	
	private int imgWidth;	//图片宽（像素）
	
	private int imgHeight;	//图片高（像素）
	
	private long putTime;	//上传时间
	
	private String fid;
	
	private String fhash;
	
	private String fcode;
	
	private String fdesc;
	
	private List<Citem> fitems;
	
	private long ctime;     //创建时间
	
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

	public int getImgWidth() {
		return imgWidth;
	}

	public void setImgWidth(int imgWidth) {
		this.imgWidth = imgWidth;
	}

	public int getImgHeight() {
		return imgHeight;
	}

	public void setImgHeight(int imgHeight) {
		this.imgHeight = imgHeight;
	}

	public long getPutTime() {
		return putTime;
	}

	public void setPutTime(long putTime) {
		this.putTime = putTime;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getFhash() {
		return fhash;
	}

	public void setFhash(String fhash) {
		this.fhash = fhash;
	}

	public String getFcode() {
		return fcode;
	}

	public void setFcode(String fcode) {
		this.fcode = fcode;
	}

	public String getFdesc() {
		return fdesc;
	}

	public void setFdesc(String fdesc) {
		this.fdesc = fdesc;
	}

	public List<Citem> getFitems() {
		return fitems;
	}

	public void setFitems(List<Citem> fitems) {
		this.fitems = fitems;
	}

	public String getHttpUrl() {
		return httpUrl;
	}

	public void setHttpUrl(String httpUrl) {
		this.httpUrl = httpUrl;
	}

	public int getRefCount() {
		return refCount;
	}

	public void setRefCount(int refCount) {
		this.refCount = refCount;
	}

	public long getCtime() {
		return ctime;
	}

	public void setCtime(long ctime) {
		this.ctime = ctime;
	}

	public long getFsize() {
		return fsize;
	}

	public void setFsize(long fsize) {
		this.fsize = fsize;
	}

	public long getUtime() {
		return utime;
	}

	public void setUtime(long utime) {
		this.utime = utime;
	}
	
}

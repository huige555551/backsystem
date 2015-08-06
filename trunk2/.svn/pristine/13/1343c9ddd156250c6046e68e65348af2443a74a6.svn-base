package operation.pojo.file;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="fileStoreInfo")
public class FileStoreInfo {

	@Id
	private String id;  //Id 数据库自生成
	@Indexed
	private String fileMD5; //文件唯一MD5码
	private String fileServerName;//服务器文件名
	private String fileContext;//文件类型
	private String fileSuffix;//文件后缀
	private String fileLocal;//文件存储地址
	private String fileUrl;//文件下载地址
	private long fileLength;//文件大小
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFileMD5() {
		return fileMD5;
	}
	public void setFileMD5(String fileMD5) {
		this.fileMD5 = fileMD5;
	}
	public String getFileServerName() {
		return fileServerName;
	}
	public void setFileServerName(String fileServerName) {
		this.fileServerName = fileServerName;
	}
	public String getFileContext() {
		return fileContext;
	}
	public void setFileContext(String fileContext) {
		this.fileContext = fileContext;
	}
	public String getFileSuffix() {
		return fileSuffix;
	}
	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}
	public String getFileLocal() {
		return fileLocal;
	}
	public void setFileLocal(String fileLocal) {
		this.fileLocal = fileLocal;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public long getFileLength() {
		return fileLength;
	}
	public void setFileLength(long fileLength) {
		this.fileLength = fileLength;
	}
	
	
}

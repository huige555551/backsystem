package operation.pojo.file;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="fileOperation")
public class FileOperation {

	@Id
	private String id;
	@Indexed
	private Object user;//操作人
	@Indexed
	private Object fileOperationInfo;
	private List<Long> read;
	private List<Long> download;
	private List<Long> live;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Object getUser() {
		return user;
	}
	public void setUser(Object user) {
		this.user = user;
	}
	public Object getFileOperationInfo() {
		return fileOperationInfo;
	}
	public void setFileOperationInfo(Object fileOperationInfo) {
		this.fileOperationInfo = fileOperationInfo;
	}
	public List<Long> getRead() {
		return read;
	}
	public void setRead(List<Long> read) {
		this.read = read;
	}
	public List<Long> getDownload() {
		return download;
	}
	public void setDownload(List<Long> download) {
		this.download = download;
	}
	public List<Long> getLive() {
		return live;
	}
	public void setLive(List<Long> live) {
		this.live = live;
	}
	
	
}

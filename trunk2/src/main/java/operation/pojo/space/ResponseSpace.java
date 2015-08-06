package operation.pojo.space;

import java.util.List;

import operation.pojo.file.FileOperationInfo;


public class ResponseSpace {

	private String id;
	private Object spaceAdmin;
	private List<FileOperationInfo> files;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Object getSpaceAdmin() {
		return spaceAdmin;
	}
	public void setSpaceAdmin(Object spaceAdmin) {
		this.spaceAdmin = spaceAdmin;
	}
	public List<FileOperationInfo> getFiles() {
		return files;
	}
	public void setFiles(List<FileOperationInfo> files) {
		this.files = files;
	}
}

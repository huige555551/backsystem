package operation.pojo.space;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="space")
public class Space {

	@Id
	private String id;
	@Indexed
	private Object spaceAdmin;
	private List<Object> files;
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
	public List<Object> getFiles() {
		return files;
	}
	public void setFiles(List<Object> files) {
		this.files = files;
	}
	
}

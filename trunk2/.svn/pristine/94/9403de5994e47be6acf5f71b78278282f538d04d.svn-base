package operation.pojo.remmond;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="remmond")
public class Remmond {
	@Id
	private String id;

	@Indexed
	private String userId;

	private Map<String, String> groupId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Map<String, String> getGroupId() {
		return groupId;
	}

	public void setGroupId(Map<String, String> groupId) {
		this.groupId = groupId;
	}

}

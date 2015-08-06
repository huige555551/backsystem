package operation.pojo.group;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="myGroup")
public class MyGroup {

	@Id
	private String Id;
	@Indexed
	private String userId;
	private List<String> groupIds;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<String> getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(List<String> groupIds) {
		this.groupIds = groupIds;
	}

	public void addGroupId(String groupId) {
		this.groupIds.add(groupId);
	}
	public void removeGroupId(String groupId) {
		this.groupIds.remove(groupId);
	}
}

package operation.pojo.group;

import org.springframework.data.annotation.Id;

public class GroupNumber {
	@Id
	private String Id;
	private String groupNumber;
	
	public GroupNumber() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}


	public String getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}
	

}

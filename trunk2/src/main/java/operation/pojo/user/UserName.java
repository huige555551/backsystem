package operation.pojo.user;

import org.springframework.data.annotation.Id;

public class UserName {
	@Id
	private String id;
	private String name;
	
	public UserName(String name){
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}

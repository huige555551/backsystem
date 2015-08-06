package operation.pojo.log;

import org.springframework.data.annotation.Id;

public class OssLog {
	@Id
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	


	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}




	private int value;
	
	 
}

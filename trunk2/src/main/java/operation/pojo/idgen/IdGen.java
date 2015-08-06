package operation.pojo.idgen;

import org.springframework.data.annotation.Id;

public class IdGen {
	@Id
	private String id;
	
	private long number;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

}

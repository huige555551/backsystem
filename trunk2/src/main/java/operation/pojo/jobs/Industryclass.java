package operation.pojo.jobs;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="industryclass")
public class Industryclass {

	@Id
	private String id;
	
	private String name;
	@Indexed
	private String pId;

	private List<Jobs> industryClass;
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

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public List<Jobs> getIndustryClass() {
		return industryClass;
	}

	public void setIndustryClass(List<Jobs> industryClass) {
		this.industryClass = industryClass;
	}

	
	
}

package operation.pojo.industry;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="industrynew")
public class IndustryBean {

	@Id
	public String id;
	
	@Indexed
	public String industryName;
	public List<String> indDirectList ;
	public int status;
	public long ctime;
	
}

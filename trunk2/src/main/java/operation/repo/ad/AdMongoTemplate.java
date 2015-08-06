package operation.repo.ad;

import java.util.List;

import operation.pojo.ad.Ad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Service
@Component
public class AdMongoTemplate {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public Object find(String ctime, String etime,
			Pageable pageable){
		Criteria criteria = new Criteria().andOperator(
				Criteria.where("createDate").gte(ctime), 
				Criteria.where("createDate").lt(etime)
				);
		String[] excludeKey={"adSellerId","name"};
		return mongoTemplate.group(criteria, "count", new GroupBy(excludeKey), Ad.class);
		
	}
	
	

}

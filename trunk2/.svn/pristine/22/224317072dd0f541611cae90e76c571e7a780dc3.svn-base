package operation.repo.ad;

import operation.exception.XueWenServiceException;
import operation.pojo.ad.ZtiaoAd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class ZtiaoAdMongoTemplate {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	/***
	 * 删除广告
	 * @param groupId
	 * @return
	 * @throws XueWenServiceException
	 */
	public Boolean deleteByGroupId(String id)throws XueWenServiceException{
		Query query=new Query(Criteria.where("id").is(id));
		mongoTemplate.remove(query, ZtiaoAd.class);
		return true;
		
	}
	
	
}

package operation.repo.activity;

import operation.exception.XueWenServiceException;
import operation.pojo.activity.NewActivityUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class NewActivityUserTemplate {

	@Autowired
	private MongoTemplate mongoTemplate;
	/**
	 * 根据用户Id和活动Id确定用户是否报名活动
	 * @param userId
	 * @param activityId
	 * @return
	 * @throws XueWenServiceException
	 */
	public boolean isApply(String userId,String activityId)throws XueWenServiceException{
		Query q=new Query(Criteria.where("userId").is(userId).and("activityId").is(activityId));
		return mongoTemplate.exists(q, NewActivityUser.class);
	}
}

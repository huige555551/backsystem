package operation.repo.group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import operation.exception.XueWenServiceException;
import operation.pojo.group.MyGroup;

@Service
@Component
public class MyGroupTemplate{

	@Autowired
	public MongoTemplate mongoTemplate;
	public List<String> getMyGroupIdListByUserId(String userId)throws XueWenServiceException{
		Query query=new Query();
		Criteria criteria=new Criteria();
		criteria.where("userId").is(userId);
		query.fields().include("groupIds");
		query.addCriteria(criteria);
//		mongoTemplate.findOne(query, entityClass, collectionName)
		MyGroup	 group=mongoTemplate.findOne(query, MyGroup.class);
		if(group != null){
			return group.getGroupIds();
		}else{
			return null;
		}
	}
}

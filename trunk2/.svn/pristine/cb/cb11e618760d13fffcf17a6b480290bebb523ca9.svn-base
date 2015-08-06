package operation.repo.group;

import operation.exception.XueWenServiceException;
import operation.pojo.group.XueWenGroup;
import operation.pojo.user.User;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 群组 mongoTemplate的数据操作方法
 * @author hjn
 *
 */

@Service
@Component
public class GroupMongoTemplate {

	private static final Logger logger=Logger.getLogger(GroupMongoTemplate.class);
	
	public GroupMongoTemplate(){
		super();
	}
	@Autowired
	private MongoTemplate mongoTemplate;
	
	/**
	 * 根据群组名判断，以此名称命名的群组是否存在
	 * @author hjn
	 * @param groupName
	 * @return
	 * @throws XueWenServiceException
	 */
	public boolean isGroupExi(String groupName)throws XueWenServiceException{
		Query query=new Query(Criteria.where("groupName").is(groupName));
		return mongoTemplate.exists(query, XueWenGroup.class);
	}
}

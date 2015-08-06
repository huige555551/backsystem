package operation.repo.course;

import java.util.List;

import operation.exception.XueWenServiceException;
import operation.pojo.course.GroupShareKnowledge;
import operation.pojo.course.Knowledge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 
* @ClassName: GroupShareKnowledgeTemplate
* @Description: 群组与分享关系表
* @author Administrator
* @date 2015年2月5日 上午9:33:18
*
 */
@Service
@Component
public class GroupShareKnowledgeTemplate {
	@Autowired
	public MongoTemplate mongoTemplate;
	/**
	 * 根据分享IDs删除该群组下的分享
	 * @author shenb
	 * @param KnowledgeIds
	 * @throws XueWenServiceException
	 */
	public void deleteByIds(List<Object> KnowledgeIds,String groupId )throws XueWenServiceException{
		Query query=new Query(Criteria.where("knowledge").in(KnowledgeIds).and("groupId").is(groupId));
		mongoTemplate.remove(query, GroupShareKnowledge.class);
		//引用次数减1
		for (Object object : KnowledgeIds) {
			mongoTemplate.updateFirst(new Query(Criteria.where("id").is(object)), new Update().inc("arc", -1), Knowledge.class);
		}
		
	}
}

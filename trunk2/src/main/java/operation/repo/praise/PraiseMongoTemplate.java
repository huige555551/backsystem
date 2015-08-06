package operation.repo.praise;


import java.util.List;

import love.cq.util.StringUtil;
import operation.exception.XueWenServiceException;
import operation.pojo.praise.Praise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import tools.Config;

@Service
@Component
public class PraiseMongoTemplate {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public boolean existsByUserIdAndDomainAndSourceIdAndType(String userId,String domain,String sourceId,String type)throws XueWenServiceException{
		if(StringUtil.isBlank(userId) || StringUtil.isBlank(domain) || StringUtil.isBlank(sourceId) || StringUtil.isBlank(type)){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_201,null);
		}
		Query query=new Query(Criteria.where("userId").is(userId).and("domain").is(domain).and("sourceId").is(sourceId).and("type").is(type));
		return mongoTemplate.exists(query, Praise.class);
	}
	
	/**
	 * 根据来源ID删除点赞记录
	 * @param sourceId
	 * @throws XueWenServiceException
	 */
	public void deleteBySourceId(String sourceId)throws XueWenServiceException{
		Query query=new Query(Criteria.where("sourceId").is(sourceId));
		mongoTemplate.remove(query, Praise.class);
	}
	/**
	 * 根据来源ID集合删除点赞记录
	 * @param sourceId
	 * @throws XueWenServiceException
	 */
	public void deleteBySourceIds(List<Object> sourceIds)throws XueWenServiceException{
		Query query=new Query(Criteria.where("sourceId").in(sourceIds));
		mongoTemplate.remove(query, Praise.class);
	}
	
	/**
	 * 根据
	 * @param sourceIds
	 * @param userId
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<Praise>  findBySourseListAndUserIdRspSourceId(List<String> sourceIds,String userId)throws XueWenServiceException{
		Query query=new Query(Criteria.where("sourceId").in(sourceIds).and("userId").is(userId));
		query.fields().include("sourceId");
		return mongoTemplate.find(query, Praise.class);
	}
}

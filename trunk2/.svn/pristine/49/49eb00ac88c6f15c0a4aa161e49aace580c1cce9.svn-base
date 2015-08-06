package operation.repo.share;

import java.util.List;

import operation.exception.XueWenServiceException;
import operation.pojo.share.Share;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class ShareTemplate {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	public ShareTemplate(){
		super();
	}
	
	/**
	 * 根据来源列表删除分享记录
	 * @param sourceIds
	 * @throws XueWenServiceException
	 */
	public void deleteBySourceIds(List<Object> sourceIds)throws XueWenServiceException{
		Query query=new Query(Criteria.where("sourceId").in(sourceIds));
		mongoTemplate.remove(query, Share.class);
	}
	
	/**
	 * 根据用户Id和来源列表删除收藏记录
	 * @param sourceIds
	 * @throws XueWenServiceException
	 */
	public void deleteByUserIdAndSourceIds(String userId,List<Object> sourceIds)throws XueWenServiceException{
		Query query=new Query(Criteria.where("userId").is(userId).and("sourceId").in(sourceIds));
		mongoTemplate.remove(query, Share.class);
	}
	/**
	 * 根据来源列表删除收藏记录
	 * @param sourceId
	 * @throws XueWenServiceException
	 */
	public void deleteBySourceId(String  sourceId)throws XueWenServiceException{
		Query query=new Query(Criteria.where("sourceId").is(sourceId));
		mongoTemplate.remove(query, Share.class);
	}
	/**
	 * 根据收藏目的地址和目的类型删除收藏记录
	 * @param sourceIds
	 * @throws XueWenServiceException
	 */
	public void deleteByToAddrAndToType(String toAddr,String toType )throws XueWenServiceException{
		Query query=new Query(Criteria.where("toAddr").is(toAddr).and("toType").is(toType));
		mongoTemplate.remove(query, Share.class);
	}
}

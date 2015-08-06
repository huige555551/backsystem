package operation.repo.dynamic;

import java.util.List;

import operation.exception.XueWenServiceException;
import operation.pojo.drycargo.Drycargo;
import operation.pojo.dynamic.GroupDynamic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import tools.Config;

@Service
@Component
public class GroupDynamicTemplate {
	@Autowired
	public MongoTemplate mongoTemplate;
	/**
	 * 
	 * @Title: deleteBySourceIdId
	 * @Description: 删除动态
	 * @param sourceId
	 * @throws XueWenServiceException void
	 * @throws
	 */
	public void deleteBySourceIdId(String sourceId)throws XueWenServiceException{
		Query query=new Query(Criteria.where("sourceId").is(sourceId));
		mongoTemplate.remove(query, GroupDynamic.class);
	}
	/**
	 * 
	 * @Title: deleteBySourceIdIds
	 * @Description: 批量删
	 * @param idList
	 * @throws XueWenServiceException void
	 * @throws
	 */
	public void deleteBySourceIdIds(List<Object> idList)throws XueWenServiceException{
		Query query=new Query(Criteria.where("sourceId").in(idList));
		mongoTemplate.remove(query, GroupDynamic.class);
	}
	/**
	 * 根据群组Id删除此群组的所有动态
	 * @param groupId
	 * @throws XueWenServiceException
	 */
	public void deleteByGroupId(String groupId)throws XueWenServiceException{
		Query query=new Query(Criteria.where("groupId").in(groupId));
		mongoTemplate.remove(query, GroupDynamic.class);
	}
	
	/**
	 * 修改赞统计数量
	 * @param sourceId
	 * @param count
	 * @throws XueWenServiceException
	 */
	public void updatelikeCount(String sourceId,int count)throws XueWenServiceException{
		Query query=new Query(Criteria.where("sourceId").is(sourceId));
		Update u=new Update();
		u.inc("likeCount", count);
		mongoTemplate.updateMulti(query, u,GroupDynamic.class);
	}
	
	/**
	 * 修改动态的校验状态
	 * @param sourceId
	 * @param count
	 * @throws XueWenServiceException
	 */
	public void updateChecked(String sourceId,boolean checked)throws XueWenServiceException{
		Query query=new Query(Criteria.where("sourceId").is(sourceId));
		Update u=new Update();
		u.set("checked", checked);
		mongoTemplate.updateMulti(query, u,GroupDynamic.class);
	}
	/**
	 * 判断创建群组动态是否存在
	 * @param groupId
	 * @return
	 * @throws XueWenServiceException
	 */
	public boolean createGroupDynamicExise(String groupId)throws XueWenServiceException{
		Query query=new Query(Criteria.where("groupId").is(groupId).and("type").is(Config.TYPE_GROUP));
		return mongoTemplate.exists(query, GroupDynamic.class);
	}
	/**
	 * 判断加群动态是否存在
	 * @param groupId
	 * @param userId
	 * @return
	 * @throws XueWenServiceException
	 */
	public boolean joinDynamicExise(String groupId,String userId)throws XueWenServiceException{
		Query query=new Query(Criteria.where("groupId").is(groupId).and("autherId").is(userId).and("type").is(Config.TYPE_JOINGROUP));
		return mongoTemplate.exists(query, GroupDynamic.class);
	}
	
	/**
	 * 判断是否存在此动态
	 * @param sourceId
	 * @return
	 * @throws XueWenServiceException
	 */
	public boolean dynamicExiseBySourceId(String sourceId)throws XueWenServiceException{
		Query query=new Query(Criteria.where("sourceId").is(sourceId));
		return mongoTemplate.exists(query, GroupDynamic.class);
	}
	/**
	 * 由于群组信息修改，导致动态中群组冗余存储的相关信息修改
	 * @param groupId
	 * @param groupName
	 * @param groupLogoUrl
	 * @throws XueWenServiceException
	 */
	public void updateGroupNameAndGroupLogo(String groupId,String groupName,String groupLogoUrl)throws XueWenServiceException{
		Query query=new Query(Criteria.where("groupId").is(groupId));
		Update u=new Update();
		u.set("groupName", groupName);
		u.set("groupLogoUrl", groupLogoUrl);
		mongoTemplate.updateMulti(query, u,GroupDynamic.class);
	}
	
	
}

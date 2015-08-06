package operation.repo.topics;

import java.util.List;

import operation.exception.XueWenServiceException;
import operation.pojo.topics.SubPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
@Component
/**
 * 副楼回复的template数据库操作方法
 * @author hjn
 *
 */

public class SubPostTemplate {

	@Autowired
	public MongoTemplate mongoTemplate;
	
	public SubPostTemplate(){
		
	}
	
	/**
	 * 根据主楼Id删除所有的副楼回复
	 * @param PostId
	 * @return
	 */
	public void deleteSubPostByPostId(String postId)throws XueWenServiceException{
		mongoTemplate.remove(new Query(Criteria.where("parentId").is(postId)), SubPost.class);
	}
	
	/**
	 * 根据subpostId 删除副楼回复
	 * @param subPostId
	 * @throws XueWenServiceException
	 */
	public void deleteSubPostBySubPostId(String subPostId)throws XueWenServiceException{
		mongoTemplate.remove(new Query(Criteria.where("_id").is(subPostId)), SubPost.class);
	}
	
	/**
	 * 根据主楼Id集合删除所有的副楼回复
	 * @param postIds
	 */
	public void deleteSubPostByPostIds(List<Object> postIds){
		mongoTemplate.remove(new Query(Criteria.where("parentId").in(postIds)), SubPost.class);
	}
	/**
	 * 将话题的创建者由fromUser 改为toUser
	 * @param fromUserId
	 * @param toUserId
	 * @param toUserNickName
	 * @param toUserLogoUrl
	 * @throws XueWenServiceException
	 */
	public void mergeSubPost(String fromUserId,String toUserId,String toUserNickName,String toUserLogoUrl)throws XueWenServiceException{
		Query query=new Query(Criteria.where("authorId").is(fromUserId));
		Update update=new Update();
		update.set("authorId", toUserId);
		update.set("authorName", toUserNickName);
		update.set("authorLogoUrl", toUserLogoUrl);
		mongoTemplate.updateMulti(query,update,SubPost.class);
	}
	/**
	 * 副楼之间回复修改
	 * @param fromUserId
	 * @param toUserId
	 * @param toUserNickName
	 * @throws XueWenServiceException
	 */
	public void mergeSubPostToOther(String fromUserId,String toUserId,String toUserNickName)throws XueWenServiceException{
		Query query=new Query(Criteria.where("toId").is(fromUserId));
		Update update=new Update();
		update.set("toId", toUserId);
		update.set("toName", toUserNickName);
		mongoTemplate.updateMulti(query,update,SubPost.class);
	}
	
}

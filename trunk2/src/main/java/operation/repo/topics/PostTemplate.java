package operation.repo.topics;

import java.util.List;

import operation.exception.XueWenServiceException;
import operation.pojo.topics.Post;
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
 * 主楼回复的template数据库操作方法
 * @author hjn
 *
 */
public class PostTemplate {

	@Autowired
	public MongoTemplate mongoTemplate;
	
	public PostTemplate(){
		
	}
	
	/**
	 * 根据topicId删除主楼回复
	 * @param topicId
	 * @return
	 */
	public void deletePostByTopicId(String topicId){
		mongoTemplate.remove(new Query(Criteria.where("topicId").is(topicId)), Post.class);
	}
	/**
	 * 根据主楼Id删除主楼记录
	 * @param postId
	 */
	public void deletePostById(String postId){
		mongoTemplate.remove(new Query(Criteria.where("postId").is(postId)), Post.class);
	}
	
	/**
	 * 根据话题ID返回所有的主楼集合，只有ID字段
	 * @param topicId
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<Post> findPostRspIdByTopicId(String topicId)throws XueWenServiceException{
		Query query=new Query(Criteria.where("topicId").is(topicId));
		query.fields().include("postId");
		return mongoTemplate.find(query, Post.class);
	}
	
	/**
	 * 根据主楼回复Id返回主楼中的副楼节点
	 * @param postId
	 * @return
	 * @throws XueWenServiceException
	 */
	public Post findSubPostListInPostByPostId(String postId)throws XueWenServiceException{
		Query query=new Query(Criteria.where("postId").is(postId));
		query.fields().include("subPosts");
		return mongoTemplate.findOne(query, Post.class);
	}
	
	/**
	 * 主楼中的副楼回复数目增减相应数值
	 * @param postId
	 * @param inc
	 * @return
	 * @throws XueWenServiceException
	 */
	public void increasingSubPostCount(String postId,int inc)throws XueWenServiceException{
		Query query=new Query(Criteria.where("postId").is(postId));
		Update update=new Update();
		update.inc("subPostsSize", inc);
		mongoTemplate.updateMulti(query, update,Post.class);
	}
	
	/**
	 * 根据主楼回复ID，移除一条副楼回复,并且副楼统计数据减一
	 * @param postId
	 * @param subPosts
	 * @throws XueWenServiceException
	 */
	public void removeOneSubPostInPostByPostId(String postId,List<SubPost> subPosts,int inc)throws XueWenServiceException{
		Query query=new Query(Criteria.where("postId").is(postId));
		Update update=new Update();
		update.inc("subPostsSize", inc);
		update.set("subPosts", subPosts);
		mongoTemplate.updateMulti(query, update,Post.class);
	}
	/**
	 * 根据主楼回复ID，添加一条副楼回复,并且副楼统计数据加一
	 * @param postId
	 * @param subPosts
	 * @throws XueWenServiceException
	 */
	public void addOneSubPostInPostByPostId(String postId,List<SubPost> subPosts,int inc)throws XueWenServiceException{
		Query query=new Query(Criteria.where("postId").is(postId));
		Update update=new Update();
		update.inc("subPostsSize", inc);
		update.set("subPosts", subPosts);
		mongoTemplate.updateMulti(query, update,Post.class);
	}
	
	/**
	 * 将话题的创建者由fromUser 改为toUser
	 * @param fromUserId
	 * @param toUserId
	 * @param toUserNickName
	 * @param toUserLogoUrl
	 * @throws XueWenServiceException
	 */
	public void mergePost(String fromUserId,String toUserId,String toUserNickName,String toUserLogoUrl)throws XueWenServiceException{
		Query query=new Query(Criteria.where("authorId").is(fromUserId));
		Update update=new Update();
		update.set("authorId", toUserId);
		update.set("authorName", toUserNickName);
		update.set("authorLogoUrl", toUserLogoUrl);
		mongoTemplate.updateMulti(query,update,Post.class);
	}
	
	/**
	 * 根据postId查询出此post 的回复类型
	 * @param postId
	 * @return
	 * @throws XueWenServiceException
	 */
	public Post findTranFlagByPostId(String postId)throws XueWenServiceException{
		Query query=new Query(Criteria.where("postId").is(postId));
		query.fields().include("tranFlag").include("topicId");
		return mongoTemplate.findOne(query, Post.class);
	}
	
}

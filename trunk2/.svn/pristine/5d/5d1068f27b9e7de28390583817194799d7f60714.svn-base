package operation.repo.user;

import java.util.List;

import operation.exception.XueWenServiceException;
import operation.pojo.user.Contact;
import operation.pojo.user.ContactUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


/**
 * 联系人数据持久层MongoTemplate操作
 * @author hjn
 *
 */
@Service
@Component
public class ContactUserTemplate {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	public ContactUserTemplate(){
		super();
	}
	
	/**
	 * 获取用户 A----> B之间的关系
	 * @author hjn
	 * @param fromUserId  A 用户ID
	 * @param toUserId B 用户ID
	 * @return
	 * @throws XueWenServiceException
	 */
	public int contactStatus(String fromUserId,String toUserId)throws XueWenServiceException{
		Query query=new Query(Criteria.where("fromUser").is(fromUserId).and("toUser").is(toUserId));
		query.fields().include("id").include("status");
		ContactUser contactUser=mongoTemplate.findOne(query, ContactUser.class);
		if(contactUser == null){
			return 0;
		}else{
			return contactUser.getStatus();
		}
	}
	
	/**
	 * 判断  A ----> B 的关系是否存在
	 * @author hjn
	 * @author hjn
	 * @param fromUserId
	 * @param toUserId
	 * @return
	 * @throws XueWenServiceException
	 */
	public boolean existsContact(String fromUserId,String toUserId)throws XueWenServiceException{
		Query query=new Query(Criteria.where("fromUser").is(fromUserId).and("toUser").is(toUserId));
		return mongoTemplate.exists(query, ContactUser.class);
	}
	
	/**
	 * 更改 A --- > B 的关系状态
	 * @author hjn
	 * @param fromUserId
	 * @param toUserId
	 * @throws XueWenServiceException
	 */
	public void changeStatus(String fromUserId,String toUserId,int status,long time)throws XueWenServiceException{
		Query query=new Query(Criteria.where("fromUser").is(fromUserId).and("toUser").is(toUserId));
		Update update=new Update();
		update.set("status", status);
		update.set("utime", time);
		mongoTemplate.findAndModify(query, update, ContactUser.class);
	}
	
	/**
	 * 删除 A ---->B 的关系
	 * @author hjn
	 * @param fromUserId
	 * @param toUserId
	 * @param status
	 * @param time
	 * @throws XueWenServiceException
	 */
	public void removeContact(String fromUserId,String toUserId)throws XueWenServiceException{
		Query query=new Query(Criteria.where("fromUser").is(fromUserId).and("toUser").is(toUserId));
		mongoTemplate.remove(query, ContactUser.class);
	}
	
	/**
	 * 查询用户各种状态的联系人列表,返回数据中过滤掉 fromUser,ctime
	 * @author hjn
	 * @param fromUserId
	 * @param status
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<ContactUser> getContactUserListByFromUserIdAndStatus(String fromUserId,int status)throws XueWenServiceException{
		Query query=new Query(Criteria.where("fromUser").is(fromUserId).and("status").is(status));
		query.fields().include("id").include("utime").include("toUser").include("noteName").include("status");
		return mongoTemplate.find(query, ContactUser.class);
	}
	
}

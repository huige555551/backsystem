package operation.repo.course;

import java.util.List;

import operation.exception.XueWenServiceException;
import operation.pojo.course.Lesson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 课时对象的template操作
 * @author hjn
 *
 */
@Service
@Component
public class LessonTemplate {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	public LessonTemplate(){
		super();
	}
	
	/**
	 * 查询课时基本信息,包括（id,title,length,timer,logoUrl,order,localUrl,type,knowledgeId）
	 * @author hjn
	 * @param lessonId
	 * @return
	 * @throws XueWenServiceException
	 */
	public Lesson findOneLessonBasicInfoById(String lessonId)throws XueWenServiceException{
		Query query=new Query(Criteria.where("id").is(lessonId));
		query.fields().include("id").include("title").include("length").include("timer").include("logoUrl")
		.include("order").include("localUrl").include("type").include("knowledge").include("isbuy");
		return mongoTemplate.findOne(query, Lesson.class);
	}
	/**
	 * 查询课时基本信息,包括（id,title,length,timer,logoUrl,order,localUrl,type）
	 * @author hjn
	 * @param lessonId
	 * @return
	 * @throws XueWenServiceException
	 */
	public Lesson findOneLessonByIdRspIdAndTimer(String lessonId)throws XueWenServiceException{
		Query query=new Query(Criteria.where("id").is(lessonId));
		query.fields().include("id").include("timer");
		return mongoTemplate.findOne(query, Lesson.class);
	}
	
	/**
	 * 根据课时的Id集合删除所有课时记录
	 * @param lessonIds
	 * @throws XueWenServiceException
	 */
	public void deleteByIds(List<Object> lessonIds)throws XueWenServiceException{
		Query query=new Query(Criteria.where("id").in(lessonIds));
		mongoTemplate.remove(query, Lesson.class);
	}
	/**
	 * 课时创建人合并
	 * @param fromUserId
	 * @param toUserId
	 * @throws XueWenServiceException
	 */
	public void mergeLesson(String fromUserId,String toUserId)throws XueWenServiceException{
		Query query=new Query(Criteria.where("createUser").is(fromUserId));
		Update update=new Update();
		update.set("createUser", toUserId);
		mongoTemplate.updateMulti(query, update, Lesson.class);
	}
	
	
}

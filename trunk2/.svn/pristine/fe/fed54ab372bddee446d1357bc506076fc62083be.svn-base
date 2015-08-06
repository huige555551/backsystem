/**   
* @Title: CourseFileRepository.java
* @Package operation.repo.cloudfile
* @Description: 
* @author yaoj
* @date 2014年12月17日 下午1:41:38
* @version V1.0
*/ 
package operation.repo.cloudfile;

import operation.pojo.cloudfile.CourseFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

/** 
 * @ClassName: CourseFileRepository
 * @Description: 云存储课件Repository
 * @author yaoj
 * @date 2014年12月17日 下午1:41:38
 * 
 */
@Component
public class CourseFileTemplate {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	/**
	 * 
	 * @Title: insertCourseFile
	 * @Description: 添加新的课件
	 * @param courseFile
	 * @return CourseFile
	 * @throws
	 */
	public CourseFile insertCourseFile(CourseFile courseFile) {
		mongoTemplate.save(courseFile);
		return courseFile;
	}

	/**
	 * 
	 * @Title: viewCourseFileById
	 * @Description: 根据id查看课件
	 * @param id
	 * @return CourseFile
	 * @throws
	 */
	public CourseFile viewCourseFileById(String id) {
		return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), CourseFile.class);
	}

	/**
	 * 
	 * @Title: addCourseCountById
	 * @Description: 添加引用次数+1
	 * @param id
	 * @return CourseFile
	 * @throws
	 */
	public CourseFile addCourseCountById(String id, long time) {
		Query query = new Query(Criteria.where("id").is(id));
		Update update = new Update().inc("refCount", 1).set("utime", time);
		mongoTemplate.updateFirst(query, update, CourseFile.class);
		return null;
	}

	/**
	 * 
	 * @Title: subCourseCountById
	 * @Description: 引用次数减一
	 * @param id
	 * @return CourseFile
	 * @throws
	 */
	public CourseFile subCourseCountById(String id, long time) {
		Query query = new Query(Criteria.where("id").is(id));
		Update update = new Update().inc("refCount", -1).set("utime", time);
		mongoTemplate.updateFirst(query, update, CourseFile.class);
		return null;
	}

}

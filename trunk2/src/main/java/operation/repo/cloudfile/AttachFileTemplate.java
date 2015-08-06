/**   
* @Title: AttachFileRepository.java
* @Package operation.repo.cloudfile
* @Description:
* @author yaoj
* @date 2014年12月17日 下午1:41:19
* @version V1.0
*/ 
package operation.repo.cloudfile;

import operation.pojo.cloudfile.AttachFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

/** 
 * @ClassName: AttachFileRepository
 * @Description: 云存储附件Repository
 * @author yaoj
 * @date 2014年12月17日 下午1:41:19
 * 
 */
@Component
public class AttachFileTemplate {

	@Autowired
	private MongoTemplate mongoTemplate;

	/**
	 * 
	 * @Title: insertAttachFile
	 * @Description: 添加新的课件
	 * @param attachFile
	 * @return AttachFile
	 * @throws
	 */
	public AttachFile insertAttachFile(AttachFile attachFile) {
		mongoTemplate.save(attachFile);
		return attachFile;
	}

	/**
	 * 
	 * @Title: viewAttachFileById
	 * @Description: 根据id查看课件
	 * @param id
	 * @return AttachFile
	 * @throws
	 */
	public AttachFile viewAttachFileById(String id) {
		return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), AttachFile.class);
	}

	/**
	 * @param time 
	 * 
	 * @Title: addAttachCountById
	 * @Description: 添加引用次数+1
	 * @param id
	 * @return AttachFile
	 * @throws
	 */
	public AttachFile addAttachCountById(String id, long time) {
		Query query = new Query(Criteria.where("id").is(id));
		Update update = new Update().inc("arc", 1).set("utime", time);
		mongoTemplate.updateFirst(query, update, AttachFile.class);
		return null;
	}

	/**
	 * 
	 * @Title: subAttachCountById
	 * @Description: 引用次数减一
	 * @param id
	 * @return AttachFile
	 * @throws
	 */
	public AttachFile subAttachCountById(String id, long time) {
		Query query = new Query(Criteria.where("id").is(id));
		Update update = new Update().inc("arc", -1).set("utime", time);
		mongoTemplate.updateFirst(query, update, AttachFile.class);
		return null;
	}
	
}

package operation.repo.box;

import operation.exception.XueWenServiceException;
import operation.pojo.box.Box;
import operation.pojo.box.BoxPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class BoxPostTemplate {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public BoxPostTemplate(){
		super();
	}
	/**
	 * 根据Id获取boxPost的长度
	 * @param id
	 * @return
	 * @throws XueWenServiceException
	 */
	public BoxPost getBoxPostSizeById(String id)throws XueWenServiceException{
		Query query=new Query(Criteria.where("id").is(id));
		query.fields().include("size");
		return mongoTemplate.findOne(query, BoxPost.class);
	}
	
	/**
	 * 根据Id删除位置
	 * @param id
	 * @return
	 * @throws XueWenServiceException
	 */
	public Boolean deleteById(String id)throws XueWenServiceException{
		Query query=new Query(Criteria.where("id").is(id));
		 try {
			mongoTemplate.remove(query, BoxPost.class);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		 
		 
	}
	
	/**
	 * 根据sourceId删除位置
	 * @param id
	 * @return
	 * @throws XueWenServiceException
	 */
	public Boolean deleteBysourceId(String sourceId)throws XueWenServiceException{
		Query query=new Query(Criteria.where("sourceId").is(sourceId));
		 try {
			mongoTemplate.remove(query, Box.class);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		 
		 
	}
	
}

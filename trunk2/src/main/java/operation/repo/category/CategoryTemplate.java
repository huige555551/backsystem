package operation.repo.category;

import operation.exception.XueWenServiceException;
import operation.pojo.category.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
/**
 * 分类 的template处理方法
 * @author hjn
 *
 */
@Service
@Component
public class CategoryTemplate {

	@Autowired
	public MongoTemplate mongoTemplate;
	
	public CategoryTemplate(){
		super();
	}
	/**
	 * 根据Id获取分类的中文描述
	 * @param id
	 * @return
	 * @throws XueWenServiceException
	 */
	public Category getCategoryNameById(String id)throws XueWenServiceException{
		Query query=new Query(Criteria.where("id").is(id));
		query.fields().include("categoryName");
		return mongoTemplate.findOne(query, Category.class);
	}
}

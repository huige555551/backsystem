package operation.repo.tags;

import java.util.ArrayList;
import java.util.List;

import operation.exception.XueWenServiceException;
import operation.pojo.tags.TagBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/** 
* @ClassName: TagTemplate
* @Description: 标签相关的template操作
* @author yangquanliang
* @date 2014年12月9日 下午7:47:28
* 
*/ 
@Service
@Component
public class TagTemplate {
	

	@Autowired
	public MongoTemplate mongoTemplate;
	
	/** 
	* @author yangquanliang
	* @Description: 根据热度分值排序选出6个做为推荐的关键词标签
	* @param @param listkey 文本分词
	* @param @return
	* @param @throws XueWenServiceException
	* @return List<TagBean>
	* @throws 
	*/ 
	public List<String> getTagsByKeyWords(List<String> listkey)
			throws XueWenServiceException {

		Criteria criteria2 = Criteria.where("tagName").in(listkey);
		Query query = new Query();
		query.fields().exclude("tagType").exclude("status");
		Sort sort = new Sort(Direction.DESC, "score");
		query.with(sort);
		query.addCriteria(criteria2).skip(0).limit(6);
		List<TagBean> result = mongoTemplate.find(query, TagBean.class);
		List<String> listRe=new ArrayList<String>();
		for(TagBean temp:result)
		{
			listRe.add(temp.getTagName());
		}
		return listRe;
	}
	
	/** 
	* @author yangquanliang
	* @Description: 根据热度分值排序选出n个做为推荐的关键词标签
	* @param @param listkey 文本分词
	* @param @return
	* @param @throws XueWenServiceException
	* @return List<TagBean>
	* @throws 
	*/ 
	public List<TagBean> getTagsByKeyWords(List<String> listkey,int n,String tagType,String status)
			throws XueWenServiceException {

		Criteria criteria2 = Criteria.where("tagName").in(listkey).and("tagType").is(tagType).and("status").is(status);
		Query query = new Query();
		Sort sort = new Sort(Direction.DESC, "score");
		query.with(sort);
		if (n > 0) {
			query.addCriteria(criteria2).skip(0).limit(n);
		}
		List<TagBean> result = mongoTemplate.find(query, TagBean.class);
		return result;
	}
	
	/** 
	* @author yangquanliang
	* @Description: 更新标签热度值
	* @param 
	* @return void
	* @throws 
	*/ 
	public void updateTagBeanAndIncSocore(TagBean upTagBean)
	{
  	
        Criteria criteria = Criteria.where("_id").is(upTagBean.getId());  
        Query query = new Query(criteria);  
        Update update = new Update().inc("score",1); 
        mongoTemplate.updateFirst(query, update, TagBean.class);
	}
}

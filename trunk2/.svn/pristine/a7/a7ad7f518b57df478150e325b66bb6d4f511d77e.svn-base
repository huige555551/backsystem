package operation.repo.tags;

import java.util.List;

import operation.pojo.tags.TagBean;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * 基础标签对象
 * @author yangquanliang
 *
 */
public interface TagRepository extends MongoRepository<TagBean, String> {
	/**
	 * 通过tagName和tagType来查找是否已存在相同的标签
	 * @param tagName
	 * @param tagType
	 * @return
	 */
	TagBean findOneByTagNameAndTagType(String tagName, String tagType);
	/**
	 * 查询基础标签根据标签类型
	 * @param tagType
	 * @return
	 */
	List<TagBean> findBytagType(String tagType);
	
	
	
	/** 
	* @author yangquanliang
	* @Description: 通过分词标签查找匹配的标签库数据
	* @param @param listkey
	* @param @return
	* @return List<TagBean>
	* @throws 
	*/ 
	@Query(fields="{'tagName':1}")
	List<TagBean> findByTagNameIn(List<String> listkey);
	

}

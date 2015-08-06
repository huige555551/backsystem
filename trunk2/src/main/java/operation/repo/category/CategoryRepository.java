package operation.repo.category;

import java.util.List;

import operation.pojo.activity.Activity;
import operation.pojo.category.Category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String>{

	//根据分类类型查询所有此类型的分类，一般用来获取所有的一级分类
	List<Category> findByCategoryType(String categoryType);
	//根据分类类型和父类Id查询获取符合条件的分类，一般用来获取某一一级分类下的所有二级分类
	List<Category> findByCategoryTypeAndParentId(String categoryType,String parentId);
	Page<Category>  findByCategoryType(String categoryType, Pageable pageable);
	Page<Category> findByCategoryTypeAndParentId(String cateforyDefaultSencond,
			String id, Pageable pageable);
	List<Category> findByIdIn(List<Object> ids);
	Page<Category> findByIdNotInAndParentIdNotNull(List<Object> ids, Pageable pageable);
	List<Category> findByCategoryNameRegexAndIdNotInAndParentIdNotNull(String keyword,
			List<Object> ids);
	
}

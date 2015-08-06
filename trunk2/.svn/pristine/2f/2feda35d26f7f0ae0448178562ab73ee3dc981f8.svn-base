package operation.repo.course;


import java.util.List;

import operation.pojo.course.Chapter;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ChapterRepository extends MongoRepository<Chapter, String>{

	Chapter findOneByMd5(String md5);
	
	Chapter findOneById(String id);
	/**
	 * chapter中只有timer和ID
	 * @param id
	 * @return
	 */
	@Query(value="{'id':?0}",fields="{'timer':1}")
	Chapter findById(String id);
	
	List<Object> findByIdIn(List<Object> ids);
	
	
}

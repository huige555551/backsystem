package operation.repo.topics;


import operation.pojo.topics.WeeksTopicTopResult;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WeeksTopicTopResultRepository extends MongoRepository<WeeksTopicTopResult, String>{
	
	Page<WeeksTopicTopResult> findAll(Pageable pageable);

}

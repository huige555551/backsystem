package operation.repo.topics;

import operation.pojo.topics.TopicTopResult;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TopResultRepository extends MongoRepository<TopicTopResult, String>{
	
	Page<TopicTopResult> findAll(Pageable pageable);

}

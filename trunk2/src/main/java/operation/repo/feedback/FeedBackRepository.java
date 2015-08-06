package operation.repo.feedback;

import operation.pojo.feedback.FeedBack;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.lang.String;
import java.util.List;

public interface FeedBackRepository extends MongoRepository<FeedBack, String>{
	
	Page<FeedBack> findAll(Pageable pageable);
	
	FeedBack findById(String id);

}

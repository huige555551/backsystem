package operation.repo.activity;


import operation.pojo.activity.NewActivityUser;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NewActivityUserRepo extends MongoRepository<NewActivityUser, String> {

	Page<NewActivityUser> findByUserId(String userId,Pageable able);
	Page<NewActivityUser> findByActivityId(String newActivityId,Pageable able);
}

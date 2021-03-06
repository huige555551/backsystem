package operation.repo.activity;

import operation.pojo.activity.ActivityUser;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActivityUserRepo extends MongoRepository<ActivityUser, String> {

	Page<ActivityUser> findByActivityId(String id, Pageable pageable);


	ActivityUser findByActivityIdAndPhone(String activityId, String phone);
  
}

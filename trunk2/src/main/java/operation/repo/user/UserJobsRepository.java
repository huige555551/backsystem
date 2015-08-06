package operation.repo.user;

import operation.pojo.user.UserJobs;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserJobsRepository extends MongoRepository<UserJobs, String>{

	UserJobs findOneByUser(String userId);
	
	UserJobs findOneByUserAndNowJobsAndFavJobs(String userId,String nowJobs,String favJobs);
	
	
}

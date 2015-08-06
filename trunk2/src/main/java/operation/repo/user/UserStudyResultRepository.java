package operation.repo.user;

import operation.pojo.user.UserStudyResult;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserStudyResultRepository extends MongoRepository<UserStudyResult, String>{

	
	UserStudyResult findOneByUserId(String userId);
	int countByStudyTimeGreaterThan(long studyTimer);
	
}

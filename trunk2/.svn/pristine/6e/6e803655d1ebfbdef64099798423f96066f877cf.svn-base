package operation.repo.user;

import java.util.List;

import operation.pojo.user.UserSkills;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserSkillsRepository extends
		MongoRepository<UserSkills, String> {
	List<Object> findByUserIdAndJobTitleId(String userId, String jobTitleId);
	UserSkills findOneByUserIdAndJobTitleIdAndSkillId(String userId, String jobTitleId,String skillId);
	List<UserSkills> findByUserIdAndJobTitleIdAndSkillId(String userId, String jobTitleId,String skillId);
	List<UserSkills> findByUserIdAndSkillId(String userId,String skillId);
	List<UserSkills> findByUserIdAndSkillIdIn(String userId,List<Object> skillId);
}

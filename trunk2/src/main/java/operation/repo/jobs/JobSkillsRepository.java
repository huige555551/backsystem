package operation.repo.jobs;

import operation.pojo.skill.JobSkills;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobSkillsRepository  extends MongoRepository<JobSkills, String>{
	
}

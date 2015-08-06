package operation.repo.skill;

import operation.pojo.skill.JobSkills;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobSkillRepository extends MongoRepository<JobSkills, String>{
	
	JobSkills findOneByJobs(String jobId);
	JobSkills findOneByJobsAndWeight(String jobId,String weight);

}

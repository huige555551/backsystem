package operation.repo.jobs;

import java.util.List;

import operation.pojo.jobs.Jobs;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobsRepository extends MongoRepository<Jobs, String>{

	List<Jobs> findByPId(String pId);
	
	Jobs findById(String id);
	
	
}

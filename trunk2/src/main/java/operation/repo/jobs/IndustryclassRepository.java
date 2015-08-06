package operation.repo.jobs;

import java.util.List;

import operation.pojo.jobs.Industryclass;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IndustryclassRepository extends MongoRepository<Industryclass, String>{

	List<Industryclass>  findByPId(String pid);
	
	Industryclass  findOneById(String id);
}

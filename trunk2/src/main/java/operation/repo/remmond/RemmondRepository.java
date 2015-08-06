package operation.repo.remmond;

import operation.pojo.remmond.Remmond;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RemmondRepository extends MongoRepository<Remmond, String> {

	Remmond findOneByUserId(String id);
	
	
	

}

package operation.repo.black;


import operation.pojo.black.Black;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BlackRepository extends MongoRepository<Black, String>{
	Black findByUser(String user);
}

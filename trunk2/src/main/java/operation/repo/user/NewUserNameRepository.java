package operation.repo.user;



import java.util.List;

import operation.pojo.user.NewUserName;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface NewUserNameRepository extends MongoRepository<NewUserName, String>{
	
	List<NewUserName> findAll();

}

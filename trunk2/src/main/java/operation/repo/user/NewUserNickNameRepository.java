package operation.repo.user;

import operation.pojo.user.NewUserNickName;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.lang.String;
import java.util.List;

public interface NewUserNickNameRepository extends MongoRepository<NewUserNickName, String>{

	
	Page<NewUserNickName> findByNameRegex(String name,Pageable page);
	
	NewUserNickName findByName(String name);
	
	NewUserNickName findByNumber(int number);
	
}

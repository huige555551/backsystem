package operation.repo.user;

import java.util.List;

import operation.pojo.user.UserContact;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserContactRepository extends MongoRepository<UserContact, String> {
	Page<UserContact> findAllByUserId(String userId,Pageable page);
	List<UserContact> findAllByUserId(String userId);
	UserContact findByUserId(String userId);
	UserContact findByUserIdAndType(String userId,int type);
}

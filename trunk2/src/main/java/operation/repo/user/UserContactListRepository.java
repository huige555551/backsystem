package operation.repo.user;

import operation.pojo.user.UserContactList;
import operation.pojo.user.UserFriendShip;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserContactListRepository extends MongoRepository<UserContactList, String> {
	UserContactList findOneByUserId(String userId);
}

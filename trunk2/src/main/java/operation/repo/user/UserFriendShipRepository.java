package operation.repo.user;

import operation.pojo.user.UserFriendShip;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserFriendShipRepository extends MongoRepository<UserFriendShip, String> {
	UserFriendShip findOneByUser(String userId);
}

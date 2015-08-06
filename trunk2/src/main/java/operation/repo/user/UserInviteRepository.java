package operation.repo.user;


import operation.pojo.user.UserInvite;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserInviteRepository extends MongoRepository<UserInvite, String> {
	
	UserInvite findByPhoneNumberAndOperation(String phoneNumber,String operation);

}

package operation.repo.user;


import java.util.List;

import operation.pojo.user.ContactAdress;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactAdressRepository extends MongoRepository<ContactAdress, String> {
	List<ContactAdress> findByUserId(String userId);
	List<ContactAdress> findByPhoneNumber(String phoneNumber);
}

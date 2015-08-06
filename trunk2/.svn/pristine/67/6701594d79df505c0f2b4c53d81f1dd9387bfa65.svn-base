package operation.repo.email;

import operation.pojo.email.MailCode;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MailCodeRepo extends MongoRepository<MailCode, String> {
	MailCode findOneByEmail(String email,Sort sort);
    
}

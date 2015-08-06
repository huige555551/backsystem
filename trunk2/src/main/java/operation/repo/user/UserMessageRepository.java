package operation.repo.user;

import java.util.List;

import operation.pojo.user.UserMessage;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
/**
 * 用户repo，与数据库进行交互
 * @author nes
 *
 */
public interface UserMessageRepository extends MongoRepository<UserMessage, String> {
	UserMessage findOneById(String id);
	List<UserMessage>  findByIdIn(List<Object> user);
	Page<UserMessage> findAllByUserId(String userId,Pageable page);
	List<UserMessage> findByMessageGroupId(String messageGroupId);
}

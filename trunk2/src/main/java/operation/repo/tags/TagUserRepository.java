package operation.repo.tags;

import operation.pojo.tags.UserTagBean;

import org.springframework.data.mongodb.repository.MongoRepository;
/**
 * 
 * @author yangquanliang
 *
 */
public interface TagUserRepository extends MongoRepository<UserTagBean, String> {

}

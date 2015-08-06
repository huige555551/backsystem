package operation.repo.log;


import java.util.List;

import operation.pojo.log.OssLog;
import operation.pojo.log.UserLoginLog;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OssLogRepository extends MongoRepository<OssLog, String>{

	List<OssLog> findAll();

}

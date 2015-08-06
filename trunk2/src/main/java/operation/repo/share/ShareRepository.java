package operation.repo.share;


import operation.pojo.share.Share;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShareRepository extends MongoRepository<Share, String>{
	
	Share findByUserIdAndSourceIdAndShareType(String userId,String sourceId,String shareType);
	int countByUserIdAndShareTypeAndAppKey(String userId,String type,String appkey);
}

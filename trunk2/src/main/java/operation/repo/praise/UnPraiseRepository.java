package operation.repo.praise;



import operation.pojo.praise.Praise;
import operation.pojo.praise.UnPraise;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UnPraiseRepository extends MongoRepository<UnPraise, String> {
	Page<UnPraise> findByDomainAndSourceIdAndType(String domain,String sourceId,String type,Pageable pageable);
	UnPraise findByUserIdAndDomainAndSourceIdAndType(String userId,String domain,String sourceId,String type);
	int countByDomainAndSourceIdAndType(String domain,String sourceId,String type);
//	boolean existsByUserIdAndAppKeyAndSourceIdAndType(String userId,String appkey,String sourceId,String type);
	Page<UnPraise> findByUserIdAndDomainAndType(String userId, String domain,String type, Pageable pageable);
}

package operation.repo.log;

import operation.pojo.log.UserRegLogS;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRegLogSReposity extends MongoRepository<UserRegLogS, String> {
	@Query("{'ctime':{$gt:?1},'ctime':{$lte:?2},'$or':[{'userNick':{'$regex':?0}},{'email':{'$regex':?0}},{'phone':{'$regex':?0}}]}")
	Page<UserRegLogS> findByKey(String key, long ctime, long etime, Pageable pageable);

	Page<UserRegLogS> findByCtimeBetween(long ctime, long etime,
			Pageable pageable);
}

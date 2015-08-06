package operation.repo.log;

import operation.pojo.log.UserRegistLog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
* 
* @ClassName: UserRegLogRepository
* @Description: 
* @author tangli
* @date 2015年3月17日 下午4:02:08
*
*/
public interface UserRegLogRepository extends MongoRepository<UserRegistLog, String> {
    
	@Query("{'ctime':{$gte:?1},'ctime':{$lte:?2},'$or':[{'vUserNick':{'$regex':?0}},{'vUserEmail':{'$regex':?0}},{'vUserPhone':{'$regex':?0}}]}")
	Page<UserRegistLog> findByVkeyAndTime(String vkey, long ctime, long etime,
			Pageable pageable);

	@Query("{'ctime':{$gte:?1},'ctime':{$lte:?2},'refType':?3,'$or':[{'userNick':{'$regex':?0}},{'email':{'$regex':?0}},{'phoneNumber':{'$regex':?0}}]}")
	Page<UserRegistLog> findByUkeyAndTime(String ukey, long ctime, long etime,
			String type,Pageable pageable);

	@Query("{'ctime':{$gte:?0},'ctime':{$lte:?1}}")
	Page<UserRegistLog> findByTime(long ct, long et, Pageable pageable);

	Page<UserRegistLog> findByAdSellerIdLikeAndCtimeBetween(String adsId,
			long ctime, long etime,Pageable pageable);
	
	@Query("{'ctime':{$gte:?2},'ctime':{$lte:?3},'refType':'1','adSellerId':{'$regex':?1},'$or':[{'userNick':{'$regex':?0}},{'email':{'$regex':?0}},{'phoneNumber':{'$regex':?0}}]}")
	Page<UserRegistLog> findAllInfo(String userKey, String adsId, long ctime,
			long etime, Pageable pageable);

	Page<UserRegistLog> findByCtimeBetweenAndCtnAndAdIdNotNull(long ctime, long etime,int i,Pageable pageable);

	@Query("{'ctime':{$gte:?2},'ctime':{$lte:?3},'refType':'?4','$or':[{'userNick':{'$regex':?0}},{'email':{'$regex':?0}},{'phoneNumber':{'$regex':?0}},{'vUserNick':{'$regex':?1}},{'vUserEmail':{'$regex':?1}},{'vUserPhone':{'$regex':?1}}]}")
	Page<UserRegistLog> findByUkeyAndVkey(String ukey, String vkey, long ctime,
			long etime, String type,Pageable pageable);
	UserRegistLog findOneByUserId(String userId);

	UserRegistLog findOneByName(String name);

	Page<UserRegistLog> findByAdSellerIdLikeAndCtimeBetweenAndCtn(String adsId,
			long ctime, long etime, int i, Pageable pageable);

	Page<UserRegistLog> findByCtimeBetweenAndRefType(long ctime, long etime,
			String logRegTypeQd, Pageable pageable);

	Page<UserRegistLog> findByAdSellerIdLikeAndCtimeBetweenAndRefType(
			String adsId, long ctime, long etime, String logRegTypeQd,
			Pageable pageable);

//	Page<UserRegistLog> findByCkeyAndTimeBetweenAndRefType(String userKey, long ctime,
//			long etime, String logRegTypeQd, Pageable pageable);
//	
	

}

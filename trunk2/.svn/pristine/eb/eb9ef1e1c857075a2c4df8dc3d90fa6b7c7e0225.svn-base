package operation.repo.ad;


import operation.pojo.ad.Ad;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface AdReposity extends MongoRepository<Ad, String> {
	
	Page<Ad> findByCtimeBetween(long ctime, long etime,
			Pageable pageable);
	@Query("{'ctime':{$gt:?1},'ctime':{$lte:?2},'$or':[{'adSellerName':{'$regex':?0}}]}")
	Page<Ad> findByAdSellerNameLikeAndCtimeBetween(String qdName,
			long ctime, long etime, Pageable pageable);
	@Query("{'ctime':{$gt:?1},'ctime':{$lte:?2},'$or':[{'adSellerId':{'$regex':?0}}]}")
	Page<Ad> findByAdSellerIdLikeAndCtimeBetween(String qdId,
			long ctime, long etime, Pageable pageable);
	@Query("{'ctime':{$gt:?2},'ctime':{$lte:?3},'adSellerId':{'$regex':?0},'$or':[{'adSellerName':{'$regex':?1}}]}")
	Page<Ad> findAllInfo(String qdId, String qdName, long ctime,
			long etime, Pageable pageable);
	

}

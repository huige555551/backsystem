package operation.repo.fav;

import operation.pojo.fav.Fav;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FavRepository extends MongoRepository<Fav, String>{
	Page<Fav> findByAppKeyAndSourceIdAndFavType(String appkey,String sourceId,int  favType,Pageable pageable);
	Fav findByUserIdAndAppKeyAndSourceIdAndFavType(String userId,String appkey,String sourceId,int favType);
	int countByAppKeyAndSourceIdAndFavType(String appkey,String sourceId,int favType);
	//用户关于某个类别的收藏列表
	Page<Fav> findByUserIdAndAppKeyAndFavTypeOrUserIdAndAppKeyAndFavType(String userId,String appkey,String favType,String userId1,String appkey1,String favType1,Pageable pageable);
	Page<Fav> findByUserIdAndAppKeyAndFavType(String userId,String appkey,String favType,Pageable pageable);
	//获得某一课程下的用户收藏列表
	Page<Fav> findBySourceIdAndDomain(String groupCourseId, String domain,Pageable pageable);
	Page<Fav> findByUserId(String userId, Pageable pageable);
	
	Page<Fav> findByUserIdAndFavTypeOrUserIdAndFavType(String userId,String favType,String userId1,String favType1,Pageable pageable);
	
	Page<Fav> findByUserIdAndFavType(String userId,String favType,Pageable pageable);
	
	Page<Fav> findByUserIdAndFavTypeOrUserIdAndFavTypeOrUserIdAndFavType(String userId,String favType,String userId1,String favType1,String userId2,String favType2,Pageable pageable);
	
	
}

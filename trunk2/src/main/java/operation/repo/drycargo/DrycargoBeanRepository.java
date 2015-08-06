//package operation.repo.drycargo;
//
//
//import java.util.List;
//
//import operation.pojo.drycargo.DrycargoBean;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.Query;
//
//public interface DrycargoBeanRepository extends MongoRepository<DrycargoBean, String>{
//
//	DrycargoBean findOneByUrl(String url);
//	
//	DrycargoBean findOneById(String id);
//	
//	List<DrycargoBean>findBySourceId(String sourceId);
//
//	Page<DrycargoBean> findByMessageLikeOrTagNamesLike(String keywords,String keywords2,Pageable pageable);
//	
//	@Query("{'shareCount':{$ne:?0},'dryFlag':0,'$or':[{'message':{'$regex':?1}},{'tagNames':{'$regex':?1}}]}")
//	Page<DrycargoBean> findByOthers(int shareCount,String keywords,Pageable pageable);
//
//	Page<DrycargoBean> findByCtimeBetween(long t2, long t1, Pageable pageable);
//
//	Page<DrycargoBean> findByIdIn(List<String> ids,Pageable pageable);
//	
//	Page<DrycargoBean> findByDryFlagAndShareCountNot(int i, int j,
//			Pageable pageable);
//
//	Page<DrycargoBean> findByDryFlag(int i, Pageable pageable);
//
//	@Query("{'dryFlag':0,'$or':[{'message':{'$regex':?1}},{'tagNames':{'$regex':?1}}]}")
//	Page<DrycargoBean> findByOther(int i, String keywords, Pageable pageable);
//  
//}

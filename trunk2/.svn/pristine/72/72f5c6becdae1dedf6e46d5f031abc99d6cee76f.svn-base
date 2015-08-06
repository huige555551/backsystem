package operation.repo.drycargo;

import java.util.List;

import operation.pojo.drycargo.UserDrycargoBean;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDrycargoRepository extends MongoRepository<UserDrycargoBean, String>{
	
	List<UserDrycargoBean>  findByDryCargoBean(String drycargoId);
	
	UserDrycargoBean findByUserAndDryCargoBean(String userID,String drycargoId);

	Page<UserDrycargoBean> findByUserAndIsFav(String userId, int i, Pageable page);
	
	Page<UserDrycargoBean> findByUserAndIsFavAndDryFlag(String userId, int i,int dryFlag, Pageable page);

}

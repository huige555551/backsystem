package operation.repo.box;

import java.util.List;

import operation.pojo.box.Box;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BoxRepository extends MongoRepository<Box, String>{
	int countByPost(String boxPostId);
	Page<Box> findByPost(String boxPostId,Pageable pageable);
	List<Box> findByPost(String boxPostId);
	List<Box> findByGroupid(String groupid);
	Box findByPostAndSourceId(String boxPostId,String SourceId);
}

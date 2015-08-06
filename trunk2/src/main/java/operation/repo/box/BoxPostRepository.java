package operation.repo.box;

import java.util.List;

import operation.pojo.box.BoxPost;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BoxPostRepository extends MongoRepository<BoxPost, String>{

	Page<BoxPost> findByType(String type,Pageable pageable);
	List<BoxPost> findByType(String type);
	BoxPost findOneByEnglishName(String name);
	BoxPost findById(String id);
}

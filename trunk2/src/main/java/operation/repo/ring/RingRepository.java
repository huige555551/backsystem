package operation.repo.ring;

import operation.pojo.ring.Ring;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RingRepository extends MongoRepository<Ring, String>{
	Ring findByUser(String user);
	
	Page<Ring> findAll(Pageable page);

}

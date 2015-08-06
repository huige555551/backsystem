package operation.repo.callpolice;

import operation.pojo.ad.AdSeller;
import operation.pojo.callpolice.Callpolice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface callpoliceReposity extends MongoRepository<Callpolice, String>{
	Page<Callpolice> findAllByType(String type,Pageable page);

}

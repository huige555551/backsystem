package operation.repo.idgen;

import java.util.List;

import operation.pojo.idgen.IdGen;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IdGenRespository extends MongoRepository<IdGen, String>{
	List<IdGen> findAll();

}

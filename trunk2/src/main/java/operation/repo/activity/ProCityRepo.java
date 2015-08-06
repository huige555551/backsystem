package operation.repo.activity;

import java.util.List;

import operation.pojo.activity.ProCity;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProCityRepo extends MongoRepository<ProCity, String>{
	
	List<ProCity> findByPId(String pId);

}

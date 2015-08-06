package operation.repo.activity;

import java.util.List;

import operation.pojo.activity.Activity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActivityRepo extends MongoRepository<Activity, String>{

	Page<Activity> findByCity(String city, Pageable pageable);

	Page<Activity> findByActivityStartTimeBetweenAndCityAndNameLike(Long ctime, Long etime,
			String city, String name,Pageable pageable);

	Page<Activity> findByActivityStartTimeBetweenAndNameLike(Long ctime, Long etime,String name, Pageable pageable);

	List<Activity> findByIdIn(List<Object> ids);

	Page<Activity> findByIdNotIn(List<Object> ids, Pageable pageable);

	List<Activity> findByNameRegexAndIdNotIn(String keyword, List<Object> ids);

	
	
}

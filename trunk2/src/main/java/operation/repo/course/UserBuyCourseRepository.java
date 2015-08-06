package operation.repo.course;


import operation.pojo.course.UserBuyCourse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserBuyCourseRepository extends  MongoRepository<UserBuyCourse,String> {
	
	Page<UserBuyCourse> findAllByUserId(String userId,Pageable pageable);
	Page<UserBuyCourse> findByCourseId(String courseid,Pageable pageable);
	
}

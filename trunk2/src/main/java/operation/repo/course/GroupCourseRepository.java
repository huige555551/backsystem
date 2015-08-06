package operation.repo.course;

import java.util.List;

import operation.pojo.course.GroupCourse;
import operation.pojo.course.NewGroupCourse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupCourseRepository extends MongoRepository<GroupCourse, String>{

	Page<GroupCourse> findByGroup(String groupId,Pageable pageable);
	Page<GroupCourse> findByGroupAndDisPlay(String groupId,int disPlay,Pageable pageable);
	GroupCourse findByGroupAndCourse(String groupId,String courseId);
	
	Page<GroupCourse> findByGroupIn(List<Object> groupId,Pageable pageable);
	
	int countByGroup(String groupId);
	List<GroupCourse> findByCourseIn(List<String> courseIds);
	List<GroupCourse> findByGroupIn(String groupId);
	NewGroupCourse findOneByCourse(String courseId);
	
}

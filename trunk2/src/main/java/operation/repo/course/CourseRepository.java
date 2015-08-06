package operation.repo.course;

import java.util.List;

import operation.pojo.course.Course;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends MongoRepository<Course, String>{
	
	Course findOneById(String courseId);
	List<Course> findAll();

	Course findOneByTitle(String title);
	List<Course> findByTitleLikeOrIntroLike(String titler,String intor);
	List<Course> findByIdIn(List<Object> courseIdList);
	
	Page<Course> findByChapters(String chaptersId,Pageable pages);
	
	
	Page<Course> findByIdNotIn(String courseId,Pageable pages);
	
	int countByWhoShare(String userId);
	Page<Course> findByTitleRegexOrIntroRegex(String title,String intro, Pageable pageable);
	
}

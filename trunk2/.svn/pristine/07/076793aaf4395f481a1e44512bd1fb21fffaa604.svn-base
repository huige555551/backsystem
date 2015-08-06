package operation.repo.usercoursechapter;

import java.util.List;

import operation.pojo.user.UserCourseChapter;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserCourseChapterRepository extends MongoRepository<UserCourseChapter, String>{
	
	UserCourseChapter findByUserIdAndCourseIdAndChapterId(String userId,String courseId,String chapterId);
	
	List<UserCourseChapter> findByUserIdAndCourseId(String userId,String courseId);
	
	
	UserCourseChapter findByUserIdAndChapterId(String userId,String courseId);
	List<UserCourseChapter> findByUserId(String userId);
	List<UserCourseChapter> findByUserIdAndCtimeGreaterThan(String userId,long time);
}

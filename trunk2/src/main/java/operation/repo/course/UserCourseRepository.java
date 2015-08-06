package operation.repo.course;

import java.util.List;

import operation.pojo.course.UserCourse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserCourseRepository extends MongoRepository<UserCourse, String>{

	UserCourse findByUserAndCourse(String userId,String courseId);
	
	UserCourse findByUserAndCourseAndSkillId(String userId,String courseId,String skillId);
	
	//查询我的课程收藏列表
	Page<UserCourse>  findByUserAndIsFavGreaterThan(String userId,int isFav,Pageable page);
	
	//查询我的课程学习列表
	Page<UserCourse>  findByUserAndIsStudyGreaterThan(String userId,int isStudy,Pageable page);
	
	//查询某一课程被收藏数量
	List<UserCourse>  findByCourseAndIsFavGreaterThan(String courseId,int isFav);
	
	//查询某一课程被学习数量
   List<UserCourse>  findByCourseAndIsStudyGreaterThan(String courseId,int isStudy);
   
   //根据用户查询课程列表
   Page<UserCourse> findByUser(String userId,Pageable page);
   
 //  UserCourse findByUserAndCourseAndIsStudyAndProessidLessThan(String userId,String courseId,int isStudy,double proess);
   
  // UserCourse findByUserAndCourseAndIsStudyAndProessid(String userId,String courseId,int isStudy,double proess);
  
   //根据用户ID和技能ID查出此用户的学习课程(包括学习中和已经学习完成的)
   List<UserCourse> findByUserAndSkillIdAndIsStudy(String userId,String skillId,int isSutdy);
   
   //根据用户ID和技能ID查出此用户的已经学习课程
 //  Page<UserCourse> findByUserAndSkillIdAndIsStudyAndProessid(String userId,String skillId,int isSutdy,double proessid,Pageable pga);
   
   //根据用户ID和技能ID查出此用户的正在学习课程
 //  Page<UserCourse> findByUserAndSkillIdAndIsStudyAndProessidLessThan(String userId,String skillId,int isSutdy,double proessid,Pageable pga);
   
   UserCourse findByUserAndCourseAndIsStudy(String userId,String courseId,int isStudy);
   
   
   //根据用户ID和是否收藏标示是否学习标示查询收藏未学习课程
   Page<UserCourse> findByUserAndIsFavAndIsStudy(String userId,int isFav,int isStudy,Pageable pga);
   //根据用户ID和是否收藏标示是否学习标示查询收藏学习中的课程
 //  Page<UserCourse> findByUserAndIsFavAndIsStudyAndProessidLessThan(String userId,int isFav,int isStudy,double proessid,Pageable pga);
   //根据用户ID和是否收藏标示是否学习标示查询收藏学习完的课程
   Page<UserCourse> findByUserAndIsFavAndIsStudyAndIsStudyed(String userId,int isFav,int isStudy,int isStudyed,Pageable pga);
   //根据用户ID和是否学习标示查询收藏学习完的课程
  // Page<UserCourse> findByUserAndIsStudyAndProessid(String userId,int isStudy,double proessid,Pageable pga);
   //根据用户ID和是否学习标示查询收藏学习中的课程
   Page<UserCourse> findByUserAndIsStudyAndIsStudyed(String userId,int isStudy,int isStudyed,Pageable pga);
   
   //根据用户ID和技能ID查出此用户的正在学习课程
   Page<UserCourse> findByUserAndSkillIdAndIsStudyAndIsStudyed(String userId,String skillId,int isSutdy,int isStudyed,Pageable pga);

   int countByUserAndIsStudy(String userId,int isStudy);
   //根据用户ID和技能ID与是否学习标示统计出来某技能学习过的/正在学习的课程数量
   int countByUserAndSkillIdAndIsStudyAndIsStudyed(String user,String skillId,int isStudy,int isStudyed);
   //根据用户ID和技能ID与是否收藏、是否学习标示统计xia出来某技能学习过的/正在学习的课程数量
   int countByUserAndSkillIdAndIsFavAndIsStudyAndIsStudyed(String user,String skillId,int isFav,int isStudy,int isStudyed);
   //根据用户ID与是否学习标示统计出来某技能学习过的/正在学习的课程数量
   int countByUserAndIsStudyAndIsStudyed(String user,int isStudy,int isStudyed);
   //根据用户ID与是否收藏、是否学习标示统计xia出来某技能学习过的/正在学习的课程数量
   int countByUserAndIsFavAndIsStudyAndIsStudyed(String user,int isFav,int isStudy,int isStudyed);
   
   
   
   
   
}

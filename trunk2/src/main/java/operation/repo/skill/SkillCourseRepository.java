package operation.repo.skill;

import java.util.List;

import operation.pojo.skill.SkillCourse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SkillCourseRepository extends MongoRepository<SkillCourse, String>{
	SkillCourse findBySkillAndCourse(String skillId,String courseId);
	Page<SkillCourse> findBySkillAndCourseNotIn(String skillId,List<Object> courseIds,Pageable pga);
	
	List<SkillCourse> findByCourse(String courseId);
	
	int countSkillCoursesBySkill(String skill);
	
}

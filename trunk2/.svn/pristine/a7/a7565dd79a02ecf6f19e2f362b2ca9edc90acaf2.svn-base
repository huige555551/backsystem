package operation.service.skill;

import java.util.ArrayList;
import java.util.List;

import operation.exception.XueWenServiceException;
import operation.pojo.course.Course;
import operation.pojo.skill.SkillCourse;
import operation.pojo.skill.Skills;
import operation.repo.course.CourseRepository;
import operation.repo.skill.SkillCourseRepository;
import operation.repo.skill.SkillsRepository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
@EnableScheduling

public class SkillCourseService {

	private static final Logger logger =Logger.getLogger(SkillCourseService.class);
	
	@Autowired
	public SkillCourseRepository skillCourseRepository;
	
	@Autowired
	public SkillsRepository skillsRepository;
	
	@Autowired
	public CourseRepository courseRepository;
	public void addSkillCourse()throws XueWenServiceException{
		List<Skills> skills=skillsRepository.findAll();
		for(Skills skill:skills){
			//根据技能预匹配课程
			List<Object> courses=findCourseBySkills(skill.getName());
			for(Object obj:courses){
				if(skillCourseRepository.findBySkillAndCourse(skill.getId(),obj.toString()) == null){
					logger.info("=====为"+skill.getName()+"建立技能课程模型");
					SkillCourse sc=new SkillCourse();
					sc.setCourse(obj);
					sc.setSkill(skill.getId());
					long time= System.currentTimeMillis();
					sc.setUtime(time);
					sc.setCtime(time);
					skillCourseRepository.save(sc);
				}
			}
		}
	}
	
	/**
	 * 根据技能名称搜索课程,返回课程ID
	 * @param skillName
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<Object> findCourseBySkills(String skillName)throws XueWenServiceException{
		List<Course> courses=courseRepository.findByTitleLikeOrIntroLike(skillName,skillName);
		List<Object> cses=new ArrayList<Object>();
		if(courses != null){
			for(Course c:courses){
				cses.add(c.getId());
			}
		}
		return cses;
	}
	
//	public List<Object> findCourseBySkillsId(String skillId)throws XueWenServiceException{
//		SkillCourse skillCourse=skillCourseRepository.findOneBySkill(skillId);
//		List<Object> courseList = skillCourse.getCourse();
//		return courseList;
//	}
	/**
	 * 根据技能ID查询出出去课程ID列表之外的其他技能课程
	 * @param skillId
	 * @param courseIds
	 * @param pga
	 * @return
	 * @throws XueWenServiceException
	 */
	public Page<SkillCourse> findBySkillIdExcCourseIdList(String skillId,List<Object> courseIds,Pageable pga )throws XueWenServiceException{
		return skillCourseRepository.findBySkillAndCourseNotIn(skillId, courseIds, pga);
	}
	
	public List<Object> findSkillIdListByCourseId(String courseId)throws XueWenServiceException{
		List<SkillCourse> scs=skillCourseRepository.findByCourse(courseId);
		if(scs != null){
			List<Object> skills=new ArrayList<Object>();
			for(SkillCourse sc:scs){
				skills.add(sc.getSkill());
			}
			return skills;
		}
		return null;
	}
	
	
	/**
	 * 将课程ID转化成课程对象
	 * @param userCourses
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<SkillCourse> toResponseCourse(List<SkillCourse> skillCourse)throws XueWenServiceException{
		
		for(SkillCourse sc:skillCourse){
			sc.setCourse(courseRepository.findOne(sc.getCourse().toString()));
		}
		return skillCourse;
	}
	
	
	/**
	 * 统计某技能下总共有多少课程
	 * @param skillId
	 * @return
	 * @throws XueWenServiceException
	 */
	public int countSkillCourses(String skillId)throws XueWenServiceException{
		return skillCourseRepository.countSkillCoursesBySkill(skillId);
	}
//	public List<Object> findSkillIdListByCourseIds(String courseId)throws XueWenServiceException{
//		List<SkillCourse> scs=skillCourseRepository.findByCourse(courseId);
//		if(scs != null){
//			List<Object> skills=new ArrayList<Object>();
//			for(SkillCourse sc:scs){
//				skills.add(sc.getSkill());
//			}
//			return skills;
//		}
//		return null;
//		return skillCourseRepository.findSkillByCourse(courseId);
//	}
}

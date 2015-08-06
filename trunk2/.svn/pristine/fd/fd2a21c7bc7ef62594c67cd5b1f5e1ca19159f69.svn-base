package operation.service.user;

import java.util.ArrayList;
import java.util.List;

import operation.exception.XueWenServiceException;
import operation.pojo.course.UserCourse;
import operation.pojo.skill.JobSkills;
import operation.pojo.skill.Skills;
import operation.pojo.user.User;
import operation.pojo.user.UserSkills;
import operation.repo.user.UserRepository;
import operation.repo.user.UserSkillsRepository;
import operation.service.course.UserCourseService;
import operation.service.skill.JobSkillService;
import operation.service.skill.SkillCourseService;
import operation.service.skill.SkillService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import tools.Config;

@Service
@Component
@EnableScheduling
public class UserSkillsService {
	private static final Logger logger = Logger.getLogger(UserService.class);
	@Autowired
	public UserSkillsRepository userSkillsRepository;
	@Autowired
	public JobSkillService jobSkillService;
	@Autowired
	public SkillService skillService;
	@Autowired
	public UserCourseService userCourseService;
	@Autowired
	public SkillCourseService skillCourseService;
	/**
	 * 从标准的岗位-技能模型的十大，复制数据给当前用户
	 * @param user
	 * @return
	 */
	public List<UserSkills> initUserSkillsFromJobSkils(String userId,String jobTitleId,JobSkills skills)throws XueWenServiceException {
		if(skills != null ){
			List<Object>  skill =	skills.getSkills();
			if(skill.size() > 0){
				for(int i = 0 ; i < skill.size() ;i ++){
					if(userSkillsRepository.findOneByUserIdAndJobTitleIdAndSkillId(userId, jobTitleId, skill.get(i).toString())==null){
						UserSkills us = new UserSkills();
						us.setUserId(userId);
						us.setJobTitleId(jobTitleId);
						us.setGrade("10");//评分  待定
						us.setCourseNum(skillCourseService.countSkillCourses(skill.get(i).toString()));
						us.setProgress(statkillProgess(userId,skill.get(i).toString())); //进度
						us.setStatus("0"); //该技能的学习状态，0 未学
						us.setSkillId(skill.get(i).toString());
						us.setSkillsVersion(skills.getVersion());
						userSkillsRepository.save(us);
					}
				}
			}
		}
		
		return null;
	}
	
	/**
	 * 获取用户技能
	 * @param user
	 * @return
	 */
	public List<Object> getUserSkills(User user) {
		return userSkillsRepository.findByUserIdAndJobTitleId(user.getId(),user.getJob().getId());

	}
	/**
	 * 根据用户ID和岗位Id 获取用户此岗位技能列表
	 * @param userId
	 * @param jobId
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<Object> getUserSkills(String userId,String jobId)throws XueWenServiceException{
		return userSkillsRepository.findByUserIdAndJobTitleId(userId,jobId);
	}
	
	/**
	 * 前段返回用户技能列表信息包装
	 * @param userId
	 * @param jobId
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<Object> getResponseUserSkills(String userId,String jobId)throws XueWenServiceException{
		List<Object> skills=getUserSkills(userId,jobId);
		List<Object> sks=new ArrayList<Object>();
		for(Object obj:skills){
			UserSkills sk=(UserSkills)obj;
			UserSkills rspSk=toResponseOneUserSkills(sk);
			if(!sks.contains(rspSk)){
				sks.add(rspSk);
			}
		}
		return sks;
	}
	
	/**
	 * 前端返回用户技能信息包装
	 * @param sk
	 * @return
	 * @throws XueWenServiceException
	 */
	public UserSkills toResponseOneUserSkills(UserSkills sk)throws XueWenServiceException{
		  sk.setSkillId(skillService.getOneSkills(sk.getSkillId().toString()));
		  return sk;
	}
	/**
	 * 用户div增添技能
	 * @param user
	 * @param skillId
	 * @return
	 */
	public void addUserSkills(String userId,String jobTitleId,String skillId)throws XueWenServiceException {
		String [] str = skillId.split(",");
		for(int i = 0 ; i < str.length; i++){
			if(userSkillsRepository.findOneByUserIdAndJobTitleIdAndSkillId(userId, jobTitleId, str[i])==null){
				UserSkills entites = new UserSkills();
				entites.setUserId(userId);
				entites.setJobTitleId(jobTitleId);
				entites.setSkillId(str[i]);
				entites.setCourseNum(skillCourseService.countSkillCourses(skillId));
				entites.setGrade("10");//评分  待定
				entites.setProgress(statkillProgess(userId,skillId)); //进度
//				entites.setProgress(0); //进度
				entites.setStatus("0"); //该技能的学习状态，0 未学
				userSkillsRepository.save(entites);
			}else{
				throw new XueWenServiceException(Config.STATUS_201, Config.MSG_SKILL_201,
						null);
			}
		}
//		return userSkillsRepository.findByUserIdAndJobTitleId(user.getId(),
//				user.getJob().getId());

	}
	
	
	/**
	 * 更改用户技能状态
	 * @param user
	 * @param skillId
	 * @param status
	 * @return
	 */
	public List<Object> changeUserSkillsStatus(User user,String skillId,String status) throws XueWenServiceException{
		UserSkills entites = new UserSkills();
		
		entites.setUserId(user.getId());
		entites.setSkillId(skillId);
		
		userSkillsRepository.save(entites);
		return userSkillsRepository.findByUserIdAndJobTitleId(user.getId(),
				user.getJob().getId());
	}
	/**
	 * 从技能树移除一个技能
	 * @param userId
	 * @param jobTitleId
	 * @param skillId
	 * @return
	 */
	public boolean deleUserSkill(String userId,String jobTitleId,String skillId) throws XueWenServiceException{
		UserSkills us = userSkillsRepository.findOneByUserIdAndJobTitleIdAndSkillId(userId,jobTitleId,skillId);
		if(us != null){
			userSkillsRepository.delete(us);
			return true;
		}else{
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_UPDATE_201,null);
		}
		
	}
	
	/**
	 * 根据userId和skillId计算用户此技能的进度
	 * @param userId
	 * @param skillsId
	 * @throws XueWenServiceException
	 */
	public int statkillProgess(String userId,String skillId)throws XueWenServiceException{
		List<UserCourse> ucs=userCourseService.findUserStudyCourseBySkillId(userId, skillId);
		int proess=0;
		for(UserCourse uc:ucs){
			if(uc.getProessid()>0 && uc.getProessid()==uc.getChapteNum()){
				proess=proess+1;
			}
		}
		return proess;
	}
	
	

	/**
	 * 根据用户Id和技能Id保存用户的技能进度
	 * @param userId
	 * @param skillId
	 * @throws XueWenServiceException
	 */
	public void updateUserSkillProessid(String userId,String skillId)throws XueWenServiceException{
		List<UserSkills> uss=userSkillsRepository.findByUserIdAndSkillId(userId, skillId);
		for(UserSkills us:uss){
			us.setProgress(statkillProgess(userId,skillId));
			us.setCourseNum(skillCourseService.countSkillCourses(skillId));
		}
		userSkillsRepository.save(uss);
	}
	
	/**
	 * 根据用户Id和技能ID列表更新用户技能列表的进度
	 * @param userId
	 * @param skillIds
	 * @throws XueWenServiceException
	 */
	public void updateUserSkillProessid(String userId,List<Object> skillIds)throws XueWenServiceException{
		List<UserSkills> uss=userSkillsRepository.findByUserIdAndSkillIdIn(userId, skillIds);
		for(UserSkills us:uss){
			us.setProgress(statkillProgess(userId,us.getSkillId().toString()));
			us.setCourseNum(skillCourseService.countSkillCourses(us.getSkillId().toString()));
		}
		userSkillsRepository.save(uss);
	}
	
	
	/**
	 * 去掉用户重复的岗位技能ID
	 */
	public void deleteRefuSkill()throws XueWenServiceException{
		List<UserSkills> uss=userSkillsRepository.findAll();
		for(UserSkills us:uss){
			List<UserSkills> ussrefu=userSkillsRepository.findByUserIdAndJobTitleIdAndSkillId(us.getUserId(), us.getJobTitleId(), us.getSkillId().toString());
			if(ussrefu.size()>1){
				logger.info("===="+ussrefu.size()+"====重复的删除："+us.getUserId()+"===:"+us.getJobTitleId()+"=="+us.getSkillId().toString());
				userSkillsRepository.delete(us.getId());
			}
		}
	}
	
	/**
	 * 测试使用，更新用户技能书课程数量
	 * @throws XueWenServiceException
	 */
	public void addCourseNumForUserSKillForTest()throws XueWenServiceException{
		List<UserSkills> uss=userSkillsRepository.findAll();
		for(UserSkills us:uss){
			us.setCourseNum(skillCourseService.countSkillCourses(us.getSkillId().toString()));
			userSkillsRepository.save(us);
		}
	}
		
}

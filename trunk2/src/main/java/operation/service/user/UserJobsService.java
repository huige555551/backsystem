package operation.service.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import operation.exception.XueWenServiceException;
import operation.pojo.jobs.Jobs;
import operation.pojo.skill.JobSkills;
import operation.pojo.skill.Skills;
import operation.pojo.user.ResponseUser;
import operation.pojo.user.User;
import operation.pojo.user.UserJobs;
import operation.pojo.user.UserJobsSkillTree;
import operation.repo.user.UserJobsRepository;
import operation.service.jobs.IndustryService;
import operation.service.skill.JobSkillService;
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
public class UserJobsService {

	private static final Logger logger = Logger
			.getLogger(UserJobsService.class);
	@Autowired
	public UserJobsRepository userJobsRepository;
	@Autowired
	public JobSkillService jobSkillService;
	@Autowired
	public IndustryService industryService;
	@Autowired
	public SkillService skillService;
	
	@Autowired
	public UserSkillsService userSkillsService;

	public UserJobs getUserJobs(String userId) throws XueWenServiceException {
		return userJobsRepository.findOneByUser(userId);
	}

	public UserJobsSkillTree getUserJobsSkillTree(User user) throws XueWenServiceException{
		try {
			UserJobs uj = this.getUserJobs(user.getId());
			if(uj!=null){
			//当前用户岗位--技能
			JobSkills nowJobSkills=new JobSkills();
			//获取用户当前岗位信息
			nowJobSkills.setJobs(industryService.findJobs(uj.getNowJobs().toString()));
			//获取用户当前岗位技能信息
			List<Object> nowUserJobSkill=userSkillsService.getResponseUserSkills(user.getId(), uj.getNowJobs().toString());
			nowJobSkills.setSkills(nowUserJobSkill);
			
			
			//感性趣的用户岗位--技能
//			JobSkills favJobSkills=new JobSkills();
			//获取用户感性趣的前岗位信息
//			favJobSkills.setJobs(industryService.findJobs(uj.getFavJobs().toString()));
			//获取用户感性趣的前岗位技能信息
		
//			List<Object> favUserJobSkill=userSkillsService.getResponseUserSkills(user.getId(), uj.getFavJobs().toString());
//			favJobSkills.setSkills(favUserJobSkill);
			
//			JobSkills nowJob = jobSkillService.findJobSkills(uj.getNowJobs().toString());
//			List<Object> nowobj = nowJob.getSkills();
//			List<Object> nowskills = skillService.getSkills(nowobj);
//			nowJob.setSkills(nowskills);
//			
//			String jobs = nowJob.getJobs().toString();
//			Jobs  job = industryService.findJobs(jobs);
//			nowJob.setJobs(job);
//			
//			JobSkills favJob = jobSkillService.findJobSkills(uj.getFavJobs()
//					.toString());
//			List<Object> ojb = favJob.getSkills();
//			List<Object> skills = skillService.getSkills(ojb);
//			
//			favJob.setSkills(skills);
			
//			UserJobsSkillTree ust = new UserJobsSkillTree(nowJobSkills, favJobSkills);
			UserJobsSkillTree ust = new UserJobsSkillTree(nowJobSkills, null);
			logger.info("返回技能树" + ust);
			return ust;
			}else{
				throw new XueWenServiceException(Config.STATUS_201, Config.MSG_201,
						null);
			}
		} catch (XueWenServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 注册成功或者修改岗位时更新用户岗位对象
	 * @param userId
	 * @param nowJobs
	 * @param favJobs
	 */
	public void addUserJobs(String userId, String nowJobs, String favJobs) throws XueWenServiceException{
		UserJobs uj = userJobsRepository.findOneByUser(userId);
		long time = System.currentTimeMillis();
		//新用户注册时
		if (uj == null) {
			UserJobs userjobs = new UserJobs();
			userjobs.setUser(userId);
			userjobs.setNowJobs(nowJobs);
			userjobs.setFavJobs(favJobs);
			
			userjobs.setCtime(time);
			userjobs.setUtime(time);
			Map<Long, String> mapNowJobs = new HashMap<Long, String>();
			mapNowJobs.put(time, nowJobs);
			userjobs.setNowHistory(mapNowJobs);
			Map<Long, String> mapFavJobs = new HashMap<Long, String>();
			mapFavJobs.put(time, favJobs);
			userjobs.setNowHistory(mapFavJobs);
			userJobsRepository.save(userjobs);
			//通过岗位ID获得当前岗位默认10大技能
			JobSkills  nowJobskills = this.getDefaultJobSkills(nowJobs,"0");
			userSkillsService.initUserSkillsFromJobSkils(userId,nowJobs,nowJobskills);
			//通过岗位ID获得感兴趣的岗位默认10大技能
			JobSkills  favJobskills = this.getDefaultJobSkills(favJobs,"0");
			userSkillsService.initUserSkillsFromJobSkils(userId,favJobs,favJobskills);
			
		}
		//修改岗位时
		else{
			//如果当前岗位有变动，则调整历史记录
			if(!nowJobs.equals(uj.getNowJobs())){
				uj.setNowJobs(nowJobs);
				Map<Long,String> mapNowHis = uj.getNowHistory();
				Map<Long, String> mapNowJobs = new HashMap<Long, String>();
				mapNowJobs.put(time, uj.getNowJobs().toString());
				mapNowHis.putAll(mapNowJobs);
				uj.setNowHistory(mapNowHis);
				//通过岗位ID获得当前岗位默认10大技能
				JobSkills  nowJobskills = this.getDefaultJobSkills(nowJobs,"0");
				userSkillsService.initUserSkillsFromJobSkils(userId,nowJobs,nowJobskills);
				
			}
			//如果感兴趣岗位有变动，则调整历史记录
			if(!favJobs.equals(uj.getFavJobs())){
				uj.setFavJobs(favJobs);
				Map<Long, String> mapFavJobs = new HashMap<Long, String>();
				mapFavJobs.put(time, uj.getFavJobs().toString());
				Map<Long,String> mapFavHis = uj.getNowHistory();
				mapFavHis.putAll(mapFavJobs);
				uj.setFavHistory(mapFavHis);
				JobSkills  favJobskills = this.getDefaultJobSkills(favJobs,"0");
				userSkillsService.initUserSkillsFromJobSkils(userId,favJobs,favJobskills);
			}
			
			userJobsRepository.save(uj);
		}
	}
	/**
	 * 注册成功或者修改岗位时更新用户岗位对象
	 * @param userId
	 * @param nowJobs
	 * @param favJobs
	 */
	public void addUserJobs(String userId, String nowJobs) throws XueWenServiceException{
		UserJobs uj = userJobsRepository.findOneByUser(userId);
		long time = System.currentTimeMillis();
		//新用户注册时
		if (uj == null) {
			UserJobs userjobs = new UserJobs();
			userjobs.setUser(userId);
			userjobs.setNowJobs(nowJobs);
			
			userjobs.setCtime(time);
			userjobs.setUtime(time);
			Map<Long, String> mapNowJobs = new HashMap<Long, String>();
			mapNowJobs.put(time, nowJobs);
			userjobs.setNowHistory(mapNowJobs);
//			Map<Long, String> mapFavJobs = new HashMap<Long, String>();
//			mapFavJobs.put(time, favJobs);
//			userjobs.setNowHistory(mapFavJobs);
			userJobsRepository.save(userjobs);
			//通过岗位ID获得当前岗位默认10大技能
			JobSkills  nowJobskills = this.getDefaultJobSkills(nowJobs,"0");
			userSkillsService.initUserSkillsFromJobSkils(userId,nowJobs,nowJobskills);
			//通过岗位ID获得感兴趣的岗位默认10大技能
//			JobSkills  favJobskills = this.getDefaultJobSkills(favJobs,"0");
//			userSkillsService.initUserSkillsFromJobSkils(userId,favJobs,favJobskills);
			
		}else{
			//修改岗位时
			//如果当前岗位有变动，则调整历史记录
			if(!nowJobs.equals(uj.getNowJobs())){
				uj.setNowJobs(nowJobs);
				Map<Long,String> mapNowHis = uj.getNowHistory();
				Map<Long, String> mapNowJobs = new HashMap<Long, String>();
				mapNowJobs.put(time, uj.getNowJobs().toString());
				mapNowHis.putAll(mapNowJobs);
				uj.setNowHistory(mapNowHis);
				//通过岗位ID获得当前岗位默认10大技能
				JobSkills  nowJobskills = this.getDefaultJobSkills(nowJobs,"0");
				userSkillsService.initUserSkillsFromJobSkils(userId,nowJobs,nowJobskills);
				
			}
			//如果感兴趣岗位有变动，则调整历史记录
//			if(!favJobs.equals(uj.getFavJobs())){
//				uj.setFavJobs(favJobs);
//				Map<Long, String> mapFavJobs = new HashMap<Long, String>();
//				mapFavJobs.put(time, uj.getFavJobs().toString());
//				Map<Long,String> mapFavHis = uj.getNowHistory();
//				mapFavHis.putAll(mapFavJobs);
//				uj.setFavHistory(mapFavHis);
//				JobSkills  favJobskills = this.getDefaultJobSkills(favJobs,"0");
//				userSkillsService.initUserSkillsFromJobSkils(userId,favJobs,favJobskills);
//			}
			
			userJobsRepository.save(uj);
		}
	}
	/**
	 * 通过jobID查询默认该岗位的10大技能，包括版本号
	 * @param jobId
	 * @return
	 */
	public JobSkills getDefaultJobSkills(String jobId,String weight){
		JobSkills js = null;
		try {
			js = jobSkillService.getDefaultTenSkills(jobId,weight);
		} catch (XueWenServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	List<Object>  skills = 	js.getSkills();
		return js;
	}
	
	
}

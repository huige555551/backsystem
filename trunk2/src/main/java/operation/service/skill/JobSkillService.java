package operation.service.skill;

import java.util.ArrayList;
import java.util.List;

import operation.exception.XueWenServiceException;
import operation.pojo.skill.JobSkills;
import operation.pojo.skill.Skills;
import operation.pojo.user.User;
import operation.pojo.user.UserSkills;
import operation.repo.skill.JobSkillRepository;
import operation.repo.skill.SkillsRepository;
import operation.service.user.UserSkillsService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
@EnableScheduling
public class JobSkillService {
	private static final Logger logger = Logger
			.getLogger(JobSkillService.class);
	@Autowired
	public JobSkillRepository jobSkillRepository;

	@Autowired
	public SkillService skillService;
	
	@Autowired
	public UserSkillsService userSkillsService;

	public JobSkills findJobSkills(String jobId) {
		return jobSkillRepository.findOneByJobs(jobId);
	}

	/**
	 * 获取岗位默认的十大技能(weight:0 为10大技能 1：其余技能)
	 * 
	 * @return
	 */
	public JobSkills getDefaultTenSkills(String jobTitleId, String weight)
			throws XueWenServiceException {
		return jobSkillRepository.findOneByJobsAndWeight(jobTitleId, weight);
	}

	/**
	 * 添加岗位技能
	 * 
	 * @param jobId
	 * @param skills
	 * @param weight
	 * @throws XueWenServiceException
	 */
	public void addJobSkills(String jobId, String skills, String weight)
			throws XueWenServiceException {
		List<Skills> sks = skillService.addSkills(skills);
		JobSkills js=jobSkillRepository.findOneByJobsAndWeight(jobId, weight);
		long time = System.currentTimeMillis();
		if(js == null){
			js = new JobSkills();
			js.setCtime(time);
			js.setJobs(jobId);
			List<Object> skillsList = new ArrayList<Object>();
			for (Skills sk : sks) {
				skillsList.add(sk.getId());
			}
			js.setSkills(skillsList);
			js.setWeight(weight);
			
		}else{
			for (Skills sk : sks) {
				js.getSkills().add(sk.getId());
			}
		}
		js.setUtime(time);
		js.setVersion(time);
		jobSkillRepository.save(js);
	}

	/**
	 * 获得岗位其他技能
	 * 
	 * @param jobTitleId
	 * @param weight
	 * @return
	 * @throws XueWenServiceException 
	 */
	public List<Object> getSkillByJobId(String jobTitleId, String weight) throws XueWenServiceException {
		JobSkills js = null;
		try {
			js = this.getDefaultTenSkills(jobTitleId, weight);
		} catch (XueWenServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Object> skillIds = null;
		List<Object> Skills = null;
		if (js != null) {
			skillIds = js.getSkills();
			
			Skills = skillService.getSkills(skillIds);
		}
		return Skills;

	}
	/**
	 * 获得岗位其他技能
	 * 
	 * @param jobTitleId
	 * @param weight
	 * @return
	 * @throws XueWenServiceException 
	 */
	public List<Object> getSkillByJobId(String jobTitleId, String weight,User user) throws XueWenServiceException {
		JobSkills js = null;
		//非10大技能
		js = this.getDefaultTenSkills(jobTitleId, weight);
		//10大技能
		JobSkills tenSkills=this.getDefaultTenSkills(jobTitleId, "0");
		List<Object> skillIds = null;
		List<Object> Skills = null;
		if (js != null) {
			//非十大岗位技能
			skillIds = js.getSkills();
			if(tenSkills !=null){
				skillIds.addAll(tenSkills.getSkills());
			}
			//获取当前用户岗位技能ID结合
			List<Object> uss=userSkillsService.getUserSkills(user);
			List<Object> userSkillIds=new ArrayList<Object>();
			for(Object obj:uss){
				UserSkills us=(UserSkills)obj;
				Object usId=us.getSkillId();
				userSkillIds.add(usId);
			}
			//在岗位的非十大技能中去掉用户已经添加过得技能Id
			skillIds.removeAll(userSkillIds);
			Skills = skillService.getSkills(skillIds);
		}
		return Skills;
		
	}

}

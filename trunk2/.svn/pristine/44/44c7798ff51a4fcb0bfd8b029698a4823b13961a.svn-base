package operation.service.skill;

import java.util.ArrayList;
import java.util.List;

import operation.exception.XueWenServiceException;
import operation.pojo.skill.Skills;
import operation.repo.skill.SkillsRepository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Caching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import tools.StringToList;
import tools.StringUtil;

@Service
@Component
@EnableScheduling
@Caching
public class SkillService {

	private static final Logger logger=Logger.getLogger(SkillService.class);
	
	@Autowired
	public SkillsRepository skillsRepository;
	
	public List<Object> getSkills(List<Object> skillsId)throws XueWenServiceException{
		List<String> ids=new ArrayList<String>();
		for(Object obj:skillsId){
			ids.add(obj.toString());
		}
		return  skillsRepository.findByIdIn(ids);
	//	return  null;
	}
	
	/**
	 * 添加新技能
	 * @param skills
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<Skills> addSkills(String skills)throws XueWenServiceException{
		List<Object> skillsList=StringToList.tranfer(skills);
		List<Skills> sks=new ArrayList<Skills>();
		for(Object obj:skillsList){
			Skills sk=skillsRepository.findByName(obj.toString());
			if(sk == null){
				sk=new Skills();
				sk.setName(obj.toString());
				sk=skillsRepository.save(sk);
			}
			sks.add(sk);
		}
		return sks;
	}
	
	/**
	 * 得到一个技能
	 * @param skillId
	 * @return
	 * @throws XueWenServiceException
	 */
	public Skills getOneSkills(String skillId)throws XueWenServiceException{
		return skillsRepository.findOne(skillId);
	}
}

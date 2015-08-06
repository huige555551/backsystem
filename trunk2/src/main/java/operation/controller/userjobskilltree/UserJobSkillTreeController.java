package operation.controller.userjobskilltree;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import operation.BaseController;
import operation.exception.XueWenServiceException;
import operation.pojo.skill.JobSkills;
import operation.pojo.user.ResponseUser;
import operation.pojo.user.User;
import operation.pojo.user.UserJobsSkillTree;
import operation.service.skill.JobSkillService;
import operation.service.user.UserJobsService;
import operation.service.user.UserService;
import operation.service.user.UserSkillsService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tools.Config;
import tools.ResponseContainer;

@RestController
@RequestMapping("/userjobskilltree")
@Configuration
public class UserJobSkillTreeController extends BaseController{
	
	private static final Logger logger=Logger.getLogger(UserJobSkillTreeController.class);
	
	@Autowired
	public UserJobsService userJobsService;
	
	@Autowired
	public UserSkillsService userSkillsService;
	
	@Autowired
	public JobSkillService jobSkillService;
	
	/**
	 * 根据用户默认岗位技能树
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/one")
	// @Cacheable()
	public @ResponseBody ResponseContainer findUserJobSkillTree(HttpServletRequest request) {
		try{
			String token = request.getParameter("token");
			User currentUser = this.getCurrentUser(token);
			UserJobsSkillTree userJobsSkillTree = userJobsService.getUserJobsSkillTree(currentUser);
			//ResponseUser respUser = new ResponseUser(userJobsSkillTree);
		return addResponse(Config.STATUS_200,Config.MSG_200,userJobsSkillTree,Config.RESP_MODE_10,"");
		}catch (Exception e){
			e.printStackTrace();
			return addResponse(Config.STATUS_505,Config.MSG_505,false,Config.RESP_MODE_10,"");
		}
		
	}
	
	/**
	 * 删除某一技能
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleSkill")
	public @ResponseBody ResponseContainer deleSkill(HttpServletRequest request) {
		try{
			String token = request.getParameter("token");
			User currentUser = this.getCurrentUser(token);
			String jobTitleId = request.getParameter("jobTitleId");
			String skillId = request.getParameter("skillId");
			boolean  result = userSkillsService.deleUserSkill(currentUser.getId(), jobTitleId, skillId);
		return addResponse(Config.STATUS_200,Config.MSG_200,result,Config.RESP_MODE_10,"");
		}catch (Exception e){
			e.printStackTrace();
			return addResponse(Config.STATUS_505,Config.MSG_505,false,Config.RESP_MODE_10,"");
		}
	}
	
	/**
	 * 查询某一岗位其他技能
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOtherSkill")
	// @Cacheable()
	public @ResponseBody ResponseContainer findOtherSkill(HttpServletRequest request) {
		try{
			String jobTitleId = request.getParameter("jobTitleId");
			String token = request.getParameter("token");
			User currentUser = this.getCurrentUser(token);
			List<Object>  skills = jobSkillService.getSkillByJobId(jobTitleId, "1",currentUser);
			return addResponse(Config.STATUS_200,Config.MSG_200,skills,Config.RESP_MODE_10,"");
		}catch (Exception e){
			e.printStackTrace();
			return addResponse(Config.STATUS_505,Config.MSG_505,false,Config.RESP_MODE_10,"");
		}
		
	}
	
	/**
	 * 用户div增添技能
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/addUserSkills")
	// @Cacheable()
	public @ResponseBody ResponseContainer addUserSkills(HttpServletRequest request) {
		try{
			String jobTitleId = request.getParameter("jobTitleId");
			String token = request.getParameter("token");
			String skillId = request.getParameter("skillId");
			User currentUser = this.getCurrentUser(token);
			userSkillsService.addUserSkills(currentUser.getId(),jobTitleId,skillId);
		return addResponse(Config.STATUS_200,Config.MSG_200,true,Config.RESP_MODE_10,"");
		}catch (XueWenServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return addResponse(e.getCode(),e.getMessage(),false,Config.RESP_MODE_10,"");
		}catch (Exception e){
			e.printStackTrace();
			return addResponse(Config.STATUS_505,Config.MSG_505,false,Config.RESP_MODE_10,"");
		}
		
	}
	
	
	/**
	 * 去掉所有用户的重复用户岗位技能
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteReful")
	// @Cacheable()
	public @ResponseBody ResponseContainer deleteReful(HttpServletRequest request) {
		try{
			userSkillsService.deleteRefuSkill();
			return addResponse(Config.STATUS_200,Config.MSG_200,true,Config.RESP_MODE_10,"");
		}catch (Exception e){
			e.printStackTrace();
			return addResponse(Config.STATUS_505,Config.MSG_505,false,Config.RESP_MODE_10,"");
		}
		
	}
	

}

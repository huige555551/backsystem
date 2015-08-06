package operation.controller.skill;

import javax.servlet.http.HttpServletRequest;

import operation.BaseController;
import operation.exception.XueWenServiceException;
import operation.pojo.user.User;
import operation.service.skill.JobSkillService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tools.Config;
import tools.ResponseContainer;


@RestController
@RequestMapping("/jobskills")
@Configuration

public class JobSkillsController extends BaseController {
	private static final Logger logger=Logger.getLogger(JobSkillsController.class);
	
	@Autowired
	public JobSkillService jobSkillService;
	
	@RequestMapping("addJobSkills")
	public @ResponseBody ResponseContainer addJobSkills(HttpServletRequest request) {
		try {
			String token = request.getParameter("token");
			User currentUser = this.getCurrentUser(token);
			String jobId=request.getParameter("jobId");
			String skills=request.getParameter("skills");
			String weight=request.getParameter("weight");
			jobSkillService.addJobSkills(jobId, skills,weight);
			return addResponse(Config.STATUS_200,Config.MSG_OUT_200,true,Config.RESP_MODE_10,"");
		}catch (XueWenServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return addResponse(e.getCode(),e.getMessage(),false,Config.RESP_MODE_10,"");
		} catch (Exception e){
			e.printStackTrace();
			return addResponse(Config.STATUS_505,Config.MSG_505,false,Config.RESP_MODE_10,"");
		}
	}
	
}

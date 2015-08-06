package operation.controller.skill;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import operation.BaseController;
import operation.exception.XueWenServiceException;
import operation.pojo.user.User;
import operation.service.skill.SkillCourseService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tools.Config;
import tools.ResponseContainer;

@RestController
@RequestMapping("/skillCourse")
@Configuration

public class SkillCourseController extends BaseController{

	private static final Logger logger=Logger.getLogger(SkillCourseController.class);
	
	@Autowired
	public SkillCourseService skillCourseService;
	
	@RequestMapping("addSkillCourse")
	public @ResponseBody ResponseContainer addSkillCourse(HttpServletRequest request) {
		try {
			String token = request.getParameter("token");
			User currentUser = this.getCurrentUser(token);
			skillCourseService.addSkillCourse();
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
	
	@RequestMapping("test")
	public @ResponseBody ResponseContainer test(HttpServletRequest request) {
		try {
			String token = request.getParameter("token");
			User currentUser = this.getCurrentUser(token);
			String courseId=request.getParameter("courseId");
			List<Object> objs=skillCourseService.findSkillIdListByCourseId(courseId);
			return addResponse(Config.STATUS_200,Config.MSG_OUT_200,objs,Config.RESP_MODE_10,"");
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

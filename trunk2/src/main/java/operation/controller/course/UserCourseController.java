package operation.controller.course;

import javax.servlet.http.HttpServletRequest;

import operation.BaseController;
import operation.exception.XueWenServiceException;
import operation.pojo.user.User;
import operation.service.course.UserCourseService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tools.Config;
import tools.ResponseContainer;

@RestController
@RequestMapping("/usercourse")
@Configuration
public class UserCourseController extends BaseController{
	private static final Logger logger=Logger.getLogger(UserCourseController.class);
	@Autowired
	public UserCourseService userCourseService;
	
	/**
	 * 将课程加入用户收藏列表
	 * @param id
	 * @param request
	 * @return
	 */
//	@RequestMapping("collect")
//	public @ResponseBody ResponseContainer addCollect( HttpServletRequest request) {
//
//		try {
//			
//			String courseId = request.getParameter("courseId");
//			String token = request.getParameter("token");
//			User currentUser = this.getCurrentUser(token);
//			userCourseService.addFav(courseId,currentUser.getId());
//			return addResponse(Config.STATUS_200, Config.MSG_200, true,Config.RESP_MODE_10, "");
//		} catch (XueWenServiceException e) {
//			e.printStackTrace();
//			logger.error("==========业务错误，根据群组ID查询群成员列表失败============"+e);
//			return addResponse(e.getCode(), e.getMessage(), false,
//					Config.RESP_MODE_10, "");
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("==========未知错误，根据群组ID查询群成员列表失败============"+e);
//			return addResponse(Config.STATUS_505, Config.MSG_505, false,
//					Config.RESP_MODE_10, "");
//		}
//	} 
	
	@RequestMapping("countUserCourse")
	public @ResponseBody ResponseContainer countUserCourse( HttpServletRequest request) {

		try {
			
			String token = request.getParameter("token");
			User currentUser = this.getCurrentUser(token);
			int i=userCourseService.userStudyCourseNum(currentUser.getId());
			return addResponse(Config.STATUS_200, Config.MSG_200, i,Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			e.printStackTrace();
			logger.error("==========业务错误，根据群组ID查询群成员列表失败============"+e);
			return addResponse(e.getCode(), e.getMessage(), false,
					Config.RESP_MODE_10, "");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("==========未知错误，根据群组ID查询群成员列表失败============"+e);
			return addResponse(Config.STATUS_505, Config.MSG_505, false,
					Config.RESP_MODE_10, "");
		}
	} 
	
	
	
	
	/**
	 * 将课程新增入用户学习列表
	 * @param id
	 * @param request
	 * @return
	 */
//	@RequestMapping("study")
//	public @ResponseBody ResponseContainer addStudy( HttpServletRequest request) {
//
//		try {
//			
//			String courseId = request.getParameter("courseId");
//			String token = request.getParameter("token");
//			User currentUser = this.getCurrentUser(token);
//			String timer = request.getParameter("timer");//学习时间点
//			String proess = request.getParameter("proess");//学习进度
//			String jsonStr = request.getParameter("timerProess");
//			
//			//userCourseService.addStudy(courseId,currentUser.getId(),timer,proess);
//			userCourseService.studyProcess(courseId,currentUser.getId(),jsonStr);
//			return addResponse(Config.STATUS_200, Config.MSG_200, true,Config.RESP_MODE_10, "");
//		} catch (XueWenServiceException e) {
//			e.printStackTrace();
//			logger.error("==========业务错误，根据群组ID查询群成员列表失败============"+e);
//			return addResponse(e.getCode(), e.getMessage(), false,
//					Config.RESP_MODE_10, "");
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("==========未知错误，根据群组ID查询群成员列表失败============"+e);
//			return addResponse(Config.STATUS_505, Config.MSG_505, false,
//					Config.RESP_MODE_10, "");
//		}
//	} 

}

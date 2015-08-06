/**   
* @Title: CourseFileController.java
* @Package operation.controller.cloudfile
* @Description: 
* @author yaoj
* @date 2014年12月17日 下午1:02:23
* @version V1.0
*/ 
package operation.controller.cloudfile;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import operation.BaseController;
import operation.exception.XueWenServiceException;
import operation.pojo.cloudfile.CourseFile;
import operation.pojo.common.ColudConfig;
import operation.service.cloudfile.CourseFileService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tools.Config;
import tools.ResponseContainer;

/** 
 * @ClassName: CourseFileController
 * @Description: 云存储课件Controller
 * @author yaoj
 * @date 2014年12月17日 下午1:02:23
 * 
 */
@RestController
@RequestMapping("/courseFile")
public class CourseFileController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(CourseFileController.class);
	
	@Autowired
	private CourseFileService courseFileService;

	/**
	 * 
	 * @Title: insertCourseFile
	 * @Description: 添加新的课件
	 * @param request
	 * @param courseFile
	 * @return ResponseContainer
	 * @throws
	 */
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public @ResponseBody ResponseContainer insertCourseFile(HttpServletRequest request,CourseFile courseFile){
		try {
			String fitems = request.getParameter("fitemJson");
			CourseFile file = courseFileService.insertCourseFile(courseFile, fitems);
			return addResponse(Config.STATUS_200, Config.MSG_200, file,Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			logger.error("==========业务错误，添加新的课件失败============" + e);
			return addResponse(e.getCode(), e.getMessage(), false, Config.RESP_MODE_10, "");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("==========未知错误，添加新的课件失败============" + e);
			return addResponse(Config.STATUS_505, Config.MSG_505, false, Config.RESP_MODE_10, "");
		}
	}
	
	/**
	 * 
	 * @Title: viewCourseFileById
	 * @Description: 根据id查看课件
	 * @param request
	 * @return ResponseContainer
	 * @throws
	 */
	@RequestMapping("/viewById")
	public @ResponseBody ResponseContainer viewCourseFileById(HttpServletRequest request){
		String id = request.getParameter("id");
		try {
			CourseFile file = courseFileService.viewCourseFileById(id);
			return addResponse(Config.STATUS_200, Config.MSG_200, file,Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			logger.error("==========业务错误，根据id查看课件失败============" + e);
			return addResponse(e.getCode(), e.getMessage(), false, Config.RESP_MODE_10, "");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("==========未知错误，根据id查看课件失败============" + e);
			return addResponse(Config.STATUS_505, Config.MSG_505, false, Config.RESP_MODE_10, "");
		}
	}
	
	/**
	 * 
	 * @Title: addCourseCountById
	 * @Description: 添加引用次数+1
	 * @param request
	 * @return ResponseContainer
	 * @throws
	 */
	@RequestMapping("/addCount")
	public @ResponseBody ResponseContainer addCourseCountById(HttpServletRequest request){
		String id = request.getParameter("id");
		try {
			courseFileService.addCourseCountById(id);
			return addResponse(Config.STATUS_200, Config.MSG_200, true,Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			logger.error("==========业务错误，课件添加引用次数失败============" + e);
			return addResponse(e.getCode(), e.getMessage(), false, Config.RESP_MODE_10, "");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("==========未知错误，根据id查看附件失败============" + e);
			return addResponse(Config.STATUS_505, Config.MSG_505, false, Config.RESP_MODE_10, "");
		}
	}
	
	/**
	 * 
	 * @Title: subCourseCountById
	 * @Description: 减少引用次数-1
	 * @param request
	 * @return ResponseContainer
	 * @throws
	 */
	@RequestMapping("/subCourse")
	public @ResponseBody ResponseContainer subCourseCountById(HttpServletRequest request){
		String id = request.getParameter("id");
		try {
			courseFileService.subCourseCountById(id);
			return addResponse(Config.STATUS_200, Config.MSG_200, true,Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			logger.error("==========业务错误，课件减少引用次数失败============" + e);
			return addResponse(e.getCode(), e.getMessage(), false, Config.RESP_MODE_10, "");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("==========未知错误，课件减少引用次数失败============" + e);
			return addResponse(Config.STATUS_505, Config.MSG_505, false, Config.RESP_MODE_10, "");
		}
	}

	@Autowired
	MongoTemplate mongoTemplate;
	@RequestMapping("/tt")
	private void tt() {
		ColudConfig coludConfig = new ColudConfig();
		List<String> list = new ArrayList<String>();
		list.add("http://ypublic.qiniudn.com/");
		list.add("http://7sbnvf.com2.z0.glb.qiniucdn.com/");
		list.add("http://7sbnvf.com2.z0.glb.clouddn.com/");
		coludConfig.setCkey("group_logo1");
		coludConfig.setBaseUrls(list);
		mongoTemplate.save(coludConfig);
	}
}

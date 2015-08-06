package operation.controller.oss;

import operation.BaseController;
import operation.exception.XueWenServiceException;
import operation.pojo.pub.QueryModel;
import operation.service.course.LessonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tools.Config;
import tools.PageRequestTools;
import tools.ResponseContainer;
@RestController
@RequestMapping("oss/lesson")
public class OssLessonController extends BaseController {
	@Autowired
	private LessonService lessonService;

	/**
	 * 
	 * @Title: checkLesson
	 * @author Tangli
	 * @Description: 课程审核75544544	 * @param lessonId
	 *            课时id
	 * @param status
	 *            审核结果
	 * @param checkDesc
	 *            审核结果
	 * @return ResponseContainer
	 * @throws
	 */
	@RequestMapping("checkLesson")
	@ResponseBody
	public ResponseContainer checkLesson(String lessonId, boolean status,
			String checkDesc,String  kngId) {
		try {
			lessonService.checkLesson(status, lessonId,checkDesc,kngId);
		} catch (XueWenServiceException e) {
			getReponseData().setResult(true);
		}
		return addResponse(Config.STATUS_200, Config.MSG_200, true,
				Config.RESP_MODE_10, "");
		
	}
//	@RequestMapping("updateAllLesson")
//	@ResponseBody
//	public ResponseContainer updateAllLesson(){
//		try {
//			lessonService.updateLesson();
//			return addResponse(Config.STATUS_200, Config.MSG_200, true,
//					Config.RESP_MODE_10, "");
//		} catch (XueWenServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return addResponse(Config.STATUS_505, Config.MSG_505, true,
//					Config.RESP_MODE_10, "");
//		}
//	}
	/**
	 * 更新所有课程的时长和大小
	 * @param dm
	 * @return
	 */
	@RequestMapping("updateLength")
	@ResponseBody
	public ResponseContainer updateLength(QueryModel dm){
			try {
				lessonService.updateAllLessonSizeAndTime();
				return addResponse(Config.STATUS_200, Config.MSG_200, true,
						Config.RESP_MODE_10, "");
			} catch (Exception e) {
				e.printStackTrace();
				return addResponse(Config.STATUS_201, Config.MSG_201, false,
						Config.RESP_MODE_10, "");
			}
	}
	
	
	
}

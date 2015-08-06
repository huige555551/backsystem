package operation.controller.course;

import operation.BaseController;
import operation.exception.XueWenServiceException;
import operation.service.course.LessonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tools.Config;
import tools.ResponseContainer;

@Controller
@RequestMapping("lesson")
public class LessonController extends BaseController {
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
	public ResponseContainer checkLesson(String lessonId,
			String checkDesc,String  kngId,int status) {
		boolean s=status==1?true:false;
		try {
			lessonService.checkLesson(s, lessonId,checkDesc,kngId);
		} catch (XueWenServiceException e) {
			getReponseData().setResult(true);
		}
		return addResponse(Config.STATUS_200, Config.MSG_200, getReponseData(),
				Config.RESP_MODE_10, "");
		
	}
}

/**   
* @Title: AttachFileController.java
* @Package operation.controller.cloudfile
* @Description: 
* @author yaoj
* @date 2014年12月17日 下午1:31:34
* @version V1.0
*/ 
package operation.controller.cloudfile;

import javax.servlet.http.HttpServletRequest;

import operation.BaseController;
import operation.exception.XueWenServiceException;
import operation.pojo.cloudfile.AttachFile;
import operation.service.cloudfile.AttachFileService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tools.Config;
import tools.ResponseContainer;

/** 
 * @ClassName: AttachFileController
 * @Description: 云存储附件Controller
 * @author yaoj
 * @date 2014年12月17日 下午1:31:34
 * 
 */
@RestController
@RequestMapping("/attachFile")
public class AttachFileController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(AttachFileController.class);
	
	@Autowired
	private AttachFileService attachFileService;
	
	/**
	 * 
	 * @Title: insertAttachFile
	 * @Description: 添加新的附件
	 * @param request
	 * @param attachFile
	 * @return ResponseContainer
	 * @throws
	 */
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public @ResponseBody ResponseContainer insertAttachFile(HttpServletRequest request,AttachFile attachFile){
		try {
			AttachFile file = attachFileService.insertAttachFile(attachFile);
			return addResponse(Config.STATUS_200, Config.MSG_200, file,Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			logger.error("==========业务错误，添加新的附件失败============" + e);
			return addResponse(e.getCode(), e.getMessage(), false, Config.RESP_MODE_10, "");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("==========未知错误，添加新的附件失败============" + e);
			return addResponse(Config.STATUS_505, Config.MSG_505, false, Config.RESP_MODE_10, "");
		}
	}
	
	/**
	 * 
	 * @Title: viewAttachFileById
	 * @Description: 根据id查看附件
	 * @param request
	 * @return ResponseContainer
	 * @throws
	 */
	@RequestMapping("/viewById")
	public @ResponseBody ResponseContainer viewAttachFileById(HttpServletRequest request){
		String id = request.getParameter("id");
		try {
			AttachFile file = attachFileService.viewAttachFileById(id);
			return addResponse(Config.STATUS_200, Config.MSG_200, file,Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			logger.error("==========业务错误，根据id查看附件失败============" + e);
			return addResponse(e.getCode(), e.getMessage(), false, Config.RESP_MODE_10, "");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("==========未知错误，根据id查看附件失败============" + e);
			return addResponse(Config.STATUS_505, Config.MSG_505, false, Config.RESP_MODE_10, "");
		}
	}
	
	/**
	 * 
	 * @Title: addAttachCountById
	 * @Description: 添加引用次数+1
	 * @param request
	 * @return ResponseContainer
	 * @throws
	 */
	@RequestMapping("/addCount")
	public @ResponseBody ResponseContainer addAttachCountById(HttpServletRequest request){
		String id = request.getParameter("id");
		try {
			attachFileService.addAttachCountById(id);
			return addResponse(Config.STATUS_200, Config.MSG_200, true,Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			logger.error("==========业务错误，附件添加引用次数失败============" + e);
			return addResponse(e.getCode(), e.getMessage(), false, Config.RESP_MODE_10, "");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("==========未知错误，根据id查看附件失败============" + e);
			return addResponse(Config.STATUS_505, Config.MSG_505, false, Config.RESP_MODE_10, "");
		}
	}
	
	/**
	 * 
	 * @Title: subAttachCountById
	 * @Description: 减少引用次数-1
	 * @param request
	 * @return ResponseContainer
	 * @throws
	 */
	@RequestMapping("/subCount")
	public @ResponseBody ResponseContainer subAttachCountById(HttpServletRequest request){
		String id = request.getParameter("id");
		try {
			attachFileService.subAttachCountById(id);
			return addResponse(Config.STATUS_200, Config.MSG_200, true,Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			logger.error("==========业务错误，附件减少引用次数失败============" + e);
			return addResponse(e.getCode(), e.getMessage(), false, Config.RESP_MODE_10, "");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("==========未知错误，附件减少引用次数失败============" + e);
			return addResponse(Config.STATUS_505, Config.MSG_505, false, Config.RESP_MODE_10, "");
		}
	}

}

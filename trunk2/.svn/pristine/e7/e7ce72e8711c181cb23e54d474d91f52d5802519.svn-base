package operation.controller.oss;

import javax.servlet.http.HttpServletRequest;

import operation.BaseController;
import operation.controller.cloudfile.AttachFileController;
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
@RestController
@RequestMapping("/oss/attachFile")
public class OssAttachFileController extends BaseController{
	@Autowired
	private AttachFileService attachFileService;
	private static final Logger logger = Logger.getLogger(OssAttachFileController.class);
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

}

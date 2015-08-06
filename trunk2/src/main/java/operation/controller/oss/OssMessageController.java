package operation.controller.oss;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import operation.OssController;
import operation.exception.XueWenServiceException;
import operation.service.drycargo.DrycargoService;
import operation.service.push.ZtiaoPushService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tools.Config;
import tools.ResponseContainer;

@RestController
@RequestMapping("/oss/message")
@Configuration
public class OssMessageController extends OssController{

	@Autowired
	public ZtiaoPushService ztiaoPushService;

	/**
	 * 发送系统消息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/sendMessage")
	public @ResponseBody ResponseContainer sendMessage(HttpServletRequest request) throws XueWenServiceException {
		try {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			ztiaoPushService.sendNormal(title, content);
			 
			return addResponse(Config.STATUS_200, Config.MSG_200, true, Config.RESP_MODE_10, "");
		} catch (Exception e) {
			e.printStackTrace();
			return addResponse(Config.STATUS_505, Config.MSG_505, false, Config.RESP_MODE_10, "");
		}
	}

}

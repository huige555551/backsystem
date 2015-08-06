package operation.controller.jpush;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import operation.BaseController;
import operation.exception.XueWenServiceException;
import operation.pojo.user.User;
import operation.pojo.user.UserMessage;
import operation.service.jpush.JPushService;
import operation.service.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tools.Config;
import tools.ResponseContainer;

@RestController
@RequestMapping("/push")
public class JPushController extends BaseController{

	@Autowired
	public JPushService jpushService;
	
	@Autowired
	public UserService userService;
	
	
	/**
	 * 用户退出
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("loginOut")
	public @ResponseBody ResponseContainer loginOut(HttpServletRequest request) {
		try {
			String msg = request.getParameter("msg");
//			User currentUser = this.getCurrentUser(token);
//			if(null !=lat && null != lng){
//				double [] position = new double[]{Double.parseDouble(lng),Double.parseDouble(lat)};
				
//				currentUser.setLng(Double.parseDouble(lng));
//				currentUser.setLat(Double.parseDouble(lat));
				
//				currentUser.setPoint(position);
//				}
//			userService.loginOut(currentUser);
			List<String> alias=new ArrayList<String>();
			List<User> users=userService.findAllUsers();
			for(User user:users){
				alias.add(user.getId());
				UserMessage userMessage = new UserMessage();
				userMessage.setUserId(user.getId());
//				userMessage.setContext(msg);
//				userMessage.setIsRead("0"); //是否可读 0 未读 1 已读
//				userMessage.setStime(System.currentTimeMillis());
//				userMessage.setType("0"); //推送消息类型
				userService.saveUserMessage(userMessage);
			}
			
			Map< String, String> extras =new HashMap<String, String>();
			extras.put("1", "1001");
//			jpushService.sendNoticeAndMsg(alias,msg, msg, extras);
			return addResponse(Config.STATUS_200,Config.MSG_OUT_200,true,Config.RESP_MODE_10,"");
		} 
		catch (XueWenServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return addResponse(e.getCode(),e.getMessage(),false,Config.RESP_MODE_10,"");
		} catch (Exception e){
			e.printStackTrace();
			return addResponse(Config.STATUS_505,Config.MSG_505,false,Config.RESP_MODE_10,"");
		}
	}
	
}

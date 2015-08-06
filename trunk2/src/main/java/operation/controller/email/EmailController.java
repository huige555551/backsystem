package operation.controller.email;
import java.io.IOException;
import java.util.List;

import operation.BaseController;
import operation.exception.XueWenServiceException;
import operation.pojo.email.MailTempter;
import operation.pojo.email.YxtRegMail;
import operation.pojo.user.User;
import operation.service.email.EmailService;
import operation.service.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tools.Config;
import tools.ResponseContainer;

@Controller
@RequestMapping("/email")
public class EmailController extends BaseController {
	@Autowired
	private EmailService emailService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("sendRefEamil")
	@ResponseBody
	public ResponseContainer send(YxtRegMail yxtRegMail,String addresses){
		try {
			emailService.sendMails(yxtRegMail,addresses);
			return addResponse(Config.STATUS_200, Config.MSG_200, true, Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			return addResponse(Config.STATUS_500, Config.MSG_500, false, Config.RESP_MODE_10, "");
		} catch (IOException e) {
			return addResponse(Config.STATUS_500, Config.MSG_500, false, Config.RESP_MODE_10, "");
		}
	}
	@RequestMapping("addMail")
	@ResponseBody
	public ResponseContainer add(String name,String content){
		try {
			emailService.add(content, name);
			return addResponse(Config.STATUS_200, Config.MSG_200, true, Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			return addResponse(Config.STATUS_200, Config.MSG_200, true, Config.RESP_MODE_10, "");
		}
		
	}
	
	@RequestMapping("modify")
	@ResponseBody
	public ResponseContainer addtem(String id,String content){           
		try {
			emailService.modify(id, content);
			return addResponse(Config.STATUS_200, Config.MSG_200, true, Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			return addResponse(Config.STATUS_200, Config.MSG_200, true, Config.RESP_MODE_10, "");
		}
		
	}
	
	@RequestMapping("getByName")
	@ResponseBody
	public ResponseContainer getByName(String name) {
		MailTempter temp = emailService.getTemppterByName(name);
		return addResponse(Config.STATUS_200, Config.MSG_200, temp,
				Config.RESP_MODE_10, "");

	}
	
	@RequestMapping("getAll")
	@ResponseBody
	public ResponseContainer getAll() {
		List<MailTempter> temp = emailService.getAll();
		return addResponse(Config.STATUS_200, Config.MSG_200, temp,
				Config.RESP_MODE_10, "");

	}
	
	/**
	 * 
	 * @auther tangli
	 * @Description: 发送邮件验证码
	 * @param email  邮件地址
	 * @param type   0代表修改密码 1代表绑定邮箱
	 * @Date:2015年4月20日
	 * @return ResponseContainer
	 * @throws
	 */
	@RequestMapping("sendMailCode")
	@ResponseBody
	public ResponseContainer add(String email,int type){
			emailService.sendMailCode(email,type);
			User user=null;
			try {
				user=userService.getOneByPhoneOrEmail(email);
			} catch (XueWenServiceException e) {
				e.printStackTrace();
			}
			return addResponse(Config.STATUS_200, Config.MSG_200, user, Config.RESP_MODE_10, "");	
	}
	
	
	
}

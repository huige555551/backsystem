package operation.service.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import operation.exception.XueWenServiceException;
import operation.pojo.user.MessageContext;
import operation.pojo.user.User;
import operation.pojo.user.UserMessage;
import operation.repo.user.UserMessageRepository;
import operation.service.jpush.JPushService;
import operation.service.jpush1.JPushService1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import tools.Config;
import tools.StringUtil;



@Service
@Component
public class UserMessageService {

	@Autowired
	private UserMessageRepository userMessageRepository;
	@Autowired
	private JPushService jpushService;
	@Autowired
	private JPushService1 jpushService1;
	public UserMessageService(){
		super();
	}
	
	/**
	 * 极光推送给 toUserId 用户消息 ：user 请求添加 目标用户好友
	 * @param user 请求人
	 * @param toUserId 目标用户
	 * @throws XueWenServiceException
	 */
	public void applyFriendMsgSend(User user,String toUserId)throws XueWenServiceException{
		String notice="用户"+user.getNickName()+"申请加您为好友";
	    MessageContext context=new MessageContext();
	    context.setUserId(user.getId());
	    context.setNikeName(user.getNickName());
	    context.setLogoURL(user.getLogoURL());
	    context.setContext(notice);
	    UserMessage userMessage = new UserMessage();
	    userMessage.setUserId(toUserId);
	    userMessage.setContext(context);
	    userMessage.setIsOpertison("0");
	    userMessage.setIsRead("0"); //是否可读 0 未读 1 已读
	    userMessage.setStime(System.currentTimeMillis());
	    userMessage.setType("2000"); //推送消息类型
	    saveUserMessage(userMessage);
	    JSONObject jo=JSONObject.fromObject(context);
	    String msg=jo.toString();
	    //通知给被添加人
	    List<String> alias=new ArrayList<String>();
		alias.add(toUserId);
		Map<String,String> extras =new HashMap<String, String>();
		extras.put("type","2000");
	    jpushService.sendNoticeAndMsg(alias, notice, notice, extras);
	    jpushService1.sendNoticeAndMsg(alias, notice, notice, extras);
	}

	/**
	 * 向目标用户推送成功成为好友消息
	 * @param user
	 * @param toUserId
	 * @throws XueWenServiceException
	 */
	public void beFriendMsgSend(User user,String toUserId)throws XueWenServiceException{
		String notice="您与"+user.getNickName()+"成为好友";
		MessageContext context=new MessageContext();
		context.setUserId(user.getId());
		context.setNikeName(user.getNickName());
		context.setLogoURL(user.getLogoURL());
		context.setContext(notice);
		UserMessage userMessage = new UserMessage();
		userMessage.setUserId(toUserId);
		userMessage.setContext(context);
		userMessage.setIsOpertison("0");
		userMessage.setIsRead("0"); //是否可读 0 未读 1 已读
		userMessage.setStime(System.currentTimeMillis());
		userMessage.setType("2001"); //推送消息类型
		saveUserMessage(userMessage);
		JSONObject jo=JSONObject.fromObject(context);
		String msg=jo.toString();
		//通知给被添加人
		List<String> alias=new ArrayList<String>();
		alias.add(toUserId);
		Map<String,String> extras =new HashMap<String, String>();
		extras.put("type","2000");
		jpushService.sendNoticeAndMsg(alias, notice, notice, extras);
		jpushService1.sendNoticeAndMsg(alias, notice, notice, extras);
	}
	
	
	/**
	 * 保存用户推送消息列表
	 * @author hjn
	 * @param userMessage
	 * @return
	 * @throws XueWenServiceException
	 */
	public void saveUserMessage(UserMessage userMessage)throws XueWenServiceException {
		userMessageRepository.save(userMessage);
	}
	
}

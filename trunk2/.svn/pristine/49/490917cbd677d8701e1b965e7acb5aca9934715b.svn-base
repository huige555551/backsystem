package operation.service.user;

import operation.exception.XueWenServiceException;
import operation.pojo.group.XueWenGroup;
import operation.pojo.user.User;
import operation.pojo.user.UserInvite;
import operation.repo.user.UserInviteRepository;
import operation.service.group.GroupService;
import operation.service.sms.SmsService;
import operation.service.version.VersionService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import tools.Config;
import tools.StringUtil;

@Service
@Component
public class UserInviteService {
	private static final Logger logger=Logger.getLogger(UserInviteService.class);
	
	@Autowired
	private UserInviteRepository userInviteRepository;
	@Autowired
	private SmsService smsService;
	@Autowired
	private VersionService versionService;
	@Autowired
	private GroupService groupService;
	@Autowired
	private UserService userService;
	
	//软件下载地址
	@Value("${app.download.url}")
	private String appDownLoadUrl;
	
	
	public UserInviteService(){
		
	}
	/**
	 * 将非注册用户写入到邀请对象中
	 * @param userId
	 * @param phoneNumber
	 * @param name
	 * @param taget
	 * @throws XueWenServiceException
	 */
	public void saveUserInvite(User user,String phoneNumber,String name,String target) throws XueWenServiceException{
		if(StringUtil.isEmpty(phoneNumber) || !StringUtil.isMobileNO(phoneNumber)){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_NOTMOBILE_201,null);
		}
		//给非注册用户发短信，需要带下载地址
		UserInvite ui = new UserInvite();
		String context="";
		if(StringUtil.isBlank(target)){
			ui.setType("1");//邀请注册APP
			context="您被"+user.getUserName()+"("+user.getNickName()+")"+"邀请使用小纸条APP,请先安装,下载地址为"+appDownLoadUrl;
		}else{
			XueWenGroup group=groupService.findOneXuewenGroupOnlyGroupNameAndGroupNum(target);
			if(group == null){
				ui.setType("1");//邀请注册APP
				context="您被"+user.getUserName()+"("+user.getNickName()+")"+"邀请使用小纸条APP,请先安装,下载地址为"+appDownLoadUrl;
			}else{
				ui.setType("0");//邀请注册APP并加入群组
				ui.setTarget(target); //此处target为群ID
				context="您被"+user.getUserName()+"("+user.getNickName()+")"+"邀请使用小纸条APP,并加入"+group.getGroupName()+"("+group.getGroupNumber()+")群组"+",请先安装,下载地址为"+appDownLoadUrl;
			}
			
		}
		smsService.sendSms(phoneNumber,"3",context, user);
		ui.setName(name);
		ui.setPhoneNumber(phoneNumber);
		ui.setUserId(user.getId());
		ui.setOperation("0"); //0未操作
		userInviteRepository.save(ui);
	}
	
	/**
	 * 用户注册时查询是否是邀请客户
	 * @param phoneNumber
	 * @param operation
	 * @return
	 * @throws XueWenServiceException
	 */
	public UserInvite findUserInvite(String phoneNumber,String operation) throws XueWenServiceException{
		return userInviteRepository.findByPhoneNumberAndOperation(phoneNumber, operation);
	}
	
	/**
	 * 邀请入群后，修改操作字段为已操作
	 * @param ui
	 * @throws XueWenServiceException
	 */
	public void saveUserInvite(UserInvite ui) throws XueWenServiceException{
		userInviteRepository.save(ui);
	}
}

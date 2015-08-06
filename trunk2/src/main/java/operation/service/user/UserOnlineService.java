package operation.service.user;

import java.util.List;

import operation.exception.XueWenServiceException;
import operation.pojo.user.User;
import operation.service.redis.OnlineUserRedisService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import tools.StringUtil;

@Service
@Component
public class UserOnlineService{
	private static final Logger logger=Logger.getLogger(UserOnlineService.class);

	@Autowired
	public UserService userService;
	@Autowired
	private OnlineUserRedisService onlineUserRedisService;
	
	public void initValidUser()throws XueWenServiceException{
			List<User> onlineUser = userService.findAllUsers();

		for (int i = 0; i < onlineUser.size(); i++) {
			long sysNow=System.currentTimeMillis();
			logger.info("系统当前时间："+sysNow+"====用户登录保持超时时间："+onlineUser.get(i).getExpireTime());
			if (sysNow < onlineUser.get(i).getExpireTime()) {
				logger.info("初始化的合法用户======用户ID："+onlineUser.get(i).getId()+"=======用户名："+onlineUser.get(i).getUserName()+"===用户token:"+onlineUser.get(i).getToken());
				try {
					if(!StringUtil.isBlank(onlineUser.get(i).getToken()) && !onlineUserRedisService.onlineUserExise(onlineUser.get(i).getToken())){
						onlineUserRedisService.addOnlineUser(onlineUser.get(i).getToken(), onlineUser.get(i));
					}
				} catch (XueWenServiceException e) {
					e.printStackTrace();
					logger.error("添加redis在线用户错误，token:"+onlineUser.get(i).getToken());
				}
			}else{
				try {
					//过期账户redis删
					if(!StringUtil.isBlank(onlineUser.get(i).getToken())){
						onlineUserRedisService.removeOnlineUser(onlineUser.get(i).getToken());
					}
					
				} catch (Exception e) {
					logger.error("删除redis在线用户错误，token:"+onlineUser.get(i).getToken()  +e );
				}
			}
		}
	}

}

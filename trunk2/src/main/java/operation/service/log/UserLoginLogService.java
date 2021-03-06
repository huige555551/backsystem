package operation.service.log;

import operation.pojo.log.UserLoginLog;
import operation.repo.log.UserLoginLogRepository;
import operation.service.user.UserService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class UserLoginLogService {
	
	private static final Logger logger=Logger.getLogger(UserService.class);
	@Autowired
	public UserLoginLogRepository userLoginLogRepository;
	
	public UserLoginLogService(){
		
	}
	
	public void saveUserLoginLog(UserLoginLog log){
		userLoginLogRepository.save(log);
	}

	public UserLoginLog findOneByUserId(String userId) {
		// TODO Auto-generated method stub
		return userLoginLogRepository.findOneByUserId(userId);
	}

}

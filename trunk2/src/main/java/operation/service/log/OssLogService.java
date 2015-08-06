package operation.service.log;

import java.util.List;

import operation.pojo.log.OssLog;
import operation.pojo.log.UserLoginLog;
import operation.repo.log.OssLogRepository;
import operation.repo.log.UserLoginLogRepository;
import operation.service.user.UserService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class OssLogService {
	
	private static final Logger logger=Logger.getLogger(UserService.class);
	@Autowired
	public OssLogRepository ossLogRepository;
	
	public OssLogService(){
		
	}
	
	public List<OssLog> getall(){
		return ossLogRepository.findAll();
	}

}

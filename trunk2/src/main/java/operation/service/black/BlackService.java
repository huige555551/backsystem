package operation.service.black;

import operation.exception.XueWenServiceException;
import operation.pojo.black.Black;
import operation.pojo.user.User;
import operation.repo.black.BlackRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class BlackService {

	@Autowired
	private BlackRepository blackRepository;

	public BlackService() {

	}
	/**
	 * 将该用户写入到黑名单中
	 * @param user
	 * @param content
	 * @throws XueWenServiceException
	 */
	public void saveBlack(User user,String content) throws XueWenServiceException {
		Black black = blackRepository.findByUser(user.getUserName());
		if(black==null){
			long time = System.currentTimeMillis();
			black = new Black();
			black.setUser(user.getUserName());
			black.setContent(content);
			black.setCtime(time);
			blackRepository.save(black);
		}
		
	}
	/**
	 * 查询黑名单用户
	 * @param user
	 * @return
	 * @throws XueWenServiceException
	 */
	public Black getBlackByUser(String user) throws XueWenServiceException {
		return blackRepository.findByUser(user);
	}

}

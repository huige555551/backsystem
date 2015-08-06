package operation.service.user;



import java.util.List;

import operation.pojo.user.UserName;
import operation.repo.user.UserNameRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Service
@Component
public class UserNameService {
	@Autowired
	private UserNameRepository userNameRepository;
	
	public void save(UserName userName){
		userNameRepository.save(userName);
	}
	
	public List<UserName> getUserName(){
		return userNameRepository.findAll();
	}

}

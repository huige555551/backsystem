package operation.service.user;



import operation.pojo.user.NewUserNickName;
import operation.repo.user.NewUserNickNameRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Service
@Component
public class NewUserNameService {
	@Autowired
	private NewUserNickNameRepository newUserNickNameRepository;
	
//	public void save(NewUserName userName){
//		newUserNameRepository.save(userName);
//	}
//	
//	public List<NewUserName> getUserName(){
//		return newUserNameRepository.findAll();
//	}
	public NewUserNickName getNickName(int number){
		return newUserNickNameRepository.findByNumber(number);
	}
	
	public long countNickName(){
		return newUserNickNameRepository.count();
	}
}

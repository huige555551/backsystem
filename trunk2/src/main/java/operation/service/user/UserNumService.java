package operation.service.user;

import operation.exception.XueWenServiceException;
import operation.pojo.user.UserNumber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
@Component
public class UserNumService {


//	private MongoTemplate mongoTemplate;
	@Autowired
	MongoTemplate mongoTemplate;  
	  
    public String getGroupNum()throws XueWenServiceException {  
        Query query=new Query();  
        UserNumber test=mongoTemplate.findAndRemove(query, UserNumber.class); 
        return test.getUserNumber();  
    }  
	public UserNumService(){
		
	}
	
	
	public static String getUserNumber(){
		return null;
	}
	
	public void setUserNumber(int begin,int end)throws XueWenServiceException{
		for(int i=begin;i<end;i++){
			UserNumber g=new UserNumber();
			g.setUserNumber(String.valueOf(i));
			mongoTemplate.save(g);
		}
	}
	

}

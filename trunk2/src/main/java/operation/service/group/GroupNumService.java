package operation.service.group;

import operation.exception.XueWenServiceException;
import operation.pojo.group.GroupNumber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
@Component
public class GroupNumService {


//	private MongoTemplate mongoTemplate;
	@Autowired
	MongoTemplate mongoTemplate;  
	  
    public String getGroupNum()throws XueWenServiceException {  
        Query query=new Query();  
        GroupNumber test=mongoTemplate.findAndRemove(query, GroupNumber.class); 
        return test.getGroupNumber();  
    }  
	public GroupNumService(){
		
	}
	
	
	public static String getGroupNumber(){
		return null;
	}
	
	public void setGroupNumber(int begin,int end)throws XueWenServiceException{
		for(int i=begin;i<end;i++){
			GroupNumber g=new GroupNumber();
			g.setGroupNumber(String.valueOf(i));
			mongoTemplate.save(g);
		}
	}
	

}

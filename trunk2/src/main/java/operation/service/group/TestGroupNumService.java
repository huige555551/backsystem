package operation.service.group;

import operation.exception.XueWenServiceException;
import operation.pojo.group.TestGroupNumber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
@Component
public class TestGroupNumService {


//	private MongoTemplate mongoTemplate;
	@Autowired
	MongoTemplate mongoTemplate;  
	  
    public String getGroupNum()throws XueWenServiceException {  
        Query query=new Query();  
        TestGroupNumber test=mongoTemplate.findAndRemove(query, TestGroupNumber.class); 
        return test.getGroupNumber();  
    }  
	public TestGroupNumService(){
		
	}
	
	
	public static String getGroupNumber(){
		return null;
	}
	
	public void setGroupNumber(int begin,int end)throws XueWenServiceException{
		for(int i=begin;i<end;i++){
			TestGroupNumber g=new TestGroupNumber();
			g.setGroupNumber(String.valueOf(i));
			mongoTemplate.save(g);
		}
	}
	

}

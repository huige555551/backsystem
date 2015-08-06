package operation.service.subject;

import java.util.ArrayList;
import java.util.List;

import operation.exception.XueWenServiceException;
import operation.pojo.like.LikeOrUnlikePojo;
import operation.pojo.user.User;
import operation.repo.like.LikeRepository;
import operation.service.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
@Component
public class LikeOrUnlikeService {
	@Autowired
	public UserService userService;
	
	@Autowired
	public LikeRepository likeRepository;
	
	public LikeOrUnlikeService(){
		super();
	}
	
	
	
	public LikeOrUnlikePojo like(String type,String typeId, String userId){
		LikeOrUnlikePojo lp = this.one(type, typeId);
		lp.addWhoLiked(userId);
		lp.setUtime(System.currentTimeMillis());
		return likeRepository.save(lp);
	}
	
	public LikeOrUnlikePojo unlike(String type,String typeId, String userId){
		LikeOrUnlikePojo lp = this.one(type, typeId);
		lp.addWhoUnLiked(userId);
		lp.setUtime(System.currentTimeMillis());
		return likeRepository.save(lp);
		
	}
	
	public LikeOrUnlikePojo getAll(String type,String typeId){
		return this.one(type, typeId);		
	}
	public List<User> getWhoLiked(String type,String typeId){
		LikeOrUnlikePojo lp = this.one(type, typeId);
		
		return this.getUserObjectByUserList(lp.getWhoLiked());
	}
	public List<User> getWhoUnLiked(String type,String typeId){
		LikeOrUnlikePojo lp = this.one(type, typeId);
		
		return this.getUserObjectByUserList(lp.getWhoUnLiked());
	}
	
	
	public List<User> getUserObjectByUserList(List<Object> userList){
		List<User> resUserList = new ArrayList<User>();
		
		for(int i=0;i<userList.size();i++){
			try {
				resUserList.add(userService.findUser(userList.get(i).toString()));
			} catch (XueWenServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resUserList;
	}
	
	private LikeOrUnlikePojo one(String type,String typeId){
		LikeOrUnlikePojo lp = likeRepository.findByTypeAndTypeId(type, typeId);
		if( lp == null){
			return this.createLikeOrUnlikePojoo(type, typeId);
		}else{
			return lp;
		}
	}
	
	private LikeOrUnlikePojo createLikeOrUnlikePojoo(String type,String typeId){
		LikeOrUnlikePojo lp = new LikeOrUnlikePojo();
		lp.setTypeId(typeId);
		lp.setType(type);
		
		
		return lp;
	}
	
	
}

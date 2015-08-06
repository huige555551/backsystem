package operation.service.user;

import java.util.List;

import operation.exception.XueWenServiceException;
import operation.pojo.user.ResponseUser;
import operation.pojo.user.ResponseUserFriendShip;
import operation.pojo.user.User;
import operation.pojo.user.UserFriendShip;
import operation.repo.user.UserFriendShipRepository;
import operation.repo.user.UserRepository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import tools.Config;

@Service
@Component
public class UserFriendShipService {

	private static final Logger logger=Logger.getLogger(UserFriendShipService.class);
	
	@Autowired
	public UserFriendShipRepository userFriendShipRepository;
	
	@Autowired
	public UserContactListService userContactListService;
	
	@Autowired
	public UserService userService;
	
	public UserFriendShipService() {
		super();
	}
	
	/**
	 * 得到用户联系人
	 * @param user
	 * @return
	 * @throws XueWenServiceException
	 */
	public UserFriendShip getUserFriendShip(User user)throws XueWenServiceException{
		UserFriendShip ufs=userFriendShipRepository.findOneByUser(user.getId());
		if(ufs==null){
			ufs=new UserFriendShip();
			ufs.setUser(user.getId());
			ufs.setWhoKonwnMe(userContactListService.whoKnownMe(user));
			ufs.setiKnownWho(userContactListService.iKnownWho(user));
			ufs.setBothKnown(userContactListService.bothKnown(ufs.getiKnownWho(), ufs.getWhoKonwnMe()));
			ufs=userFriendShipRepository.save(ufs);
		}else{
			//更新
			ufs=updateUserFriendShip(ufs,user);
		}
		return ufs;
	}
	
	/**
	 * 更新用户联系人信息
	 * @param userFriendShip
	 * @param user
	 * @return
	 * @throws XueWenServiceException
	 */
	public UserFriendShip updateUserFriendShip(UserFriendShip userFriendShip,User user)throws XueWenServiceException{
		if(userFriendShip == null || user ==null ){
			throw new XueWenServiceException(Config.STATUS_201,Config.MSG_201,null);
		}else{
			List<Object> whoKonwnMe=userFriendShip.getWhoKonwnMe();
			List<Object> newWhoKonwnMe=userContactListService.whoKnownMe(user);
			userFriendShip.setWhoKonwnMe(getNoRepeatListAll(whoKonwnMe,newWhoKonwnMe));
			List<Object> iKnownWho=userFriendShip.getiKnownWho();
			List<Object> newIKnownWho=userContactListService.iKnownWho(user);
			userFriendShip.setiKnownWho(getNoRepeatListAll(iKnownWho,newIKnownWho));
			List<Object> bothKnown=userFriendShip.getBothKnown();
			List<Object> newbothKnown=userContactListService.bothKnown(userFriendShip.getiKnownWho(), userFriendShip.getWhoKonwnMe());
			userFriendShip.setBothKnown(getNoRepeatListAll(bothKnown,newbothKnown));
			userFriendShip.setUtime();
			return userFriendShipRepository.save(userFriendShip);
		}
	}
	
	private List<Object> getNoRepeatListAll(List<Object> list1,List<Object> list2)throws XueWenServiceException{
		if(list1== null){
			return list2;
		}else if(list2 ==null){
			return list1;
		}else{
			list1.removeAll(list2);
			list2.addAll(list1);
			return list2;
		}
	}
	
	/**
	 * 得到用户的ResponseUserFriendShip
	 * @param user
	 * @return
	 * @throws XueWenServiceException
	 */
	public ResponseUserFriendShip getResponseUserFriendShip(User user)throws XueWenServiceException{
		return toResponseUserFriendShip(getUserFriendShip(user));
	}
	
	/**
	 * 将一个userFriendShip转为ResponseUserFriendShip
	 * @param userFriendShip
	 * @return
	 * @throws XueWenServiceException
	 */
	public ResponseUserFriendShip toResponseUserFriendShip(UserFriendShip userFriendShip)throws XueWenServiceException{
		if(userFriendShip==null){
			throw new XueWenServiceException(Config.STATUS_201,Config.MSG_201,null);
		}
		ResponseUserFriendShip rufs=new ResponseUserFriendShip();
		rufs.setId(userFriendShip.getId());
		rufs.setUser(userService.toResponseUser(userFriendShip.getUser().toString()));
		if(userFriendShip.getiKnownWho() !=null || userFriendShip.getiKnownWho().size()>0){
			rufs.setiKnownWho(userService.toResponseUserList(userFriendShip.getiKnownWho()));
		}
		if(userFriendShip.getWhoKonwnMe() !=null || userFriendShip.getWhoKonwnMe().size()>0){
			rufs.setWhoKonwnMe(userService.toResponseUserList(userFriendShip.getWhoKonwnMe()));
		}
		if(userFriendShip.getBothKnown() !=null || userFriendShip.getBothKnown().size()>0){
			rufs.setBothKnown(userService.toResponseUserList(userFriendShip.getBothKnown()));
		}
		rufs.setCtime(userFriendShip.getCtime());
		rufs.setUtime(userFriendShip.getUtime());
		return rufs;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	
}

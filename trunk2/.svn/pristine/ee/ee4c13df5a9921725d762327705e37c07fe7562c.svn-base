package operation.service.drycargo;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import operation.exception.XueWenServiceException;
import operation.pojo.drycargo.Drycargo;
import operation.pojo.drycargo.UserDrycargoBean;
import operation.repo.drycargo.UserDrycargoRepository;
import operation.service.category.CategoryService;
import operation.service.fav.FavService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import tools.Config;
import tools.StringUtil;


@Service
@Component
public class UserDrycargoService {
	private static final Logger logger = Logger.getLogger(UserDrycargoService.class);
	@Autowired
	public UserDrycargoRepository userDrycargoRepository;
	@Autowired
	public DrycargoService drycargoService;
	
	@Autowired
	public CategoryService categoryService;
	
	@Autowired
	public FavService favService;
	
	public UserDrycargoService(){
		super();
	}
	
	/**
	 * 将课程加入用户收藏列表
	 * @param courseId
	 * @param userId
	 * @throws XueWenServiceException
	 */
	public void addFavByDryFlag(String id,String userId,String fromGroupId,String appkey,int dryFlag)throws XueWenServiceException{
		
		UserDrycargoBean udb=userDrycargoRepository.findByUserAndDryCargoBean(userId, id);
		Drycargo dc = drycargoService.findOneById(id);
		if(udb == null){
			udb=new UserDrycargoBean(userId,id);
			
			drycargoService.countOperationFav(userId, dc.getId().toString(),appkey,dryFlag);
		}else{
			if(udb.getIsFav()==0){
				drycargoService.countOperationFav(userId, dc.getId().toString(),appkey,dryFlag);
			}
			else{
//				if(StringUtil.isBlank(fromGroupId)){
				throw new XueWenServiceException(Config.STATUS_201,
						Config.MSG_FAVDAY_201, null);
//				}
			}
			udb.setUtime(System.currentTimeMillis());
		}
		udb.setIsFav(1);
		udb.setDryFlag(dryFlag);
		userDrycargoRepository.save(udb);
		
	}
	
	/**
	 * 查询我的干货收藏
	 * @param courseId
	 * @param userId
	 * @throws XueWenServiceException
	 */
	public Page<UserDrycargoBean> findDryCollect(String userId,int dryFlag,Pageable page)throws XueWenServiceException{
		Page<UserDrycargoBean> udb = userDrycargoRepository.findByUserAndIsFavAndDryFlag(userId, 1,dryFlag,page);
		if(udb != null && udb.getTotalElements() > 0){
			if(udb.getTotalElements() > 0){
				List<UserDrycargoBean> userDrycargoBean = udb.getContent();
				UserDrycargoBean one = null;
				if(userDrycargoBean != null){
					for(int i=0 ;i< userDrycargoBean.size(); i++){
						one = userDrycargoBean.get(i);
						String dryId = (String)one.getDryCargoBean();
						Drycargo co = drycargoService.findOneById(dryId);
						if(co==null){
							one.setDryCargoBean(null);
							continue;
						}
						//String childCategoryId = co.getChildCategoryId();
						//Category category = categoryService.getCategoryNameById(id)
					
						if(dryFlag==co.getDryFlag()){
							one.setDryCargoBean(co);
						}else{
							continue;
						}
						
					}
				}
			}
		}else{
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_201,
					null);
		}
		return udb;
	}
	
	/**
	 * 删除干货收藏
	 * @param courseId
	 * @param userId
	 * @throws XueWenServiceException
	 */
	public void deleFav(String drycargoId)throws XueWenServiceException{
		List<UserDrycargoBean> udb = userDrycargoRepository.findByDryCargoBean(drycargoId);
		UserDrycargoBean userDryBean = null;
		if(udb != null){
			if(udb.size() > 0){
				for(int i = 0; i < udb.size() ; i++){
					userDryBean = udb.get(i);
					userDryBean.setIsFav(0);
					userDrycargoRepository.save(userDryBean);
				}
			}
		}
		
	}
	/**
	 * 删除干货收藏
	 * @param courseId
	 * @param userId
	 * @throws XueWenServiceException
	 */
	public void deleFav(String drycargoId,String userId)throws XueWenServiceException{
		UserDrycargoBean udb = userDrycargoRepository.findByUserAndDryCargoBean(userId, drycargoId);
		String dryId = "";
		if(udb == null){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_201,
					null);
		}else{
			if(udb.getIsFav()==1){
				udb.setIsFav(0);
				udb.setUtime(System.currentTimeMillis());
				dryId = udb.getDryCargoBean().toString();
				Drycargo dry = drycargoService.findOneById(dryId);
//				Map<String,Long> whoFav = dry.getWhoFav();
//				whoFav.remove(userId);
//				dry.setWhoFav(whoFav);
				if(dry.getFavCount() > 0){
				dry.setFavCount(dry.getFavCount()-1);
				drycargoService.saveDrycargo(dry);
				}
			}
			List<Object> sourceId = new ArrayList<Object>();
			sourceId.add(drycargoId);
			favService.deleteByUserIdAndSourceIds(userId,sourceId);
			
			
			
		}
		userDrycargoRepository.save(udb);
		
	}
	
	/**
	 * 将课程加入用户收藏列表
	 * @param courseId
	 * @param userId
	 * @throws XueWenServiceException
	 */
	public void addFav(String id,String userId,String fromGroupId,String appkey)throws XueWenServiceException{
		
		UserDrycargoBean udb=userDrycargoRepository.findByUserAndDryCargoBean(userId, id);
		Drycargo dc = drycargoService.findOneById(id);
		if(udb == null){
			udb=new UserDrycargoBean(userId,id);
			
			drycargoService.countOperationFav(userId, dc.getId().toString(),appkey,dc.getDryFlag());
		}else{
			if(udb.getIsFav()==0){
				drycargoService.countOperationFav(userId, dc.getId().toString(),appkey,dc.getDryFlag());
			}
			else{
				if(StringUtil.isBlank(fromGroupId)){
				throw new XueWenServiceException(Config.STATUS_201,
						Config.MSG_FAVDAY_201, null);
				}
			}
			udb.setUtime(System.currentTimeMillis());
		}
		udb.setIsFav(1);
		userDrycargoRepository.save(udb);
		
	}	
}

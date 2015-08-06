package operation.service.xuanye;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;
import operation.exception.XueWenServiceException;
import operation.pojo.user.User;
import operation.pojo.user.UserShort;
import operation.pojo.xuanye.Xuanye;
import operation.repo.xuanye.XuanyeRepository;
import operation.service.user.UserService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import tools.Config;
import tools.JSON2ObjUtil;
import tools.YXTJSONHelper;

@Service
@Component
public class XuanyeService {
	private static final Logger logger = Logger.getLogger(XuanyeService.class);
	@Autowired
	public XuanyeRepository xuanyeRepo;
	@Autowired
	public UserService  userService;
	@Value("${tag.service.url}")
	private String tagServiceUrl;	

	/**
	 * 上传炫页
	 * @param user
	 * @param xuanye
	 * @param tagName
	 * @return
	 * @throws XueWenServiceException
	 */
	public Xuanye uploadXuanye(User user, Xuanye xuanye, String tagName) throws XueWenServiceException{
		Xuanye result = this.isExitXueye(xuanye.getGroup().toString(), xuanye.getUrl());
		long time = System.currentTimeMillis();
		if(tagName!=null){
			tagName = JSON2ObjUtil.getArrayFromString(tagName);
		}
		if(result != null){
			if(!result.getShareids().contains(user.getId())){ //如果创建者不包含此用户
				UserShort usr = new UserShort();
				usr.setUserId(user.getId());
				usr.setUserName(user.getUserName());
				result.getSharePerList().add(usr);
				result.getShareids().add(user.getId());
				result.setUtime(time);
				return saveXueye(result);
			}else{
				throw new XueWenServiceException(Config.STATUS_201,Config.MSG_XUEYE_201, null);
			}
		}else{
				UserShort us=new UserShort();
				us.setUserId(user.getId());
				us.setUserName(user.getUserName());
				List<UserShort> list=new ArrayList<UserShort>();
				list.add(us);
				xuanye.setSharePerList(list);
				List<Object> listId =new ArrayList<Object>();
				listId.add(user.getId());
				xuanye.setShareids(listId);
				xuanye.setCtime(time);
				xuanye = xuanyeRepo.save(xuanye);
				RestTemplate restTemplate=new RestTemplate();
				restTemplate.getForObject(tagServiceUrl+"tag/createTagBatch?domain="+"yxtapp"+"&itemId="+xuanye.getId()+"&userId="+user.getId()+"&userName="+user.getNickName()+"&itemType="+20+"&tagNames="+tagName, String.class);
		}
		return xuanye;
		
	}
	/**
	 * 判断该群下是否有同一炫页
	 * @param groupId
	 * @param url
	 * @return
	 * @throws XueWenServiceException
	 */
	public Xuanye isExitXueye(String groupId,String url) throws XueWenServiceException{
		Xuanye xuanye = xuanyeRepo.findOneByGroupAndUrl(groupId,url);
		return xuanye;
	}
	/**
	 * 保存炫页
	 * @param xuanye
	 * @return
	 * @throws XueWenServiceException
	 */
	public Xuanye saveXueye(Xuanye xuanye)  throws XueWenServiceException{
		return xuanyeRepo.save(xuanye);
	}
	public Page<Xuanye> all(String keywords, Pageable pageable) {
		return xuanyeRepo.findAll(pageable);
	}
	
	/**
	 * 处理干货对象转换成客户端使用
	 * @param drycargo
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<Object> toResponeses(List<Xuanye> xuanyeList) throws XueWenServiceException{
		List<Object> uprs = new ArrayList<Object>();
		if(xuanyeList==null || xuanyeList.size()<=0){
		}else{
			for(int i = 0; i < xuanyeList.size(); i++){
				uprs.add(toResponse(xuanyeList.get(i)));
			}
		}
		return uprs;
	}
	/**
	 * 处理干货对象转换成客户端使用
	 * @param Drycargo
	 * @return
	 * @throws XueWenServiceException
	 */
	public Object toResponse(Xuanye xuanye)throws XueWenServiceException{
		if (xuanye == null) {
			throw new XueWenServiceException(Config.STATUS_201,Config.MSG_NODATA_201, null);
		}
		List<UserShort> us = new ArrayList<UserShort>();
		for(UserShort temp:xuanye.getSharePerList()){
			User user=userService.getOneByUserId(temp.getUserId());
			if(user!=null){
				temp.setUserName(user.getNickName());
				us.add(temp);
			}
			xuanye.setSharePerList(us);
		}
		 
		RestTemplate restTemplate=new RestTemplate();
		String tag = restTemplate.getForObject(tagServiceUrl+"tag/getTagsByIdAndType?domain="+"yxtapp"+"&itemId="+xuanye.getId()+"&itemType="+20, String.class);
		JSONObject objj=JSONObject.fromObject(tag);
		JSONObject obss=objj.getJSONObject("data");
		net.sf.json.JSONArray childs= obss.getJSONArray("result"); 
		xuanye.setXuanyeTagName(childs);
		//去掉无需返回前端的属性,只包含以下属性
		String[] exclude = {"shareids","whoView","viewCount"};
		return  YXTJSONHelper.excludeAttrJsonObject(xuanye, exclude);
	}
	/**
	 * 通过炫页ID与群组ID查询炫页
	 * @param currentUser
	 * @param xuanyeId
	 * @param groupId
	 * @return
	 * @throws XueWenServiceException
	 */
	public Xuanye xuanyeDetail(User currentUser, String xuanyeId,String groupId) throws XueWenServiceException{
		return xuanyeRepo.findOneByGroupAndId(groupId,xuanyeId);
	}
}

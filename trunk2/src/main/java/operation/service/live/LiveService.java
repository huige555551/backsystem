package operation.service.live;

import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;
import operation.exception.XueWenServiceException;
import operation.pojo.activity.NewActivity;
import operation.pojo.group.XueWenGroup;
import operation.pojo.live.Live;
import operation.pojo.live.VhallWebinar;
import operation.pojo.topics.Images;
import operation.pojo.user.User;
import operation.repo.live.LiveRepo;
import operation.repo.live.LiveTemplate;
import operation.service.activity.NewActivityService;
import operation.service.dynamic.GroupDynamicService;
import operation.service.group.GroupService;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tools.Config;
import tools.ImageUtil;
import tools.JSON2ObjUtil;
import tools.StringUtil;

/**
 * 直播
 * 
 * @author nes
 *
 */
@Service
public class LiveService {
	private static final Logger l=Logger.getLogger(NewActivityService.class);
	@Autowired
	private LiveRepo liveRepo;
	@Autowired
	private LiveTemplate liveTemplate;
	@Autowired
	private GroupDynamicService groupDynamicService;
	@Autowired
	private VhallWebinarService vhallWebinarService;
	
	@Autowired
	private GroupService groupService;
	
	/**
	 * 创建直播
	 * @param url
	 * @param currUser
	 * @param sourceId
	 * @param groupName
	 * @param groupLogoUrl
	 * @return
	 * @throws XueWenServiceException
	 */
	public Live create(String url, User currUser, String sourceId,String groupName, String groupLogoUrl) throws XueWenServiceException{
		List<JSONObject> groupList = new ArrayList<JSONObject>();
		JSONObject group = new JSONObject();
		group.put("groupId", sourceId);
		group.put("groupName",groupName);
		group.put("groupLogoUrl",groupLogoUrl);
		groupList.add(group);
		if(!this.exiseByUrl(url)){
			if(!StringUtil.isBlank(url)){
				//判断此url是否已经被创建
				if(url.contains(Config.LIVE_URL_VHSTART)){
					try {
						return this.createVhLive(url, groupList,currUser);
					} catch (Exception e) {
						throw  new XueWenServiceException(Config.STATUS_201, "创建直播错误",null);
					}
				}
			}
		}else{
			Live l=this.findByLiveUrl(url);
			List<JSONObject> groups=l.getGroup();
			for(JSONObject g:groups){
				if(sourceId.equals(g.getString("groupId"))){
					throw new XueWenServiceException(Config.STATUS_201, "此直播已存在",null);
				}
			}
			groups.addAll(groupList);
			return liveRepo.save(l);
		}
		
		throw  new XueWenServiceException(Config.STATUS_201, "创建直播失败",null);	}
	/**
	 * 根据微吼url创建直播信息
	 * @param url
	 * @param groupList
	 * @return
	 * @throws Exception
	 */
	private  Live createVhLive(String url,List<JSONObject> groupList,User user )throws Exception{
		int i=url.lastIndexOf("/");
		String num=url.substring(i+1);
		if(!StringUtil.isBlank(num)){
			Pattern p = Pattern.compile("^\\d{9}$");
			if(p.matcher(num).matches()){
				VhallWebinar vh=vhallWebinarService.getVhLiveInfo(num);
				if(vh !=null){
//					2015-07-11 13:30:00
					SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date start = sdf.parse(vh.getT_start());
					Date end = sdf.parse(vh.getT_end());
					String image=getVhPicUrl(url);
					List<Images> images=this.changePicUrlToImage(image);
					return liveRepo.save(new Live(vh.getSubject(), start.getTime(),end.getTime(), Config.LIVE_TYPE_VH, 
							vh.getWebinar_desc(), images, groupList, num, url,user.getId(),user.getNickName(),user.getLogoURL()));
				}
			}
		}
		throw new Exception("创建直播失败");
	}
	/**
	 * 更改图片
	 * @param live
	 * @param image
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<Images> changePicUrlToImage(String image)
			throws XueWenServiceException {
		// 如果image不为空，代表新版本创建
		List<Images> imageList = new ArrayList<Images>();
		if (!StringUtil.isBlank(image)) {
			if(!image.startsWith("http://")){
				image="http://"+image;
			}
			BufferedImage bi=ImageUtil.getBufferedImage(image);
			Images i=new Images();
			i.setPicUrl(image);
			if(bi !=null){
				i.setPicHeight(bi.getHeight());
				i.setPicWidth(bi.getWidth());
			}
			imageList.add(i);
		}
		return imageList;
	}
	/**
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public  String getVhPicUrl(String url)throws Exception{
		Document doc1=	Jsoup.connect(url).
				userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31")
				.timeout(10000).get();
		Element item = doc1.getElementsByClass("video-img").first();
		if(item!=null){
			return item.attr("src").substring(2);
		}
		return null;
	}
	/**
	 * 根据url判断直播是否存在
	 * 
	 * @param url
	 * @return
	 * @throws XueWenServiceException
	 */
	public boolean exiseByUrl(String url)throws XueWenServiceException{
		return liveTemplate.exiseByUrl(url);
	}
	/**
	 * 根据地址查找活动信息
	 * @param url
	 * @return
	 * @throws XueWenServiceException
	 */
	public Live findByLiveUrl(String url)throws XueWenServiceException{
		return liveRepo.findByLiveUrl(url);
	}
	/**
	 * 查询群组直播课
	 * @param groupId
	 * @param pageable
	 * @return
	 * @throws XueWenServiceException
	 */
	public Page<Live> findByGroupId(String groupId, Pageable pageable) throws XueWenServiceException{
		return liveTemplate.findByGroupId(pageable, groupId);
	}
	/**
	 * 修改直播课
	 * @param activityId
	 * @param currUser
	 * @param live
	 * @return
	 */
	public Live update(String liveId, User currUser, Live live,String image,String group) throws XueWenServiceException{
		Live liveResult = liveRepo.findOne(liveId);
		if(liveResult == null){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_NODATA_201,null);
		}
		if (!StringUtil.isBlank(image)) { //如果图片不为空
			List<Images> imageList = JSON2ObjUtil.getDTOList(image,Images.class);
			liveResult.setImages(imageList);
		}
		//去掉的群组
		List<JSONObject> removeGroup=new ArrayList<JSONObject>();//去掉的群组列表
		List<JSONObject> addGroup=new ArrayList<JSONObject>();//新增的群组列表
		
		if(!StringUtil.isBlank(group)){ //如果分享的群不为空
			List<JSONObject> groupList = JSON2ObjUtil.getDTOList(group,JSONObject.class);
			List<JSONObject> oldGroupList=liveResult.getGroup();//原有的群组列表
			
			addGroup.addAll(groupList);//修改后的群组列表
			addGroup.removeAll(oldGroupList);//新增的群组
			removeGroup.addAll(oldGroupList);//以前有的群组列表
			removeGroup.removeAll(groupList);//删除的群组列表
			
			liveResult.setGroup(groupList);
		}
		if(!StringUtil.isBlank(live.getIntro())){
			liveResult.setIntro(live.getIntro());
		}
		if(!StringUtil.isBlank(live.getTitle())){
			liveResult.setTitle(live.getTitle());
		}
		if(live.getLiveEndTime()!=0){
			liveResult.setLiveEndTime(live.getLiveEndTime());
		}
		if(live.getLiveStartTime()!=0){
			liveResult.setLiveStartTime(live.getLiveStartTime());
		}
		live = liveRepo.save(liveResult);
		/**
		 * 新增的群组添加动态
		 */
		for(JSONObject obj:addGroup){
    		try {
    			//创建群组动态
    			this.createGroupDynamic(liveResult, obj.getString("groupId"));
    		} catch (Exception e) {
    			l.error("======创建活动群组动态失败：========"+e);
    		}
    	}
		//删除去掉的群组动态
		for(JSONObject obj:removeGroup){
			try {
				//创建群组动态
				this.deleteByGroupIdAndSourceId( obj.getString("groupId"),liveResult.getId());
			} catch (Exception e) {
				l.error("======创建活动群组动态失败：========"+e);
			}
		}
		return live;
	}
	/**
	 * 分享直播
	 * @param liveId
	 * @param group
	 * @param currUser
	 * @return
	 * @throws XueWenServiceException
	 */
	public boolean share(String liveId, String source, User currUser) throws XueWenServiceException{
		Live liveResult = liveRepo.findOne(liveId);
		if(liveResult == null){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_NODATA_201,null);
		}
		List<JSONObject> newAddGroups=new ArrayList<JSONObject>();
		if(!StringUtil.isBlank(source)){
			List<JSONObject> groupList = JSON2ObjUtil.getDTOList(source,JSONObject.class);
			newAddGroups.addAll(groupList);
			List<JSONObject> oldGroup=liveResult.getGroup();
			//取新增的群组
			newAddGroups.removeAll(oldGroup); 
			liveResult.getGroup().addAll(newAddGroups);
		}

    	liveRepo.save(liveResult);
    	for(JSONObject obj:newAddGroups){
    		try {
    			//创建群组动态
    			this.createGroupDynamic(liveResult, obj.getString("groupId"));
    		} catch (Exception e) {
    			l.error("======创建活动群组动态失败：========"+e);
    		}
    	}
    	return true;
	}
	/**
	 * 创建动态
	 * @param a
	 * @param groupId
	 * @throws XueWenServiceException
	 */
	private void createGroupDynamic(Live a, String groupId) throws XueWenServiceException{
		if(a !=null && !StringUtil.isBlank(groupId) ){
			groupDynamicService.addGroupDynamic(groupId, "","",a.getId(), a.getTitle(), a.getIntro(),
			"", a.getImages(),a.getCreateUser(), a.getCreateUserName(), a.getCreateUserLogoUrl(), Config.TYPE_LIVE_GROUP,a.getCtime());
		}
	}
	/**
	 * 根据sourceId和群组Id删除相关动态
	 * @param groupId
	 * @param sourceId
	 * @throws XueWenServiceException
	 */
	public void deleteByGroupIdAndSourceId(String groupId,String sourceId)throws XueWenServiceException{
		if(!StringUtil.isBlank(groupId) && !StringUtil.isBlank(sourceId)){
			groupDynamicService.deleteByGroupIdAndSourceId(groupId, sourceId);
		}
	}
	/**
	 * 删除直播
	 * @param liveId
	 * @param groupId
	 * @param currUser
	 * @return
	 * @throws XueWenServiceException
	 */
	public boolean delete(String liveId, String groupId, User currUser) throws XueWenServiceException{
		Live live = liveRepo.findOne(liveId);
		if(StringUtil.isBlank(groupId)){//创建者删除
			if(currUser.getId().equals(live.getCreateUser())){
				liveRepo.delete(live);
				for(JSONObject obj:live.getGroup()){
					try {
						//删除群组动态
						this.deleteByGroupIdAndSourceId( obj.getString("groupId"),live.getId());
					} catch (Exception e) {
						l.error("======创建活动群组动态失败：========"+e);
					}
				}
				return true;
			}else{
				throw new XueWenServiceException(Config.STATUS_201, Config.MSG_NOACESS_201,null);
			}
			
		}else{//从小组里删除直播
			XueWenGroup group = groupService.findByid(groupId);
			if(group!=null){
				if(group.getOwner().contains(currUser.getId())){
					JSONObject[] str = new JSONObject[3];
					str[0] = JSONObject.fromObject(groupId);;
					str[1] = JSONObject.fromObject(group.getGroupName());
					str[2] = JSONObject.fromObject(group.getLogoUrl());
					List<JSONObject> stooges = Arrays.asList(str);
					List<JSONObject> newAddGroups=live.getGroup();
					newAddGroups.removeAll(stooges);
					live.setGroup(newAddGroups);
					//删除去掉的群组动态
					for(JSONObject obj:stooges){
						try {
							this.deleteByGroupIdAndSourceId( obj.getString("groupId"),live.getId());
						} catch (Exception e) {
							l.error("======创建活动群组动态失败：========"+e);
						}
					}
				}
			}
		}
		
		liveRepo.save(live);
		return true;
	}
}

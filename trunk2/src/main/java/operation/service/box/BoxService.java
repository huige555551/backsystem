package operation.service.box;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import operation.exception.XueWenServiceException;
import operation.pojo.box.Box;
import operation.pojo.course.NewCourse;
import operation.pojo.dynamic.GroupDynamic;
import operation.pojo.index.Recommend;
import operation.pojo.pub.QueryModel;
import operation.repo.box.BoxPostTemplate;
import operation.repo.box.BoxRepository;
import operation.repo.box.BoxTemplate;
import operation.service.course.NewCourseService;
import operation.service.course.NewGroupCourseService;
import operation.service.dynamic.GroupDynamicService;
import operation.service.group.GroupService;
import operation.service.index.IndexService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import tools.Config;
import tools.PageRequestTools;
import tools.RestfulTemplateUtil;
import tools.StringUtil;
import tools.YXTJSONHelper;

@Service
@Component
public class BoxService {

	@Autowired
	private BoxRepository boxRepository;
	@Autowired
	private BoxTemplate boxTemplate;
	@Autowired
	private BoxPostService boxPostService;
	@Autowired
	private BoxPostTemplate boxPostTemplate;
	
//	private String indexGroupBoxId ="54f52619e9a1357e2cef3945";
//	private String indexCourseBoxId = "54f52633e9a1357e2cef3947";
	private String indexGroupBoxId ="55150471e4b0f94f1d1ba1e1";
	private String indexCourseBoxId = "551504a4e4b0f94f1d1ba23a";
	
	@Autowired
	private GroupService groupService;
	@Autowired
	public NewGroupCourseService newGroupCourseService;
	
	@Autowired
	private GroupDynamicService groupDynamicService;
	
	@Autowired
	private IndexService indexService;
	
	@Autowired
	private NewCourseService newCourseService;
	
	
	@Value("${coupon.service.url}")
	private String ztiaoCouponUrl;
	
	
	public BoxService(){
		super();
	}
	
	/**
	 * 添加对象到相应位置列表
	 * @param boxPostId
	 * @param sourceType
	 * @param sourceId
	 * @return
	 * @throws XueWenServiceException
	 */
	public Box addInBoxPost(String boxPostId,String sourceType,String sourceId,String ctime,String groupid)throws XueWenServiceException{
		if(StringUtil.isBlank(boxPostId) || StringUtil.isBlank(sourceType) || StringUtil.isBlank(sourceId)){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
		if(!isExiseByBoxPostIdAndSourceId(boxPostId,sourceId)){
			int hasIn=countByBoxPostId(boxPostId);
			int size=boxPostService.getBoxPostSizeById(boxPostId);
//			if(size ==-1  || size-hasIn>0){
				long l=System.currentTimeMillis();
				return boxRepository.save(new Box(boxPostId, sourceId, sourceType,l,0,groupid));
//			}else{
//				throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
//			}
		}else{
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
	}
	/**
	 * 统计位置中的对象数目
	 * @param boxPostId
	 * @return
	 * @throws XueWenServiceException
	 */
	public int countByBoxPostId(String boxPostId)throws XueWenServiceException{
		return boxRepository.countByPost(boxPostId);
	}
	
	/**
	 * 根据Id删除位置中的对象
	 * @param boxId
	 * @throws XueWenServiceException
	 */
	public void deleteByBoxId(String boxId)throws XueWenServiceException{
		boxRepository.delete(boxId);
	}
	
	/**
	 * 根据Id删除位置中的对象
	 * @param boxId
	 * @throws XueWenServiceException
	 */
	public void deleteByGroupId(Box b)throws XueWenServiceException{
		boxRepository.delete(b);
	}
	
	/**
	 * 根据sourse删除位置中的对象
	 * @param boxId
	 * @throws XueWenServiceException
	 */
	public void deleteBysourseId(String sourseId)throws XueWenServiceException{
		boxPostTemplate.deleteBysourceId(sourseId);
	}
	
	/**
	 * 根据Id删除位置
	 * @param boxId
	 * @throws XueWenServiceException
	 */
	public void deleteById(String id)throws XueWenServiceException{
		boxTemplate.deleteByParentId(id);
		boxPostTemplate.deleteById(id);
		
	}
	
	/**
	 * 根据PostId和sourceId判断数据条目是否存在
	 * @param boxPostId
	 * @param sourceId
	 * @return
	 * @throws XueWenServiceException
	 */
	public boolean isExiseByBoxPostIdAndSourceId(String boxPostId,String sourceId)throws XueWenServiceException{
		if(StringUtil.isBlank(boxPostId)  || StringUtil.isBlank(sourceId)){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
		return boxTemplate.isExiseByBoxPostIdAndSourceId(boxPostId, sourceId);
	}
	
	/**
	 * 根据位置Id获取位置下所有的对象Id
	 * @param boxPostId
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<Object> getSourceIdsByBoxPostId(String boxPostId)throws XueWenServiceException{
		List<Box> boxs=boxTemplate.getSourceIdsByBoxPostId(boxPostId);
		return getSourceIdsByBoxList(boxs);
	}
	
	
	
	/**
	 * 根据位置下所有对象集合，获取位置下所有的对象Id
	 * @param boxs
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<Object> getSourceIdsByBoxList(List<Box> boxs)throws XueWenServiceException{
		if(boxs !=null){
			List<Object> objs=new ArrayList<Object>();
			for(Box box:boxs){
				objs.add(box.getSourceId());
			}
			return objs;
		}else{
			return null;
		}
	}
	
	/**
	 * 根据位置Id分页获取位置下的对象
	 * @param boxPostId
	 * @param pageable
	 * @return
	 * @throws XueWenServiceException
	 */
	public Page<Box> findByBoxPostId(String boxPostId,Pageable pageable)throws XueWenServiceException{
		return boxRepository.findByPost(boxPostId, pageable);
	}
	
	/**
	 * 根据位置Id分页获取位置下的对象
	 * @param boxPostId
	 * @param pageable
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<Box> findAllByBoxPostId(String boxPostId)throws XueWenServiceException{
		return boxRepository.findByPost(boxPostId);
	}
	
	/**
	 * 根据位置Id和来源ID获取位置下的对象
	 * @param boxPostId
	 * @param pageable
	 * @return
	 * @throws XueWenServiceException
	 */
	public Box findByBoxPostIdAndSourceId(String boxPostId,String sourceId)throws XueWenServiceException{
		return boxRepository.findByPostAndSourceId(boxPostId, sourceId);
	}
	
	
	/**
	 * 根据boxid查询
	 * @param boxPostId
	 * @param pageable
	 * @return
	 * @throws XueWenServiceException
	 */
	public Box findByBoxId(String boxId)throws XueWenServiceException{
		return boxRepository.findOne(boxId);
	}
	
	
	/**
	 * 根据boxid查询
	 * @param boxPostId
	 * @param pageable
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<Box> findByGroupid(String groupid)throws XueWenServiceException{
		return boxRepository.findByGroupid(groupid);
	}
	
	/**
	 * 根据boxid查询
	 * @param boxPostId
	 * @param pageable
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<Box> findByBoxIdtest()throws XueWenServiceException{
		return boxRepository.findAll();
	}
	
	/**
	 * 跟新权重
	 * @param boxPostId
	 * @param pageable
	 * @return
	 * @throws XueWenServiceException
	 */
	public void saveBox(Box box)throws XueWenServiceException{
		 boxRepository.save(box);
	}
	/**
	 * 查询首页推荐数据（盒子里获取）
	 * @param qm
	 * @return
	 * @throws XueWenServiceException
	 */
	public JSONObject findRecommended(String n,String courseN,String userId) throws XueWenServiceException{
		QueryModel dm = new QueryModel();
		QueryModel dm1 = new QueryModel();
		dm.setS(3);
		dm1.setS(1);
		dm.setN(Integer.parseInt(n));
		dm1.setN(Integer.parseInt(n));
		Pageable pageable = PageRequestTools.pageRequesMake(dm);
		Pageable pageable1 = PageRequestTools.pageRequesMake(dm1);
		Page<Box> groupBoxs = this.findByBoxPostId(indexGroupBoxId, pageable);//群组
		int groupPages = groupBoxs.getTotalPages();//群的总页数
		Page<Box> courseBoxs = this.findByBoxPostId(indexCourseBoxId, pageable1);//课程
		Map<String,Object> addAndModifyMap=new HashMap<String, Object>();
		addAndModifyMap.put("group", groupService.toBox(groupBoxs.getContent()));
		addAndModifyMap.put("course", newGroupCourseService.toBoxResponses(courseBoxs.getContent(),"index",userId));
		int coursePages = courseBoxs.getTotalPages();//课程的总页数
	//	int page = StringUtil.compare(groupPages,coursePages);//获得最小页数
		addAndModifyMap.put("nGroup", StringUtil.getPage(Integer.parseInt(n), groupPages));//群组页数
		addAndModifyMap.put("nCourse", StringUtil.getPage(Integer.parseInt(n), coursePages));//课程页数
		Recommend r = new Recommend();
		return  YXTJSONHelper.addAndModifyAttrJsonObject(r, addAndModifyMap);
	}
	
	
	/**
	 * 根据位置Id获取位置下所有的对象Id
	 * @param boxPostId
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<Object> getSourceIdsByBoxPostIdAndNotInCagetory(String boxPostId,String type)throws XueWenServiceException{
		List<Box> boxs=boxTemplate.getSourceIdsByBoxPostId(boxPostId);
		return getSourceIdsByBoxAndCategoryList(boxs,type);
	}
	
	/**
	 * 根据位置下所有对象集合，获取位置下所有的对象Id
	 * @param boxs
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<Object> getSourceIdsByBoxAndCategoryList(List<Box> boxs,String type)throws XueWenServiceException{
		if(boxs !=null){
			List<Object> objs=new ArrayList<Object>();
			for(Box box:boxs){
				//if(type.equals(box.getSourceType())){
					objs.add(box.getSourceId());
				//}
			}
			return objs;
		}else{
			return null;
		}
	}
	/**
	 * 专题盒子遍历
	 * @param boxList
	 * @param userId
	 * @return
	 * @throws XueWenServiceException
	 */
	public Object formatForData(List<Box> boxList,String specialType) throws XueWenServiceException{
		List<String> sourceIds = new ArrayList<String>();
		//如果是红包专题
		if("activityspecial".equals(specialType)){
			String strR = "";
			for(int i = 0 ; i < boxList.size() ; i++){
				Box box = boxList.get(i);
				sourceIds.add(box.getSourceId().toString());//获得活动ids
				if(i<boxList.size()-1){
	    			strR = strR +boxList.get(i).getSourceId()+",";
	    		}else{
	    			strR = strR +boxList.get(i).getSourceId();
	    		}
			}
			long currTime = System.currentTimeMillis();
			JSONObject obj = this.findAllActivity(strR, String.valueOf(currTime));
			JSONObject data=obj.getJSONObject("data");
			data=YXTJSONHelper.addAndModifyAttrJsonObject(data, new HashMap<String, Object>());
			System.out.println(data.toString());
			JSONArray result=data.getJSONArray("result");
			result=result.fromObject(result,YXTJSONHelper.initJSONObjectConfig()); //获得活动列表
			System.out.println(result.toString());
			result = this.formateActivity(result);
			return result;
			
		}else{
			for(int i = 0; i < boxList.size(); i++ ){
				sourceIds.add(boxList.get(i).getSourceId().toString());
			}
			List<GroupDynamic>  groupDynamics = groupDynamicService.findAllGroupDynamicInSourceIds(sourceIds);
			JSONArray ja = JSONArray.fromObject(groupDynamics);
			 
			//ja = indexService.formateGroupDynamic(userId, ja);
		return ja;
		}
	}
	
	
	
	
	
	/**
	 * 专题盒子遍历
	 * @param boxList
	 * @param userId
	 * @return
	 * @throws XueWenServiceException
	 */
	public Object formatForData_3(List<Box> boxList,String specialType,String boxpostid) throws XueWenServiceException{
		List<String> sourceIds = new ArrayList<String>();
		//如果是红包专题
		if("activityspecial".equals(specialType)){
			String strR = "";
			for(int i = 0 ; i < boxList.size() ; i++){
				Box box = boxList.get(i);
				sourceIds.add(box.getSourceId().toString());//获得活动ids
				if(i<boxList.size()-1){
	    			strR = strR +boxList.get(i).getSourceId()+",";
	    		}else{
	    			strR = strR +boxList.get(i).getSourceId();
	    		}
			}
			long currTime = System.currentTimeMillis();
			JSONObject obj = this.findAllActivity(strR, String.valueOf(currTime));
			JSONObject data=obj.getJSONObject("data");
			data=YXTJSONHelper.addAndModifyAttrJsonObject(data, new HashMap<String, Object>());
			System.out.println(data.toString());
			JSONArray result=data.getJSONArray("result");
			result=result.fromObject(result,YXTJSONHelper.initJSONObjectConfig()); //获得活动列表
			//System.out.println(result.toString());
			result = this.formateActivity_2(result,boxpostid);
			System.out.println(result.toString());
			return result;
			
		}else{
			for(int i = 0; i < boxList.size(); i++ ){
				sourceIds.add(boxList.get(i).getSourceId().toString());
			}
			List<GroupDynamic>  groupDynamics = groupDynamicService.findAllGroupDynamicInSourceIds(sourceIds);
			List l=new ArrayList();
			for(int i = 0; i < groupDynamics.size(); i++){
				Map<String, Object> m=new HashMap<String, Object>();
				Box b=boxRepository.findByPostAndSourceId(boxpostid,groupDynamics.get(i).getSourceId());
				m.put("boxid", b.getId());
				m.put("content", groupDynamics.get(i));
				l.add(m);
			}
			JSONArray ja = JSONArray.fromObject(l);
			
			//ja = indexService.formateGroupDynamic(userId, ja);
		return ja;
		}
	}
	
	
	
	
	
	
	/**
	 * 专题盒子遍历
	 * @param boxList
	 * @param userId
	 * @return
	 * @throws XueWenServiceException
	 */
	public Object formatForData_2(List<Box> boxList,String specialType) throws XueWenServiceException{
		List<String> sourceIds = new ArrayList<String>();
		Map<String , Object> m=new HashMap<String, Object>();
		//如果是红包专题
		if("activityspecial".equals(specialType)){
			String strR = "";
			for(int i = 0 ; i < boxList.size() ; i++){
				Box box = boxList.get(i);
				sourceIds.add(box.getSourceId().toString());//获得活动ids
				if(i<boxList.size()-1){
	    			strR = strR +boxList.get(i).getSourceId()+",";
	    		}else{
	    			strR = strR +boxList.get(i).getSourceId();
	    		}
			}
			long currTime = System.currentTimeMillis();
			JSONObject obj = this.findAllActivity(strR, String.valueOf(currTime));
			JSONObject data=obj.getJSONObject("data");
			data=YXTJSONHelper.addAndModifyAttrJsonObject(data, new HashMap<String, Object>());
			System.out.println(data.toString());
			JSONArray result=data.getJSONArray("result");
			result=result.fromObject(result,YXTJSONHelper.initJSONObjectConfig()); //获得活动列表
			System.out.println(result.toString());
			result = this.formateActivity(result);
			return result;
			
		}else{
			for(int i = 0; i < boxList.size(); i++ ){
				sourceIds.add(boxList.get(i).getSourceId().toString());
			}
			List<GroupDynamic>  groupDynamics = groupDynamicService.findAllGroupDynamicInSourceIds(sourceIds);
			JSONArray ja = JSONArray.fromObject(groupDynamics);
			
			//ja = indexService.formateGroupDynamic(userId, ja);
		return ja;
		}
	}
	
	public Object differentiate(List<Box> boxList,String userId,String specialType) throws XueWenServiceException{
		List<String> sourceIds = new ArrayList<String>();
			//如果是红包专题
			if("sepcialCoupon".equals(specialType)){
				String strR = "";
				for(int i = 0 ; i < boxList.size() ; i++){
					Box box = boxList.get(i);
					sourceIds.add(box.getSourceId().toString());//获得活动ids
					if(i<boxList.size()-1){
		    			strR = strR +boxList.get(i).getSourceId()+",";
		    		}else{
		    			strR = strR +boxList.get(i).getSourceId();
		    		}
				}
				long currTime = System.currentTimeMillis();
				JSONObject obj = this.findAllActivity(strR, String.valueOf(currTime));
				JSONObject data=obj.getJSONObject("data");
				data=YXTJSONHelper.addAndModifyAttrJsonObject(data, new HashMap<String, Object>());
				System.out.println(data.toString());
				JSONArray result=data.getJSONArray("result");
				result=result.fromObject(result,YXTJSONHelper.initJSONObjectConfig()); //获得活动列表
				System.out.println(result.toString());
				//result = this.formateActivity(result, userId);
				return result;
				
				
			}
			return null;
		
	}
	/**
	 * 调用红包服务获得多批次未过期活动
	 * @param couponId
	 * @param status
	 * @return
	 */
	public JSONObject findAllActivity(String couponId, String currTime) {
		String url = ztiaoCouponUrl+ Config.COUPON_ALL_ACTIVITY;
		Map<String,String> map=new HashMap<String, String>();
		map.put("couponids", couponId);
		map.put("currTime", currTime);
		RestfulTemplateUtil restfulTemplateUtil=new RestfulTemplateUtil();
		JSONObject obj=restfulTemplateUtil.getRestApiData(url, map);
		return obj;
	}
	/***
	 * 处理活动列表
	 * @param gds
	 * @return
	 * @throws XueWenServiceException
	 */
	public JSONArray formateActivity(JSONArray  gds)throws XueWenServiceException{
		//List<String> ss=new ArrayList<String>();
		for(int i=0;i<gds.size();i++){
			JSONObject gd=gds.getJSONObject(i);
			String sourceId=gd.getString("activityCode");//活动编码 ss.size() 数量
			
			//ss.add(sourceId);
//			JSONObject obj = this.findCouponByUserId(sourceId, userId);
//			JSONObject data=obj.getJSONObject("data");
//			data=YXTJSONHelper.addAndModifyAttrJsonObject(data, new HashMap<String, Object>());
//			System.out.println(data.toString());
//			JSONArray result=data.getJSONArray("result");
//			result=result.fromObject(result,YXTJSONHelper.initJSONObjectConfig()); //获得是否领过红包
//			JSONObject coupon=result.getJSONObject(i);
//			if(coupon==null){
//				gd.put("isled", false);
//			}else{
//				gd.put("isled", true);
//			}
			String courseId = gd.getString("courseId");//获得课程ID
			NewCourse course = newCourseService.findOneNewCourseByIdBasicInfoIncludeChapter(courseId);
			//Object course1=course.fromObject(course,YXTJSONHelper.initJSONObjectConfig()); //获得是否领过红包
			
			gd.put("course", newGroupCourseService.formateCourse(course));
		}
		return gds;
	}
	
	
	
	/***
	 * 处理活动列表
	 * @param gds
	 * @return
	 * @throws XueWenServiceException
	 */
	public JSONArray formateActivity_2(JSONArray  gds,String boxpostid)throws XueWenServiceException{
		//List<String> ss=new ArrayList<String>();
		for(int i=0;i<gds.size();i++){
			JSONObject gd=gds.getJSONObject(i);
			String sourceId=gd.getString("activityCode");//活动编码 ss.size() 数量
			Box b=boxRepository.findByPostAndSourceId(boxpostid,gd.getString("id"));
			gd.put("boxid",b.getId());
			//ss.add(sourceId);
//			JSONObject obj = this.findCouponByUserId(sourceId, userId);
//			JSONObject data=obj.getJSONObject("data");
//			data=YXTJSONHelper.addAndModifyAttrJsonObject(data, new HashMap<String, Object>());
//			System.out.println(data.toString());
//			JSONArray result=data.getJSONArray("result");
//			result=result.fromObject(result,YXTJSONHelper.initJSONObjectConfig()); //获得是否领过红包
//			JSONObject coupon=result.getJSONObject(i);
//			if(coupon==null){
//				gd.put("isled", false);
//			}else{
//				gd.put("isled", true);
//			}
			String courseId = gd.getString("courseId");//获得课程ID
			NewCourse course = newCourseService.findOneNewCourseByIdBasicInfoIncludeChapter(courseId);
			//Object course1=course.fromObject(course,YXTJSONHelper.initJSONObjectConfig()); //获得是否领过红包
			
			gd.put("course", newGroupCourseService.formateCourse(course));
		}
		return gds;
	}
	
	
	
	
	
	/**
	 * 
	 * @param couponId
	 * @param status
	 * @return
	 */
	public JSONObject findCouponByUserId(String activityCode, String userId) {
		String url = ztiaoCouponUrl+ Config.COUPON_EXTRACT;
		Map<String,String> map=new HashMap<String, String>();
		map.put("activitycode", activityCode);
		map.put("userid", userId);
		map.put("username", "");
		RestfulTemplateUtil restfulTemplateUtil=new RestfulTemplateUtil();
		JSONObject obj=restfulTemplateUtil.getRestApiData(url, map);
		return obj;
	}
	
	
}

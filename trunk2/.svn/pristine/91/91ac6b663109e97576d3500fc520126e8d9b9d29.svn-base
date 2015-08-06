package operation.service.ossRecomend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import operation.exception.XueWenServiceException;
import operation.pojo.activity.Activity;
import operation.pojo.box.BoxPost;
import operation.pojo.category.Category;
import operation.pojo.course.NewCourse;
import operation.pojo.course.NewGroupCourse;
import operation.pojo.drycargo.Drycargo;
import operation.pojo.group.XueWenGroup;
import operation.pojo.topics.Topic;
import operation.service.activity.ActivityService;
import operation.service.box.BoxPostService;
import operation.service.box.BoxService;
import operation.service.category.CategoryService;
import operation.service.course.NewCourseService;
import operation.service.course.NewGroupCourseService;
import operation.service.drycargo.DrycargoService;
import operation.service.group.GroupService;
import operation.service.topics.TopicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tools.Config;
import tools.YXTJSONHelper;

/**
 * 
 * @ClassName: 
 * @Description: 运维数据
 * @author tangli
 * @date 2015年3月3日 上午8:40:20
 *
 */
@Service
public class OssRecomendService {
	@Autowired
	private BoxPostService boxPostService;
	@Autowired
	private BoxService boxService;
	@Autowired
	private DrycargoService drycargoService;
	@Autowired
	private GroupService groupService;
	@Autowired
	private TopicService topicService;
	@Autowired
	private NewGroupCourseService newGroupCourseService;
	@Autowired
	private NewCourseService newCourseService;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private CategoryService categoryService;

	/**
	 * 
	 * @Title: findIndexDry
	 * @auther tangli
	 * @Description: 取干货内容
	 * @param name
	 * @return
	 * @throws XueWenServiceException
	 *             List<Drycargo>
	 * @throws
	 */
	public Page<Drycargo> findDry(String name ,Pageable pageable)
			throws XueWenServiceException {
		List<Object> ids = getSourceIdByBoxPostName(name);
		Page<Drycargo> drycargos = drycargoService.findByIdIn(ids,pageable);
		return drycargos;
	}
	
	
	/**
	 * 
	 * @Title: shutDown
	 * @auther Tangli
	 * @Description: 瘦身
	 * @param drycargos
	 * @return List<JSONObject>
	 * @throws
	 */
	public List<JSONObject> shutDown(Page<Drycargo> drycargos){
		List<JSONObject> res = new ArrayList<JSONObject>();
		for (Drycargo drycargo : drycargos) {
			JSONObject object = YXTJSONHelper.includeAttrJsonObject(drycargo,
					new String[] { "id","group","groupName", "url", "fileUrl", "message",
							"description" });
			res.add(object);
		}
		return res;
	}

	/**
	 * 
	 * @Title: findGroups
	 * @auther Tangli
	 * @Description: 取群组内容
	 * @param name
	 * @return
	 * @throws XueWenServiceException
	 *             List<JSONObject>
	 * @throws
	 */
	public Page<XueWenGroup> findGroups(String name,Pageable pageable)
			throws XueWenServiceException {
		List<Object> ids = getSourceIdByBoxPostName(name);
		Page<XueWenGroup> groups = groupService.findByIdIn(ids,pageable);
		return groups;
	}
	
	/**
	 * 
	 * @Title: shutDownGroups
	 * @auther Tangli
	 * @Description: 瘦身
	 * @param groups
	 * @return List<JSONObject>
	 * @throws
	 */
	public List<JSONObject> shutDownGroups(Page<XueWenGroup> groups) {
		List<JSONObject> res = new ArrayList<JSONObject>();
		for (XueWenGroup xueWenGroup : groups) {
			JSONObject object = YXTJSONHelper.includeAttrJsonObject(
					xueWenGroup, new String[] { "id", "groupName", "intro",
							"logoUrl", "memberCount" });
			res.add(object);
		}
		return res;
	}

	/**
	 * 
	 * @Title: findIndexTopics
	 * @auther Tangli
	 * @Description: 取话题内容
	 * @param name
	 * @return
	 * @throws XueWenServiceException
	 *             List<JSONObject>
	 * @throws
	 */
	public Page<Topic> findTopics(String name,Pageable pageable)
			throws XueWenServiceException {
		List<Object> ids = getSourceIdByBoxPostName(name);
		Page<Topic> topics = topicService.findByIdIn(ids,pageable);
		return topics;
		//return topicService.shoutlistforpc(topics);
	}

	/**
	 * 
	 * @Title: findCourse
	 * @auther Tangli
	 * @Description: 取课程内容
	 * @param name
	 * @return
	 * @throws XueWenServiceException
	 *             List<JSONObject>
	 * @throws
	 */
	public Page<NewGroupCourse>  findCourse(String name,Pageable pageable)
			throws XueWenServiceException {
		List<Object> ids = getSourceIdByBoxPostName(name);
		Page<NewGroupCourse> courses = newGroupCourseService.findByIdIn(ids,pageable);
	    return courses;
		}
	
	public List<JSONObject> shoutDownCourse(List<NewGroupCourse>courses){
		List<JSONObject> res = new ArrayList<JSONObject>();
		for (NewGroupCourse newGroupCourse : courses) {
			XueWenGroup group = groupService.findById((String) newGroupCourse
					.getGroup());
			NewCourse course = newCourseService.findOne((String) newGroupCourse
					.getCourse());
			Map<String, Object> map = new HashMap<String, Object>();
			if (course != null) {
				map.put("courseTitle", course.getTitle());
				map.put("courseId", course.getId());
				map.put("praiseCount", course.getPraiseCount());
				map.put("courseIntro", course.getIntro());
				map.put("logoUrl", course.getLogoUrl());
			} else {
				map.put("courseTitle", "");
				map.put("courseId", "");
				map.put("praiseCount", "");
				map.put("courseIntro", "");
				map.put("logoUrl","");
			}
			if (group != null) {
				map.put("groupId", group.getId());
				map.put("groupName", group.getGroupName());
			} else {
				map.put("groupId", "");
				map.put("groupName", "");
			}
			res.add(YXTJSONHelper.getInObjectAttrJsonObject(newGroupCourse,
					map, "id"));
		}
		return res;	
	}

	/**
	 * 
	 * @Title: getSourceIdByBoxPostName
	 * @auther Tangli
	 * @Description:通过首页位置名称获取相关内容的id集合
	 * @param name
	 * @return
	 * @throws XueWenServiceException
	 *             List<Object>
	 * @throws
	 */
	private List<Object> getSourceIdByBoxPostName(String name)
			throws XueWenServiceException {

		BoxPost boxPost = boxPostService.findByName(name);
		if (boxPost == null) {
			throw new XueWenServiceException(Config.STATUS_201, "位置不存在", null);
		}
		List<Object> ids = boxService.getSourceIdsByBoxPostId(boxPost.getId());
		return ids;
	}
	
	
	public List<Activity> findActivity(String name)
			throws XueWenServiceException {
		List<Object> ids = getSourceIdByBoxPostName(name);
		List<Activity> activitys = activityService.findByIdIn(ids);
		if (activitys==null) {
			return null;
			
		}
		return activitys;
	}


	public List<Category> findCategory(String name) throws XueWenServiceException {
		List<Object> ids = getSourceIdByBoxPostName(name);
		List<Category> categorys =new ArrayList<Category>();
		for (Object object : ids) {
			if(categoryService.findById(object.toString())!=null){
				categorys.add(categoryService.findById(object.toString()));
			}
			
		}
		
		if (categorys.size()==0) {
			return null;
			
		}
		return categorys;
	}
	
	public List<JSONObject> getCategoryJson(String name) throws XueWenServiceException{
		List<JSONObject> jsonObjects=new ArrayList<JSONObject>();
		JSONObject all=new JSONObject();
		all.put("count",groupService.count());
		all.put("categoryName", "全部");
		all.put("categoryId","");
		jsonObjects.add(all);
		List<Category> categorys=findCategory(name);
		for (Category category : categorys) {
			if(groupService.countByChildCategoryId(category.getId())!=0){
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("count", groupService.countByChildCategoryId(category.getId()));
				jsonObject.put("categoryName",category.getCategoryName());
				jsonObject.put("categoryId",category.getId());
				jsonObjects.add(jsonObject);
			};
		}
		return jsonObjects;
	}
}

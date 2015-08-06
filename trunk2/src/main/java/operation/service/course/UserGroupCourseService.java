package operation.service.course;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import operation.exception.XueWenServiceException;
import operation.pojo.cloudfile.Citem;
import operation.pojo.course.GroupCourse;
import operation.pojo.course.Knowledge;
import operation.pojo.course.Lesson;
import operation.pojo.course.NewChapter;
import operation.pojo.course.NewCourse;
import operation.pojo.course.NewGroupCourse;
import operation.pojo.course.UserChapter;
import operation.pojo.course.UserGroupCourse;
import operation.pojo.course.UserLesson;
import operation.pojo.fav.Fav;
import operation.pojo.group.XueWenGroup;
import operation.pojo.study.Study;
import operation.pojo.user.User;
import operation.repo.course.UserGroupCourseRepository;
import operation.repo.course.UserGroupCourseTemplate;
import operation.service.category.CategoryService;
import operation.service.fav.FavService;
import operation.service.group.GroupService;
import operation.service.praise.PraiseService;
import operation.service.share.ShareService;
import operation.service.study.StudyService;
import operation.service.user.UserService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.mongodb.util.Hash;

import tools.Config;
import tools.JSON2ObjUtil;
import tools.ObjectComparator;
import tools.StringToList;
import tools.StringUtil;
import tools.YXTJSONHelper;
/**
 * UserGroupCourse service实现类
 * @author hjn
 *
 */
@Service
@Component

public class UserGroupCourseService {
	private static final Logger logger=Logger.getLogger(UserGroupCourseService.class);
	
	@Autowired
	private UserGroupCourseRepository userGroupCourseRepository;
	@Autowired
	private UserGroupCourseTemplate userGroupCourseTemplate;
	@Autowired
	private NewCourseService newCourseService;
	@Autowired
	private NewChapterService newChapterService;
	@Autowired
	private LessonService lessonService;
	@Autowired
	private KnowledgeService knowledgeService;
	@Autowired
	private NewGroupCourseService newGroupCourseService;
	@Autowired
	private GroupService groupService;
	@Autowired
	public UserService userService;
	@Autowired
	public FavService favService;
	@Autowired
	public StudyService studyService;
	@Autowired
	public ShareService shareService;
	@Autowired
	public CategoryService categoryService;
	@Autowired
	private UserBuyCourseService userBuyCourseService;
	
	@Autowired
	public PraiseService praiseService;
	
	public UserGroupCourseService(){
		super();
	}
	
	/**
	 * 将用户小组课程收藏学习转成用户列表
	 * @param groupCourses
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<JSONObject> toResponeses(List<Fav> favList ,String userId)throws XueWenServiceException{
		Fav fav = null;
		List<Object>  userIds = new ArrayList<Object>();
		for(int i = 0 ; i < favList.size() ; i++){
			fav = favList.get(i);
			userIds.add(fav.getUserId());
		}
		List<JSONObject> ru = userService.toJsonHelperUserList(userIds,userId);
		return ru;
	}
	/**
	 * 
	 * @param groupCourses
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<GroupCourse> toResponseNewGroupsCourseList(List<GroupCourse> groupCourses)throws XueWenServiceException{
		return null;
	}
	
	/**
	 * 通过用户ID，群组ID，课程ID获取用户课程实例
	 * @author hjn
	 * @param userId
	 * @param groupId
	 * @param courseId
	 * @return
	 * @throws XueWenServiceException
	 */
	public Object one(String userId,String groupCourseId,String groupId,String courseId)throws XueWenServiceException{
		//参数判断
		if(StringUtil.isBlank(userId) || StringUtil.isBlank(groupCourseId) || StringUtil.isBlank(groupId) || StringUtil.isBlank(courseId)){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
		UserGroupCourse ugc=userGroupCourseRepository.findOneUserGroupCourseByUserIdAndGroupCourseId(userId, groupCourseId);
		if(ugc == null ){
			return createRspUserGroupCourse(userId,groupCourseId,groupId,courseId);
		}else{
			return formateUserGroupCourse(ugc);
		}
	}
	
	
	/**
	 * 根据用户ID和groupCourseId判断是否存在
	 * @author hjn
	 * @param userId
	 * @param groupCourseId
	 * @return
	 * @throws XueWenServiceException
	 */
	public boolean isExiseByUserAndGroupCourseId(String userId,String groupCourseId)throws XueWenServiceException{
		if(StringUtil.isBlank(userId) || StringUtil.isBlank(groupCourseId)){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
		return userGroupCourseTemplate.isExiseByUserAndGroupCourseId(userId, groupCourseId);
	}
	
	/**
	 * 群组课程收藏
	 * @param userId
	 * @param groupId
	 * @param courseId
	 * @param groupCourseId
	 * @param appkey
	 * @throws XueWenServiceException
	 */
	public void favGroupCourse(String userId,String groupId,String courseId,String groupCourseId,String appkey)throws XueWenServiceException{
		//参数判断
		if(StringUtil.isBlank(userId) || StringUtil.isBlank(groupId) || StringUtil.isBlank(courseId) || StringUtil.isBlank(groupCourseId) || StringUtil.isBlank(appkey)){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
		
		if(!groupService.isUserInGroupByGroupIdAndUserId(groupId, userId)){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_NOTINGROUP_201,null);
		}
		//判断群组课程是否收藏
	    if(!newGroupCourseService.isGroupCourseFavByUser(groupCourseId, userId)){
	    	//群组课程未收藏,增加群组课程收藏记录
	    	newGroupCourseService.groupCourseFav(groupCourseId, userId, appkey);
		   //群组课程为收藏,增加群组课程收藏数量
	    	newGroupCourseService.increaseFavCount(groupCourseId, 1);
	    }else{
	    	throw new XueWenServiceException(Config.STATUS_201, Config.MSG_COURSEHADFAV_201,null);
	    }
		//判断userGroupCourse记录是否存在如果不存在
		if(!isExiseByUserAndGroupCourseId(userId,groupCourseId)){
			//不存在则新建记录并保存
			UserGroupCourse ugc=createBasicUserGroupCourse(userId, groupCourseId, groupId, courseId);
			ugc.setFaved(true);
			userGroupCourseRepository.save(ugc);
		}else{
			//存在则更新为已收藏
			userGroupCourseTemplate.updateUserGroupCourseFaved(userId, groupCourseId);
		}
		//判断课程池课程是否收藏
	    if(!newCourseService.isCourseFavByUser(courseId, userId)){
		   //课程池课程未收藏，增加课程池课程收藏记录
		   newCourseService.courseFav(courseId, userId, appkey);
		   //课程池课程未收藏，课程收藏数量+1
		   newCourseService.increaseFavCount(courseId, 1);
	    }
	}
	/**
	 * 群组课程学习
	 * @param userId
	 * @param groupId
	 * @param courseId
	 * @param groupCourseId
	 * @param appkey
	 * @throws XueWenServiceException
	 */
	public void studyGroupCourse(String userId,String groupId,String courseId,String groupCourseId,String appkey,String chapterId,String lessonId,int proess,String isStudy)throws XueWenServiceException{
		//参数判断
		if(StringUtil.isBlank(userId) || StringUtil.isBlank(groupId) || StringUtil.isBlank(courseId) 
				|| StringUtil.isBlank(groupCourseId) || StringUtil.isBlank(appkey) || StringUtil.isBlank(chapterId) 
				|| StringUtil.isBlank(lessonId) || StringUtil.isBlank(isStudy)){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
		
		if(!groupService.isUserInGroupByGroupIdAndUserId(groupId, userId)){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_NOTINGROUP_201,null);
		}
		UserGroupCourse ugc=userGroupCourseRepository.findOneUserGroupCourseByUserIdAndGroupCourseId(userId, groupCourseId);
		//判断userGroupCourse记录是否存在如果不存在
		if(ugc == null){
			//不存在则新建记录并保存
			ugc=createBasicUserGroupCourse(userId, groupCourseId, groupId, courseId);
		}
		//更新用户群组课程学习进度标示
		ugc=updateLessonProess(ugc,chapterId,lessonId,proess,isStudy);
		userGroupCourseRepository.save(ugc);
		//判断课程池课程是否收藏
		if(!newCourseService.isCourseStudyByUser(courseId, userId)){
			//课程池课程未收藏，增加课程池课程收藏记录
			newCourseService.courseStudy(courseId, userId, appkey);
			//课程池课程未收藏，课程收藏数量+1
			newCourseService.increaseStudyCount(courseId, 1);
		}
		//判断群组课程是否收藏
		if(!newGroupCourseService.isGroupCourseStudyByUser(groupCourseId, userId)){
			//群组课程未收藏,增加群组课程收藏记录
			newGroupCourseService.groupCourseStudy(groupCourseId, userId, appkey);
			//群组课程为收藏,增加群组课程收藏数量
			newGroupCourseService.increaseStudyCount(groupCourseId, 1);
		}
	}
	
	/**
	 * 更新用户群组课程学习进度标示
	 * @param ugc
	 * @param chapterId
	 * @param lessonId
	 * @param proess
	 * @param isStudy
	 * @return
	 * @throws XueWenServiceException
	 */
	public UserGroupCourse updateLessonProess(UserGroupCourse ugc,String chapterId,String lessonId,int proess,String isStudy)throws XueWenServiceException{
		//参数判断
		if(ugc == null || StringUtil.isBlank(chapterId) || StringUtil.isBlank(lessonId)){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
		for(UserChapter uc:ugc.getUserChapters()){
			if(chapterId.equals(uc.getChapter().toString())){
				for(UserLesson ul:uc.getUserLessones()){
					if(lessonId.equals(ul.getLesson().toString())){
						//课时学习进度记录
						ul.setLastProess(proess);
						if(!ul.isStudyed() && Config.STUTYED.equals(isStudy)){
							//此前此课时为为学完状态
							ul.setStudyed(true);
							//此章节下学完的课时+1
							int lessonStudyedNum= uc.getLessonStudyedNum()+1;
							int allLessonStudyedNum=ugc.getAllLessonStudyedNum()+1;
							uc.setLessonStudyedNum(lessonStudyedNum);
							ugc.setAllLessonStudyedNum(allLessonStudyedNum);
							if(uc.getLessonNum() == lessonStudyedNum){
								//此章节下所有课时都学完
								uc.setStudyed(true);
							}
							if(allLessonStudyedNum == ugc.getAllLessonNum()){
								ugc.setStudyed(true);
							}
						}
					}
				}
			}
		}
		return ugc;
	}
	
	/**
	 * 群组课程分享
	 * @param userId
	 * @param groupId
	 * @param courseId
	 * @param groupCourseId
	 * @param appkey
	 * @param toType
	 * @param toAddr
	 * @throws XueWenServiceException
	 */
	public void shareGroupCourse(String userId,String groupId,String courseId,String groupCourseId,String appkey,String toType,String toAddr)throws XueWenServiceException{
		//参数判断
		if(StringUtil.isBlank(userId) || StringUtil.isBlank(groupId) || StringUtil.isBlank(courseId) || StringUtil.isBlank(groupCourseId) || StringUtil.isBlank(appkey) || StringUtil.isBlank(appkey)){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
		if(!groupService.isUserInGroupByGroupIdAndUserId(groupId, userId)){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_NOTINGROUP_201,null);
		}
		//判断userGroupCourse记录是否存在如果不存在
		if(!isExiseByUserAndGroupCourseId(userId,groupCourseId)){
			//不存在则新建记录并保存
			UserGroupCourse ugc=createBasicUserGroupCourse(userId, groupCourseId, groupId, courseId);
			userGroupCourseRepository.save(ugc);
		}
		//增加课程池课程分享记录
		newCourseService.courseShare(courseId, userId, appkey, toType, toAddr);
		//增加课程池课程分享统计数目
		newCourseService.increaseShareCount(courseId, 1);
		//增加群组课程分享记录
		newGroupCourseService.groupCourseShare(groupCourseId, userId, appkey, toType, toAddr);
		//增加群组课程分享统计数目
		newGroupCourseService.increaseShareCount(groupCourseId, 1);
		
	}
	
//	/**
//	 * 新建UserGroupCourse对象，并保存到数据库
//	 * @param userId
//	 * @param groupCourseId
//	 * @param groupId
//	 * @param courseId
//	 */
//	public void createUserGroupCourseAndSave(String userId,String groupCourseId,String groupId,String courseId){
//		//参数判断
//		if(StringUtil.isBlank(userId) || StringUtil.isBlank(groupId) || StringUtil.isBlank(courseId) || StringUtil.isBlank(groupCourseId) || StringUtil.isBlank(appkey)){
//			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
//		}
//	}
	
	/**
	 * 根据用户Id，群组Id，课程ID组装UserGroupCourse对象(插入数据库中的对象)
	 * @author hjn
	 * @param userId
	 * @param groupId
	 * @param courseId
	 * @return
	 * @throws XueWenServiceException
	 */
	public UserGroupCourse createBasicUserGroupCourse(String userId,String groupCourseId,String groupId,String courseId)throws XueWenServiceException{
		//参数校验
		if(StringUtil.isBlank(userId) || StringUtil.isBlank(groupCourseId) || StringUtil.isBlank(groupId) || StringUtil.isBlank(courseId)){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
		//判断此小组课程是否存在
		if(!newGroupCourseService.isExiseByIdAndGroupIdAndCourseId(groupCourseId,groupId, courseId)){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
		//根据courseId获取课程，取得课程ID，和chapter List
		NewCourse nc=newCourseService.findOneNewCourseByIdRspOnlyIdAndChapters(courseId);
		if(nc == null){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
		UserGroupCourse ugc=new UserGroupCourse(userId, groupId, courseId);
		ugc.setGroupCourseId(groupCourseId);
		//组装UserChapter集合
		ugc.setUserChapters(createBasicUserChapterList(nc.getChapters()));
		//组装此课程所有的课时数量
		ugc.setAllLessonNum(countAllCourseLessonsNum(ugc.getUserChapters()));
		//判断是否
		ugc.setAllPermissions(groupService.isUserInGroupByGroupIdAndUserId(groupId, userId));
		return ugc;
	}
	
	/**
	 * 根据newChapter Id集合 组装UserChapter 对象集合（同userGroupCourse一起插入数据库的对象）
	 * @author hjn
	 * @param newChapter
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<UserChapter> createBasicUserChapterList(List<Object> chapters)throws XueWenServiceException{
		//参数校验
		if(chapters == null || chapters.size() == 0){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
		//获取newChapter 集合
		List<NewChapter> ncs=newChapterService.findChapterListByIdListRspOnlyIdAndLessonIds(chapters);
		//获取UserChpater集合
		return createBasicUserChapterListByNewChapterList(ncs);
	}
	
	/**
	 * 根据newChapter对象集合 组装UserChapter 对象集合（同userGroupCourse一起插入数据库的对象）
	 * @author hjn
	 * @param newChapter
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<UserChapter> createBasicUserChapterListByNewChapterList(List<NewChapter> newChapters)throws XueWenServiceException{
		//参数校验
		if(newChapters == null || newChapters.size() == 0 ) {
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
		
		ObjectComparator coc=new ObjectComparator("order");
		Collections.sort(newChapters, coc);
		
		List<UserChapter> ucs=new ArrayList<UserChapter>();
		//获取所有的课时id集合
		List<String> lids=this.getCourseAllLessonsByAllNewChapter(newChapters);
		//根据lid集合获取
		List<Lesson> ls=this.getLessonsByLessonIds(lids);
		
		for(NewChapter newChapter:newChapters){
			ucs.add(createBasicUserChapter(newChapter));
		}
		return ucs;
	}
	
	/**
	 * 根据newChapter对象 组装UserChapter 对象（同userGroupCourse一起插入数据库的对象）
	 * @author hjn
	 * @param newChapter
	 * @return
	 * @throws XueWenServiceException
	 */
	public UserChapter createBasicUserChapter(NewChapter newChapter)throws XueWenServiceException{
		//参数校验
		if(newChapter == null){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
		//组装UsrChapter对象
		UserChapter uc=new UserChapter();
		uc.setChapter(newChapter.getId());
		uc.setStudyed(false);
		//List<UserLesson> uls=createBasicUserLessonList(newChapter.getLessons());
 		List<UserLesson> uls=createBasicUserLessonList(lessonService.findByIdIn(newChapter.getLessonIds()));
		uc.setLessonNum(uls.size());
		uc.setUserLessones(uls);
		return uc;
	}
	
	/**
	 * 根据用户Id，群组Id，课程ID组装UserGroupCourse对象(返回前端的对象)
	 * @author hjn
	 * @param userId
	 * @param groupId
	 * @param courseId
	 * @return
	 * @throws XueWenServiceException
	 */
	public Object createRspUserGroupCourse(String userId,String groupCourseId,String groupId,String courseId)throws XueWenServiceException{
		//参数校验
		if(StringUtil.isBlank(userId) || StringUtil.isBlank(groupCourseId)  || StringUtil.isBlank(groupId) || StringUtil.isBlank(courseId)){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
		//根据courseId获取课程，取得课程ID，和chapter List
		NewCourse nc=newCourseService.findOneNewCourseByIdBasicInfoIncludeChapter(courseId);
		if(nc == null){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
		UserGroupCourse ugc=new UserGroupCourse();
		ugc.setGroupCourseId(groupCourseId);
		//组装课程信息
		ugc.setCourse(formateCourse(nc));
		ugc.setGroup(groupId);
		ugc.setUserId(userId);
		ugc.setFaved(false);
		ugc.setStudyed(false);
		//组装UserChapter集合
		ugc.setUserChapters(createRspUserChapterList(nc.getChapters()));
		//组装此课程所有的课时数量
		ugc.setAllLessonNum(countAllCourseLessonsNum(ugc.getUserChapters()));
		//判断是否
		ugc.setAllPermissions(groupService.isUserInGroupByGroupIdAndUserId(groupId, userId));
		return formateOneUserGroupCourse(ugc);
	}
	
	/**
	 * 根据newChapter Id集合 组装UserChapter 对象集合（返回前端的对象）
	 * @author hjn
	 * @param newChapter
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<UserChapter> createRspUserChapterList(List<Object> chapters)throws XueWenServiceException{
		//参数校验
		if(chapters == null || chapters.size() == 0){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
		//获取newChapter 集合
		List<NewChapter> ncs=newChapterService.findChapterList(chapters);
		ObjectComparator coc=new ObjectComparator("order");
		Collections.sort(ncs, coc);
		//获取UserChpater集合
		return createRspUserChapterListByNewChapterList(ncs);
	}
	
	/**
	 * 根据newChapter对象集合 组装UserChapter 对象集合（返回前端的对象）
	 * @author hjn
	 * @param newChapter
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<UserChapter> createRspUserChapterListByNewChapterList(List<NewChapter> newChapters)throws XueWenServiceException{
		//参数校验
		if(newChapters == null || newChapters.size() == 0 ) {
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
		List<UserChapter> ucs=new ArrayList<UserChapter>();
		//获取课时id集合
		List<String> lessonIds=getCourseAllLessonsByAllNewChapter(newChapters);
		//获取课程集合
		List<Lesson> ls=this.getLessonsByLessonIds(lessonIds);
		//对lesson进行排序
		ObjectComparator coc=new ObjectComparator("order");
		Collections.sort(ls, coc);
		//获取知识Id集合
		List<Object> kids=this.getKnowledegeIdsByLessons(ls);
		//获取知识集合
		List<Knowledge> ks=this.getKnowledgeByKids(kids);
		
		for(NewChapter newChapter:newChapters){
			//组装UsrChapter对象
			UserChapter uc=new UserChapter();
			//组装前端需要的chapter信息
			uc.setChapter(formateChapter(newChapter));
			uc.setStudyed(false);
			//组装前端需要的userLesson结合
			//List<UserLesson> uls=createRspUserLessonList(newChapter.getLessons());
			List<UserLesson> uls=createRspUserLessonList(newChapter.getLessonIds(),ls,ks);
			uc.setLessonNum(uls.size());
			uc.setUserLessones(uls);
			ucs.add(uc);
		}
		return ucs;
	}
	
	/**
	 * 根据章节下课时的id集合 ,和课程下所有课时集合，以及知识集合，组装此章节下的
	 * @param lids
	 * @param lessons
	 * @param ks
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<UserLesson> createRspUserLessonList(List<String> lids,List<Lesson> lessons ,List<Knowledge> ks)throws XueWenServiceException{
		//参数校验
		if(lessons == null || lessons.size()== 0){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
		List<UserLesson> userLessons=new ArrayList<UserLesson>();
		for(Lesson l:lessons){
			if(lids.contains(l.getId())){
				Knowledge k=null;
				for(Knowledge kld:ks){
					if(l.getKnowledge().toString().equals(kld.getId())){
						k=kld;
					}
				}
				//从Knowledge中取出app播放地址替代课时源地址
				String localUrl="";
				String downloadUrl="";
				if(k != null){
					downloadUrl=k.getCacheUrl();
					List<Citem>  citems=k.getAppItems();
					if(citems != null ){
						for(Citem ci:citems){
								localUrl= ci.getFurl();
						}
					}
				}
			    l.setLocalUrl(localUrl);
				userLessons.add(createRspUserLesson(l,downloadUrl));
			}
		}
		return userLessons;
	}
	
	/**
	 * 根据Lesson对象 组装UserLesson 对象（返回前端的对象）
	 * @author hjn
	 * @param lessonId
	 * @return
	 * @throws XueWenServiceException
	 */
	public UserLesson createRspUserLesson(Lesson lesson,String downloadUrl)throws XueWenServiceException{
		//参数校验
		if(lesson == null){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
		UserLesson ul=new UserLesson();
		//组装前端需要的lesson对象
		ul.setLesson(formateLesson(lesson,downloadUrl));
		ul.setLastProess(0);
		ul.setStudyed(false);
		return  ul;
	}
	
	/**
	 * 根据UserChapter集合 计算课程拥有的课时总数
	 * @param chaptes
	 * @return
	 * @throws XueWenServiceException
	 */
	public int countAllCourseLessonsNum(List<UserChapter> chaptes)throws XueWenServiceException{
		int allCourseLessonsNum=0;
		for(UserChapter uc:chaptes){
			allCourseLessonsNum=allCourseLessonsNum+uc.getLessonNum();
		}
		return allCourseLessonsNum;
	}
	
	/**
	 * 
	 * @param ugc
	 * @return
	 * @throws XueWenServiceException
	 */
	public Object formateOneUserGroupCourse(UserGroupCourse ugc)throws XueWenServiceException{
		NewGroupCourse ngc=newGroupCourseService.getNewGroupCourseCountInfo(ugc.getGroupCourseId());
		if(ngc == null){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_NEWGROUPCOURSENULL_201,null);
		}
		Map<String, Object> addAndModifyMap =new HashMap<String, Object>();
		addAndModifyMap.put("favCount", ngc.getFavCount());
		addAndModifyMap.put("shareCount", ngc.getShareCount());
		addAndModifyMap.put("studyCount", ngc.getStudyCount());
		JSONObject course=(JSONObject)ugc.getCourse();
		List<Object> objs=userBuyCourseService.courseDetailBuyCourseUserList(course.get("id").toString());
		addAndModifyMap.put("isBuy", userBuyCourseService.findOneByUserIdAndCourseId(ugc.getUserId(), course.get("id").toString()));
		addAndModifyMap.put("isOwner", ugc.getUserId().equals(course.get("createUser").toString())?true:false);
		addAndModifyMap.put("buyers", objs);
		//获取课程活动
		JSONArray coupons=newCourseService.getCanUserCouponsByCourseIdAndUserId(course.get("id").toString(),ugc.getUserId());
		if(coupons !=null){
			addAndModifyMap.put("coupons", coupons);
		}
		return YXTJSONHelper.addAndModifyAttrJsonObject(ugc, addAndModifyMap);
	}
	
	/**
	 * 根据UserChapter集合 计算课程已学完的课时总数
	 * @param chaptes
	 * @return
	 * @throws XueWenServiceException
	 */
	public int countAllCourseLessonsStudyedNum(List<UserChapter> chaptes)throws XueWenServiceException{
		int allCourseLessonsStudyedNum=0;
		for(UserChapter uc:chaptes){
			allCourseLessonsStudyedNum=allCourseLessonsStudyedNum+uc.getLessonStudyedNum();
		}
		return allCourseLessonsStudyedNum;
	}
	
	
	
	/**
	 * 依据课程的章节列表，获取课时id 集合
	 * @param newChapters
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<String> getCourseAllLessonsByAllNewChapter(List<NewChapter> newChapters)throws XueWenServiceException{
		List<String> lessonIds=new ArrayList<String>();
		if(newChapters !=null){
			for(NewChapter newChapter:newChapters){
				lessonIds.addAll(newChapter.getLessonIds());
			}
		}
		return lessonIds;
	}
	/**
	 * 依据课时ID集合获取
	 * @param lessonIds
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<Lesson> getLessonsByLessonIds(List<String> lessonIds)throws XueWenServiceException{
		return lessonService.findByIdIn(lessonIds);
	}
	/**
	 * 根据课时集合列表，获取所有知识的id集合
	 * @param lessons
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<Object> getKnowledegeIdsByLessons(List<Lesson> lessons)throws XueWenServiceException{
		List<Object> kids=new ArrayList<Object>();
		if(lessons !=null){
			for(Lesson l:lessons){
				kids.add(l.getKnowledge());
			}
		}
		return kids;
	}
	/**
	 * 根据知识id集合，获取相应的知识集合
	 * 
	 * @param kids
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<Knowledge> getKnowledgeByKids(List<Object> kids)throws XueWenServiceException{
		return knowledgeService.findByIdRspAppItemsAndDownloadUlr(kids);
	}

	
	/**
	 * 根据lesson List 组装UserLesson 对象集合（同userGroupCourse一起插入数据库的对象）
	 * @author hjn
	 * @param lessonId
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<UserLesson> createBasicUserLessonList(List<Lesson> lessons )throws XueWenServiceException{
		//参数校验
		if(lessons == null || lessons.size()== 0){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
		List<UserLesson> userLessons=new ArrayList<UserLesson>();
		ObjectComparator coc=new ObjectComparator("order");
		Collections.sort(lessons, coc);
		for(Lesson lesson:lessons){
			userLessons.add(createBasicUserLesson(lesson.getId()));
		}
		return userLessons;
	}
	
	
	/**
	 * 根据lessonId 组装UserLesson 对象（同userGroupCourse一起插入数据库的对象）
	 * @author hjn
	 * @param lessonId
	 * @return
	 * @throws XueWenServiceException
	 */
	public UserLesson createBasicUserLesson(String lessonId)throws XueWenServiceException{
		//参数校验
		if(StringUtil.isBlank(lessonId)){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
		return new UserLesson(lessonId);
	}
	
	/**
	 * 格式化UserGroupCourse 返回前端需要的对象
	 * @author hjn
	 * @param userGroupCourse
	 * @return
	 * @throws XueWenServiceException
	 */
	public Object formateUserGroupCourse(UserGroupCourse userGroupCourse)throws XueWenServiceException{
		//参数校验
		if(userGroupCourse == null){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
		NewCourse nc=newCourseService.findOneNewCourseByIdBasicInfo(userGroupCourse.getCourse().toString());
		//组装课程基本信息
		userGroupCourse.setCourse(formateCourse(nc));
		//格式化userChapter集合
		userGroupCourse.setUserChapters(formateUserChapterList(userGroupCourse.getUserChapters()));
		//判断用户是否有全部权限
		userGroupCourse.setAllPermissions(groupService.isUserInGroupByGroupIdAndUserId(userGroupCourse.getGroup().toString(),userGroupCourse.getUserId()));
		return formateOneUserGroupCourse(userGroupCourse);
	}
	
	/**
	 * 格式化newCourse对象，只包含返回前端需要属性
	 * @author hjn
	 * @param lesson
	 * @return
	 * @throws XueWenServiceException
	 */
	public JSONObject formateCourse(NewCourse newCourse)throws XueWenServiceException{
		//参数校验
		if(newCourse == null){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
		//去掉无需返回前端的属性,只包含以下属性
		String[] include = {"id","title","intro","tags","logoUrl","tagNames","price","createUser","createUserName","userLogo",
				"pricemodel","favProp","buyCount","postCount"};
		Map<String, Object> addAndModifyMap=new HashMap<String, Object>();
		//将分类ID置换为分类描述
		//获取一级分类中文描述
		if(!StringUtil.isBlank(newCourse.getCategoryId())){
			String categoryName=categoryService.getCategoryNameById(newCourse.getCategoryId());
			addAndModifyMap.put("categoryId", categoryName);
		}
		if(!StringUtil.isBlank(newCourse.getChildCategoryId())){
			String childCategoryName=categoryService.getCategoryNameById(newCourse.getChildCategoryId());
			addAndModifyMap.put("childCategoryId", childCategoryName);
		}
		return  YXTJSONHelper.getInObjectAttrJsonObject(newCourse,addAndModifyMap ,include);
	} 

	
	/**
	 * 格式化UserChapter 对象集合，包含前端必须信息
	 * @author hjn
	 * @param userChapter
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<UserChapter> formateUserChapterList(List<UserChapter> userChapters)throws XueWenServiceException{
		//参数校验
		if(userChapters == null || userChapters.size()==0){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
		//获取章节id集合
		List<Object> cids=this.getChapterIdsByUserChapters(userChapters);
		//获取章节数据集合
		List<NewChapter> ncs=this.getChaptersByIds(cids);
		//根据章节获取所有的课时id集合
		List<String> lids=this.getCourseAllLessonsByAllNewChapter(ncs);
		//根据课时id集合获取课时集合
		List<Lesson> ls=this.getLessonsByLessonIds(lids);
		//根据课时集合获取知识id集合
		List<Object> kids=this.getKnowledegeIdsByLessons(ls);
		//根据知识id集合获取知识集合
		List<Knowledge> ks=this.getKnowledgeByKids(kids);
		
		for(UserChapter userChapter:userChapters){
			formateUserChapter(userChapter,ncs,ls,ks);
		}
		return userChapters;
	}
	/**
	 * 根据 userChapter集合获取课程下所有chapterId
	 * @param userChapters
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<Object> getChapterIdsByUserChapters(List<UserChapter> userChapters)throws XueWenServiceException{
		List<Object> cs=new ArrayList<Object>();
		for(UserChapter uc:userChapters){
			cs.add(uc.getChapter());
		}
		return cs;
	}
	/**
	 * 根据章节的id集合，获取章节集合
	 * @param cids
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<NewChapter> getChaptersByIds(List<Object> cids)throws XueWenServiceException{
		return newChapterService.findChapterList(cids);
	}
	/**
	 * 格式化UserChapter ,填充相关数据
	 * @param userChapter 数据库存储的userChpater数据
	 * @param cs  章节列表
	 * @param ls 课时列表
	 * @param ks 知识列表
	 * @throws XueWenServiceException
	 */
	public void formateUserChapter(UserChapter userChapter,List<NewChapter> cs,List<Lesson> ls,List<Knowledge> ks)throws XueWenServiceException{
		//参数校验
		if(userChapter == null){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
		
		NewChapter nc=null;
		for(NewChapter c:cs){
			if(userChapter.getChapter().toString().equals(c.getId())){
				nc=c;
			}
		}
		//组装chapter信息
		userChapter.setChapter(formateChapter(nc));
		//格式化UserLesson List 信息
		fromateUserLessonList(userChapter.getUserLessones(),ls,ks);
	}
	/**
	 * 格式化 userLesson 集合， 返回前端显示
	 * @param userLessons 数据库存储的UserLesson集合
	 * @param ls 课时集合
	 * @param ks 知识集合
	 * @return
	 * @throws XueWenServiceException
	 */
	public void fromateUserLessonList(List<UserLesson> userLessons,List<Lesson> ls,List<Knowledge> ks)throws XueWenServiceException{
		//参数校验
		if(userLessons == null || userLessons.size()== 0){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
		for(UserLesson userLesson:userLessons){
			formateUserLesson(userLesson ,ls,ks);
		}
	}
	
	/**
	 * 格式化 userLesson 返回前端显示
	 * @param userLesson 数据库存储的数据
	 * @param ls 课时集合
	 * @param ks 知识集合
	 * @return
	 * @throws XueWenServiceException
	 */
	public void formateUserLesson(UserLesson userLesson,List<Lesson> ls,List<Knowledge> ks)throws XueWenServiceException{
		//参数校验
		if(userLesson == null){
			logger.error("=======用户课时格式化失败  formateUserLesson 方法  userLesson 为空");
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
		Lesson lesson=null;
		for(Lesson l:ls){
			if(userLesson.getLesson().toString().equals(l.getId())){
				lesson=l;
			}
		}
		if(lesson == null){
			logger.error("=======用户课时格式化失败  formateUserLesson 方法  lesson 查询不到:"+userLesson.getLesson().toString());
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}	
		//从Knowledge中取出app播放地址替代课时源地址
		Knowledge k=null;
		for(Knowledge kld:ks){
			if(lesson.getKnowledge().toString().equals(kld.getId())){
				k=kld;
			}
		}
		if(k == null){
			logger.error("=======用户课时格式化失败  formateUserLesson 方法  Knowledge 查询不到:"+lesson.getKnowledge().toString());
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}	
		String localUrl="";
		String downloadUrl="";
		if(k != null){
			downloadUrl=k.getCacheUrl();
			List<Citem>  citems=k.getAppItems();
			if(citems != null ){
				for(Citem ci:citems){
						localUrl= ci.getFurl();
				}
			}
		}
	    lesson.setLocalUrl(localUrl);
		//组装Lesson基本信息
		userLesson.setLesson(formateLesson(lesson,downloadUrl));
	}
	
	/**
	 * 格式化newChapter对象，只包含返回前端属性,去掉lesson属性
	 * @author hjn
	 * @param lesson
	 * @return
	 * @throws XueWenServiceException
	 */
	public JSONObject formateChapter(NewChapter newChapter)throws XueWenServiceException{
		//参数校验
		if(newChapter == null){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
		//去掉无需返回前端的属性,只包含以下属性
		String[] exclude = {"lessons","newLessons","lessonIds"};
		return  YXTJSONHelper.excludeAttrJsonObject(newChapter, exclude);
	} 
	
	/**
	 * 格式化Group对象，只包含返回前端属性,包括Id,groupName,groupNumber
	 * @author hjn
	 * @param lesson
	 * @return
	 * @throws XueWenServiceException
	 */
	public JSONObject formateGroup(XueWenGroup group)throws XueWenServiceException{
		//参数校验
		if(group == null){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
		//去掉无需返回前端的属性,只包含以下属性
		String[] include = {"id","groupName","groupNumber"};
		return  YXTJSONHelper.includeAttrJsonObject(group, include);
	} 
	
	
	/**
	 * 格式化lesson对象，只包含返回前端属性
	 * @author hjn
	 * @param lesson
	 * @return
	 * @throws XueWenServiceException
	 */
	public JSONObject formateLesson(Lesson lesson,String downloadUrl)throws XueWenServiceException{
		//参数校验
		if(lesson == null){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
		//去掉无需返回前端的属性,只包含以下属性
		String[] include = {"id","title","length","timer","order","localUrl","type","isbuy"};
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("downloadUrl",downloadUrl);
		return  YXTJSONHelper.getInObjectAttrJsonObject(lesson,map, include);
	}
	/**
	 * 获得小组课程收藏人列表
	 * @param id
	 * @param groupCourseId
	 * @param domain
	 * @throws XueWenServiceException
	 */
	public Page<Fav> getUserFavList(String userId, String groupCourseId, Pageable pageable)throws XueWenServiceException {
		Page<Fav> favPage = favService.getUserFavList(userId,groupCourseId,Config.YXTDOMAIN,pageable);
		return favPage;
		
	}
	/**
	 * 统计学完的课程数目
	 * @param userId
	 * @return
	 * @throws XueWenServiceException
	 */
	public int countFavStudyed(String userId)throws XueWenServiceException{
		return userGroupCourseRepository.countByUserIdAndFavedAndStudyed(userId, true, true);
	}
	/**
	 * 统计正在学学的课程数目
	 * @param userId
	 * @return
	 * @throws XueWenServiceException
	 */
	public int countFavStudying(String userId)throws XueWenServiceException{
		return userGroupCourseRepository.countByUserIdAndFavedAndStudyed(userId, true, false);
	}
	
	/**
	 * 用户我的收藏页面显示所需的用户已学完和未学完课程的统计
	 * @param user
	 * @return
	 * @throws XueWenServiceException
	 */
	public JSONObject userFavStudyCountNum(User user)throws XueWenServiceException{
		int countFavStudyed=countFavStudyed(user.getId());
		int countFavStudying=countFavStudying(user.getId());
		 //去掉无需返回前端的属性,只包含以下属性
		String[] include = {"id"};
		//添加的属性
		Map<String,Object> addAndModifyMap=new HashMap<String, Object>();
		addAndModifyMap.put("countFavStudyed", countFavStudyed);
		addAndModifyMap.put("countFavStudying", countFavStudying);
		return  YXTJSONHelper.getInObjectAttrJsonObject(user, addAndModifyMap, include);
	}
	
	/**
	 * 用户收藏且学习完成群组课程列表
	 * @param userId
	 * @param pageable
	 * @return
	 * @throws XueWenServiceException
	 */
	public Page<UserGroupCourse> findUserFavStudyed(String userId,Pageable pageable)throws XueWenServiceException{
		Page<UserGroupCourse> studyeds=userGroupCourseRepository.findByUserIdAndFavedAndStudyed(userId, true, true, pageable);
		if(studyeds == null || studyeds.getContent() == null || studyeds.getContent().size()==0){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_NOFAVSTUDYED_201,null);
		}
		return studyeds;
	}
	/**
	 * 用户收藏且学习完成群组课程列表
	 * @param userId
	 * @param pageable
	 * @return
	 * @throws XueWenServiceException
	 */
	public Page<UserGroupCourse> findUserFavStudying(String userId,Pageable pageable)throws XueWenServiceException{
		Page<UserGroupCourse> studyings=userGroupCourseRepository.findByUserIdAndFavedAndStudyed(userId, true, false, pageable);
		if(studyings == null || studyings.getContent() == null || studyings.getContent().size()==0){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_NOFAVSTUDYING_201,null);
		}
		return studyings;
	}
	
	
	/**
	 * 格式化UserGroupCourse 返回用户收藏或者学习列表的对象
	 * @author hjn
	 * @param userGroupCourse
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<JSONObject> formateUserGroupCourseRspUserFavOrStudyList(List<UserGroupCourse> userGroupCourses)throws XueWenServiceException{
		//参数校验
		if(userGroupCourses  == null  || userGroupCourses.size() == 0){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
		List<JSONObject> userGroupCourseList=new ArrayList<JSONObject>();
		for(UserGroupCourse userGroupCourse :userGroupCourses){
			userGroupCourseList.add(formateUserGroupCourseRspUserFavOrStudy(userGroupCourse));
		}
		return userGroupCourseList;
	}
	
	/**
	 * 格式化UserGroupCourse 返回用户收藏或者学习列表的对象
	 * @author hjn
	 * @param userGroupCourse
	 * @return
	 * @throws XueWenServiceException
	 */
	public Map<String, Object> toNewCourseForFav(List<Fav> fav,String userId) throws XueWenServiceException{
		Map<String, Object> res = new HashMap<String, Object>();
		//Page<Fav> favs = favService.findByUserId(userId, pageable);
		List<JSONObject> jObjects = new ArrayList<JSONObject>();
		for(int i = 0 ; i < fav.size(); i++){
			Map<String, Object> map = new HashMap<String, Object>();
			//User user=userService.findOne(fav.get(i).getUserId().toString());
			NewCourse newCourse = newCourseService.findOne(fav.get(i).getSourceId());
			if (newCourse != null) {
				map.put("itemId", newCourse.getId());
				map.put("title", newCourse.getTitle());
				map.put("intro", newCourse.getIntro());
				JSONObject obj = newCourseService.getCourseJson(newCourse, "");
				map.put("groupId", (String) obj.get("groupId"));
				map.put("groupName", (String) obj.get("groupName"));
				map.put("praiseCount", newCourse.getPraiseCount());
				
				map.put("coverLogoUrl", newCourse.getLogoUrl());
				map.put("searchType", Config.TYPE_COURSE_GROUP);
				map.put("groupLogoUrl", (String)obj.get("groupLogoUrl"));//增加群图标
				map.put("ctime", fav.get(i).getCtime());//增加收藏时间
				map.put("groupCourseId", obj.get("groupLogoUrl"));//群组课程id
				map.put("favCount", newCourse.getFavCount());//收藏数量
				
			}
			else{
				NewGroupCourse newGroupCourse = newGroupCourseService.findOneByid(fav.get(i).getSourceId());
				if(newGroupCourse!=null){
					NewCourse course = newCourseService.findOne(newGroupCourse.getCourse().toString());
					map.put("itemId", newGroupCourse.getCourse().toString());
					map.put("title", course.getTitle());
					map.put("intro", course.getIntro());
					//JSONObject obj = newCourseService.getCourseJson(newCourse, "");
					map.put("groupId", newGroupCourse.getGroup());
					map.put("groupName",newGroupCourse.getGroupName());
					map.put("praiseCount", course.getPraiseCount());
					
					map.put("coverLogoUrl", course.getLogoUrl());
					map.put("searchType", Config.TYPE_COURSE_GROUP);
					map.put("groupLogoUrl", newGroupCourse.getLogoUrl());//增加群图标
					map.put("ctime", fav.get(i).getCtime());//增加收藏时间
					map.put("groupCourseId", newGroupCourse.getId());//群组课程id
					map.put("favCount", newGroupCourse.getFavCount());//收藏数量
				}
			}
			
			jObjects.add(YXTJSONHelper.addAndModifyAttrJsonObject(fav.get(i), map));
		}
		//res.put("page", favs);
		res.put("items", jObjects);
		return res;
	}
	
	/**
	 * 格式化UserGroupCourse 返回用户收藏或者学习列表的对象
	 * @author hjn
	 * @param userGroupCourse
	 * @return
	 * @throws XueWenServiceException
	 */
	public JSONObject formateUserGroupCourseRspUserFavOrStudy(UserGroupCourse userGroupCourse)throws XueWenServiceException{
		//参数校验
		if(userGroupCourse == null){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
		NewCourse nc=newCourseService.findOneNewCourseByIdBasicInfo(userGroupCourse.getCourse().toString());
		logger.info("nc object"+nc+"courseId ："+userGroupCourse.getCourse().toString());
		//组装课程基本信息
		userGroupCourse.setCourse(formateCourse(nc));
		//组装群组信息
		XueWenGroup group=groupService.findOneXuewenGroupOnlyGroupNameAndGroupNum(userGroupCourse.getGroup().toString());
		logger.info("group obj:"+group +"===groupID:"+userGroupCourse.getGroup().toString());
		if(group!=null){
		userGroupCourse.setGroup(formateGroup(group));
		}
//		}else{
//			userGroupCourse.setGroup(null);
//		}
		
		return formateUserGroupCourseExcludeChapters(userGroupCourse);
	}
	
	
	/**
	 * 格式化UserGroupCourse对象，去掉userChapter属性
	 * @author hjn
	 * @param lesson
	 * @return
	 * @throws XueWenServiceException
	 */
	public JSONObject formateUserGroupCourseExcludeChapters(UserGroupCourse userGroupCourse)throws XueWenServiceException{
		//参数校验
		if(userGroupCourse == null){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
		//去掉无需返回前端的属性,只包含以下属性
		NewGroupCourse groupCourse=newGroupCourseService.findOneRspStudyCountAndFavCount(userGroupCourse.getGroupCourseId());
		String[] exclude = {"userChapters"};
		Map<String,Object> addAndModifyMap=new HashMap<String, Object>();
		addAndModifyMap.put("favCount", groupCourse.getFavCount());
		addAndModifyMap.put("studyCount", groupCourse.getStudyCount());
		addAndModifyMap.put("shareCount", groupCourse.getShareCount());
		return  YXTJSONHelper.getExObjectAttrJsonObject(userGroupCourse,addAndModifyMap,exclude);
	} 
	
	
	
	/**
	 * 获得小组课程学习人列表
	 * @param id
	 * @param groupCourseId
	 * @param domain
	 * @throws XueWenServiceException
	 */
	public Page<Study> getUserStudyedList(String userId, String groupCourseId, Pageable pageable)throws XueWenServiceException {
		Page<Study> favPage = studyService.getUserStudyedList(userId,groupCourseId,Config.YXTDOMAIN,pageable);
		return favPage;
		
	}
	/**
	 * 将用户小组课程收藏学习转成用户列表
	 * @param groupCourses
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<JSONObject> toResponesesStudy(List<Study> studyList ,String userId)throws XueWenServiceException{
		Study study = null;
		List<Object>  userIds = new ArrayList<Object>();
		for(int i = 0 ; i < studyList.size() ; i++){
			study = studyList.get(i);
			userIds.add(study.getUserId());
		}
		List<JSONObject> ru = userService.toJsonHelperUserList(userIds,userId);
		return ru;
	}
	/**
	 * 获得某一用户学习课程的数量
	 * @param userId
	 * @return
	 * @throws XueWenServiceException
	 */
	public int getStudyedCountByUser(String userId) throws XueWenServiceException{
		return userGroupCourseRepository.countByUserId(userId);
	}
	/**
	 * 获得某一用户课程学习列表
	 * @param userId
	 * @param pageable
	 * @return
	 * @throws XueWenServiceException
	 */
	public Page<UserGroupCourse> userCourseList(String userId,Pageable pageable)throws XueWenServiceException{
		if(StringUtil.isBlank(userId)){
			throw new XueWenServiceException(Config.STATUS_201,Config.MSG_NODATA_201, null);
		}
		Page<UserGroupCourse> userCourseLists=userGroupCourseRepository.findByUserId(userId, pageable);
		if(userCourseLists == null || userCourseLists.getContent() == null || userCourseLists.getContent().size()==0){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_NOSTUDYCOURSE_201,null);
		}
		return userCourseLists;
	}
	/**
	 * 将usergroupcourse转换成前端使用
	 * @param userGroupCourseList
	 * @param id
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<Object> toResponesesUserGroupCourse(List<UserGroupCourse> userGroupCourseList,String id) throws XueWenServiceException{
		UserGroupCourse userGroupCourse = null;
		List<Object> objs=new ArrayList<Object>();
		if(userGroupCourseList!=null){
			for(int i= 0; i < userGroupCourseList.size(); i++){
				userGroupCourse = userGroupCourseList.get(i);
				Object obj = newGroupCourseService.formateGroupCourse(userGroupCourse.getGroupCourseId());
				objs.add(obj);
			}
		}
		return objs;
	}
	
	/**
	 * 根据groupCourseId 列表 删除所有用户群组课程记录
	 * @param groupCourseIds
	 * @throws XueWenServiceException
	 */
	public void deleteByGroupCourseIds(List<Object> groupCourseIds)throws XueWenServiceException{
		userGroupCourseTemplate.deleteByGroupCourseIds(groupCourseIds);
	}
	/**
	 * 根据groupCourseId 列表 删除所有用户群组课程记录
	 * @param groupCourseIds
	 * @throws XueWenServiceException
	 */
	public void deleteByGroupId(String groupId)throws XueWenServiceException{
		userGroupCourseTemplate.deleteByGroupId(groupId);
	}
	
	/**
	 * 根据用户ID和groupCourseId 列表 删除所有用户群组课程记录
	 * @param userId
	 * @param groupCourseId
	 * @throws XueWenServiceException
	 */
	public void deleteByUserIdAndGroupCourseId(String userId,List<Object> groupCourseIds)throws XueWenServiceException{
		userGroupCourseTemplate.deleteByUserIdAndGroupCourseIds(userId, groupCourseIds);
	}
	
	/**
	 * 根据用户ID和群组课程ID集合删除记录
	 * @param userId
	 * @param groupCourseId
	 * @throws XueWenServiceException
	 */
	public void deleteUserGroupListByUserIdAndGroupCourseIds(String userId,String groupCourseId)throws XueWenServiceException{
		//参数判断
		if(StringUtil.isBlank(userId) || StringUtil.isBlank(groupCourseId) ){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
		//前端传值的courseID JSON数组转换为Object对象
		List<Object> groupCourseIds=StringToList.tranfer(JSON2ObjUtil.getArrayFromString(groupCourseId));
		//根据用户ID和群组课程ID集合删除记录
		deleteByUserIdAndGroupCourseId(userId,groupCourseIds);
		//删除用户的收藏记录
		favService.deleteByUserIdAndSourceIds(userId, groupCourseIds);
		//删除用户的分享记录
		shareService.deleteByUserIdAndSourceIds(userId, groupCourseIds);
		//删除用户的学习记录
		studyService.deleteByUserIdAndSourceIds(userId, groupCourseIds);
	}
	
	/**
	 * 根据课程ID删除用户群组课程
	 * @throws XueWenServiceException
	 */
	public void deleteUserGroupCourseByCourseId(String courseId)throws XueWenServiceException{
		userGroupCourseTemplate.deleteByCourseId(courseId);
	}
	/**
	 * 合并用户数据，从fromUser 的数据更改到  toUser
	 * @param fromUserId
	 * @param toUserId
	 * @throws XueWenServiceException
	 */
	public void mergeUserGroupCourse(String fromUserId,String toUserId)throws XueWenServiceException{
		List<UserGroupCourse> ugcs=userGroupCourseRepository.findByUserId(fromUserId);
		if(ugcs !=null && ugcs.size()>0){
			for(UserGroupCourse ugc:ugcs){
				if(!isExiseByUserAndGroupCourseId(toUserId,ugc.getGroupCourseId())){
					ugc.setUserId(toUserId);
				}
			}
			userGroupCourseRepository.save(ugcs);
		}
	}
	
	/**
	 * 用户收藏群组课程列表(不区分是否学完和正在学)
	 * @param userId
	 * @param pageable
	 * @return
	 * @throws XueWenServiceException
	 */
	public Page<UserGroupCourse> findUserFav(String userId,Pageable pageable)throws XueWenServiceException{
		Page<UserGroupCourse> studyings=userGroupCourseRepository.findByUserIdAndFaved(userId, true, pageable);
		if(studyings == null || studyings.getContent() == null || studyings.getContent().size()==0){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_NOFAV_201,null);
		}
		return studyings;
	}
	
	/**
	 * 获得我的干货收藏列表
	 * @param userId
	 * @param appkey
	 * @param type
	 * @param pageable
	 * @return
	 * @throws XueWenServiceException
	 */
	public Page<Fav> getMyCollect(String userId,Pageable pageable)throws XueWenServiceException{
//		String type = Config.TYPE_DRYCARGO_GROUP;
//		if(1==dryFlag){
//			type = Config.TYPE_XUANYE_GROUP;
//		}
		return favService.findMyCourseCollect(userId,pageable);
	}
	
//	/**
//	 * 用户收藏群组课程列表(不区分是否学完和正在学)
//	 * @param userId
//	 * @param pageable
//	 * @return
//	 * @throws XueWenServiceException
//	 */
//	public Page<Fav> courseNewFav(String userId,Pageable pageable)throws XueWenServiceException{
//		Page<UserGroupCourse> studyings=userGroupCourseRepository.findByUserIdAndFaved(userId, true, pageable);
//		if(studyings == null || studyings.getContent() == null || studyings.getContent().size()==0){
//			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_NOFAV_201,null);
//		}
//		return studyings;
//	}
	
	
	
	/**
	 * 课程群组赞
	 * @param user
	 * @param dryCargoId
	 * @param groupId
	 * @return
	 * @throws XueWenServiceException
	 */
	public NewGroupCourse addParise(User user,String newGroupCourseId,String groupId,String appKey)throws XueWenServiceException{
		
		if(!groupService.isUserInGroupByGroupIdAndUserId(groupId, user.getId())){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_NOTINGROUP_201,null);
		}
		NewGroupCourse newGrouoCourse = newGroupCourseService.findOneByid(newGroupCourseId);

		if(newGrouoCourse==null){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_201,null);
		}
		
		praiseService.addPraise(user,Config.YXTDOMAIN,appKey,newGroupCourseId,Config.TYPE_COURSE_GROUP);
		newGrouoCourse.setLikeCount(newGrouoCourse.getLikeCount()+1);
		return newGroupCourseService.saveCourse(newGrouoCourse);//保存小组课程赞数量
	}
	
}

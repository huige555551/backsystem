package operation.service.course;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;
import operation.exception.XueWenServiceException;
import operation.pojo.course.Course;
import operation.pojo.course.UserCourse;
import operation.pojo.user.UserCourseChapter;
import operation.repo.course.UserCourseRepository;
import operation.service.skill.SkillCourseService;
import operation.service.user.UserCourseChapterService;
import operation.service.user.UserSkillsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import tools.Config;
import tools.MathTools;
import tools.StringUtil;

@Service
@Component

public class UserCourseService {

	@Autowired
	public UserCourseRepository userCourseRepository;
	
	@Autowired
	public CourseService courseService;
	
	@Autowired
	public UserCourseChapterService userCourseChapterService;
	
	@Autowired
	public SkillCourseService  skillCourseService;
	
	@Autowired
	public UserSkillsService  userSkillsService;
	
	
	/**
	 * 将课程加入用户收藏列表
	 * @param courseId
	 * @param userId
	 * @throws XueWenServiceException
	 */
	public void addFav(String courseId,String userId,String fromGroupId)throws XueWenServiceException{
		UserCourse uc=userCourseRepository.findByUserAndCourse(userId, courseId);
		if(uc == null){
			uc=new UserCourse(userId,courseId);
			courseService.countOperation(userId, courseId,"fav");
		}else{
			if(uc.getIsFav()==0){
				courseService.countOperation(userId, courseId,"fav");
			}
			else{
				if(StringUtil.isBlank(fromGroupId)){
				throw new XueWenServiceException(Config.STATUS_201,
						Config.MSG_FAV_201, null);
				}
			}
			uc.setUtime(System.currentTimeMillis());
		}
		uc.setSkillId(skillCourseService.findSkillIdListByCourseId(courseId));
		uc.setIsFav(1);
		userCourseRepository.save(uc);
		
	}
	
	/**
	 * 将课程新增入用户学习列表
	 * @param courseId
	 * @param userId
	 * @throws XueWenServiceException
	 */
	public void addStudy(String courseId,String userId,String timer,String proess)throws XueWenServiceException{
//		UserCourse uc=userCourseRepository.findByUserAndCourse(userId, courseId);
//		long time=System.currentTimeMillis();
//		if(uc == null){
//			uc=new UserCourse(userId,courseId);
//			
//			//记录初始学习时间
//			Map<Long, Float> proessids=new LinkedHashMap<Long, Float>();
//			float pro=0;
//			proessids.put(time,pro);
//			uc.setMaxProessid(pro);
//			uc.setProessid(proessids);
//			courseService.countOperation(userId, courseId,"study");
//		}else{
//			if(uc.getIsStudy()==0){
//				courseService.countOperation(userId, courseId,"study");
//			}
//			Map<Long,Float> pro = uc.getProessid();
//			if(pro == null){
//				pro = new LinkedHashMap<Long,Float>();
//			}
//			float f=Float.parseFloat(proess);
//			pro.put(Long.parseLong(timer),f);
//			uc.setProessid(pro);
//			if(f>uc.getMaxProessid()){
//				uc.setMaxProessid(f);
//			}
//			uc.setUtime(time);
//		}
//		uc.setIsStudy(1);
//		userCourseRepository.save(uc);
//		UserCourse uc=userCourseRepository.findByUserAndCourse(userId, courseId);
//		long time=System.currentTimeMillis();
//		if(uc == null){
//			uc=new UserCourse(userId,courseId);
//			
//			//记录初始学习时间
//			Map<Long, Float> proessids=new LinkedHashMap<Long, Float>();
//			float pro=0;
//			proessids.put(time,pro);
////			uc.setMaxProessid(pro);
////			uc.setProessid(proessids);
//			courseService.countOperation(userId, courseId,"study");
//		}else{
//			if(uc.getIsStudy()==0){
//				courseService.countOperation(userId, courseId,"study");
//			}
//			Map<Long,Float> pro = uc.getProessid();
//			if(pro == null){
//				pro = new LinkedHashMap<Long,Float>();
//			}
//			float f=Float.parseFloat(proess);
//			pro.put(Long.parseLong(timer),f);
//			uc.setProessid(pro);
//			if(f>uc.getMaxProessid()){
//				uc.setMaxProessid(f);
//			}
//			uc.setUtime(time);
//		}
//		uc.setIsStudy(1);
//		userCourseRepository.save(uc);
		
	}
	
	
	/**
	 * 将课程新增入用户学习列表
	 * @param courseId
	 * @param userId
	 * @throws XueWenServiceException
	 */
	public void addStudyNew(String courseId,String userId,String chapterId,String jsonStr)throws XueWenServiceException{
		//用户课程
		UserCourse uc=userCourseRepository.findByUserAndCourse(userId, courseId);
		long time=System.currentTimeMillis();
		if(uc == null){
			uc=new UserCourse(userId,courseId);
			
			courseService.countOperation(userId, courseId,"study");
			//新增用户课程学习操作
		}else{
			if(uc.getIsStudy()==0){
				courseService.countOperation(userId, courseId,"study");
			}
		}
		uc.setSkillId(skillCourseService.findSkillIdListByCourseId(courseId));
		uc.setUtime(time);
		uc.setIsStudy(1);
		//保存章节学习进度
		userCourseChapterService.saveChapterStudy( userId, courseId, chapterId,  jsonStr,time);
		//计算该课程的学习进度
		int process = this.getCourseProcess(userId, courseId);
		uc.setProessid(process);
		uc.setChapteNum(courseService.findCourse(courseId).getChapters().size());
		if(uc.getProessid()==uc.getChapteNum()){
			uc.setIsStudyed(1);
		}else{
			uc.setIsStudyed(0);
		}
		uc=userCourseRepository.save(uc);
		userSkillsService.updateUserSkillProessid(userId, uc.getSkillId());
		
	}
	
	
	/**
	 * 计算课程的学习进度
	 * @param userId
	 * @param courseId
	 * @return
	 */
//	public double getCourseProcess(String userId,String courseId)throws XueWenServiceException{
//		List<UserCourseChapter> userCourseChapter = userCourseChapterService.findChapterByCourse(userId, courseId);
//		double processSum = 0;
//		double process = 0;
//		if(null == userCourseChapter){
//			return 0;
//		}else{
//			for(int i = 0 ; i < userCourseChapter.size(); i++){
//				processSum =MathTools.add(processSum, userCourseChapter.get(i).getMaxProessid()) ;
//			}
//			process = MathTools.div(processSum, courseService.findCourse(courseId).getChapters().size(), 2);
//		}
//		
//		return process;
//		
//	}
	public int getCourseProcess(String userId,String courseId)throws XueWenServiceException{
		List<UserCourseChapter> userCourseChapters = userCourseChapterService.findChapterByCourse(userId, courseId);
		int processSum = 0;
		if(null == userCourseChapters){
			return 0;
		}else{
			for(UserCourseChapter ucc:userCourseChapters){
				if(MathTools.compareToOne(ucc.getMaxProessid())){
					processSum=processSum+1;
				}
			}
		}
		
		return processSum;
		
	}
	/**
	 * 解析客户端传递的学习进度
	 * @param courseId
	 * @param userId
	 * @param timer
	 * @param proess
	 * @throws XueWenServiceException
	 */
	public void studyProcess(String courseId,String userId,String jsonStr)throws XueWenServiceException{
		
		Map<String,Integer> map =(Map<String,Integer>)JSONObject.toBean(JSONObject.fromObject(jsonStr), Map.class);
		Set<Map.Entry<String, Integer>> set = map.entrySet();
		 for (Iterator<Map.Entry<String, Integer>> it = set.iterator(); it.hasNext();) {
	            Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) it.next();
	            System.out.println(entry.getKey() + "--->" + entry.getValue());
	            this.addStudy(courseId, userId, entry.getKey(), entry.getValue().toString());
	        }
	}
	
	
	/**
	 * 查询我的收藏
	 * @param courseId
	 * @param userId
	 * @throws XueWenServiceException
	 */
	public Page<UserCourse> findMyCollect(String userId,Pageable page)throws XueWenServiceException{
		Page<UserCourse> uc=userCourseRepository.findByUserAndIsFavGreaterThan(userId, 0,page);
		if(uc != null && uc.getTotalElements() > 0){
			if(uc.getTotalElements() > 0){
				List<UserCourse> userCourse = uc.getContent();
				UserCourse one = null;
				if(userCourse != null){
					for(int i=0 ;i< userCourse.size(); i++){
						one = userCourse.get(i);
						String course = (String)one.getCourse();
						Course co = courseService.findCourse(course);
						one.setCourse(co);
					}
				}
			}
		}else{
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_201,
					null);
		}
		return uc;
	}
	
	/**
	 * 查询用户收藏但未曾学习的课程
	 * @param userId
	 * @param page
	 * @return
	 * @throws XueWenServiceException
	 */
	public Page<UserCourse> findMyCollectNoStudy(String userId,Pageable page)throws XueWenServiceException{
		Page<UserCourse> uc=userCourseRepository.findByUserAndIsFavAndIsStudy(userId, 1, 0,page);
		return uc;
	}
	
	/**
	 * 查询用户收藏正在学习的课程
	 * @param userId
	 * @param page
	 * @return
	 * @throws XueWenServiceException
	 */
	public Page<UserCourse> findMyCollectStudying(String userId,Pageable page)throws XueWenServiceException{
		Page<UserCourse> uc=userCourseRepository.findByUserAndIsFavAndIsStudyAndIsStudyed(userId, 1, 1,0,page);
		return uc;
	}
	
	/**
	 * 查询用户收藏且已经学完的课程
	 * @param userId
	 * @param page
	 * @return
	 * @throws XueWenServiceException
	 */
	public Page<UserCourse> findMyCollectStudyed(String userId,Pageable page)throws XueWenServiceException{
		Page<UserCourse> uc=userCourseRepository.findByUserAndIsFavAndIsStudyAndIsStudyed(userId, 1, 1,1,page);
		return uc;
	}
	
	
	/**
	 * 查询我的学习
	 * @param courseId
	 * @param userId
	 * @throws XueWenServiceException
	 */
	public Page<UserCourse> findMyStudy(String userId,Pageable page)throws XueWenServiceException{
		Page<UserCourse> uc=userCourseRepository.findByUserAndIsStudyGreaterThan(userId, 0,page);
		if(uc != null){
			if(uc.getTotalElements() > 0){
				List<UserCourse> userCourse = uc.getContent();
				UserCourse one = null;
				if(userCourse != null){
					for(int i=0 ;i< userCourse.size(); i++){
						one = userCourse.get(i);
						String course = (String)one.getCourse();
						Course co = courseService.findCourse(course);
						one.setCourse(co);
					}
				}
			}
		}else{
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_201,
					null);
		}
		return uc;
	}
	
	/**
	 * 查询用户学习中的课程
	 * @param userId
	 * @param page
	 * @return
	 * @throws XueWenServiceException
	 */
	public Page<UserCourse> findMyStudying(String userId,Pageable page)throws XueWenServiceException{
		return  userCourseRepository.findByUserAndIsStudyAndIsStudyed(userId, 1, 0,page);
	}
	
	/**
	 * 查询用户学习完的课程
	 * @param userId
	 * @param page
	 * @return
	 * @throws XueWenServiceException
	 */
	public Page<UserCourse> findMyStudyed(String userId,Pageable page)throws XueWenServiceException{
		return  userCourseRepository.findByUserAndIsStudyAndIsStudyed(userId, 1, 1,page);
	}
	
	/**
	 * 删除收藏
	 * @param courseId
	 * @param userId
	 * @throws XueWenServiceException
	 */
	public void deleFav(String courseId,String userId)throws XueWenServiceException{
		UserCourse uc=userCourseRepository.findByUserAndCourse(userId, courseId);
		if(uc == null){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_201,
					null);
		}else{
			if(uc.getIsFav()==1){
				uc.setIsFav(0);
				uc.setUtime(System.currentTimeMillis());
			}
			
		}
		userCourseRepository.save(uc);
		
	}
	/**
	 * 根据用户ID查询课程列表
	 * @param userId
	 * @return
	 */
	public Page<UserCourse>  findCourseByUserId(String userId,Pageable page){
		Page<UserCourse> userCourse  = userCourseRepository.findByUser(userId, page);
		return userCourse;
	}
	
	/**
	 * 判断此课程是否正在学习
	 * @param userId
	 * @param courseId
	 * @param isStudy
	 * @param proessid
	 * @return
	 * @throws XueWenServiceException
	 */
//	public boolean findStudying(String userId,String courseId)throws XueWenServiceException{
//		if( userCourseRepository.findByUserAndCourseAndIsStudyAndProessidLessThan(userId, courseId,1,1.0)==null){
//			return false;
//		}else{
//			return true;
//		}
//	}
//	
	/**
	 * 判断此课程是否已经学习完毕
	 * @param userId
	 * @param courseId
	 * @return
	 * @throws XueWenServiceException
	 */
//	public boolean findStudyed(String userId,String courseId)throws XueWenServiceException{
//		if( userCourseRepository.findByUserAndCourseAndIsStudyAndProessid(userId, courseId,1,1.0)==null){
//			return false;
//		}else{
//			return true;
//		}
//	}
	
//	public Page<UserCourse> findUserStudyingCourseIdsBySkillId(String userId,String skillId,Pageable pga) throws XueWenServiceException{
//		
//	}
//	public boolean findUserStudyedCourseIdsBySkillId(String userId,String SkillId) throws XueWenServiceException{
//		return null;
//	}
	/**
	 * 查询出来用户正在学习的技能课程
	 * @param userId
	 * @param skillId
	 * @param pga
	 * @return
	 * @throws XueWenServiceException
	 */
	public Page<UserCourse> findUserStudyingCourseBySkillId(String userId,String skillId,Pageable pga) throws XueWenServiceException{
		return userCourseRepository.findByUserAndSkillIdAndIsStudyAndIsStudyed(userId, skillId, 1,0, pga);
	}
	
	/**
	 * 查询出来用户已经学习完成的技能课程
	 * @param userId
	 * @param skillId
	 * @param pga
	 * @return
	 * @throws XueWenServiceException
	 */
	public Page<UserCourse> findUserStudyedCourseBySkillId(String userId,String skillId,Pageable pga) throws XueWenServiceException{
		return userCourseRepository.findByUserAndSkillIdAndIsStudyAndIsStudyed(userId, skillId, 1,1, pga);
	}
	
	/**
	 * 查询出来用户所有学习的课程技能
	 * @param userId
	 * @param skillId
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<UserCourse> findUserStudyCourseBySkillId(String userId,String skillId)throws XueWenServiceException{
		return userCourseRepository.findByUserAndSkillIdAndIsStudy(userId, skillId, 1);
	}
	
	public UserCourse findUCByUserAndCousr(String userId,String courseId)throws XueWenServiceException{
		return userCourseRepository.findByUserAndCourse(userId, courseId);
	}
	
	/**
	 * 将课程ID转化成课程对象
	 * @param userCourses
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<UserCourse> toResponseUserCourse(List<UserCourse> userCourses)throws XueWenServiceException{
		
		for(UserCourse uc:userCourses){
			uc.setCourse(courseService.findCourse(uc.getCourse().toString()));
		}
		return userCourses;
	}
	
	/**
	 * 根据用户Id和课程Id查询出正在学习的课程信息
	 * @param userId
	 * @param courseId
	 * @return
	 * @throws XueWenServiceException
	 */
	public UserCourse findUCByUserAndCousrAndStudy(String userId,String courseId)throws XueWenServiceException{
		return userCourseRepository.findByUserAndCourseAndIsStudy(userId, courseId,1);
	}
	
	/**
	 * 统计用户学习的课程数量
	 * @param userId
	 * @return
	 * @throws XueWenServiceException
	 */
	public int userStudyCourseNum(String userId)throws XueWenServiceException{
		return userCourseRepository.countByUserAndIsStudy(userId, 1);
	}
	
	
	/**
	 * 统计某技能下用户收藏过没有学习的课程数量
	 * @param userId
	 * @param skillId
	 * @return
	 * @throws XueWenServiceException
	 */
	public int countUserFavNoStudySkillCourse(String userId,String skillId)throws XueWenServiceException{
		if(StringUtil.isBlank(userId) || StringUtil.isBlank(skillId)){
			throw new  XueWenServiceException(Config.STATUS_201,Config.MSG_201,null);
		}
		
		return userCourseRepository.countByUserAndSkillIdAndIsFavAndIsStudyAndIsStudyed(userId, skillId, 1, 0, 0);
	}
	
	/**
	 * 统计某技能下用户收藏过正在学习的课程数量
	 * @param userId
	 * @param skillId
	 * @return
	 * @throws XueWenServiceException
	 */
	public int countUserFavStudyingSkillCourse(String userId,String skillId)throws XueWenServiceException{
		if(StringUtil.isBlank(userId) || StringUtil.isBlank(skillId)){
			throw new  XueWenServiceException(Config.STATUS_201,Config.MSG_201,null);
		}
		
		return userCourseRepository.countByUserAndSkillIdAndIsFavAndIsStudyAndIsStudyed(userId, skillId, 1, 1, 0);
	}
	
	/**
	 * 统计某技能下用户收藏过学习完成的课程数量
	 * @param userId
	 * @param skillId
	 * @return
	 * @throws XueWenServiceException
	 */
	public int countUserFavStudyedSkillCourse(String userId,String skillId)throws XueWenServiceException{
		if(StringUtil.isBlank(userId) || StringUtil.isBlank(skillId)){
			throw new  XueWenServiceException(Config.STATUS_201,Config.MSG_201,null);
		}
		
		return userCourseRepository.countByUserAndSkillIdAndIsFavAndIsStudyAndIsStudyed(userId, skillId, 1, 1, 1);
	}
	
	/**
	 * 统计某技能下用户正在学习的课程数量
	 * @param userId
	 * @param skillId
	 * @return
	 * @throws XueWenServiceException
	 */
	public int countUserStudyingSkillCourse(String userId,String skillId)throws XueWenServiceException{
		if(StringUtil.isBlank(userId) || StringUtil.isBlank(skillId)){
			throw new  XueWenServiceException(Config.STATUS_201,Config.MSG_201,null);
		}
		
		return userCourseRepository.countByUserAndSkillIdAndIsStudyAndIsStudyed(userId, skillId, 1, 0);
	}
	
	/**
	 * 统计某技能下用户学习完成的课程数量
	 * @param userId
	 * @param skillId
	 * @return
	 * @throws XueWenServiceException
	 */
	public int countUserStudyedSkillCourse(String userId,String skillId)throws XueWenServiceException{
		if(StringUtil.isBlank(userId) || StringUtil.isBlank(skillId)){
			throw new  XueWenServiceException(Config.STATUS_201,Config.MSG_201,null);
		}
		
		return userCourseRepository.countByUserAndSkillIdAndIsStudyAndIsStudyed(userId, skillId, 1, 1);
	}
	/**
	 * 统计用户收藏过没有学习的课程数量
	 * @param userId
	 * @param skillId
	 * @return
	 * @throws XueWenServiceException
	 */
	public int countUserFavNoStudyCourse(String userId)throws XueWenServiceException{
		if(StringUtil.isBlank(userId)){
			throw new  XueWenServiceException(Config.STATUS_201,Config.MSG_201,null);
		}
		
		return userCourseRepository.countByUserAndIsFavAndIsStudyAndIsStudyed(userId, 1, 0, 0);
	}
	
	/**
	 * 统计用户收藏过正在学习的课程数量
	 * @param userId
	 * @param skillId
	 * @return
	 * @throws XueWenServiceException
	 */
	public int countUserFavStudyingCourse(String userId)throws XueWenServiceException{
		if(StringUtil.isBlank(userId)){
			throw new  XueWenServiceException(Config.STATUS_201,Config.MSG_201,null);
		}
		
		return userCourseRepository.countByUserAndIsFavAndIsStudyAndIsStudyed(userId, 1, 1, 0);
	}
	
	/**
	 * 统计用户收藏过学习完成的课程数量
	 * @param userId
	 * @param skillId
	 * @return
	 * @throws XueWenServiceException
	 */
	public int countUserFavStudyedCourse(String userId)throws XueWenServiceException{
		if(StringUtil.isBlank(userId)){
			throw new  XueWenServiceException(Config.STATUS_201,Config.MSG_201,null);
		}
		
		return userCourseRepository.countByUserAndIsFavAndIsStudyAndIsStudyed(userId, 1, 1, 1);
	}
	
	/**
	 * 统计用户正在学习的课程数量
	 * @param userId
	 * @param skillId
	 * @return
	 * @throws XueWenServiceException
	 */
	public int countUserStudyingCourse(String userId)throws XueWenServiceException{
		if(StringUtil.isBlank(userId) ){
			throw new  XueWenServiceException(Config.STATUS_201,Config.MSG_201,null);
		}
		
		return userCourseRepository.countByUserAndIsStudyAndIsStudyed(userId, 1, 0);
	}
	
	/**
	 * 统计用户学习完成的课程数量
	 * @param userId
	 * @param skillId
	 * @return
	 * @throws XueWenServiceException
	 */
	public int countUserStudyedCourse(String userId)throws XueWenServiceException{
		if(StringUtil.isBlank(userId)){
			throw new  XueWenServiceException(Config.STATUS_201,Config.MSG_201,null);
		}
		
		return userCourseRepository.countByUserAndIsStudyAndIsStudyed(userId, 1, 1);
	}
}

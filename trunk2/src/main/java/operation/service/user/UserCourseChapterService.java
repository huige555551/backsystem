package operation.service.user;


import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;
import operation.exception.XueWenServiceException;
import operation.pojo.user.User;
import operation.pojo.user.UserCourseChapter;
import operation.pojo.user.UserStudyResult;
import operation.repo.user.UserRepository;
import operation.repo.user.UserStudyResultRepository;
import operation.repo.usercoursechapter.UserCourseChapterRepository;
import operation.service.course.ChapterService;
import operation.service.course.UserCourseService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import tools.MathTools;
import tools.StringUtil;


@Service
@Component
@EnableScheduling
public class UserCourseChapterService {
private static final Logger logger = Logger.getLogger(UserCourseChapterService.class);
	
	@Autowired
	public UserCourseChapterRepository userCourseChapterRepository;
	@Autowired
	public ChapterService chapterService;
	@Autowired
	public UserService userService;
	@Autowired
	public UserStudyResultRepository userStudyResultRepository;
	@Autowired
	public UserCourseService userCourseService;
	
	public UserCourseChapterService(){
		super();
	}
	/**
	 * 获得
	 * @param userId
	 * @param courseId
	 * @param chapterId
	 * @return
	 */
	public UserCourseChapter findUserCourseChapter(String userId,String courseId,String chapterId){
		return userCourseChapterRepository.findByUserIdAndCourseIdAndChapterId(userId, courseId, chapterId);
	}
	/**
	 * 保存章节学习进度
	 * @param ucc
	 */
	public void saveUserCourseChapter(UserCourseChapter ucc){
		 userCourseChapterRepository.save(ucc);
	}
	/**
	 * 根据课程查询章节
	 * @param userId
	 * @param courseId
	 * @return
	 */
	public List<UserCourseChapter>  findChapterByCourse(String userId,String courseId){
		return userCourseChapterRepository.findByUserIdAndCourseId(userId, courseId);
	}
	
	/**
	 * 保存章节学习进度
	 * @param userId
	 * @param courseId
	 * @param chapterId
	 * @param timer
	 * @param proess
	 * @param time
	 */
	public void saveChapterStudy(String userId,String courseId,String chapterId,String jsonStr,long time)throws XueWenServiceException{
		
		UserCourseChapter ucc=findUserCourseChapter(userId, courseId, chapterId);
		if(ucc == null){
			ucc = new UserCourseChapter(userId,courseId,chapterId);
		}
		
		if(!StringUtil.isBlank(jsonStr)){
			Map<String, String> map = new LinkedHashMap<String, String>();
			map = (Map<String, String>) JSONObject.toBean(JSONObject.fromObject(jsonStr), Map.class);
			Map<Long,Double> pro = ucc.getProessid();
			
			if(pro == null){
				pro = new LinkedHashMap<Long,Double>();
			}
			int timer=chapterService.getChapter(chapterId).getTimer();
			Set<Map.Entry<String, String>> set = map.entrySet();
			double format=0d;
			for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
				Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
				long l=Long.parseLong(entry.getKey());
				double d=Double.parseDouble(entry.getValue());
				pro.put(l, d);
				double dp=ucc.getMaxProessid();
				if(l>format){
					format=l;
					ucc.setLastTimer(MathTools.mul(timer, d));
				}
				ucc.setMaxProessid(MathTools.compare(d,dp));
			}
			ucc.setProessid(pro);
		}
		ucc.setUtime(time);
		saveUserCourseChapter(ucc);
	}
	/**
	 * 通过用户ID和章节ID查询该章节进度
	 * @param userId
	 * @param chapterId
	 * @return
	 */
	public UserCourseChapter findChapterProcess(String userId,String chapterId){
		UserCourseChapter ucc = userCourseChapterRepository.findByUserIdAndChapterId(userId, chapterId);
		return ucc;
	}
	
	
	
	
	
	
	/**
	 * 计算用户总共的学习时间
	 * @param userId
	 * @return
	 * @throws XueWenServiceException
	 */
	public long countUserStudyTime(String userId)throws XueWenServiceException{
		List<UserCourseChapter> uccs=userCourseChapterRepository.findByUserId(userId);
		long studyTime=0L;
		if(uccs == null || uccs.size() == 0){
			return 0L;
		}else{
			for(UserCourseChapter ucc:uccs){
				studyTime=studyTime+countOneUserChapterStudyTimer(ucc);
			}
			return studyTime;
		}
		
	}
	
	/**
	 * 计算用户单个章节的学习时间
	 * @param ucc
	 * @return
	 * @throws XueWenServiceException
	 */
	public long countOneUserChapterStudyTimer(UserCourseChapter ucc)throws XueWenServiceException{
		if(ucc !=null){
			int timer=chapterService.getChapterTimer(ucc.getChapterId().toString());
			double proessid=ucc.getMaxProessid();
			return MathTools.mul(timer, proessid);
		}
		return 0L;
	}
	
}

package operation.service.user;

import java.util.List;

import operation.exception.XueWenServiceException;
import operation.pojo.user.User;
import operation.pojo.user.UserStudyResult;
import operation.repo.user.UserStudyResultRepository;
import operation.service.course.UserCourseService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
@Component
//@EnableScheduling
public class UserStudyResultService {
	private static final Logger logger=Logger.getLogger(UserStudyResultService.class);

	@Autowired
	public UserService userService;
	@Autowired
	public UserStudyResultRepository userStudyResultRepository;
	@Autowired
	public UserCourseChapterService userCourseChapterService;
	@Autowired
	public UserCourseService userCourseService;
	/**
	 * 定时器---统计所有用户的学习成果
	 * @throws XueWenServiceException
	 */
	//@Scheduled(fixedRate = 86400000)
	public void saveAllUserStudyResult()throws XueWenServiceException{
//		userCourseChapterRepository.
		logger.info("===============定时器：统计用户学习成果=============");
		List<User> users=userService.findAllUsers();
		if(users !=null && users.size()>0){
			for(User user:users){
				updateOrSaveUserStudyResult(user.getId());
			}
		}
	}
	
	

	/**
	 * 根据用户ID更新或者新建用户学习记录
	 * @param userId
	 * @return
	 * @throws XueWenServiceException
	 */
	public UserStudyResult updateOrSaveUserStudyResult(String userId)throws XueWenServiceException{
		UserStudyResult userStudyResult= userStudyResultRepository.findOneByUserId(userId);
		long studyTimer=userCourseChapterService.countUserStudyTime(userId);
		int rank=rankByStudyTimer(studyTimer);
		logger.info("=======此用户排名："+rank);
		int studyCourseNum=userCourseService.userStudyCourseNum(userId);
		long time=System.currentTimeMillis();
		if(userStudyResult == null ){
			userStudyResult=new UserStudyResult();
			userStudyResult.setUserId(userId);
			userStudyResult.setRanking(rank);
			userStudyResult.setStudyTime(studyTimer);
			userStudyResult.setStudyCourseNum(studyCourseNum);
			userStudyResult.setCtime(time);
			userStudyResult.setUtime(time);
		}else{
			userStudyResult.setRanking(rank);
			userStudyResult.setStudyTime(studyTimer);
			userStudyResult.setStudyCourseNum(studyCourseNum);
			userStudyResult.setUtime(time);
		}
		return userStudyResultRepository.save(userStudyResult);
	}
	
	/**
	 * 获取用户学习成果
	 * @param userId
	 * @return
	 * @throws XueWenServiceException
	 */
	public UserStudyResult findByUserId(String userId)throws XueWenServiceException{
		UserStudyResult userStudyResult=userStudyResultRepository.findOneByUserId(userId);
		if(userStudyResult == null){
			return updateOrSaveUserStudyResult(userId);
		}else{
			return userStudyResult;
		}
	}
	
	/**
	 * 根据用户的学习时间计算排名
	 * @param studyTimer
	 * @return
	 * @throws XueWenServiceException
	 */
	public int rankByStudyTimer(long studyTimer)throws XueWenServiceException{
		return userStudyResultRepository.countByStudyTimeGreaterThan(studyTimer)+1;
	}
}

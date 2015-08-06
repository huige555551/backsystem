package operation.service.course;


import java.util.ArrayList;
import java.util.List;

import operation.exception.XueWenServiceException;
import operation.pojo.course.UserBuyCourse;
import operation.repo.course.UserBuyCourseRepository;
import operation.repo.course.UserBuyCourseTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import tools.StringUtil;
import tools.YXTJSONHelper;



@Component
@Service
public class UserBuyCourseService {
	
	@Autowired
	private UserBuyCourseRepository userBuyCourseRepository;
	
	@Autowired
	private UserBuyCourseTemplate userBuyCourseTemplate;
	
	@Autowired
	private NewCourseService newCourseService;
	
	/**
	 * 判断该用户是否已经购买过该课程（true,已购买；false 未购买）
	 * @param userId
	 * @param courseId
	 * @return
	 * @throws XueWenServiceException
	 */
	public boolean findOneByUserIdAndCourseId(String userId,String courseId)throws XueWenServiceException{
		return userBuyCourseTemplate.findOneByUserIdAndCourseId(userId, courseId);
	}
	
	/**
	 * 购买课程
	 * @param userId
	 * @param nickName
	 * @param logoUrl
	 * @param courseId
	 * @param courseTitle
	 * @param courseLogo
	 * @param coursePrice
	 * @return
	 * @throws XueWenServiceException
	 */
	public UserBuyCourse buyCourse(String userId,String nickName,String logoUrl,String courseId,String courseTitle,String courseLogo,String coursePrice)throws XueWenServiceException{
		UserBuyCourse ubc = new UserBuyCourse(userId,courseId);
		ubc.setCourseLogo(courseLogo);
		ubc.setCourseTitle(courseTitle);
		ubc.setCoursePrice(coursePrice);
		ubc.setLogoUrl(logoUrl);
		ubc.setNickName(nickName);
		userBuyCourseRepository.save(ubc);
		newCourseService.addBuyCount(courseId);
		return ubc;
	}
	/**
	 * 查询我购买的课程
	 * @param userId
	 * @param pageable
	 * @return
	 * @throws XueWenServiceException
	 */
	public Page<UserBuyCourse> userBuyCourse(String userId,Pageable pageable) throws XueWenServiceException{
		return userBuyCourseRepository.findAllByUserId(userId, pageable);
	}
	
	
	/**
	 * 查询课程被谁购买过
	 * @param userId
	 * @param pageable
	 * @return
	 * @throws XueWenServiceException
	 */
	public Page<UserBuyCourse> whoBuyCourse(String courseid,Pageable pageable) throws XueWenServiceException{
		return userBuyCourseRepository.findByCourseId(courseid, pageable);
	}
	
	/**
	 * 课程详情使用的用户购买列表
	 * @param courseId
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<Object> courseDetailBuyCourseUserList(String courseId)throws XueWenServiceException{
		//排序方式 默认DESC
		Direction d = Direction.DESC;
		Sort st = new Sort(d,"ctime");
		Pageable pab=new PageRequest(0,10,st);
		Page<UserBuyCourse> bcs=this.whoBuyCourse(courseId, pab);
		List<UserBuyCourse> bcList=bcs.getContent();
		if(bcList !=null && bcList.size()>0){
			List<Object> objs=new ArrayList<Object>();
			for(UserBuyCourse ubc:bcList){
				objs.add(formateUserBuyCourseRspUserInfo(ubc));
			}
			return objs;
		}
		return null;
	}
	/**
	 * 格式化数据，APP课程详情页使用
	 * @param ubc
	 * @return
	 * @throws XueWenServiceException
	 */
	public Object formateUserBuyCourseRspUserInfo(UserBuyCourse ubc)throws XueWenServiceException{
		return YXTJSONHelper.includeAttrJsonObject(ubc, new String[]{"userId","nickName","logoUrl"});
	}
	
	
	
}

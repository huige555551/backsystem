package operation.controller.test;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import operation.BaseController;
import operation.exception.XueWenServiceException;
import operation.pojo.course.GroupShareKnowledge;
import operation.pojo.course.Knowledge;
import operation.pojo.course.NewCourse;
import operation.pojo.group.XueWenGroup;
import operation.pojo.user.User;
import operation.service.course.ChapterService;
import operation.service.course.GroupShareKnowledgeService;
import operation.service.course.KnowledgeService;
import operation.service.course.NewChapterService;
import operation.service.course.NewCourseService;
import operation.service.group.GroupNumService;
import operation.service.group.GroupService;
import operation.service.qrcode.QRCodeService;
import operation.service.rabbitmq.RabbitmqService;
import operation.service.user.UserCourseChapterService;
import operation.service.user.UserService;
import operation.service.user.UserSkillsService;
import operation.service.user.UserStudyResultService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tools.ResponseContainer;
import tools.YXTJSONHelper;

@RestController
@RequestMapping("/mytest")
@Configuration
public class Test extends BaseController {

	private static final Logger logger = Logger
			.getLogger(MyTestController.class);
	@Autowired
	public GroupService groupService;
	@Autowired
	public GroupNumService groupNumService;

	@Autowired
	public ChapterService chapterService;
	
	@Autowired
	public NewChapterService newChapterService;

	@Autowired
	public UserService userService;

	@Autowired
	public UserCourseChapterService userCourseChapterService;

	@Autowired
	public UserStudyResultService userStudyResultService;

	@Autowired
	public UserSkillsService userSkillsService;

	@Autowired
	private QRCodeService qRCodeService;

	@Autowired
	private NewCourseService s1;

	@Autowired
	private GroupShareKnowledgeService gservice;
	@Autowired
	private KnowledgeService knowledgeService;
	
	

	@Autowired
	private QRCodeService qs;

	@Autowired
	private RabbitmqService rabbservice;

	@RequestMapping("t1")
	@ResponseBody
	public Object test1111(String time) {
		try {
			List<NewCourse> ssssCourses = s1.getCoursesFromCenter(time);
			return ssssCourses;
		} catch (XueWenServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	@RequestMapping(value = "t5")
	public Object test5() throws XueWenServiceException {
		s1.deletec();
		return "ok";
	}

	@RequestMapping("t6")
	public @ResponseBody Object t6() throws XueWenServiceException {
		List<GroupShareKnowledge> gs = gservice.findByKngType(null);
		for (GroupShareKnowledge groupShareKnowledge : gs) {
			String groupId = groupShareKnowledge.getGroupId();
			String userId = groupShareKnowledge.getShareUserId();
			XueWenGroup group = groupService.findById(groupId);
			User user = userService.findOne(userId);
			Knowledge knowledge = knowledgeService.getById(groupShareKnowledge
					.getKnowledge());
			if (group != null && user != null && knowledge != null) {
				groupShareKnowledge = gservice.createOne(groupShareKnowledge,
						user, knowledge, group);
			}
		}
		gservice.save(gs);
		return gs;
	}

	@RequestMapping("t8")
	public @ResponseBody Object sendt2(HttpServletRequest request, String id,
			String type) throws Exception {

		rabbservice.sendRegexMessage(id, type);
		return "ok";

	}
	
	@RequestMapping("t9")
	public @ResponseBody ResponseContainer t9(HttpServletRequest request, String id,
			String type) throws Exception {
        User user=new User();
        
		JSONObject object=JSONObject.fromObject(user);
		
		return addResponse(0, "", object, 10, "");

	}
	@RequestMapping("t19")
	public Object pnumber() throws XueWenServiceException{
		groupNumService.setGroupNumber(800073, 900073);
		return "ok";
	}
	
//	@RequestMapping("t20")
//	@ResponseBody
//	public Object test20(){
//	newChapterService.updateChaterOldData();
//		return "ok";
//	}
//	
//	@RequestMapping("t21")
//	@ResponseBody
//	public Object test21(){
//		newChapterService.deleteOldData();
//		return "ok";
//	}
}

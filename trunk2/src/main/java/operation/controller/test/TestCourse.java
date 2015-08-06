package operation.controller.test;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import operation.BaseController;
import operation.pojo.course.NewChapter;
import operation.pojo.course.NewCourse;
//import operation.pojo.drycargo.DrycargoBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tools.Config;
import tools.ResponseContainer;

//只供测试。。。测完删除，代码不规范
@RestController
@RequestMapping("/testCourse")
public class TestCourse extends BaseController {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
//	@RequestMapping("/allDrycargoBean")
//	public @ResponseBody ResponseContainer allCategory(HttpServletRequest request) {
//		try {
//			Query query = new Query();
//			List<DrycargoBean> list = mongoTemplate.find(query, DrycargoBean.class);
//			return addResponse(Config.STATUS_200, Config.MSG_200, list,Config.RESP_MODE_10, "");
//		} catch (Exception e) {
//			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
//		}
//	}
//	
	@RequestMapping("/courses")
	public @ResponseBody ResponseContainer showCourses(HttpServletRequest request) {
		try {
			String[] sort = {"ctime","studyCount"};
			Query query = new Query();
			query.with(new Sort(sort));
			List<NewCourse> list = mongoTemplate.find(query, NewCourse.class);
			return addResponse(Config.STATUS_200, Config.MSG_200, list,Config.RESP_MODE_10, "");
		} catch (Exception e) {
			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
		}
	}
	
	@RequestMapping("/oneCourse")
	public @ResponseBody ResponseContainer showChapters(HttpServletRequest request) {
		try {
			String courseId = request.getParameter("courseId");
			NewCourse course = mongoTemplate.findOne(new Query(Criteria.where("id").is(courseId)), NewCourse.class);
			List<Object> chapterIds = course.getChapters();
			List<NewChapter> chapters = mongoTemplate.find(new Query(Criteria.where("id").in(chapterIds)).with(new Sort("order")), NewChapter.class);
			List<Object> chapterBack = new ArrayList<Object>();
			for (NewChapter newChapter : chapters) {
				chapterBack.add(newChapter);
			}
			course.setChapters(chapterBack);
			return addResponse(Config.STATUS_200, Config.MSG_200, course,Config.RESP_MODE_10, "");
		} catch (Exception e) {
			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
		}
	}
	
	
	
}

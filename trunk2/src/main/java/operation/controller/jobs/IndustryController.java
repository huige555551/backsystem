package operation.controller.jobs;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import operation.BaseController;
import operation.exception.XueWenServiceException;
import operation.pojo.group.ResponseGroup;
import operation.pojo.jobs.Industry;
import operation.pojo.jobs.Industryclass;
import operation.pojo.user.User;
import operation.service.jobs.IndustryService;
import tools.Config;
import tools.ResponseContainer;

@RestController
@RequestMapping("/industry")
@Configuration
public class IndustryController extends BaseController {

	private static final Logger logger=Logger.getLogger(IndustryController.class);
	@Autowired
	public IndustryService industryService;
	/**
	 * 查找到所有的岗位分类
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/all")
	public @ResponseBody ResponseContainer allIndustry(HttpServletRequest request) {
		try {
			String token = request.getParameter("token");
//			User currentUser = this.getCurrentUser(token);
			List<Industryclass> industrys=industryService.getAllIndustry();
			return addResponse(Config.STATUS_200, Config.MSG_200, industrys,Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			e.printStackTrace();
			logger.error("==========业务错误，根据群组ID查询群信息,不包含成员列表失败============"+e);
			return addResponse(e.getCode(), e.getMessage(), false,
					Config.RESP_MODE_10, "");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("==========未知错误，根据群组ID查询群信息,不包含成员列表失败============"+e);
			return addResponse(Config.STATUS_505, Config.MSG_505, false,
					Config.RESP_MODE_10, "");
		}
	}
	
	
	@RequestMapping("/insert")
	public @ResponseBody ResponseContainer insertIndustry(HttpServletRequest request) {
		try {
			String names = request.getParameter("industry");
//			User currentUser = this.getCurrentUser(token);
			industryService.insertIndustry(names);;
			return addResponse(Config.STATUS_200, Config.MSG_200, true,Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			e.printStackTrace();
			logger.error("==========业务错误，根据群组ID查询群信息,不包含成员列表失败============"+e);
			return addResponse(e.getCode(), e.getMessage(), false,
					Config.RESP_MODE_10, "");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("==========未知错误，根据群组ID查询群信息,不包含成员列表失败============"+e);
			return addResponse(Config.STATUS_505, Config.MSG_505, false,
					Config.RESP_MODE_10, "");
		}
	}
	
	@RequestMapping("/insertSec")
	public @ResponseBody ResponseContainer insertIndustryclass(HttpServletRequest request) {
		try {
			String names = request.getParameter("industryclass");
			String pid=request.getParameter("pid");
//			User currentUser = this.getCurrentUser(token);
			logger.info(names);
			industryService.insertIndustryclass(names, pid);
			return addResponse(Config.STATUS_200, Config.MSG_200, true,Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			e.printStackTrace();
			logger.error("==========业务错误，根据群组ID查询群信息,不包含成员列表失败============"+e);
			return addResponse(e.getCode(), e.getMessage(), false,
					Config.RESP_MODE_10, "");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("==========未知错误，根据群组ID查询群信息,不包含成员列表失败============"+e);
			return addResponse(Config.STATUS_505, Config.MSG_505, false,
					Config.RESP_MODE_10, "");
		}
	}
	
	
	@RequestMapping("/insertjob")
	public @ResponseBody ResponseContainer insertJobs(HttpServletRequest request) {
		try {
			String names = request.getParameter("jobs");
			String pid=request.getParameter("pid");
//			User currentUser = this.getCurrentUser(token);
			logger.info(names);
			industryService.insertJobs(names, pid);
			return addResponse(Config.STATUS_200, Config.MSG_200, true,Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			e.printStackTrace();
			logger.error("==========业务错误，根据群组ID查询群信息,不包含成员列表失败============"+e);
			return addResponse(e.getCode(), e.getMessage(), false,
					Config.RESP_MODE_10, "");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("==========未知错误，根据群组ID查询群信息,不包含成员列表失败============"+e);
			return addResponse(Config.STATUS_505, Config.MSG_505, false,
					Config.RESP_MODE_10, "");
		}
	}
	
	/**
	 * 查询职能
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/findIndustry")
	public @ResponseBody ResponseContainer findAllIndustry(HttpServletRequest request) {
		try {
			String token = request.getParameter("token");
//			User currentUser = this.getCurrentUser(token);
			List<Industryclass> industryClass=industryService.findAllIndustryClass();
			return addResponse(Config.STATUS_200, Config.MSG_200, industryClass,Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			e.printStackTrace();
			logger.error("==========业务错误，根据群组ID查询群信息,不包含成员列表失败============"+e);
			return addResponse(e.getCode(), e.getMessage(), false,
					Config.RESP_MODE_10, "");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("==========未知错误，根据群组ID查询群信息,不包含成员列表失败============"+e);
			return addResponse(Config.STATUS_505, Config.MSG_505, false,
					Config.RESP_MODE_10, "");
		}
	}
	
	
	
	
}

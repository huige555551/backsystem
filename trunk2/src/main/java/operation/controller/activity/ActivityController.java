package operation.controller.activity;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import operation.BaseController;
import operation.exception.XueWenServiceException;
import operation.pojo.activity.NewActivity;
import operation.pojo.activity.NewActivityUser;
import operation.pojo.pub.QueryModelMul;
import operation.pojo.user.User;
import operation.service.activity.NewActivityService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tools.Config;
import tools.PageRequestTools;
import tools.ReponseDataTools;
import tools.ResponseContainer;

@RestController
@RequestMapping("/activity")
public class ActivityController extends BaseController {
	private static final Logger l=Logger.getLogger(ActivityController.class);
	@Autowired
	private NewActivityService newActivityService;

	/**
	 * 创建活动
	 * @param request
	 * @param activity
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody ResponseContainer create(HttpServletRequest request,NewActivity activity) {
		try {
			String image = request.getParameter("image");// 活动图片需要传数组对象
			String sourceId = request.getParameter("sourceId");// 群Id
			String groupName = request.getParameter("groupName");
			String groupLogoUrl = request.getParameter("groupLogoUrl");
			String token = request.getParameter("token");
			String lat = request.getParameter("lat"); // 维度
			String lng = request.getParameter("lng");// 精度
			
			String activityLat = request.getParameter("activityLat"); // 维度
			String activityLng = request.getParameter("activityLng");// 精度

			User currUser = this.getCurrentUser(token);
			NewActivity newActivity = newActivityService.create(activity,image, currUser, sourceId, groupName,groupLogoUrl,lat, lng,activityLat,activityLng);
			return addResponse(Config.STATUS_200, Config.MSG_CREATE_200,newActivity, Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			l.error("=====创建活动业务报错："+e);
			return addResponse(e.getCode(), e.getMessage(), false,Config.RESP_MODE_10, "");
		} catch (Exception e) {
			l.error("=====创建活动系统报错："+e);
			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
		}

	}
	
	/**
	 * 创建活动
	 * @param request
	 * @param activity
	 * @return
	 */
	@RequestMapping(value = "/share")
	public @ResponseBody ResponseContainer share(HttpServletRequest request) {
		try {
			
			String group = request.getParameter("source");// 群数组,包含图片 名称
			String activityId = request.getParameter("activityId");//活动Id
			String token = request.getParameter("token");
			User currUser = this.getCurrentUser(token);
			boolean result = newActivityService.share(activityId,group,currUser);
			return addResponse(Config.STATUS_200, Config.MSG_SHARESUCESS_200,result, Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			l.error("=====分享活动业务报错："+e);
			return addResponse(e.getCode(), e.getMessage(), false,Config.RESP_MODE_10, "");
		} catch (Exception e) {
			l.error("=====分享活动系统报错："+e);
			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
		}

	}
	
	/**
	 * 修改活动
	 * @param request
	 * @param activity
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody ResponseContainer update(HttpServletRequest request,NewActivity activity) {
		try {
			String activityId = request.getParameter("activityId");
			String image = request.getParameter("image");// 活动图片需要传数组对象
			String group = request.getParameter("source");// 群Id数组包含群名称 logo
			String lat = request.getParameter("lat"); // 维度
			String lng = request.getParameter("lng");// 精度
			String activityLat = request.getParameter("activityLat"); // 维度
			String activityLng = request.getParameter("activityLng");// 精度
			String token = request.getParameter("token");
			User currUser = this.getCurrentUser(token);
			NewActivity result = newActivityService.update(activityId,group,image,lat, lng,currUser,activity,activityLat,activityLng);
			return addResponse(Config.STATUS_200, Config.MSG_UPDATE_200,result, Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			l.error("=====更新活动业务报错："+e);
			return addResponse(e.getCode(), e.getMessage(), false,Config.RESP_MODE_10, "");
		} catch (Exception e) {
			l.error("=====跟新活动系统报错："+e);
			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
		}

	}
	/**
	 * 分页获取群组下的活动
	 * @param request
	 * @param dm
	 * @return
	 */
	@RequestMapping(value = "/groupActivity")
	public @ResponseBody ResponseContainer groupActivity(HttpServletRequest request,QueryModelMul dm) {
		try {
			List<String> sort = new ArrayList<String>();
			sort.add("activityStartTime");
			dm.setSort(sort);
			Pageable pageable = PageRequestTools.pageRequesMake(dm);
			String groupId = request.getParameter("groupId");
			Page<NewActivity> as=newActivityService.findByGroupId(groupId, pageable);
			ReponseDataTools.getClientReponseData(getReponseData(), as);
			this.getReponseData().setResult((newActivityService.formaterBase(as.getContent())));
			return addPageResponse(Config.STATUS_200, Config.MSG_200, getReponseData(),Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			l.error("=====分页获取群组下的活动业务报错："+e);
			return addResponse(e.getCode(), e.getMessage(), false,Config.RESP_MODE_10, "");
		} catch (Exception e) {
			l.error("=====分页获取群组下的活动系统报错："+e);
			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
		}
		
	}
	
	/**
	 * 分页获取我创建的活动
	 * @param request
	 * @param dm
	 * @return
	 */
	@RequestMapping(value = "/myCreate")
	public @ResponseBody ResponseContainer myCreate(HttpServletRequest request,QueryModelMul dm) {
		try {
			List<String> sort = new ArrayList<String>();
			sort.add("ctime");
			dm.setSort(sort);
			Pageable pageable = PageRequestTools.pageRequesMake(dm);
			String token = request.getParameter("token");
			User user=this.getCurrentUser(token);
			Page<NewActivity> as=newActivityService.findMyCreate(user.getId(), pageable);
			ReponseDataTools.getClientReponseData(getReponseData(), as);
			this.getReponseData().setResult((newActivityService.formaterBase(as.getContent())));
			return addPageResponse(Config.STATUS_200, Config.MSG_200, getReponseData(),Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			l.error("=====分页获取我创建的活动业务报错："+e);
			return addResponse(e.getCode(), e.getMessage(), false,Config.RESP_MODE_10, "");
		} catch (Exception e) {
			l.error("=====分页获取我创建的活动系统报错："+e);
			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
		}
		
	}
	/**
	 * 分页获取我加入的活动
	 * @param request
	 * @param dm
	 * @return
	 */
	@RequestMapping(value = "/myApply")
	public @ResponseBody ResponseContainer myApply(HttpServletRequest request,QueryModelMul dm) {
		try {
			List<String> sort = new ArrayList<String>();
			sort.add("activityStartTime");
			dm.setSort(sort);
			Pageable pageable = PageRequestTools.pageRequesMake(dm);
			String token = request.getParameter("token");
			User user=this.getCurrentUser(token);
			Page<NewActivityUser> as=newActivityService.findMyApply(user.getId(), pageable);
			ReponseDataTools.getClientReponseData(getReponseData(), as);
			return addPageResponse(Config.STATUS_200, Config.MSG_200, getReponseData(),Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			l.error("=====分页获取我加入的活动业务报错："+e);
			return addResponse(e.getCode(), e.getMessage(), false,Config.RESP_MODE_10, "");
		} catch (Exception e) {
			l.error("=====分页获取我加入的活动系统报错："+e);
			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
		}
	}
	/**
	 * 获取活动下申请人列表，分页
	 * @param request
	 * @param dm
	 * @return
	 */
	@RequestMapping(value = "/applyList")
	public @ResponseBody ResponseContainer applyList(HttpServletRequest request,QueryModelMul dm) {
		try {
			List<String> sort = new ArrayList<String>();
			sort.add("ctime");
			dm.setSort(sort);
			Pageable pageable = PageRequestTools.pageRequesMake(dm);
			String newActivityId = request.getParameter("activityId");
			Page<NewActivityUser> as=newActivityService.findApplyList(newActivityId, pageable);
			ReponseDataTools.getClientReponseData(getReponseData(), as);
			this.getReponseData().setResult((newActivityService.formater(as.getContent())));
			return addPageResponse(Config.STATUS_200, Config.MSG_200, getReponseData(),Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			l.error("=====获取活动下申请人列表，分页业务报错："+e);
			return addResponse(e.getCode(), e.getMessage(), false,Config.RESP_MODE_10, "");
		} catch (Exception e) {
			l.error("=====获取活动下申请人列表，分页系统报错："+e);
			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
		}
	}
	/**
	 * 活动详情
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/activityDetails")
	public @ResponseBody ResponseContainer activityDetails(HttpServletRequest request) {
		try {
			String newActivityId = request.getParameter("activityId");
			String token = request.getParameter("token");
			User user=this.getCurrentUser(token);
			Object a=newActivityService.newActivityDetails(user.getId(), newActivityId);
			return addResponse(Config.STATUS_200, Config.MSG_200, a,Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			l.error("=====活动详情业务报错："+e);
			return addResponse(e.getCode(), e.getMessage(), false,Config.RESP_MODE_10, "");
		} catch (Exception e) {
			l.error("=====活动详情系统报错："+e);
			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
		}
	}
	
	/**
	 * 报名活动判断
	 * @param request
	 * @param activity
	 * @return
	 */
	@RequestMapping(value = "/signupcheck")
	public @ResponseBody ResponseContainer check(HttpServletRequest request,NewActivity activity) {
		try {
			String activityId = request.getParameter("activityId");
			String token = request.getParameter("token");
			User currUser = this.getCurrentUser(token);
			boolean result = newActivityService.check(activityId,currUser);
			return addResponse(Config.STATUS_200, Config.MSG_TOADMIN_200,result, Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			l.error("=====报名活动判断业务报错："+e);
			return addResponse(e.getCode(), e.getMessage(), false,Config.RESP_MODE_10, "");
		} catch (Exception e) {
			l.error("=====报名活动判断系统报错："+e);
			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
		}

	}
	
	/**
	 * 生产预付单（报名收费）
	 * @param request
	 * @param activity
	 * @return
	 */
	@RequestMapping(value = "/signup")
	public @ResponseBody ResponseContainer signup(HttpServletRequest request,NewActivity activity) {
		try {
			String activityId = request.getParameter("activityId");
			String token = request.getParameter("token");
			User currUser = this.getCurrentUser(token);
			String groupId = request.getParameter("groupId");
			String groupName = request.getParameter("groupName");
			String groupLogoUrl = request.getParameter("groupLogoUrl");
			String price = request.getParameter("price");
			String spbill_create_ip = request.getParameter("spbill_create_ip");
			String channel = request.getParameter("channel");//渠道来源
			String openid = request.getParameter("openid");//渠道来源
			String phoneNumber = request.getParameter("phoneNumber");
			JSONObject data = newActivityService.signup(activityId,token,groupId,groupName,groupLogoUrl,price,spbill_create_ip,channel,phoneNumber,openid);
			return addResponse(Config.STATUS_200, Config.MSG_CREATEACTIVITY_200,data, Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			l.error("=====报名活动业务报错："+e);
			return addResponse(e.getCode(), e.getMessage(), false,Config.RESP_MODE_10, "");
		} catch (Exception e) {
			l.error("=====报名活动系统报错："+e);
			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
		}

	}
	
	/**
	 * 报名（免费）
	 * @param request
	 * @param activity
	 * @return
	 */
	@RequestMapping(value = "/signupFree")
	public @ResponseBody ResponseContainer signupFree(HttpServletRequest request,NewActivity activity) {
		try {
			String activityId = request.getParameter("activityId");
			String token = request.getParameter("token");
			User currUser = this.getCurrentUser(token);
			
			String groupId = request.getParameter("groupId");
			String groupName = request.getParameter("groupName");
			String groupLogoUrl = request.getParameter("groupLogoUrl");
			String channel = request.getParameter("channel");//渠道来源
			String phoneNumber = request.getParameter("phoneNumber");
			NewActivityUser activityUser = newActivityService.signupFree(activityId,currUser,groupId,groupName,groupLogoUrl,channel,phoneNumber);
			return addResponse(Config.STATUS_200, Config.MSG_CREATEACTIVITY_200,activityUser, Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			l.error("=====报名活动业务报错："+e);
			return addResponse(e.getCode(), e.getMessage(), false,Config.RESP_MODE_10, "");
		} catch (Exception e) {
			l.error("=====报名活动系统报错："+e);
			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
		}

	}
	

}

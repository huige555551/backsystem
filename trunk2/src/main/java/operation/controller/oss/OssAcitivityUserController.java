package operation.controller.oss;

import javax.servlet.http.HttpServletRequest;

import operation.BaseController;
import operation.exception.XueWenServiceException;
import operation.pojo.activity.ActivityUser;
import operation.pojo.pub.QueryModel;
import operation.service.activity.ActivityUserServie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tools.Config;
import tools.PageRequestTools;
import tools.ReponseDataTools;
import tools.ResponseContainer;
@RestController
@RequestMapping("/oss/activityUser")
public class OssAcitivityUserController extends BaseController {
	@Autowired
	private ActivityUserServie activityUserServie;
	@RequestMapping("/page")
	public @ResponseBody ResponseContainer page(HttpServletRequest request, QueryModel dm,String activityId) {
		
		try {
			Pageable pageable = PageRequestTools.pageRequesMake(dm);
			Page<ActivityUser> activitys = activityUserServie.findByActivityId(activityId,pageable);
			ReponseDataTools.getClientReponseData(getReponseData(), activitys);
			return addPageResponse(Config.STATUS_200, Config.MSG_200,
					getReponseData(), Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			return addPageResponse(Config.STATUS_505, Config.MSG_505,
					getReponseData(), Config.RESP_MODE_10, "");
		}
		
	}
	/**
	 * 
	 * @Title: page
	 * @Description: 活动报名
	 * @param request
	 * @param dm
	 * @param activityUser
	 * @return ResponseContainer
	 * @throws
	 */
	@RequestMapping("/create")
	public @ResponseBody ResponseContainer create(HttpServletRequest request, QueryModel dm,ActivityUser activityUser) {
		ActivityUser activity;
		try {
			activity = activityUserServie.add(activityUser);
			return addResponse(Config.STATUS_200, Config.MSG_200, activity,Config.RESP_MODE_10, "");
		}catch(XueWenServiceException e) {
			// TODO Auto-generated catch block
			return addResponse(e.getCode(), e.getMessage(), false,Config.RESP_MODE_10, "");
		} catch (Exception e) {
			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
		}
		
		
	}

}

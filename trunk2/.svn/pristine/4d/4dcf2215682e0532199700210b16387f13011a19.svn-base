package operation.controller.xuanye;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import operation.BaseController;
import operation.controller.drycargo.DrycargoController;
import operation.exception.XueWenServiceException;
import operation.pojo.drycargo.DrycargoResponse;
import operation.pojo.pub.QueryModelMul;
import operation.pojo.user.User;
import operation.pojo.xuanye.Xuanye;
import operation.service.xuanye.XuanyeService;

import org.apache.log4j.Logger;
import org.jsoup.helper.StringUtil;
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
@RequestMapping("/xuanye")
public class XuanyeController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(DrycargoController.class);
	@Autowired
	public XuanyeService xuanyeService;
	/**
	 * 上传炫页
	 * @param request
	 * @param xuanye
	 * @return
	 */
	@RequestMapping("/uploadXuanye")
	public @ResponseBody ResponseContainer uploadXuanye(HttpServletRequest request,Xuanye xuanye) {
		try {
			String token = request.getParameter("token");
			User currentUser = this.getCurrentUser(token);
			String tagName = request.getParameter("tagName");
			// 非空校验
			if (StringUtil.isBlank(xuanye.getUrl()) || currentUser == null) {
				return addResponse(Config.STATUS_500, Config.MSG_500, false,Config.RESP_MODE_10, "");
			}
			Xuanye db = xuanyeService.uploadXuanye(currentUser, xuanye,tagName);
			return addResponse(Config.STATUS_200, Config.MSG_200, db,Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			e.printStackTrace();
			logger.error("==========业务错误，上传炫页失败============" + e);
			return addResponse(e.getCode(), e.getMessage(), false,Config.RESP_MODE_10, "");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("==========未知错误，上传炫页失败============" + e);
			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
		}

	}
	/**
	 * 根据群组查询炫页列表
	 * 
	 * @param request
	 * @param dm
	 * @return
	 */
	@RequestMapping("all")
	public @ResponseBody ResponseContainer all(HttpServletRequest request,QueryModelMul dm) {
		// 根据请求参数封装一个分页信息对象
		List<String> sort = new ArrayList<String>();
		sort.add("viewCount");
		sort.add("ctime");
		dm.setSort(sort);
		Pageable pageable = PageRequestTools.pageRequesMake(dm);
		Page<Xuanye> xuanyeResult;
		try {
			String keywords = request.getParameter("groupId");
			xuanyeResult = xuanyeService.all(keywords, pageable);
			ReponseDataTools.getClientReponseData(getReponseData(), xuanyeResult);
			getReponseData().setResult((xuanyeService.toResponeses(xuanyeResult.getContent())));
			return addPageResponse(Config.STATUS_200, Config.MSG_200, getReponseData(),Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			e.printStackTrace();
			return addResponse(e.getCode(), e.getMessage(), false,Config.RESP_MODE_10, "");
		} catch (Exception e) {
			e.printStackTrace();
			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
		}
	}
	
	/**
	 * 查询炫页详情
	 * @param request
	 * @return
	 */
	@RequestMapping("one")
	public @ResponseBody ResponseContainer groupDryDetail(HttpServletRequest request) {
		try {
		String token = request.getParameter("token");
		User currentUser = this.getCurrentUser(token);
		String xuanyeId = request.getParameter("xuanyeId");//炫页id
		String groupId = request.getParameter("groupId");
		Xuanye xuanye = xuanyeService.xuanyeDetail(currentUser,xuanyeId,groupId);
		return addResponse(Config.STATUS_200, Config.MSG_200, xuanyeService.toResponse(xuanye),Config.RESP_MODE_10, "");
		}catch (XueWenServiceException e) {
			e.printStackTrace();
			logger.error("==========业务错误，查询炫页详情失败============" + e);
			return addResponse(e.getCode(), e.getMessage(), false,Config.RESP_MODE_10, "");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("==========未知错误，查询炫页详情失败============" + e);
			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
		}
	}
}

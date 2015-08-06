package operation.controller.fav;

import javax.servlet.http.HttpServletRequest;

import operation.BaseController;
import operation.controller.praise.PraiseController;
import operation.exception.XueWenServiceException;
import operation.pojo.fav.Fav;
import operation.pojo.pub.QueryModel;
import operation.pojo.user.User;
import operation.service.fav.FavService;

import org.apache.log4j.Logger;
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
@RequestMapping("/fav")
public class FavController extends BaseController{
	
private static final Logger logger=Logger.getLogger(PraiseController.class);
	
	@Autowired
	private FavService favService;
	/**
	 * 收藏
	 * @author hjn
	 * @param request
	 * @return
	 */
	@RequestMapping("addFav")
	public @ResponseBody ResponseContainer addFav(HttpServletRequest request,Fav fav) {
		try {
			String token=request.getParameter("token");
			User user=this.getCurrentUser(token);
			favService.addFav(fav,user.getId());
			return addResponse(Config.STATUS_200, Config.MSG_200, true,Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			// TODO Auto-generated catch block
			logger.error("业务错误："+e);
			return addResponse(e.getCode(), e.getMessage(), false,Config.RESP_MODE_10, "");
		}
	}
	/**
	 * 收藏统计
	 * @author hjn
	 * @param request
	 * @return
	 */
	@RequestMapping("favCount")
	public @ResponseBody ResponseContainer favCount(HttpServletRequest request) {
		try {
			String appKey=request.getParameter("appKey");
			String sourceId=request.getParameter("sourceId");
			String favType=request.getParameter("favType");
			int count=favService.countByAppkeyAndSourceIdAndType(appKey, sourceId, Integer.valueOf(favType));
			return addResponse(Config.STATUS_200, Config.MSG_200, count,Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			// TODO Auto-generated catch block
			logger.error("业务错误："+e);
			return addResponse(e.getCode(), e.getMessage(), false,Config.RESP_MODE_10, "");
		}
	}
	
	/**
	 * 收藏列表
	 * @author hjn
	 * @param request
	 * @return
	 */
	@RequestMapping("favList")
	public @ResponseBody ResponseContainer favList(HttpServletRequest request,QueryModel dm) {
		try {
			String appKey=request.getParameter("appKey");
			String favType=request.getParameter("favType");
			String token=request.getParameter("token");
			User user=this.getCurrentUser(token);
			Pageable pageable = PageRequestTools.pageRequesMake(dm);
			Page<Fav> favs=favService.findListByUserIdAndAppkeyAndType(user.getId(), appKey, favType, pageable);
			ReponseDataTools.getClientReponseData(getReponseData(), favs);
			return addPageResponse(Config.STATUS_200, Config.MSG_200, getReponseData(),Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			// TODO Auto-generated catch block
			logger.error("业务错误："+e);
			return addResponse(e.getCode(), e.getMessage(), false,Config.RESP_MODE_10, "");
		}
	}
}

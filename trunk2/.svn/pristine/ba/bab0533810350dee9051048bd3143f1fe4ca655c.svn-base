package operation.controller.news;

import javax.servlet.http.HttpServletRequest;

import operation.BaseController;
import operation.controller.group.GroupController;
import operation.exception.XueWenServiceException;
import operation.pojo.news.NewsBean;
import operation.pojo.pub.QueryModel;
import operation.service.NewsBeanService;

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
@RequestMapping("/news")
public class NewsBeanController extends BaseController{
	
	private static final Logger logger=Logger.getLogger(GroupController.class);
	
	@Autowired
	private NewsBeanService newsBeanService; 
	
	/**
	 * 查询最新新闻列表
	 * @param request
	 * @param dm
	 * @return
	 */
	@RequestMapping("all")
	public @ResponseBody ResponseContainer findGroup(HttpServletRequest request,QueryModel dm) {
		try {
			Pageable pageable = PageRequestTools.pageRequesMake(dm);
			Page<NewsBean> newsBean = newsBeanService.findAllNewsBean(pageable);
			ReponseDataTools.getClientReponseData(getReponseData(), newsBean);
			this.getReponseData().setResult(newsBean.getContent());
			return addPageResponse(Config.STATUS_200, Config.MSG_200, getReponseData(),Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			e.printStackTrace();
			logger.error("==========业务错误，查询新闻列表失败============"+e);
			return addResponse(e.getCode(), e.getMessage(), false,Config.RESP_MODE_10, "");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("==========未知错误，查询新闻列表失败============"+e);
			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
		}
	}
	
	/**
	 * 查询单条新闻
	 * @param request
	 * @return
	 */
	@RequestMapping("one")
	public @ResponseBody ResponseContainer findGroup(HttpServletRequest request) {
		try {
			String newsId = request.getParameter("newsId");
			NewsBean newsBean = newsBeanService.findOneById(newsId);
			return addResponse(Config.STATUS_200, Config.MSG_200, newsBean,Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			e.printStackTrace();
			logger.error("==========业务错误，查询新闻失败============"+e);
			return addResponse(e.getCode(), e.getMessage(), false,Config.RESP_MODE_10, "");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("==========未知错误，查询新闻失败============"+e);
			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
		}
	}
}

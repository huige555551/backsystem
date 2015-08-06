package operation.controller.oss;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import operation.OssController;
import operation.exception.XueWenServiceException;
import operation.pojo.drycargo.Drycargo;
import operation.pojo.pub.QueryModelMul;
import operation.service.drycargo.DrycargoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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
@RequestMapping("/oss/xuanye")
@Configuration
public class OssXuanYeController extends OssController{
	@Autowired
	public DrycargoService drycargoService;

	public OssXuanYeController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 查询干货列表 包括没有关联群组的
	 * 
	 * @param request
	 * @param dm
	 * @return
	 */
	@RequestMapping("searchXuanye")
	public @ResponseBody ResponseContainer searchXuanye(HttpServletRequest request, QueryModelMul dm) {
		// 根据请求参数封装一个分页信息对象
		// dm.setSort("c");
		List<String> sort = new ArrayList<String>();
		// sort.add("weightSort");
		// sort.add("viewCount");
		sort.add("ctime");
		dm.setSort(sort);
		Pageable pageable = PageRequestTools.pageRequesMake(dm);
		Page<Drycargo> dryCargoResult;
		try {
			String keywords = request.getParameter("keywords");
			dryCargoResult = drycargoService.searchByKeyWordsAndTagNamesLikeForXuanye(keywords, pageable);
			ReponseDataTools.getClientReponseData(getReponseData(), dryCargoResult);
			this.getReponseData().setResult(dryCargoResult.getContent());
			return addPageResponse(Config.STATUS_200, Config.MSG_200, getReponseData(), Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			e.printStackTrace();
			return addResponse(e.getCode(), e.getMessage(), false, Config.RESP_MODE_10, "");
		} catch (Exception e) {
			e.printStackTrace();
			return addResponse(Config.STATUS_505, Config.MSG_505, false, Config.RESP_MODE_10, "");
		}
	}
}

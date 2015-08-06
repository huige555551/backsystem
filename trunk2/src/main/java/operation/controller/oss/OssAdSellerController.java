package operation.controller.oss;

import javax.servlet.http.HttpServletRequest;

import operation.BaseController;
import operation.exception.XueWenServiceException;
import operation.pojo.ad.AdSeller;
import operation.pojo.pub.QueryModel;
import operation.service.ad.AdSellerService;

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

@RequestMapping("/oss/adSeller")
@RestController
public class OssAdSellerController extends BaseController {
	@Autowired
	private AdSellerService adSellerService;
	/**
	 * 
	 * @Title: getAds
	 * @Description: 渠道商分页
	 * @param request
	 * @param dm
	 * @return ResponseContainer
	 * @throws
	 */
	@RequestMapping("/adSellerPage")
	public @ResponseBody ResponseContainer getAds(HttpServletRequest request, QueryModel dm) {
		try {
			Pageable pageable = PageRequestTools.pageRequesMake(dm);
			Page<AdSeller> ads = adSellerService.page(pageable);
			ReponseDataTools.getClientReponseData(getReponseData(), ads);
			return addPageResponse(Config.STATUS_200, Config.MSG_200, getReponseData(), Config.RESP_MODE_10, "");
		} catch (Exception e) {
			return addResponse(Config.STATUS_505, Config.MSG_505, false, Config.RESP_MODE_10, "");
		}
	}
	/**
	 * 
	 * @Title: create
	 * @Description:创建渠道商
	 * @param request
	 * @param adSeller
	 * @return ResponseContainer
	 * @throws
	 */
	@RequestMapping(value="/create",method = RequestMethod.POST)
	public @ResponseBody ResponseContainer create(HttpServletRequest request,AdSeller adSeller){
		try {
			return addResponse(Config.STATUS_200, Config.MSG_200, adSellerService.createAdSeller(adSeller),Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			// TODO Auto-generated catch block
			return addResponse(Config.STATUS_200, Config.MSG_201, false,Config.RESP_MODE_10, "");
		}

	}
	/**
	 * 
	 * @Title: delete
	 * @Description: 删除渠道商
	 * @param request
	 * @param adSeller
	 * @return ResponseContainer
	 * @throws
	 */
	@RequestMapping("/delete")
	public @ResponseBody ResponseContainer delete(HttpServletRequest request,AdSeller adSeller){
		adSellerService.deleteAdSeller(adSeller);
		return addResponse(Config.STATUS_200, Config.MSG_200, true,Config.RESP_MODE_10, "");

	}
	/**
	 * 
	 * @Title: adSellerList
	 * @Description: 渠道商列表
	 * @param request
	 * @return ResponseContainer
	 * @throws
	 */
	@RequestMapping("/adSellerList")
	public @ResponseBody ResponseContainer adSellerList(HttpServletRequest request){
		return addResponse(Config.STATUS_200, Config.MSG_200, adSellerService.adSellerList(),Config.RESP_MODE_10, "");

	}
	
	
	
}

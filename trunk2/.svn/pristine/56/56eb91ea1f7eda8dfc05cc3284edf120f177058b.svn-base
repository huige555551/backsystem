package operation.controller.oss;

import javax.servlet.http.HttpServletRequest;

import operation.BaseController;
import operation.pojo.ad.Ad;
import operation.pojo.ad.AdVo;
import operation.pojo.pub.QueryModel;
import operation.service.ad.AdService;
import operation.service.ad.AdVoService;

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
@RequestMapping(value="/oss/ad")
public class OssAdController extends BaseController{
	@Autowired
	private AdService adService;
	@Autowired
	private AdVoService adVoService;
	/**
	 * 
	 * @Title: getAds
	 * @Description: 分页获取广告位
	 * @param request
	 * @param dm
	 * @return ResponseContainer
	 * @throws
	 */
	@RequestMapping("/adPage")
	public @ResponseBody ResponseContainer getAds(long ctime,long etime,String qdId,String qdName,HttpServletRequest request, QueryModel dm) {
		Pageable pageable = PageRequestTools.pageRequesMake(dm);
		Page<AdVo> losg = adVoService.searchByKey(ctime, etime, qdId, qdName, pageable);
		//userRegistLogService.setloginTime(losg.getContent());
		ReponseDataTools.getClientReponseData(getReponseData(), losg);
		return addPageResponse(Config.STATUS_200, Config.MSG_200,
				getReponseData(), Config.RESP_MODE_10, "");
	}
	/**
	 * 
	 * @Title: create
	 * @Description: 创建广告
	 * @param request
	 * @param ad
	 * @return ResponseContainer
	 * @throws
	 */
	@RequestMapping(value="/create",method = RequestMethod.POST)
	public @ResponseBody ResponseContainer create(HttpServletRequest request,Ad ad){
		return addResponse(Config.STATUS_200, Config.MSG_200, adService.createAd(ad),Config.RESP_MODE_10, "");

	}
	/**
	 * 
	 * @Title: delete
	 * @Description:删除广告
	 * @param request
	 * @param ad
	 * @return ResponseContainer
	 * @throws
	 */
	@RequestMapping("/delete")
	public @ResponseBody ResponseContainer delete(HttpServletRequest request,Ad ad){
		adService.deleteAd(ad);
		return addResponse(Config.STATUS_200, Config.MSG_200, true,Config.RESP_MODE_10, "");

	}
	/**
	 * 
	 * @Title: adList
	 * @Description: 广告列表
	 * @param request
	 * @return ResponseContainer
	 * @throws
	 */
	@RequestMapping("/adList")
	public @ResponseBody ResponseContainer adList(HttpServletRequest request){
		return addResponse(Config.STATUS_200, Config.MSG_200, adService.adList(),Config.RESP_MODE_10, "");

	}

}

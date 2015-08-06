package operation.controller.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import operation.BaseController;
import operation.exception.XueWenServiceException;
import operation.pojo.common.ColudConfig;
import operation.pojo.pub.QueryModel;
import operation.service.common.ColudConfigService;

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

/**
 * 
* @ClassName: CloudConfigController
* @Description: 云服务配置信息
* @author yaoj
* @date 2015年1月5日 下午1:10:29
*
 */
@RestController
@RequestMapping("/cloudConfig")
public class CloudConfigController extends BaseController {

	private static final Logger logger = Logger.getLogger(CloudConfigController.class);
	
	@Autowired
	private ColudConfigService coludConfigService;
	
	/**
	 * 
	 * @Title: find
	 * @Description: 查找配置信息
	 * @param request
	 * @param cloudConfig
	 * @return ResponseContainer
	 * @throws
	 */
	@RequestMapping(value="/findByCkey")
	public @ResponseBody ResponseContainer findByCkey(HttpServletRequest request,QueryModel dm){
		try {
			String ckey = request.getParameter("ckey");
			Pageable pageable = PageRequestTools.pageRequesMake(dm);
			Page<ColudConfig> cloudConfig = coludConfigService.findByCkey(ckey,pageable);
			ReponseDataTools.getClientReponseData(getReponseData(), cloudConfig);
			return addPageResponse(Config.STATUS_200, Config.MSG_200, getReponseData(),Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			logger.error("==========业务错误，添加新的配置信息失败============" + e);
			return addResponse(e.getCode(), e.getMessage(), false, Config.RESP_MODE_10, "");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("==========未知错误，添加新的配置信息失败============" + e);
			return addResponse(Config.STATUS_505, Config.MSG_505, false, Config.RESP_MODE_10, "");
		}
	}
	
	/**
	 * 
	 * @Title: find
	 * @Description: 查找配置信息
	 * @param request
	 * @param cloudConfig
	 * @return ResponseContainer
	 * @throws
	 */
	@RequestMapping(value="/findOne")
	public @ResponseBody ResponseContainer findOne(HttpServletRequest request){
		try {
			String id = request.getParameter("id");
			ColudConfig cloudConfig = coludConfigService.findOneById(id);
			return addResponse(Config.STATUS_200, Config.MSG_200, cloudConfig,Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			logger.error("==========业务错误，添加新的配置信息失败============" + e);
			return addResponse(e.getCode(), e.getMessage(), false, Config.RESP_MODE_10, "");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("==========未知错误，添加新的配置信息失败============" + e);
			return addResponse(Config.STATUS_505, Config.MSG_505, false, Config.RESP_MODE_10, "");
		}
	}
	
	/**
	 * 
	 * @Title: add
	 * @Description: 添加新的配置信息
	 * @param request
	 * @param cloudConfig
	 * @return ResponseContainer
	 * @throws
	 */
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public @ResponseBody ResponseContainer add(HttpServletRequest request,ColudConfig cloudConfig){
		try {
			String baseUrlList = request.getParameter("baseUrlList");
			coludConfigService.add(cloudConfig, baseUrlList);
			return addResponse(Config.STATUS_200, Config.MSG_200, true,Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			logger.error("==========业务错误，添加新的配置信息失败============" + e);
			return addResponse(e.getCode(), e.getMessage(), false, Config.RESP_MODE_10, "");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("==========未知错误，添加新的配置信息失败============" + e);
			return addResponse(Config.STATUS_505, Config.MSG_505, false, Config.RESP_MODE_10, "");
		}
	}
	
	/**
	 * 
	 * @Title: modify
	 * @Description: 修改配置信息 不包含baseUrl,ckey
	 * @param request
	 * @param cloudConfig
	 * @return ResponseContainer
	 * @throws
	 */
	@RequestMapping(value="/modify",method = RequestMethod.POST)
	public @ResponseBody ResponseContainer modify(HttpServletRequest request,ColudConfig cloudConfig){
		try {
			String baseUrlList = request.getParameter("baseUrlList");
			coludConfigService.modify(cloudConfig,baseUrlList);
			return addResponse(Config.STATUS_200, Config.MSG_200, true,Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			logger.error("==========业务错误，修改配置信息失败============" + e);
			return addResponse(e.getCode(), e.getMessage(), false, Config.RESP_MODE_10, "");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("==========未知错误，修改配置信息失败============" + e);
			return addResponse(Config.STATUS_505, Config.MSG_505, false, Config.RESP_MODE_10, "");
		}
	}
	
	/**
	 * 
	 * @Title: remove
	 * @Description: 删除
	 * @param request
	 * @return ResponseContainer
	 * @throws
	 */
	@RequestMapping(value="/remove")
	public @ResponseBody ResponseContainer remove(HttpServletRequest request){
		try {
			String id = request.getParameter("id");
			coludConfigService.remove(id);
			return addResponse(Config.STATUS_200, Config.MSG_200, true,Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			logger.error("==========业务错误，删除配置信息失败============" + e);
			return addResponse(e.getCode(), e.getMessage(), false, Config.RESP_MODE_10, "");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("==========未知错误，删除配置信息失败============" + e);
			return addResponse(Config.STATUS_505, Config.MSG_505, false, Config.RESP_MODE_10, "");
		}
	}
	
	/**
	 * 
	 * @Title: find
	 * @Description: 查找配置信息
	 * @param request
	 * @param cloudConfig
	 * @return ResponseContainer
	 * @throws
	 */
	@RequestMapping(value="/findAll")
	public @ResponseBody ResponseContainer findAll(HttpServletRequest request){
		try {
			List<ColudConfig> cloudConfigs = coludConfigService.findAll();
			return addResponse(Config.STATUS_200, Config.MSG_200, cloudConfigs,Config.RESP_MODE_10, "");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("==========未知错误，添加新的配置信息失败============" + e);
			return addResponse(Config.STATUS_505, Config.MSG_505, false, Config.RESP_MODE_10, "");
		}
	}
}

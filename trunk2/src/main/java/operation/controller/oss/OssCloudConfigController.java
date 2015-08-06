package operation.controller.oss;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import operation.BaseController;
import operation.controller.common.CloudConfigController;
import operation.exception.XueWenServiceException;
import operation.pojo.common.ColudConfig;
import operation.pojo.pub.QueryModel;
import operation.pojo.user.User;
import operation.service.common.ColudConfigService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/oss/cloudConfig")
public class OssCloudConfigController extends BaseController {

	private static final Logger logger = Logger.getLogger(OssCloudConfigController.class);
	
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
	@RequestMapping(value="/configList")
	public @ResponseBody ResponseContainer configList(HttpServletRequest request,QueryModel dm){
		Pageable pageable = PageRequestTools.pageRequesMake(dm);
		Page<ColudConfig> configs = coludConfigService.findAll(pageable);
		ReponseDataTools.getClientReponseData(getReponseData(), configs);
		this.getReponseData().setResult(configs.getContent());
		return addPageResponse(Config.STATUS_200, Config.MSG_200, getReponseData(), Config.RESP_MODE_10, "");
	}
	@RequestMapping(value="/createConfig")
	public @ResponseBody ResponseContainer createConfig(HttpServletRequest request,ColudConfig coludConfig,String baseUrlList){
		try {
			return addResponse(Config.STATUS_200, Config.MSG_200, coludConfigService.add(coludConfig, baseUrlList),Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("==========未知错误，添加新的配置信息失败============" + e);
			return addResponse(Config.STATUS_505, Config.MSG_505, false, Config.RESP_MODE_10, "");
		}
		
	}
	@RequestMapping(value="/deleteConfig")
	public @ResponseBody ResponseContainer deleteConfig(HttpServletRequest request){
		String id=request.getParameter("configId");
		try {
			coludConfigService.remove(id);
			return addResponse(Config.STATUS_200, Config.MSG_200, true,  Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("==========未知错误，删除配置信息失败============" + e);
			return addResponse(Config.STATUS_505, Config.MSG_505, false, Config.RESP_MODE_10, "");
		}
	}
	@RequestMapping(value="/updateConfig")
	public @ResponseBody ResponseContainer updateConfig(HttpServletRequest request,ColudConfig config,String baseUrlList){
		try {
			coludConfigService.modify(config, baseUrlList);
			return addResponse(Config.STATUS_200, Config.MSG_200, true,  Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("==========未知错误，修改配置信息失败============" + e);
			return addResponse(Config.STATUS_505, Config.MSG_505, false, Config.RESP_MODE_10, "");
		}
	}
	@RequestMapping(value="/one")
	public @ResponseBody ResponseContainer findOne(HttpServletRequest request,String id){
		try {
			
			return addResponse(Config.STATUS_200, Config.MSG_200, coludConfigService.findOneById(id),  Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("==========未知错误，修改配置信息失败============" + e);
			return addResponse(Config.STATUS_505, Config.MSG_505, false, Config.RESP_MODE_10, "");
		}
	}
}

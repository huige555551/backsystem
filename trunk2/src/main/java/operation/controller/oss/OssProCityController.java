package operation.controller.oss;

import javax.servlet.http.HttpServletRequest;

import operation.BaseController;
import operation.pojo.activity.ProCity;
import operation.service.activity.ProCityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tools.Config;
import tools.ResponseContainer;

@RestController
@RequestMapping(value="/oss/proCity")
public class OssProCityController extends BaseController {
	@Autowired
	private ProCityService proCityService;
	@RequestMapping("/list")
	public @ResponseBody ResponseContainer list(HttpServletRequest request,String pId){
		return addResponse(Config.STATUS_200, Config.MSG_200, proCityService.findByPId(pId),Config.RESP_MODE_10, "");
	}
	
	@RequestMapping("/create")
	public @ResponseBody ResponseContainer create(HttpServletRequest request,ProCity proCity){
		return addResponse(Config.STATUS_200, Config.MSG_200, proCityService.save(proCity),Config.RESP_MODE_10, "");
	}
}

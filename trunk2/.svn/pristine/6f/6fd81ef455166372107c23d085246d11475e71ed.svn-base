package operation.controller.oss;
import operation.BaseController;
import operation.exception.XueWenServiceException;
import operation.pojo.log.UserRegLogS;
import operation.pojo.log.UserRegistLog;
import operation.pojo.pub.QueryModel;
import operation.service.log.UserRegLogService;
import operation.service.log.UserRegistLogService;

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
@RequestMapping("oss/user/log")
public class OssUserRegistLogController extends BaseController {
	@Autowired
	private UserRegistLogService userRegistLogService;
	@Autowired 
	private  UserRegLogService userRegLogService;
	
	/**
	 * 
	 * @Title: search
	 * @auther Tangli
	 * @Description: 邀请用户明细统计
	 * @param ctime  开始时间
	 * @param etime  结束时间
	 * @param vkey   邀请人搜索关键词
	 * @param ukey   注册人搜索关键词
	 * @param dm     分页信息
	 * @return ResponseContainer
	 * @throws
	 */
	@RequestMapping("search")
	public @ResponseBody  ResponseContainer search(long ctime,long etime,String vkey,String ukey,QueryModel dm){
		Pageable pageable=PageRequestTools.pageRequesMake(dm);
		Page<UserRegistLog>losg=userRegistLogService.search(ctime,etime,vkey,ukey,pageable);
		ReponseDataTools.getClientReponseData(getReponseData(), losg);
		userRegistLogService.setTotal(losg.getContent());
		return addPageResponse(Config.STATUS_200, Config.MSG_200, getReponseData(), Config.RESP_MODE_10, "");
	}
	
	/**
	 * 
	 * @Title: searchUser
	 * @auther Tangli
	 * @Description: 渠道注册用户统计
	 * @param ctime  开始时间
	 * @param etime  结束时间
	 * @param userKey 注册用户搜索关键词
	 * @param qdId   渠道id搜索关键词
	 * @param dm    分页信息
	 * @return ResponseContainer
	 * @throws
	 */
	@RequestMapping("searchUser")
	public @ResponseBody ResponseContainer searchUser(long ctime, long etime,
			String userKey, String qdId, QueryModel dm) {
		Pageable pageable = PageRequestTools.pageRequesMake(dm);
		Page<UserRegistLog> losg = userRegistLogService.searchQd(ctime, etime,
				userKey, qdId, pageable);
		userRegistLogService.setloginTime(losg.getContent());
		ReponseDataTools.getClientReponseData(getReponseData(), losg);
		return addPageResponse(Config.STATUS_200, Config.MSG_200,
				getReponseData(), Config.RESP_MODE_10, "");
	}
	
	/**
	 * 
	 * @Title: searchRlog
	 * @auther Tangli
	 * @Description: 搜索邀请用户 统计
	 * @param key    邀请用户搜索关键词
	 * @param ctime  开始时间
	 * @param etime  结束时间
	 * @param dm     分页信息
	 * @return ResponseContainer
	 * @throws
	 */
	@RequestMapping("searchRlog")
	public @ResponseBody  ResponseContainer searchRlog(String key,long ctime,long etime,QueryModel dm){
		Pageable pageable=PageRequestTools.pageRequesMake(dm);
		Page<UserRegLogS>logs=userRegLogService.searchByKey(key, ctime, etime, pageable);
		ReponseDataTools.getClientReponseData(getReponseData(), logs);
		userRegistLogService.setVUser(logs.getContent());
		return addPageResponse(Config.STATUS_200, Config.MSG_200, getReponseData(), Config.RESP_MODE_10, "");
	}
	
	@RequestMapping("save")
	public @ResponseBody  ResponseContainer save(UserRegistLog log){
		userRegistLogService.save(log);
		return addPageResponse(Config.STATUS_200, Config.MSG_200, getReponseData(), Config.RESP_MODE_10, "");
	}
	
	@RequestMapping("saveRlog")
	public @ResponseBody  ResponseContainer saveRlog(UserRegLogS log){
		userRegLogService.save(log);
		return addPageResponse(Config.STATUS_200, Config.MSG_200, getReponseData(), Config.RESP_MODE_10, "");
	}
	
	
	@RequestMapping("addViewCount")
	public @ResponseBody  ResponseContainer addViewCount(String a,QueryModel dm){
		try {
			return addResponse(Config.STATUS_200, Config.MSG_200,userRegistLogService.save(a), Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			// TODO Auto-generated catch block
			return addResponse(Config.STATUS_201, Config.MSG_201,false, Config.RESP_MODE_10, "");
		}	
	}
	
	
	
	
	

}

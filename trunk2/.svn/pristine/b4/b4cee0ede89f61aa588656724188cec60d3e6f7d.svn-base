package operation;

import java.util.Map;

import net.sf.json.JSONObject;
import operation.exception.XueWenServiceException;
import operation.pojo.user.User;
import operation.service.redis.OnlineUserRedisService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import tools.ReponseData;
import tools.ResponseContainer;
public class BaseController {

	/**
	 * ResponseContainer 对象多线程
	 */
	private ThreadLocal<ResponseContainer> resConLocal = new ThreadLocal<ResponseContainer>();
	
	
    /**
     * ReponseData 对象多线程管理
     */
	private ThreadLocal<ReponseData> rsDataLocal = new ThreadLocal<ReponseData>();
	
	@Autowired
	OnlineUserRedisService onlineUserRedisService;

	public ResponseContainer getResponseContainer() {

		if (resConLocal.get() == null) {

			ResponseContainer con = new ResponseContainer();
			resConLocal.set(con);
			return con;
		} else {
			return resConLocal.get();
		}
	}

	public ReponseData getReponseData() {

		if (rsDataLocal.get() == null) {
			ReponseData rsdata = new ReponseData();
			rsDataLocal.set(rsdata);
			return rsdata;
		} else {
			return rsDataLocal.get();
		}
	}

	/**
	 * 根据token获取在线用户的用户信息
	 * @param token
	 * @return
	 * @throws XueWenServiceException
	 */
	public User getCurrentUser(String token) throws XueWenServiceException{
		return onlineUserRedisService.getOnlineUser(token);
	}
	/**
	 * 判断是否是在线用户
	 * @param token
	 * @return
	 * @throws XueWenServiceException
	 */
	public boolean isOnlineUser(String token)throws XueWenServiceException{
	    return onlineUserRedisService.onlineUserExise(token);
		
	}
	/**
	 * 根据token删除在线用户信息
	 * @param token
	 * @throws XueWenServiceException
	 */
	public void deleteOnlineUser(String token)throws XueWenServiceException{
		onlineUserRedisService.removeOnlineUser(token);
	}
	public ResponseContainer addResponse(int status, String msg, Object obj,
			int mode, String md5) {
		ResponseContainer res = this.getResponseContainer();
		ReponseData rsdata = this.getReponseData();
		res.setStatus(status);
		res.setMsg(msg);
		rsdata.setResult(obj);
		rsdata.resetPageInfo();// 清除分页相关信息
		res.setData(rsdata);
		res.setMode(mode);
		res.setMd5(md5);
		return res;
	}

	public ResponseContainer addPageResponse(int status, String msg,
			ReponseData rspData, int mode, String md5) {
		ResponseContainer res = this.getResponseContainer();
		res.setStatus(status);
		res.setMsg(msg);
		res.setData(rspData);
		res.setMode(mode);
		res.setMd5(md5);
		return res;
	}
	/*
	 * public ResponseContainer reponse(Object rsData, int status){
	 * this.responseContainer.setStatus(status); this.rsData.setResult(rsData);
	 * this.responseContainer.setData(this.rsData);
	 * this.responseContainer.setMode(Config.RESP_MODE_10);
	 * this.responseContainer.setMd5(null); return this.responseContainer; }
	 * public ResponseContainer reponse(Object rsData, int status, String msg){
	 * this.responseContainer.setStatus(status);
	 * this.responseContainer.setMsg(msg); this.rsData.setResult(rsData);
	 * this.responseContainer.setData(this.rsData);
	 * this.responseContainer.setMode(Config.RESP_MODE_10);
	 * this.responseContainer.setMd5(null); return this.responseContainer; }
	 */
	/**
	 * 传入一个地址，Get 请求
	 * @param url
	 * @return
	 */
	public JSONObject getRestApiData(String url) {
		String courSharResoStr;
		RestTemplate restTemplate = new RestTemplate();
		courSharResoStr = restTemplate.postForObject(url, null, String.class);
		try {
			JSONObject objj = JSONObject.fromObject(courSharResoStr);
			return objj;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 传入Map，Post到对一个的地址（为了兼容之前传入的Map参数）
	 * @param url
	 * @param requestParams
	 * @return JSONObject
	 */
	public JSONObject getRestApiData(String url,
			Map<String, String> requestParams) {
		String courSharResoStr;
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>(); 
		map.setAll(requestParams);
		
		RestTemplate restTemplate = new RestTemplate();
		courSharResoStr = restTemplate.postForObject(url, map, String.class,"");
		try {
			JSONObject objj = JSONObject.fromObject(courSharResoStr);
			return objj;
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 传入MultiValueMap，Post给对应的地址
	 * @param url
	 * @param requestParams
	 * @return JSONObject
	 */
	public JSONObject getRestApiData(String url,
			MultiValueMap<String, String> requestParams) {
		String courSharResoStr;
		RestTemplate restTemplate = new RestTemplate();
		courSharResoStr = restTemplate.postForObject(url, requestParams, String.class, "");
		try {
			JSONObject objj = JSONObject.fromObject(courSharResoStr);
			return objj;
		} catch (Exception e) {
			return null;
		}
	}

}

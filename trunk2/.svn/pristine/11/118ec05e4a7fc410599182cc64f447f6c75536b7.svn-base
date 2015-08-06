package operation.controller.oss;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import operation.BaseController;
import operation.exception.XueWenServiceException;
import operation.pojo.category.Category;
import operation.pojo.drycargo.Drycargo;
import operation.pojo.feedback.FeedBack;
import operation.pojo.pub.QueryModelMul;
import operation.pojo.user.User;
import operation.service.feedback.FeedBackService;
import operation.service.user.UserService;

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
import tools.StringUtil;

@RestController
@RequestMapping("/oss/feedback")
public class OssFeedBackController extends BaseController{
	@Autowired
	public FeedBackService feedBackService;
	
	@Autowired
	public UserService userService;
	
	public OssFeedBackController(){
		
	}
	
	
	
	
	/**
	 * 查询干货列表 包括没有关联群组的
	 * 
	 * @param request
	 * @param dm
	 * @return
	 */
	@RequestMapping("searchFeedBack")
	public @ResponseBody ResponseContainer searchFeedBack(HttpServletRequest request, QueryModelMul dm) {
		// 根据请求参数封装一个分页信息对象
		// dm.setSort("c");
		List<String> sort = new ArrayList<String>();
		// sort.add("weightSort");
		// sort.add("viewCount");
		sort.add("ctime");
		dm.setSort(sort);
		Pageable pageable = PageRequestTools.pageRequesMake(dm);
		Page<FeedBack> feedback;
		
		List<FeedBack> lfb=new ArrayList<FeedBack>();
		try {
			String keywords = request.getParameter("keywords");
			feedback=feedBackService.findAllFeedBack(pageable);
			for(FeedBack fb:feedback){
				User user = userService.findOneByPhoneNumber(fb.getUserPhone());
				if(user!=null){
					fb.setUsername(user.getUserName());
				}
				
				lfb.add(fb);
			}
			
			ReponseDataTools.getClientReponseData(getReponseData(), feedback);
			this.getReponseData().setResult(lfb);
			
			return addPageResponse(Config.STATUS_200, Config.MSG_200, getReponseData(), Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			e.printStackTrace();
			return addResponse(e.getCode(), e.getMessage(), false, Config.RESP_MODE_10, "");
		} catch (Exception e) {
			e.printStackTrace();
			return addResponse(Config.STATUS_505, Config.MSG_505, false, Config.RESP_MODE_10, "");
		}
	}
	
	
	/**
	 * 查询干货列表 包括没有关联群组的
	 * 
	 * @param request
	 * @param dm
	 * @return
	 */
	@RequestMapping("deleteFeedBack")
	public @ResponseBody ResponseContainer deleteFeedBack(HttpServletRequest request) {
		// 根据请求参数封装一个分页信息对象
		// dm.setSort("c");
		 
		FeedBack feedback;
		boolean b=false;
		try {
			
			String feedbackid = request.getParameter("feedbackid");
			feedback=feedBackService.findOneFeedBack(feedbackid);
			if(feedBackService.deleteFeedBack(feedback)==true){
				b=true; 
			}
			 
			return addResponse(Config.STATUS_200, Config.MSG_200,b, Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			e.printStackTrace();
			return addResponse(e.getCode(), e.getMessage(), false, Config.RESP_MODE_10, "");
		} catch (Exception e) {
			e.printStackTrace();
			return addResponse(Config.STATUS_505, Config.MSG_505, false, Config.RESP_MODE_10, "");
		}
	}
	/**
	 * 反馈信息
	 * @param request
	 * @param category
	 * @return
	 */
	@RequestMapping("create")
	public @ResponseBody ResponseContainer create(HttpServletRequest request,
			FeedBack feedBack) {
		try{
		//	将中文字段转码
			if(null != feedBack.getContext()){
				if(!StringUtil.isEmpty(feedBack.getContext().toString())){
					feedBack.setContext((URLDecoder.decode(feedBack.getContext(),"UTF-8")));
				}
			}
		feedBack.setCtime(System.currentTimeMillis());
		FeedBack feed = feedBackService.save(feedBack);
		
		return addResponse(Config.STATUS_200,Config.MSG_TOADMIN_200,feed,Config.RESP_MODE_10,"");
	} catch (XueWenServiceException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return addResponse(e.getCode(),e.getMessage(),false,Config.RESP_MODE_10,"");
	} catch (Exception e){
		e.printStackTrace();
		return addResponse(Config.STATUS_505,Config.MSG_505,false,Config.RESP_MODE_10,"");
	}
	}


}

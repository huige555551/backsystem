package operation.controller.order;

import javax.servlet.http.HttpServletRequest;

import operation.BaseController;
import operation.exception.XueWenServiceException;
import operation.pojo.user.User;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tools.Config;

@RestController
@RequestMapping("/order")
@Configuration
public class OrderController extends BaseController{
	
	private static final Logger logger=Logger.getLogger(OrderController.class);
	
	public OrderController(){
		super();
	}
	
//	/**
//	 * 生成订单
//	 * @param request
//	 * @param dm
//	 * @return
//	 */
//	@RequestMapping("create")
//	public @ResponseBody Object advancePay(HttpServletRequest request) {
//		try {
//			String token = request.getParameter("token");
//			User currentUser = this.getCurrentUser(token);
//			String courseId = request.getParameter("courseId"); //商品编号
//			String price = request.getParameter("price");//购买价格
//			String spbill_create_ip = request.getParameter("spbill_create_ip");
//			String orderId = request.getParameter("orderId");
//			String groupId = request.getParameter("groupId");
//			String groupName = request.getParameter("groupName");
//			String groupLogoUrl = request.getParameter("groupLogoUrl");
//			String channel = request.getParameter("channel");//渠道
//			Object obj = this.payCreate(currentUser.getId(), currentUser.getNickName(), currentUser.getLogoURL(), courseId,price,spbill_create_ip,orderId,groupId,groupName,groupLogoUrl,Long.toString(currentUser.getUserNumber()),channel);
//			//return addResponse(Config.STATUS_200, Config.MSG_ORDERCREATE_200,obj, Config.RESP_MODE_10, "");
//			return obj;
//		} catch (XueWenServiceException e) {
//			return addResponse(e.getCode(), e.getMessage(), false,Config.RESP_MODE_10, "");
//		} catch (Exception e) {
//			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
//		}
//	}
//	/**
//	 * 调用支付系统进行订单生成
//	 * @param userId
//	 * @param nickName
//	 * @param logoUrl
//	 * @param courseId
//	 * @param price
//	 * @return
//	 */
//	private Object payCreate(String userId, String nickName, String logoUrl,String courseId, String price,String spbill_create_ip,String orderId,String groupId,String groupName,String groupLogoUrl,String userNumber,String channel) {
//		String url = Config.YXTSERVER3 + "order/create?userId=" + userId+"&nickName="+nickName+"&logoUrl="+logoUrl+"&courseId="+courseId+"&price="+price+
//				"&spbill_create_ip="+spbill_create_ip+"&orderId="+orderId+"&groupId="+groupId+"&groupName="+groupName+"&groupLogoUrl="+groupLogoUrl
//				+"&userNumber="+userNumber+"&channel="+channel;
//		return getRestApiData(url);
//	}
//	
//	/**
//	 * 我的订单
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("myOrder")
//	public @ResponseBody Object myOrder(HttpServletRequest request) {
//		try {
//			String token = request.getParameter("token");
//			User currentUser = this.getCurrentUser(token);
//			String orderStatus = request.getParameter("orderStatus");//9代表全部 0 代表未付款 2 代表已付款
//			
//			String n = request.getParameter("n");
//			if (n == null) {
//				n = "0";
//			}
//			// 每页条数
//			String s = request.getParameter("s");
//
//			if (s == null) {
//				s = "10";
//			}
//			Object obj = this.payMyOrder(currentUser.getId(),n,s,orderStatus);
//			//return addResponse(Config.STATUS_200, Config.MSG_CREATE_200,obj, Config.RESP_MODE_10, "");
//			return obj;
//		} catch (XueWenServiceException e) {
//			return addResponse(e.getCode(), e.getMessage(), false,Config.RESP_MODE_10, "");
//		} catch (Exception e) {
//			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
//		}
//	}
//	/**
//	 * 调用支付订单系统查询我的订单
//	 * @param userId
//	 * @param n
//	 * @param s
//	 * @return
//	 */
//	private Object payMyOrder(String userId,String n,String s,String orderStatus) {
//		String url = Config.YXTSERVER3 + "order/myOrder?userId=" + userId+"&n="+n+"&s="+s+"&orderStatus="+orderStatus;
//		return getRestApiData(url);
//	}
//	
//	/**
//	 * 订单详情
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("orderDetail")
//	public @ResponseBody Object orderDetail(HttpServletRequest request) {
//		try {
//			String token = request.getParameter("token");
//			User currentUser = this.getCurrentUser(token);
//			String orderId = request.getParameter("orderId"); //商品编号
//			Object obj = this.payOrderDetail(orderId);
//			return addResponse(Config.STATUS_200, Config.MSG_TOADMIN_200,obj, Config.RESP_MODE_10, "");
//			//return obj;
//		} catch (XueWenServiceException e) {
//			return addResponse(e.getCode(), e.getMessage(), false,Config.RESP_MODE_10, "");
//		} catch (Exception e) {
//			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
//		}
//	}
//	
//	/**
//	 * 调用支付订单系统订单详情
//	 * @param userId
//	 * @param n
//	 * @param s
//	 * @return
//	 */
//	private Object payOrderDetail(String orderId) {
//		String url = Config.YXTSERVER3 + "order/orderDetail?orderId=" + orderId;
//		return getRestApiData(url);
//	}
//	
//	/**
//	 * 修改订单
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("updateOrder")
//	public @ResponseBody Object updateOrder(HttpServletRequest request) {
//		try {
//			String token = request.getParameter("token");
//			User currentUser = this.getCurrentUser(token);
//			String orderId = request.getParameter("orderId"); //订单编号
//			String payType = request.getParameter("payType"); //支付类型
//			String channel = request.getParameter("channel");//来源渠道
//			Object obj = this.payUpdateOrder(orderId,payType,currentUser,channel);
//			//增加用户购买记录
//			if("2".equals(payType)){
//				return addResponse(Config.STATUS_200, Config.MSG_PAY_200,obj, Config.RESP_MODE_10, "");
//			}else{
//				return addResponse(Config.STATUS_200, Config.MSG_PAY_201,obj, Config.RESP_MODE_10, "");
//			}
//			//return obj;
//		} catch (XueWenServiceException e) {
//			return addResponse(e.getCode(), e.getMessage(), false,Config.RESP_MODE_10, "");
//		} catch (Exception e) {
//			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
//		}
//	}
//	
//	/**
//	 * 调用支付用户中心修改订单状态
//	 * @param orderId
//	 * @param payType
//	 * @return
//	 */
//	private Object payUpdateOrder(String orderId,String payType,User user,String channel) {
//		String url = Config.YXTSERVER3 + "order/updateOrder?orderId=" + orderId+"&payType="+payType+"&userId="+user.getId()+"&nickName="+user.getNickName()+"&logoUrl="+user.getLogoURL()+"&channel="+channel;
//		return getRestApiData(url);
//	}
//	
//	
//
//	/**
//	 * 取消订单
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("cancel")
//	public @ResponseBody Object cancel(HttpServletRequest request) {
//		try {
//			String token = request.getParameter("token");
//			User currentUser = this.getCurrentUser(token);
//			String orderId = request.getParameter("orderId"); //订单编号
//			Object obj = this.payCancelOrder(orderId);
//			return addResponse(Config.STATUS_200, Config.MSG_ORDERCANCEL_200,obj, Config.RESP_MODE_10, "");
//		} catch (XueWenServiceException e) {
//			return addResponse(e.getCode(), e.getMessage(), false,Config.RESP_MODE_10, "");
//		} catch (Exception e) {
//			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
//		}
//	}
//	/**
//	 * 调用支付中心系统取消订单
//	 * @param orderId
//	 * @return
//	 */
//	private Object payCancelOrder(String orderId) {
//		String url = Config.YXTSERVER3 + "order/cancel?orderId=" + orderId;
//		return getRestApiData(url);
//	}
//	
//	/**
//	 * 删除订单
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("delete")
//	public @ResponseBody Object delete(HttpServletRequest request) {
//		try {
//			String token = request.getParameter("token");
//			User currentUser = this.getCurrentUser(token);
//			String orderId = request.getParameter("orderId"); //订单编号
//			Object obj = this.payDeleteOrder(orderId);
//			return addResponse(Config.STATUS_200, Config.MSG_ORDERDELE_200,obj, Config.RESP_MODE_10, "");
//		} catch (XueWenServiceException e) {
//			return addResponse(e.getCode(), e.getMessage(), false,Config.RESP_MODE_10, "");
//		} catch (Exception e) {
//			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
//		}
//	}
//	/**
//	 * 调用支付中心系统取消订单
//	 * @param orderId
//	 * @return
//	 */
//	private Object payDeleteOrder(String orderId) {
//		String url = Config.YXTSERVER3 + "order/delete?orderId=" + orderId;
//		return getRestApiData(url);
//	}
//	
//	
//	/**
//	 * 刷新订单
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("queryOrder")
//	public @ResponseBody Object queryOrder(HttpServletRequest request) {
//		try {
//			String token = request.getParameter("token");
//			User currentUser = this.getCurrentUser(token);
//			String orderId = request.getParameter("orderId"); //订单编号
//			Object obj = this.payQuertOrder(orderId);
//			return addResponse(Config.STATUS_200, Config.MSG_REFRESH_200,obj, Config.RESP_MODE_10, "");
//		} catch (XueWenServiceException e) {
//			return addResponse(e.getCode(), e.getMessage(), false,Config.RESP_MODE_10, "");
//		} catch (Exception e) {
//			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
//		}
//	}
//	/**
//	 * 调用支付中心系统取消订单
//	 * @param orderId
//	 * @return
//	 */
//	private Object payQuertOrder(String orderId) {
//		String url = Config.YXTSERVER3 + "order/queryOrder?orderId=" + orderId;
//		return getRestApiData(url);
//	}
//	/**
//	 * 删除订单
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("replayOrder")
//	public @ResponseBody Object replayOrder(HttpServletRequest request) {
//		try {
//			String token = request.getParameter("token");
//			User currentUser = this.getCurrentUser(token);
//			String orderId = request.getParameter("orderId"); //订单编号
//			String appKey = request.getParameter("appKey"); //订单编号
//			String message = request.getParameter("message"); //订单编号
//			String lat = request.getParameter("lat"); //订单编号
//			String lng = request.getParameter("lng"); //订单编号
//			String score = request.getParameter("score"); //订单编号
//			return this.replayOrder(orderId,appKey,message,lat,lng,score,currentUser.getId());
//		} catch (XueWenServiceException e) {
//			return addResponse(e.getCode(), e.getMessage(), false,Config.RESP_MODE_10, "");
//		} catch (Exception e) {
//			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
//		}
//	}
//	
//	public Object replayOrder(String orderId,String appKey,String message,String lat,String lng,String score,String userId){
//		String url = Config.YXTSERVER3 + "orderPost/replayOrder?authorId=" + userId+"&orderId="+orderId+"&appKey="+appKey+"&message="+message+
//				"&lat="+lat+"&lng="+lng+"&score="+score;
//		return getRestApiData(url);
//	}
//	
//	
//	@RequestMapping("orderPost")
//	public @ResponseBody Object orderPost(HttpServletRequest request) {
//		try {
//			String token = request.getParameter("token");
//			User currentUser = this.getCurrentUser(token);
//			String courseId = request.getParameter("courseId");//9代表全部 0 代表未付款 2 代表已付款
//			
//			String n = request.getParameter("n");
//			if (n == null) {
//				n = "0";
//			}
//			// 每页条数
//			String s = request.getParameter("s");
//
//			if (s == null) {
//				s = "10";
//			}
//			Object obj = this.orderPost(courseId);
//			//return addResponse(Config.STATUS_200, Config.MSG_CREATE_200,obj, Config.RESP_MODE_10, "");
//			return obj;
//		} catch (XueWenServiceException e) {
//			return addResponse(e.getCode(), e.getMessage(), false,Config.RESP_MODE_10, "");
//		} catch (Exception e) {
//			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
//		}
//	}
//	
//	public Object orderPost(String courseId){
//		String url = Config.YXTSERVER3 + "orderPost/orderPost?courseId=" + courseId;
//		return getRestApiData(url);
//	}
	
	
	

}

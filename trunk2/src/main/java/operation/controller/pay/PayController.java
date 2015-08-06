package operation.controller.pay;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import operation.BaseController;
import operation.exception.XueWenServiceException;
import operation.pojo.user.User;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tools.Config;
import tools.ResponseContainer;

@RestController
@RequestMapping("/pay")
@Configuration
public class PayController extends BaseController{
	
	private static final Logger logger=Logger.getLogger(PayController.class);
	
	public PayController(){
		super();
	}
	
	/**
	 * 预支付
	 * @param request
	 * @param dm
	 * @return
	 */
	@RequestMapping("advancePay")
	public @ResponseBody Object advancePay(HttpServletRequest request) {
		try {
			String token = request.getParameter("token");
			User currentUser = this.getCurrentUser(token);
			String body = request.getParameter("body"); //商品描述
			String total_fee = request.getParameter("total_fee");//商品价格
			String spbill_create_ip = request.getParameter("spbill_create_ip");//购买者ip地址
			String out_trade_no = request.getParameter("out_trade_no");//商品订单号
			Object obj = this.payAdvancePay(body, total_fee, spbill_create_ip, out_trade_no);
			//return addResponse(Config.STATUS_200, Config.MSG_CREATE_200,obj, Config.RESP_MODE_10, "");
			return obj;
		} catch (XueWenServiceException e) {
			return addResponse(e.getCode(), e.getMessage(), false,Config.RESP_MODE_10, "");
		} catch (Exception e) {
			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
		}
	}
	/**
	 * 调用支付中心进行预支付
	 * @param boxPostId
	 * @param sourceType
	 * @param sourceId
	 * @param ctime
	 * @return
	 */
	private JSONObject payAdvancePay(String body,String total_fee,String spbill_create_ip,String out_trade_no) {
		String url = Config.YXTSERVER3 + "unifiedOrder/getPrepayId?body=" + body+"&total_fee="+total_fee+"&spbill_create_ip="+spbill_create_ip+"&out_trade_no="+out_trade_no;
		return getRestApiData(url);
	}

}

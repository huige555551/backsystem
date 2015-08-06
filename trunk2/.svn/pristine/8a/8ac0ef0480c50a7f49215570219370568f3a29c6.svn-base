package operation;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import operation.repo.user.UserRepository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import tools.Config;
import tools.ResponseContainer;
@Component
public class UserSecurityInterceptor extends BaseController  implements HandlerInterceptor{

	private static final Logger logger=Logger.getLogger("railyFile");
	@Autowired
	public UserRepository userRepo;
	
	public UserSecurityInterceptor() {
		super();
	}

	@Override
	public void afterCompletion(HttpServletRequest req,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String token = request.getParameter("token");
		String udid = request.getParameter("udid");
		
		StringBuffer reString=new StringBuffer();
		for (Enumeration<String> paramNames = request.getParameterNames(); paramNames.hasMoreElements();) {
			String paramName = paramNames.nextElement();
			String[] values = request.getParameterValues(paramName);

			 if(values!=null && values.length >0)
			{
				StringBuffer s=new StringBuffer();
				if(values.length==1)
				{
					s.append(values[0]);
				}
				else
				{
					for(String temp:values)
					{
						s.append(temp).append(",");
					}
				}
				
				reString.append(paramName+"="+s.toString()+"&");
			}
			 else
			 {
				 reString.append(paramName+"= null");
			 }
			
			}		
		
		logger.info("IP=="+request.getRemoteAddr()+"||token=="
				+ token
				+ "||deviceId=="
				+ udid
				+ "||time=="
				+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(new Date()) + "||restUrl=="
				+ request.getRequestURI() + "||param==" + reString);
				
		if (!this.isOnlineUser(token)) {
			logger.info("===========token验证不通过=-=====!=");
			ResponseContainer responseContainer=getResponseContainer();
			getReponseData().setResult(false);
			responseContainer.setStatus(Config.STATUS_203);
			responseContainer.setMsg(Config.MSG_203);
			responseContainer.setData(getReponseData());
			responseContainer.setMode(Config.RESP_MODE_10);
			responseContainer.setMd5("");
			JSONObject object = JSONObject.fromObject(responseContainer);
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter out =  response.getWriter();
			out.println(object);
			return false;
		} else {
			return true;
//			logger.info("map中udid"+currentUser.getUdid());
//			if (currentUser.getUdid().equals(udid)) {
//				return true;
//			} else {
//				logger.info("===============udid验证不通过");
//				rsData.setResult(false);
//				responseContainer.setStatus(Config.STATUS_203);
//				responseContainer.setMsg(Config.MSG_203);
//				responseContainer.setData(rsData);
//				responseContainer.setMode(Config.RESP_MODE_10);
//				responseContainer.setMd5("");
//				JSONObject object = JSONObject.fromObject(responseContainer);
//				response.setContentType("application/json;charset=UTF-8");
//				PrintWriter out =  response.getWriter();
//				out.println(object);
//				return false;
//			}
		}

	}

}

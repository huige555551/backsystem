package operation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class UserLogInterceptor extends BaseController implements
		HandlerInterceptor {
	private static final Logger logger=Logger.getLogger("railyFile");
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
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
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}

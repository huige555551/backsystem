package operation;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tools.Config;
import tools.ResponseContainer;

@RestController
@RequestMapping("/oss")
@Configuration
public class OssController extends BaseController {

	private static String IP1="127.0.0.1";
	private static String IP2="192.168.100.2";
	
	
	/**
	 * 创建主题
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="checkip")
	public @ResponseBody ResponseContainer checkip(HttpServletRequest request) {
		try {
			String ossipString=request.getRemoteAddr();
			Boolean b=false;
			
			if (IP1.equals(ossipString)||IP2.equals(ossipString)) {
				b=true;
			}
			return addResponse(Config.STATUS_200, Config.MSG_CREATE_200, b,Config.RESP_MODE_10, "");
		}catch (Exception e) {
//			e.printStackTrace();
			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
		}
	}
}

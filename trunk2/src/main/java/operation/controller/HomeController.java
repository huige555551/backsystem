package operation.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import operation.BaseController;
import operation.pojo.user.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tools.Config;
import tools.ResponseContainer;

/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping("/testredis")
public class HomeController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired 
	StringRedisTemplate stringRedisTemplate;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/rtt", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	
	@RequestMapping("pushredis")
	public  void pushDataToRedis(
			HttpServletRequest request, String key,String value) {
		
		stringRedisTemplate.opsForList().leftPush(key, value); 
	}
	
	@RequestMapping("popredis")
	public  ResponseContainer popDataFromRedis(
			HttpServletRequest request, String key) {
		
		String result = stringRedisTemplate.opsForList().leftPop(key);
		
		return addResponse(Config.STATUS_200, Config.MSG_TOADMIN_200, result,
				Config.RESP_MODE_10, "");
	}
	
	
	
}

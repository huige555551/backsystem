package operation.service.rabbitmq;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import operation.exception.XueWenServiceException;
import operation.pojo.dynamic.GroupDynamic;
import operation.pojo.email.YxtRegMail;
import operation.pojo.push.ZtiaoPush;


//import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
@Component
@Configuration
@EnableAutoConfiguration
public class RabbitmqService {
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	/**
	 * 向rabbitmq中发送消息
	 * @param queueName 队列名称
	 * @param message 字符串消息
	 * @throws XueWenServiceException
	 */
	public void sendMessage(String queueName,String message)throws XueWenServiceException{
		rabbitTemplate.convertAndSend(queueName, message);
	}
	
	/**
	 * 向rabbitmq中发送可序列化对象
	 * @param queueName
	 * @param obj
	 * @throws XueWenServiceException
	 */
	public void sendObject(String queueName,Object obj)throws XueWenServiceException{
		rabbitTemplate.convertAndSend(queueName, obj);
	}
	
	/**
	 * 
	 * @Title: sendRegexMessage
	 * @Description: 发送过滤消息
	 * @param id  对象id
	 * @param type 对象类别 参考tag
	 * @throws Exception void
	 * @throws
	 */
	public void sendRegexMessage(String id,String type) throws Exception{
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("id", id);
		map.put("type", type);
		String message=JSONObject.fromObject(map).toString();
		sendMessage("regexInfo",message);	
	}
	
	/**
	 * 
	 * @Title: sendSMSMessage
	 * @auther Tangli
	 * @Description: 发送短信队列
	 * @param phone
	 * @param code
	 * @throws Exception void
	 * @throws
	 */
	public void sendSMSMessage(String phone,String code) throws Exception{
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("phone", phone);
		map.put("code", code);
		String message=JSONObject.fromObject(map).toString();
		sendMessage("regexInfo",message);	
	}
	
	/**
	 * 
	 * @Title: sendMailMessage
	 * @auther Tangli
	 * @Description: 发送邮件消息队列
	 * @param yxtRegMail
	 * @throws Exception void
	 * @throws
	 */
	public void sendMailMessage(YxtRegMail yxtRegMail) throws Exception{
		String message=JSONObject.fromObject(yxtRegMail).toString();
		sendMessage("Mail",message);	
	}
	/**
	 * 发送推送
	 * @author hjn
	 * @param ztiaoPush
	 * @throws Exception
	 */
	public void sendPushMessage(ZtiaoPush ztiaoPush) throws Exception{
		String message=JSONObject.fromObject(ztiaoPush).toString();
		sendMessage("push",message);	
	}
	/**
	 * 发送
	 * @param groupDynamic
	 * @throws Exception
	 */
	public void sendGroupDynamic(String operation,String groupId,String sourcId,String userId,GroupDynamic groupDynamic) throws Exception{
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("operation", operation);
		map.put("groupId", groupId);
		map.put("sourcId", sourcId);
		map.put("userId", userId);
		map.put("groupDynamic", groupDynamic);
		String message=JSONObject.fromObject(map).toString();
		sendMessage("groupDynamic",message);	
	}
	/**
	 * 
	 * @param sourceId
	 * @param likeType
	 * @throws Exception
	 */
	public void sendLike(String sourceId,String type,int num) throws Exception{
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("sourceId", sourceId);
		map.put("type", type);
		map.put("num", num);
		String message=JSONObject.fromObject(map).toString();
		sendMessage("like",message);	
	}
	/**
	 * 发送群组更新或用户信息更新命令
	 * @param sourceId
	 * @param updateType
	 * @throws Exception
	 */
	public void sendUpdateInfo(String sourceId,String updateType) throws Exception{
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("sourceId", sourceId);
		map.put("updateType", updateType);
		String message=JSONObject.fromObject(map).toString();
		sendMessage("updateInfo",message);	
	}
}

package operation.service.jpush;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import net.sf.json.JSONObject;
import operation.exception.XueWenServiceException;
import operation.pojo.user.MessageContext;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import tools.Config;
import tools.StringUtil;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
//import cn.jpush.api.common.APIConnectionException;
//import cn.jpush.api.common.APIRequestException;

@Service
@Component
@Configuration
public class JPushService {
	private static final Logger logger=Logger.getLogger(JPushService.class);
	@Inject Environment env;
	private JPushClient jpush;
	/**
	 * 获取极光连接
	 * @return
	 * @throws XueWenServiceException
	 */
	public JPushClient getJPushClient()throws XueWenServiceException{
		JPushClient jpush =new JPushClient(env.getProperty("jpush.masterSecret"), env.getProperty("jpush.appKey"));
//		JPushClient jpush =new JPushClient("af052fa33d9962941060cee8","e766ed8fba8461ce1ed03c05");
//		JPushClient jpush =new JPushClient("9f7b00454fb809af0cb04fe4","1cac47eac2defe6e1a36eff4");
		return jpush;
	}
	
	public void sendToUser(String machine,String msg,String alia)throws XueWenServiceException{
		try {
			
			PushResult pr=null;
			if(machine==null || machine.equals("ios")){
				pr=getJPushClient().sendIosNotificationWithAlias(msg,null,alia);
			}else{
				pr=getJPushClient().sendAndroidNotificationWithAlias(null,msg, null, alia);
			}
			if(pr == null || !pr.isResultOK()){
				throw new XueWenServiceException(Config.STATUS_201,Config.MSG_PUSHFAIL_201,null);
			}
//			jpush.sendAndroidNotificationWithAlias(title, alert, extras, alias)
//			jpush.sendIosNotificationWithAlias(alert, extras, alias)
			
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("=============推送通知异常："+e);
			throw new XueWenServiceException(Config.STATUS_201,Config.MSG_PUSHFAIL_201,null);
		}
	}
	
	/**
	 * 根据别名推送通知
	 * @param machine
	 * @throws XueWenServiceException
	 */
	public void sendNotice(String machine,List<String> alias,String notice,Map<String,String> extras)throws XueWenServiceException{
		try {
			PushPayload ppl=null;
			if(machine==null){
				ppl=buildPushObject_all_alias_alert(alias,notice);
			}else if(machine.equals("ios")){
				ppl=buildPushObject_ios_alertWithExtras(alias,notice,extras);
			}else if(machine.equals("android")){
				ppl=buildPushObject_android_alertWithExtras(alias,notice,extras);
			}else{
				ppl=buildPushObject_all_alias_alert(alias,notice);
			}
			getJPushClient().sendPush(ppl);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("=============推送通知异常："+e);
			throw new XueWenServiceException(Config.STATUS_201,Config.MSG_PUSHFAIL_201,null);
		}
	}
	
	
	/**
	 * 根据别名推送通知和消息
	 * @param machine
	 * @throws XueWenServiceException
	 */
	public void sendNoticeAndMsg(List<String> alias,String notice,String msg,Map<String,String> extras)throws XueWenServiceException{
		try {
			PushPayload ppl=buildPushObject_all_alias_alertAndMsg(alias,notice,msg,extras);
			logger.info("===============请求的地址："+ppl.toJSON());
			getJPushClient().sendPush(ppl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("=============推送通知异常："+e);
//			throw new XueWenServiceException(Config.STATUS_201,Config.MSG_PUSHFAIL_201,null);
		}
	}
	
	/**
	 * 根据别名推送通知和消息
	 * @param machine
	 * @throws XueWenServiceException
	 */
	public void sendNoticeAndMsg(String alias,String notice,String msg,Map<String,String> extras)throws XueWenServiceException{
		try {
			PushPayload ppl=buildPushObject_all_alias_alertAndMsg(alias,notice,msg,extras);
			getJPushClient().sendPush(ppl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("=============推送通知异常："+e);
//			throw new XueWenServiceException(Config.STATUS_201,Config.MSG_PUSHFAIL_201,null);
		}
	}
	
	
	
	/**
	 * 根据标签推送通知和消息
	 * @param machine
	 * @throws XueWenServiceException
	 */
	public void sendToTagsNoticeAndMsg(List<String> tags,String notice,String msg,Map<String,String> extras)throws XueWenServiceException{
		try {
			PushPayload ppl=buildPushObject_all_tags_alertAndMsg(tags,notice,msg,extras);
			getJPushClient().sendPush(ppl);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("=============推送通知异常："+e);
			throw new XueWenServiceException(Config.STATUS_201,Config.MSG_PUSHFAIL_201,null);
		}
	}
	
	/**
	 * 快捷地构建推送对象：所有平台，所有设备，内容为 ALERT 的通知
	 * @param alert
	 * @return
	 */
	 public static PushPayload buildPushObject_all_all_alert(String alert) {
	        return PushPayload.alertAll(alert);
	 }
	 
	 public static PushPayload buildPushObject_all_tags_alertAndMsg(List<String> tags,String alert,String msg,Map<String,String> extra) {
	        return PushPayload.newBuilder()
	                .setPlatform(Platform.all())
	                .setAudience(Audience.tag(tags))
	                .setNotification(Notification.alert(alert))
	                .setMessage(Message.newBuilder()
	                        .setMsgContent(msg)
	                        .addExtras(extra)
	                        .build())
	                .build();
	        
	 }
	 
	 /**
	  * 构建推送对象：所有平台，推送目标是别名为 "alias1"，通知内容为 ALERT
	  * @param alias
	  * @param alert
	  * @return
	  */
	 public static PushPayload buildPushObject_all_alias_alert(List<String> alias,String alert) {
	        return PushPayload.newBuilder()
	                .setPlatform(Platform.all())
	                .setAudience(Audience.alias(alias))
	                .setNotification(Notification.alert(alert))
	                .build();
	 }
	 
	 
	 public static PushPayload buildPushObject_all_alias_alertAndMsg(String alia,String alert,String msg,Map<String,String> extra) {
	        return PushPayload.newBuilder()
	                .setPlatform(Platform.ios())
	                .setAudience(Audience.all())
	                .setNotification(Notification.alert(alert))
	                .setMessage(Message.newBuilder()
	                        .setMsgContent(msg)
	                        .addExtras(extra)
	                        .build())
	                .build();
	 }
	 
	 /**
	  * 构建推送对象：所有平台，推送目标是别名为 "alias1"，通知内容为 ALERT,消息内容为msg
	  * @param alias
	  * @param alert
	  * @param msg
	  * @param extra
	  * @return
	  */
	 public static PushPayload buildPushObject_all_alias_alertAndMsg(List<String> alias,String alert,String msg,Map<String,String> extra) {
		 return PushPayload.newBuilder()
	                .setPlatform(Platform.all())
	                .setAudience(Audience.alias(alias))
	                .setNotification(Notification.newBuilder()
	                        .addPlatformNotification(IosNotification.newBuilder()
	                                .setAlert(alert)
	                                .addExtras(extra)
	                                .build())
	                        .build())
	                .setMessage(Message.newBuilder()
                        .setMsgContent(msg)
                        .addExtras(extra)
                        .build())
	                .build();
	 }
	 
	 
	 /**
	  * 构建推送对象：平台是 Android，目标是 tag 为 "tag1" 的设备，内容是 Android 通知 ALERT，并且标题为 TITLE。
	  * @param tag
	  * @param alert
	  * @param title
	  * @return
	  */
	 public static PushPayload buildPushObject_android_tag_alertWithTitle(List<String> tags,String alert,String title) {
	        return PushPayload.newBuilder()
	                .setPlatform(Platform.android())
	                .setAudience(Audience.tag(tags))
	                .setNotification(Notification.android(alert, title, null))
	                .build();
	 }
	 
	 /**
	  * 构建推送对象：平台是 ios，目标是 别名是aliases  的设备，内容是 ios 通知 alert，key=value。
	  * @param aliases
	  * @param alert
	  * @param key
	  * @param value
	  * @return
	  */
	 public static PushPayload buildPushObject_ios_alertWithExtras(List<String> aliases,String alert,Map<String,String> extra) {
	        return PushPayload.newBuilder()
	                .setPlatform(Platform.ios())
	                .setAudience(Audience.alias(aliases))
	                .setNotification(Notification.newBuilder()
	                        .addPlatformNotification(IosNotification.newBuilder()
	                                .setAlert(alert)
	                                .addExtras(extra)
	                                .build())
	                        .build())
	                 .setOptions(Options.newBuilder()
	                         .setApnsProduction(true)
	                         .build())
	                 .build();
	 }
	 
	 /**
	  * 构建推送对象：平台是 android，目标是 别名是aliases  的设备，内容是 android 通知 alert，key=value。
	  * @param aliases
	  * @param alert
	  * @param key
	  * @param value
	  * @return
	  */
	 public static PushPayload buildPushObject_android_alertWithExtras(List<String> aliases,String alert,Map<String,String> extra) {
	        return PushPayload.newBuilder()
	                .setPlatform(Platform.android())
	                .setAudience(Audience.alias(aliases))
	                .setNotification(Notification.newBuilder()
	                        .addPlatformNotification(AndroidNotification.newBuilder()
	                                .setAlert(alert)
	                                .addExtras(extra)
	                                .build())
	                        .build())
	                 .setOptions(Options.newBuilder()
	                         .setApnsProduction(true)
	                         .build())
	                 .build();
	 }
	 
	 /**
	  * 构建推送对象：平台是 iOS，推送目标是 "tag1", "tag_all" 的并集，推送内容同时包括通知与消息 - 通知信息是 ALERT，角标数字为 5，通知声音为 "happy"，并且附加字段 from = "JPush"；消息内容是 MSG_CONTENT。通知是 APNs 推送通道的，消息是 JPush 应用内消息通道的。APNs 的推送环境是“生产”（如果不显式设置的话，Library 会默认指定为开发）
	  * @param tags
	  * @param alert
	  * @param sound
	  * @param extra
	  * @param msg
	  * @return
	  */
	 public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage(List<String> tags,String alert,String sound,Map<String,String> extra,String msg) {
	        return PushPayload.newBuilder()
	                .setPlatform(Platform.ios())
	                .setAudience(Audience.tag_and(tags))
	                .setNotification(Notification.newBuilder()
	                        .addPlatformNotification(IosNotification.newBuilder()
	                                .setAlert(alert)
	                                .setSound(sound)
	                                .addExtras(extra)
	                                .build())
	                        .build())
	                 .setMessage(Message.content(msg))
	                 .setOptions(Options.newBuilder()
	                         .setApnsProduction(true)
	                         .build())
	                 .build();
	 }
	 
	 /**
	  * 构建推送对象：平台是 Andorid 与 iOS，推送目标是 （"tag1" 与 "tag2" 的交集）并（"alias1" 与 "alias2" 的交集），推送内容是 - 内容为 MSG_CONTENT 的消息，并且附加字段 from = JPush。
	  * @param tags
	  * @param alias
	  * @param msg
	  * @param extra
	  * @return
	  */
	 public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras(List<String> tags,List<String> alias,String msg,Map<String,String> extra)  {
	        return PushPayload.newBuilder()
	                .setPlatform(Platform.android_ios())
	                .setAudience(Audience.newBuilder()
	                        .addAudienceTarget(AudienceTarget.tag(tags))
	                        .addAudienceTarget(AudienceTarget.alias(alias))
	                        .build())
	                .setMessage(Message.newBuilder()
	                        .setMsgContent(msg)
	                        .addExtras(extra)
	                        .build())
	                .build();
	 }
	 
	
	public static void main(String[] args) {
		try {
			List<String> alilas=new ArrayList<String>();
			alilas.add("53df91e8e4b040f309809392");
			JPushService jp=new JPushService();
			Map<String,String> extras=new HashMap<String, String>();
			extras.put("0","1000");
			MessageContext mc=new MessageContext();
			mc.setContext("苹果加");
			mc.setGroupId("11");
			mc.setGroupName("22");
			JSONObject js=JSONObject.fromObject(mc);
			extras.put("context",js.toString());
			jp.sendNoticeAndMsg(alilas,"申请入群",js.toString(), extras);
		} catch (XueWenServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

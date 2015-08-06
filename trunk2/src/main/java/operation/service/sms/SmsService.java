package operation.service.sms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import operation.exception.XueWenServiceException;
import operation.pojo.sms.Sms;
import operation.pojo.user.User;
import operation.repo.sms.SmsRepository;
import operation.service.user.UserService;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import tools.Config;
import tools.MD5Util;
import tools.StringUtil;


@Service
@Component
@EnableScheduling
public class SmsService {

	private static final Logger logger=Logger.getLogger(SmsService.class);
	
	@Autowired
	private SmsRepository smsRepository;
	
	@Autowired
	private UserService userService;
	
	public SmsService(){
		
	}
	/**
	 * 发送短信验证码
	 * @param phoneNumber
	 */
	public void sendSms(String phoneNumber,String type) throws XueWenServiceException{
		long time=System.currentTimeMillis();
		phoneNumber=StringUtil.formatePhoneNum(phoneNumber);
		if(StringUtil.isBlank(phoneNumber)){
			throw new XueWenServiceException(Config.STATUS_201,Config.MSG_NOTMOBILE_201 ,null);
		}
		if(StringUtil.isBlank(type)){
			throw new XueWenServiceException(Config.STATUS_500, Config.MSG_500,null);
		}
		if("1".equals(type)){
			//判断一天之内注册短信条数是否超标
			logger.info("一天之内注册短信条数是否超标========");
			if(userService.isRegistUserByPhoneNumber(phoneNumber)){
				throw new XueWenServiceException(Config.STATUS_201,Config.MSG_400 ,null);
			}
			if(countOneDaySend(phoneNumber,type)>=10){
				throw new XueWenServiceException(Config.STATUS_201,Config.MSG_REGISTSMS_201 ,null);
			}
		}else if("2".equals(type)){
			//判断一天之内更改密码短信条数是否超标
			logger.info("一天之内更改密码短信条数是否超标========");
			if(!userService.isRegistUserByPhoneNumber(phoneNumber)){
				throw new XueWenServiceException(Config.STATUS_201,Config.MSG_ISNOTUSER_201 ,null);
			}
			if(countOneDaySend(phoneNumber,type)>=5){
				throw new XueWenServiceException(Config.STATUS_201,Config.MSG_CHANGEPWD_201 ,null);
			}
		}
		if("1".equals(type) || "2".equals(type) || "3".equals(type) || "4".equals(type)){
			//注册短信验证码或修改密码短信验证码
			//验证此用户发送短信验证码是否超过一分钟
			logger.info("验证此用户发送短信验证码是否超过一分钟========");
			List<Sms> msgL1=smsRepository.findOneByPhoneNumAndCtimeGreaterThan(phoneNumber,time-(1000*60));
			if(msgL1 == null  || msgL1.size() == 0){
				//查询是否有五分钟内发送的没有验证的同类型短信
				logger.info("查询是否有五分钟内发送的没有验证的同类型短信========");
				Sms msgL5=smsRepository.findOneByPhoneNumAndTypeAndCheckedAndCheckTimeAndCtimeGreaterThan(phoneNumber,type,0,0,time-(1000*60*5));
				//生成短信验证码
				int code=nextInt(1000,9999);
				int num=getSmsNum();
				if(num<=0){
					throw new XueWenServiceException(Config.STATUS_201,Config.MSG_NOSMSNUM_201 ,null);
				}
				String sendCode="";
				
				if(msgL5 == null ){
					//如没有则生成新的短信验证码
					logger.info("如没有则生成新的短信验证码========");
					Sms msg=new Sms();
					msg.setPhoneNum(phoneNumber);
					msg.setCode(String.valueOf(code));
					msg.setCheckTime(0);
					msg.setCtime(time);
					msg.setUtime(time);
					msg.setType(type);
					msg.setChecked(0);
					msg.setToPhoneNum(phoneNumber);
					msg.setLastNum(num);
					//发送的验证码为新生成的验证码
					sendCode=String.valueOf(code);
					toSend(phoneNumber,sendCode,type);
					smsRepository.save(msg);
				}else{
					//如有则使用上次未验证的短信验证码
					logger.info("如有则使用上次未验证的短信验证码========");
					Sms msg=new Sms();
					msg.setPhoneNum(phoneNumber);
					msg.setCode(msgL5.getCode());
					msg.setCheckTime(0);
					msg.setCtime(time);
					msg.setUtime(time);
					msg.setType(type);
					msg.setChecked(0);
					msg.setToPhoneNum(phoneNumber);
					msg.setLastNum(num);
					//发送的验证码为上一条相同的验证码
					sendCode=msgL5.getCode();
					toSend(phoneNumber,sendCode,type);
					//保存新的发送短信
					smsRepository.save(msg);
					//更改上一条的短信为无效
					msgL5.setChecked(3);
					smsRepository.save(msgL5);
				}
			}else{
				throw new XueWenServiceException(Config.STATUS_201,Config.MSG_FREQUENT_201,null);
			}
		}else{
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_201,null);
		} 
	}
	
	/**
	 * 邀请好友发送短信验证码
	 * @param phoneNumber
	 * @param type
	 * @param user
	 * @throws XueWenServiceException
	 */
	public void sendSms(String phoneNumber,String type,String code,User user) throws XueWenServiceException{
		long time=System.currentTimeMillis();
		phoneNumber=StringUtil.formatePhoneNum(phoneNumber);
		if(StringUtil.isBlank(phoneNumber)){
			throw new XueWenServiceException(Config.STATUS_201,Config.MSG_NOTMOBILE_201 ,null);
		}
		if("3".equals(type)){
			int num=getSmsNum();
			if(num<=0){
				throw new XueWenServiceException(Config.STATUS_201,Config.MSG_NOSMSNUM_201 ,null);
			}
			Sms msg=new Sms();
			msg.setPhoneNum(user.getUserName());
			msg.setNickName(user.getNickName());
			msg.setCode(code);
			msg.setCheckTime(0);
			msg.setCtime(time);
			msg.setUtime(time);
			msg.setType(type);
			msg.setChecked(0);
			msg.setLastNum(num);
			msg.setToPhoneNum(phoneNumber);
			smsRepository.save(msg);
			toSend(phoneNumber,String.valueOf(code),type);
		}else{
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_201,null);
		} 
	}
	
	
	/**
	 * 统计一种类型的一天内的短信条数
	 * @param phoneNum
	 * @param type
	 * @return
	 * @throws XueWenServiceException
	 */
	public int countOneDaySend(String phoneNum ,String type)throws XueWenServiceException{
		long time=System.currentTimeMillis()-1000*60*60*24;
		return smsRepository.countByPhoneNumAndTypeAndCtimeGreaterThan(phoneNum, type, time);
	}
	
	
	/**
	 * 获取固定区间的随机数
	 * @param min
	 * @param max
	 * @return
	 */
	private int nextInt(final int min, final int max){

	    int tmp = Math.abs(new Random().nextInt());
	    return tmp % (max - min + 1) + min;
	}
	
	public void toSend(String phoneNum,String code,String type)throws XueWenServiceException{
		try {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String dateStr=df.format(new Date());
			logger.info(dateStr);
			long time=(df.parse(dateStr).getTime())/1000;
			String dt =String.valueOf(time);
			
			String user="yunxuetang";
			String pwd=MD5Util.MD5(user+"suzhouyxt"+dt).toLowerCase();
			String params = "";
			if("1".equals(type) || "2".equals(type) || "4".equals(type)){
				code="【云学堂】您的验证码为:"+code+"。如不是您本人操作，可联系纸条客服010：59492887。http://dwz.cn/11gsGT";
				params="username="+user+"&pwd="+pwd+"&dt="+dt+"&msg="+code+"&mobiles="+phoneNum+"&code=4110";
			}else{
				code = "【云学堂】"+code;
				params="username="+user+"&pwd="+pwd+"&dt="+dt+"&mobiles="+phoneNum+"&code=999"+"&msg="+URLDecoder.decode(code, "UTF-8");
			}

			String url="http://sms.ensms.com:8080/sendsms/?"+params;
			logger.info("pwd===="+pwd+"========url:"+url);
//			url=URLEncoder.encode(url);
//			logger.info("pwd===="+pwd+"========url:"+url);
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet get=new HttpGet(url);
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }
            };
			String responseBody= httpclient.execute(get,responseHandler);
//			HttpEntity entity=response.getEntity();
//			String str ="";
//			if (entity != null) {    
//				InputStream instreams = entity.getContent();    
//				str=convertStreamToString(instreams);  
//				System.out.println("Do something");   
//				System.out.println(str);  
//				// Do not need the rest    
//			}
			if(!responseBody.replaceAll("\r|\n", "").equals("0")){
				throw new XueWenServiceException(Config.STATUS_201, Config.MSG_SENDMSGERROR_201,null);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_SENDMSGERROR_201,null);
		}
	}
	
	public static String convertStreamToString(InputStream is) {      
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));      
        StringBuilder sb = new StringBuilder();      
       
        String line = null;      
        try {      
            while ((line = reader.readLine()) != null) {  
                sb.append(line + "\n");      
            }      
        } catch (IOException e) {      
            e.printStackTrace();      
        } finally {      
            try {      
                is.close();      
            } catch (IOException e) {      
               e.printStackTrace();      
            }      
        }      
        return sb.toString();      
    }
	
	/**
     * 验证短息码
     * @param phoneNumber
     * @param smsCode
     */
	public boolean checkSms(String phoneNumber,String type,String smsCode) throws XueWenServiceException{
		// TODO Auto-generated method stub
		//短信验证码有效期为5分钟
		long time=System.currentTimeMillis();
		Direction d = Direction.DESC;
		Sort sort=new Sort(d,"utime");
		Sms sms=smsRepository.findOneByPhoneNumAndTypeAndCheckedAndUtimeGreaterThan(phoneNumber,type,0,time-(1000*60*5),sort);
		if(sms==null){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_CODEFAILURE_201,null);
		}
		if((sms.getCheckTime()+1)>5){
			logger.info("短信验证码有效期为5分钟=========");
			sms.setChecked(2);
			sms.setCheckTime(sms.getCheckTime()+1);
			sms.setUtime(time);
			smsRepository.save(sms);
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_ERRORTOMUCH_201,null);
		}else{
			if(smsCode.equals(sms.getCode())){
				logger.info("短信验证码相同=========");
				sms.setChecked(1);
				sms.setCheckTime(sms.getCheckTime()+1);
				sms.setUtime(time);
				smsRepository.delete(sms);
				return true;
			}else{
				logger.info("短信验证码不相同=========");
				sms.setCheckTime(sms.getCheckTime()+1);
				sms.setUtime(time);
				smsRepository.save(sms);
				throw new XueWenServiceException(Config.STATUS_201, Config.MSG_ERRORCODE_201,null);
			}
		}
	}
	
	public static int getSmsNum()throws XueWenServiceException{
		try {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String dateStr=df.format(new Date());
			logger.info(dateStr);
			long time=(df.parse(dateStr).getTime())/1000;
			String dt =String.valueOf(time);
			
			String user="yunxuetang";
			String pwd=MD5Util.MD5(user+"suzhouyxt"+dt).toLowerCase();
				
			String params="username="+user+"&pwd="+pwd+"&dt="+dt;

			String url="http://sms.ensms.com:8080/getsmsnum/?"+params;
			logger.info("pwd===="+pwd+"========url:"+url);
//			url=URLEncoder.encode(url);
//			logger.info("pwd===="+pwd+"========url:"+url);
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet get=new HttpGet(url);
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }
            };
			String responseBody= httpclient.execute(get,responseHandler);
//			HttpEntity entity=response.getEntity();
//			String str ="";
//			if (entity != null) {    
//				InputStream instreams = entity.getContent();    
//				str=convertStreamToString(instreams);  
//				System.out.println("Do something");   
//				System.out.println(str);  
//				// Do not need the rest 
//				
//			}
//			if(!str.replaceAll("\r|\n", "").equals("0")){
//				throw new XueWenServiceException(Config.STATUS_201, Config.MSG_SENDMSGERROR_201,null);
//			}
			return Integer.valueOf(responseBody.replaceAll("\r|\n", ""));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_SENDMSGERROR_201,null);
		}
	}
	
//	@Scheduled(fixedRate = 300000)
//	private void deleteOldMsgCode(){
//		long time=System.currentTimeMillis();
//		logger.info("定时器启动时间："+time);
//		List<MsgCode> msgs=msgCodeRepository.findByUtimeLessThan(time-(300000));
//		logger.info("小于此时间点："+String.valueOf(time-300000)+"的记录删除，总共有："+msgs.size() );
//		if(msgs !=null && msgs.size()>0){
//			msgCodeRepository.delete(msgs);
//		}
//	}
	
}

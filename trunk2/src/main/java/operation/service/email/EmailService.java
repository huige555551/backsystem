package operation.service.email;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import operation.exception.XueWenServiceException;
import operation.pojo.email.MailTempter;
import operation.pojo.email.YxtRegMail;
import operation.repo.email.MailTempRepo;
import operation.service.rabbitmq.RabbitmqService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tools.Config;
import tools.StringUtil;

@Service
public class EmailService {
	@Autowired
	private MailTempRepo mailTempRepo;
	@Autowired
	private RabbitmqService rabbitmqService;
	@Autowired
	private MailCodeService mailCodeService;
	
	private static final Logger logger=Logger.getLogger(EmailService.class);
	
	public MailTempter getTemppterByName(String name){
		return mailTempRepo.findOneByName(name);
	}
	
	/**
	 * 
	 * @Title: save
	 * @auther Tangli
	 * @Description: 邮件模板新增或更新
	 * @param mailTempter void
	 * @throws
	 */
	public void save(MailTempter mailTempter){
		mailTempRepo.save(mailTempter);
	}
	
	/**
	 * 
	 * @Title: findOne
	 * @auther Tangli
	 * @Description:根据取
	 * @param id
	 * @return MailTempter
	 * @throws
	 */
	public MailTempter findOne(String id){
		return mailTempRepo.findOne(id);
	}
	
	/**
	 * 
	 * @Title: add
	 * @auther Tangli
	 * @Description: 新增一个
	 * @param content
	 * @param name
	 * @throws XueWenServiceException void
	 * @throws
	 */
	public void add(String content,String name) throws XueWenServiceException{
		if(StringUtil.isBlank(content)||StringUtil.isBlank(name)){
			throw new XueWenServiceException(Config.STATUS_201, "模板信息缺失", null);
		}
		MailTempter mailTempter=new MailTempter();
		mailTempter.setName(name);
		mailTempter.setContext(content);
		save(mailTempter);	
	}
	
    /**
     * 
     * @Title: sendMails
     * @auther tangli
     * @Description: 发送邀请邮件
     * @param yxtRegMail
     * @param addresses
     * @throws XueWenServiceException
     * @throws IOException void
     * @throws
     */
	public void sendMails(YxtRegMail yxtRegMail, String addresses) throws XueWenServiceException, IOException {
		String[] address=addresses.split(",");
		String url=yxtRegMail.getToUserRegUrl();
		for (String ads : address) {
			yxtRegMail.setToEmailAddress(ads);
			yxtRegMail.setTempName(Config.MAIL_TEMP_VI);
			yxtRegMail.setToUserRegUrl(url+"&email="+ads);
			//sendYxtRegMail(yxtRegMail);
			try {
				rabbitmqService.sendMailMessage(yxtRegMail);
			} catch (Exception e) {
				logger.error("==============发送邀请邮件激活消息发送未知错误==============");
			}
			
		}
		
	}
	
	/**
	 * 
	 * @Title: sendVmail
	 * @auther Tangli
	 * @Description: 发送注册激活邮件
	 * @param emailAdress 接收邮件地址
	 * @param Url  注册激活Url
	 * @param userNick 用户昵称
	 * @throws 
	 * @throws
	 */
	public void sendRegMail(String emailAdress,String url,String userNick){
		YxtRegMail mail=new YxtRegMail();
		mail.setToEmailAddress(emailAdress);
		mail.setToUserRegUrl(url);
		mail.setUserNick(userNick);
		mail.setTempName(Config.MAIL_TEMP_REG);
		try {
			rabbitmqService.sendMailMessage(mail);
		} catch (Exception e) {
			logger.error("==============发送注册邮件激活消息发生未知错误==============");
		}
	}
	
	/**
	 * 
	 * @auther tangli
	 * @Description: 发送验证码类型的邮件
	 * @param email 邮件地址
	 * @Date:2015年4月20日
	 * @throws
	 */
	public void sendMailCode(String email,int type){
		String code=nextInt(100000, 999999)+"";	
		YxtRegMail yxtRegMail=new YxtRegMail();
		yxtRegMail.setToEmailAddress(email);
		yxtRegMail.setCode(code);
		if(type==1){
			yxtRegMail.setTempName(Config.MAIL_TEMP_BMAIL);	
		}else {
			yxtRegMail.setTempName(Config.MAIL_TEMP_CODEMAIL);	
		}
		logger.info("============邮箱验证码=="+code+"==============");
		mailCodeService.insert(code, email);
		try {
			rabbitmqService.sendMailMessage(yxtRegMail);
		} catch (Exception e) {
			logger.error("==============发送注册邮件激活消息发生未知错误==============");
		}
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
	
	
	/**
	 * 
	 * @Title: sendRestPwdMail
	 * @auther Tangli
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param emailAdress  接收邮件地址
	 * @param url    修改密码的前端地址
	 * @param userNick 用户
	 * @throws
	 */
	public void sendRestPwdMail(String emailAdress,String url,String userNick){
		//TODO 等宜兴 出需求
	}
	
	/**
	 * 
	 * @Title: modify
	 * @auther Tangli
	 * @Description: 修改
	 * @param id
	 * @param content
	 * @throws XueWenServiceException void
	 * @throws
	 */
	public void modify(String id, String content) throws XueWenServiceException {
		if (StringUtil.isBlank(content) || StringUtil.isBlank(id)) {
			throw new XueWenServiceException(Config.STATUS_201, "参数错误", null);
		}
		MailTempter tempter = findOne(id);
		if (tempter == null) {
			throw new XueWenServiceException(Config.STATUS_201, "没有找到相对应的模板",
					null);
		} else {
			tempter.setContext(content);
			save(tempter);
		}
	}
	
	public void test(){
		sendRegMail("597384808@qq.com", "http://www.baidu.com", "小牧");
	}

	public List<MailTempter> getAll() {
		return mailTempRepo.findAll();
	}
}

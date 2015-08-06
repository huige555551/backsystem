/**   
* @Title: AttachFileService.java
* @Package operation.service.cloudfile
* @Description: 
* @author yaoj
* @date 2014年12月17日 下午1:37:06
* @version V1.0
*/ 
package operation.service.cloudfile;

import java.util.List;
import java.util.Random;

import operation.exception.XueWenServiceException;
import operation.pojo.cloudfile.AttachFile;
import operation.pojo.common.ColudConfig;
import operation.repo.cloudfile.AttachFileTemplate;
import operation.service.common.ColudConfigService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tools.Config;

/** 
 * @ClassName: AttachFileService
 * @Description: 云存储附件service
 * @author yaoj
 * @date 2014年12月17日 下午1:37:06
 * 
 */
@Service
public class AttachFileService {
	
	@Autowired
	private AttachFileTemplate attachFileTemplate;
	
	@Autowired
	private ColudConfigService coludConfigService;
	
	/**
	 * 
	 * @Title: insertAttachFile
	 * @Description: 添加新的附件
	 * @return AttachFile
	 * @throws XueWenServiceException
	 */
	public AttachFile insertAttachFile(AttachFile attachFile) throws XueWenServiceException {
		if (attachFile.getCkey() == null) {
			throw new XueWenServiceException(Config.STATUS_201,Config.MSG_201,null);
		}
		//拼装全路径
		ColudConfig coludConfig = coludConfigService.getColudConfig(attachFile.getCkey());
		if (coludConfig == null) {
			throw new XueWenServiceException(Config.STATUS_201,Config.MSG_ERROCKEY_201,null);
		}
		//获取List中的随机一个数
		List<String> baseurls = coludConfig.getBaseUrls();
		if (baseurls == null || baseurls.size() == 0) {
			throw new XueWenServiceException(Config.STATUS_201,Config.MSG_ERROCKEY_201,null);
		}
		String baseurl = baseurls.get(new Random().nextInt(baseurls.size()));
		
		attachFile.setFurl(baseurl + attachFile.getFkey());
		long time = System.currentTimeMillis();
		attachFile.setCtime(time);
		attachFile.setUtime(time);
		AttachFile file = attachFileTemplate.insertAttachFile(attachFile);
		return file;
	}

	/**
	 * 
	 * @Title: viewAttachFileById
	 * @Description: 根据id查看附件
	 * @param id
	 * @return AttachFile
	 * @throws XueWenServiceException
	 */
	public AttachFile viewAttachFileById(String id) throws XueWenServiceException {
		if (StringUtils.isBlank(id)) {
			throw new XueWenServiceException(Config.STATUS_201,Config.MSG_201,null);
		}
		AttachFile file = attachFileTemplate.viewAttachFileById(id);
		return file;
	}

	/**
	 * @throws XueWenServiceException 
	 * 
	 * @Title: addAttachCountById
	 * @Description: 添加引用次数+1
	 * @param id
	 * @return AttachFile
	 * @throws
	 */
	public AttachFile addAttachCountById(String id) throws XueWenServiceException {
		if (StringUtils.isBlank(id)) {
			throw new XueWenServiceException(Config.STATUS_201,Config.MSG_201,null);
		}
		long time = System.currentTimeMillis();
		AttachFile file = attachFileTemplate.addAttachCountById(id,time);
		return file;
	}

	/**
	 * 
	 * @Title: subAttachCountById
	 * @Description: 减少引用次数-1
	 * @param id
	 * @return
	 * @throws XueWenServiceException AttachFile
	 * @throws
	 */
	public AttachFile subAttachCountById(String id) throws XueWenServiceException {
		if (StringUtils.isBlank(id)) {
			throw new XueWenServiceException(Config.STATUS_201,Config.MSG_201,null);
		}
		long time = System.currentTimeMillis();
		AttachFile file = attachFileTemplate.subAttachCountById(id,time);
		return file;
	}

}

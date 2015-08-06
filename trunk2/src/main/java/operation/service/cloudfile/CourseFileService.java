/**   
* @Title: CourseFileService.java
* @Package operation.service.cloudfile
* @Description: 
* @author yaoj
* @date 2014年12月17日 下午1:37:57
* @version V1.0
*/ 
package operation.service.cloudfile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import operation.exception.XueWenServiceException;
import operation.pojo.cloudfile.CourseFile;
import operation.pojo.cloudfile.Citem;
import operation.pojo.common.ColudConfig;
import operation.repo.cloudfile.CourseFileTemplate;
import operation.service.common.ColudConfigService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tools.Config;

/** 
 * @ClassName: CourseFileService
 * @Description: 云存储课件service
 * @author yaoj
 * @date 2014年12月17日 下午1:37:57
 * 
 */
@Service
public class CourseFileService {
	
	@Autowired
	private CourseFileTemplate courseFileTemplate;
	
	@Autowired
	private ColudConfigService coludConfigService;
	
	/**
	 * 
	 * @param fitems 
	 * @Title: insertCourseFile
	 * @Description: 添加新的课件
	 * @return CourseFile
	 * @throws XueWenServiceException
	 */
	public CourseFile insertCourseFile(CourseFile courseFile, String fitemJson) throws XueWenServiceException {
		if (courseFile.getCkey() == null) {
			throw new XueWenServiceException(Config.STATUS_201,Config.MSG_201,null);
		}
		//拼装全路径
		ColudConfig coludConfig = coludConfigService.getColudConfig(courseFile.getCkey());
		if (coludConfig == null) {
			throw new XueWenServiceException(Config.STATUS_201,"云存储配置信息有误",null);
		}
		//获取List中的随机一个数
		List<String> baseurls = coludConfig.getBaseUrls();
		String baseurl = baseurls.get(new Random().nextInt(baseurls.size()));
		
		if (baseurl == null) {
			throw new XueWenServiceException(Config.STATUS_201,"云存储配置信息有误",null);
		}
		courseFile.setHttpUrl(baseurl + courseFile.getFkey());
		
		if (fitemJson != null) {
			JSONArray array = JSONArray.fromObject(fitemJson);
			if(array!=null){
				if(array.size() > 0 ){
					List<Citem> fitems = new ArrayList<Citem>();
					JSONObject jsonObject;
					for(int i = 0; i < array.size(); i++) {  
						jsonObject = JSONObject.fromObject(array.get(i));
						Citem fitem = (Citem) JSONObject.toBean(jsonObject,Citem.class);
						fitem.setFurl(baseurl + fitem.getKey());
						fitems.add(fitem);
					}
					courseFile.setFitems(fitems);
				}
			}
		}
		//设置创建时间
		long time = System.currentTimeMillis();
		courseFile.setCtime(time);
		courseFile.setUtime(time);
		CourseFile file = courseFileTemplate.insertCourseFile(courseFile); 
		return file;
	}

	/**
	 * 
	 * @Title: viewCourseFileById
	 * @Description: 根据id查看课件
	 * @param id
	 * @return CourseFile
	 * @throws XueWenServiceException
	 */
	public CourseFile viewCourseFileById(String id) throws XueWenServiceException {
		if (StringUtils.isBlank(id)) {
			throw new XueWenServiceException(Config.STATUS_201,Config.MSG_201,null);
		}
		CourseFile file = courseFileTemplate.viewCourseFileById(id);
		return file;
	}

	/**
	 * @throws XueWenServiceException 
	 * 
	 * @Title: addCourseCountById
	 * @Description: 添加引用次数+1
	 * @param id
	 * @return CourseFile
	 * @throws
	 */
	public CourseFile addCourseCountById(String id) throws XueWenServiceException {
		if (StringUtils.isBlank(id)) {
			throw new XueWenServiceException(Config.STATUS_201,Config.MSG_201,null);
		}
		long time = System.currentTimeMillis();
		CourseFile file = courseFileTemplate.addCourseCountById(id,time);
		return file;
	}

	/**
	 * 
	 * @Title: subCourseCountById
	 * @Description: 减少引用次数-1
	 * @param id
	 * @return
	 * @throws XueWenServiceException CourseFile
	 * @throws
	 */
	public CourseFile subCourseCountById(String id) throws XueWenServiceException {
		if (StringUtils.isBlank(id)) {
			throw new XueWenServiceException(Config.STATUS_201,Config.MSG_201,null);
		}
		long time = System.currentTimeMillis();
		CourseFile file = courseFileTemplate.subCourseCountById(id,time);
		return file;
	}

}

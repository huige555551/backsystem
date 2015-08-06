package operation.controller.file;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import operation.BaseController;
import operation.exception.XueWenServiceException;
import operation.pojo.file.FileOperationInfo;
import operation.pojo.file.FileStoreInfo;
import operation.pojo.space.ResponseSpace;
import operation.pojo.user.User;
import operation.service.file.MyFileService;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.rs.PutPolicy;

import tools.Config;
import tools.ResponseContainer;
import tools.StringToList;
import tools.StringUtil;

/** 
* @ClassName: FileController
* @Description: 文件上传相关功能
* @author yangquanliang
* @date 2014年12月8日 下午4:09:07
* 
*/ 
@RestController
@RequestMapping("/file")

public class FileController extends BaseController {
	private static final Logger logger=Logger.getLogger(FileController.class);
	
	@Autowired
	public MyFileService myfileService;

	public FileController() {
		// TODO Auto-generated constructor stub
	}
	
//	/**
//	 * 空间文件上传
//	 * @param request  请求参数，包括，groupId 群ID token 用户票据
//	 * @param file
//	 * @return
//	 */
//	@RequestMapping("/fileUpload")
//	public @ResponseBody ResponseContainer fileUpload(HttpServletRequest request,@RequestParam(value = "file", required = false) MultipartFile file) {
//		try {
//			logger.info("=============群空间文件上传==============");
//			String token = request.getParameter("token");
//			//增加中文字符转码问题
//			User currentUser = this.getCurrentUser(token);
//			FileStoreInfo fileStoreInfo=myfileService.upload(currentUser, file);
//			return addResponse(Config.STATUS_200, Config.MSG_CREATE_200,
//					fileStoreInfo, Config.RESP_MODE_10, "");
//		} catch (XueWenServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			logger.error("==========业务错误，空间文件上传失败============"+e);
//			return addResponse(e.getCode(), e.getMessage(), false,
//					Config.RESP_MODE_10, "");
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("==========未知错误，空间文件上传失败============"+e);
//			return addResponse(Config.STATUS_505, Config.MSG_505, false,
//					Config.RESP_MODE_10, "");
//		}
//	}
	
	
	@RequestMapping("/fileDownload")
	public void fileDownload(HttpServletRequest request,HttpServletResponse response) {
		 try {
			  	String fileId=request.getParameter("fileId");
			  	String token = request.getParameter("token");
			  	String isOnline=request.getParameter("isOnline");
			    if(StringUtil.isBlank(isOnline)){
			    	isOnline="online";
			    }
				User currentUser = this.getCurrentUser(token);
				//根据fileId查找出文件操作信息
			    //根据fileMD5查找文件信息
				FileStoreInfo fsi=myfileService.findOneByFileId(fileId);
				logger.info("文件的名称："+fsi.getFileServerName());
				if(fsi !=null){
					// 以流的形式下载文件。
					BufferedInputStream br = new BufferedInputStream(new FileInputStream(fsi.getFileLocal()));
			        byte[] buf = new byte[1024];
			        int len = 0;
			        response.reset(); // 非常重要
			        if (isOnline.equals("online")) { // 在线打开方式
			        	//记录在线阅读
			            URL u = new URL("file:///" + fsi.getFileUrl());
			            response.setContentType(fsi.getFileContext());
			            response.setHeader("Content-Disposition", "inline; filename=" + fsi.getFileServerName());
			            // 文件名应该编码成UTF-8
			        } else { // 纯下载方式
			        	//记录下载
			            response.setContentType(fsi.getFileContext());
			            response.setHeader("Content-Disposition", "attachment; filename=" + fsi.getFileServerName());
			        }
			        OutputStream out = response.getOutputStream();
			        while ((len = br.read(buf)) > 0)
			            out.write(buf, 0, len);
			        br.close();
			        out.close();
				}else{
					response.sendError(201, "File not found!");
				}
	        }catch (Exception e) {
	        	e.printStackTrace();
	        	logger.info("文件下载系统错误："+e);
	        	try {
					response.sendError(201, "File not found!");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					logger.info("文件下载系统错误："+e1);
				}
	       }
	}
	
	/** 
	* @author yangquanliang
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param request
	* @param @param response
	* @param @return
	* @return ResponseContainer
	* @throws 
	*/ 
	@Inject Environment env;
	@RequestMapping("/getuptoken")
	public ResponseContainer getUpToken(HttpServletRequest request,HttpServletResponse response) {
	        String ACCESS_KEY = env.getProperty("qnAccessKey");
	        String SECRET_KEY = env.getProperty("qnSecretKey");
	        Mac mac = new Mac(ACCESS_KEY, SECRET_KEY);
	        // 请确保该bucket已经存在
	        String bucketName = env.getProperty("qnBucketName"); 
	        PutPolicy putPolicy = new PutPolicy(bucketName);
	        try {
	        	String uptoken = putPolicy.token(mac);
					return addResponse(Config.STATUS_200, Config.MSG_200, uptoken,
							Config.RESP_MODE_10, "");
			} catch (AuthException e) {
				return addResponse(Config.STATUS_505, Config.MSG_505, false,
						Config.RESP_MODE_10, "");
			} catch (JSONException e) {
				return addResponse(Config.STATUS_505, Config.MSG_505, false,
						Config.RESP_MODE_10, "");
			}	
	}
	


}

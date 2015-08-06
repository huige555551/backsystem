package operation.controller.space;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import operation.BaseController;
import operation.exception.XueWenServiceException;
import operation.pojo.file.FileOperationInfo;
import operation.pojo.file.FileStoreInfo;
import operation.pojo.space.ResponseSpace;
import operation.pojo.user.User;
import operation.service.file.MyFileService;
import operation.service.space.SpaceService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tools.Config;
import tools.ResponseContainer;
import tools.StringToList;
import tools.StringUtil;

@RestController
@RequestMapping("/space")
/**
 *空间controller，用户接受客户端请求的数据，
 *与service层交互，并将数据反馈给客户端
 * @author hjn
 *
 */
public class SpaceController extends BaseController  {

	private static final Logger logger=Logger.getLogger(SpaceController.class);
	@Autowired
	public SpaceService spaceService;
	@Autowired
	public MyFileService myfileService;
	
//	/**
//	 * 空间文件上传
//	 * @param request  请求参数，包括，groupId 群ID token 用户票据
//	 * @param file
//	 * @return
//	 */
//	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
//	public @ResponseBody ResponseContainer fileUpload(HttpServletRequest request,@RequestParam(value = "file", required = false) MultipartFile file) {
//		try {
//			logger.info("=============群空间文件上传==============");
//			String spaceAdminId = request.getParameter("spaceAdminId");
//			String token = request.getParameter("token");
//			String fileName=request.getParameter("fileName");
//			String intro=request.getParameter("intro");
//			String tag=request.getParameter("tag");
//			//增加中文字符转码问题
//			if(!StringUtil.isBlank(fileName)){
//				fileName=URLDecoder.decode(fileName, "UTF-8");
//			}
//			if(!StringUtil.isEmpty(intro)){
//				intro=URLDecoder.decode(intro,"UTF-8");
//			}
//			List<Object> tags=new ArrayList<Object>();
//			if(!StringUtil.isEmpty(tag)){
//				tags=StringToList.tranfer(URLDecoder.decode(request.getParameter("tag"),"UTF-8"));
//			}
//			User currentUser = this.getCurrentUser(token);
//			ResponseSpace space=spaceService.upload(spaceAdminId, currentUser, file,fileName,intro,tags);
//			return addResponse(Config.STATUS_200, Config.MSG_CREATE_200,
//					space, Config.RESP_MODE_10, "");
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
	
	
	@RequestMapping("/spaceFileDownload")
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
				FileOperationInfo foi=myfileService.getFoi(fileId);
			    //根据fileMD5查找文件信息
				FileStoreInfo fsi=myfileService.findOneByFileMD5(foi.getFileMD5().toString());
				if(fsi !=null){
					// 以流的形式下载文件。
					BufferedInputStream br = new BufferedInputStream(new FileInputStream(fsi.getFileLocal()));
			        byte[] buf = new byte[1024];
			        int len = 0;
			        response.reset(); // 非常重要
			        if (isOnline.equals("online")) { // 在线打开方式
			        	//记录在线阅读
			        	spaceService.read(fileId, currentUser.getId());
			            URL u = new URL("file:///" + fsi.getFileUrl());
			            response.setContentType(fsi.getFileContext());
			            response.setHeader("Content-Disposition", "inline; filename=" + fsi.getFileServerName());
			            // 文件名应该编码成UTF-8
			        } else { // 纯下载方式
			        	//记录下载
			        	spaceService.download(fileId, currentUser.getId());
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
	
	@RequestMapping("/spaceCheck")
	public @ResponseBody ResponseContainer spaceCheck(HttpServletRequest request) {
		String token = request.getParameter("token");
		String spaceAdminId=request.getParameter("spaceAdminId");
		try {
			User currentUser = this.getCurrentUser(token);
			ResponseSpace space=spaceService.spaceCheck(spaceAdminId);
			return addResponse(Config.STATUS_200, Config.MSG_200, space,
					Config.RESP_MODE_10, "");
			}
			catch (XueWenServiceException e) {
				e.printStackTrace();
				return addResponse(e.getCode(), e.getMessage(), false,
						Config.RESP_MODE_10, "");
			} catch (Exception e) {
				e.printStackTrace();
				return addResponse(Config.STATUS_505, Config.MSG_505, false,
						Config.RESP_MODE_10, "");
			}	
	}
	
	
	/**
	 * 空间文件赞
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/{id}/like")
	public @ResponseBody ResponseContainer like(@PathVariable("id") String id,HttpServletRequest request) {
		String token = request.getParameter("token");
		try {
			User currentUser = this.getCurrentUser(token);
			FileOperationInfo foi = spaceService.like(id, currentUser.getId());
			return addResponse(Config.STATUS_200, Config.MSG_200, foi,
					Config.RESP_MODE_10, "");
			}
			catch (XueWenServiceException e) {
				e.printStackTrace();
				return addResponse(e.getCode(), e.getMessage(), false,
						Config.RESP_MODE_10, "");
			} catch (Exception e) {
				e.printStackTrace();
				return addResponse(Config.STATUS_505, Config.MSG_505, false,
						Config.RESP_MODE_10, "");
			}	
	}

	/**
	 * 话题不赞
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/{id}/unlike")
	public @ResponseBody ResponseContainer unlike(
			@PathVariable("id") String id, HttpServletRequest request) {
		String token = request.getParameter("token");
		try {
			User currentUser = this.getCurrentUser(token);
			FileOperationInfo foi = spaceService.unlike(id, currentUser.getId());
			return addResponse(Config.STATUS_200, Config.MSG_200, foi,
					Config.RESP_MODE_10, "");
			}
			catch (XueWenServiceException e) {
				e.printStackTrace();
				return addResponse(e.getCode(), e.getMessage(), false,
						Config.RESP_MODE_10, "");
			} catch (Exception e) {
				e.printStackTrace();
				return addResponse(Config.STATUS_505, Config.MSG_505, false,
						Config.RESP_MODE_10, "");
			}	
	}

	/**
	 * 话题分享
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/{id}/share")
	public @ResponseBody ResponseContainer share(@PathVariable("id") String id,
			HttpServletRequest request) {
		String token = request.getParameter("token");
		String shareAdrr = request.getParameter("shareAdrr");
		try {
			User currentUser = this.getCurrentUser(token);
			FileOperationInfo foi = spaceService.share(id, currentUser.getId(),shareAdrr);
			return addResponse(Config.STATUS_200, Config.MSG_200, foi,
					Config.RESP_MODE_10, "");
			}
			catch (XueWenServiceException e) {
				e.printStackTrace();
				return addResponse(e.getCode(), e.getMessage(), false,
						Config.RESP_MODE_10, "");
			} catch (Exception e) {
				e.printStackTrace();
				return addResponse(Config.STATUS_505, Config.MSG_505, false,
						Config.RESP_MODE_10, "");
			}	
	}
	
	
	/**
	 * 话题关注
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/{id}/attention")
	public @ResponseBody ResponseContainer attention(@PathVariable("id") String id,HttpServletRequest request) {
		String token = request.getParameter("token");
		try {
			User currentUser = this.getCurrentUser(token);
			FileOperationInfo foi = spaceService.attention(id, currentUser.getId());
			return addResponse(Config.STATUS_200, Config.MSG_200, foi,
					Config.RESP_MODE_10, "");
			}
			catch (XueWenServiceException e) {
				e.printStackTrace();
				return addResponse(e.getCode(), e.getMessage(), false,
						Config.RESP_MODE_10, "");
			} catch (Exception e) {
				e.printStackTrace();
				return addResponse(Config.STATUS_505, Config.MSG_505, false,
						Config.RESP_MODE_10, "");
			}	
	}
	
	/**
	 * 查询赞，不赞，分享，关注成员列表
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/{id}/checkmember")
	public @ResponseBody ResponseContainer checkMember(@PathVariable("id") String id,HttpServletRequest request) {
		String token = request.getParameter("token");
		//action 的值为like,unlike,share,attention
		String action=request.getParameter("action");
		try {
			User currentUser = this.getCurrentUser(token);
			List<Object> members = spaceService.checkMember(id, currentUser.getId(),action);
			return addResponse(Config.STATUS_200, Config.MSG_200, members,Config.RESP_MODE_10, "");
		}catch (XueWenServiceException e) {
			e.printStackTrace();
			return addResponse(e.getCode(), e.getMessage(), false,
			Config.RESP_MODE_10, "");
		} catch (Exception e) {
			e.printStackTrace();
			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
		}	
	}
	
	
	/**
	 * 用户学习轨迹保存
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/{id}/saveFileOperation")
	public @ResponseBody ResponseContainer saveFileOperation(@PathVariable("id") String id,HttpServletRequest request) {
		String token = request.getParameter("token");
		String operation=request.getParameter("operation");
		String time=request.getParameter("time");
		try {
			User currentUser = this.getCurrentUser(token);
			boolean saveStudy=spaceService.saveFileOperation(currentUser.getId(), id, operation,Long.valueOf(time));
			return addResponse(Config.STATUS_200, Config.MSG_200, saveStudy,Config.RESP_MODE_10, "");
		}catch (XueWenServiceException e) {
			e.printStackTrace();
			return addResponse(e.getCode(), e.getMessage(), false,
			Config.RESP_MODE_10, "");
		} catch (Exception e) {
			e.printStackTrace();
			return addResponse(Config.STATUS_505, Config.MSG_505, false,Config.RESP_MODE_10, "");
		}	
	}
	
	
	
	
}

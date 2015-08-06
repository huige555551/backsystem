package operation.controller.black;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import operation.BaseController;
import operation.exception.XueWenServiceException;
import operation.pojo.user.NewUserName;
import operation.pojo.user.UserName;
import operation.service.black.BlackService;
import operation.service.user.NewUserNameService;
import operation.service.user.UserNameService;
import tools.Config;
import tools.ResponseContainer;
import tools.StringUtil;


@RestController
@RequestMapping("/black")
public class BlackController extends BaseController{
	
	@Autowired
	private BlackService blackService;
	
	@Autowired
	private UserNameService userNameService;
	
	@Autowired
	private NewUserNameService newUserNameService;
	/**
	 * 增加黑名单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("create")
	public @ResponseBody ResponseContainer create(HttpServletRequest request) {
		String blackName = request.getParameter("blackName");
		if(null != blackName){
			if(!StringUtil.isEmpty(blackName)){
				try {
					blackName = URLDecoder.decode(blackName, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		try {
			//blackService.saveBlack(blackName);
			return addResponse(Config.STATUS_200, Config.MSG_CREATE_200, true,
					Config.RESP_MODE_10, "");
		} catch (Exception e) {
			e.printStackTrace();
			return addResponse(Config.STATUS_505, Config.MSG_505, false,
					Config.RESP_MODE_10, "");
		}
	}
	
	@RequestMapping("add")
	public @ResponseBody ResponseContainer add(HttpServletRequest request) throws IOException {
		 String encoding="GBK";
		 String filePath = "/Users/nes/Downloads/11.txt";
         File file=new File(filePath);
         if(file.isFile() && file.exists()){ //判断文件是否存在
             InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);//考虑到编码格式
             BufferedReader bufferedReader = new BufferedReader(read);
             String lineTxt = null;
             while((lineTxt = bufferedReader.readLine()) != null){
            	 UserName un = new UserName(lineTxt);
            	 userNameService.save(un);
             }
             read.close();
		
	}
         return  null;
	}
	
//	@RequestMapping("addNickName")
//	public @ResponseBody ResponseContainer addNickName(HttpServletRequest request) throws IOException {
//		 String encoding="GBK";
//		 String filePath = "/Users/nes/Downloads/11.txt";
//         File file=new File(filePath);
//         if(file.isFile() && file.exists()){ //判断文件是否存在
//             InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);//考虑到编码格式
//             BufferedReader bufferedReader = new BufferedReader(read);
//             String lineTxt = null;
//             List<String> nickName = new ArrayList<String>();
//             NewUserName  newUserName = new NewUserName();
//             while((lineTxt = bufferedReader.readLine()) != null){
//            	 nickName.add(lineTxt);
//             }
//             newUserName.setName(nickName);
//             newUserNameService.save(newUserName);
//             read.close();
//		
//	}
//         return  null;
//	}
//	
//
//	@RequestMapping("getNickName")
//	public @ResponseBody ResponseContainer getNickName(HttpServletRequest request) throws IOException {
//		List<NewUserName> newUserNameList = newUserNameService.getUserName();
//		NewUserName  newUserName = null;
//		List<String> nickName = new ArrayList<String>();
//		if(newUserNameList!=null){
//			newUserName = newUserNameList.get(0);
//			nickName = newUserName.getName();
//			System.out.println(nickName.size());
//			String un = nickName.get(StringUtil.getOneInt(nickName.size()-10));
//			System.out.println(un);
//		}
//		return null;
//	}
	
}

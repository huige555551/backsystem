package operation.controller.oss;

import operation.BaseController;
import operation.exception.XueWenServiceException;
import operation.service.words.StopWordsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tools.Config;
import tools.ResponseContainer;

@RestController
@RequestMapping("oss/stopWords/")
public class OssStopWordsController extends BaseController {
	@Autowired
	private StopWordsService stopWordsService;
	
	/**
	 * 
	 * @Title: add
	 * @auther Tangli
	 * @Description: 添加过滤词
	 * @param word
	 * @return ResponseContainer
	 * @throws
	 */
	@RequestMapping("add")
	public @ResponseBody ResponseContainer add(String word){
		try {
			stopWordsService.addWords(word);
			return addResponse(Config.STATUS_200, Config.MSG_200, true, Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			return addResponse(Config.STATUS_201, Config.MSG_201, false, Config.RESP_MODE_10, "");
		}
	}
	
	/**
	 * 
	 * @Title: add
	 * @auther Tangli
	 * @Description:删除过滤词
	 * @param word
	 * @return ResponseContainer
	 * @throws
	 */
	@RequestMapping("delete")
	public @ResponseBody ResponseContainer delete(String word){
		try {
			stopWordsService.delete(word);
			return addResponse(Config.STATUS_200, Config.MSG_200, true, Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			return addResponse(Config.STATUS_201, Config.MSG_201, false, Config.RESP_MODE_10, "");
		}
	}
	
	
	/**
	 * 
	 * @Title: add
	 * @auther Tangli
	 * @Description:删除所有过滤词
	 * @param word
	 * @return ResponseContainer
	 * @throws
	 */
	@RequestMapping("deleteAll")
	public @ResponseBody ResponseContainer deleteAll(){
		try {
			stopWordsService.deleteAll();
			return addResponse(Config.STATUS_200, Config.MSG_200, true, Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			return addResponse(Config.STATUS_201, Config.MSG_201, false, Config.RESP_MODE_10, "");
		}
	}
	
	@RequestMapping("add/file")
	public @ResponseBody ResponseContainer addfile(@RequestParam MultipartFile[] myfiles){
		try {
			stopWordsService.addWordFile(myfiles);
			return addResponse(Config.STATUS_200, Config.MSG_200, true, Config.RESP_MODE_10, "");
		} catch (Exception e) {
			return addResponse(Config.STATUS_201, Config.MSG_201, false, Config.RESP_MODE_10, "");
		}
	}
	
	
}

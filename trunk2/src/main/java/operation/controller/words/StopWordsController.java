package operation.controller.words;

import operation.BaseController;
import operation.exception.XueWenServiceException;
import operation.service.words.StopWordsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tools.Config;
import tools.ResponseContainer;
@RestController
@RequestMapping("stopwords")
public class StopWordsController extends BaseController {
	@Autowired
	private StopWordsService stopWordsService;
	
	
	/**
	 * 
	 * @Title: addWord
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param word
	 * @return ResponseContainer
	 * @throws
	 */
	@RequestMapping(value="addWord")
	@ResponseBody
	public ResponseContainer addWord(String word){
		try {
			stopWordsService.addWords(word);
			return addResponse(Config.STATUS_200, Config.MSG_200, true, Config.RESP_MODE_10, "");
		} catch (XueWenServiceException e) {
			e.printStackTrace();
			return addResponse(e.getCode(), e.getMessage(), false, Config.RESP_MODE_10, "");
		}	
	}
	
	@RequestMapping(value="test")
	@ResponseBody
	public ResponseContainer test(String word){
			//String string=stopWordsService.removeWord(word);
			return addResponse(Config.STATUS_200, Config.MSG_200, true, Config.RESP_MODE_10, "");

	}
}

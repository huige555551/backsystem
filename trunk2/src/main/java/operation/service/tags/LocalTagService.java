package operation.service.tags;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import tools.HttpUtil;
import tools.ReponseData;
import tools.ResponseContainer;
import tools.StringUtil;

/**
 * 
* @ClassName: LocalTagService
* @Description: 用于标签操作
* @author tangli
* @date 2015年3月31日 下午1:27:20
*
 */
@Service
public class LocalTagService {
	@Value("${tag.service.url}")
	private String tagServiceUrl;//"http://bj.tag.ztiao.cn/";
	/**
	 * 
	 * @auther tangli
	 * @Description: 通过关键词来匹配标签
	 * @param words  需要匹配的关键词
	 * @return String 返回以","分割的tasNames String 未匹配返回""
	 * @Date:2015年4月23日
	 * @throws
	 */
	public String getTagNamesByAnalysis(String words) {
		if (StringUtil.isBlank(words)) {
			return "";
		} else {
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("words", words);
			ResponseContainer responseContainer = HttpUtil.doPost(tagServiceUrl
					+ "tag/tagsByWords", args, ResponseContainer.class);
			int code = responseContainer.getStatus();
			if (code == 200) {
				ReponseData data = (ReponseData) responseContainer.getData();
				if (data != null) {
					@SuppressWarnings("unchecked")
					List<String> tasg = (List<String>) data.getResult();
					StringBuilder builder = new StringBuilder();
					
					for (String string : tasg) {
						builder.append(string + ",");
					}
					if(tasg.size()>0){
						builder.deleteCharAt(builder.lastIndexOf(","));
					}
					return builder.toString();

				} else {
					return "";
				}
			} else {
				return "";
			}
		}
	}
	@Test
	public void test(){
		String string=getTagNamesByAnalysis("java net 马云  IT 意见");
		System.out.println(string);
	}
	
}

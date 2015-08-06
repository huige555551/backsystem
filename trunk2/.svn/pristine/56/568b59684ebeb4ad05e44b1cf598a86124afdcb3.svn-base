package operation.service.topics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import operation.exception.XueWenServiceException;
import operation.pojo.topics.Topic;
import operation.pojo.topics.TopicResponse;
import operation.pojo.topics.TopicTopResult;
import operation.pojo.topics.WeeksTopicTopResult;
import operation.repo.topics.TopResultRepository;
import operation.repo.topics.WeeksTopicTopResultRepository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import tools.Config;
import tools.YXTJSONHelper;

/**
 * 话题排行榜之神贴、穿越
 * 
 * @author nes
 *
 */
@Service
@Component
public class TopicRingService {

	private static final Logger logger = Logger.getLogger(TopicRingService.class);
	@Autowired
	private TopResultRepository topResultRepository;
	@Autowired
	private TopicService topicService;
	
	@Autowired
	private WeeksTopicTopResultRepository weeksTopicTopResultRepository;
	
	@Value("${tag.service.url}")
	private String tagServiceUrl;
	
	/**
	 * 根据群组或者课程来查询主题列表
	 * 
	 * @param groupId
	 * @param courseId
	 * @param pageable
	 * @return
	 * @throws XueWenServiceException
	 */
	public Page<TopicTopResult> findAll(Pageable pageable)throws XueWenServiceException {
		return topResultRepository.findAll(pageable);
	}


	/**
	 * 将结果转换成topic
	 * @param content
	 * @return
	 */
	public Object toJSONHelper(List<TopicTopResult> topicTopResult) throws XueWenServiceException{
		List<Object> topicRes = new ArrayList<Object>();
		if (topicTopResult == null || topicTopResult.size() <= 0) {
		} else {
			for (int i = 0; i < topicTopResult.size(); i++) {
				Map<String,Object> addAndModifyMap=new HashMap<String, Object>();
				String[] exclude = {"post","praiseResponse","position","tagName","group","categoryId","childCategoryId"};
				topicRes.add(YXTJSONHelper.getExObjectAttrJsonObject(toResponse(topicTopResult.get(i)), addAndModifyMap, exclude));
			}
		}
		return topicRes;
		
	}
	/**
	 * 单个对象转换成前端对象
	 * 
	 * @param topic
	 * @return
	 * @throws XueWenServiceException
	 */
	public TopicResponse toResponse(TopicTopResult topicTopResult) throws XueWenServiceException {
		if (topicTopResult == null) {
			throw new XueWenServiceException(Config.STATUS_201,Config.MSG_NODATA_201, null);
		}
		Topic topic = topicService.findOneById(topicTopResult.getTopicTopResultId());
		RestTemplate restTemplate = new RestTemplate();
		String tag = restTemplate.getForObject(tagServiceUrl
				+ "tag/getTagsByIdAndType?domain=" + "yxtapp" + "&itemId="
				+ topic.getTopicId() + "&itemType=" + Config.TAG_TYPE_TOPIC, String.class);
		JSONObject objj = JSONObject.fromObject(tag);
		JSONObject obss = objj.getJSONObject("data");
		net.sf.json.JSONArray childs = obss.getJSONArray("result");
		TopicResponse topicRespone = new TopicResponse(topic);
		topicRespone.setTagName(childs);
		return topicRespone;
	}
	
	/**
	 * 根据群组或者课程来查询主题列表
	 * 
	 * @param groupId
	 * @param courseId
	 * @param pageable
	 * @return
	 * @throws XueWenServiceException
	 */
	public Page<WeeksTopicTopResult> findAllWeeksTopic(Pageable pageable)throws XueWenServiceException {
		return weeksTopicTopResultRepository.findAll(pageable);
	}
	
	
	/**
	 * 将结果转换成topic
	 * @param content
	 * @return
	 */
	public Object toJSONHelperForWeeks(List<WeeksTopicTopResult> weksTopicTopResult) throws XueWenServiceException{
		List<Object> topicRes = new ArrayList<Object>();
		if (weksTopicTopResult == null || weksTopicTopResult.size() <= 0) {
		} else {
			for (int i = 0; i < weksTopicTopResult.size(); i++) {
				Map<String,Object> addAndModifyMap=new HashMap<String, Object>();
				String[] exclude = {"post","praiseResponse","position","tagName","group","categoryId","childCategoryId"};
				topicRes.add(YXTJSONHelper.getExObjectAttrJsonObject(toResponse(weksTopicTopResult.get(i)), addAndModifyMap, exclude));
			}
		}
		return topicRes;
		
	}
	
	/**
	 * 单个对象转换成前端对象
	 * 
	 * @param topic
	 * @return
	 * @throws XueWenServiceException
	 */
	public TopicResponse toResponse(WeeksTopicTopResult weeksTopicTopResult) throws XueWenServiceException {
		if (weeksTopicTopResult == null) {
			throw new XueWenServiceException(Config.STATUS_201,Config.MSG_NODATA_201, null);
		}
		Topic topic = topicService.findOneById(weeksTopicTopResult.getTopicTopResultId());
		RestTemplate restTemplate = new RestTemplate();
		String tag = restTemplate.getForObject(tagServiceUrl
				+ "tag/getTagsByIdAndType?domain=" + "yxtapp" + "&itemId="
				+ topic.getTopicId() + "&itemType=" + Config.TAG_TYPE_TOPIC, String.class);
		JSONObject objj = JSONObject.fromObject(tag);
		JSONObject obss = objj.getJSONObject("data");
		net.sf.json.JSONArray childs = obss.getJSONArray("result");
		TopicResponse topicRespone = new TopicResponse(topic);
		topicRespone.setTagName(childs);
		return topicRespone;
	}

}

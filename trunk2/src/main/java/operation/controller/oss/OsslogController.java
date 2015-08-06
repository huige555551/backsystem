package operation.controller.oss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import operation.OssController;
import operation.exception.XueWenServiceException;
import operation.pojo.drycargo.Drycargo;
import operation.pojo.group.XueWenGroup;
import operation.pojo.log.OssLog;
import operation.pojo.pub.QueryModel;
import operation.pojo.pub.QueryModelMul;
import operation.pojo.topics.Images;
import operation.pojo.topics.Post;
import operation.pojo.topics.SubPost;
import operation.pojo.topics.Topic;
import operation.pojo.user.User;
import operation.service.group.GroupService;
import operation.service.log.OssLogService;
import operation.service.topics.PostService;
import operation.service.topics.TopicService;
import operation.service.user.UserService;
import operation.service.util.ObjCopyPropsService;

import org.apache.commons.lang.StringUtils;
import org.jivesoftware.smackx.hoxt.packet.HttpOverXmppReq.Req;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tools.Config;
import tools.JSON2ObjUtil;
import tools.PageRequestTools;
import tools.ReponseDataTools;
import tools.ResponseContainer;

@RestController
@RequestMapping("/oss/log")
@Configuration
public class OsslogController extends OssController {

	@Autowired
	private OssLogService ossLogService;
	 

	public OsslogController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 话题删除
	 * 
	 * @param request
	 * @return
	 * @throws
	 * @throws IOException
	 */
	@RequestMapping(value = "/getall")
	public @ResponseBody ResponseContainer getall(HttpServletRequest request) {
		try {
			 
			List<OssLog> l = ossLogService.getall();
			return addResponse(Config.STATUS_200, Config.MSG_DELETE_200, l, Config.RESP_MODE_10, "");
		} catch (Exception e) {
			return addResponse(Config.STATUS_505, Config.MSG_505, false, Config.RESP_MODE_10, "");
		}
	}
	
	
	
	
	
}

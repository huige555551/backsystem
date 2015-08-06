package operation.service.queue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import operation.exception.XueWenServiceException;
import operation.pojo.group.XueWenGroup;
import operation.pojo.remmond.Remmond;
import operation.pojo.user.User;
import operation.repo.group.GroupRepository;
import operation.repo.user.UserRepository;
import operation.service.remmond.RemmondService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import tools.StringUtil;

@Service
@Component
@EnableScheduling
public class QueueService {
	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	public UserRepository userRepo;

	@Autowired
	public GroupRepository groupRepo;

	@Autowired
	public RemmondService remmondService;

	public QueueService() {

	}

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"HH:mm:ss");

	@Scheduled(fixedRate = 1000000)
	public void getWork() throws XueWenServiceException {

		System.out.println("The time is now " + dateFormat.format(new Date()));
		Query query = new Query();
		WorkNumber work = mongoTemplate.findAndRemove(query, WorkNumber.class);
		if(null != work){
			if ("userRegist".equals(work.getWorkName())) {
				this.workerForUserRegist(work);
			}
			if ("createGroup".equals(work.getWorkName())) {
				this.workerForCreateGroup(work);
			}
		}
		// return work.getWorkName();
	}

	private void workerForUserRegist(WorkNumber worker) {
		User user = userRepo.findOneById(worker.getUserIdOrGroupId());
		if(null != user.getTag()){
			List<XueWenGroup> groupList = groupRepo.findAll();
			Map<String, String> recommandRes = new HashMap<String, String>();
	
			if (null != groupList) {
				for (int i = 0; i < groupList.size(); i++) {
					if (this.matchResult(user, groupList.get(i)).size() > 0) {
						recommandRes
								.putAll(this.matchResult(user, groupList.get(i)));
					}
				}
				Remmond r = new Remmond();
				r.setUserId(user.getId());
				r.setGroupId(recommandRes);
				remmondService.addRemmond(r);
			}
		}
	}

	private void workerForCreateGroup(WorkNumber worker) {

		XueWenGroup group = groupRepo.findOneById(worker.getUserIdOrGroupId());

		List<User> userList = userRepo.findAll();

		if (null != userList) {
			for (int i = 0; i < userList.size(); i++) {
				Map<String, String> recommandRes = new HashMap<String, String>();
				if (this.matchResult2(userList.get(i), group).size() > 0) {

					recommandRes.putAll(this.matchResult2(userList.get(i),
							group));
					Remmond r = remmondService.findOneByUserId(userList.get(i)
							.getId());
					if (r == null) {
						r = new Remmond();
						r.setUserId(userList.get(i).getId());
						r.setGroupId(recommandRes);
					} else {
						r.getGroupId().putAll(recommandRes);
					}
					remmondService.save(r);
				}

			}
		}
	}

	private Map<String, String> matchResult(User user, XueWenGroup group) {
		int matchCount = 0;
		String userTagString = user.getTag();
		List<Object> userTagList = new ArrayList<Object>();
		if (!StringUtil.isEmpty(userTagString)) {
			String[] tagArry = userTagString.split(",");
			for (int i = 0; i < tagArry.length; i++) {
				userTagList.add(tagArry[i]);
				matchCount +=  this.matchGroupInfo(group, tagArry[i]);
			}
		}

//		List<Object> groupTagList = group.getTag();
		Map<String, String> resultGroupIds = new HashMap<String, String>();
//		if(null!=groupTagList){
//			groupTagList.retainAll(userTagList);
//		}
//		int totalCount = 0;
//		if (groupTagList != null) {
//			totalCount = groupTagList.size() + matchCount;
//		} else {
//			totalCount = matchCount;
//		}
//		if (totalCount > 0) {
//			resultGroupIds.put(group.getId().toString(),
//					Integer.toString(totalCount));
//		}

		return resultGroupIds;
	}

	private Map<String, String> matchResult2(User user, XueWenGroup group) {
		int matchCount = 0;
		String userTagString = user.getTag();
		List<Object> userTagList = new ArrayList<Object>();
		if (!StringUtil.isEmpty(userTagString)) {
			String[] tagArry = userTagString.split(",");
			for (int i = 0; i < tagArry.length; i++) {
				userTagList.add(tagArry[i]);
				matchCount += this.matchGroupInfo(group, tagArry[i]);
			}
		}

		//List<Object> groupTagList = group.getTag();
		Map<String, String> resultGroupIds = new HashMap<String, String>();
//		if(userTagList != null && groupTagList != null){
//		userTagList.retainAll(groupTagList);
//		}
//		int totalCount = 0;
//		if (userTagList != null) {
//			totalCount = userTagList.size() + matchCount;
//		} else {
//			totalCount = matchCount;
//		}
//		if (totalCount > 0) {
//			resultGroupIds.put(group.getId().toString(),
//					Integer.toString(totalCount));
//		}

		return resultGroupIds;
	}

	private int matchGroupInfo(XueWenGroup group, String tag) {
		int myMatchCount = 0;
		if(null != group.getIntro()){
			if (group.getIntro().contains(tag)) {
				myMatchCount++;
			}
		}
		if(null != group.getGroupName()){
			if (group.getGroupName().contains(tag)) {
				myMatchCount++;
			}
		}
		return myMatchCount;
	}

	private Map<String, String> matchResultForGroupIntro(User user,
			XueWenGroup group) {

		int matchCount = 0;
		String userTagString = user.getTag();
		List<Object> userTagList = new ArrayList<Object>();
		String[] tagArry = null;
		if (!StringUtil.isEmpty(userTagString)) {
			tagArry = userTagString.split(",");
			for (int i = 0; i < tagArry.length; i++) {
				if (group.getIntro().contains(tagArry[i])) {
					matchCount++;
				}

			}
		}
		Map<String, String> resultGroupIds = new HashMap<String, String>();
		if (matchCount > 0) {
			resultGroupIds.put(user.getId().toString(),
					Integer.toString(matchCount));
		}

		return resultGroupIds;
	}

	public void setWork(String workName, String userIdOrGroupId)
			throws XueWenServiceException {
		WorkNumber g = new WorkNumber();
		g.setWorkName(workName);
		g.setUserIdOrGroupId(userIdOrGroupId);
		mongoTemplate.save(g);
	}

}

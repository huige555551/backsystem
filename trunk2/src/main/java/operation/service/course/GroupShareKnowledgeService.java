package operation.service.course;

import java.util.ArrayList;
import java.util.List;

import operation.exception.XueWenServiceException;
import operation.pojo.course.GroupShareKnowledge;
import operation.pojo.course.Knowledge;
import operation.pojo.group.XueWenGroup;
import operation.pojo.user.User;
import operation.repo.course.GroupShareKnowledgeRepository;
import operation.repo.course.GroupShareKnowledgeTemplate;
import operation.repo.course.KnowledgeRepository;
import operation.service.group.GroupService;
import operation.service.user.UserService;
import operation.repo.course.KnowledgeTemplate;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tools.Config;
import tools.StringUtil;

/**
 * 
 * @ClassName: GroupShareKnowledgeService
 * @Description:群知识
 * @author Jack Tang
 * @date 2014年12月24日 上午11:24:34
 *
 */
@Service
public class GroupShareKnowledgeService {

	private static final Logger logger = Logger
			.getLogger(KnowledgeService.class);
	@Autowired
	private GroupShareKnowledgeRepository gsknoKnowledgeRepository;
	@Autowired
	private KnowledgeRepository knowledgeRepository;
	@Autowired
	private GroupService groupService;
	@Autowired
	private UserService userService;
	@Autowired
	private KnowledgeTemplate knowledgeTemplate;
	
	@Autowired
	private GroupShareKnowledgeTemplate groupShareKnowledgeTemplate;
	
	@Autowired
	private  KnowledgeService knowledgeService;

	/**
	 * @throws XueWenServiceException 
	 * 
	 * @Title: insert
	 * @Description: 插入一条群知识
	 * @param groupShareKnowledge
	 *            void
	 * @throws
	 */
	public void insert(GroupShareKnowledge groupShareKnowledge) throws XueWenServiceException {
		//增加引用次数
		Knowledge knowledge=knowledgeRepository.findOne(groupShareKnowledge.getKnowledge());
		knowledge.setArc(knowledge.getArc()+1);
		knowledgeRepository.save(knowledge);
		gsknoKnowledgeRepository.save(groupShareKnowledge);

	}

	/**
	 * @throws XueWenServiceException 
	 * 
	 * @Title: add
	 * @Description:添加群知识
	 * @param knowledgeId
	 * @param groupId
	 * @param userId
	 * @return GroupShareKnowledge
	 * @throws
	 */
	public GroupShareKnowledge add(String knowledgeId, String groupId,
			String userId) throws XueWenServiceException {
		XueWenGroup group=groupService.findById(groupId);
		User user=userService.findOne(userId);
		Knowledge knowledge=knowledgeRepository.findOne(knowledgeId);
		GroupShareKnowledge groupShareKnowledge =createOne(user, knowledge, group);
		insert(groupShareKnowledge);
		return groupShareKnowledge;
	}

	/**
	 * @param pageable 
	 * 
	 * @Title: getByGroupId
	 * @Description: 通过Group获取关系Bean
	 * @param groupId
	 * @return
	 * @throws XueWenServiceException
	 *             List<GroupShareKnowledge>
	 * @throws
	 */
	public Page<GroupShareKnowledge> getByGroupId(String groupId, Pageable pageable)
			throws XueWenServiceException {
		if (StringUtil.isBlank(groupId)) {
			logger.info("---------参数非法---------");
			throw new XueWenServiceException(Config.STATUS_200, Config.MSG_201,
					null);
		}

		return gsknoKnowledgeRepository.findByGroupId(groupId,pageable);

	}
	
	/**
	 * @param pageable 
	 * 
	 * @Title: getByGroupId
	 * @Description: 通过Group获取关系Bean
	 * @param groupId
	 * @return
	 * @throws XueWenServiceException
	 *             List<GroupShareKnowledge>
	 * @throws
	 */
	public List<GroupShareKnowledge> getByGroupId(String groupId)
			throws XueWenServiceException {
		if (StringUtil.isBlank(groupId)) {
			logger.info("---------参数非法---------");
			throw new XueWenServiceException(Config.STATUS_200, Config.MSG_201,
					null);
		}

		return gsknoKnowledgeRepository.findByGroupId(groupId);

	}
	/**
	 * 
	 * @Title: findByGidAndKid
	 * @Description: 通过gid和Kid
	 * @param gid
	 * @param kid
	 * @return GroupShareKnowledge
	 * @throws
	 */
	public GroupShareKnowledge findByGidAndKid(String gid, String kid) {
		return gsknoKnowledgeRepository.findOneByGroupIdAndKnowledge(gid, kid);
	}
	/**
	 * @throws XueWenServiceException 
	 * 
	 * @Title: del
	 * @Description: 删除
	 * @param groupShareKnowledge void
	 * @throws
	 */
	public void del(GroupShareKnowledge groupShareKnowledge) throws XueWenServiceException{
		gsknoKnowledgeRepository.delete(groupShareKnowledge);
		//分享次数-1
		knowledgeService.removeArc(groupShareKnowledge.getKnowledge());
	}
	
	/**
	 * 根据分享IDs删除群组下的所有的分享
	 * @param drycargoId
	 * @throws XueWenServiceException
	 */
	public boolean deleteByIds(String knowledgeIds,String groupId)throws XueWenServiceException{
		String[] ids;
		if(!StringUtil.isBlank(knowledgeIds)){
			ids=knowledgeIds.split(",");
		}else{
			throw new XueWenServiceException(Config.STATUS_201, "请选择需要删除的知识", null);
		}
		try {
			List<Object> idList=new ArrayList<Object>();
			for (String id : ids) {
				idList.add(id);
				//删除所有的评论(包括主楼评论，副楼评论)
//				postService.deleteByTopicId(id);
			}
			groupShareKnowledgeTemplate.deleteByIds(idList,groupId);
			//删除所有的赞记录
//			praiseService.deleteBySourceIds(idList);
//			//删除所有的不攒接口
//			unPraiseService.deleteBySourceIds(idList);
//			//删除所有的分享记录
//			shareService.deleteBySourceIds(idList);
//			//删除所有的分享记录
//			favService.deleteBySourceIds(idList);
//			//删除干货记录
//			drycargoTemplate.deleteByDrycargoIds(idList);
			
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 
	 * @Title: countByGroupId
	 * @Description: 获取群组分享数量
	 * @param groupId
	 * @return int
	 * @throws
	 */
	public int countByGroupId(String groupId){
		return gsknoKnowledgeRepository.countByGroupId(groupId);
	}
	
	public Page<GroupShareKnowledge> findByKnowledgeIn(List<String> knowledgeIds,Pageable pageable) {
		return gsknoKnowledgeRepository.findByKnowledgeIn(knowledgeIds,pageable);
	}
	
	/**
	 * 
	 * @Title: findByKnowledgeIn
	 * @auther Tangli
	 * @Description: 通过知识id取
	 * @param knowledgeIds
	 * @return List<GroupShareKnowledge>
	 * @throws
	 */
	public List<GroupShareKnowledge> findByKnowledgeIn(List<String> knowledgeIds) {
		return gsknoKnowledgeRepository.findByKnowledgeIn(knowledgeIds);
	}
	public GroupShareKnowledge findOneByKnowledgeId(String knowledgeId){
		return gsknoKnowledgeRepository.findOneByKnowledge(knowledgeId);
	}
	/**
	 * @throws XueWenServiceException 
	 * 
	 * @Title: createOne
	 * @Description: 构建一个关系
	 * @param shareUser
	 * @param knowledge
	 * @param xueWenGroup
	 * @return GroupShareKnowledge
	 * @throws
	 */
	public GroupShareKnowledge createOne(User shareUser,Knowledge knowledge,XueWenGroup xueWenGroup) throws XueWenServiceException{
		GroupShareKnowledge gkng=new GroupShareKnowledge();
		gkng.setGroupId(xueWenGroup.getId());
		gkng.setGroupName(xueWenGroup.getGroupName());
		gkng.setGroupLogoUrl(xueWenGroup.getLogoUrl());
		gkng.setKnowledge(knowledge.getId());
		gkng.setKngName(knowledge.getName());
		gkng.setKngTagName(knowledge.getTagNames());
		gkng.setKngType(knowledge.getKngType());
		gkng.setShareUserId(shareUser.getId());
		gkng.setUserLogo(shareUser.getLogoURL());
		gkng.setShareUserName(shareUser.getNickName());
		gkng.setShareTime(System.currentTimeMillis());
		return gkng;
	}
	
	public GroupShareKnowledge createOne(GroupShareKnowledge gkng,User shareUser,Knowledge knowledge,XueWenGroup xueWenGroup) throws XueWenServiceException{
		//GroupShareKnowledge gkng=new GroupShareKnowledge();
		gkng.setGroupId(xueWenGroup.getId());
		gkng.setGroupName(xueWenGroup.getGroupName());
		gkng.setGroupLogoUrl(xueWenGroup.getLogoUrl());
		gkng.setKnowledge(knowledge.getId());
		gkng.setKngName(knowledge.getName());
		gkng.setKngTagName(knowledge.getTagNames());
		gkng.setKngType(knowledge.getKngType());
		gkng.setShareUserId(shareUser.getId());
		gkng.setUserLogo(shareUser.getLogoURL());
		gkng.setShareUserName(shareUser.getNickName());
		gkng.setShareTime(System.currentTimeMillis());
		return gkng;
	}
	/**
	 * 
	 * @Title: findByGroupIdAndkngNameLike
	 * @Description: 搜索群分享
	 * @param groupId
	 * @param keyWords
	 * @param pageable
	 * @return
	 * @throws XueWenServiceException Page<GroupShareKnowledge>
	 * @throws
	 */
	public Page<GroupShareKnowledge> findByGroupIdAndkngNameLike(
			String groupId, String keyWords, Pageable pageable) throws XueWenServiceException {
		if(StringUtil.isBlank(groupId)){
			throw new XueWenServiceException(Config.STATUS_201, "群Id不能为空",null);
		}
		
		if(!StringUtil.isBlank(keyWords)){
			return gsknoKnowledgeRepository.findByGroupIdAndKngNameLike(groupId,keyWords,pageable);
		}
		else{
			return gsknoKnowledgeRepository.findByGroupId(groupId,pageable);
		}
	}
	
	/**
	 * 
	 * @Title: findByGroupIdAndkngNameLike
	 * @Description: 搜索群分享
	 * @param groupId
	 * @param keyWords
	 * @param pageable
	 * @return
	 * @throws XueWenServiceException Page<GroupShareKnowledge>
	 * @throws
	 */
	public List<GroupShareKnowledge> findByGroupIdAndkngNameLike(
			String groupId, String keyWords) throws XueWenServiceException {
		if(StringUtil.isBlank(groupId)){
			throw new XueWenServiceException(Config.STATUS_201, "群Id不能为空",null);
		}
		
		if(!StringUtil.isBlank(keyWords)){
			return gsknoKnowledgeRepository.findByGroupIdAndKngNameLike(groupId,keyWords);
		}
		else{
			return gsknoKnowledgeRepository.findByGroupId(groupId);
		}
	}
	

	public List<GroupShareKnowledge> findByKngType(Integer type) {
		 return gsknoKnowledgeRepository.findByKngType(type);
	}

	public void save(List<GroupShareKnowledge> gs) {
		gsknoKnowledgeRepository.save(gs)	;	
	}

}

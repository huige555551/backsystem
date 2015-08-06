package operation.service.drycargo;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import operation.exception.XueWenServiceException;
import operation.pojo.box.Box;
import operation.pojo.box.BoxPost;
import operation.pojo.callpolice.Callpolice;
import operation.pojo.drycargo.Drycargo;
//import operation.pojo.drycargo.DrycargoBean;
import operation.pojo.drycargo.DrycargoResponse;
import operation.pojo.dynamic.GroupDynamic;
import operation.pojo.fav.Fav;
import operation.pojo.group.XueWenGroup;
import operation.pojo.pub.QueryModel;
import operation.pojo.tags.UserTagBean;
import operation.pojo.topics.Images;
import operation.pojo.topics.Topic;
import operation.pojo.user.User;
import operation.repo.box.BoxTemplate;
//import operation.repo.drycargo.DrycargoBeanRepository;
import operation.repo.drycargo.DrycargoRepository;
import operation.repo.drycargo.DrycargoTemplate;
import operation.repo.dynamic.GroupDynamicRepository;
import operation.repo.dynamic.GroupDynamicTemplate;
import operation.repo.group.GroupTemplate;
import operation.repo.user.UserRepository;
import operation.service.box.BoxPostService;
import operation.service.box.BoxService;
import operation.service.callpolice.CallpoliceSevice;
import operation.service.category.CategoryService;
import operation.service.dynamic.GroupDynamicService;
import operation.service.fav.FavService;
import operation.service.file.MyFileService;
import operation.service.group.GroupService;
import operation.service.praise.PraiseService;
import operation.service.praise.UnPraiseService;
import operation.service.rabbitmq.RabbitmqService;
import operation.service.share.ShareService;
import operation.service.tags.LocalTagService;
import operation.service.tags.TagService;
import operation.service.topics.PostService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import tools.Config;
import tools.JSON2ObjUtil;
import tools.PageRequestTools;
import tools.ReponseData;
import tools.ReponseDataTools;
import tools.StringUtil;
import tools.YXTJSONHelper;


@Service
@Component
public class DrycargoService {
	private static final Logger logger = Logger.getLogger(DrycargoService.class);
	
	@Autowired
	public GroupDynamicRepository groupDynamicRepository;
	
	@Autowired
	public DrycargoRepository drycargoRepository;
	
	@Autowired
	public MyFileService myFileService;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	public GroupService groupService;
	
	@Autowired
	private TagService tagService;
	
	@Autowired
	private LocalTagService localTagService;
	
	@Autowired 
	private DrycargoTemplate drycargoTemplate;
	
	@Autowired
	public PraiseService praiseService;
	@Autowired
	public UnPraiseService unPraiseService;
	
	@Autowired
	public UserDrycargoService  userDrycargoService;
	
	@Autowired
	public FavService favService;
	@Autowired
	public ShareService shareService;
	
	@Autowired
	public PostService postService;
	@Autowired
	public BoxService boxService;
	@Autowired
	BoxTemplate boxTemplate;
	
	@Autowired
	public  GroupTemplate groupTemplate;
	
	@Autowired
	public GroupDynamicTemplate groupDynamicTemplate;
	
	@Value("${tag.service.url}")
	private String tagServiceUrl;	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private BoxPostService boxPostService;
	
	@Autowired
	private RabbitmqService rabbitService;
	@Autowired
	private GroupDynamicService groupDynamicService;
	
	@Autowired
	private CallpoliceSevice callpoliceSevice;

	public DrycargoService(){
		super();
	}
	
	/**
	 * 查询某一群下得干货
	 * @param keywords
	 * @param pageable
	 * @return
	 */
	public Page<Drycargo> all(String keywords, int dryFlag,Pageable pageable) throws XueWenServiceException{
		if(StringUtil.isBlank(keywords)){
			throw new XueWenServiceException(Config.STATUS_601, Config.MSG_NOGROUP_601,null);
		}
		Page<Drycargo>  dryCargo = drycargoRepository.findByGroup(keywords,pageable);
		return dryCargo;
	}
	/**
	 * 查询某一群下得干货
	 * @param keywords
	 * @param pageable
	 * @return
	 */
	public Page<Drycargo> allByDryFlag(String keywords, int dryFlag,Pageable pageable) throws XueWenServiceException{
		if(StringUtil.isBlank(keywords)){
			throw new XueWenServiceException(Config.STATUS_601, Config.MSG_NOGROUP_601,null);
		}
		Page<Drycargo>  dryCargo = drycargoRepository.findByGroupAndDryFlag(keywords, dryFlag, pageable);
		return dryCargo;
	}
	
	/**
	 * 查询某一群下得干货Pc
	 * @param keywords
	 * @param pageable
	 * @return
	 */

	public Page<Drycargo> allPc(String groupId,String keyWords, Pageable pageable) throws XueWenServiceException{
		Page<Drycargo>  dryCargo = drycargoRepository.findByOthers(0, keyWords, groupId, pageable);
		return dryCargo;
	}
	 
	
	/**
	 * 查询某一群下得干货
	 * @param keywords
	 * @param pageable
	 * @return
	 */
	public Page<Drycargo> searchByDryFlag(String keywords,int dryFlag, Pageable pageable) throws XueWenServiceException{
		Page<Drycargo>  dryCargo = null;
		if(StringUtil.isBlank(keywords)){
			 dryCargo = drycargoRepository.findByGroupNotNull(pageable);
		}
		else{
			keywords =".*?(?i)"+keywords+".*";
			dryCargo = drycargoRepository.findByMessageRegexAndGroupNotNullOrDescriptionRegexAndGroupNotNull(keywords,keywords,pageable);
		}
		return dryCargo;
	}
		
	
	/**
	 * 发现干货（只查询6个，根据我所在的群组ID集合，过滤出本人不在的群组）
	 * @param userId
	 * @param pageable
	 * @return
	 * @throws XueWenServiceException
	 */
	public Page<Drycargo> findAll(int dryFlag,Pageable pageable)throws XueWenServiceException {
		//Page<Drycargo>  drycargo = drycargoRepository.findByDryFlagAndGroupNotNull(dryFlag,pageable);
		Page<Drycargo>  drycargo = drycargoRepository.findByReviewAndGroupNotNull(true,pageable);
		return drycargo;
	}
	/**
	 * 发布全部干货
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<Drycargo> findAll()throws XueWenServiceException{
		return drycargoRepository.findAll();
	}
	/**
	 * 发现更多干货
	 * @param pageable
	 * @return
	 */
	public Page<Drycargo> findAllDrycargo(Pageable pageable) throws XueWenServiceException{
		Page<Drycargo>  drycargo = drycargoRepository.findAll(pageable);
		return drycargo;
	}
	
	public UserTagBean createUserTag(User user,Drycargo dry,String tagName){
		UserTagBean utb = new UserTagBean();
		utb.setUserId(user.getId());
		utb.setUserName(user.getUserName());
		utb.setItemId(dry.getId());
		utb.setItemType(Config.TAG_TYPE_DRYCARGO);
		utb.setCtime(String.valueOf(System.currentTimeMillis()));
		utb.setTagName(tagName);
		return utb;
		
	}
	/**
	 * 获得某一人干货分享的数量
	 * @param userId
	 * @return
	 */
	public long getDryCount(String userId,int dryFlag){
		return drycargoTemplate.getCountsByDryFlagAndUser(userId,dryFlag);
	}
	
	
	/**
	 * 获得某一人分享的干货
	 * @param userId
	 * @return
	 */
//	public long getDryByUser(String userId){
//		return drycargoTemplate.getCountsByShareidsIn(Lists.newArrayList(userId));
//	}
	/**
	 * 获得某一群的干货数量
	 * @param groupId
	 * @return
	 */
	public int getDryCountByGroup(String groupId,int dryFlag){
		return drycargoRepository.countByGroup(groupId);
	}
	
	/** 
	* @author yangquanliang
	* @Description: 通过url获取干货是否存储
	* @param @param url
	* @param @return
	* @return int
	* @throws 
	*/ 
	public List<Drycargo> getDryByUrl(String url)
	{
		return drycargoRepository.findOneByUrl(url);
	}	

	/** 
	* @author yangquanliang
	* @Description: 更新或保存逻辑
	* @param @param dry
	* @param @return
	* @return Drycargo
	* @throws 
	*/ 
	public Drycargo saveDrycargo(Drycargo drycargo,String tagName)
	{
		if(tagName!=null||"".equals(tagName)){
			drycargo.setDrycargoTagName(tagName);
			drycargo =  drycargoRepository.save(drycargo);
			RestTemplate restTemplate=new RestTemplate();
			restTemplate.postForObject(tagServiceUrl+"tag/createTagBatch?domain="+Config.YXTDOMAIN+"&itemId="+drycargo.getId()+"&userId="+drycargo.getAuthorId()+"&userName="+drycargo.getAuthorName()+"&itemType="+Config.TAG_TYPE_DRYCARGO+"&tagNames="+tagName,null, String.class);
			//drycargo.setDrycargoTagName(tagName);
		}
		return drycargoRepository.save(drycargo);
	}
	
	
	/** 
	* @author yangquanliang
	* @Description: 更新或保存逻辑
	* @param @param dry
	* @param @return
	* @return Drycargo
	* @throws 
	*/ 
	public Drycargo updateDrycargo(Drycargo drycargo,String tagName)
	{
		if(tagName!=null||"".equals(tagName)){
			drycargo.setDrycargoTagName(tagName);
			drycargo =  drycargoRepository.save(drycargo);
			RestTemplate restTemplate=new RestTemplate();
			restTemplate.postForObject(tagServiceUrl+"tag/editTagsDelAdd?domain="+Config.YXTDOMAIN+"&itemId="+drycargo.getId()+"&userId="+drycargo.getAuthorId()+"&userName="+drycargo.getAuthorName()+"&itemType="+Config.TAG_TYPE_DRYCARGO+"&tagNames="+tagName,null, String.class);
			if(drycargo.getGroup()!=null&&!("".equals(drycargo.getGroup()))){
				GroupDynamic g=groupDynamicRepository.findByGroupIdAndSourceId(drycargo.getGroup().toString(),drycargo.getId());
				if(g!=null)
				{
					g.setTitle(drycargo.getMessage());
					g.setContent(drycargo.getDescription());
					Images images=new Images();
					images.setPicHeight(drycargo.getPicHeight());
					images.setPicUrl(drycargo.getFileUrl());
					images.setPicWidth(drycargo.getPicWidth());
					List<Images> l=new ArrayList<Images>();
					l.add(images);
					g.setImages(l);
					groupDynamicRepository.save(g);
				}
			}
		}
		return drycargoRepository.save(drycargo);
	}
	
	
	/** 
	* @author yangquanliang
	* @Description: 更新或保存逻辑
	* @param @param dry
	* @param @return
	* @return Drycargo
	* @throws 
	*/ 
	public Drycargo saveDrycargo(Drycargo drycargo)
	{
		return drycargoRepository.save(drycargo);
	}
	
	/** 
	* @author yangquanliang
	* @Description: 通过url获取干货是否存储
	* @param @param url
	* @param @return
	* @return int
	* @throws 
	*/ 
	public Drycargo getDryByUrlAndGroup(String url,String group) throws XueWenServiceException
	{
		return drycargoRepository.findOneByUrlAndGroup(url,group);
	}
	/**
	 * 创建干货
	 * @param user
	 * @param drycargo
	 * @param tagName
	 * @param drycargoId
	 * @return
	 * @throws XueWenServiceException
	 */
	public Drycargo uploadDry(User user,Drycargo drycargo,String tagName,String isOriginal,String image) throws XueWenServiceException {
		if(drycargo.getGroup()==null){
			throw new XueWenServiceException(Config.STATUS_601, Config.MSG_NOGROUP_601,null);
		}
		long time = System.currentTimeMillis();
		if(tagName!=null){
			tagName = JSON2ObjUtil.getArrayFromString(tagName);
		}else{
			tagName=localTagService.getTagNamesByAnalysis(drycargo.getMessage()+","+drycargo.getDescription());
		}
		try {
			drycargo.setUrl(URLDecoder.decode(drycargo.getUrl(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		try {
			drycargo.setFileUrl(URLDecoder.decode(drycargo.getFileUrl(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Drycargo dry = getDryByUrlAndGroup(drycargo.getUrl(),drycargo.getGroup().toString());
		if(dry!=null){
			if(drycargo.getDryFlag()==0){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_DRYCAROGEXIT_201,null);
			}else{
				throw new XueWenServiceException(Config.STATUS_201, Config.MSG_XUANYEGEXIT_201,null);
			}
		}else{
			drycargo.setAuthorId(user.getId());
			drycargo.setAuthorLogoUrl(user.getLogoURL());
			drycargo.setAuthorName(user.getNickName());
			drycargo.setCtime(time);
			if(StringUtil.isBlank(isOriginal)){
				drycargo.setIsOriginal(0);
			}
			logger.info(image+"调整支持多张图片（老版本也保持支持1张图片）");
			drycargo = changePicUrlToImage(drycargo,image,drycargo.getFileUrl(),String.valueOf(drycargo.getIsOriginal()));
			XueWenGroup group = groupService.findGroup(drycargo.getGroup().toString());
			//干货抓取时没有群组
			if(group!=null){
				drycargo.setCategoryId(group.getCategoryId()==null?null:group.getCategoryId().toString());
				drycargo.setChildCategoryId(group.getChildCategoryId()==null?null:group.getChildCategoryId().toString());
				drycargo.setGroupName(group.getGroupName());
				drycargo.setGroupLogoUrl(group.getLogoUrl());
			}
			
			if(tagName!=null){
				drycargo.setDrycargoTagName(tagName);
				drycargo =  drycargoRepository.save(drycargo);
				RestTemplate restTemplate=new RestTemplate();
				restTemplate.postForObject(tagServiceUrl+"tag/createTagBatch?domain="+Config.YXTDOMAIN+"&itemId="+drycargo.getId()+"&userId="+user.getId()+"&userName="+user.getNickName()+"&itemType="+Config.TAG_TYPE_DRYCARGO+"&tagNames="+tagName, null,String.class);
				//drycargo.setDrycargoTagName(tagName);
			}else{
				drycargo =  drycargoRepository.save(drycargo);
			}
			// 添加消息队列
			try {
				rabbitService.sendRegexMessage(drycargo.getId(),
						Config.TAG_TYPE_DRYCARGO);
				//创建群组干货动态
				createGroupDrycargoDynamic(drycargo);
			} catch (Exception e) {
				logger.error("=============发送消息队列发送错误===" + drycargo.getId()
						+ "====类别：" + Config.TAG_TYPE_DRYCARGO);
				e.printStackTrace();
			}
			
			return drycargo;
		}
	}
	/**
	 * 创建干货Pc
	 * @param user
	 * @param drycargoPc
	 * @param tagName
	 * @param drycargoId
	 * @return
	 * @throws XueWenServiceException
	 */
	public Drycargo uploadDryPc(User user,Drycargo drycargo,String tagName,String isOriginal,String image) throws XueWenServiceException {
		long time = System.currentTimeMillis();
		//自动推荐标签
		if(StringUtil.isBlank(tagName)){
			tagName=localTagService.getTagNamesByAnalysis(drycargo.getMessage()+","+drycargo.getDescription());
		}
//		try {
//			drycargo.setUrl(URLDecoder.decode(drycargo.getUrl(), "UTF-8"));
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		try {
//			drycargo.setFileUrl(URLDecoder.decode(drycargo.getFileUrl(), "UTF-8"));
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		Drycargo dry = getDryByUrlAndGroup(drycargo.getUrl(),drycargo.getGroup().toString());
		if(dry!=null){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_DRYCAROGEXIT_201,null);
		}else{
			String groupId=(String)drycargo.getGroup();
			XueWenGroup group=groupService.findById(groupId);
			drycargo.setCategoryId(group.getCategoryId()==null?Config.CATEFORY_DEFAULT_PRIMARY:group.getCategoryId().toString());
			drycargo.setChildCategoryId(group.getChildCategoryId()==null?Config.CATEFORY_DEFAULT_SENCOND:group.getChildCategoryId().toString());
			drycargo.setGroupName(group.getGroupName());
			drycargo.setGroupLogoUrl(group.getLogoUrl());
			drycargo.setAuthorId(user.getId());
			drycargo.setAuthorLogoUrl(user.getLogoURL());
			drycargo.setAuthorName(user.getNickName());
			drycargo.setCtime(time);
			if(StringUtil.isBlank(isOriginal)){//代表外链干货
				drycargo.setIsOriginal(0);
				try {
					drycargo.setUrl(URLDecoder.decode(drycargo.getUrl(), "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				try {
					drycargo.setFileUrl(URLDecoder.decode(drycargo.getFileUrl(), "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			if(StringUtil.isBlank(isOriginal)){
				drycargo.setIsOriginal(0);
			}
			logger.info(image+"调整支持多张图片（老版本也保持支持1张图片）");
			drycargo = changePicUrlToImage(drycargo,image,drycargo.getFileUrl(),String.valueOf(drycargo.getIsOriginal()));
			
			drycargo.setDrycargoTagName(tagName);
			if(tagName!=null){
				RestTemplate restTemplate=new RestTemplate();
				restTemplate.postForObject(tagServiceUrl+"tag/createTagBatch?domain="+"yxt"+"&itemId="+drycargo.getId()+"&userId="+user.getId()+"&userName="+user.getNickName()+"&itemType="+Config.TAG_TYPE_DRYCARGO+"&tagNames="+tagName,null, String.class);
			}
			drycargo =  drycargoRepository.save(drycargo);
			if(!StringUtil.isBlank(isOriginal)){
				if("1".equals(isOriginal)){ //原生干货
					drycargo.setUrl(Config.DRYCARGOCONTEXT+drycargo.getGroup().toString()+Config.DRYCARGOPATH+drycargo.getId()+Config.DRYCARGOVIEW);
					drycargo =  drycargoRepository.save(drycargo);
				}
			}
			
			// 添加消息队列
			try {
				rabbitService.sendRegexMessage(drycargo.getId(),
						Config.TAG_TYPE_DRYCARGO);
				//创建群组干货动态
				createGroupDrycargoDynamic(drycargo);
			} catch (Exception e) {
				logger.error("=============发送消息队列发送错误===" + drycargo.getId()
						+ "====类别：" + Config.TAG_TYPE_DRYCARGO);
				e.printStackTrace();
			}
			//增加图片大小审核
			if(StringUtil.doImagesOp(drycargo.getPicHeight(), drycargo.getPicWidth())){
				Callpolice call = new Callpolice();
				call.setSourceId(drycargo.getId());
				call.setType(Config.TYPE_DRYCARGO);
				call.setCtime(drycargo.getCtime());
				callpoliceSevice.save(call);
			}
			return drycargo;
		}
		
	}
	/**
	 * 判断干货是否存在
	 * @param user
	 * @param drycargo
	 * @param tagName
	 * @param drycargoId
	 * @return
	 * @throws XueWenServiceException
	 */
	public boolean isDrycargoExist(Drycargo drycargo) throws XueWenServiceException {
		Drycargo dry = getDryByUrlAndGroup(drycargo.getUrl(),drycargo.getGroup().toString());
		if(dry!=null){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_DRYCAROGEXIT_201,null);
		}
		return true;
	}
	/**
	 * 干货赞
	 * @param user
	 * @param dryCargoId
	 * @param groupId
	 * @return
	 * @throws XueWenServiceException
	 */
	public Drycargo dryCargoAddParise(User user,String dryCargoId,String groupId)throws XueWenServiceException{
			Drycargo dry = findOneByDrycargoId(dryCargoId,groupId);
			if(dry==null){
				throw new XueWenServiceException(Config.STATUS_201, Config.MSG_201,null);
			}
			String type=Config.TYPE_DRYCARGO_GROUP;
			if(dry.getDryFlag()==1){
				type = Config.TYPE_XUANYE_GROUP;
			}
			praiseService.addPraise(user,Config.YXTDOMAIN,"yxtapp",dryCargoId,type);
			dry.setLikesCount(dry.getLikesCount()+1);
			return saveDrycargo(dry);//保存小组干货赞数量
	}
	
	/**
	 * 干货赞
	 * @param user
	 * @param dryCargoId
	 * @param groupId
	 * @return
	 * @throws XueWenServiceException
	 */
	public Drycargo dryCargoAddParisePc(User user,String dryCargoId,String groupId)throws XueWenServiceException{
		Drycargo dry = findOneByDrycargoId(dryCargoId,groupId);
		if(dry==null){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_201,null);
		}
		praiseService.addPraiseTip(Config.YXTDOMAIN, Config.APPKEY_PC, dryCargoId, Config.TYPE_DRYCARGO_GROUP, user.getId());
		dry.setLikesCount(dry.getLikesCount()+1);
		return saveDrycargo(dry);//保存小组干货赞数量
	}
	
	/**
	 * 干货不赞
	 * @param user
	 * @param dryCargoId
	 * @param groupId
	 * @return
	 * @throws XueWenServiceException
	 */
	public Drycargo dryCargoAddUnParise(User user,String dryCargoId,String groupId)throws XueWenServiceException{
		//从小组里对干货进行点不赞
		Drycargo dry = findOneByDrycargoId(dryCargoId,groupId);
		if(dry==null){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_201,null);
		}
		String type=Config.TYPE_DRYCARGO_GROUP;
		if(dry.getDryFlag()==1){
			type = Config.TYPE_XUANYE_GROUP;
		}
		unPraiseService.addUnPraiseNotCheck(Config.YXTDOMAIN, "yxtapp", dryCargoId, type, user.getId());
		dry.setUnLikeCount(dry.getUnLikeCount()+1);
		return saveDrycargo(dry);//保存小组干货不赞数量
		
	}
	
	public Drycargo findOneById(String dryCargoId){
		return drycargoRepository.findOneById(dryCargoId);
	}
	
	/**
	 * 
	 * @auther tangli
	 * @Description: 带有群logo的干货详情
	 * @param id
	 * @return JSONObject
	 * @Date:2015年4月29日
	 * @throws
	 */
	public JSONObject getOneWithGroupInfo(String id){
		Drycargo drycargo= drycargoRepository.findOneById(id);
		Map<String,Object>map=new HashMap<String,Object>();
		if(drycargo!=null){
			XueWenGroup group=groupService.findById(drycargo.getGroup().toString());
			if(group!=null){
				map=new HashMap<String,Object>();
				map.put("groupLogoUrl", group.getLogoUrl());
				map.put("isHavaGroup", true);
				return YXTJSONHelper.addAndModifyAttrJsonObject(drycargo, map);
			}else{
				map.put("isHavaGroup", false);
				return JSONObject.fromObject(drycargo);
			}
		}else{
			return null;
		}
	}
	
	public Drycargo findOneByDrycargoId(String dryCargoId,String groupId){
		return drycargoRepository.findByIdAndGroup(dryCargoId,groupId);
	}
	/**
	 * 分享干货
	 * @param fromGroupId
	 * @param dryCargoId
	 * @param toGroupId
	 * @param currentUser
	 * @throws XueWenServiceException
	 */
	public void shareDryCargo(String fromGroupId, String dryCargoId,String toGroupId, User currentUser,String appkey,String toType,String toAddr) throws XueWenServiceException{
		this.shareDrycargoToOthers(fromGroupId, toGroupId,dryCargoId, currentUser,appkey,toType,toAddr);
	}

	
	public void shareDryCargos(String fromGroupId, String dryCargoId,String toGroupId, User currentUser,String appkey,String toType,String toAddr) throws XueWenServiceException{
		if (StringUtil.isBlank(fromGroupId) && !StringUtil.isBlank(toGroupId)) {
			// 1.其他位置->小组课堂
	//		this.shareDryToGroupClass(toGroupId, dryCargoId,currentUser,appkey,toType,toAddr);
		} else if (!StringUtil.isBlank(fromGroupId)) {
			// 2.小组课堂-->小组课堂 3.小组课堂-->其他位置
			this.shareDrycargoToOthers(fromGroupId, toGroupId,dryCargoId, currentUser,appkey,toType,toAddr);
		}
	}
	/**
	 * 小组课堂分享到其他地方
	 * @param fromGroupId
	 * @param toGroupId
	 * @param dryCargoId
	 * @param currentUser
	 */
	private void shareDrycargoToOthers(String fromGroupId, String toGroupId,String dryCargoId, User currentUser,String appkey,String toType,String toAddr) throws XueWenServiceException{
		Drycargo dry=drycargoRepository.findByIdAndGroup(dryCargoId, fromGroupId);
		if(dry==null){
			throw new XueWenServiceException(Config.STATUS_201,Config.MSG_201,null);
		}
		//将干货分享到目标小组课堂
		if(!StringUtil.isBlank(toGroupId)){
			shareDryToGroupClass(toGroupId, dry,currentUser,appkey,toType,toAddr);
		}else{
			String type=Config.TYPE_DRYCARGO_GROUP;
			if(dry.getDryFlag()==1){
				type = Config.TYPE_XUANYE_GROUP;
			}
			shareService.addShare(currentUser.getId(), Config.YXTDOMAIN, appkey, dry.getId(),type, toType, toAddr);
			dry.setShareCount(dry.getShareCount()+1);
			drycargoRepository.save(dry);
		}
		
	}

	/**
	 * 分享课程到小组课堂
	 * @param groupId
	 * @param courseId
	 * @throws XueWenServiceException
	 */
	public void shareDryToGroupClass(String groupId,Drycargo drycargo,User user,String appkey,String toType,String toAddr)throws XueWenServiceException{
		XueWenGroup group=groupService.findById(groupId);
		Drycargo dry=null;
		if(group!=null){
			dry=drycargoRepository.findByIdAndGroup(drycargo.getId(), group.getId());
		}
		long time=System.currentTimeMillis();
		if(dry==null&&group!=null){
			//此干货第一次分享
			dry=new Drycargo(group,user.getId());
			dry.setFileUrl(drycargo.getFileUrl());
			dry.setMessage(drycargo.getMessage());
			dry.setDescription(drycargo.getDescription());
			dry.setUrl(drycargo.getUrl());
			dry.setAuthorId(user.getId());
			dry.setAuthorLogoUrl(user.getLogoURL());
			dry.setAuthorName(user.getNickName());
			List<String> whoImport = new ArrayList<String>();
			whoImport.add(user.getId().toString());
			dry.setWhoImport(whoImport);
			dry.setImportCount(1);
			
		}else{
			//此干货已经被分享
			List<String> whoImport=dry.getWhoImport();
			if(whoImport.contains(user.getId())){
				//此干货已经被此人分享，则更新时间
				dry.setUtime(time);
			}else{
				//此干货未被此人分享，则加入导入者列表
				whoImport.add(user.getId());
				dry.setWhoImport(whoImport);
				dry.setImportCount(dry.getImportCount()+1);
				dry.setUtime(time);
				
			}
		}
		dry = drycargoRepository.save(dry);
		shareService.addShare(user.getId(), Config.YXTDOMAIN, appkey, dry.getId(), Config.TYPE_DRYCARGO_GROUP, toType, toAddr);
		
		
		
	}
	/**
	 * 
	 * @Title: shareDryCargosToGroup
	 * @Description: 批量分享群干货
	 * @param group
	 * @param drycargoIds
	 * @param user
	 * @param appkey
	 * @param toType
	 * @param toAddr
	 * @throws XueWenServiceException void
	 * @throws
	 */
	public void shareDryCargosToGroup(String group,String drycargoIds,User user,String appkey,String toType,String toAddr) throws XueWenServiceException{
		if(StringUtil.isBlank(drycargoIds)){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_201,null);
		}
		String[] ids=drycargoIds.split(",");
		for (String drycargoId : ids) {
		//	shareDryToGroupClass(group, drycargoId, user, appkey, toType, toAddr);
		}
	}
	/**
	 * 计算主楼回复数
	 * @param id  群干货id
	 */
	public void saveReplyCount(String id,int num) throws XueWenServiceException{
		drycargoTemplate.updatePostCount(id,num);
	}
	
	/**
	 * 查询干货详情(从小组课堂)
	 * @param user
	 * @param dryCargoId
	 * @return
	 * @throws XueWenServiceException
	 */
	public DrycargoResponse dryDetail(User user,String dryCargoId,String groupId) throws XueWenServiceException {
		Drycargo db =  drycargoRepository.findByIdAndGroup(dryCargoId, groupId);
		if(db==null){
			throw new XueWenServiceException(Config.STATUS_201,Config.MSG_201, null);
		}
		long time = System.currentTimeMillis();
		//增加浏览量
		db.setUtime(time);
		db.setViewCount(db.getViewCount()+1);
		DrycargoResponse  dbr = new DrycargoResponse(db);
		//设置赞标示
		int dryFlag = db.getDryFlag();
		String dryType =  Config.TYPE_DRYCARGO_GROUP;
		if(dryFlag==1)
		{
			dryType = Config.TYPE_XUANYE_GROUP;
		}
		dbr.setLike(praiseService.isUserPraise(user.getId(),Config.YXTDOMAIN, dryCargoId, dryType) ? true: false);
		//设置踩标示
		dbr.setUnlike(unPraiseService.isUserUnPraise(user.getId(),Config.YXTDOMAIN, dryCargoId, dryType) ? true: false);
		//设置收藏标示
		dbr.setIsFav(favService.isUserFav(user.getId(), Config.YXTDOMAIN, dryCargoId, dryType)? 1: 0);
		RestTemplate restTemplate=new RestTemplate();
		String tag = restTemplate.getForObject(tagServiceUrl+"tag/getTagsByIdAndType?domain="+Config.YXTDOMAIN+"&itemId="+db.getId()+"&itemType="+Config.TAG_TYPE_DRYCARGO, String.class);
		JSONObject objj=JSONObject.fromObject(tag);
		JSONObject obss=objj.getJSONObject("data");
		net.sf.json.JSONArray childs= obss.getJSONArray("result"); 
		dbr.setDrycargoTagName(childs);
		//判断该用户是否有回复权限（此人是否为该群）
		dbr.setAuthority(groupService.findMember(db.getGroup().toString(),user.getId())?true:false);
		//获得一级分类对象
//		if(db.getCategoryId()!=null){
//			dbr.setCategoryId(categoryService.formateCategory(categoryService.findOneCategoryById(db.getCategoryId())));
//		}
		//获得二级分类对象
		if(db.getChildCategoryId()!=null){
			dbr.setChildCategoryId(categoryService.formateCategory(categoryService.findOneCategoryById(db.getChildCategoryId())));
		}
		drycargoRepository.save(db);
		return dbr;
	}
	/**
	 * 收藏干货
	 * @param fromGroupId
	 * @param dryCargoId
	 * @param currentUser
	 */
	public void favDrycargo(String fromGroupId, String dryCargoId,User currentUser,String appkey,int dryFlag) throws XueWenServiceException {
			this.favCourse(fromGroupId, dryCargoId, currentUser,appkey,dryFlag);
	}
	
	/**
	 * 
	 * @Title: getCountsByGroupId
	 * @Description:获取群组下的干货
	 * @param groupId
	 * @return long
	 * @throws
	 */
	public long getCountsByGroupId(String groupId){
		return drycargoTemplate.getCountsByGroupId(groupId);
	}
	/**
	 * 收藏小组课堂课程到用户收藏列表
	 * @param groupCoursId
	 * @param user
	 * @throws XueWenServiceException
	 */
	public void favCourse(String fromGroupId,String dryCargoId,User user,String appkey,int dryFlag)throws XueWenServiceException{
		Drycargo db = drycargoRepository.findByIdAndGroup(dryCargoId, fromGroupId);
		if(db==null){
			throw new XueWenServiceException(Config.STATUS_201,Config.MSG_201,null);
		}else{
				//加入用户干货收藏列表
				userDrycargoService.addFavByDryFlag(db.getId(),user.getId(),fromGroupId,appkey,dryFlag);
		}
		
		//favService.addFavNotCheck(Config.YXTDOMAIN, "yxtapp", db.getId(),Config.TYPE_TOPIC_GROUP, user.getId());
	}
	
	/**
	 * 处理干货对象转换成客户端使用
	 * @param drycargo
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<DrycargoResponse> toResponeses(List<Drycargo> drycargo,String userId) throws XueWenServiceException{
		List<DrycargoResponse> uprs = new ArrayList<DrycargoResponse>();
		if(drycargo==null || drycargo.size()<=0){
		}else{
			for(int i = 0; i < drycargo.size(); i++){
				uprs.add(toResponse(drycargo.get(i),userId));
				//DrycargoResponse upr = new DrycargoResponse(drycargo.get(i));
				//uprs.add(upr);
			}
		}
		return uprs;
	}
	/**
	 * 处理干货对象转换成客户端使用
	 * @param Drycargo
	 * @return
	 * @throws XueWenServiceException
	 */
	public DrycargoResponse toResponse(Drycargo Drycargo,String userId)throws XueWenServiceException{
		if (Drycargo == null) {
			throw new XueWenServiceException(Config.STATUS_201,
					Config.MSG_NODATA_201, null);
		}
		DrycargoResponse upr = new DrycargoResponse(Drycargo);
		RestTemplate restTemplate=new RestTemplate();
		String tag = restTemplate.getForObject(tagServiceUrl+"tag/getTagsByIdAndType?domain="+Config.YXTDOMAIN+"&itemId="+Drycargo.getId()+"&itemType="+Config.TAG_TYPE_DRYCARGO, String.class);
		JSONObject objj=JSONObject.fromObject(tag);
		JSONObject obss=objj.getJSONObject("data");
		net.sf.json.JSONArray childs= obss.getJSONArray("result"); 
		upr.setDrycargoTagName(childs);
		return upr;
	}
	/**
	 * 获得某一用户创建的干货
	 * @param userId
	 * @param pageable
	 * @return
	 * @throws XueWenServiceException
	 */
	public Page<Drycargo> getUserCreateDrycargoByDryFlag(String userId,int dryFlag,Pageable pageable) throws XueWenServiceException{
		Page<Drycargo>  dryCargo = drycargoRepository.findByAuthorId(userId,pageable);
		return dryCargo;
	}
	
	/**
	 * 获得某一用户创建的干货 区分玄页
	 * @param userId
	 * @param pageable
	 * @return
	 * @throws XueWenServiceException
	 */
	public Page<Drycargo> getUserDrycargoByDryFlag(String userId,int dryFlag,Pageable pageable) throws XueWenServiceException{
		Page<Drycargo>  dryCargo = drycargoRepository.findByAuthorIdAndDryFlag(userId,dryFlag, pageable);
		return dryCargo;
	}
	/**
	 * 解散群得时候同时将该群下的干货删除
	 * @param groupId
	 * @throws XueWenServiceException 
	 */
	public void deleDryCargoByGroupId(String groupId) throws XueWenServiceException{
		drycargoTemplate.deleteByGroupId(groupId);
	}
	/**
	 * 查询某一群下得干货
	 * @param keywords
	 * @param pageable
	 * @return
	 */
	public Page<Drycargo> search(String keywords, Pageable pageable) throws XueWenServiceException{
		Page<Drycargo>  dryCargo = null;
		if(StringUtil.isBlank(keywords)){
			 dryCargo = drycargoRepository.findAll(pageable);
		}
		else{ 
			keywords =".*?(?i)"+keywords+".*";
			dryCargo = drycargoRepository.findByMessageRegex(keywords,pageable);
		}
		return dryCargo;
	}
	/**
	 * 获得某一用户创建的干货
	 * @param userId
	 * @param pageable
	 * @return
	 * @throws XueWenServiceException
	 */
	public Page<Drycargo> getUserCreateDrycargo(String userId,Pageable pageable) throws XueWenServiceException{
		Page<Drycargo>  dryCargo = drycargoRepository.findByAuthorId(userId,pageable);
		return dryCargo;
	}
	
	/**
	 * 计算收藏次数次数
	 * @param userId
	 * @param dryCargoId
	 * @throws XueWenServiceException
	 */
	public void countOperationFav(String userId, String dryCargoId,String appkey,int tranFlag)
			throws XueWenServiceException {
		Drycargo db = findOneById(dryCargoId);
		if (db == null) {
			throw new XueWenServiceException(Config.STATUS_201,
					Config.MSG_NODATA_201, null);
		} else {
				db.setFavCount(db.getFavCount() + 1);
				drycargoRepository.save(db);
				String type=Config.TYPE_DRYCARGO_GROUP;
				if(tranFlag==1){
					type = Config.TYPE_XUANYE_GROUP;
				}
				favService.addFavNotCheck(Config.YXTDOMAIN, appkey, db.getId(), type, userId);
		}
	}
	
	
	/**
	 * 
	 * @Title: searchByKeyWords
	 * @Description: 炫页搜索
	 * @param keywords
	 * @param pageable
	 * @return
	 * @throws XueWenServiceException Page<DrycargoBean>
	 * @throws
	 */
	public Page<Drycargo>  searchByKeyWordsAndTagNamesLikeForXuanye(String keywords,Pageable pageable) throws XueWenServiceException{
		if(StringUtil.isBlank(keywords)){
			return drycargoRepository.findByDryFlagAndGroupNotNull(1,pageable);
		}     
		else{
			return drycargoRepository.findByOthers(1,keywords, pageable);
		}
	}
	
	/**
	 * 
	 * @Title: searchByKeyWords
	 * @Description: 干货搜索
	 * @param keywords
	 * @param pageable
	 * @return
	 * @throws XueWenServiceException Page<DrycargoBean>
	 * @throws
	 */
	public Page<Drycargo>  searchByKeyWordsAndTagNamesLike(String keywords,Pageable pageable) throws XueWenServiceException{
		if(StringUtil.isBlank(keywords)){
			//return drycargoRepository.findByDryFlagAndGroupNotNull(0,pageable);
			List<Object> l=new ArrayList<Object>();
			l.add("");
			return drycargoRepository.findByDryFlagAndGroupNotIn(0,l,pageable);
			
		}     
		else{
			return drycargoRepository.findByOthers(0,keywords, pageable);
		}
	}
	
	/**
	 * 
	 * @Title: searchByKeyWords
	 * @Description: 干货搜索
	 * @param keywords
	 * @param pageable
	 * @return
	 * @throws XueWenServiceException Page<DrycargoBean>
	 * @throws
	 */
	public Page<Drycargo>  searchCatchByKeyWordsAndTagNamesLike(String keywords,Pageable pageable) throws XueWenServiceException{
		if(StringUtil.isBlank(keywords)){
			//return drycargoRepository.findByDryFlagAndGroupNotNull(0,pageable);
			List<Object> l=new ArrayList<Object>();
			l.add("");
			return drycargoRepository.findByDryFlagAndGroupIn(0,l,pageable);
			
		}     
		else{
			return drycargoRepository.findByOthers(0,keywords, pageable);
		}
	}
	
	
	/**
	 * 
	 * @Title: searchByKeyWords
	 * @Description: 干货搜索未审核列表
	 * @param keywords
	 * @param pageable
	 * @return
	 * @throws XueWenServiceException Page<DrycargoBean>
	 * @throws
	 */
	public Page<Drycargo>  searchByKeyWordsAndTagNamesLikeAndNoCheck(String keywords,Pageable pageable) throws XueWenServiceException{
		if(StringUtil.isBlank(keywords)){
			return drycargoRepository.findByDryFlagAndReview(0, false, pageable);
		}     
		else{
			return drycargoRepository.findByOthers(0,keywords,false, pageable);
		}
	}
	
	
	/**
	 * 
	 * @Title: searchByKeyWords
	 * @Description: 炫页搜索未审核列表
	 * @param keywords
	 * @param pageable
	 * @return
	 * @throws XueWenServiceException Page<DrycargoBean>
	 * @throws
	 */
	public Page<Drycargo>  searchByKeyWordsAndTagNamesLikeAndNoCheckXuanYe(String keywords,Pageable pageable) throws XueWenServiceException{
		if(StringUtil.isBlank(keywords)){
			return drycargoRepository.findByDryFlagAndReview(1, false, pageable);
		}     
		else{
			return drycargoRepository.findByOthers(1,keywords,false, pageable);
		}
	}
	
	
	/**
	 * 
	 * @Title: searchByKeyWordsAndTagNamesLikeInGroup
	 * @Description: 干货搜索(群组里)
	 * @param keywords
	 * @param pageable
	 * @return
	 * @throws XueWenServiceException Page<DrycargoBean>
	 * @throws
	 */
	public Page<Drycargo>  searchByKeyWordsAndTagNamesLikeInGroupInGroup(String groupId,String keywords,Pageable pageable) throws XueWenServiceException{
		if(StringUtil.isBlank(keywords)){
			return drycargoRepository.findByGroupAndDryFlag(groupId,0,pageable);
		}     
		else{
			return drycargoRepository.findByOthers(0, keywords, groupId, pageable);
			
		}
	}
	
	/**
	 * 
	 * @Title: getTop10
	 * @Description: 获取全站top10 干货
	 * @return List<JSONObject>
	 * @throws
	 */
	public List<JSONObject>  getTop10(Integer s){
		QueryModel qm=new QueryModel();
		qm.setN(0);
		if(s==null){
			s=10;
		}
		qm.setS(s);
		qm.setSort("viewCount");
		qm.setMode("DESC");
		long t1=System.currentTimeMillis();
		long t2=t1-7*24*60*60*1000;
		Pageable pageable=PageRequestTools.pageRequesMake(qm);
		Page<Drycargo> drycargoBeans=drycargoRepository.findByCtimeBetween(t2,t1,pageable);
		List<JSONObject> drys=new ArrayList<JSONObject>();
		for(Drycargo dry:drycargoBeans){
			JSONObject object=YXTJSONHelper.getInObjectAttrJsonObject(dry,new HashMap<String, Object>(), new String[]{"id","group","groupName","fileUrl","description","message","viewCount","url","likesCount","isOriginal"})	;
			drys.add(object);
		}
		if(drys.size()==0){
			return null;
		}
		return drys;
		
	}
	
	/**
	 * 根据干货ID删除所有的干货
	 * @param drycargoId
	 * @throws XueWenServiceException
	 */
	public boolean deleteById(String drycargoId)throws XueWenServiceException{
		try {
			//删除所有的赞记录
			praiseService.deleteBySourceId(drycargoId);
			//删除所有的不攒接口
			unPraiseService.deleteBySourceId(drycargoId);
			//删除所有的分享记录
			shareService.deleteBySourceId(drycargoId);
			//删除所有的分享记录
			favService.deleteBySourceId(drycargoId);
			//删除所有的分享记录
			userDrycargoService.deleFav(drycargoId);
			//删除所有的评论(包括主楼评论，副楼评论)
			postService.deleteByTopicId(drycargoId);
			//删除干货记录
			drycargoTemplate.deleteByDrycargoId(drycargoId);
			//删除排行榜记录
			boxTemplate.deleteBysourceId(drycargoId);
			//删除干货动态
			groupDynamicTemplate.deleteBySourceIdId(drycargoId);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 根据干货IDs删除所有的干货
	 * @param drycargoId
	 * @throws XueWenServiceException
	 */
	public boolean deleteByIds(String drycargoIds)throws XueWenServiceException{
		String[] ids;
		if(!StringUtil.isBlank(drycargoIds)){
			ids=drycargoIds.split(",");
		}else{
			throw new XueWenServiceException(Config.STATUS_201, "请选择需要删除的干货", null);
		}
		try {
			List<Object> idList=new ArrayList<Object>();
			for (String id : ids) {
				idList.add(id);
				//删除所有的评论(包括主楼评论，副楼评论)
				postService.deleteByTopicId(id);
				//删除干货动态
				groupDynamicTemplate.deleteBySourceIdId(id);
			}
			//删除所有的赞记录
			praiseService.deleteBySourceIds(idList);
			//删除所有的不攒接口
			unPraiseService.deleteBySourceIds(idList);
			//删除所有的分享记录
			shareService.deleteBySourceIds(idList);
			//删除所有的分享记录
			favService.deleteBySourceIds(idList);
			//删除干货记录
			drycargoTemplate.deleteByDrycargoIds(idList);
			
			
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 通过群组ID查询该群下的干货
	 * @param groupId
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<Drycargo> getDryCargos(String groupId) throws XueWenServiceException{
		return  drycargoRepository.findByGroup( groupId);
	}
	
	/**
	 * 分页获取不在此位置的干货列表
	 * @param boxPostId
	 * @return
	 * @throws XueWenServiceException
	 */
	public Page<Drycargo> findByBoxPostId(int dryFlag,String boxPostId,Pageable pageable)throws XueWenServiceException{
		List<Object> ids=boxService.getSourceIdsByBoxPostId(boxPostId);
		return drycargoRepository.findByDryFlagAndReviewAndIdNotIn(dryFlag,true,ids,pageable);
	}
	
	
	/**
	 * 分页获取不在此位置的干货列表
	 * @param boxPostId
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<Drycargo> findBychildCategoryId(int dryFlag,String id)throws XueWenServiceException{
		 
		return drycargoRepository.findByDryFlagAndChildCategoryId(dryFlag,id);
	}
	
	
	/**
	 * 查询不在推荐位置里的干活信息
	 * @param boxPostId
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<Drycargo> findByNotInBoxPostId(int dryFlag,String boxPostId,String keywords)throws XueWenServiceException{
		List<Object> ids=boxService.getSourceIdsByBoxPostId(boxPostId);
		keywords =".*?(?i)"+keywords+".*";
		return drycargoRepository.findByDryFlagAndMessageRegexAndIdNotIn(dryFlag,keywords,ids);
	}

	
	/**
	 * 通过分类查询干货列表
	 * @param userId
	 * @param pageable
	 * @return
	 * @throws XueWenServiceException
	 */
	public Page<Drycargo> findAllByCategory(int dryFlag,String categoryId,String childCategoryId,Pageable pageable)throws XueWenServiceException {
		Page<Drycargo>  drycargo = null;
		if(!StringUtil.isBlank(childCategoryId)){
			drycargo = drycargoRepository.findByChildCategoryId(childCategoryId,pageable);
		}else{
			 drycargo = drycargoRepository.findByCategoryId(categoryId,pageable);
		}
		  
		return drycargo;
	}
	
	/**
	 * 通过一级分类Id计算该分类下的主题数量
	 * 
	 * @param p
	 * @return
	 * @throws XueWenServiceException
	 */
	public int countByCategoryId(int dryFlag,String categoryId)throws XueWenServiceException {
		return drycargoRepository.countByCategoryId(categoryId);
	}
	
	/**
	 * 通过二级分类Id计算该分类下的主题数量
	 * 
	 * @param p
	 * @return
	 * @throws XueWenServiceException
	 */
	public int countByChildCategoryId(int dryFlag,String childCategoryId)throws XueWenServiceException {
		return drycargoRepository.countByChildCategoryId(childCategoryId);
	}
	
	/**
	 * 将没有分类的干货填充分类
	 * @throws XueWenServiceException
	 */
	public void addCategoryForDrycargo()throws XueWenServiceException {
		List<Drycargo> drycargoList = drycargoRepository.findAll();
		Drycargo drycargo = null;
		XueWenGroup group = null;
		if(drycargoList!=null && drycargoList.size() > 0){
			for(int i = 0 ; i < drycargoList.size(); i++){
				drycargo = drycargoList.get(i);
				group =  groupService.findById(drycargo.getGroup().toString());
				if(group==null){
					continue;
				}
				//if(StringUtil.isBlank(drycargo.getCategoryId())){
				drycargo.setCategoryId(group.getCategoryId().toString());
				drycargo.setChildCategoryId(group.getChildCategoryId().toString());
				drycargoRepository.save(drycargo);
				//}
			}
		}
	}
	
	/**
	 * 格式化干货盒子，OSS使用
	 * @param categorys
	 * @param type
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<Object> formateDrycatgoList(List<Box> boxs)throws XueWenServiceException{
		if(boxs == null ){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
		List<Object> objs=new ArrayList<Object>();
		for(Box box:boxs){
			objs.add(formateOssDryCargoBox(box));
		}
		return objs;
	}
	
	/**
	 * 格式化干活类的盒子，OSS 使用
	 * @param category
	 * @return
	 * @throws XueWenServiceException
	 */
	public Object formateOssDryCargoBox(Box box)throws XueWenServiceException{
		if(box !=null ){
				//根据type值添加相应的统计数据
				String dryCargoId = box.getSourceId().toString();
				Drycargo drycargo = drycargoRepository.findOne(dryCargoId);
				box.setSourceId(drycargo);
				//去掉无需返回前端的属性,只包含以下属性
				String[] include = {"sourceId","id","weightSort"};
				return  YXTJSONHelper.includeAttrJsonObject(box, include);
		}else{
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
	}
	/**
	 * app首页，从盒子中获取干货，然后格式化
	 * @param boxs
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<Object> formateIndexDrycatgoBox(List<Box> boxs)throws XueWenServiceException{
		if(boxs == null ){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
		List<Object> objs=new ArrayList<Object>();
		for(Box box:boxs){
			Object obj=formateIndexDryCargo(box);
			if(obj !=null){
				objs.add(formateIndexDryCargo(box));
			}
			
		}
		return objs;
	}
	/**
	 * 格式化单个盒子的对象
	 * @param box
	 * @return
	 * @throws XueWenServiceException
	 */
	public Object formateIndexDryCargo(Box box)throws XueWenServiceException{
		if(box !=null ){
			//根据type值添加相应的统计数据
			String dryCargoId = box.getSourceId().toString();
			Drycargo drycargo = drycargoRepository.findOne(dryCargoId);
			if(drycargo !=null){
				return  toResponse(drycargo,"");
			}else{
				return null;
			}
			
		}else{
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_PROPERTIESERROR_201,null);
		}
	}
	
	/**
	 * 
	 * @Title: getDryindex
	 * @Description: 取干货频道数据
	 * @param boxPostName
	 * @return
	 * @throws XueWenServiceException List<JSONObject>
	 * @throws
	 */
	public void getDryindex(String boxPostName,Pageable pageable,ReponseData date) throws XueWenServiceException {
	   if(StringUtil.isBlank(boxPostName)){
		   throw new XueWenServiceException(Config.STATUS_201, "名称不能为空", null);
	   }
	   BoxPost boxPosts=boxPostService.findByName(boxPostName);
	   if(boxPosts!=null){
	   List<Object> ids= boxService.getSourceIdsByBoxPostId(boxPosts.getId());
	   Page<Drycargo>drys= drycargoRepository.findByIdIn(ids,pageable);
	   List<JSONObject> objects=new ArrayList<JSONObject>();
	   for (Drycargo drycargo : drys) {
		  XueWenGroup group=groupService.findById((String)drycargo.getGroup());
		  if(group!=null){
			  Map<String, Object>map=new HashMap<String, Object>();
			  map.put("groupName", group.getGroupName());
			  objects.add(YXTJSONHelper.addAndModifyAttrJsonObject(drycargo, map));
		  }
	   }
	   ReponseDataTools.getClientReponseData(date, drys);
	   date.setResult(objects);
	}
	}
	
	/**
	 * 
	 * @Title: findByIdIn
	 * @auther Tangli
	 * @Description: 通过id批量取
	 * @param ids
	 * @return List<Drycargo>
	 * @throws
	 */
	public List<Drycargo> findByIdIn(List<Object> ids) {
		return drycargoRepository.findByIdIn(ids);
	}
	
	/**
	 * 排行榜-查询审核通过的干货或炫页
	 * @param pageable
	 * @return
	 * @throws XueWenServiceException
	 */
	public Page<Drycargo> ringDryCargo(int dryFlag, Pageable pageable)throws XueWenServiceException {
		return drycargoRepository.findByDryFlagAndReview(dryFlag, true, pageable);
	}
	
	/**
	 * 将drycargo转成JSON
	 * 
	 * @param subs
	 * @param userId
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<Object> toJSONHelper(List<Drycargo> drycargo)throws XueWenServiceException {
		List<Object> topicRes = new ArrayList<Object>();
		if (drycargo == null || drycargo.size() <= 0) {
		} else {
			for (int i = 0; i < drycargo.size(); i++) {
				Map<String,Object> addAndModifyMap=new HashMap<String, Object>();
				String[] exclude = {"weightSort","importCount","categoryId","childCategoryId"};
				topicRes.add(YXTJSONHelper.getExObjectAttrJsonObject(toResponse(drycargo.get(i),""), addAndModifyMap, exclude));
			}
		}
		return topicRes;
	}
	
	
	
	/**
	 * 
	 * @Title: findByIdIn
	 * @auther Tangli
	 * @Description: 通过id批量取
	 * @param ids
	 * @return List<Drycargo>
	 * @throws
	 */
	public Page<Drycargo> findByIdIn(List<Object> ids,Pageable pageable) {
		return drycargoRepository.findByIdIn(ids,pageable);
	}
	
	/**
	 * 根据位置中的话题列表返回话题列表
	 * @param boxs
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<Object> toBoxResponses(List<Box> boxs)throws XueWenServiceException {
		List<Object> drycargoRes = new ArrayList<Object>();
		List<Object> dryCargoIds = new ArrayList<Object>();
		if(boxs !=null && boxs.size()>0){
			for(Box box:boxs){
				dryCargoIds.add(box.getSourceId().toString());
			}
			List<Drycargo> dryCargos =  drycargoRepository.findByIdIn(dryCargoIds);
			for(int i=0 ;i < dryCargos.size(); i++){
					Map<String,Object> addAndModifyMap=new HashMap<String, Object>();
					String[] exclude = {"weightSort","importCount","categoryId","childCategoryId"};
					drycargoRes.add(YXTJSONHelper.getExObjectAttrJsonObject(toResponse(dryCargos.get(i),""), addAndModifyMap, exclude));
				}
			}
		return drycargoRes;
	}

   
	/**
	 * 
	 * @Title: countByCuser
	 * @auther Tangli
	 * @Description:用户干货数量
	 * @param userId
	 * @return int
	 * @throws
	 */
	public int countByCuser(String userId) {
		return  drycargoRepository.countByAuthorId(userId);
	}


	/**
	 * 修改干货创建人，从fromUser 到  toUser
	 * @param fromUserId
	 * @param toUserId
	 * @param toUserNickName
	 * @param toUserLogoUrl
	 * @throws XueWenServiceException
	 */
	public void mergeDrycargo(String fromUserId,String toUserId,String toUserNickName,String toUserLogoUrl)throws XueWenServiceException{
		drycargoTemplate.mergeDrycargo(fromUserId, toUserId, toUserNickName, toUserLogoUrl);
	}
	/**
	 * 创建群组干货或炫页动态
	 * @param drycargo
	 * @throws XueWenServiceException
	 */
	public void createGroupDrycargoDynamic(Drycargo drycargo)throws XueWenServiceException{
		List<Images> images=creatImagesList(drycargo);
		String type="";
		if(drycargo.getDryFlag() == 1){
			type=Config.TYPE_XUANYE_GROUP;
		}else{
			type=Config.TYPE_DRYCARGO_GROUP;
		}
		groupDynamicService.addGroupDynamic(drycargo.getGroup().toString(),"","", drycargo.getId(), drycargo.getMessage(),
				drycargo.getDescription(), "", images,drycargo.getAuthorId() , drycargo.getAuthorName(),
				drycargo.getAuthorLogoUrl(), type, drycargo.getCtime());
		
	}
	/**
	 * 创建关键图片集合
	 * @param drycargo
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<Images> creatImagesList(Drycargo drycargo)throws XueWenServiceException{
		List<Images> images=new ArrayList<Images>();
		if(drycargo !=null && !StringUtil.isBlank(drycargo.getFileUrl())){
			Images image=new Images();
			image.setPicUrl(drycargo.getFileUrl());
			image.setPicHeight(drycargo.getPicHeight());
			image.setPicWidth(drycargo.getPicWidth());
			images.add(image);
			return images;
		}else{
			return null;
		}
		
	}
	/**
	 * 老数据生成群组干货动态
	 * @throws XueWenServiceException
	 */
//	public void creatOldGroupDrycargoDynamic()throws XueWenServiceException{
//		List<Drycargo> drycargos=findAll();
//		for(Drycargo drycargo:drycargos){
//			createGroupDrycargoDynamic(drycargo);
//		}
//	}
	/**
	 * 获得我的干货收藏列表
	 * @param userId
	 * @param appkey
	 * @param type
	 * @param pageable
	 * @return
	 * @throws XueWenServiceException
	 */
	public Page<Fav> getMyCollect(String userId,int dryFlag,Pageable pageable)throws XueWenServiceException{
//		String type = Config.TYPE_DRYCARGO_GROUP;
//		if(1==dryFlag){
//			type = Config.TYPE_XUANYE_GROUP;
//		}
		return favService.findListByUserIdAndAppkeyAndType(userId,"yxtapp",pageable);
	}
	/**
	 * 处理干货对象转换成客户端使用
	 * @param drycargo
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<DrycargoResponse> toDrycargoForFav(List<Fav> fav,String userId) throws XueWenServiceException{
		List<DrycargoResponse> uprs = new ArrayList<DrycargoResponse>();
		if(fav==null || fav.size()<=0){
		}else{
			for(int i = 0; i < fav.size(); i++){
				uprs.add(toResponse(drycargoRepository.findOne(fav.get(i).getSourceId()),userId));
			}
		}
		return uprs;
	}
	/**
	 * 处理干货对象转换成客户端使用
	 * @param drycargo
	 * @return
	 * @throws XueWenServiceException
	 */
	public Map<String, Object> toNewDrycargoForFav(List<Fav> fav,String userId) throws XueWenServiceException{
		Map<String, Object> res = new HashMap<String, Object>();
		//Page<Fav> favs = favService.findByUserId(userId, pageable);
		List<JSONObject> jObjects = new ArrayList<JSONObject>();
		for(int i = 0 ; i < fav.size(); i++){
			Map<String, Object> map = new HashMap<String, Object>();
			Drycargo drycargo = this.findOneById(fav.get(i).getSourceId());
			if (drycargo != null) {
				map.put("itemId", drycargo.getId());
				map.put("title", drycargo.getMessage());
				map.put("intro", drycargo.getDescription());
				map.put("groupId",drycargo.getGroup());
				map.put("groupName", drycargo.getGroupName());
				map.put("praiseCount", drycargo.getLikesCount());
				map.put("logoURL",drycargo.getAuthorLogoUrl());
				map.put("uId",drycargo.getAuthorId());
				map.put("coverLogoUrl", drycargo.getFileUrl());
				map.put("searchType",Config.TYPE_DRYCARGO_GROUP);
				//XueWenGroup group = groupTemplate.findOneXuewenGroupRspGroupNameAndMemberAndLogoUrl(drycargo.getGroup().toString());
				if(!StringUtil.isBlank(drycargo.getGroupLogoUrl())){
					map.put("groupLogoUrl", drycargo.getGroupLogoUrl());
				}else{
					map.put("groupLogoUrl", "");
				}
				map.put("ctime", fav.get(i).getCtime());//增加收藏时间
				map.put("favCount", drycargo.getFavCount());//增加收藏数量
				map.put("url", drycargo.getUrl());//增加干货外链地址
				map.put("picHeight", drycargo.getPicHeight());//增加图片高度
				map.put("picWidth", drycargo.getPicWidth());//增加图片宽度
				
			}
			
			jObjects.add(YXTJSONHelper.addAndModifyAttrJsonObject(fav.get(i), map));
		}
		//res.put("page", favs);
		res.put("items", jObjects);
		return res;
	}
	
	
	
	/**
	 * 
	 * @Title: addfavCount
	 * @auther Tangli
	 * @Description: 更新收藏数量
	 * @param id
	 * @param inc 要变更的数量 1：加1 -1：减1
	 * @throws
	 */
	public void addfavCount(String id,int inc) {
		drycargoTemplate.updateFavCount(id,inc);
	}
	/**
	 * OSS创建干货
	 * @param user
	 * @param drycargoPc
	 * @param tagName
	 * @param drycargoId
	 * @return
	 * @throws XueWenServiceException
	 */
	public Drycargo uploadDryOss(User user,Drycargo drycargo,String tagName,String isOriginal,String image) throws XueWenServiceException {
		long time = System.currentTimeMillis();
		//自动推荐标签
		if(StringUtil.isBlank(tagName)){
			tagName=localTagService.getTagNamesByAnalysis(drycargo.getMessage()+","+drycargo.getDescription());
		}
		try {
			drycargo.setUrl(URLDecoder.decode(drycargo.getUrl(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		try {
			drycargo.setFileUrl(URLDecoder.decode(drycargo.getFileUrl(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<Drycargo> dry = getDryByUrl(drycargo.getUrl());
		if(dry!=null && dry.size() > 0){
			throw new XueWenServiceException(Config.STATUS_201, Config.MSG_DRYCAROGEXIT_201,null);
		}else{
			drycargo.setAuthorId(user.getId());
			drycargo.setAuthorLogoUrl(user.getLogoURL());
			drycargo.setAuthorName(user.getNickName());
			drycargo.setCtime(time);
			drycargo.setDrycargoTagName(tagName);
			if(StringUtil.isBlank(isOriginal)){
				drycargo.setIsOriginal(0);
			}
			logger.info(image+"调整支持多张图片（老版本也保持支持1张图片）");
			drycargo = changePicUrlToImage(drycargo,image,drycargo.getFileUrl(),String.valueOf(drycargo.getIsOriginal()));
			if(tagName!=null){
				RestTemplate restTemplate=new RestTemplate();
				restTemplate.postForObject(tagServiceUrl+"tag/createTagBatch?domain="+"yxt"+"&itemId="+drycargo.getId()+"&userId="+user.getId()+"&userName="+user.getNickName()+"&itemType="+Config.TAG_TYPE_DRYCARGO+"&tagNames="+tagName, null,String.class);
			}
			drycargo =  drycargoRepository.save(drycargo);
			// 添加消息队列
			try {
				rabbitService.sendRegexMessage(drycargo.getId(),
						Config.TAG_TYPE_DRYCARGO);
				//创建群组干货动态
				createGroupDrycargoDynamic(drycargo);
			} catch (Exception e) {
				logger.error("=============发送消息队列发送错误===" + drycargo.getId()
						+ "====类别：" + Config.TAG_TYPE_DRYCARGO);
				e.printStackTrace();
			}
			return drycargo;
		}
		
	}
	
	/**
	 * 干货置顶
	 * @param topicId
	 * @param currentUser
	 * @return
	 */
	public DrycargoResponse display(String drycargoId, User currentUser,String disType) throws XueWenServiceException{
		Drycargo drycargo = findOneById(drycargoId);//获得干货对象
		//判断该群已经置顶的干货数量如果大于3则不允许置顶
		if(Config.DISPALY == Integer.parseInt(disType)){ //如果是置顶
			int displayCount = this.doDisplayCount(drycargo);
			if(displayCount>=Config.DISPLAYCOUNT){
				throw new XueWenServiceException(Config.STATUS_201, Config.MSG_DISPALYCOUNT_201,null);
			}
		}
		boolean admin = groupService.isGroupAdmin(drycargo.getGroup().toString(), currentUser.getId());//判断该用户是否为该群的管理员
		boolean owner = groupService.isGroupOwner(currentUser.getId(), drycargo.getGroup().toString());//判断该用户是否为该群的创建员
		if(Config.DISPALY == Integer.parseInt(disType)){//如果是置顶
			if(admin==false && owner==false){
				throw new XueWenServiceException(Config.STATUS_201, Config.MSG_DISPALY_201,null);
			}
		}else{
			if(admin==false && owner==false){
				throw new XueWenServiceException(Config.STATUS_201, Config.MSG_NODISPALY_201,null);
			}
		}
		if(Config.DISPALY == Integer.parseInt(disType)){ //如果是置顶
			drycargo.setDisplayOrder(Config.DISPALY);
			drycargo.setDisplayTime(System.currentTimeMillis());
		}
		else{
			drycargo.setDisplayOrder(Config.NODISPLAY);
			drycargo.setDisplayTime(0);
		}
		
		drycargoRepository.save(drycargo);
		DrycargoResponse tr = new DrycargoResponse(drycargo);
		return tr;
	}
	
	/**
	 * 计算某一群下的干货置顶数量
	 * @param topic
	 * @return
	 * @throws XueWenServiceException
	 */
	public int doDisplayCount(Drycargo drycargo)throws XueWenServiceException {
		return drycargoRepository.countByDisplayOrderAndGroup(Config.DISPALY, drycargo.getGroup());
	}

	public Page<Drycargo> findByBoxPostIdNotInBoxAndNotInCategory(String boxPostId, String type, String category, Pageable pageable) throws XueWenServiceException {
		List<Object> ids=boxService.getSourceIdsByBoxPostIdAndNotInCagetory(boxPostId,type);
		return drycargoRepository.findByIdNotIn(ids, pageable);
	}
	
	/**
	 * 处理话题创建图片（为了支持低版本）
	 * @param image（1.2新版本创建）
	 * @param picUrl（1.1旧版本创建）
	 * @return
	 * @throws XueWenServiceException
	 */
	public Drycargo changePicUrlToImage(Drycargo drycargo,String image,String fileUrl,String isOriginal)  throws XueWenServiceException{
		//如果image不为空，代表新版本创建
		//if(!StringUtil.isBlank(image)){
//		if(!StringUtil.isBlank(isOriginal) && "1".equals(isOriginal) && !StringUtil.isBlank(image)){//如果是原生的
//			List<Images> imageList = JSON2ObjUtil.getDTOList(image, Images.class);
//			Images images = null;
//			if(imageList!=null && imageList.size() > 0){
//				drycargo.setImages(imageList);
//					images = imageList.get(0);
//					drycargo.setFileUrl(fileUrl);
//					drycargo.setPicWidth(images.getPicWidth());
//					drycargo.setPicHeight(images.getPicHeight());
//			}
//			return drycargo;
//		}
		//如果picUrl不是空 则代表从旧版本创建
		if(StringUtil.isBlank(isOriginal)){
			Images images = new Images();
			images.setPicUrl(fileUrl);
			images.setPicHeight(drycargo.getPicHeight());
			images.setPicWidth(drycargo.getPicWidth());
			List<Images> imageList  = new ArrayList<Images>();
			imageList.add(images);
			drycargo.setImages(imageList);
			return drycargo;
		}
		else{
			if(!StringUtil.isBlank(image)){
			List<Images> imageList = JSON2ObjUtil.getDTOList(image, Images.class);
			if(imageList!=null && imageList.size() > 0){
				drycargo.setImages(imageList);
			}
			return drycargo;
			}
		}
		return drycargo;
		
	}
		
		
}	


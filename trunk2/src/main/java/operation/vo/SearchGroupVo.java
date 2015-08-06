//package operation.vo;
//import operation.pojo.course.Knowledge;
//import operation.pojo.course.NewCourse;
//import operation.pojo.drycargo.Drycargo;
//import operation.pojo.group.XueWenGroup;
//import operation.pojo.topics.Topic;
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.index.Indexed;
//
///**
// * 
// * @ClassName: SearchGroupVo
// * @Description: 用于搜索界面显示的Bean
// * @author Jack Tang
// * @date 2015年1月14日 下午4:32:43
// *
// */
//public class SearchGroupVo {
//	@Id
//	private String id;
//	@Indexed
//	private String groupId;//群id
//	private String groupName;//群名称
//	private String groupLogoUrl;//群logoUrl
//	private String groupIntro;//群logoUrl
//	private int groupMemberNumber;//群成员数
//	private String groupTag;
//	private int topicNumber;//群话题数量
//	private String TopicId;//群最新话题Id
//	private String TopicTitle;//群最新话题标题
//	private int newCourseNumber;//群课程数量
//	private String newCourseId;//群最新课程id
//	private String newCourseTitle;//群最新课程标题
//	private int dryCargoNumber;//群干货数量
//	private String dryCargoId;//群最新干货id
//	private String dryCargoMessage;//群干货信息
//	private int kngNumber;//群分享数量
//	private String kngId;//群最新分享Id
//	private String kngTitle;//群最新分享标题
//
//	/**
//	 * 
//	 * Title: Description:
//	 * 
//	 * @param group
//	 * @param topic
//	 * @param course
//	 * @param drycargoBean
//	 * @param kng
//	 */
//	public SearchGroupVo(XueWenGroup group) {
//		this.groupId=group.getId();
//		this.groupName=group.getGroupName();
//		this.groupLogoUrl=group.getLogoUrl();
//		this.groupMemberNumber=group.getMember().size();	
//		this.groupIntro = group.getIntro();
//		String string="";
//	    string=group.getTagNames();	
//		this.groupTag=string;
//	}
//	
//	public SearchGroupVo() {
//		
//		
//	}
//
//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
//
//	public String getGroupId() {
//		return groupId;
//	}
//
//	public void setGroupId(String groupId) {
//		this.groupId = groupId;
//	}
//
//	public String getGroupName() {
//		return groupName;
//	}
//
//	public void setGroupName(String groupName) {
//		this.groupName = groupName;
//	}
//
//	public String getGroupLogoUrl() {
//		return groupLogoUrl;
//	}
//
//	public void setGroupLogoUrl(String groupLogoUrl) {
//		this.groupLogoUrl = groupLogoUrl;
//	}
//
//	public int getGroupMemberNumber() {
//		return groupMemberNumber;
//	}
//
//	public void setGroupMemberNumber(int groupMemberNumber) {
//		this.groupMemberNumber = groupMemberNumber;
//	}
//
//	public int getTopicNumber() {
//		return topicNumber;
//	}
//
//	public void setTopicNumber(int topicNumber) {
//		this.topicNumber = topicNumber;
//	}
//
//	public String getTopicId() {
//		return TopicId;
//	}
//
//	public void setTopicId(String topicId) {
//		TopicId = topicId;
//	}
//
//	public String getTopicTitle() {
//		return TopicTitle;
//	}
//
//	public void setTopicTitle(String topicTitle) {
//		TopicTitle = topicTitle;
//	}
//
//	public int getNewCourseNumber() {
//		return newCourseNumber;
//	}
//
//	public void setNewCourseNumber(int newCourseNumber) {
//		this.newCourseNumber = newCourseNumber;
//	}
//
//	public String getNewCourseId() {
//		return newCourseId;
//	}
//
//	public void setNewCourseId(String newCourseId) {
//		this.newCourseId = newCourseId;
//	}
//
//	public String getNewCourseTitle() {
//		return newCourseTitle;
//	}
//
//	public void setNewCourseTitle(String newCourseTitle) {
//		this.newCourseTitle = newCourseTitle;
//	}
//
//	public int getDryCargoNumber() {
//		return dryCargoNumber;
//	}
//
//	public void setDryCargoNumber(int dryCargoNumber) {
//		this.dryCargoNumber = dryCargoNumber;
//	}
//
//	public String getDryCargoId() {
//		return dryCargoId;
//	}
//
//	public void setDryCargoId(String dryCargoId) {
//		this.dryCargoId = dryCargoId;
//	}
//
//	public String getDryCargoMessage() {
//		return dryCargoMessage;
//	}
//
//	public void setDryCargoMessage(String dryCargoMessage) {
//		this.dryCargoMessage = dryCargoMessage;
//	}
//
//	public int getKngNumber() {
//		return kngNumber;
//	}
//
//	public void setKngNumber(int kngNumber) {
//		this.kngNumber = kngNumber;
//	}
//
//	public String getKngId() {
//		return kngId;
//	}
//
//	public void setKngId(String kngId) {
//		this.kngId = kngId;
//	}
//
//	public String getKngTitle() {
//		return kngTitle;
//	}
//
//	public void setKngTitle(String kngTitle) {
//		this.kngTitle = kngTitle;
//	}
//	
//	
//	public void updateTopicInf(Topic topic){
//		this.topicNumber++;
//		this.TopicId=topic.getTopicId();
//		this.TopicTitle=topic.getTitle();
//		
//	}
//	
//	public void updateCourseInf(NewCourse course){
//		this.newCourseNumber++;
//		this.newCourseId=course.getId();
//		this.newCourseTitle=course.getTitle();
//		
//	}
//	
//	public void updateKngInf(Knowledge knowledge){
//		this.kngNumber++;
//		this.kngId=knowledge.getId();
//		this.kngTitle=knowledge.getName();
//	}
//	
//	public void updateDryInf(Drycargo db){
//		this.dryCargoNumber++;
//		this.dryCargoId=db.getId();
//		this.dryCargoMessage=db.getMessage();
//	}
//	
//	public void updateByGroup(XueWenGroup group,String tags){
//		this.groupId=group.getId();
//		this.groupName=group.getGroupName();
//		this.groupLogoUrl=group.getLogoUrl();
//		this.groupMemberNumber=group.getMember().size();	
//		this.groupIntro = group.getIntro();
//		this.groupTag=tags;
//	}
//	
//	
//	public String getGroupTag() {
//		return groupTag;
//	}
//	public void setGroupTag(String groupTag) {
//		this.groupTag = groupTag;
//	}
//
//	public String getGroupIntro() {
//		return groupIntro;
//	}
//	public void setGroupIntro(String groupIntro) {
//		this.groupIntro = groupIntro;
//	}
//
//
//}

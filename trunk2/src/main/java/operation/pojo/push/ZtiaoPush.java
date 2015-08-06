package operation.pojo.push;

import operation.pojo.group.OpenFireGroup;

public class ZtiaoPush {
	private String title;//通知或广播标题
	
	private String content;//通知或广播内容
	
	private String pushType;//推送类型
	
	private String fromUserId;//推送消息产生人Id 
	
	private String fromUserNickName;//推送消息产生人昵称
	
	private String fromuserLogoUrl; //推送消息产生人logo
	
	private String toUserId;//推送消息接受者
	
	private String sourceId;//来源Id（群等）
	
	private String sourceName;//来源名称（群等）
	
	private String sourceLogoUrl; //来源logo（群等）
	
	private String resourcesId; //资源Id（话题等）
	
	private String resourcesName; //资源标题（话题等）
	
	private String resourceLogoUrl;  //资源Logo（话题等）
	
	private OpenFireGroup  openFireGroup;  //openfire节点
	
	private String reason; //申请加入群组理由
	
	private String adminUserId; //群组管理员ID
	
	private String adminUserNickName;//群组管理员昵称
	
	private String applyGroupId;//申请群组消息Id
	public ZtiaoPush(){
		super();
	}
	
	public ZtiaoPush(String title,String content,String pushType,String fromUserId,String fromUserNickName,String fromUserLogoUrl,
			String toUserId,String sourceId,String sourceName,String sourceLogoUrl,OpenFireGroup  openFireGroup,String resourcesId,
			String resourcesName,String resourceLogoUrl){
		this.title=title;
		this.content=content;
		this.pushType=pushType;
		this.fromUserId=fromUserId;
		this.fromUserNickName=fromUserNickName;
		this.fromuserLogoUrl=fromUserLogoUrl;
		this.toUserId=toUserId;
		this.sourceId=sourceId;
		this.sourceName=sourceName;
		this.sourceLogoUrl=sourceLogoUrl;
		this.openFireGroup=openFireGroup;
		this.resourcesId=resourcesId;
		this.resourcesName=resourcesName;
		this.resourceLogoUrl=resourceLogoUrl;
	}
	
	public ZtiaoPush(String pushType,String fromUserId,String fromUserNickName,String fromUserLogoUrl,
			String toUserId,String sourceId,String sourceName,String sourceLogoUrl,OpenFireGroup  openFireGroup
			,String reason, String adminUserId,String adminUserNickName,String applyGroupId){
		this.pushType=pushType;
		this.fromUserId=fromUserId;
		this.fromUserNickName=fromUserNickName;
		this.fromuserLogoUrl=fromUserLogoUrl;
		this.toUserId=toUserId;
		this.sourceId=sourceId;
		this.sourceName=sourceName;
		this.sourceLogoUrl=sourceLogoUrl;
		this.openFireGroup=openFireGroup;
		this.reason=reason;
		this.adminUserId=adminUserId;
		this.adminUserNickName=adminUserNickName;
		this.applyGroupId=applyGroupId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPushType() {
		return pushType;
	}

	public void setPushType(String pushType) {
		this.pushType = pushType;
	}

	public String getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}

	public String getFromUserNickName() {
		return fromUserNickName;
	}

	public void setFromUserNickName(String fromUserNickName) {
		this.fromUserNickName = fromUserNickName;
	}

	public String getFromuserLogoUrl() {
		return fromuserLogoUrl;
	}

	public void setFromuserLogoUrl(String fromuserLogoUrl) {
		this.fromuserLogoUrl = fromuserLogoUrl;
	}

	public String getToUserId() {
		return toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getSourceLogoUrl() {
		return sourceLogoUrl;
	}

	public void setSourceLogoUrl(String sourceLogoUrl) {
		this.sourceLogoUrl = sourceLogoUrl;
	}

	public OpenFireGroup getOpenFireGroup() {
		return openFireGroup;
	}

	public void setOpenFireGroup(OpenFireGroup openFireGroup) {
		this.openFireGroup = openFireGroup;
	}

	public String getResourcesId() {
		return resourcesId;
	}

	public void setResourcesId(String resourcesId) {
		this.resourcesId = resourcesId;
	}

	public String getResourcesName() {
		return resourcesName;
	}

	public void setResourcesName(String resourcesName) {
		this.resourcesName = resourcesName;
	}

	public String getResourceLogoUrl() {
		return resourceLogoUrl;
	}

	public void setResourceLogoUrl(String resourceLogoUrl) {
		this.resourceLogoUrl = resourceLogoUrl;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(String adminUserId) {
		this.adminUserId = adminUserId;
	}

	public String getAdminUserNickName() {
		return adminUserNickName;
	}

	public void setAdminUserNickName(String adminUserNickName) {
		this.adminUserNickName = adminUserNickName;
	}

	public String getApplyGroupId() {
		return applyGroupId;
	}

	public void setApplyGroupId(String applyGroupId) {
		this.applyGroupId = applyGroupId;
	}
}

package operation.pojo.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="userMessage")
public class UserMessage {
	@Id
	private String id;
	@Indexed
	private String userId;//接收人ID
	@Indexed
	private String messageGroupId;//消息组ID
	private MessageContext context;
	private String type; //消息类型
	private long stime; //消息产生时间
	private String isRead;//是否已经阅读
	private String isOpertison;//是否已经操作
	private String future;//预留字段
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMessageGroupId() {
		return messageGroupId;
	}
	public void setMessageGroupId(String messageGroupId) {
		this.messageGroupId = messageGroupId;
	}
	public MessageContext getContext() {
		return context;
	}
	public void setContext(MessageContext context) {
		this.context = context;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getStime() {
		return stime;
	}
	public void setStime(long stime) {
		this.stime = stime;
	}
	public String getIsRead() {
		return isRead;
	}
	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
	public String getIsOpertison() {
		return isOpertison;
	}
	public void setIsOpertison(String isOpertison) {
		this.isOpertison = isOpertison;
	}
	public String getFuture() {
		return future;
	}
	public void setFuture(String future) {
		this.future = future;
	}

}

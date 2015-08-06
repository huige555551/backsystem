package operation.pojo.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



/**
 * neo4j 用户联系人实体
 *@author hjn
 */
@Document(collection="contactUser")
public class ContactUser {
	@Id
	private String id;
	private String fromUser;  //添加好友发起人
	private Object toUser; //好友
	private String noteName;//好友备注名
	private int status;//状态  0 :无关系  1：请求  2：好友  3：黑名单
	private long ctime;  //创建时间
	private long utime; //更新时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFromUser() {
		return fromUser;
	}
	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}
	public Object getToUser() {
		return toUser;
	}
	public void setToUser(Object toUser) {
		this.toUser = toUser;
	}
	public String getNoteName() {
		return noteName;
	}
	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getCtime() {
		return ctime;
	}
	public void setCtime(long ctime) {
		this.ctime = ctime;
	}
	public long getUtime() {
		return utime;
	}
	public void setUtime(long utime) {
		this.utime = utime;
	}
}

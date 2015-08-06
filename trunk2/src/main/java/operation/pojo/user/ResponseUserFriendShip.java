package operation.pojo.user;

import java.util.List;

public class ResponseUserFriendShip {

	private String id;
	private ResponseUser user;
	private List<ResponseUser> whoKonwnMe;
	private List<ResponseUser> iKnownWho;
	private List<ResponseUser> bothKnown;
	
	private long ctime;
	private long utime;
	public ResponseUserFriendShip() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ResponseUser getUser() {
		return user;
	}
	public void setUser(ResponseUser user) {
		this.user = user;
	}
	public List<ResponseUser> getWhoKonwnMe() {
		return whoKonwnMe;
	}
	public void setWhoKonwnMe(List<ResponseUser> whoKonwnMe) {
		this.whoKonwnMe = whoKonwnMe;
	}
	public List<ResponseUser> getiKnownWho() {
		return iKnownWho;
	}
	public void setiKnownWho(List<ResponseUser> iKnownWho) {
		this.iKnownWho = iKnownWho;
	}
	public List<ResponseUser> getBothKnown() {
		return bothKnown;
	}
	public void setBothKnown(List<ResponseUser> bothKnown) {
		this.bothKnown = bothKnown;
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

package operation.pojo.user;

import java.util.List;

import org.springframework.data.annotation.Id;

public class UserFriendShip {
	@Id
	private String id;
	
	private String user;
	private List<Object> whoKonwnMe;
	private List<Object> iKnownWho;
	private List<Object> bothKnown;
	private long ctime;
	private long utime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public List<Object> getWhoKonwnMe() {
		return whoKonwnMe;
	}
	public void setWhoKonwnMe(List<Object> whoKonwnMe) {
		this.whoKonwnMe = whoKonwnMe;
	}
	public List<Object> getiKnownWho() {
		return iKnownWho;
	}
	public void setiKnownWho(List<Object> iKnownWho) {
		this.iKnownWho = iKnownWho;
	}
	public List<Object> getBothKnown() {
		return bothKnown;
	}
	public void setBothKnown(List<Object> bothKnown) {
		this.bothKnown = bothKnown;
	}
	public long getCtime() {
		return ctime;
	}
	public void setCtime() {
		this.ctime = System.currentTimeMillis();
	}
	public long getUtime() {
		return utime;
	}
	public void setUtime() {
		this.utime = System.currentTimeMillis();
	}
	public UserFriendShip() {
		super();
		this.setCtime();
		this.setUtime();
		// TODO Auto-generated constructor stub
	}
	
}

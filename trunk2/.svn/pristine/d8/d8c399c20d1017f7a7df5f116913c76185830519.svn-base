package operation.pojo.drycargo;

import org.springframework.data.annotation.Id;

public class UserDrycargoBean {
	@Id
	private String id;
	private Object user;
	private Object dryCargoBean;
	private int isFav = 0;//是否收藏
	private long ctime;//创建时间
	private long utime;//修改时间
	
	private int dryFlag;//0干货1炫页
	
	public UserDrycargoBean(){
		super();
	}
	
	public UserDrycargoBean(String userId,String dryCargoId){
		this.user = userId;
		this.dryCargoBean = dryCargoId;
		long time=System.currentTimeMillis();
		this.ctime=time;
		this.utime=time;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Object getUser() {
		return user;
	}


	public void setUser(Object user) {
		this.user = user;
	}


	public Object getDryCargoBean() {
		return dryCargoBean;
	}


	public void setDryCargoBean(Object dryCargoBean) {
		this.dryCargoBean = dryCargoBean;
	}


	public int getIsFav() {
		return isFav;
	}


	public void setIsFav(int isFav) {
		this.isFav = isFav;
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

	public int getDryFlag() {
		return dryFlag;
	}

	public void setDryFlag(int dryFlag) {
		this.dryFlag = dryFlag;
	}

	

	


}

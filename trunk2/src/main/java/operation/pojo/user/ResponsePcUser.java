package operation.pojo.user;

public class ResponsePcUser {
	private String Id;
	
	private String userName;
	
	private String token;
	
	private String udid;
	
	private String nickName;
	
	private String logoURL;
	
	private String type;
	
	private String tag;
	
	private String email;
	
	private String phoneNumber;

	private String intro;
	
	private String job;
	
	private String area;
	
	private String station;
	
	private long  dryCargoCount;//干货数量
 	private long topicCount;
	private int attentionCount;
	
	public ResponsePcUser(String id, String userName, String token,
			String udid, String nickName, String logoURL) {
		super();
		Id = id;
		this.userName = userName;
		this.token = token;
		this.udid = udid;
		this.nickName = nickName;
		this.logoURL = logoURL;
	}

	public ResponsePcUser(String id, String userName, String token,
			String udid, String nickName, String logoURL,
			String tag, String email, String phoneNumber,String intro,String area,String station) {
		super();
		Id = id;
		this.userName = userName;
		this.token = token;
		this.udid = udid;
		this.nickName = nickName;
		this.logoURL = logoURL;
		this.tag = tag;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.intro = intro;
		this.area=area;
		this.station=station;
	
	}

	public ResponsePcUser() {
	}
	
	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}
	


	public String getId() {
		return Id;
	}


	


	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUdid() {
		return udid;
	}

	public void setUdid(String udid) {
		this.udid = udid;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getLogoURL() {
		return logoURL;
	}

	public void setLogoURL(String logoURL) {
		this.logoURL = logoURL;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public long getDryCargoCount() {
		return dryCargoCount;
	}

	public void setDryCargoCount(long dryCargoCount) {
		this.dryCargoCount = dryCargoCount;
	}

	public long getTopicCount() {
		return topicCount;
	}

	public void setTopicCount(long topicCount) {
		this.topicCount = topicCount;
	}

	public int getAttentionCount() {
		return attentionCount;
	}

	public void setAttentionCount(int attentionCount) {
		this.attentionCount = attentionCount;
	}

	

}

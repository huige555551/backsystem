package operation.pojo.live;

import java.util.List;

import net.sf.json.JSONObject;
import operation.pojo.topics.Images;

import org.springframework.data.annotation.Id;

/**
 * 直播对象
 * @author nes
 *
 */
public class Live {
	@Id
	private String id;
	private String title; //直播标题
	private long liveStartTime;//直播开始时间
	private long liveEndTime;//直播结束时间
	private int channel;//直播来源 1 微吼  0 KK
	private String intro;//直播文字简介
	private List<Images> images;//直播展示图片
	private long ctime;//创建时间
	private long utime;//修改时间
	private List<JSONObject> group;	//直播所在群组
	private String liveNumber;//直播间房间号
	private String liveUrl;//直播地址
	private String createUser;//创建者
	private String createUserName;
	private String createUserLogoUrl;
	
	public Live() {
		super();
		ctime=System.currentTimeMillis();
	}
	
	


	public Live( String title, long liveStartTime, long liveEndTime,
			int channel, String intro, List<Images> images,
			List<JSONObject> group, String liveNumber,String liveUrl,String createUser,
			String createUserName,String createUserLogoUrl) {
		super();
		this.title = title;
		this.liveStartTime = liveStartTime;
		this.liveEndTime = liveEndTime;
		this.channel = channel;
		this.intro = intro;
		this.images = images;
		long time=System.currentTimeMillis();
		this.ctime=time;
		this.utime=time;
		this.group = group;
		this.liveNumber = liveNumber;
		this.liveUrl=liveUrl;
		this.createUser=createUser;
		this.createUserName=createUserName;
		this.createUserLogoUrl=createUserLogoUrl;
	}




	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public long getLiveStartTime() {
		return liveStartTime;
	}


	public void setLiveStartTime(long liveStartTime) {
		this.liveStartTime = liveStartTime;
	}


	public long getLiveEndTime() {
		return liveEndTime;
	}


	public void setLiveEndTime(long liveEndTime) {
		this.liveEndTime = liveEndTime;
	}


	public int getChannel() {
		return channel;
	}


	public void setChannel(int channel) {
		this.channel = channel;
	}


	public String getIntro() {
		return intro;
	}


	public void setIntro(String intro) {
		this.intro = intro;
	}


	public List<Images> getImages() {
		return images;
	}


	public void setImages(List<Images> images) {
		this.images = images;
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


	public List<JSONObject> getGroup() {
		return group;
	}


	public void setGroup(List<JSONObject> group) {
		this.group = group;
	}


	public String getLiveNumber() {
		return liveNumber;
	}


	public void setLiveNumber(String liveNumber) {
		this.liveNumber = liveNumber;
	}

	public String getLiveUrl() {
		return liveUrl;
	}


	public void setLiveUrl(String liveUrl) {
		this.liveUrl = liveUrl;
	}
	


	public String getCreateUser() {
		return createUser;
	}


	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}


	public String getCreateUserName() {
		return createUserName;
	}


	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}


	public String getCreateUserLogoUrl() {
		return createUserLogoUrl;
	}


	public void setCreateUserLogoUrl(String createUserLogoUrl) {
		this.createUserLogoUrl = createUserLogoUrl;
	}
	
}

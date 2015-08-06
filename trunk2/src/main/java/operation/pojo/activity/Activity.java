package operation.pojo.activity;

import org.springframework.data.annotation.Id;

/**
 * 
* @ClassName: Activity
* @Description:	活动
* @author tangli
* @date 2015年3月23日 下午1:51:29
*
*/
public class Activity {
	@Id
	private String id;
	private String name;
	private long activityStartTime;//活动开始时间
	private long activityEndTime;//活动结束时间
	private int type;//活动类型 1 不需要报名  0 需要报名
	private String[] options;//报名必选项
	private String intro;//活动简介
	private String des;//活动详情
	private String desMainImg;//详情页主图
	private String[] desImgs;//详情图片
	private String mainImg;//活动主图
	private double price;//活动价格
	private String company;//主办方
	private long optionStartTime;//报名开始时间
	private long optionEndTime;//报名截止时间
	private String province;//活动举办省份
	private String city;//活动举办城市
	private String address;//活动举办街道地址
	private long ctime;//创建时间
	private long maxCount;//报名上限
	private long registrationCount;//已报名人数
	
	
	public Activity() {
		ctime=System.currentTimeMillis();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getActivityStartTime() {
		return activityStartTime;
	}
	public void setActivityStartTime(long activityStartTime) {
		this.activityStartTime = activityStartTime;
	}
	public long getActivityEndTime() {
		return activityEndTime;
	}
	public void setActivityEndTime(long activityEndTime) {
		this.activityEndTime = activityEndTime;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String[] getOptions() {
		return options;
	}
	public void setOptions(String[] options) {
		this.options = options;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String[] getDesImgs() {
		return desImgs;
	}
	public void setDesImgs(String[] desImgs) {
		this.desImgs = desImgs;
	}
	public String getMainImg() {
		return mainImg;
	}
	public void setMainImg(String mainImg) {
		this.mainImg = mainImg;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public long getOptionStartTime() {
		return optionStartTime;
	}
	public void setOptionStartTime(long optionStartTime) {
		this.optionStartTime = optionStartTime;
	}
	public long getOptionEndTime() {
		return optionEndTime;
	}
	public void setOptionEndTime(long optionEndTime) {
		this.optionEndTime = optionEndTime;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesMainImg() {
		return desMainImg;
	}
	public void setDesMainImg(String desMainImg) {
		this.desMainImg = desMainImg;
	}
	public long getCtime() {
		return ctime;
	}
	public void setCtime(long ctime) {
		this.ctime = ctime;
	}
	public long getMaxCount() {
		return maxCount;
	}
	public void setMaxCount(long maxCount) {
		this.maxCount = maxCount;
	}
	public long getRegistrationCount() {
		return registrationCount;
	}
	public void setRegistrationCount(long registrationCount) {
		this.registrationCount = registrationCount;
	}
	
	
	
	
	


	
}

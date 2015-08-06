package operation.pojo.ad;

import org.springframework.data.annotation.Id;

/**
 * 
 * @ClassName: Ad
 * @Description: 广告位
 * @author tangli
 * @date 2015年3月17日 下午1:35:16
 *
 */
public class Ad {
	@Id
	private String id;
	private String name;//广告位名称
	private String remark;//广告备注
	private String adSid;//渠道商Id 
	private String creater;//创建人
	private String adSellerId;//渠道商Id号 
	private String adSellerName;//渠道商名称	
	private long ctime;
	private long utime;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdSid() {
		return adSid;
	}

	public void setAdSid(String adSid) {
		this.adSid = adSid;
	}

	public String getAdSellerId() {
		return adSellerId;
	}

	public void setAdSellerId(String adSellerId) {
		this.adSellerId = adSellerId;
	}

	public String getAdSellerName() {
		return adSellerName;
	}

	public void setAdSellerName(String adSellerName) {
		this.adSellerName = adSellerName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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


	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}


}

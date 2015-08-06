package operation.pojo.ad;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

/**
 * 
* @ClassName: AdSeller
* @Description: 渠道商
* @author tangli
* @date 2015年3月17日 下午1:29:01
*
 */
public class AdSeller {
    @Id
	private String id;
	private String name;//名称
	private String adSellerId;//渠道商id
    private long ctime;
    private long utime;
    
	public AdSeller() {
		this.ctime = System.currentTimeMillis();
		this.utime = System.currentTimeMillis();
	}
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
	public String getAdSellerId() {
		return adSellerId;
	}
	public void setAdSellerId(String adSellerId) {
		this.adSellerId = adSellerId;
	}
	
}

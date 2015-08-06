package operation.pojo.index;

import java.util.List;

import operation.pojo.ad.ZtiaoAd;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Explore {
	
	private List<ZtiaoAd> ztiaoAd; //广告
	
	private JSONArray special; //专题
	
	private JSONArray group; //群组
	
	private Object exploreData; //推荐数据(不包含群组)

	public List<ZtiaoAd> getZtiaoAd() {
		return ztiaoAd;
	}

	public void setZtiaoAd(List<ZtiaoAd> ztiaoAd) {
		this.ztiaoAd = ztiaoAd;
	}

	public JSONArray getSpecial() {
		return special;
	}

	public void setSpecial(JSONArray special) {
		this.special = special;
	}

	public JSONArray getGroup() {
		return group;
	}

	public void setGroup(JSONArray group) {
		this.group = group;
	}

	public Object getExploreData() {
		return exploreData;
	}

	public void setExploreData(Object exploreData) {
		this.exploreData = exploreData;
	}
	
	

}

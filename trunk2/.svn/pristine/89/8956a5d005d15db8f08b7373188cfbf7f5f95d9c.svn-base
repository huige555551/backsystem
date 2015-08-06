package operation.service.ad;

import java.util.List;

import operation.exception.XueWenServiceException;
import operation.pojo.ad.AdSeller;
import operation.repo.ad.AdSellerReposity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tools.Config;
import tools.StringUtil;

@Service
public class AdSellerService {
	@Autowired
	private AdSellerReposity adSellerReposity;
	/**
	 * 
	 * @Title: createAdSeller
	 * @Description: 创建渠道商
	 * @param adSeller
	 * @return
	 * @throws XueWenServiceException AdSeller
	 * @throws
	 */
	public AdSeller createAdSeller(AdSeller adSeller) throws XueWenServiceException{
		if(StringUtil.isBlank(adSeller.getName())||StringUtil.isBlank(adSeller.getAdSellerId())){
			throw new XueWenServiceException(Config.STATUS_201,Config.MSG_201, null);	
		}
		if(null!=adSellerReposity.findByAdSellerId(adSeller.getAdSellerId())){
			throw new XueWenServiceException(Config.STATUS_201,Config.MSG_ADSELLER_ID, null);
		}
		if(null!=adSellerReposity.findByName(adSeller.getName())){
			throw new XueWenServiceException(Config.STATUS_201,Config.MSG_ADSELLER_NAME, null);
		}
		return this.adSellerReposity.save(adSeller);
	}
	public void deleteAdSeller(AdSeller adSeller){
		this.adSellerReposity.delete(adSeller);
	}
	public Page<AdSeller> page(Pageable pageable){
		return this.adSellerReposity.findAll(pageable);
	}
	public List<AdSeller> adSellerList(){
		return this.adSellerReposity.findAll();
	}
	public AdSeller findById(String id) {
		return adSellerReposity.findOne(id);
	}

}

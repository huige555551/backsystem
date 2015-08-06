package operation.service.ad;

import java.util.ArrayList;
import java.util.List;

import operation.exception.XueWenServiceException;
import operation.pojo.ad.ZtiaoAd;
import operation.repo.ad.ZtiaoAdMongoTemplate;
import operation.repo.ad.ZtiaoAdReposity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ZtiaoAdService {
	@Autowired
	private ZtiaoAdReposity ztiaoAdReposity;
	@Autowired
	private ZtiaoAdMongoTemplate ztiaoAdMongoTemplate;
	/**
	 * 新增广告位置信息
	 * @param ztiaoAd
	 * @return
	 * @throws XueWenServiceException
	 */
	public ZtiaoAd addNew(ZtiaoAd ztiaoAd)throws XueWenServiceException{
		long time=System.currentTimeMillis();
		ztiaoAd.setCtime(time);
		ztiaoAd.setUtime(time);
		ztiaoAd.setEffective(false);
		return ztiaoAdReposity.save(ztiaoAd);
	}
//	private String id;
//	private String adId;//广告位id   标示广告位的位置标号 如： 0  app首页
//	private String name;//广告位名称  如：App首页
//	private String adSid;//渠道商Id  0:站内  10：站外（如企业大学）20：站外（如机构网校）
//	private String adSellerId;//渠道商Id号 如 0 ：站内话题 1：站内干货 2：站内课程  10：企业大学话题  11 ：企业大学课程  20：机构网校机构 21：机构网校课程
//	private String adSellerName;//渠道商名称	 如  站内话题
//	private String creater;//创建人 
//	private String linkUrl;//外链跳转地址
//	private String picUrl; //广告位图片
//	private float picWidth;//广告位图片宽度
//	private float picHeight;//广告位图片高度
//	private long ctime; //广告创建时间
//	private long utime;//广告更新时间
//	private boolean effective;//广告是否有效(控制显示)
//	private int index;//广告序列
//	private String groupId;//群组Id
//	private String topicId;//话题Id
//	private String courseId;//课程Id
//	private String groupCourseId;//群组课程ID
//	private String dryCargoId;//干货Id
//	private long ccount;//点击数量
	/**
	 * 纸条首页广告位信息获取
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<ZtiaoAd> getAppIndexAd(String adid)throws XueWenServiceException{
		return ztiaoAdReposity.findByAdIdAndEffective(adid,true);
	}
	
	
	/**
	 * 纸条首页广告位信息数量
	 * @return
	 * @throws XueWenServiceException
	 */
	public float getAppIndexAdCount(String adid)throws XueWenServiceException{
		return ztiaoAdReposity.countByAdId(adid);
	}
	
	
	/**
	 * 搜索纸条首页广告位信息获取
	 * @return
	 * @throws XueWenServiceException
	 */
	public Page<ZtiaoAd> findAd(String adId,Pageable page)throws XueWenServiceException{
		return ztiaoAdReposity.findByAdId(adId, page);
	}
	
	
	/**
	 * 搜索纸条首页广告位总数
	 * @return
	 * @throws XueWenServiceException
	 */
	public float Count(String ids)throws XueWenServiceException{
		return ztiaoAdReposity.countByAdId(ids);
	}
	
	/**
	 * 搜索纸条首页广告位信息获取
	 * @return
	 * @throws XueWenServiceException
	 */
	public ZtiaoAd findAdIInfo(String id)throws XueWenServiceException{
		return ztiaoAdReposity.findById(id);
	}
	
	
	/**
	 * 根据位置获取广告
	 * @return
	 * @throws XueWenServiceException
	 */
	public ZtiaoAd findAdByindex(int index)throws XueWenServiceException{
		return ztiaoAdReposity.findByIndex(index);
	}
	
	/**
	 * 保存广告
	 * @return
	 * @throws XueWenServiceException
	 */
	public boolean savead(ZtiaoAd z){
		try {
			ztiaoAdReposity.save(z);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	
	
	/**
	 * 搜索纸条首页广告位信息获取
	 * @return
	 * @throws XueWenServiceException
	 */
	public boolean updateAdIInfo(ZtiaoAd z)throws XueWenServiceException{
		 
		try {
			ztiaoAdReposity.save(z);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	/**
	 * 通过群组查询广告
	 * @param groupId
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<String> findAllZtiaoAdByGroup(String groupId) throws XueWenServiceException{
		List<ZtiaoAd> ztiaoAD = ztiaoAdReposity.findByGroupId(groupId);
		List<String> ztiaoIdList = new ArrayList<String>();
		if(ztiaoAD!=null && ztiaoAD.size() > 0){
			for(int i = 0 ; i < ztiaoAD.size(); i++){
				ZtiaoAd z = ztiaoAD.get(i);
				ztiaoIdList.add(z.getId());
			}
		}
		return ztiaoIdList;
	}
	
}

package operation.service.ad;
import operation.pojo.ad.Ad;
import operation.pojo.ad.AdVo;
import operation.pojo.log.UserRegLogS;
import operation.repo.log.UserRegLogSReposity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import tools.StringUtil;

@Service
public class AdVoService {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	
	/**
	 * 
	 * @Title: searchByKey Mongo聚合
	 * @auther Tangli
	 * @Description:搜索
	 * @param key 关键词
	 * @param ctime 开始时间
	 * @param etime 结束数据
	 * @param pageable
	 * @return Page<UserRegLogS>
	 * @throws
	 */
	public Page<AdVo> searchByKey(long ctime, long etime, String qdId,
			String qdName, Pageable pageable) {
		String[] strings = new String[] { "adId","name", "adSid",
				"creater", "adSellerId","adSellerName","adTime" };
		Criteria criteria = new Criteria();
		if(StringUtil.isBlank(qdId)&&StringUtil.isBlank(qdName)){
		}
		if(StringUtil.isBlank(qdId)&&(!StringUtil.isBlank(qdName))){
			criteria.orOperator(Criteria.where("adSellerName").regex(qdName));
		}
		if(StringUtil.isBlank(qdName)&&(!StringUtil.isBlank(qdId))){
			criteria.orOperator(Criteria.where("adSellerId").regex(qdId));
		}
		if((!StringUtil.isBlank(qdId))&&(!StringUtil.isBlank(qdName))){
			criteria.orOperator(Criteria.where("adSellerId").regex(qdId),
					Criteria.where("adSellerName").regex(qdName));
		}
		criteria.and("adTime").gte(ctime).lte(etime);
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(criteria),
				Aggregation.group(strings).sum("ctn").as("rcount").count().as("ccount"),
				Aggregation.sort(new Sort(Direction.DESC, "rcount")),
				Aggregation.skip(pageable.getOffset()),
				Aggregation.limit(pageable.getPageSize()));
		AggregationResults<AdVo> ss = mongoTemplate.aggregate(
				aggregation, "userRegistLog", AdVo.class);		
	    Page<AdVo>logss=	new PageImpl<AdVo>(ss.getMappedResults(), pageable, ss.getMappedResults().size());
		return logss;
	}
}

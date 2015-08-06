package operation.service.log;
import java.util.List;

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
import org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.mongodb.AggregationOptions;
import com.mongodb.DBObject;

import tools.Config;
import tools.StringUtil;

@Service
public class UserRegLogService {
	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private UserRegLogSReposity userRegLogSReposity;
	
	
	/**
	 * 
	 * @Title: save
	 * @auther Tangli
	 * @Description: 保存
	 * @param log void
	 * @throws
	 */
	public void save(UserRegLogS log){
		userRegLogSReposity.save(log);
	}
	
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
	public Page<UserRegLogS> searchByKey(String key, long ctime, long etime,
			Pageable pageable) {
		String[] strings = new String[] { "vUserId", "vUserEmail",
				"vUserPhone", "vUserNick" };
		Criteria criteria = new Criteria();
		//criteria.where("refType").is(Config.LOG_REG_TYPE_YQ);
		criteria.and("refType").is(Config.LOG_REG_TYPE_YQ);
		if (StringUtil.isBlank(key)) {
			criteria.and("ctime").gte(ctime).lte(etime);
		} else {
			criteria.orOperator(Criteria.where("vUserNick").regex(key),
					Criteria.where("vUserEmail").regex(key),
					Criteria.where("vUserPhone").regex(key));
			criteria.and("ctime").gte(ctime).lte(etime);
		}
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(criteria),
				Aggregation.group(strings).sum("ctn").as("total"),
				Aggregation.sort(new Sort(Direction.DESC, "total")),
				Aggregation.skip(pageable.getOffset()),
				Aggregation.limit(pageable.getPageSize()));
		AggregationResults<UserRegLogS> ss = mongoTemplate.aggregate(
				aggregation, "userRegistLog", UserRegLogS.class);		
	    Page<UserRegLogS>logss=	new PageImpl<UserRegLogS>(ss.getMappedResults(), pageable, ss.getMappedResults().size());
		return logss;
	}
}

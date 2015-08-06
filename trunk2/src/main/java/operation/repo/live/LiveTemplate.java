package operation.repo.live;

import java.util.List;

import operation.exception.XueWenServiceException;
import operation.pojo.activity.NewActivity;
import operation.pojo.live.Live;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class LiveTemplate {

	@Autowired
	private MongoTemplate mongoTemplate;
	/**
	 * 查询群组下的直播课
	 * @param able
	 * @param groupId
	 * @return
	 * @throws XueWenServiceException
	 */
	public Page<Live> findByGroupId(Pageable able,String groupId)throws XueWenServiceException{
		Query q=new Query(Criteria.where("group.groupId").is(groupId));
		//获取总条数  
        long totalCount = this.mongoTemplate.count(q,Live.class);  
        int skip = able.getPageNumber()*able.getPageSize();  
        q.skip(skip);// skip相当于从那条记录开始  
        q.limit(able.getPageSize());// 从skip开始,取多少条记录  
        List<Live> as=mongoTemplate.find(q,Live.class);
        Page<Live> aspage=new PageImpl<Live>(as, able, totalCount);
        return aspage;  
	}
	
	/**
	 * 根据地址和群组判断此直播是否已经创建
	 * @param groupId
	 * @param url
	 * @return
	 * @throws XueWenServiceException
	 */
	public boolean exiseByGroupIdAndUrl(String groupId,String url)throws XueWenServiceException{
		Query q=new Query(Criteria.where("group.groupId").is(groupId).and("liveUrl").is(url));
		return mongoTemplate.exists(q, Live.class);
	}
	/**
	 * 根据url判断此直播是否存在
	 * @param url
	 * @return
	 * @throws XueWenServiceException
	 */
	public boolean exiseByUrl(String url)throws XueWenServiceException{
		Query q=new Query(Criteria.where("liveUrl").is(url));
		return mongoTemplate.exists(q, Live.class);
	}
	
}

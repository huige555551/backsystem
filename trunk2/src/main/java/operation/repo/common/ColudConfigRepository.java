/**   
* @Title: ColudConfigTemplate.java
* @Package operation.pojo.common
* @Description: 
* @author yaoj
* @date 2014年12月17日 下午4:44:15
* @version V1.0
*/ 
package operation.repo.common;

import operation.pojo.common.ColudConfig;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

/** 
 * @ClassName: ColudConfigTemplate
 * @Description: 云存储服务器的配置信息
 * @author yaoj
 * @date 2014年12月17日 下午4:44:15
 * 
 */
@Component
public interface ColudConfigRepository extends MongoRepository<ColudConfig, String> {
	
	/**
	 * 
	 * @Title: findoneByCkey
	 * @Description: 根据ckey查找单条记录
	 * @param ckey
	 * @return ColudConfig
	 * @throws
	 */
	ColudConfig findOneByCkey(String ckey);

	/**
	 * 
	 * @Title: findOneById
	 * @Description: 根据id查找单条记录
	 * @param id
	 * @return ColudConfig
	 * @throws
	 */
	ColudConfig findOneById(String id);

	/**
	 * 
	 * @Title: find
	 * @Description: 查找
	 * @return List<ColudConfig>
	 * @throws
	 */
	Page<ColudConfig> findByCkey(String ckey, Pageable pageable);
	
}

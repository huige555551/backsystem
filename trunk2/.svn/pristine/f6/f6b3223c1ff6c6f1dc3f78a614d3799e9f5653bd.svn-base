package operation.repo.jobs;

import java.util.List;

import operation.pojo.industry.IndustryBean;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * 改版之后的
 * @author yangquanliang
 *
 */
public interface IndustryNewRepository extends MongoRepository<IndustryBean, String> {


@Query(value="{'status':?0}",fields="{ 'indDirectList': 0}")
List<IndustryBean> findAllAndStatus(int status);

IndustryBean findById(String id);
}

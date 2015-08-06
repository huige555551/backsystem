package operation.repo.tags;

import operation.pojo.industry.IndustryBean;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IndustryRepostory extends MongoRepository<IndustryBean, String> {

}

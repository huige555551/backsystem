package operation.repo.xuanye;

import operation.pojo.xuanye.Xuanye;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface XuanyeRepository extends MongoRepository<Xuanye, String> {

	Xuanye findOneByGroupAndUrl(String groupId, String url);
	Xuanye findOneByGroupAndId(String groupId, String id);
}

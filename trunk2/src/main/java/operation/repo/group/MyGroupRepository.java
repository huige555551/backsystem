package operation.repo.group;

import operation.pojo.group.MyGroup;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MyGroupRepository extends MongoRepository<MyGroup, String> {
	MyGroup findByUserId(String userId);
}

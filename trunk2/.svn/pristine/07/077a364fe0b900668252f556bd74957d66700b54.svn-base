package operation.repo.like;


import operation.pojo.like.LikeOrUnlikePojo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LikeRepository extends MongoRepository<LikeOrUnlikePojo, String> {
	LikeOrUnlikePojo findByTypeAndTypeId(String type,String typeId);
}

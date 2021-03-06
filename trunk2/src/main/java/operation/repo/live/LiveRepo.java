package operation.repo.live;

import operation.pojo.live.Live;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LiveRepo extends MongoRepository<Live, String> {

	Live findByLiveUrl(String url);
}

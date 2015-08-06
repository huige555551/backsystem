package operation.repo.topics;

import java.util.List;

import operation.pojo.topics.Post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String>{
	Page<Post> findByTopicId(String topicId,Pageable pages);
	List<Post> findByTopicId(String topicId);
	Post findOneByPostId(String postId);
	List<Post> findByTopicIdIn(List<String>topids, Sort sort);
	Post findOneByTopicId(String topicId, Sort sort);
	List<Post> findByAuthorId(String id);
	Page<Post> findByTopicIdAndType(String topicId, int i, Pageable pageable);
}

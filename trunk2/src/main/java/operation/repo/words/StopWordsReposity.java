package operation.repo.words;

import operation.pojo.words.StopWords;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface StopWordsReposity extends MongoRepository<StopWords, String> {

	StopWords findOneByContentIn(String word);

	//StopWords findOneByContentLike(String word);

}

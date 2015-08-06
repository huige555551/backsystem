package operation.repo.course;


import java.util.List;

import operation.pojo.course.NewChapter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NewChapterRepository  extends MongoRepository<NewChapter, String>{
  // List<NewChapter> findByLessonsIn(String id);

   List<Object> findByIdIn(List<Object> chapter);
   List<NewChapter> findNewChapterByIdIn(List<Object> chapter);
   NewChapter findOneBylessonIdsIn(String id);
   Page<NewChapter> findByIsUsed(int isUsed,Pageable pageable);

}

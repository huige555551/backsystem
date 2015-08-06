package operation.repo.study;

import operation.pojo.study.Study;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudyRepository extends MongoRepository<Study, String>{

	Page<Study> findBySourceIdAndDomain(String groupCourseId, String domain, Pageable pageable);
	int countByUserIdAndDomainAndStudyType(String userId,String domian,String studyType);

	
}

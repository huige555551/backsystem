package operation.repo.skill;

import java.util.List;

import operation.pojo.skill.Skills;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SkillsRepository extends MongoRepository<Skills, String>{

	List<Object> findByIdIn(List<String> ids);
	Skills findByName(String name);
}

package operation.repo.common;

import java.util.List;

import operation.pojo.common.ConfigChange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class ConfigChangeTemplate {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	/**
	 * 
	 * @Title: add
	 * @Description: 添加
	 * @param cloudConfig
	 * @return ColudConfig
	 * @throws
	 */
	public void add(List<ConfigChange> configChange) {
		mongoTemplate.insert(configChange, ConfigChange.class);
	}

}

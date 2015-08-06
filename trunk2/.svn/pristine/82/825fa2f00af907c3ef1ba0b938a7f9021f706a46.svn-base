package operation.service.common;

import java.util.List;

import operation.pojo.common.ConfigChange;
import operation.repo.common.ConfigChangeTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigChangeService {
	
	@Autowired
	private ConfigChangeTemplate changeTemplate;

	public void add(List<ConfigChange> configChange) {
		changeTemplate.add(configChange);
	}

}

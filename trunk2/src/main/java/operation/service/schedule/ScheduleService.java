package operation.service.schedule;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import tools.Config;

@Service
@Component
public class ScheduleService {
	
	@Value("${schedule.service.url}")
	private String scheduleServiceUrl;	
	

	public void schedule(){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getForObject(scheduleServiceUrl+ "schedule/findindex",String.class);
	}
	
}

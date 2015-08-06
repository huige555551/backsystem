package operation.service.remmond;

import java.util.List;

import operation.pojo.remmond.Remmond;
import operation.repo.remmond.RemmondRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class RemmondService {

	@Autowired
	public RemmondRepository remmondRepository;

	public boolean addRemmond(Remmond r) {
		remmondRepository.save(r);
		return true;
	}

	public Remmond findGroupId(String userId) {
		return remmondRepository.findOne(userId);
	}

	public Remmond findOneByUserId(String id) {
		return remmondRepository.findOneByUserId(id);

	}

	public Remmond save(Remmond r) {
		return remmondRepository.save(r);

	}
	
	
}

package operation.service.activity;

import java.util.List;

import operation.pojo.activity.ProCity;
import operation.repo.activity.ProCityRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tools.StringUtil;

@Service
public class ProCityService {
	@Autowired
	private ProCityRepo proCityRepo;
	public List<ProCity> findByPId(String pId){
		return proCityRepo.findByPId(pId);
	}
	public ProCity save(ProCity proCity){
		if(!StringUtil.isBlank(proCity.getpId())){
			ProCity pro=proCityRepo.findOne(proCity.getpId());
			proCity.setpName(pro.getName());
		}
		return proCityRepo.save(proCity);
	}

}

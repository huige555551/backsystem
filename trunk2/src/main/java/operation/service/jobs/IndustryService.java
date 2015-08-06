package operation.service.jobs;

import java.util.List;

import operation.exception.XueWenServiceException;
import operation.pojo.industry.IndustryBean;
import operation.pojo.jobs.Industry;
import operation.pojo.jobs.Industryclass;
import operation.pojo.jobs.Jobs;
import operation.repo.jobs.IndustryNewRepository;
import operation.repo.jobs.IndustryRepository;
import operation.repo.jobs.IndustryclassRepository;
import operation.repo.jobs.JobsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import tools.StringToList;

@Service
@Component
@Configuration
public class IndustryService {

	@Autowired
	public JobsRepository jobsRepository;

	@Autowired
	public IndustryclassRepository industryclassRepository;

	@Autowired
	public IndustryRepository industryRepository;
	
	@Autowired
	public IndustryNewRepository industryNewRepository;

	// public List<Industry> getAllIndustry()throws XueWenServiceException{
	// List<Industry> industrys=industryRepository.findAll();
	// if(industrys !=null && industrys.size()>0){
	// for(Industry industry:industrys){
	// List<Industryclass>
	// industryclasses=industryclassRepository.findByPId(industry.getId());
	// if(industryclasses !=null && industryclasses.size()>0){
	// for(Industryclass industryclass:industryclasses){
	// List<Jobs> jobs=jobsRepository.findByPId(industryclass.getId());
	// if(jobs !=null && jobs.size()>0){
	// industryclass.setIndustryClass(jobs);
	// }
	// }
	// industry.setIndustryClass(industryclasses);
	// }
	// }
	// }
	//
	// return industrys;
	// }
	/**
	 * 获取新版行业
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<IndustryBean> getAllNewIndustry() throws XueWenServiceException {

		return industryNewRepository.findAllAndStatus(0);
	}
	
	/**
	 * 
	 * @return
	 * @throws XueWenServiceException
	 */
	public IndustryBean getInduDirect(String id) throws XueWenServiceException {
	      return industryNewRepository.findById(id);
	}
	

	public List<Industryclass> getAllIndustry() throws XueWenServiceException {

		List<Industryclass> industryclasses = industryclassRepository.findAll();
		if (industryclasses != null && industryclasses.size() > 0) {
			for (Industryclass industryclass : industryclasses) {
				List<Jobs> jobs = jobsRepository.findByPId(industryclass
						.getId());
				if (jobs != null && jobs.size() > 0) {
					industryclass.setIndustryClass(jobs);
				}
			}
		}
		return industryclasses;

	}

	public void insertIndustry(String industrys) throws XueWenServiceException {
		List<Object> names = StringToList.tranfer(industrys);
		for (Object obj : names) {
			Industry ind = new Industry();
			ind.setName(obj.toString());
			industryRepository.save(ind);
		}
	}

	public void insertIndustryclass(String industryclass, String pid)
			throws XueWenServiceException {
		List<Object> names = StringToList.tranfer(industryclass);
		for (Object obj : names) {
			Industryclass ind = new Industryclass();
			ind.setName(obj.toString());
			ind.setpId(pid);
			industryclassRepository.save(ind);
		}
	}

	public void insertJobs(String industryclass, String pid)
			throws XueWenServiceException {
		List<Object> names = StringToList.tranfer(industryclass);
		for (Object obj : names) {
			Jobs ind = new Jobs();
			ind.setName(obj.toString());
			ind.setpId(pid);
			jobsRepository.save(ind);
		}
	}

	public Jobs findJobs(String id) throws XueWenServiceException {
		return jobsRepository.findById(id);
	}

	public Industryclass findIndustryClass(String id)
			throws XueWenServiceException {
		return industryclassRepository.findOneById(id);
	}

	public List<Industryclass> findAllIndustryClass()
			throws XueWenServiceException {
		return industryclassRepository.findAll();
	}
}

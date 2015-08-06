package operation.service.feedback;

import operation.exception.XueWenServiceException;
import operation.pojo.feedback.FeedBack;
import operation.repo.feedback.FeedBackRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
@Component
@Configuration
public class FeedBackService {

	@Autowired
	public FeedBackRepository feedBackRepository;
	
	public FeedBackService(){
		
	}
	
	public FeedBack save(FeedBack feedBack) throws XueWenServiceException{
		return feedBackRepository.save(feedBack);
	}
	
	public Page<FeedBack> findAllFeedBack(Pageable pageable) throws XueWenServiceException{
		return feedBackRepository.findAll(pageable);
	}
	
	public FeedBack findOneFeedBack(String id) throws XueWenServiceException{
		return feedBackRepository.findById(id);
	}
	
	public boolean deleteFeedBack(FeedBack id) throws XueWenServiceException{
		try {
			feedBackRepository.delete(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}

package operation.service;

import operation.exception.XueWenServiceException;
import operation.pojo.news.NewsBean;
import operation.repo.news.NewsBeanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class NewsBeanService {
	@Autowired
	private NewsBeanRepository newsBeanRepository;
	
	public NewsBeanService(){
		
	}
	/**
	 * 查询新闻列表
	 * @param pageable
	 * @return
	 * @throws XueWenServiceException
	 */
	public Page<NewsBean> findAllNewsBean(Pageable pageable) throws XueWenServiceException {
		Page<NewsBean> newsBean = newsBeanRepository.findAll(pageable);
		return newsBean;
	}
	/**
	 * 通过新闻ID查询新闻详情
	 * @param newsId
	 * @return
	 * @throws XueWenServiceException
	 */
	public NewsBean findOneById(String newsId)throws XueWenServiceException {
		NewsBean newsBean =  newsBeanRepository.findOne(newsId);
		newsBean.setViews(newsBean.getViews()+1);
		newsBeanRepository.save(newsBean);
		return newsBean;
	}
}

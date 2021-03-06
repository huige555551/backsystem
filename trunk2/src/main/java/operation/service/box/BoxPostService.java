package operation.service.box;

import java.util.List;

import operation.exception.XueWenServiceException;
import operation.pojo.box.BoxPost;
import operation.pojo.drycargo.Drycargo;
import operation.repo.box.BoxPostRepository;
import operation.repo.box.BoxPostTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class BoxPostService {

	@Autowired
	private BoxPostRepository boxPostRepository;
	@Autowired
	private BoxPostTemplate boxPostTemplate;
	public BoxPostService(){
		super();
	}
	/**
	 * 创建推荐位置
	 * @param chinaName
	 * @param englishName
	 * @return
	 * @throws XueWenServiceException
	 */
	public BoxPost create(String chinaName,String englishName,String local,String type,int size)throws XueWenServiceException{
		return boxPostRepository.save(new BoxPost(chinaName, englishName,local,type,size));
	}
	
	/**
	 * 分页获取所有推荐的位置
	 * @param pageable
	 * @return
	 * @throws XueWenServiceException
	 */
	public Page<BoxPost> findAllOfPage(Pageable pageable)throws XueWenServiceException{
		return boxPostRepository.findAll(pageable);
	}
	
	/**
	 * 根据类型获取此类型下所有的位置
	 * @param type
	 * @return
	 * @throws XueWenServiceException
	 */
	public List<BoxPost> findByType(String type)throws XueWenServiceException{
		return boxPostRepository.findByType(type);
	}
	
	/**
	 * 根据类型获取此类型下所有的位置
	 * @param type
	 * @return
	 * @throws XueWenServiceException
	 */
	public BoxPost findById(String id)throws XueWenServiceException{
		return boxPostRepository.findById(id);
	}
	
	/**
	 * 根据Id获取位置规定长度
	 * @param id
	 * @return
	 * @throws XueWenServiceException
	 */
	public int getBoxPostSizeById(String id)throws XueWenServiceException{
		BoxPost boxPost=boxPostTemplate.getBoxPostSizeById(id);
		if(boxPost !=null){
			return boxPost.getSize();
		}else{
			return 0;
		}
	}
	
	/**
	 * 
	 * @Title: findByName
	 * @Description: 通过name获取 豆腐块
	 * @param name
	 * @return List<BoxPost>
	 * @throws
	 */
	public BoxPost findByName(String name){
		return boxPostRepository.findOneByEnglishName(name);
	}
	
	
	

}

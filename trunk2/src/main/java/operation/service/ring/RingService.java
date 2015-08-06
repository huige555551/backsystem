package operation.service.ring;


import operation.pojo.ring.Ring;
import operation.pojo.user.User;
import operation.repo.ring.RingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class RingService {
	@Autowired
	private RingRepository ringRepository;
	
	/**
	 * 计算擂台积分
	 * @param user
	 * @param type
	 */
	public void addRingScore(User user,String type){
		//判断该用户是否参与过擂台
		Ring ring = ringRepository.findByUser(user.getId());
		long time = System.currentTimeMillis();
		if(ring ==null){
			if(type.equals("1")){//擂台话题创建
				ring = new Ring();
				ring.setScore(5);
				ring.setUser(user.getId());
				ring.setCtime(time);
				ring.setUtime(time);
				ring.setUserLogo(user.getLogoURL());
				ring.setNikeName(user.getNickName());
				ringRepository.save(ring);
			}
		}else{
			if(type.equals("1")){//擂台话题创建
				ring.setScore(ring.getScore()+5);
				ringRepository.save(ring);
			}
			
		}
	}
	/**
	 * 获得擂台积分排名
	 * @return
	 */
	public Page<Ring> getRingScore(Pageable pageable){
		Page<Ring> ring = ringRepository.findAll(pageable);
		return ring;
	}
}

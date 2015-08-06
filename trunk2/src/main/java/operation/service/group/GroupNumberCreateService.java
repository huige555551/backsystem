package operation.service.group;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import operation.pojo.idgen.IdGen;
import operation.pojo.user.User;
import operation.repo.idgen.IdGenRespository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import tools.Config;

@Service
@Component
public class GroupNumberCreateService  implements InitializingBean{
	private static final Logger logger=Logger.getLogger(GroupNumberCreateService.class);

	@Autowired
	public IdGenRespository idGenRespository;
	
	public void initNumber(){
		List<IdGen> idGen = idGenRespository.findAll();
		if(idGen == null || idGen.size() ==0){
			Config.counter.set(10000);
		}
		else{
		long number = idGen.get(0).getNumber();
		Config.counter.set(number);
		}
		//logger.info("map中元素长度:"+Config.counter.size());

	}
	public void saveNumber(long l){
		List<IdGen> idGen = idGenRespository.findAll();
		idGen.get(0).setNumber(l);
		idGenRespository.save(idGen);
		//logger.info("map中元素长度:"+Config.counter.size());
	}
	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		this.initNumber();
	}

}

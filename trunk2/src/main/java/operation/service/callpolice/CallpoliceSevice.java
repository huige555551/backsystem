package operation.service.callpolice;

import operation.pojo.callpolice.Callpolice;
import operation.repo.callpolice.callpoliceReposity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CallpoliceSevice {
	@Autowired
	private callpoliceReposity callpoliceReposity;
	
	
	
	public boolean save(Callpolice callpolice){
		 try {
			callpoliceReposity.save(callpolice);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		 
	}
	public Page<Callpolice> findcallpolice(String type,Pageable page){
		
		return callpoliceReposity.findAllByType(type, page);
	}
	public boolean delete(String id) {
		 try {
			callpoliceReposity.delete(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}

}

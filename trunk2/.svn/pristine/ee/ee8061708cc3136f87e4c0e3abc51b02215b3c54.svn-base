package operation.repo.sms;

import java.util.List;

import operation.pojo.sms.Sms;
import operation.pojo.user.MsgCode;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SmsRepository extends MongoRepository<Sms, String>{
	List<Sms> findOneByPhoneNumAndCtimeGreaterThan(String phoneNum,long time);
	Sms findOneByPhoneNum(String phoneNum);
	int countByPhoneNumAndTypeAndCtimeGreaterThan(String phoneNum,String type,long time);
	List<Sms> findByCtimeLessThan(long time);
	Sms findOneByPhoneNumAndTypeAndCheckedAndCheckTimeAndCtimeGreaterThan(String phoneNum,String type,int checked,int checktime,long time);
	Sms findOneByPhoneNumAndTypeAndCheckedAndUtimeGreaterThan(String phoneNum,String type,int checked,long time,Sort st);
}

package operation.repo.user;

import java.util.List;

import operation.pojo.user.MsgCode;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MsgCodeRepository extends MongoRepository<MsgCode, String> {

	MsgCode findOneByPhoneNumAndUtimeGreaterThan(String phoneNum,long time);
	MsgCode findOneByPhoneNum(String phoneNum);
	List<MsgCode> findByUtimeLessThan(long time);
}

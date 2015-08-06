package operation.repo.file;

import java.util.List;

import operation.pojo.file.FileOperationInfo;
import operation.pojo.user.User;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileOperationInfoRepository extends MongoRepository<FileOperationInfo, String>{

	FileOperationInfo findOneById(String id);
	List<FileOperationInfo>  findByIdIn(List<Object> fileOperationInfo);
	
	//20140911新增加我的收藏
	List<FileOperationInfo>  findByUploadUser(String userid);
}

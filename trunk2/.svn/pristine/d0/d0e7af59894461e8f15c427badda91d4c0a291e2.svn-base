package operation.repo.file;

import operation.pojo.file.FileOperation;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileOperationRepository extends MongoRepository<FileOperation, String>{

	FileOperation findByUserAndFileOperationInfo(String useId,String fileOperationInfoId);
}

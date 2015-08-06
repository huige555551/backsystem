package operation.repo.file;

import operation.pojo.file.FileStoreInfo;
import operation.pojo.group.XueWenGroup;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileStoreInfoRepository extends MongoRepository<FileStoreInfo, String> {

	FileStoreInfo findOneByFileMD5(String fileMD5);
	FileStoreInfo findOneById(String fileId);
}

package operation.service.version;

import java.util.List;

import operation.exception.XueWenServiceException;
import operation.pojo.version.YunXueTangVersion;
import operation.repo.version.VersionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import tools.Config;

@Service
@Component
@EnableScheduling
public class VersionService {
	@Autowired
	public VersionRepository versionRepository;

	/**
	 * 判断是否有新版本
	 * 
	 * @return
	 */
	public YunXueTangVersion checkVersion(Sort st,
			YunXueTangVersion yunXueTangversion) throws XueWenServiceException {
		String device = yunXueTangversion.getDevice();
		if (device == null || "".equals(device)) {
			device = "iphone";
		}
		List<YunXueTangVersion> versionList = versionRepository.findByDevice(
				device, st);
		YunXueTangVersion version = null;
		if (null != versionList) {
			if (versionList.size() > 0) {
				version = versionList.get(0);
				String dbVersion = version.getVersionId();
				String appVersion = yunXueTangversion.getVersionId();
				int result = dbVersion.compareTo(appVersion);
				if (result > 0) {
					version.setSatue(1);
					return version;
				}
				// if(!version.getVersionId().equals(
				// yunXueTangversion.getVersionId())){
				// version.setSatue(0);
				// return version;
				// }
				else {
					yunXueTangversion.setSatue(0);
					return yunXueTangversion;
				}
			}
		} else {
			throw new XueWenServiceException(Config.STATUS_203, Config.MSG_203,
					null);
		}
		return null;

	}

	/**
	 * 加入新版本
	 * 
	 * @return
	 */
	public YunXueTangVersion save(YunXueTangVersion yunXueTangVersion)
			throws XueWenServiceException {
		YunXueTangVersion result = versionRepository
				.findByVersionIdAndDevice(yunXueTangVersion.getVersionId(),
						yunXueTangVersion.getDevice());
		if (result != null) {
			throw new XueWenServiceException(Config.STATUS_201,
					Config.MSG_VERSION_201, null);
		}
		YunXueTangVersion version = versionRepository.save(yunXueTangVersion);
		if (null != version) {
			return version;
		} else {
			throw new XueWenServiceException(Config.STATUS_203, Config.MSG_203,
					null);
		}

	}
	
	
	/**
	 * 查询所有列表
	 * 
	 * @return
	 */
	public Page<YunXueTangVersion> versionList(Pageable page) throws XueWenServiceException {
		Page<YunXueTangVersion> result = versionRepository.findAll(page);
		return result;
		

	}

}

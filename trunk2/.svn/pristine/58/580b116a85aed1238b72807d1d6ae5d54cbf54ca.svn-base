package operation.service.email;

import operation.pojo.email.MailCode;
import operation.repo.email.MailCodeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tools.Config;

@Service
@Component
public class MailCodeService {
	@Autowired
	private MailCodeRepo mailCodeRepo;

	public void save(MailCode mailCode) {
		mailCodeRepo.save(mailCode);
	}

	/**
	 * 
	 * @Title: findOneByEmail
	 * @auther Tangli
	 * @Description: 取出最新一条验证码
	 * @param email
	 * @return MailCode
	 * @throws
	 */
	public MailCode findOneByEmail(String email) {
		return mailCodeRepo.findOneByEmail(email, new Sort(Direction.DESC,
				"ctime"));
	}

	/**
	 * 
	 * @Title: insert
	 * @auther Tangli
	 * @Description: 新增
	 * @param code
	 * @param email
	 *            void
	 * @throws
	 */
	public void insert(String code, String email) {
		MailCode mailCode = new MailCode();
		mailCode.setCode(code);
		mailCode.setEmail(email);
		mailCode.setCtime(System.currentTimeMillis());
		mailCode.setExpressTime(System.currentTimeMillis()
				+ Config.MAILCODE_DEFAUT_OUTTIME);
		save(mailCode);
	}

}

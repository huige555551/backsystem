package operation.service.util;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import operation.exception.XueWenServiceException;
import operation.pojo.user.ResponseUser;
import operation.pojo.user.User;
import tools.Config;
@Service
@Component

public class ObjCopyPropsService {

	
	/**
	 * 利用反射实现对象之间属性复制
	 * @param from
	 * @param to
	 */
	public Object copyProperties(Object from, Object to) throws XueWenServiceException {
		return copyPropertiesExclude(from, to, null);
	}
	
	/**
	 * 复制对象属性
	 * @param from
	 * @param to
	 * @param excludsArray 排除属性列表
	 * @throws Exception
	 */
	public Object copyPropertiesExclude(Object from, Object to, String[] excludsArray) throws XueWenServiceException {
		try {
			List<String> excludesList = null;
			if(excludsArray != null && excludsArray.length > 0) {
				excludesList = Arrays.asList(excludsArray);	//构造列表对象
			}
			Method[] fromMethods = from.getClass().getDeclaredMethods();
			Method[] toMethods = to.getClass().getDeclaredMethods();
			Method fromMethod = null, toMethod = null;
			String fromMethodName = null, toMethodName = null;
			for (int i = 0; i < fromMethods.length; i++) {
				fromMethod = fromMethods[i];
				fromMethodName = fromMethod.getName();
				if (!fromMethodName.contains("get"))
					continue;
				//排除列表检测
				if(excludesList != null && excludesList.contains(fromMethodName.substring(3).toLowerCase())) {
					continue;
				}
				toMethodName = "set" + fromMethodName.substring(3);
				toMethod = findMethodByName(toMethods, toMethodName);
				if (toMethod == null)
					continue;
				Object value = fromMethod.invoke(from, new Object[0]);
				if(value == null)
					continue;
				//集合类判空处理
				if(value instanceof Collection) {
					@SuppressWarnings("unchecked")
					Collection<Object> newValue = (Collection<Object>)value;
					if(newValue.size() <= 0)
						continue;
				}
				toMethod.invoke(to, new Object[] {value});
			}
			return to;
		} catch (Exception e){
			e.printStackTrace();
			throw new XueWenServiceException(Config.STATUS_500,Config.MSG_500,null);
		}
	}
	
	/**
	 * 对象属性值复制，仅复制指定名称的属性值
	 * @param from
	 * @param to
	 * @param includsArray
	 * @throws Exception
	 */
	public Object copyPropertiesInclude(Object from, Object to, String[] includsArray) throws XueWenServiceException {
		try {
			List<String> includesList = null;
			if(includsArray != null && includsArray.length > 0) {
				includesList = Arrays.asList(includsArray);	//构造列表对象
			} else {
				return null;
			}
			Method[] fromMethods = from.getClass().getDeclaredMethods();
			Method[] toMethods = to.getClass().getDeclaredMethods();
			Method fromMethod = null, toMethod = null;
			String fromMethodName = null, toMethodName = null;
			for (int i = 0; i < fromMethods.length; i++) {
				fromMethod = fromMethods[i];
				fromMethodName = fromMethod.getName();
				if (!fromMethodName.contains("get"))
					continue;
				//排除列表检测
				String str = fromMethodName.substring(3);
				if(!includesList.contains(str.substring(0,1).toLowerCase() + str.substring(1))) {
					continue;
				}
				toMethodName = "set" + fromMethodName.substring(3);
				toMethod = findMethodByName(toMethods, toMethodName);
				if (toMethod == null)
					continue;
				Object value = fromMethod.invoke(from, new Object[0]);
				if(value == null)
					continue;
				//集合类判空处理
				if(value instanceof Collection) {
					@SuppressWarnings("unchecked")
					Collection<Object> newValue = (Collection<Object>)value;
					if(newValue.size() <= 0)
						continue;
				}
				toMethod.invoke(to, new Object[] {value});
			}
			return to;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new XueWenServiceException(Config.STATUS_500,Config.MSG_500,null);
		}
	}
	/**
	 * 从方法数组中获取指定名称的方法
	 * 
	 * @param methods
	 * @param name
	 * @return
	 */
	public  Method findMethodByName(Method[] methods, String name) {
		for (int j = 0; j < methods.length; j++) {
			if (methods[j].getName().equals(name))
				return methods[j];
		}
		return null;
	}
	
	public static void main(String[] args) {
		User user1=new User();
		user1.setId("123444555");
		user1.setEmail("163.com");
		user1.setUserName("hjn01");
		user1.setLogoURL("11111111");
		user1.setSex("1");
        user1.setPhoneNumber("1111111111111");
        user1.setIntro("243423");
        user1.setCtime(Long.parseLong("11111"));
        user1.setLogintime(Long.parseLong("2222222"));
    	user1.setLogoURL("213434");
    	
		ResponseUser rsu=new ResponseUser();
		System.out.println("=========================old user1:"+user1.toString());
		System.out.println("=========================old user2:"+rsu.toString());
		
		try {
			rsu=(ResponseUser)new ObjCopyPropsService().copyPropertiesExclude(user1, rsu, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("=========================old user1:"+user1.toString());
		System.out.println("=========================old user2:"+rsu.toString());
	}
}

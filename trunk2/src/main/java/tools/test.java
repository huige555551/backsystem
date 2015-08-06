package tools;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;





public class test {
	public static String getAdrr() {
		
		
//		FileInputStream fis = null;
//		try {
//		fis = new FileInputStream(Config.openfireProperty);
//		Properties prop = new Properties();
//		prop.load(fis);
//		String ss = prop.getProperty("openfireAdrr");// 得到文件参数值	
//		System.out.println(ss);
//		return ss;
//		     } finally {
//		try {
//		fis.close();
//		} catch (IOException ex) {
//		System.out.println(ex.getMessage());
//		}

	//}
	//}
		return null;
	}
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		List<String> strList = new ArrayList<String>();
		List<String> strList2 = new ArrayList<String>();
		for(int i = 0; i < 10; i ++) {
			//strList.add("aaa>>" + i);
			strList2.add((10 - i)+"");
		}
		strList.add(19+"");
		strList.add( 2+"");
		//求出交集
		if(strList2.retainAll(strList))
		{
			System.out.println("交集大小：" + strList2.size());
			
			for(int i = 0; i < strList2.size(); i++) {
				System.out.println(strList2.get(i));
			}
			
		}
	}
}

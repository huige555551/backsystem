package tools;

import java.util.ArrayList;
import java.util.List;


public class StringToList {
	
	public static List<Object> tranfer (String str){
		String[] sourceStrArray = str.split(",");
		List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < sourceStrArray.length; i++) {
            list.add(sourceStrArray[i]);
        }
		return list;
	}
	
	/**
	 * 将Object list 转化为String list
	 * @param list
	 * @return
	 */
	public static List<String> tranfer(List<Object> list){
		List<String> strs=new ArrayList<String>();
		for(Object str:list){
			strs.add(str.toString());
		}
		return strs;
	}
	
}

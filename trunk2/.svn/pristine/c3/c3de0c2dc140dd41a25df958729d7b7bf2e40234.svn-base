package tools;

import java.util.Comparator;

import net.sf.json.JSONObject;


public class ListComparator implements Comparator<Object>{
    private String keyName;
    public ListComparator(String key) {
    		keyName=key;
    }
	@Override
	public int compare(Object o1, Object o2) {
		JSONObject a=(JSONObject)o1;
		JSONObject b=(JSONObject)o2;
		if(a.getLong(keyName)<=b.getLong(keyName)){
			return 0;
		} else {
			return -1;
		}
	}

	
}

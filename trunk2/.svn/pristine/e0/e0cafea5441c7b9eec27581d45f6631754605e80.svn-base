package tools;

import java.lang.reflect.Method;
import java.util.Comparator;


public class ObjectComparator implements Comparator<Object>{
	
	private String fieldName;
	
	public ObjectComparator(){
		super();
	}
	
	public ObjectComparator(String fieldName){
		this.fieldName=fieldName;
	}

	@Override
	public int compare(Object o1, Object o2) {
		int c1=Integer.valueOf(getFieldValueByName(fieldName,o1).toString());
		int c2=Integer.valueOf(getFieldValueByName(fieldName,o2).toString());
		if(c1<=c2){
			return -1;
		} else {
			return 0;
		}
	}
	/**
	 * 依据反射获取对象中对应属性名的值
	 * @param fieldName
	 * @param o
	 * @return
	 */
	private Object getFieldValueByName(String fieldName, Object o) {  
        try {  
            String firstLetter = fieldName.substring(0, 1).toUpperCase();  
            String getter = "get" + firstLetter + fieldName.substring(1);  
            Method method = o.getClass().getMethod(getter, new Class[] {});  
            Object value = method.invoke(o, new Object[] {});  
            return value;  
        } catch (Exception e) {  
            System.out.println("属性不存在");  
            return null;  
        }  
    } 

//	@Override
//	public Comparator<Object> reversed() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Comparator<Object> thenComparing(Comparator<? super Object> other) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <U> Comparator<Object> thenComparing(
//			Function<? super Object, ? extends U> keyExtractor,
//			Comparator<? super U> keyComparator) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <U extends Comparable<? super U>> Comparator<Object> thenComparing(
//			Function<? super Object, ? extends U> keyExtractor) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Comparator<Object> thenComparingInt(
//			ToIntFunction<? super Object> keyExtractor) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Comparator<Object> thenComparingLong(
//			ToLongFunction<? super Object> keyExtractor) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Comparator<Object> thenComparingDouble(
//			ToDoubleFunction<? super Object> keyExtractor) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}

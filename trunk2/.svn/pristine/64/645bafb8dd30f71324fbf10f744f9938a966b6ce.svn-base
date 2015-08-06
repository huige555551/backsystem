package tools;

import java.math.BigDecimal;
import java.util.Random;

public class MathTools {

	/**
	 * 得到两个double值中较大的double值
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double compare(double d1,double d2){
		BigDecimal data1 = new BigDecimal(d1);  
	    BigDecimal data2 = new BigDecimal(d2);
	    if(data1.compareTo(data2)<=0){
	    	return data2.doubleValue();
	    }else{
	    	return data1.doubleValue();
	    }
	}
	
	/** 
     * 提供精确的加法运算。 
     * @param value1 被加数 
     * @param value2 加数 
     * @return 两个参数的和 
     */  
    public static double add(double value1, double value2) {  
        BigDecimal b1 = new BigDecimal(value1);  
        BigDecimal b2 = new BigDecimal(value2);  
        return b1.add(b2).doubleValue();  
    } 
    
    
    /** 
     * 提供（相对）精确的除法运算。 当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入。 
     *  
     * @param dividend 
     *            被除数 
     * @param divisor 
     *            除数 
     * @param scale 
     *            表示表示需要精确到小数点以后几位。 
     * @return 两个参数的商 
     */  
    public static Double div(double dividend, int count, Integer scale) {  
        if (scale < 0) {  
            throw new IllegalArgumentException(  
                    "The scale must be a positive integer or zero");  
        }  
        BigDecimal b1 = new BigDecimal(dividend);  
        BigDecimal b2 = new BigDecimal(count);  
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();  
    }
    
    /**
     * 判断double值是否大于1.0
     * @param d1
     * @return
     */
    public static boolean  compareToOne(double d1){
		BigDecimal data1 = new BigDecimal(d1);  
	    BigDecimal data2 = new BigDecimal(1);
	    if(data1.compareTo(data2)>=0){
	    	return true;
	    }else{
	    	return false;
	    }
	}
    
    public static int mul(int i,double d){
    	BigDecimal data1 = new BigDecimal(i);  
	    BigDecimal data2 = new BigDecimal(d);
	    return data1.multiply(data2).intValue();
    }
    
    public static void main(String[] args) {
		System.out.println(add(0.5,0.3));
		System.out.println(System.currentTimeMillis());
		System.out.println(compareToOne(1.1));
		System.out.println(mul(55555,0.33));
	}
    
    /**
	 * 获取固定区间的随机数
	 * @param min
	 * @param max
	 * @return
	 */
	public static int nextInt(final int min, final int max){

	    int tmp = Math.abs(new Random().nextInt());
	    return tmp % (max - min + 1) + min;
	}
}

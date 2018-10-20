# 方法一：通过String.substring()方法将最后的三位去掉

/** 
 * 获取精确到秒的时间戳 
 * @return 
 */  
public static int getSecondTimestamp(Date date){  
    if (null == date) {  
        return 0;  
    }  
    String timestamp = String.valueOf(date.getTime());  
    int length = timestamp.length();  
    if (length > 3) {  
        return Integer.valueOf(timestamp.substring(0,length-3));  
    } else {  
        return 0;  
    }  
}


#方法二：通过整除将最后的三位去掉

/ *
 * 获取精确到秒的时间戳 
  *@param date 
  *@return 
 */ 
public static int getSecondTimestampTwo(Date date){  
    if (null == date) {  
        return 0;  
    }  
    String timestamp = String.valueOf(date.getTime()/1000);  
    return Integer.valueOf(timestamp);  
} 








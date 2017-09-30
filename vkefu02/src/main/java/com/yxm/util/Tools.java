package com.yxm.util;

import java.text.SimpleDateFormat;
import java.util.UUID;

public class Tools {
    public static SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
	public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
	public static SimpleDateFormat timeRangeDateFormat = new SimpleDateFormat("HH:mm");
	
	/**
	 * 当前时间+已过随机生成的 长整形数字
	 * @return
	 */
	public static String genID(){
		return Base62.encode(getUUID()).toLowerCase() ;
	}
	
	public static String genIDByKey(String key){
		return Base62.encode(key).toLowerCase() ;
	}
	
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "") ;
	}
}

package cn.mldn.util.tools;

import cn.mldn.util.StringUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 此类负责VO类与JSON对象之间的转换处理
 */
public class ObjectToJSON {
    public static JSONObject convertorListToJSON(String name ,List<?> all) {
        JSONObject obj = new JSONObject() ;
        JSONArray array = new JSONArray() ;
        Iterator<?> iter = all.iterator() ;
        while(iter.hasNext()) {
            array.add(convertorObjectToJSON(iter.next())) ;
        }
        obj.put(name,array) ;
        return obj ;
    }

    public static JSONObject convertorListSplitToJSON(String name ,List<?> all,Integer allRecorders) {
        JSONObject obj = convertorListToJSON(name,all) ;
        obj.put("allRecorders",allRecorders) ;
        return obj ;
    }
    public static JSONObject convertorObjectToJSON(Object vo) {
        JSONObject obj = new JSONObject() ;
        // 取得全部的成员
        Field fields [] = vo.getClass().getDeclaredFields() ;
        for (int x = 0 ; x < fields.length ; x ++) {
            Field f = fields[x] ;
            String methodName = "get" + StringUtils.initcap(f.getName()) ;
            try {
                Method met = vo.getClass().getMethod(methodName);
                if("Date".equalsIgnoreCase(f.getType().getSimpleName())) {
                    Date date = (Date) met.invoke(vo) ;
                    obj.put(f.getName(), new SimpleDateFormat("yyyy-MM-dd").format(date));
                } else {
                    obj.put(f.getName(), met.invoke(vo));
                }
            } catch (Exception e) {}
        }
        return obj ;
    }
}

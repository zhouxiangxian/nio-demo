package cn.mldn.test;

import cn.mldn.util.tools.ObjectToJSON;
import cn.mldn.vo.News;

import java.util.Date;

public class TestJSON {
    public static void main(String[] args) {
        News vo = new News() ;
        vo.setNid(10);
        vo.setTitle("测试");
        vo.setContent("内容");
        vo.setPubdate(new Date());
        System.out.println(ObjectToJSON.convertorObjectToJSON(vo));
        
        
        
        
        
    }
}

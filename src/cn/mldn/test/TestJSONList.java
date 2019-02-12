package cn.mldn.test;

import cn.mldn.util.tools.ObjectToJSON;
import cn.mldn.vo.Item;
import cn.mldn.vo.News;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestJSONList {
    public static void main(String[] args) {
        List<News> all = new ArrayList<News>() ;
        for (int x = 0; x < 10; x++) {
            News vo = new News();
            vo.setNid(10 + x);
            vo.setTitle("测试 - " + x);
            vo.setContent("内容 - " + x);
            vo.setPubdate(new Date());
            Item item = new Item() ;
            item.setIid(1);
            vo.setItem(item);
            all.add(vo) ;
        }
        System.out.println(ObjectToJSON.convertorListToJSON("allNewses",all));
    }
}

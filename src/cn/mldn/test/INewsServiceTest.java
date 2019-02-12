package cn.mldn.test;

import cn.mldn.service.INewsService;
import cn.mldn.vo.Item;
import cn.mldn.vo.News;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by mldn on 2015/7/7.
 */
public class INewsServiceTest {
    private static ApplicationContext ctx;
    private static INewsService newsService;

    static {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        newsService = ctx.getBean("newsServiceImpl", INewsService.class);
    }

    @Test
    public void testInsert() throws Exception {
        News vo = new News() ;
        vo.setTitle("新闻标题 - " + System.currentTimeMillis());
        vo.setContent("新闻内容 - " + System.currentTimeMillis());
        vo.setPubdate(new Date());
        Item item = new Item() ;
        item.setIid(1);
        vo.setItem(item);
        TestCase.assertTrue(this.newsService.insert(vo));
    }

    @Test
    public void testCheckTitle() throws Exception {
        TestCase.assertTrue(this.newsService.checkTitle("hello"));
    }

    @Test
    public void testUpdate() throws Exception {
        News vo = new News() ;
        vo.setNid(1);
        vo.setTitle("恭喜大家毕业");
        vo.setContent("祝你们工作越来越好，加油！");
        Item item = new Item() ;
        item.setIid(2);
        vo.setItem(item);
        TestCase.assertTrue(this.newsService.update(vo));
    }

    @Test
    public void testDelete() throws Exception {
        List<Integer> ids = new ArrayList<Integer>() ;
        ids.add(2) ;
        TestCase.assertTrue(this.newsService.delete(ids));
    }

    @Test
    public void testList() throws Exception {
        Map<String,Object> map = this.newsService.list(1,2) ;
        System.out.println(map.get("newsCount"));
        System.out.println(map.get("allNewses"));
        TestCase.assertTrue(map.size() == 2);
    }

    @Test
    public void testGet() throws Exception {
        TestCase.assertTrue(this.newsService.get(1) != null);
    }
}
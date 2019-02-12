package cn.mldn.test;

import cn.mldn.service.IItemService;
import cn.mldn.vo.Item;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class IItemServiceTest {
    private static ApplicationContext ctx;
    private static IItemService itemService ;
    static {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        itemService = ctx.getBean("itemServiceImpl",IItemService.class) ;
    }

    @org.junit.Test
    public void testList() throws Exception {
        List<Item> all = itemService.list() ;
        System.out.println(all);
        TestCase.assertTrue(all.size() > 0);
    }
}
package cn.mldn.action;

import cn.mldn.service.INewsService;
import cn.mldn.util.action.DefaultAction;
import cn.mldn.vo.News;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/pages/news/*")
public class NewsAction extends DefaultAction {
    @Resource
    private INewsService newsService;

    @RequestMapping(value = "news_insert")
    public ModelAndView insert(News news) {
        news.setPubdate(new Date());
        ModelAndView mav = new ModelAndView(super.getResource("forward.page"));
        try {
            if (this.newsService.insert(news)) {
                super.setMsgAndPath(mav, "vo.insert.success", "news.insert.page");
            } else {
                super.setMsgAndPath(mav, "vo.insert.failure", "news.insert.page");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }

    @RequestMapping(value = "news_checkTitle")
    public void checkTitle(HttpServletResponse response, String title) {
        try {
            super.print(response, this.newsService.checkTitle(title));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "news_checkTitleForUpdate")
    public void checkTitleForUpdate(HttpServletResponse response, int nid, String title) {
        try {
            super.print(response, this.newsService.checkTitleForUpdate(nid, title));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "news_update")
    public void update(HttpServletResponse response, News news) {
        try {
            super.print(response, this.newsService.update(news));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "news_delete")
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        try {
            String result[] = request.getParameter("ids").split("\\|");
            List<Integer> all = new ArrayList<Integer>();
            for (int x = 0; x < result.length; x++) {
                all.add(Integer.parseInt(result[x]));
            }
            if (all.size() > 0) {
                super.print(response, this.newsService.delete(all));
            } else {
                super.print(response, "false");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "news_list")
    public void list(HttpServletRequest request, HttpServletResponse response) {
        super.handSplit(request);   // 处理分页参数
        try {
            Map<String, Object> map = this.newsService.list(super.getCurrentPage(), super.getLineSize());
            List<News> all = (List<News>) map.get("allNewses");
            Integer allRecorders = (Integer) map.get("newsCount");
            super.printListSplitJson(response, "allNewses", all, allRecorders);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getText() {
        return "新闻";
    }
}

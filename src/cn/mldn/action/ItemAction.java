package cn.mldn.action;

import cn.mldn.service.IItemService;
import cn.mldn.util.action.DefaultAction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/pages/item/*")
public class ItemAction extends DefaultAction {
    @Resource
    private IItemService itemService;

    @Override
    public String getText() {
        return "新闻类型";
    }
    @RequestMapping(value="item_list")
    public void list(HttpServletResponse response) {
        try {
            super.printListJson(response, "allItems", this.itemService.list());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

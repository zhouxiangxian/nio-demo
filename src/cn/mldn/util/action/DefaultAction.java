package cn.mldn.util.action;

import cn.mldn.util.tools.ObjectToJSON;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public abstract class DefaultAction {
    @Resource
    private MessageSource messageSource;
    private int currentPage = 1 ;
    private int lineSize = 10;     // 默认每页显示10行内容

    /**
     * 处理分页操作数据
     *
     * @param request request对象，可以取得所有的请求内容
     */
    public void handSplit(HttpServletRequest request) {
        try {   // 接收当前所在的页数
            this.currentPage = Integer.parseInt(request.getParameter("cp"));
        } catch (Exception e) {
        }
        try {   // 接收当前每页显示的数据量
            this.lineSize = Integer.parseInt(request.getParameter("ls"));
        } catch (Exception e) {
        }
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getLineSize() {
        return lineSize;
    }

    /**
     * 根据key取得资源的信息
     *
     * @param key   资源文件的key
     * @param param 所需要设置的参数
     * @return
     */
    public String getResource(String key, String... param) {
        return this.messageSource.getMessage(key, param, Locale.getDefault());
    }

    public void print(HttpServletResponse response,Object msg) {
        try {
            response.getWriter().print(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置跳转的信息以及路径
     *
     * @param mav
     * @param msg
     * @param path
     */
    public void setMsgAndPath(ModelAndView mav, String msg, String path) {
        if (mav != null) {
            if (this.getText() != null) {
                String[] result = this.getText().split("\\|");
                mav.addObject("msg", this.messageSource.getMessage(msg, result, Locale.getDefault()));
            } else {
                mav.addObject("msg", this.messageSource.getMessage(msg, null, Locale.getDefault()));
            }
            mav.addObject("path", this.messageSource.getMessage(path, null, Locale.getDefault()));
        }
    }

    /**
     * 将对象变为JSON输出
     * @param response
     * @param vo
     */
    public void printObjectJson(HttpServletResponse response, Object vo) {
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().print(ObjectToJSON.convertorObjectToJSON(vo));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将List集合变为对象输出
     * @param response
     * @param name
     * @param all
     */
    public void printListJson(HttpServletResponse response, String name, List<?> all) {
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().print(ObjectToJSON.convertorListToJSON(name, all));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printListSplitJson(HttpServletResponse response, String name, List<?> all,Integer allReocrders) {
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().print(ObjectToJSON.convertorListSplitToJSON(name, all, allReocrders));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
    }

    /**
     * 取得资源读取的参数的内容，内容之间使用“|”分割
     *
     * @return
     */
    public abstract String getText();
}

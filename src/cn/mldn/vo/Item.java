package cn.mldn.vo;

import java.io.Serializable;
import java.util.List;

public class Item implements Serializable {
    private Integer iid ;
    private String title ;
    private List<News> newses ;

    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<News> getNewses() {
        return newses;
    }

    public void setNewses(List<News> newses) {
        this.newses = newses;
    }

    @Override
    public String toString() {
        return "Item{" +
                "iid=" + iid +
                ", title='" + title + '\'' +
                '}';
    }
}

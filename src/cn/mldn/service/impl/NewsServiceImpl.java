package cn.mldn.service.impl;

import cn.mldn.dao.INewsDAO;
import cn.mldn.service.INewsService;
import cn.mldn.vo.News;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NewsServiceImpl implements INewsService {
    @Resource
    private INewsDAO newsDAO;

    @Override
    public boolean insert(News vo) throws Exception {
        if (this.newsDAO.findByTitle(vo.getTitle()) == null) { // 没有重复的标题
            return this.newsDAO.doCreate(vo);   // 现在允许保存
        }
        return false;
    }

    @Override
    public boolean checkTitle(String title) throws Exception {
        return this.newsDAO.findByTitle(title) == null;
    }
    @Override
    public boolean checkTitleForUpdate(int nid,String title) throws Exception {
        return this.newsDAO.findByTitleForUpdate(nid,title) == null;
    }
    @Override
    public boolean update(News vo) throws Exception {
        if (this.newsDAO.findByTitleForUpdate(vo.getNid(), vo.getTitle()) == null) { // 没有重复的标题
            return this.newsDAO.doUpdate(vo);   // 现在允许保存
        }
        return false;
    }

    @Override
    public boolean delete(List<Integer> ids) throws Exception {
        return this.newsDAO.doRemoveBatch(ids);
    }

    @Override
    public Map<String, Object> list(int currentPage, int lineSize) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("allNewses", this.newsDAO.findAllSplit(currentPage, lineSize));
        map.put("newsCount", this.newsDAO.getAllCount());
        return map;
    }

    @Override
    public News get(int nid) throws Exception {
        return this.newsDAO.findById(nid);
    }
}

package cn.mldn.dao.impl;

import cn.mldn.dao.INewsDAO;
import cn.mldn.vo.News;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class NewsDAOImpl extends SqlSessionDaoSupport implements INewsDAO {
    @Autowired
    public NewsDAOImpl(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public News findByTitle(String title) throws Exception {
        return super.getSqlSession().selectOne("NewsNS.findByTitle", title);
    }

    @Override
    public News findByTitleForUpdate(Integer nid, String title) throws Exception {
        Map<String,Object> map = new HashMap<String,Object>() ;
        map.put("title",title) ;
        map.put("nid",nid) ;
        return super.getSqlSession().selectOne("NewsNS.findByTitleForUpdate", map);
    }

    @Override
    public boolean doCreate(News vo) throws Exception {
        return super.getSqlSession().insert("NewsNS.doCreate", vo) > 0;
    }

    @Override
    public boolean doUpdate(News vo) throws Exception {
        return super.getSqlSession().update("NewsNS.doUpdate", vo) > 0;
    }

    @Override
    public boolean doRemoveBatch(List<Integer> ids) throws Exception {
        return super.getSqlSession().delete("NewsNS.doRemove", ids) > 0;
    }

    @Override
    public List<News> findAll() throws Exception {
        return super.getSqlSession().selectList("NewsNS.findAll");
    }

    @Override
    public List<News> findAllSplit(Integer currentPage, Integer lineSize) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", (currentPage - 1) * lineSize);
        map.put("lineSize", lineSize);
        return super.getSqlSession().selectList("NewsNS.findAllBySplit", map);
    }

    @Override
    public News findById(Integer id) throws Exception {
        return super.getSqlSession().selectOne("NewsNS.findById", id);
    }

    @Override
    public Integer getAllCount() throws Exception {
        return super.getSqlSession().selectOne("NewsNS.getAllCount");
    }
}

package cn.mldn.dao.impl;

import cn.mldn.dao.IItemDAO;
import cn.mldn.vo.Item;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemDAOImpl extends SqlSessionDaoSupport implements IItemDAO {
    @Autowired
    public ItemDAOImpl(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
    @Override
    public boolean doCreate(Item vo) throws Exception {
        return false;
    }

    @Override
    public boolean doUpdate(Item vo) throws Exception {
        return false;
    }

    @Override
    public boolean doRemoveBatch(List<Integer> ids) throws Exception {
        return false;
    }

    @Override
    public List<Item> findAll() throws Exception {
        return super.getSqlSession().selectList("ItemNS.findAll");
    }

    @Override
    public List<Item> findAllSplit(Integer currentPage, Integer lineSize) throws Exception {
        return null;
    }

    @Override
    public Item findById(Integer id) throws Exception {
        return null;
    }

    @Override
    public Integer getAllCount() throws Exception {
        return null;
    }
}

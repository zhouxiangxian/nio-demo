package cn.mldn.service.impl;

import cn.mldn.dao.IItemDAO;
import cn.mldn.service.IItemService;
import cn.mldn.vo.Item;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class ItemServiceImpl implements IItemService {
    @Resource
    private IItemDAO itemdao ;
    @Override
    public List<Item> list() throws Exception {
        return this.itemdao.findAll() ;
    }
}

package cn.mldn.dao;

import cn.mldn.vo.News;

public interface INewsDAO extends IDAO<Integer,News>{
    /**
     * 根据标题查看是否存在有信息
     * @param title 要查询的标题
     * @return 如果有对应的标题内容返回VO对象，否则返回null
     * @throws Exception
     */
    public News findByTitle(String title) throws Exception ;

    public News findByTitleForUpdate(Integer nid,String title) throws Exception ;
}

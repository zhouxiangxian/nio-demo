package cn.mldn.service;

import cn.mldn.vo.News;

import java.util.List;
import java.util.Map;

public interface INewsService {
    /**
     * 增加新闻数据，本操作执行以下功能：<br>
     *     <li>执行INewsDAO.findByTitle()方法判断标题是否存在！</li>
     *     <li>执行INewsDAO.doCreate()方法实现数据增加</li>
     * @param vo
     * @return 增加成功返回true，否则返回false
     * @throws Exception
     */
    public boolean insert(News vo) throws Exception ;

    /**
     * 检查标题信息是否存在，这个的操作是基于News对象的为null的判断
     * @param title 新闻标题
     * @return 如果存在则返回false，否则返回true
     * @throws Exception
     */
    public boolean checkTitle(String title) throws Exception ;

    public boolean checkTitleForUpdate(int nid,String title) throws Exception ;

    /**
     * 修改新闻数据，同样需要检查标题是否存在，之后再执行更新操作
     * @param vo
     * @return 更新成功返回true，否则返回false
     * @throws Exception
     */
    public boolean update(News vo) throws Exception ;

    /**
     * 新闻数据的批量删除操作
     * @param ids
     * @return
     * @throws Exception
     */
    public boolean delete(List<Integer> ids) throws Exception ;

    /**
     * 实现新闻数据的列表显示
     * @param currentPage 当前所在页
     * @param lineSize 每页显示的数据行
     * @return 返回Map集合，包含有以下内容：<br>
     *     <li>key = allNewses，value = INewsDAO.findAll(currentPage,lineSize)</li>
     *     <li>key = newsCount，value = INewsDAO.getAllCount()</li?>
     * @throws Exception
     */
    public Map<String,Object> list(int currentPage,int lineSize) throws Exception ;

    /**
     * 查询单个新闻数据
     * @param nid 新闻编号
     * @return 如果有内容则返回VO对象，否则返回null
     * @throws Exception
     */
    public News get(int nid) throws Exception ;
}

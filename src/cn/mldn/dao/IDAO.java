package cn.mldn.dao;

import cn.mldn.vo.News;

import java.util.List;

public interface IDAO<K,V> {
    /**
     * 实现数据的增加操作
     * @param vo 要增加的数据对象
     * @return 如果增加成功返回true，否则返回false
     * @throws Exception
     */
    public boolean doCreate(V vo) throws Exception ;

    /**
     * 实现数据的修改操作
     * @param vo
     * @return
     * @throws Exception
     */
    public boolean doUpdate(V vo) throws Exception ;

    /**
     * 实现数据的批量删除操作
     * @param ids 包含要删除数据的ID信息
     * @return
     * @throws Exception
     */
    public boolean doRemoveBatch(List<K> ids) throws Exception ;

    /**
     * 查询全部数据
     * @return 如果没有数据返回则集合的长度为0
     * @throws Exception
     */
    public List<V> findAll() throws Exception ;

    /**
     * 分页查询全部数据，要求计算出开始页
     * @param currentPage 当前页
     * @param lineSize 每页显示的数据量
     * @return 如果没有数据返回则集合的长度为0
     * @throws Exception
     */
    public List<V> findAllSplit(Integer currentPage,Integer lineSize) throws Exception ;

    /**
     * 根据ID查询一个数据的信息
     * @param id
     * @return 如果现在查询到内容则以VO对象返回，否则返回null
     * @throws Exception
     */
    public V findById(K id) throws Exception ;

    /**
     * 查询全部数据量，使用COUNT()函数
     * @return 如果没有内容返回0，有内容返回一个统计结果
     * @throws Exception
     */
    public Integer getAllCount() throws Exception ;
}

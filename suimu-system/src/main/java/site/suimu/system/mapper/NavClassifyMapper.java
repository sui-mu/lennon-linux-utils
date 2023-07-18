package site.suimu.system.mapper;

import java.util.List;
import site.suimu.system.domain.NavClassify;

/**
 * 导航分类Mapper接口
 * 
 * @author lennon
 * @date 2023-07-18
 */
public interface NavClassifyMapper 
{
    /**
     * 查询导航分类
     * 
     * @param id 导航分类主键
     * @return 导航分类
     */
    public NavClassify selectNavClassifyById(Long id);

    /**
     * 查询导航分类列表
     * 
     * @param navClassify 导航分类
     * @return 导航分类集合
     */
    public List<NavClassify> selectNavClassifyList(NavClassify navClassify);

    /**
     * 新增导航分类
     * 
     * @param navClassify 导航分类
     * @return 结果
     */
    public int insertNavClassify(NavClassify navClassify);

    /**
     * 修改导航分类
     * 
     * @param navClassify 导航分类
     * @return 结果
     */
    public int updateNavClassify(NavClassify navClassify);

    /**
     * 删除导航分类
     * 
     * @param id 导航分类主键
     * @return 结果
     */
    public int deleteNavClassifyById(Long id);

    /**
     * 批量删除导航分类
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNavClassifyByIds(Long[] ids);
}

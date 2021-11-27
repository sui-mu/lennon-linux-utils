package site.suimu.system.mapper;

import java.util.List;
import site.suimu.system.domain.NavClassify;

/**
 * 分类Mapper接口
 * 
 * @author ruoyi
 * @date 2021-11-27
 */
public interface NavClassifyMapper 
{
    /**
     * 查询分类
     * 
     * @param id 分类主键
     * @return 分类
     */
    public NavClassify selectNavClassifyById(Long id);

    /**
     * 查询分类列表
     * 
     * @param navClassify 分类
     * @return 分类集合
     */
    public List<NavClassify> selectNavClassifyList(NavClassify navClassify);

    /**
     * 新增分类
     * 
     * @param navClassify 分类
     * @return 结果
     */
    public int insertNavClassify(NavClassify navClassify);

    /**
     * 修改分类
     * 
     * @param navClassify 分类
     * @return 结果
     */
    public int updateNavClassify(NavClassify navClassify);

    /**
     * 删除分类
     * 
     * @param id 分类主键
     * @return 结果
     */
    public int deleteNavClassifyById(Long id);

    /**
     * 批量删除分类
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNavClassifyByIds(Long[] ids);
}

package site.suimu.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.suimu.system.mapper.NavClassifyMapper;
import site.suimu.system.domain.NavClassify;
import site.suimu.system.service.INavClassifyService;

/**
 * 导航分类Service业务层处理
 * 
 * @author lennon
 * @date 2023-07-18
 */
@Service
public class NavClassifyServiceImpl implements INavClassifyService 
{
    @Autowired
    private NavClassifyMapper navClassifyMapper;

    /**
     * 查询导航分类
     * 
     * @param id 导航分类主键
     * @return 导航分类
     */
    @Override
    public NavClassify selectNavClassifyById(Long id)
    {
        return navClassifyMapper.selectNavClassifyById(id);
    }

    /**
     * 查询导航分类列表
     * 
     * @param navClassify 导航分类
     * @return 导航分类
     */
    @Override
    public List<NavClassify> selectNavClassifyList(NavClassify navClassify)
    {
        return navClassifyMapper.selectNavClassifyList(navClassify);
    }

    /**
     * 新增导航分类
     * 
     * @param navClassify 导航分类
     * @return 结果
     */
    @Override
    public int insertNavClassify(NavClassify navClassify)
    {
        return navClassifyMapper.insertNavClassify(navClassify);
    }

    /**
     * 修改导航分类
     * 
     * @param navClassify 导航分类
     * @return 结果
     */
    @Override
    public int updateNavClassify(NavClassify navClassify)
    {
        return navClassifyMapper.updateNavClassify(navClassify);
    }

    /**
     * 批量删除导航分类
     * 
     * @param ids 需要删除的导航分类主键
     * @return 结果
     */
    @Override
    public int deleteNavClassifyByIds(Long[] ids)
    {
        return navClassifyMapper.deleteNavClassifyByIds(ids);
    }

    /**
     * 删除导航分类信息
     * 
     * @param id 导航分类主键
     * @return 结果
     */
    @Override
    public int deleteNavClassifyById(Long id)
    {
        return navClassifyMapper.deleteNavClassifyById(id);
    }
}

package site.suimu.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.suimu.system.mapper.NavClRelMapper;
import site.suimu.system.domain.NavClRel;
import site.suimu.system.service.INavClRelService;

/**
 * 链接分类关系Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-11-27
 */
@Service
public class NavClRelServiceImpl implements INavClRelService 
{
    @Autowired
    private NavClRelMapper navClRelMapper;

    /**
     * 查询链接分类关系
     * 
     * @param linkId 链接分类关系主键
     * @return 链接分类关系
     */
    @Override
    public NavClRel selectNavClRelByLinkId(Long linkId)
    {
        return navClRelMapper.selectNavClRelByLinkId(linkId);
    }

    /**
     * 查询链接分类关系列表
     * 
     * @param navClRel 链接分类关系
     * @return 链接分类关系
     */
    @Override
    public List<NavClRel> selectNavClRelList(NavClRel navClRel)
    {
        return navClRelMapper.selectNavClRelList(navClRel);
    }

    /**
     * 新增链接分类关系
     * 
     * @param navClRel 链接分类关系
     * @return 结果
     */
    @Override
    public int insertNavClRel(NavClRel navClRel)
    {
        return navClRelMapper.insertNavClRel(navClRel);
    }

    /**
     * 修改链接分类关系
     * 
     * @param navClRel 链接分类关系
     * @return 结果
     */
    @Override
    public int updateNavClRel(NavClRel navClRel)
    {
        return navClRelMapper.updateNavClRel(navClRel);
    }

    /**
     * 批量删除链接分类关系
     * 
     * @param linkIds 需要删除的链接分类关系主键
     * @return 结果
     */
    @Override
    public int deleteNavClRelByLinkIds(Long[] linkIds)
    {
        return navClRelMapper.deleteNavClRelByLinkIds(linkIds);
    }

    /**
     * 删除链接分类关系信息
     * 
     * @param linkId 链接分类关系主键
     * @return 结果
     */
    @Override
    public int deleteNavClRelByLinkId(Long linkId)
    {
        return navClRelMapper.deleteNavClRelByLinkId(linkId);
    }
}

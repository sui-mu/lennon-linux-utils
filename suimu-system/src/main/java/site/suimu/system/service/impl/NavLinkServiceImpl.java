package site.suimu.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.suimu.system.mapper.NavLinkMapper;
import site.suimu.system.domain.NavLink;
import site.suimu.system.service.INavLinkService;

/**
 * 导航链接Service业务层处理
 * 
 * @author lennon
 * @date 2023-07-18
 */
@Service
public class NavLinkServiceImpl implements INavLinkService 
{
    @Autowired
    private NavLinkMapper navLinkMapper;

    /**
     * 查询导航链接
     * 
     * @param id 导航链接主键
     * @return 导航链接
     */
    @Override
    public NavLink selectNavLinkById(Long id)
    {
        return navLinkMapper.selectNavLinkById(id);
    }

    /**
     * 查询导航链接列表
     * 
     * @param navLink 导航链接
     * @return 导航链接
     */
    @Override
    public List<NavLink> selectNavLinkList(NavLink navLink)
    {
        return navLinkMapper.selectNavLinkList(navLink);
    }

    /**
     * 新增导航链接
     * 
     * @param navLink 导航链接
     * @return 结果
     */
    @Override
    public int insertNavLink(NavLink navLink)
    {
        return navLinkMapper.insertNavLink(navLink);
    }

    /**
     * 修改导航链接
     * 
     * @param navLink 导航链接
     * @return 结果
     */
    @Override
    public int updateNavLink(NavLink navLink)
    {
        return navLinkMapper.updateNavLink(navLink);
    }

    /**
     * 批量删除导航链接
     * 
     * @param ids 需要删除的导航链接主键
     * @return 结果
     */
    @Override
    public int deleteNavLinkByIds(Long[] ids)
    {
        return navLinkMapper.deleteNavLinkByIds(ids);
    }

    /**
     * 删除导航链接信息
     * 
     * @param id 导航链接主键
     * @return 结果
     */
    @Override
    public int deleteNavLinkById(Long id)
    {
        return navLinkMapper.deleteNavLinkById(id);
    }
}

package site.suimu.system.service;

import java.util.List;
import site.suimu.system.domain.NavLink;

/**
 * 导航链接Service接口
 * 
 * @author lennon
 * @date 2023-07-18
 */
public interface INavLinkService 
{
    /**
     * 查询导航链接
     * 
     * @param id 导航链接主键
     * @return 导航链接
     */
    public NavLink selectNavLinkById(Long id);

    /**
     * 查询导航链接列表
     * 
     * @param navLink 导航链接
     * @return 导航链接集合
     */
    public List<NavLink> selectNavLinkList(NavLink navLink);

    /**
     * 新增导航链接
     * 
     * @param navLink 导航链接
     * @return 结果
     */
    public int insertNavLink(NavLink navLink);

    /**
     * 修改导航链接
     * 
     * @param navLink 导航链接
     * @return 结果
     */
    public int updateNavLink(NavLink navLink);

    /**
     * 批量删除导航链接
     * 
     * @param ids 需要删除的导航链接主键集合
     * @return 结果
     */
    public int deleteNavLinkByIds(Long[] ids);

    /**
     * 删除导航链接信息
     * 
     * @param id 导航链接主键
     * @return 结果
     */
    public int deleteNavLinkById(Long id);
}

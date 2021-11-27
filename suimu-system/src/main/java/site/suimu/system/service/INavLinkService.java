package site.suimu.system.service;

import java.util.List;
import site.suimu.system.domain.NavLink;

/**
 * 链接Service接口
 * 
 * @author ruoyi
 * @date 2021-11-27
 */
public interface INavLinkService 
{
    /**
     * 查询链接
     * 
     * @param id 链接主键
     * @return 链接
     */
    public NavLink selectNavLinkById(Long id);

    /**
     * 查询链接列表
     * 
     * @param navLink 链接
     * @return 链接集合
     */
    public List<NavLink> selectNavLinkList(NavLink navLink);

    /**
     * 新增链接
     * 
     * @param navLink 链接
     * @return 结果
     */
    public int insertNavLink(NavLink navLink);

    /**
     * 修改链接
     * 
     * @param navLink 链接
     * @return 结果
     */
    public int updateNavLink(NavLink navLink);

    /**
     * 批量删除链接
     * 
     * @param ids 需要删除的链接主键集合
     * @return 结果
     */
    public int deleteNavLinkByIds(Long[] ids);

    /**
     * 删除链接信息
     * 
     * @param id 链接主键
     * @return 结果
     */
    public int deleteNavLinkById(Long id);
}

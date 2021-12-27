package site.suimu.system.service;

import java.util.List;
import site.suimu.system.domain.NavClRel;

/**
 * 链接分类关系Service接口
 * 
 * @author ruoyi
 * @date 2021-11-27
 */
public interface INavClRelService 
{
    /**
     * 查询链接分类关系
     * 
     * @param linkId 链接分类关系主键
     * @return 链接分类关系
     */
    public NavClRel selectNavClRelByLinkId(Long linkId);


    public List<NavClRel> selectNavClRelByClassifyIds(Long[] classifyIds);

    /**
     * 查询链接分类关系列表
     * 
     * @param navClRel 链接分类关系
     * @return 链接分类关系集合
     */
    public List<NavClRel> selectNavClRelList(NavClRel navClRel);

    /**
     * 新增链接分类关系
     * 
     * @param navClRel 链接分类关系
     * @return 结果
     */
    public int insertNavClRel(NavClRel navClRel);

    /**
     * 修改链接分类关系
     * 
     * @param navClRel 链接分类关系
     * @return 结果
     */
    public int updateNavClRel(NavClRel navClRel);

    /**
     * 批量删除链接分类关系
     * 
     * @param linkIds 需要删除的链接分类关系主键集合
     * @return 结果
     */
    public int deleteNavClRelByLinkIds(Long[] linkIds);

    /**
     * 删除链接分类关系信息
     * 
     * @param linkId 链接分类关系主键
     * @return 结果
     */
    public int deleteNavClRelByLinkId(Long linkId);
}

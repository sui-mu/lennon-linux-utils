package site.suimu.system.mapper;

import java.util.List;
import site.suimu.system.domain.NavClRel;
import site.suimu.system.domain.NavClassify;

/**
 * 链接分类关系Mapper接口
 * 
 * @author ruoyi
 * @date 2021-11-27
 */
public interface NavClRelMapper 
{
    /**
     * 查询链接分类关系
     * 
     * @param linkId 链接分类关系主键
     * @return 链接分类关系
     */
    public NavClRel selectNavClRelByLinkId(Long linkId);

    /**
     * 查询链接分类关系列表
     * 
     * @param navClRel 链接分类关系
     * @return 链接分类关系集合
     */
    public List<NavClRel> selectNavClRelList(NavClRel navClRel);


    public List<NavClassify> selectClassifys(NavClRel navClRel);

    public List<NavClassify> selectTags(NavClRel navClRel);


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
     * 删除链接分类关系
     * 
     * @param linkId 链接分类关系主键
     * @return 结果
     */
    public int deleteNavClRelByLinkId(Long linkId);

    /**
     * 批量删除链接分类关系
     * 
     * @param linkIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNavClRelByLinkIds(Long[] linkIds);
}

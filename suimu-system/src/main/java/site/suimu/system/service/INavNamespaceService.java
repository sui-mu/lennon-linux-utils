package site.suimu.system.service;

import java.util.List;
import site.suimu.system.domain.NavNamespace;

/**
 * 命名空间Service接口
 * 
 * @author lennon
 * @date 2023-07-18
 */
public interface INavNamespaceService 
{
    /**
     * 查询命名空间
     * 
     * @param id 命名空间主键
     * @return 命名空间
     */
    public NavNamespace selectNavNamespaceById(Long id);

    /**
     * 查询命名空间列表
     * 
     * @param navNamespace 命名空间
     * @return 命名空间集合
     */
    public List<NavNamespace> selectNavNamespaceList(NavNamespace navNamespace);

    /**
     * 新增命名空间
     * 
     * @param navNamespace 命名空间
     * @return 结果
     */
    public int insertNavNamespace(NavNamespace navNamespace);

    /**
     * 修改命名空间
     * 
     * @param navNamespace 命名空间
     * @return 结果
     */
    public int updateNavNamespace(NavNamespace navNamespace);

    /**
     * 批量删除命名空间
     * 
     * @param ids 需要删除的命名空间主键集合
     * @return 结果
     */
    public int deleteNavNamespaceByIds(Long[] ids);

    /**
     * 删除命名空间信息
     * 
     * @param id 命名空间主键
     * @return 结果
     */
    public int deleteNavNamespaceById(Long id);
}

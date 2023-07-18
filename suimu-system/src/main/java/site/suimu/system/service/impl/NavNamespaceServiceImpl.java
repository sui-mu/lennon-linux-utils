package site.suimu.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.suimu.system.mapper.NavNamespaceMapper;
import site.suimu.system.domain.NavNamespace;
import site.suimu.system.service.INavNamespaceService;

/**
 * 命名空间Service业务层处理
 * 
 * @author lennon
 * @date 2023-07-18
 */
@Service
public class NavNamespaceServiceImpl implements INavNamespaceService 
{
    @Autowired
    private NavNamespaceMapper navNamespaceMapper;

    /**
     * 查询命名空间
     * 
     * @param id 命名空间主键
     * @return 命名空间
     */
    @Override
    public NavNamespace selectNavNamespaceById(Long id)
    {
        return navNamespaceMapper.selectNavNamespaceById(id);
    }

    /**
     * 查询命名空间列表
     * 
     * @param navNamespace 命名空间
     * @return 命名空间
     */
    @Override
    public List<NavNamespace> selectNavNamespaceList(NavNamespace navNamespace)
    {
        return navNamespaceMapper.selectNavNamespaceList(navNamespace);
    }

    /**
     * 新增命名空间
     * 
     * @param navNamespace 命名空间
     * @return 结果
     */
    @Override
    public int insertNavNamespace(NavNamespace navNamespace)
    {
        return navNamespaceMapper.insertNavNamespace(navNamespace);
    }

    /**
     * 修改命名空间
     * 
     * @param navNamespace 命名空间
     * @return 结果
     */
    @Override
    public int updateNavNamespace(NavNamespace navNamespace)
    {
        return navNamespaceMapper.updateNavNamespace(navNamespace);
    }

    /**
     * 批量删除命名空间
     * 
     * @param ids 需要删除的命名空间主键
     * @return 结果
     */
    @Override
    public int deleteNavNamespaceByIds(Long[] ids)
    {
        return navNamespaceMapper.deleteNavNamespaceByIds(ids);
    }

    /**
     * 删除命名空间信息
     * 
     * @param id 命名空间主键
     * @return 结果
     */
    @Override
    public int deleteNavNamespaceById(Long id)
    {
        return navNamespaceMapper.deleteNavNamespaceById(id);
    }
}

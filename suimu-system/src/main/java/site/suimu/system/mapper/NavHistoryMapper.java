package site.suimu.system.mapper;

import java.util.List;
import site.suimu.system.domain.NavHistory;

/**
 * 历史记录Mapper接口
 * 
 * @author ruoyi
 * @date 2021-11-27
 */
public interface NavHistoryMapper 
{
    /**
     * 查询历史记录
     * 
     * @param id 历史记录主键
     * @return 历史记录
     */
    public NavHistory selectNavHistoryById(String id);

    /**
     * 查询历史记录列表
     * 
     * @param navHistory 历史记录
     * @return 历史记录集合
     */
    public List<NavHistory> selectNavHistoryList(NavHistory navHistory);

    /**
     * 新增历史记录
     * 
     * @param navHistory 历史记录
     * @return 结果
     */
    public int insertNavHistory(NavHistory navHistory);

    /**
     * 修改历史记录
     * 
     * @param navHistory 历史记录
     * @return 结果
     */
    public int updateNavHistory(NavHistory navHistory);

    /**
     * 删除历史记录
     * 
     * @param id 历史记录主键
     * @return 结果
     */
    public int deleteNavHistoryById(String id);

    /**
     * 批量删除历史记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNavHistoryByIds(String[] ids);
}

package site.suimu.system.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.suimu.common.constant.Constants;
import site.suimu.common.core.redis.RedisCache;
import site.suimu.common.utils.DateUtils;
import site.suimu.common.utils.StringUtils;
import site.suimu.system.mapper.NavHistoryMapper;
import site.suimu.system.domain.NavHistory;
import site.suimu.system.service.INavHistoryService;

/**
 * 历史记录Service业务层处理
 *
 * @author ruoyi
 * @date 2021-11-27
 */
@Service
public class NavHistoryServiceImpl implements INavHistoryService {

    @Autowired
    private NavHistoryMapper navHistoryMapper;

    @Autowired
    private RedisCache redisCache;

    /**
     * 查询历史记录
     *
     * @param id 历史记录主键
     * @return 历史记录
     */
    @Override
    public NavHistory selectNavHistoryById(String id) {
        return navHistoryMapper.selectNavHistoryById(id);
    }

    /**
     * 查询历史记录列表
     *
     * @param navHistory 历史记录
     * @return 历史记录
     */
    @Override
    public List<NavHistory> selectNavHistoryList(NavHistory navHistory) {
        return navHistoryMapper.selectNavHistoryList(navHistory);
    }

    /**
     * 新增历史记录
     *
     * @param navHistory 历史记录
     * @return 结果
     */
    @Override
    public int insertNavHistory(NavHistory navHistory) {
        int re = 0;
        String id = DateUtils.dateTime() + StringUtils.addZeroForNum(navHistory.getLinkId().toString(), 6) + StringUtils.addZeroForNum(navHistory.getUserId().toString(), 4);
        // 限制记录频率 ，同一个登录的用户、同一小时内 对一个链接的点击量只有一次，防止 恶意加载。
        String oneDay = redisCache.getCacheObject(Constants.NAV_HISTORY_KEY + id);
        if (StringUtils.isEmpty(oneDay)) {
            navHistory.setId(id);
            redisCache.setCacheObject(Constants.NAV_HISTORY_KEY + id, "1", 1, TimeUnit.HOURS);
            re = navHistoryMapper.insertNavHistory(navHistory);
        } else {
            return re;
        }
        return re;
    }

    /**
     * 修改历史记录
     *
     * @param navHistory 历史记录
     * @return 结果
     */
    @Override
    public int updateNavHistory(NavHistory navHistory) {
        return navHistoryMapper.updateNavHistory(navHistory);
    }

    /**
     * 批量删除历史记录
     *
     * @param ids 需要删除的历史记录主键
     * @return 结果
     */
    @Override
    public int deleteNavHistoryByIds(String[] ids) {
        return navHistoryMapper.deleteNavHistoryByIds(ids);
    }

    /**
     * 删除历史记录信息
     *
     * @param id 历史记录主键
     * @return 结果
     */
    @Override
    public int deleteNavHistoryById(String id) {
        return navHistoryMapper.deleteNavHistoryById(id);
    }
}

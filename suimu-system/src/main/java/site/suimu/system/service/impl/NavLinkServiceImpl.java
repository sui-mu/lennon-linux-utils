package site.suimu.system.service.impl;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import site.suimu.common.annotation.DataScope;
import site.suimu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.suimu.common.utils.SecurityUtils;
import site.suimu.common.utils.StringUtils;
import site.suimu.system.domain.NavClRel;
import site.suimu.system.domain.NavClassify;
import site.suimu.system.mapper.NavLinkMapper;
import site.suimu.system.domain.NavLink;
import site.suimu.system.service.INavClRelService;
import site.suimu.system.service.INavLinkService;

/**
 * 链接Service业务层处理
 *
 * @author ruoyi
 * @date 2021-11-27
 */
@Service
public class NavLinkServiceImpl implements INavLinkService {

    private static final Logger log = LoggerFactory.getLogger(NavLinkServiceImpl.class);

    @Autowired
    private NavLinkMapper navLinkMapper;

    @Autowired
    private INavClRelService iNavClRelService;

    /**
     * 查询链接
     *
     * @param id 链接主键
     * @return 链接
     */
    @Override
    public NavLink selectNavLinkById(Long id) {
        return navLinkMapper.selectNavLinkById(id);
    }

    /**
     * 查询链接列表
     *
     * @param navLink 链接
     * @return 链接
     */
    @Override
    public List<NavLink> selectNavLinkList(NavLink navLink) {
        handleUser(navLink);
        return navLinkMapper.selectNavLinkList(navLink);
    }

    @Override
    public Set<Long> selectNavIdSet(NavLink navLink) {
        handleUser(navLink);
        return navLinkMapper.selectNavIdSet(navLink);
    }

    private void handleUser(NavLink navLink) {
        try {
            Long userId = SecurityUtils.getUserId();
            if (userId != 1L) {
                Long deptId = SecurityUtils.getDeptId();
                navLink.setDeptId(deptId);
                navLink.setCreateBy(String.valueOf(userId));
            }
        } catch (Exception e) {
            navLink.setStatus("1");
        }
    }

    /**
     * 新增链接
     *
     * @param navLink 链接
     * @return 结果
     */
    @Override
    public int insertNavLink(NavLink navLink) {
        int re = 0;
        Long deptId;
        Long userId;
        try {
            userId = SecurityUtils.getUserId();
            deptId = SecurityUtils.getDeptId();
        } catch (Exception e) {
            userId = 0L;
            deptId = 0L;
        }
        navLink.setCreateBy(String.valueOf(userId));
        navLink.setCreateTime(DateUtils.getNowDate());
        navLink.setDeptId(deptId);
        re = navLinkMapper.insertNavLink(navLink);
        if (re > 0) {
            handleReLation(navLink);
        }
        return re;
    }

    private void handleReLation(NavLink navLink) {
        iNavClRelService.deleteNavClRelByLinkId(navLink.getId());
        if (StringUtils.isNotEmpty(navLink.getClassifies())) {
            for (NavClassify classify : navLink.getClassifies()) {
                Long classifyId = classify.getId();
                NavClRel navClRel = new NavClRel();
                navClRel.setClassifyId(classifyId);
                navClRel.setLinkId(navLink.getId());
                navClRel.setUserId(Long.parseLong(navLink.getCreateBy()));
                iNavClRelService.insertNavClRel(navClRel);
            }
        }
        if (StringUtils.isNotEmpty(navLink.getTags())) {
            for (NavClassify classify : navLink.getTags()) {
                Long classifyId = classify.getId();
                NavClRel navClRel = new NavClRel();
                navClRel.setClassifyId(classifyId);
                navClRel.setLinkId(navLink.getId());
                navClRel.setUserId(Long.parseLong(navLink.getCreateBy()));
                iNavClRelService.insertNavClRel(navClRel);
            }
        }
    }


    /**
     * 修改链接
     *
     * @param navLink 链接
     * @return 结果
     */
    @Override
    public int updateNavLink(NavLink navLink) {
        Long userId;
        try {
            userId = SecurityUtils.getUserId();
        } catch (Exception e) {
            userId = 0L;
        }
        navLink.setUpdateBy(String.valueOf(userId));
        navLink.setUpdateTime(DateUtils.getNowDate());
        handleReLation(navLink);
        return navLinkMapper.updateNavLink(navLink);
    }

    /**
     * 批量删除链接
     *
     * @param ids 需要删除的链接主键
     * @return 结果
     */
    @Override
    public int deleteNavLinkByIds(Long[] ids) {
        int re = navLinkMapper.deleteNavLinkByIds(ids);
        if (re > 0) {
            re += iNavClRelService.deleteNavClRelByLinkIds(ids);
        }
        return re;
    }

    /**
     * 删除链接信息
     *
     * @param id 链接主键
     * @return 结果
     */
    @Override
    public int deleteNavLinkById(Long id) {
        return navLinkMapper.deleteNavLinkById(id);
    }
}

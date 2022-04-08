package site.suimu.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.suimu.common.core.domain.entity.SysUser;
import site.suimu.common.utils.SecurityUtils;
import site.suimu.common.utils.StringUtils;
import site.suimu.system.domain.NavClassify;
import site.suimu.system.domain.NavLink;
import site.suimu.system.service.INavClassifyService;
import site.suimu.system.service.INavLinkService;
import site.suimu.system.service.ISuiMuNavService;
import site.suimu.system.service.ISysUserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class SuiMuNavServiceImpl implements ISuiMuNavService {

    @Autowired
    private INavClassifyService navClassifyService;

    @Autowired
    private INavLinkService navLinkService;

    @Autowired
    private ISysUserService sysUserService;


    @Override
    public List<NavClassify> tree(NavClassify navClassify) {
        NavLink query = new NavLink();
        if (StringUtils.isNotNull(navClassify) && StringUtils.isNotEmpty(navClassify.getUserKey())) {
            SysUser sysUser = sysUserService.selectUserByUserName(navClassify.getUserKey());
            navClassify.setDeptId(sysUser.getDeptId());
            navClassify.setCreateBy(String.valueOf(sysUser.getUserId()));
            query.setDeptId(sysUser.getDeptId());
            query.setCreateBy(String.valueOf(sysUser.getUserId()));
        } else {
            try {
                Long userId = SecurityUtils.getUserId();
                if (userId != 1L) {
                    Long deptId = SecurityUtils.getDeptId();
                    navClassify.setDeptId(deptId);
                    navClassify.setCreateBy(String.valueOf(userId));
                    query.setDeptId(deptId);
                    query.setCreateBy(String.valueOf(userId));
                }
            } catch (Exception e) {
                navClassify.setShare("1");
                query.setStatus("1");
            }
        }
        List<NavClassify> origin = navClassifyService.selectNavClassifyList(navClassify);
        Set<Long> linkIds = navLinkService.selectNavIdSet(query);
        boolean find = StringUtils.isNotEmpty(linkIds);
        for (NavClassify navClassifyItem : origin) {
            if (StringUtils.isNotEmpty(navClassifyItem.getNavLinks())) {
                List<NavLink> newList = new ArrayList<>();
                for (NavLink link : navClassifyItem.getNavLinks()) {
                    if (find && linkIds.contains(link.getId())) {
                        newList.add(link);
                    }
                }
                navClassifyItem.setNavLinks(newList);
            }
        }
        if (StringUtils.isNull(navClassify.getParentId())) {
            navClassify.setParentId(0L);
        }
        return navClassifyService.buildClassifyTree(origin, navClassify.getParentId());
    }
}

package site.suimu.system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.suimu.common.core.domain.AjaxResult;
import site.suimu.common.utils.SecurityUtils;
import site.suimu.system.domain.NavClassify;
import site.suimu.system.service.INavClassifyService;

import java.util.List;

@RestController
@RequestMapping("/system/navigation")
public class SuiMuNavController {


    @Autowired
    private INavClassifyService navClassifyService;

    /**
     * 查询分类树
     */
    @GetMapping("/tab/tree")
    public AjaxResult tree(NavClassify navClassify) {
        try {
            Long userId = SecurityUtils.getUserId();
            if (userId != 1L) {
                Long deptId = SecurityUtils.getDeptId();
                navClassify.setDeptId(deptId);
                navClassify.setCreateBy(String.valueOf(userId));
            }
        } catch (Exception e) {
            navClassify.setParentId(0L);
            navClassify.setShare("1");
        }
        List<NavClassify> list = navClassifyService.selectNavClassifyTree(navClassify);
        return AjaxResult.success(list);
    }


}

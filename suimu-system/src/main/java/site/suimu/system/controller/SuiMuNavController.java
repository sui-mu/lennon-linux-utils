package site.suimu.system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.suimu.common.core.domain.AjaxResult;
import site.suimu.system.domain.NavClassify;
import site.suimu.system.service.ISuiMuNavService;

import java.util.List;

@RestController
@RequestMapping("/system/navigation")
public class SuiMuNavController {


    @Autowired
    private ISuiMuNavService suiMuNavService;

    /**
     * 查询分类树
     */
    @GetMapping("/tab/tree")
    public AjaxResult tree(NavClassify navClassify) {
        List<NavClassify> list = suiMuNavService.tree(navClassify);
        return AjaxResult.success(list);
    }


}

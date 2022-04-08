package site.suimu.web.controller.nologin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.suimu.common.core.domain.AjaxResult;
import site.suimu.system.domain.NavClassify;
import site.suimu.system.service.INavLinkService;
import site.suimu.system.service.ISuiMuNavService;

import java.util.List;

@RestController
@RequestMapping("/nologin")
public class NologinController {


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

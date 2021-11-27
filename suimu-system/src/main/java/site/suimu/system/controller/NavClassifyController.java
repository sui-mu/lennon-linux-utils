package site.suimu.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.suimu.common.annotation.Log;
import site.suimu.common.core.controller.BaseController;
import site.suimu.common.core.domain.AjaxResult;
import site.suimu.common.enums.BusinessType;
import site.suimu.common.utils.SecurityUtils;
import site.suimu.system.domain.NavClassify;
import site.suimu.system.service.INavClassifyService;
import site.suimu.common.utils.poi.ExcelUtil;

/**
 * 分类Controller
 * 
 * @author ruoyi
 * @date 2021-11-27
 */
@RestController
@RequestMapping("/system/navclassify")
public class NavClassifyController extends BaseController
{
    @Autowired
    private INavClassifyService navClassifyService;

    /**
     * 查询分类列表
     */
    @PreAuthorize("@ss.hasPermi('system:navclassify:list')")
    @GetMapping("/list")
    public AjaxResult list(NavClassify navClassify)
    {
        List<NavClassify> list = navClassifyService.selectNavClassifyList(navClassify);
        return AjaxResult.success(list);
    }

    /**
     * 查询分类树
     */
    @PreAuthorize("@ss.hasPermi('system:navclassify:list')")
    @GetMapping("/tree")
    public AjaxResult tree(NavClassify navClassify)
    {
        List<NavClassify> list = navClassifyService.selectNavClassifyTree(navClassify);
        return AjaxResult.success(list);
    }

    /**
     * 导出分类列表
     */
    @PreAuthorize("@ss.hasPermi('system:navclassify:export')")
    @Log(title = "分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NavClassify navClassify)
    {
        List<NavClassify> list = navClassifyService.selectNavClassifyList(navClassify);
        ExcelUtil<NavClassify> util = new ExcelUtil<NavClassify>(NavClassify.class);
        util.exportExcel(response, list, "分类数据");
    }

    /**
     * 获取分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:navclassify:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(navClassifyService.selectNavClassifyById(id));
    }

    /**
     * 新增分类
     */
    @PreAuthorize("@ss.hasPermi('system:navclassify:add')")
    @Log(title = "分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NavClassify navClassify)
    {
        return toAjax(navClassifyService.insertNavClassify(navClassify));
    }

    /**
     * 修改分类
     */
    @PreAuthorize("@ss.hasPermi('system:navclassify:edit')")
    @Log(title = "分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NavClassify navClassify)
    {
        Long userId = SecurityUtils.getUserId();
        navClassify.setUpdateBy(String.valueOf(userId));
        return toAjax(navClassifyService.updateNavClassify(navClassify));
    }

    /**
     * 删除分类
     */
    @PreAuthorize("@ss.hasPermi('system:navclassify:remove')")
    @Log(title = "分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(navClassifyService.deleteNavClassifyByIds(ids));
    }
}

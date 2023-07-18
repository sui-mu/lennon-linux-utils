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
import site.suimu.system.domain.NavClassify;
import site.suimu.system.service.INavClassifyService;
import site.suimu.common.utils.poi.ExcelUtil;
import site.suimu.common.core.page.TableDataInfo;

/**
 * 导航分类Controller
 * 
 * @author lennon
 * @date 2023-07-18
 */
@RestController
@RequestMapping("/system/navClassify")
public class NavClassifyController extends BaseController
{
    @Autowired
    private INavClassifyService navClassifyService;

    /**
     * 查询导航分类列表
     */
    @PreAuthorize("@ss.hasPermi('system:navClassify:list')")
    @GetMapping("/list")
    public TableDataInfo list(NavClassify navClassify)
    {
        startPage();
        List<NavClassify> list = navClassifyService.selectNavClassifyList(navClassify);
        return getDataTable(list);
    }

    /**
     * 导出导航分类列表
     */
    @PreAuthorize("@ss.hasPermi('system:navClassify:export')")
    @Log(title = "导航分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NavClassify navClassify)
    {
        List<NavClassify> list = navClassifyService.selectNavClassifyList(navClassify);
        ExcelUtil<NavClassify> util = new ExcelUtil<NavClassify>(NavClassify.class);
        util.exportExcel(response, list, "导航分类数据");
    }

    /**
     * 获取导航分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:navClassify:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(navClassifyService.selectNavClassifyById(id));
    }

    /**
     * 新增导航分类
     */
    @PreAuthorize("@ss.hasPermi('system:navClassify:add')")
    @Log(title = "导航分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NavClassify navClassify)
    {
        return toAjax(navClassifyService.insertNavClassify(navClassify));
    }

    /**
     * 修改导航分类
     */
    @PreAuthorize("@ss.hasPermi('system:navClassify:edit')")
    @Log(title = "导航分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NavClassify navClassify)
    {
        return toAjax(navClassifyService.updateNavClassify(navClassify));
    }

    /**
     * 删除导航分类
     */
    @PreAuthorize("@ss.hasPermi('system:navClassify:remove')")
    @Log(title = "导航分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(navClassifyService.deleteNavClassifyByIds(ids));
    }
}

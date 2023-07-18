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
import site.suimu.system.domain.NavNamespace;
import site.suimu.system.service.INavNamespaceService;
import site.suimu.common.utils.poi.ExcelUtil;
import site.suimu.common.core.page.TableDataInfo;

/**
 * 命名空间Controller
 * 
 * @author lennon
 * @date 2023-07-18
 */
@RestController
@RequestMapping("/system/navNamespace")
public class NavNamespaceController extends BaseController
{
    @Autowired
    private INavNamespaceService navNamespaceService;

    /**
     * 查询命名空间列表
     */
    @PreAuthorize("@ss.hasPermi('system:navNamespace:list')")
    @GetMapping("/list")
    public TableDataInfo list(NavNamespace navNamespace)
    {
        startPage();
        List<NavNamespace> list = navNamespaceService.selectNavNamespaceList(navNamespace);
        return getDataTable(list);
    }

    /**
     * 导出命名空间列表
     */
    @PreAuthorize("@ss.hasPermi('system:navNamespace:export')")
    @Log(title = "命名空间", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NavNamespace navNamespace)
    {
        List<NavNamespace> list = navNamespaceService.selectNavNamespaceList(navNamespace);
        ExcelUtil<NavNamespace> util = new ExcelUtil<NavNamespace>(NavNamespace.class);
        util.exportExcel(response, list, "命名空间数据");
    }

    /**
     * 获取命名空间详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:navNamespace:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(navNamespaceService.selectNavNamespaceById(id));
    }

    /**
     * 新增命名空间
     */
    @PreAuthorize("@ss.hasPermi('system:navNamespace:add')")
    @Log(title = "命名空间", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NavNamespace navNamespace)
    {
        return toAjax(navNamespaceService.insertNavNamespace(navNamespace));
    }

    /**
     * 修改命名空间
     */
    @PreAuthorize("@ss.hasPermi('system:navNamespace:edit')")
    @Log(title = "命名空间", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NavNamespace navNamespace)
    {
        return toAjax(navNamespaceService.updateNavNamespace(navNamespace));
    }

    /**
     * 删除命名空间
     */
    @PreAuthorize("@ss.hasPermi('system:navNamespace:remove')")
    @Log(title = "命名空间", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(navNamespaceService.deleteNavNamespaceByIds(ids));
    }
}

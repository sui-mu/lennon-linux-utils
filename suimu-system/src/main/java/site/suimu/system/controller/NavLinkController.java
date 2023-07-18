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
import site.suimu.system.domain.NavLink;
import site.suimu.system.service.INavLinkService;
import site.suimu.common.utils.poi.ExcelUtil;
import site.suimu.common.core.page.TableDataInfo;

/**
 * 导航链接Controller
 * 
 * @author lennon
 * @date 2023-07-18
 */
@RestController
@RequestMapping("/system/navLink")
public class NavLinkController extends BaseController
{
    @Autowired
    private INavLinkService navLinkService;

    /**
     * 查询导航链接列表
     */
    @PreAuthorize("@ss.hasPermi('system:navLink:list')")
    @GetMapping("/list")
    public TableDataInfo list(NavLink navLink)
    {
        startPage();
        List<NavLink> list = navLinkService.selectNavLinkList(navLink);
        return getDataTable(list);
    }

    /**
     * 导出导航链接列表
     */
    @PreAuthorize("@ss.hasPermi('system:navLink:export')")
    @Log(title = "导航链接", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NavLink navLink)
    {
        List<NavLink> list = navLinkService.selectNavLinkList(navLink);
        ExcelUtil<NavLink> util = new ExcelUtil<NavLink>(NavLink.class);
        util.exportExcel(response, list, "导航链接数据");
    }

    /**
     * 获取导航链接详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:navLink:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(navLinkService.selectNavLinkById(id));
    }

    /**
     * 新增导航链接
     */
    @PreAuthorize("@ss.hasPermi('system:navLink:add')")
    @Log(title = "导航链接", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NavLink navLink)
    {
        return toAjax(navLinkService.insertNavLink(navLink));
    }

    /**
     * 修改导航链接
     */
    @PreAuthorize("@ss.hasPermi('system:navLink:edit')")
    @Log(title = "导航链接", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NavLink navLink)
    {
        return toAjax(navLinkService.updateNavLink(navLink));
    }

    /**
     * 删除导航链接
     */
    @PreAuthorize("@ss.hasPermi('system:navLink:remove')")
    @Log(title = "导航链接", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(navLinkService.deleteNavLinkByIds(ids));
    }
}

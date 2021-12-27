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
import site.suimu.system.domain.NavLink;
import site.suimu.system.service.INavLinkService;
import site.suimu.common.utils.poi.ExcelUtil;
import site.suimu.common.core.page.TableDataInfo;

/**
 * 链接Controller
 * 
 * @author ruoyi
 * @date 2021-11-27
 */
@RestController
@RequestMapping("/system/navlink")
public class NavLinkController extends BaseController
{
    @Autowired
    private INavLinkService navLinkService;

    /**
     * 查询链接列表
     */
    @PreAuthorize("@ss.hasPermi('system:navlink:list')")
    @GetMapping("/list")
    public TableDataInfo list(NavLink navLink)
    {
        startPage();
        List<NavLink> list = navLinkService.selectNavLinkList(navLink);
        return getDataTable(list);
    }

    /**
     * 导出链接列表
     */
    @PreAuthorize("@ss.hasPermi('system:navlink:export')")
    @Log(title = "链接", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NavLink navLink)
    {
        List<NavLink> list = navLinkService.selectNavLinkList(navLink);
        ExcelUtil<NavLink> util = new ExcelUtil<NavLink>(NavLink.class);
        util.exportExcel(response, list, "链接数据");
    }

    /**
     * 获取链接详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:navlink:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(navLinkService.selectNavLinkById(id));
    }

    /**
     * 新增链接
     */
    @PreAuthorize("@ss.hasPermi('system:navlink:add')")
    @Log(title = "链接", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NavLink navLink)
    {
        return toAjax(navLinkService.insertNavLink(navLink));
    }

    /**
     * 修改链接
     */
    @PreAuthorize("@ss.hasPermi('system:navlink:edit')")
    @Log(title = "链接", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NavLink navLink)
    {
        return toAjax(navLinkService.updateNavLink(navLink));
    }

    /**
     * 删除链接
     */
    @PreAuthorize("@ss.hasPermi('system:navlink:remove')")
    @Log(title = "链接", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(navLinkService.deleteNavLinkByIds(ids));
    }
}

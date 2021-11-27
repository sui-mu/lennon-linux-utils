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
import site.suimu.system.domain.NavClRel;
import site.suimu.system.service.INavClRelService;
import site.suimu.common.utils.poi.ExcelUtil;
import site.suimu.common.core.page.TableDataInfo;

/**
 * 链接分类关系Controller
 * 
 * @author ruoyi
 * @date 2021-11-27
 */
@RestController
@RequestMapping("/system/navclrel")
public class NavClRelController extends BaseController
{
    @Autowired
    private INavClRelService navClRelService;

    /**
     * 查询链接分类关系列表
     */
    @PreAuthorize("@ss.hasPermi('system:navclrel:list')")
    @GetMapping("/list")
    public TableDataInfo list(NavClRel navClRel)
    {
        startPage();
        List<NavClRel> list = navClRelService.selectNavClRelList(navClRel);
        return getDataTable(list);
    }

    /**
     * 导出链接分类关系列表
     */
    @PreAuthorize("@ss.hasPermi('system:navclrel:export')")
    @Log(title = "链接分类关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NavClRel navClRel)
    {
        List<NavClRel> list = navClRelService.selectNavClRelList(navClRel);
        ExcelUtil<NavClRel> util = new ExcelUtil<NavClRel>(NavClRel.class);
        util.exportExcel(response, list, "链接分类关系数据");
    }

    /**
     * 获取链接分类关系详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:navclrel:query')")
    @GetMapping(value = "/{linkId}")
    public AjaxResult getInfo(@PathVariable("linkId") Long linkId)
    {
        return AjaxResult.success(navClRelService.selectNavClRelByLinkId(linkId));
    }

    /**
     * 新增链接分类关系
     */
    @PreAuthorize("@ss.hasPermi('system:navclrel:add')")
    @Log(title = "链接分类关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NavClRel navClRel)
    {
        return toAjax(navClRelService.insertNavClRel(navClRel));
    }

    /**
     * 修改链接分类关系
     */
    @PreAuthorize("@ss.hasPermi('system:navclrel:edit')")
    @Log(title = "链接分类关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NavClRel navClRel)
    {
        return toAjax(navClRelService.updateNavClRel(navClRel));
    }

    /**
     * 删除链接分类关系
     */
    @PreAuthorize("@ss.hasPermi('system:navclrel:remove')")
    @Log(title = "链接分类关系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{linkIds}")
    public AjaxResult remove(@PathVariable Long[] linkIds)
    {
        return toAjax(navClRelService.deleteNavClRelByLinkIds(linkIds));
    }
}

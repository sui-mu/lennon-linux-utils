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
import site.suimu.system.domain.NavHistory;
import site.suimu.system.service.INavHistoryService;
import site.suimu.common.utils.poi.ExcelUtil;
import site.suimu.common.core.page.TableDataInfo;

/**
 * 历史记录Controller
 * 
 * @author ruoyi
 * @date 2021-11-27
 */
@RestController
@RequestMapping("/system/navlinkhistory")
public class NavHistoryController extends BaseController
{
    @Autowired
    private INavHistoryService navHistoryService;

    /**
     * 查询历史记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:navlinkhistory:list')")
    @GetMapping("/list")
    public TableDataInfo list(NavHistory navHistory)
    {
        startPage();
        List<NavHistory> list = navHistoryService.selectNavHistoryList(navHistory);
        return getDataTable(list);
    }

    /**
     * 导出历史记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:navlinkhistory:export')")
    @Log(title = "历史记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NavHistory navHistory)
    {
        List<NavHistory> list = navHistoryService.selectNavHistoryList(navHistory);
        ExcelUtil<NavHistory> util = new ExcelUtil<NavHistory>(NavHistory.class);
        util.exportExcel(response, list, "历史记录数据");
    }

    /**
     * 获取历史记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:navlinkhistory:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(navHistoryService.selectNavHistoryById(id));
    }

    /**
     * 新增历史记录
     */
    @PreAuthorize("@ss.hasPermi('system:navlinkhistory:add')")
    @Log(title = "历史记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NavHistory navHistory)
    {
        return toAjax(navHistoryService.insertNavHistory(navHistory));
    }

    /**
     * 修改历史记录
     */
    @PreAuthorize("@ss.hasPermi('system:navlinkhistory:edit')")
    @Log(title = "历史记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NavHistory navHistory)
    {
        return toAjax(navHistoryService.updateNavHistory(navHistory));
    }

    /**
     * 删除历史记录
     */
    @PreAuthorize("@ss.hasPermi('system:navlinkhistory:remove')")
    @Log(title = "历史记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(navHistoryService.deleteNavHistoryByIds(ids));
    }
}

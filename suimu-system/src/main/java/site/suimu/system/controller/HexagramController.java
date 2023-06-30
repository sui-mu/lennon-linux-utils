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
import site.suimu.system.domain.Hexagram;
import site.suimu.system.service.IHexagramService;
import site.suimu.common.utils.poi.ExcelUtil;
import site.suimu.common.core.page.TableDataInfo;

/**
 * 卦Controller
 * 
 * @author ruoyi
 * @date 2023-06-30
 */
@RestController
@RequestMapping("/system/hexagram")
public class HexagramController extends BaseController
{
    @Autowired
    private IHexagramService hexagramService;

    /**
     * 查询卦列表
     */
    @PreAuthorize("@ss.hasPermi('system:hexagram:list')")
    @GetMapping("/list")
    public TableDataInfo list(Hexagram hexagram)
    {
        startPage();
        List<Hexagram> list = hexagramService.selectHexagramList(hexagram);
        return getDataTable(list);
    }

    /**
     * 导出卦列表
     */
    @PreAuthorize("@ss.hasPermi('system:hexagram:export')")
    @Log(title = "卦", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Hexagram hexagram)
    {
        List<Hexagram> list = hexagramService.selectHexagramList(hexagram);
        ExcelUtil<Hexagram> util = new ExcelUtil<Hexagram>(Hexagram.class);
        util.exportExcel(response, list, "卦数据");
    }

    /**
     * 获取卦详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:hexagram:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(hexagramService.selectHexagramById(id));
    }

    /**
     * 新增卦
     */
    @PreAuthorize("@ss.hasPermi('system:hexagram:add')")
    @Log(title = "卦", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Hexagram hexagram)
    {
        return toAjax(hexagramService.insertHexagram(hexagram));
    }

    /**
     * 修改卦
     */
    @PreAuthorize("@ss.hasPermi('system:hexagram:edit')")
    @Log(title = "卦", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Hexagram hexagram)
    {
        return toAjax(hexagramService.updateHexagram(hexagram));
    }

    /**
     * 删除卦
     */
    @PreAuthorize("@ss.hasPermi('system:hexagram:remove')")
    @Log(title = "卦", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(hexagramService.deleteHexagramByIds(ids));
    }
}

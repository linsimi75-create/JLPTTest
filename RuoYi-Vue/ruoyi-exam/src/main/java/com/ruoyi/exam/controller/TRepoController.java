package com.ruoyi.exam.controller;

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
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.exam.domain.TRepo;
import com.ruoyi.exam.service.ITRepoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 题库Controller
 * 
 * @author Jacks
 * @date 2025-10-05
 */
@RestController
@RequestMapping("/exam/repo")
public class TRepoController extends BaseController
{
    @Autowired
    private ITRepoService tRepoService;

    /**
     * 查询题库列表
     */
    @PreAuthorize("@ss.hasPermi('exam:repo:list')")
    @GetMapping("/list")
    public TableDataInfo list(TRepo tRepo)
    {
        startPage();
        List<TRepo> list = tRepoService.selectTRepoList(tRepo);
        return getDataTable(list);
    }

    /**
     * 导出题库列表
     */
    @PreAuthorize("@ss.hasPermi('exam:repo:export')")
    @Log(title = "题库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TRepo tRepo)
    {
        List<TRepo> list = tRepoService.selectTRepoList(tRepo);
        ExcelUtil<TRepo> util = new ExcelUtil<TRepo>(TRepo.class);
        util.exportExcel(response, list, "题库数据");
    }

    /**
     * 获取题库详细信息
     */
    @PreAuthorize("@ss.hasPermi('exam:repo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tRepoService.selectTRepoById(id));
    }

    /**
     * 新增题库
     */
    @PreAuthorize("@ss.hasPermi('exam:repo:add')")
    @Log(title = "题库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TRepo tRepo)
    {
        return toAjax(tRepoService.insertTRepo(tRepo));
    }

    /**
     * 修改题库
     */
    @PreAuthorize("@ss.hasPermi('exam:repo:edit')")
    @Log(title = "题库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TRepo tRepo)
    {
        return toAjax(tRepoService.updateTRepo(tRepo));
    }

    /**
     * 删除题库
     */
    @PreAuthorize("@ss.hasPermi('exam:repo:remove')")
    @Log(title = "题库", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tRepoService.deleteTRepoByIds(ids));
    }
}

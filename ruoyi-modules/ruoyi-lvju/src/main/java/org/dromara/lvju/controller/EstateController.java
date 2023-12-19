package org.dromara.lvju.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.web.core.BaseController;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.lvju.domain.vo.EstateVo;
import org.dromara.lvju.domain.bo.EstateBo;
import org.dromara.lvju.service.IEstateService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 小区信息操作
 *
 * @author xsQian
 * @date 2023-12-19
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/lvju/estate")
public class EstateController extends BaseController {

    private final IEstateService estateService;

    /**
     * 查询小区信息操作列表
     */
    @SaCheckPermission("lvju:estate:list")
    @GetMapping("/list")
    public TableDataInfo<EstateVo> list(EstateBo bo, PageQuery pageQuery) {
        return estateService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出小区信息操作列表
     */
    @SaCheckPermission("lvju:estate:export")
    @Log(title = "小区信息操作", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EstateBo bo, HttpServletResponse response) {
        List<EstateVo> list = estateService.queryList(bo);
        ExcelUtil.exportExcel(list, "小区信息操作", EstateVo.class, response);
    }

    /**
     * 获取小区信息操作详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("lvju:estate:query")
    @GetMapping("/{id}")
    public R<EstateVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(estateService.queryById(id));
    }

    /**
     * 新增小区信息操作
     */
    @SaCheckPermission("lvju:estate:add")
    @Log(title = "小区信息操作", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EstateBo bo) {
        return toAjax(estateService.insertByBo(bo));
    }

    /**
     * 修改小区信息操作
     */
    @SaCheckPermission("lvju:estate:edit")
    @Log(title = "小区信息操作", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EstateBo bo) {
        return toAjax(estateService.updateByBo(bo));
    }

    /**
     * 删除小区信息操作
     *
     * @param ids 主键串
     */
    @SaCheckPermission("lvju:estate:remove")
    @Log(title = "小区信息操作", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(estateService.deleteWithValidByIds(List.of(ids), true));
    }
}

package org.dromara.system.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import org.dromara.system.domain.bo.SupplierBo;
import org.dromara.system.domain.vo.SupplierVo;
import org.dromara.system.service.ISupplierService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 供应商信息
 *
 * @author xsQian
 * @date 2023-12-19
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/lvju/supplier")
public class SupplierController extends BaseController {

    private final ISupplierService supplierService;

    /**
     * 查询供应商信息列表
     */
    @SaCheckPermission("lvju:supplier:list")
    @GetMapping("/list")
    public TableDataInfo<SupplierVo> list(SupplierBo bo, PageQuery pageQuery) {
        return supplierService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出供应商信息列表
     */
    @SaCheckPermission("lvju:supplier:export")
    @Log(title = "供应商信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SupplierBo bo, HttpServletResponse response) {
        List<SupplierVo> list = supplierService.queryList(bo);
        ExcelUtil.exportExcel(list, "供应商信息", SupplierVo.class, response);
    }

    /**
     * 获取供应商信息详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("lvju:supplier:query")
    @GetMapping("/{id}")
    public R<SupplierVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(supplierService.queryById(id));
    }

    /**
     * 新增供应商信息
     */
    @SaCheckPermission("lvju:supplier:add")
    @Log(title = "供应商信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SupplierBo bo) {
        return toAjax(supplierService.insertByBo(bo));
    }

    /**
     * 修改供应商信息
     */
    @SaCheckPermission("lvju:supplier:edit")
    @Log(title = "供应商信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SupplierBo bo) {
        return toAjax(supplierService.updateByBo(bo));
    }

    /**
     * 删除供应商信息
     *
     * @param ids 主键串
     */
    @SaCheckPermission("lvju:supplier:remove")
    @Log(title = "供应商信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(supplierService.deleteWithValidByIds(List.of(ids), true));
    }
}

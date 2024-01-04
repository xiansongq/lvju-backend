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
import org.dromara.lvju.domain.vo.TenantsVo;
import org.dromara.lvju.domain.bo.TenantsBo;
import org.dromara.lvju.service.ITenantsService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 租客用户信息
 *
 * @author seky
 * @date 2024-01-04
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/lvju/tenants")
public class TenantsController extends BaseController {

    private final ITenantsService tenantsService;

    /**
     * 查询租客用户信息列表
     */
    @SaCheckPermission("lvju:tenants:list")
    @GetMapping("/list")
    public TableDataInfo<TenantsVo> list(TenantsBo bo, PageQuery pageQuery) {
        return tenantsService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出租客用户信息列表
     */
    @SaCheckPermission("lvju:tenants:export")
    @Log(title = "租客用户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TenantsBo bo, HttpServletResponse response) {
        List<TenantsVo> list = tenantsService.queryList(bo);
        ExcelUtil.exportExcel(list, "租客用户信息", TenantsVo.class, response);
    }

    /**
     * 获取租客用户信息详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("lvju:tenants:query")
    @GetMapping("/{id}")
    public R<TenantsVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String id) {
        return R.ok(tenantsService.queryById(id));
    }

    /**
     * 新增租客用户信息
     */
    @SaCheckPermission("lvju:tenants:add")
    @Log(title = "租客用户信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TenantsBo bo) {
        return toAjax(tenantsService.insertByBo(bo));
    }

    /**
     * 修改租客用户信息
     */
    @SaCheckPermission("lvju:tenants:edit")
    @Log(title = "租客用户信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TenantsBo bo) {
        return toAjax(tenantsService.updateByBo(bo));
    }

    /**
     * 删除租客用户信息
     *
     * @param ids 主键串
     */
    @SaCheckPermission("lvju:tenants:remove")
    @Log(title = "租客用户信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] ids) {
        return toAjax(tenantsService.deleteWithValidByIds(List.of(ids), true));
    }
}

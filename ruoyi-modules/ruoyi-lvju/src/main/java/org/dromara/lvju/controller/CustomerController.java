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
import org.dromara.lvju.domain.vo.CustomerVo;
import org.dromara.lvju.domain.bo.CustomerBo;
import org.dromara.lvju.service.ICustomerService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 客户信息管理
 *
 * @author seky
 * @date 2024-01-02
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/lvju/customer")
public class CustomerController extends BaseController {

    private final ICustomerService customerService;

    /**
     * 查询客户信息管理列表
     */
    @SaCheckPermission("lvju:customer:list")
    @GetMapping("/list")
    public TableDataInfo<CustomerVo> list(CustomerBo bo, PageQuery pageQuery) {
        return customerService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出客户信息管理列表
     */
    @SaCheckPermission("lvju:customer:export")
    @Log(title = "客户信息管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(CustomerBo bo, HttpServletResponse response) {
        List<CustomerVo> list = customerService.queryList(bo);
        ExcelUtil.exportExcel(list, "客户信息管理", CustomerVo.class, response);
    }

    /**
     * 获取客户信息管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("lvju:customer:query")
    @GetMapping("/{id}")
    public R<CustomerVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String id) {
        return R.ok(customerService.queryById(id));
    }

    /**
     * 新增客户信息管理
     */
    @SaCheckPermission("lvju:customer:add")
    @Log(title = "客户信息管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody CustomerBo bo) {
        return toAjax(customerService.insertByBo(bo));
    }

    /**
     * 修改客户信息管理
     */
    @SaCheckPermission("lvju:customer:edit")
    @Log(title = "客户信息管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody CustomerBo bo) {
        return toAjax(customerService.updateByBo(bo));
    }

    /**
     * 删除客户信息管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("lvju:customer:remove")
    @Log(title = "客户信息管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] ids) {
        return toAjax(customerService.deleteWithValidByIds(List.of(ids), true));
    }
}

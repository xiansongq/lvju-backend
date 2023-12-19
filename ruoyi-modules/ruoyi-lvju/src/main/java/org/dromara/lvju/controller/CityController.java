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
import org.dromara.lvju.domain.vo.CityVo;
import org.dromara.lvju.domain.bo.CityBo;
import org.dromara.lvju.service.ICityService;

/**
 * 城市信息管理
 *
 * @author xsQian
 * @date 2023-12-19
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/lvju/city")
public class CityController extends BaseController {

    private final ICityService cityService;

    /**
     * 查询城市信息管理列表
     */
    @SaCheckPermission("lvju:city:list")
    @GetMapping("/list")
    public R<List<CityVo>> list(CityBo bo) {
        List<CityVo> list = cityService.queryList(bo);
        return R.ok(list);
    }

    /**
     * 导出城市信息管理列表
     */
    @SaCheckPermission("lvju:city:export")
    @Log(title = "城市信息管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(CityBo bo, HttpServletResponse response) {
        List<CityVo> list = cityService.queryList(bo);
        ExcelUtil.exportExcel(list, "城市信息管理", CityVo.class, response);
    }

    /**
     * 获取城市信息管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("lvju:city:query")
    @GetMapping("/{id}")
    public R<CityVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(cityService.queryById(id));
    }

    /**
     * 新增城市信息管理
     */
    @SaCheckPermission("lvju:city:add")
    @Log(title = "城市信息管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody CityBo bo) {
        return toAjax(cityService.insertByBo(bo));
    }

    /**
     * 修改城市信息管理
     */
    @SaCheckPermission("lvju:city:edit")
    @Log(title = "城市信息管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody CityBo bo) {
        return toAjax(cityService.updateByBo(bo));
    }

    /**
     * 删除城市信息管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("lvju:city:remove")
    @Log(title = "城市信息管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(cityService.deleteWithValidByIds(List.of(ids), true));
    }
}

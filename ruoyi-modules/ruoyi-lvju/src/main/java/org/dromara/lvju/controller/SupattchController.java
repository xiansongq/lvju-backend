package org.dromara.lvju.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.dromara.lvju.domain.vo.SupInfoVo;
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
import org.dromara.lvju.domain.vo.SupattchVo;
import org.dromara.lvju.domain.bo.SupattchBo;
import org.dromara.lvju.service.ISupattchService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 供应商资质证明材料
 *
 * @author xsQian
 * @date 2024-01-05
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/lvju/supattch")
public class SupattchController extends BaseController {

    private final ISupattchService supattchService;

    @SaCheckPermission("lvju:supattch:list")
    @GetMapping("/all")
    public TableDataInfo<SupInfoVo> all(SupattchBo bo, PageQuery pageQuery) {
        return supattchService.queryAll(bo, pageQuery);
    }
    /**
     * 查询供应商资质证明材料列表
     */
    @SaCheckPermission("lvju:supattch:list")
    @GetMapping("/list")
    public TableDataInfo<SupattchVo> list(SupattchBo bo, PageQuery pageQuery) {
        return supattchService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出供应商资质证明材料列表
     */
    @SaCheckPermission("lvju:supattch:export")
    @Log(title = "供应商资质证明材料", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SupattchBo bo, HttpServletResponse response) {
        List<SupattchVo> list = supattchService.queryList(bo);
        ExcelUtil.exportExcel(list, "供应商资质证明材料", SupattchVo.class, response);
    }

    /**
     * 获取供应商资质证明材料详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("lvju:supattch:query")
    @GetMapping("/{id}")
    public R<SupattchVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(supattchService.queryById(id));
    }

    /**
     * 新增供应商资质证明材料
     */
    @SaCheckPermission("lvju:supattch:add")
    @Log(title = "供应商资质证明材料", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SupattchBo bo) {
        return toAjax(supattchService.insertByBo(bo));
    }

    /**
     * 修改供应商资质证明材料
     */
    @SaCheckPermission("lvju:supattch:edit")
    @Log(title = "供应商资质证明材料", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SupattchBo bo) {
        return toAjax(supattchService.updateByBo(bo));
    }

    /**
     * 删除供应商资质证明材料
     *
     * @param ids 主键串
     */
    @SaCheckPermission("lvju:supattch:remove")
    @Log(title = "供应商资质证明材料", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(supattchService.deleteWithValidByIds(List.of(ids), true));
    }
}

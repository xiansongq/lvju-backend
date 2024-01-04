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
import org.dromara.lvju.domain.vo.RoombookVo;
import org.dromara.lvju.domain.bo.RoombookBo;
import org.dromara.lvju.service.IRoombookService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 房间订单信息
 *
 * @author seky
 * @date 2024-01-02
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/lvju/roombook")
public class RoombookController extends BaseController {

    private final IRoombookService roombookService;

    /**
     * 查询房间订单信息列表
     */
    @SaCheckPermission("lvju:roombook:list")
    @GetMapping("/list")
    public TableDataInfo<RoombookVo> list(RoombookBo bo, PageQuery pageQuery) {
        return roombookService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出房间订单信息列表
     */
    @SaCheckPermission("lvju:roombook:export")
    @Log(title = "房间订单信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(RoombookBo bo, HttpServletResponse response) {
        List<RoombookVo> list = roombookService.queryList(bo);
        ExcelUtil.exportExcel(list, "房间订单信息", RoombookVo.class, response);
    }

    /**
     * 获取房间订单信息详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("lvju:roombook:query")
    @GetMapping("/{id}")
    public R<RoombookVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String id) {
        return R.ok(roombookService.queryById(id));
    }

    /**
     * 新增房间订单信息
     */
    @SaCheckPermission("lvju:roombook:add")
    @Log(title = "房间订单信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody RoombookBo bo) {
        return toAjax(roombookService.insertByBo(bo));
    }

    /**
     * 修改房间订单信息
     */
    @SaCheckPermission("lvju:roombook:edit")
    @Log(title = "房间订单信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody RoombookBo bo) {
        return toAjax(roombookService.updateByBo(bo));
    }

    /**
     * 删除房间订单信息
     *
     * @param ids 主键串
     */
    @SaCheckPermission("lvju:roombook:remove")
    @Log(title = "房间订单信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] ids) {
        return toAjax(roombookService.deleteWithValidByIds(List.of(ids), true));
    }
}

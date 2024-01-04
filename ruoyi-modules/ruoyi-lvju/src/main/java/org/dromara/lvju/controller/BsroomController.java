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
import org.dromara.lvju.domain.vo.BsroomVo;
import org.dromara.lvju.domain.bo.BsroomBo;
import org.dromara.lvju.service.IBsroomService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 房间详细信息
 *
 * @author seky
 * @date 2024-01-02
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/lvju/bsroom")
public class BsroomController extends BaseController {

    private final IBsroomService bsroomService;

    /**
     * 查询房间详细信息列表
     */
    @SaCheckPermission("lvju:bsroom:list")
    @GetMapping("/list")
    public TableDataInfo<BsroomVo> list(BsroomBo bo, PageQuery pageQuery) {
        return bsroomService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出房间详细信息列表
     */
    @SaCheckPermission("lvju:bsroom:export")
    @Log(title = "房间详细信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BsroomBo bo, HttpServletResponse response) {
        List<BsroomVo> list = bsroomService.queryList(bo);
        ExcelUtil.exportExcel(list, "房间详细信息", BsroomVo.class, response);
    }

    /**
     * 获取房间详细信息详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("lvju:bsroom:query")
    @GetMapping("/{id}")
    public R<BsroomVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String id) {
        return R.ok(bsroomService.queryById(id));
    }

    /**
     * 新增房间详细信息
     */
    @SaCheckPermission("lvju:bsroom:add")
    @Log(title = "房间详细信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BsroomBo bo) {
        return toAjax(bsroomService.insertByBo(bo));
    }

    /**
     * 修改房间详细信息
     */
    @SaCheckPermission("lvju:bsroom:edit")
    @Log(title = "房间详细信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BsroomBo bo) {
        return toAjax(bsroomService.updateByBo(bo));
    }

    /**
     * 删除房间详细信息
     *
     * @param ids 主键串
     */
    @SaCheckPermission("lvju:bsroom:remove")
    @Log(title = "房间详细信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] ids) {
        return toAjax(bsroomService.deleteWithValidByIds(List.of(ids), true));
    }
}

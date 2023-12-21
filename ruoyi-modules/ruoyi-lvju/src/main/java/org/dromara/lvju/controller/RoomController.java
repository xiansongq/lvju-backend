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
import org.dromara.lvju.domain.vo.RoomVo;
import org.dromara.lvju.domain.bo.RoomBo;
import org.dromara.lvju.service.IRoomService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 房间信息管理
 *
 * @author xsQian
 * @date 2023-12-21
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/lvju/room")
public class RoomController extends BaseController {

    private final IRoomService roomService;

    /**
     * 查询房间信息管理列表
     */
    @SaCheckPermission("lvju:room:list")
    @GetMapping("/list")
    public TableDataInfo<RoomVo> list(RoomBo bo, PageQuery pageQuery) {
        return roomService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出房间信息管理列表
     */
    @SaCheckPermission("lvju:room:export")
    @Log(title = "房间信息管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(RoomBo bo, HttpServletResponse response) {
        List<RoomVo> list = roomService.queryList(bo);
        ExcelUtil.exportExcel(list, "房间信息管理", RoomVo.class, response);
    }

    /**
     * 获取房间信息管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("lvju:room:query")
    @GetMapping("/{id}")
    public R<RoomVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(roomService.queryById(id));
    }

    /**
     * 新增房间信息管理
     */
    @SaCheckPermission("lvju:room:add")
    @Log(title = "房间信息管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody RoomBo bo) {
        return toAjax(roomService.insertByBo(bo));
    }

    /**
     * 修改房间信息管理
     */
    @SaCheckPermission("lvju:room:edit")
    @Log(title = "房间信息管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody RoomBo bo) {
        return toAjax(roomService.updateByBo(bo));
    }

    /**
     * 删除房间信息管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("lvju:room:remove")
    @Log(title = "房间信息管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(roomService.deleteWithValidByIds(List.of(ids), true));
    }
}

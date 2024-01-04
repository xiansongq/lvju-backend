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
import org.dromara.lvju.domain.vo.RoomstusVo;
import org.dromara.lvju.domain.bo.RoomstusBo;
import org.dromara.lvju.service.IRoomstusService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 房间预定信息
 *
 * @author seky
 * @date 2024-01-02
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/lvju/roomstus")
public class RoomstusController extends BaseController {

    private final IRoomstusService roomstusService;

    /**
     * 查询房间预定信息列表
     */
    @SaCheckPermission("lvju:roomstus:list")
    @GetMapping("/list")
    public TableDataInfo<RoomstusVo> list(RoomstusBo bo, PageQuery pageQuery) {
        return roomstusService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出房间预定信息列表
     */
    @SaCheckPermission("lvju:roomstus:export")
    @Log(title = "房间预定信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(RoomstusBo bo, HttpServletResponse response) {
        List<RoomstusVo> list = roomstusService.queryList(bo);
        ExcelUtil.exportExcel(list, "房间预定信息", RoomstusVo.class, response);
    }

    /**
     * 获取房间预定信息详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("lvju:roomstus:query")
    @GetMapping("/{id}")
    public R<RoomstusVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String id) {
        return R.ok(roomstusService.queryById(id));
    }

    /**
     * 新增房间预定信息
     */
    @SaCheckPermission("lvju:roomstus:add")
    @Log(title = "房间预定信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody RoomstusBo bo) {
        return toAjax(roomstusService.insertByBo(bo));
    }

    /**
     * 修改房间预定信息
     */
    @SaCheckPermission("lvju:roomstus:edit")
    @Log(title = "房间预定信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody RoomstusBo bo) {
        return toAjax(roomstusService.updateByBo(bo));
    }

    /**
     * 删除房间预定信息
     *
     * @param ids 主键串
     */
    @SaCheckPermission("lvju:roomstus:remove")
    @Log(title = "房间预定信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] ids) {
        return toAjax(roomstusService.deleteWithValidByIds(List.of(ids), true));
    }
}

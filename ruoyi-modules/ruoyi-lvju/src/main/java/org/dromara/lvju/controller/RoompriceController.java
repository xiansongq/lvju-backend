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
import org.dromara.lvju.domain.vo.RoompriceVo;
import org.dromara.lvju.domain.bo.RoompriceBo;
import org.dromara.lvju.service.IRoompriceService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 房间价格信息
 *
 * @author seky
 * @date 2024-01-02
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/lvju/roomprice")
public class RoompriceController extends BaseController {

    private final IRoompriceService roompriceService;

    /**
     * 查询房间价格信息列表
     */
    @SaCheckPermission("lvju:roomprice:list")
    @GetMapping("/list")
    public TableDataInfo<RoompriceVo> list(RoompriceBo bo, PageQuery pageQuery) {
        return roompriceService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出房间价格信息列表
     */
    @SaCheckPermission("lvju:roomprice:export")
    @Log(title = "房间价格信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(RoompriceBo bo, HttpServletResponse response) {
        List<RoompriceVo> list = roompriceService.queryList(bo);
        ExcelUtil.exportExcel(list, "房间价格信息", RoompriceVo.class, response);
    }

    /**
     * 获取房间价格信息详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("lvju:roomprice:query")
    @GetMapping("/{id}")
    public R<RoompriceVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String id) {
        return R.ok(roompriceService.queryById(id));
    }

    /**
     * 新增房间价格信息
     */
    @SaCheckPermission("lvju:roomprice:add")
    @Log(title = "房间价格信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody RoompriceBo bo) {
        return toAjax(roompriceService.insertByBo(bo));
    }

    /**
     * 修改房间价格信息
     */
    @SaCheckPermission("lvju:roomprice:edit")
    @Log(title = "房间价格信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody RoompriceBo bo) {
        return toAjax(roompriceService.updateByBo(bo));
    }

    /**
     * 删除房间价格信息
     *
     * @param ids 主键串
     */
    @SaCheckPermission("lvju:roomprice:remove")
    @Log(title = "房间价格信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] ids) {
        return toAjax(roompriceService.deleteWithValidByIds(List.of(ids), true));
    }
}

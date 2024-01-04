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
import org.dromara.lvju.domain.vo.HnoticesVo;
import org.dromara.lvju.domain.bo.HnoticesBo;
import org.dromara.lvju.service.IHnoticesService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 房源预定须知
 *
 * @author seky
 * @date 2024-01-02
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/lvju/hnotices")
public class HnoticesController extends BaseController {

    private final IHnoticesService hnoticesService;

    /**
     * 查询房源预定须知列表
     */
    @SaCheckPermission("lvju:hnotices:list")
    @GetMapping("/list")
    public TableDataInfo<HnoticesVo> list(HnoticesBo bo, PageQuery pageQuery) {
        return hnoticesService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出房源预定须知列表
     */
    @SaCheckPermission("lvju:hnotices:export")
    @Log(title = "房源预定须知", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HnoticesBo bo, HttpServletResponse response) {
        List<HnoticesVo> list = hnoticesService.queryList(bo);
        ExcelUtil.exportExcel(list, "房源预定须知", HnoticesVo.class, response);
    }

    /**
     * 获取房源预定须知详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("lvju:hnotices:query")
    @GetMapping("/{id}")
    public R<HnoticesVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String id) {
        return R.ok(hnoticesService.queryById(id));
    }

    /**
     * 新增房源预定须知
     */
    @SaCheckPermission("lvju:hnotices:add")
    @Log(title = "房源预定须知", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody HnoticesBo bo) {
        return toAjax(hnoticesService.insertByBo(bo));
    }

    /**
     * 修改房源预定须知
     */
    @SaCheckPermission("lvju:hnotices:edit")
    @Log(title = "房源预定须知", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody HnoticesBo bo) {
        return toAjax(hnoticesService.updateByBo(bo));
    }

    /**
     * 删除房源预定须知
     *
     * @param ids 主键串
     */
    @SaCheckPermission("lvju:hnotices:remove")
    @Log(title = "房源预定须知", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] ids) {
        return toAjax(hnoticesService.deleteWithValidByIds(List.of(ids), true));
    }
}

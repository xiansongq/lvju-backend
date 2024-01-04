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
import org.dromara.lvju.domain.vo.HtagsVo;
import org.dromara.lvju.domain.bo.HtagsBo;
import org.dromara.lvju.service.IHtagsService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 房源标签信息
 *
 * @author seky
 * @date 2024-01-02
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/lvju/htags")
public class HtagsController extends BaseController {

    private final IHtagsService htagsService;

    /**
     * 查询房源标签信息列表
     */
    @SaCheckPermission("lvju:htags:list")
    @GetMapping("/list")
    public TableDataInfo<HtagsVo> list(HtagsBo bo, PageQuery pageQuery) {
        return htagsService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出房源标签信息列表
     */
    @SaCheckPermission("lvju:htags:export")
    @Log(title = "房源标签信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HtagsBo bo, HttpServletResponse response) {
        List<HtagsVo> list = htagsService.queryList(bo);
        ExcelUtil.exportExcel(list, "房源标签信息", HtagsVo.class, response);
    }

    /**
     * 获取房源标签信息详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("lvju:htags:query")
    @GetMapping("/{id}")
    public R<HtagsVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String id) {
        return R.ok(htagsService.queryById(id));
    }

    /**
     * 新增房源标签信息
     */
    @SaCheckPermission("lvju:htags:add")
    @Log(title = "房源标签信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody HtagsBo bo) {
        return toAjax(htagsService.insertByBo(bo));
    }

    /**
     * 修改房源标签信息
     */
    @SaCheckPermission("lvju:htags:edit")
    @Log(title = "房源标签信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody HtagsBo bo) {
        return toAjax(htagsService.updateByBo(bo));
    }

    /**
     * 删除房源标签信息
     *
     * @param ids 主键串
     */
    @SaCheckPermission("lvju:htags:remove")
    @Log(title = "房源标签信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] ids) {
        return toAjax(htagsService.deleteWithValidByIds(List.of(ids), true));
    }
}

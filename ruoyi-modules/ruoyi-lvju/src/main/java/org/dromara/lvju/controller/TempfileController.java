package org.dromara.lvju.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.dromara.system.service.ISysOssService;
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
import org.dromara.lvju.domain.vo.TempfileVo;
import org.dromara.lvju.domain.bo.TempfileBo;
import org.dromara.lvju.service.ITempfileService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 模板文件信息管理
 *
 * @author xsQian
 * @date 2024-01-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/lvju/tempfile")
public class TempfileController extends BaseController {

    private final ITempfileService tempfileService;
    private final ISysOssService service;

    /**
     * 查询模板文件信息管理列表
     */
    @SaCheckPermission("lvju:tempfile:list")
    @GetMapping("/list")
    public TableDataInfo<TempfileVo> list(TempfileBo bo, PageQuery pageQuery) {
        return tempfileService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出模板文件信息管理列表
     */
    @SaCheckPermission("lvju:tempfile:export")
    @Log(title = "模板文件信息管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TempfileBo bo, HttpServletResponse response) {
        List<TempfileVo> list = tempfileService.queryList(bo);
        ExcelUtil.exportExcel(list, "模板文件信息管理", TempfileVo.class, response);
    }

    /**
     * 获取模板文件信息管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("lvju:tempfile:query")
    @GetMapping("/{id}")
    public R<TempfileVo> getInfo(@NotNull(message = "主键不能为空")
                                 @PathVariable Long id) {
        return R.ok(tempfileService.queryById(id));
    }

    /**
     * 新增模板文件信息管理
     */
    @SaCheckPermission("lvju:tempfile:add")
    @Log(title = "模板文件信息管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TempfileBo bo) {
        return toAjax(tempfileService.insertByBo(bo));
    }

    /**
     * 修改模板文件信息管理
     */
    @SaCheckPermission("lvju:tempfile:edit")
    @Log(title = "模板文件信息管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TempfileBo bo) {
        return toAjax(tempfileService.updateByBo(bo));
    }

    /**
     * 删除模板文件信息管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("lvju:tempfile:remove")
    @Log(title = "模板文件信息管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        /*获取对应的详细信息*/
        for (Long id : ids) {
            TempfileVo vo = tempfileService.queryById(id);
            /*删除对应的文件*/
            try {
                service.deleteWithValidByIds(List.of(vo.getOssId()), true);
            } catch (Exception e) {
                return toAjax(0);
            }
            return toAjax(tempfileService.deleteWithValidByIds(List.of(id), true));

        }
        return toAjax(1);
    }
}

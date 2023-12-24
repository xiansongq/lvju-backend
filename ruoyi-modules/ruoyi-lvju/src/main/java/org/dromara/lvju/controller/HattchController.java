package org.dromara.lvju.controller;

import java.io.File;
import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import lombok.val;
import org.dromara.lvju.utils.FileUtils;
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
import org.dromara.lvju.domain.vo.HattchVo;
import org.dromara.lvju.domain.bo.HattchBo;
import org.dromara.lvju.service.IHattchService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 房源附件信息
 *
 * @author xsQian
 * @date 2023-12-24
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/lvju/hattch")
public class HattchController extends BaseController {

    private final IHattchService hattchService;

    /**
     * 查询房源附件信息列表
     */
    @SaCheckPermission("lvju:hattch:list")
    @GetMapping("/list")
    public TableDataInfo<HattchVo> list(HattchBo bo, PageQuery pageQuery) {
        return hattchService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出房源附件信息列表
     */
    @SaCheckPermission("lvju:hattch:export")
    @Log(title = "房源附件信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HattchBo bo, HttpServletResponse response) {
        List<HattchVo> list = hattchService.queryList(bo);
        ExcelUtil.exportExcel(list, "房源附件信息", HattchVo.class, response);
    }

    /**
     * 获取房源附件信息详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("lvju:hattch:query")
    @GetMapping("/{id}")
    public R<HattchVo> getInfo(@NotNull(message = "主键不能为空")
                               @PathVariable String id) {
        return R.ok(hattchService.queryById(id));
    }

    /**
     * 新增房源附件信息
     */
    @SaCheckPermission("lvju:hattch:add")
    @Log(title = "房源附件信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody HattchBo bo) {
        return toAjax(hattchService.insertByBo(bo));
    }

    /**
     * 修改房源附件信息
     */
    @SaCheckPermission("lvju:hattch:edit")
    @Log(title = "房源附件信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody HattchBo bo) {
        /*检查前后两次的文件 路径是否变化*/
        if (!bo.getOldPath().equals(bo.getPath())) {
            /* 删除以前的文件 */
            if (FileUtils.delFile(bo.getOldPath()) != 1) {
                /*源文件删除失败 不更新数据*/
                /*先删除新上传的文件*/
                FileUtils.delFile(bo.getPath());
                return R.fail("修改失败");
            }
        }
        /*直接修改文件*/
        return toAjax(hattchService.updateByBo(bo));
    }

    /**
     * 删除房源附件信息
     *
     * @param ids 主键串
     */
    @SaCheckPermission("lvju:hattch:remove")
    @Log(title = "房源附件信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] ids) {
        /* 1.首先需要先删除对应的文件*/
        /*2.根据传入的获取对应的信息*/
        for (String id : ids) {
            HattchVo hattchVo = hattchService.queryById(id);
            if (FileUtils.delFile(hattchVo.getPath()) == 1) {
                hattchService.deleteWithValidByIds(List.of(id), true);
            } else {
                return R.fail(id.toString() + "删除失败");
            }
        }
        return R.ok("删除成功");
    }
}

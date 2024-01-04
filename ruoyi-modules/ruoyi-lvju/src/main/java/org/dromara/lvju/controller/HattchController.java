package org.dromara.lvju.controller;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import lombok.val;
import org.dromara.lvju.utils.FileUtils;
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
    private final ISysOssService ossService;

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
        // 首先根据id为什么查询Hattch的信息
        val hattchVo = hattchService.queryById(bo.getId());
        // 判断提交的ossId与查询的是否相同
        /*检查前后两次的文件 ossId是否相同*/
        /*不同则是更新了文件 需要删除旧的文件*/
        if (bo.getOssId() != hattchVo.getOssId()) {
            ossService.deleteWithValidByIds(Arrays.asList(hattchVo.getOssId()), true);
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


        List<String> idList = Arrays.asList(ids);
        /*2.根据传入的获取对应的信息*/
        for (String id : idList) {
            HattchVo hattchVo = hattchService.queryById(id);
            try {
                /* 1.删除对应的文件*/
                ossService.deleteWithValidByIds(List.of(hattchVo.getOssId()), true);

            } catch (Exception e) {
                idList.remove(id);
                return R.ok(hattchVo.getId() + "删除失败");
            }
        }
        hattchService.deleteWithValidByIds(idList, true);
        return R.ok("删除成功");
    }
}

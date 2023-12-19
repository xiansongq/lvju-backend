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
import org.dromara.lvju.domain.vo.HouseVo;
import org.dromara.lvju.domain.bo.HouseBo;
import org.dromara.lvju.service.IHouseService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 房源信息表
 *
 * @author xsQian
 * @date 2023-12-19
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/lvju/house")
public class HouseController extends BaseController {

    private final IHouseService houseService;

    /**
     * 查询房源信息表列表
     */
    @SaCheckPermission("lvju:house:list")
    @GetMapping("/list")
    public TableDataInfo<HouseVo> list(HouseBo bo, PageQuery pageQuery) {
        return houseService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出房源信息表列表
     */
    @SaCheckPermission("lvju:house:export")
    @Log(title = "房源信息表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HouseBo bo, HttpServletResponse response) {
        List<HouseVo> list = houseService.queryList(bo);
        ExcelUtil.exportExcel(list, "房源信息表", HouseVo.class, response);
    }

    /**
     * 获取房源信息表详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("lvju:house:query")
    @GetMapping("/{id}")
    public R<HouseVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(houseService.queryById(id));
    }

    /**
     * 新增房源信息表
     */
    @SaCheckPermission("lvju:house:add")
    @Log(title = "房源信息表", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody HouseBo bo) {
        return toAjax(houseService.insertByBo(bo));
    }

    /**
     * 修改房源信息表
     */
    @SaCheckPermission("lvju:house:edit")
    @Log(title = "房源信息表", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody HouseBo bo) {
        return toAjax(houseService.updateByBo(bo));
    }

    /**
     * 删除房源信息表
     *
     * @param ids 主键串
     */
    @SaCheckPermission("lvju:house:remove")
    @Log(title = "房源信息表", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(houseService.deleteWithValidByIds(List.of(ids), true));
    }
}

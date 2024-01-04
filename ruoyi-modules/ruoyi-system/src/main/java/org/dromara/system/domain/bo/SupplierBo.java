package org.dromara.system.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.system.domain.Supplier;

/**
 * 供应商信息业务对象 lv_supplier
 *
 * @author xsQian
 * @date 2023-12-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = Supplier.class, reverseConvertGenerate = false)
public class SupplierBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 用户编号
     */
    @NotBlank(message = "用户编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String userid;

    /**
     * 公司名称
     */
    @NotBlank(message = "公司名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String company;

    /**
     * 联系人
     */
    @NotBlank(message = "联系人不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 身份证号
     */
    @NotBlank(message = "身份证号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String sdnum;

    /**
     * 联系电话
     */
    @NotBlank(message = "联系电话不能为空", groups = { AddGroup.class, EditGroup.class })
    private String iphone;

    /**
     * 1：供应商 2：代理商 3：个人
     */
    @NotNull(message = "1：供应商 2：代理商 3：个人不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long stype;


}

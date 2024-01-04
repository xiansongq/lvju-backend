package org.dromara.lvju.domain.bo;

import org.dromara.lvju.domain.Tenants;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 租客用户信息业务对象 lv_tenant
 *
 * @author seky
 * @date 2024-01-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = Tenants.class, reverseConvertGenerate = false)
public class TenantsBo extends BaseEntity {

    /**
     * id
     */
    @NotBlank(message = "id不能为空", groups = { EditGroup.class })
    private String id;

    /**
     * 客户编号
     */
    private String customerid;

    /**
     * 预定房间编号
     */
    private String roomstusid;

    /**
     * 租客名称
     */
    @NotBlank(message = "租客名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 身份证
     */
    @NotBlank(message = "身份证不能为空", groups = { AddGroup.class, EditGroup.class })
    private String sdno;

    /**
     * 电话号码
     */
    @NotBlank(message = "电话号码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String iphone;


}

package org.dromara.lvju.domain.bo;

import org.dromara.lvju.domain.Supattch;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 供应商资质证明材料业务对象 lv_supattch
 *
 * @author xsQian
 * @date 2023-12-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = Supattch.class, reverseConvertGenerate = false)
public class SupattchBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long userid;

    /**
     * 附件编号
     */
    @NotBlank(message = "附件编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String supplierid;

    /**
     * 附件名称
     */
    @NotBlank(message = "附件名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 文件路径
     */
    @NotBlank(message = "文件路径不能为空", groups = { AddGroup.class, EditGroup.class })
    private String path;

    /**
     * 文件类型/多种类型存放在字典
     */
    @NotNull(message = "文件类型/多种类型存放在字典不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long stype;

    /**
     * 是否删除
     */
    @NotNull(message = "是否删除不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long ideleted;


}

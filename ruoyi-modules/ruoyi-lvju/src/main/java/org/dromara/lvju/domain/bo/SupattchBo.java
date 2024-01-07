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
 * @date 2024-01-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = Supattch.class, reverseConvertGenerate = false)
public class SupattchBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long userid;

    /**
     * ossId
     */
    @NotNull(message = "ossId不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long ossId;

    /**
     * 文件名称
     */
    @NotBlank(message = "文件名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String typeName;

    /**
     * 文件类型值
     */
    @NotNull(message = "文件类型值不能为空", groups = {AddGroup.class, EditGroup.class})
    private Integer typeValue;


    /**
     * 表示是法人 还是个人
     */
    private Integer peopleType;


}

package org.dromara.lvju.domain.bo;

import org.dromara.lvju.domain.Hnotices;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 房源预定须知业务对象 lv_hnotices
 *
 * @author seky
 * @date 2024-01-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = Hnotices.class, reverseConvertGenerate = false)
public class HnoticesBo extends BaseEntity {

    /**
     * id
     */
    @NotBlank(message = "id不能为空", groups = { EditGroup.class })
    private String id;

    /**
     * 房源编号
     */
    @NotBlank(message = "房源编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String houseid;

    /**
     * 须知名称
     */
    @NotBlank(message = "须知名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 须知值
     */
    private String val;

    /**
     * 须知类型
     */
    @NotNull(message = "须知类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long ntype;


}

package org.dromara.lvju.domain.bo;

import org.dromara.lvju.domain.Bsroom;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 房间详细信息业务对象 lv_bsroom
 *
 * @author seky
 * @date 2024-01-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = Bsroom.class, reverseConvertGenerate = false)
public class BsroomBo extends BaseEntity {

    /**
     * id
     */
    @NotBlank(message = "id不能为空", groups = { EditGroup.class })
    private String id;

    /**
     * 房间编号
     */
    @NotBlank(message = "房间编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String roomid;

    /**
     * 设施名称
     */
    @NotBlank(message = "设施名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 信息类型
     */
    @NotBlank(message = "信息类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String rtype;

    /**
     * 设施值
     */
    @NotBlank(message = "设施值不能为空", groups = { AddGroup.class, EditGroup.class })
    private String val;


}

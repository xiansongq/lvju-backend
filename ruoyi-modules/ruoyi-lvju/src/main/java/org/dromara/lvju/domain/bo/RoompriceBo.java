package org.dromara.lvju.domain.bo;

import org.dromara.lvju.domain.Roomprice;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 房间价格信息业务对象 lv_roomprice
 *
 * @author seky
 * @date 2024-01-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = Roomprice.class, reverseConvertGenerate = false)
public class RoompriceBo extends BaseEntity {

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
     * 房间价格
     */
    @NotBlank(message = "房间价格不能为空", groups = { AddGroup.class, EditGroup.class })
    private String price;

    /**
     * 优惠力度
     */
    private Long prefeforce;

    /**
     * 价格时间
     */
    @NotBlank(message = "价格时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private String pdate;

    /**
     * 价格是否有效
     */
    @NotNull(message = "价格是否有效不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long isvalid;


}

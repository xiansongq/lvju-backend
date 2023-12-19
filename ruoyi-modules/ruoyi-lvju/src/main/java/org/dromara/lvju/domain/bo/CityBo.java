package org.dromara.lvju.domain.bo;

import org.dromara.lvju.domain.City;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/**
 * 城市信息管理业务对象 lv_city
 *
 * @author xsQian
 * @date 2023-12-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = City.class, reverseConvertGenerate = false)
public class CityBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 是否是热门城市：1是 0不是
     */
    @NotNull(message = "是否是热门城市：1是 0不是不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long ishot;

    /**
     * 父城市id
     */
    @NotNull(message = "父城市id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long parentid;

    /**
     * 经度
     */
    @NotNull(message = "经度不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal logtude;

    /**
     * 维度
     */
    @NotNull(message = "维度不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal latttude;


}

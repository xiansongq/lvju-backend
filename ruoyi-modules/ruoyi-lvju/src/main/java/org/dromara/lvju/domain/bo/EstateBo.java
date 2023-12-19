package org.dromara.lvju.domain.bo;

import com.baomidou.mybatisplus.annotation.TableField;
import org.dromara.lvju.domain.Estate;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/**
 * 小区信息操作业务对象 lv_estate
 *
 * @author xsQian
 * @date 2023-12-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = Estate.class, reverseConvertGenerate = false)
public class EstateBo extends BaseEntity {

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
     * 城市
     */
    @NotNull(message = "城市不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long cityid;

    /**
     * 经度
     */
    @NotNull(message = "经度不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal logtude;

    /**
     * 纬度
     */
    @NotNull(message = "纬度不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal lattude;

    /**
     * 描述
     */
    @NotBlank(message = "描述不能为空", groups = { AddGroup.class, EditGroup.class })
    @TableField(value="`describe`")

    private String describe;

    /**
     * 商业介绍
     */
    @NotBlank(message = "商业介绍不能为空", groups = { AddGroup.class, EditGroup.class })
    private String busdescribe;


}

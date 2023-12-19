package org.dromara.lvju.domain.bo;

import com.baomidou.mybatisplus.annotation.TableField;
import org.dromara.lvju.domain.House;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 房源信息表业务对象 lv_house
 *
 * @author xsQian
 * @date 2023-12-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = House.class, reverseConvertGenerate = false)
public class HouseBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 用户编号
     */
    @NotNull(message = "用户编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long userid;

    /**
     * 供应商编号
     */
    @NotNull(message = "供应商编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long supplierid;

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
     * 小区
     */
    @NotNull(message = "小区不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long estateid;

    /**
     * 描述
     */
    @NotBlank(message = "描述不能为空", groups = { AddGroup.class, EditGroup.class })
    @TableField(value="`describe`")

    private String describe;

    /**
     * 商业描述
     */
    @NotBlank(message = "商业描述不能为空", groups = { AddGroup.class, EditGroup.class })
    private String busdescribe;

    /**
     * 地址
     */
    @NotBlank(message = "地址不能为空", groups = { AddGroup.class, EditGroup.class })
    private String address;

    /**
     * 房间数量
     */
    @NotNull(message = "房间数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long num;


}

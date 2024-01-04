package org.dromara.lvju.domain.bo;

import org.dromara.lvju.domain.Roombook;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 房间订单信息业务对象 lv_roombook
 *
 * @author seky
 * @date 2024-01-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = Roombook.class, reverseConvertGenerate = false)
public class RoombookBo extends BaseEntity {

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
     * 用户编号
     */
    @NotBlank(message = "用户编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String userid;

    /**
     * 客户名称
     */
    @NotBlank(message = "客户名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String customerid;

    /**
     * 供应商
     */
    @NotBlank(message = "供应商不能为空", groups = { AddGroup.class, EditGroup.class })
    private String supplierid;

    /**
     * 价格
     */
    @NotBlank(message = "价格不能为空", groups = { AddGroup.class, EditGroup.class })
    private String price;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 预定编号
     */
    @NotBlank(message = "预定编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String roomstusid;

    /**
     * 起始时间
     */
    @NotBlank(message = "起始时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private String startdate;

    /**
     * 结束时间
     */
    @NotBlank(message = "结束时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private String enddate;

    /**
     * 总天数
     */
    @NotNull(message = "总天数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long totaldays;


}

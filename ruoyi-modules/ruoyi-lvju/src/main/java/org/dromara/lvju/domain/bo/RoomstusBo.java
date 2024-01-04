package org.dromara.lvju.domain.bo;

import org.dromara.lvju.domain.Roomstus;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 房间预定信息业务对象 lv_roomstus
 *
 * @author seky
 * @date 2024-01-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = Roomstus.class, reverseConvertGenerate = false)
public class RoomstusBo extends BaseEntity {

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
     * 客户编号
     */
    @NotBlank(message = "客户编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String userid;

    /**
     * 客户姓名
     */
    @NotBlank(message = "客户姓名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String customerid;

    /**
     * 房间状态
     */
    @NotNull(message = "房间状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long status;

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
     * 预定价格
     */
    @NotNull(message = "预定价格不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long bookprice;

    /**
     * 总金额
     */
    @NotNull(message = "总金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long totalprice;

    /**
     * 总天数
     */
    @NotNull(message = "总天数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long totaldays;


}

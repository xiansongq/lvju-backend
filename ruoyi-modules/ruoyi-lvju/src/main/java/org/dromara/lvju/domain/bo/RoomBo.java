package org.dromara.lvju.domain.bo;

import org.dromara.lvju.domain.Room;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 房间信息管理业务对象 lv_room
 *
 * @author xsQian
 * @date 2023-12-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = Room.class, reverseConvertGenerate = false)
public class RoomBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 房源id
     */
    @NotNull(message = "房源id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long houseid;

    /**
     * 楼栋
     */
    @NotBlank(message = "楼栋不能为空", groups = { AddGroup.class, EditGroup.class })
    private String houseno;

    /**
     * 楼层
     */
    @NotBlank(message = "楼层不能为空", groups = { AddGroup.class, EditGroup.class })
    private String houselv;

    /**
     * 房号
     */
    @NotBlank(message = "房号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String roomno;

    /**
     * 房型
     */
    @NotBlank(message = "房型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String apartment;

    /**
     * 面积
     */
    @NotBlank(message = "面积不能为空", groups = { AddGroup.class, EditGroup.class })
    private String area;

    /**
     * 房间状态
     */
    @NotNull(message = "房间状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long status;

    /**
     * 房间描述
     */
    @NotBlank(message = "房间描述不能为空", groups = { AddGroup.class, EditGroup.class })
    private String describe;

    /**
     * 房间关键字
     */
//    @NotBlank(message = "房间关键字不能为空", groups = { AddGroup.class, EditGroup.class })
    private String keys;

    /**
     * 备注
     */
    private String remarks;


}

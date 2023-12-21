package org.dromara.lvju.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 房间信息管理对象 lv_room
 *
 * @author xsQian
 * @date 2023-12-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("lv_room")
public class Room extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 房源id
     */
    private Long houseid;

    /**
     * 楼栋
     */
    private String houseno;

    /**
     * 楼层
     */
    private String houselv;

    /**
     * 房号
     */
    private String roomno;

    /**
     * 房型
     */
    private String apartment;

    /**
     * 面积
     */
    private String area;

    /**
     * 房间状态
     */
    private Long status;

    /**
     * 房间描述
     */
    @TableField(value = "`describe`")
    private String describe;

    /**
     * 房间关键字
     */
    @TableField(value = "`keys`")

    private String keys;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 删除标志
     */
    @TableLogic
    private String delFlag;


}

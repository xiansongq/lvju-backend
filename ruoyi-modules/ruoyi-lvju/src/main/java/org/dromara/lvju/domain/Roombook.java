package org.dromara.lvju.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 房间订单信息对象 lv_roombook
 *
 * @author seky
 * @date 2024-01-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("lv_roombook")
public class Roombook extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id")
    private String id;

    /**
     * 房间编号
     */
    private String roomid;

    /**
     * 用户编号
     */
    private String userid;

    /**
     * 客户名称
     */
    private String customerid;

    /**
     * 供应商
     */
    private String supplierid;

    /**
     * 价格
     */
    private String price;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 是否删除
     */
    private Long ideleted;

    /**
     * 创建时间
     */
    private String createon;

    /**
     * 修改时间
     */
    private String modifyon;

    /**
     * 预定编号
     */
    private String roomstusid;

    /**
     * 起始时间
     */
    private String startdate;

    /**
     * 结束时间
     */
    private String enddate;

    /**
     * 总天数
     */
    private Long totaldays;


}

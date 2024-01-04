package org.dromara.lvju.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 房间预定信息对象 lv_roomstus
 *
 * @author seky
 * @date 2024-01-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("lv_roomstus")
public class Roomstus extends BaseEntity {

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
     * 客户编号
     */
    private String userid;

    /**
     * 客户姓名
     */
    private String customerid;

    /**
     * 房间状态
     */
    private Long status;

    /**
     * 起始时间
     */
    private String startdate;

    /**
     * 结束时间
     */
    private String enddate;

    /**
     * 预定价格
     */
    private Long bookprice;

    /**
     * 总金额
     */
    private Long totalprice;

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
     * 总天数
     */
    private Long totaldays;


}

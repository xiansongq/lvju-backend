package org.dromara.lvju.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 房间价格信息对象 lv_roomprice
 *
 * @author seky
 * @date 2024-01-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("lv_roomprice")
public class Roomprice extends BaseEntity {

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
     * 房间价格
     */
    private String price;

    /**
     * 优惠力度
     */
    private Long prefeforce;

    /**
     * 价格时间
     */
    private String pdate;

    /**
     * 价格是否有效
     */
    private Long isvalid;

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


}

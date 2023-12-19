package org.dromara.lvju.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

import java.io.Serial;

/**
 * 城市信息管理对象 lv_city
 *
 * @author xsQian
 * @date 2023-12-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("lv_city")
public class City extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 是否是热门城市：1是 0不是
     */
    private Long ishot;

    /**
     * 父城市id
     */
    private Long parentid;

    /**
     * 经度
     */
    private BigDecimal logtude;

    /**
     * 维度
     */
    private BigDecimal latttude;

    /**
     * 是否删除
     */
    @TableLogic
    private String delFlag;


}

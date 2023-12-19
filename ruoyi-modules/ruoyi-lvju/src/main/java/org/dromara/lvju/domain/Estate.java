package org.dromara.lvju.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

import java.io.Serial;

/**
 * 小区信息操作对象 lv_estate
 *
 * @author xsQian
 * @date 2023-12-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("lv_estate")
public class Estate extends BaseEntity {

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
     * 城市
     */
    private Long cityid;

    /**
     * 经度
     */
    private BigDecimal logtude;

    /**
     * 纬度
     */
    private BigDecimal lattude;

    /**
     * 描述
     */

    @TableField(value="`describe`")
    private String describe;

    /**
     * 商业介绍
     */
    private String busdescribe;

    /**
     * 是否删除
     */
    @TableLogic
    private String delFlag;

//    /*城市名称*/
//    private String cityname;


}

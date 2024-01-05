package org.dromara.lvju.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 供应商资质证明材料对象 lv_supattch
 *
 * @author xsQian
 * @date 2024-01-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("lv_supattch")
public class Supattch extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 用户ID
     */
    private Long userid;

    /**
     * ossId
     */
    private Long ossId;

    /**
     * 文件名称
     */
    private String typeName;

    /**
     * 文件类型值
     */
    private Integer typeValue;

    /**
     * 是否删除
     */
    private Integer ideleted;


}

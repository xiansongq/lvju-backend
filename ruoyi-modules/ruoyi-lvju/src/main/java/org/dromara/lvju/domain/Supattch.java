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
 * @date 2023-12-18
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
     * 附件编号
     */
    private String supplierid;

    /**
     * 附件名称
     */
    private String name;

    /**
     * 文件路径
     */
    private String path;

    /**
     * 文件类型/多种类型存放在字典
     */
    private Long stype;

    /**
     * 是否删除
     */
    private Long ideleted;


}

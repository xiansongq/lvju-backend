package org.dromara.lvju.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 房源标签信息对象 lv_htags
 *
 * @author seky
 * @date 2024-01-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("lv_htags")
public class Htags extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id")
    private String id;

    /**
     * 房源编号
     */
    private String houseid;

    /**
     * 标签名称
     */
    private String name;

    /**
     * 状态
     */
    private Long status;

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

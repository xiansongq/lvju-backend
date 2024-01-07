package org.dromara.lvju.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 模板文件信息管理对象 lvju_tempfile
 *
 * @author xsQian
 * @date 2024-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("lvju_tempfile")
public class Tempfile extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 模板名称
     */
    private String tempName;

    /**
     * 文件存储id
     */
    private Long ossId;

    /**
     * 法人/个人
     */
    private Integer agentType;

    /**
     * 删除标志
     */
    @TableLogic
    private String delFlag;


}

package org.dromara.lvju.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 房间详细信息对象 lv_bsroom
 *
 * @author seky
 * @date 2024-01-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("lv_bsroom")
public class Bsroom extends BaseEntity {

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
     * 设施名称
     */
    private String name;

    /**
     * 信息类型
     */
    private String rtype;

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
     * 设施值
     */
    private String val;


}

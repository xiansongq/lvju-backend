package org.dromara.lvju.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 房源附件信息对象 lv_hattch
 *
 * @author xsQian
 * @date 2023-12-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("lv_hattch")
public class Hattch extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id")
    private String id;

    /**
     * 房源id
     */
    private Long houseid;

    /**
     * 原始名称
     */
    private String name;

    /**
     * 保存路径
     */
    private String path;

    /**
     * 文件类型 0图片 1视频
     */
    private String htype;

    /**
     * 删除标志
     */
    @TableLogic
    private String delFlag;


}

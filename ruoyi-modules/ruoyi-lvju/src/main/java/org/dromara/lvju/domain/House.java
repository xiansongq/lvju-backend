package org.dromara.lvju.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 房源信息表对象 lv_house
 *
 * @author xsQian
 * @date 2023-12-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("lv_house")
public class House extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 用户编号
     */
    private Long userid;

    /**
     * 供应商编号
     */
    private Long supplierid;

    /**
     * 名称
     */
    private String name;

    /**
     * 城市
     */
    private Long cityid;

    /**
     * 小区
     */
    private Long estateid;

    /**
     * 描述
     */
    @TableField(value="`describe`")

    private String describe;

    /**
     * 商业描述
     */
    private String busdescribe;

    /**
     * 地址
     */
    private String address;

    /**
     * 房间数量
     */
    private Long num;

    /**
     * 删除标志
     */
    @TableLogic
    private String delFlag;


}

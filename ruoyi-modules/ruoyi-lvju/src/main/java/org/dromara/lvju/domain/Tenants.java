package org.dromara.lvju.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 租客用户信息对象 lv_tenant
 *
 * @author seky
 * @date 2024-01-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("lv_tenant")
public class Tenants extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id")
    private String id;

    /**
     * 客户编号
     */
    private String customerid;

    /**
     * 预定房间编号
     */
    private String roomstusid;

    /**
     * 租客名称
     */
    private String name;

    /**
     * 身份证
     */
    private String sdno;

    /**
     * 电话号码
     */
    private String iphone;

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

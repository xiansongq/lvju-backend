package org.dromara.system.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;

/**
 * 供应商信息对象 lv_supplier
 *
 * @author xsQian
 * @date 2023-12-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("lv_supplier")
public class Supplier extends BaseEntity {

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
    private String userid;

    /**
     * 公司名称
     */
    private String company;

    /**
     * 联系人
     */
    private String name;

    /**
     * 身份证号
     */
    private String sdnum;

    /**
     * 联系电话
     */
    private String iphone;

    /**
     * 1：供应商 2：代理商 3：个人
     */
    private Long stype;

    /**
     * 1：法人 2 个人
     */
    private Long agentType;

    /**
     * 是否删除
     */
    @TableLogic
    private String delFlag;


}

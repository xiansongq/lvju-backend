package org.dromara.lvju.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 客户信息管理对象 lv_customer
 *
 * @author seky
 * @date 2024-01-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("lv_customer")
public class Customer extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id")
    private String id;

    /**
     * 用户
     */
    private String userid;

    /**
     * 姓名
     */
    private String sname;

    /**
     * 身份证号码
     */
    private String sdnum;

    /**
     * 性别
     */
    private String ssex;

    /**
     * 出生年月日
     */
    private String sbirth;

    /**
     * 手机号码
     */
    private String iphone;

    /**
     * 紧急联系电话
     */
    private String imiphone;

    /**
     * 紧急联系人
     */
    private String imname;

    /**
     * 照片
     */
    private String sphoto;

    /**
     * 积分
     */
    private Long integral;

    /**
     * 住宿时长
     */
    private Long classh;

    /**
     * 等级
     */
    private Long level;

    /**
     * 是否新会员
     */
    private Long isaudi;

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

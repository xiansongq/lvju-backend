package org.dromara.lvju.domain.bo;

import org.dromara.lvju.domain.Customer;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 客户信息管理业务对象 lv_customer
 *
 * @author seky
 * @date 2024-01-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = Customer.class, reverseConvertGenerate = false)
public class CustomerBo extends BaseEntity {

    /**
     * id
     */
    @NotBlank(message = "id不能为空", groups = { EditGroup.class })
    private String id;

    /**
     * 用户
     */
    private String userid;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String sname;

    /**
     * 身份证号码
     */
    @NotBlank(message = "身份证号码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String sdnum;

    /**
     * 性别
     */
    @NotBlank(message = "性别不能为空", groups = { AddGroup.class, EditGroup.class })
    private String ssex;

    /**
     * 出生年月日
     */
    private String sbirth;

    /**
     * 手机号码
     */
    @NotBlank(message = "手机号码不能为空", groups = { AddGroup.class, EditGroup.class })
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


}

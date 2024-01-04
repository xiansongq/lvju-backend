package org.dromara.lvju.domain.vo;

import org.dromara.lvju.domain.Customer;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * 客户信息管理视图对象 lv_customer
 *
 * @author seky
 * @date 2024-01-02
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Customer.class)
public class CustomerVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 姓名
     */
    @ExcelProperty(value = "姓名")
    private String sname;

    /**
     * 身份证号码
     */
    @ExcelProperty(value = "身份证号码")
    private String sdnum;

    /**
     * 性别
     */
    @ExcelProperty(value = "性别")
    private String ssex;

    /**
     * 手机号码
     */
    @ExcelProperty(value = "手机号码")
    private String iphone;

    /**
     * 紧急联系电话
     */
    @ExcelProperty(value = "紧急联系电话")
    private String imiphone;

    /**
     * 紧急联系人
     */
    @ExcelProperty(value = "紧急联系人")
    private String imname;


}

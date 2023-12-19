package org.dromara.lvju.domain.vo;

import org.dromara.lvju.domain.Supplier;
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
 * 供应商信息视图对象 lv_supplier
 *
 * @author xsQian
 * @date 2023-12-19
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Supplier.class)
public class SupplierVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;

    /**
     * 用户编号
     */
    @ExcelProperty(value = "用户编号")
    private String userid;

    /**
     * 公司名称
     */
    @ExcelProperty(value = "公司名称")
    private String company;

    /**
     * 联系人
     */
    @ExcelProperty(value = "联系人")
    private String name;

    /**
     * 身份证号
     */
    @ExcelProperty(value = "身份证号")
    private String sdnum;

    /**
     * 联系电话
     */
    @ExcelProperty(value = "联系电话")
    private String iphone;

    /**
     * 1：供应商 2：代理商 3：个人
     */
    @ExcelProperty(value = "1：供应商 2：代理商 3：个人", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "lvju_supplier_type")
    private Long stype;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;


}

package org.dromara.lvju.domain.vo;

import org.dromara.lvju.domain.Tenants;
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
 * 租客用户信息视图对象 lv_tenant
 *
 * @author seky
 * @date 2024-01-04
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Tenants.class)
public class TenantsVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 预定房间编号
     */
    @ExcelProperty(value = "预定房间编号")
    private String roomstusid;

    /**
     * 租客名称
     */
    @ExcelProperty(value = "租客名称")
    private String name;

    /**
     * 身份证
     */
    @ExcelProperty(value = "身份证")
    private String sdno;

    /**
     * 电话号码
     */
    @ExcelProperty(value = "电话号码")
    private String iphone;


}

package org.dromara.lvju.domain.vo;

import org.dromara.lvju.domain.Roombook;
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
 * 房间订单信息视图对象 lv_roombook
 *
 * @author seky
 * @date 2024-01-02
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Roombook.class)
public class RoombookVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 房间编号
     */
    @ExcelProperty(value = "房间编号")
    private String roomid;

    /**
     * 客户名称
     */
    @ExcelProperty(value = "客户名称")
    private String customerid;

    /**
     * 供应商
     */
    @ExcelProperty(value = "供应商")
    private String supplierid;

    /**
     * 价格
     */
    @ExcelProperty(value = "价格")
    private String price;

    /**
     * 预定编号
     */
    @ExcelProperty(value = "预定编号")
    private String roomstusid;

    /**
     * 起始时间
     */
    @ExcelProperty(value = "起始时间")
    private String startdate;

    /**
     * 结束时间
     */
    @ExcelProperty(value = "结束时间")
    private String enddate;

    /**
     * 总天数
     */
    @ExcelProperty(value = "总天数")
    private Long totaldays;


}

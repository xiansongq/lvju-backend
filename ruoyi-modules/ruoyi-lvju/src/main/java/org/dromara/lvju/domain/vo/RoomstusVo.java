package org.dromara.lvju.domain.vo;

import org.dromara.lvju.domain.Roomstus;
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
 * 房间预定信息视图对象 lv_roomstus
 *
 * @author seky
 * @date 2024-01-02
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Roomstus.class)
public class RoomstusVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private String id;

    /**
     * 房间编号
     */
    @ExcelProperty(value = "房间编号")
    private String roomid;

    /**
     * 客户姓名
     */
    @ExcelProperty(value = "客户姓名")
    private String customerid;

    /**
     * 房间状态
     */
    @ExcelProperty(value = "房间状态")
    private Long status;

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
     * 预定价格
     */
    @ExcelProperty(value = "预定价格")
    private Long bookprice;

    /**
     * 总金额
     */
    @ExcelProperty(value = "总金额")
    private Long totalprice;

    /**
     * 总天数
     */
    @ExcelProperty(value = "总天数")
    private Long totaldays;


}

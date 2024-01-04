package org.dromara.lvju.domain.vo;

import org.dromara.lvju.domain.Roomprice;
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
 * 房间价格信息视图对象 lv_roomprice
 *
 * @author seky
 * @date 2024-01-02
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Roomprice.class)
public class RoompriceVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 房间编号
     */
    @ExcelProperty(value = "房间编号")
    private String roomid;

    /**
     * 房间价格
     */
    @ExcelProperty(value = "房间价格")
    private String price;

    /**
     * 优惠力度
     */
    @ExcelProperty(value = "优惠力度")
    private Long prefeforce;

    /**
     * 价格时间
     */
    @ExcelProperty(value = "价格时间")
    private String pdate;

    /**
     * 价格是否有效
     */
    @ExcelProperty(value = "价格是否有效")
    private Long isvalid;


}

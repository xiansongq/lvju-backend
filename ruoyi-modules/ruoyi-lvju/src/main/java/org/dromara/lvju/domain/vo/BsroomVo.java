package org.dromara.lvju.domain.vo;

import org.dromara.lvju.domain.Bsroom;
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
 * 房间详细信息视图对象 lv_bsroom
 *
 * @author seky
 * @date 2024-01-02
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Bsroom.class)
public class BsroomVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 房间编号
     */
    @ExcelProperty(value = "房间编号")
    private String roomid;

    /**
     * 设施名称
     */
    @ExcelProperty(value = "设施名称")
    private String name;

    /**
     * 信息类型
     */
    @ExcelProperty(value = "信息类型")
    private String rtype;

    /**
     * 设施值
     */
    @ExcelProperty(value = "设施值")
    private String val;


}

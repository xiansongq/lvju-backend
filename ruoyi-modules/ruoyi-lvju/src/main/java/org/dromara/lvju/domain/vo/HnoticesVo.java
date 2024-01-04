package org.dromara.lvju.domain.vo;

import org.dromara.lvju.domain.Hnotices;
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
 * 房源预定须知视图对象 lv_hnotices
 *
 * @author seky
 * @date 2024-01-02
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Hnotices.class)
public class HnoticesVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 房源编号
     */
    @ExcelProperty(value = "房源编号")
    private String houseid;

    /**
     * 须知名称
     */
    @ExcelProperty(value = "须知名称")
    private String name;

    /**
     * 须知值
     */
    @ExcelProperty(value = "须知值")
    private String val;

    /**
     * 须知类型
     */
    @ExcelProperty(value = "须知类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "lv_hnotices_ntype")
    private Long ntype;


}

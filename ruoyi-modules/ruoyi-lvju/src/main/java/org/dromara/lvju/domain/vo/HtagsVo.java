package org.dromara.lvju.domain.vo;

import org.dromara.lvju.domain.Htags;
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
 * 房源标签信息视图对象 lv_htags
 *
 * @author seky
 * @date 2024-01-02
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Htags.class)
public class HtagsVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 房源编号
     */
    @ExcelProperty(value = "房源编号")
    private String houseid;

    /**
     * 标签名称
     */
    @ExcelProperty(value = "标签名称")
    private String name;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态")
    private Long status;


}

package org.dromara.lvju.domain.vo;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
import jakarta.validation.constraints.NotBlank;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.lvju.domain.Estate;
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
 * 小区信息操作视图对象 lv_estate
 *
 * @author xsQian
 * @date 2023-12-19
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Estate.class)
public class EstateVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;

    /**
     * 名称
     */
    @ExcelProperty(value = "名称")
    private String name;

    /**
     * 城市
     */
    @ExcelProperty(value = "城市")
    private Long cityid;

    /**
     * 经度
     */
    @ExcelProperty(value = "经度")
    private BigDecimal logtude;

    /**
     * 纬度
     */
    @ExcelProperty(value = "纬度")
    private BigDecimal lattude;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 描述
     */
    @TableField(value="`describe`")
    @ExcelProperty(value = "描述")
    private String describe;

    /**
     * 商业介绍
     */
    @ExcelProperty(value = "商业介绍")
    private String busdescribe;

    /**
     * 城市名称
     */
    @ExcelProperty(value = "商业介绍")
    private String cityname;


}

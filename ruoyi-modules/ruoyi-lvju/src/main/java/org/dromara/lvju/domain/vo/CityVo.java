package org.dromara.lvju.domain.vo;

import java.math.BigDecimal;
import org.dromara.lvju.domain.City;
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
 * 城市信息管理视图对象 lv_city
 *
 * @author xsQian
 * @date 2023-12-19
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = City.class)
public class CityVo implements Serializable {

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
     * 是否是热门城市：1是 0不是
     */
    @ExcelProperty(value = "是否是热门城市：1是 0不是")
    private Long ishot;

    /**
     * 父城市id
     */
    @ExcelProperty(value = "父城市id")
    private Long parentid;

    /**
     * 经度
     */
    @ExcelProperty(value = "经度")
    private BigDecimal logtude;

    /**
     * 维度
     */
    @ExcelProperty(value = "维度")
    private BigDecimal latttude;


}

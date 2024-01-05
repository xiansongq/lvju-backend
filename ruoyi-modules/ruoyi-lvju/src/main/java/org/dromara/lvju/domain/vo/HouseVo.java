package org.dromara.lvju.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import org.dromara.lvju.domain.House;
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
 * 房源信息表视图对象 lv_house
 *
 * @author xsQian
 * @date 2023-12-19
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = House.class)
public class HouseVo implements Serializable {

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
    private Long userid;

    /**
     * 供应商编号
     */
    @ExcelProperty(value = "供应商编号")
    private Long supplierid;

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
     * 小区
     */
    @ExcelProperty(value = "小区")
    private Long estateid;

    /**
     * 描述
     */
    @ExcelProperty(value = "描述")
    @TableField(value = "`describe`")

    private String describe;

    /**
     * 商业描述
     */
    @ExcelProperty(value = "商业描述")
    private String busdescribe;

    /**
     * 地址
     */
    @ExcelProperty(value = "地址")
    private String address;

    /**
     * 房间数量
     */
    @ExcelProperty(value = "房间数量")
    private Long num;

    /*城市名称*/
    @ExcelProperty(value = "城市名称")
    private String cityname;

    /*小区名称*/
    @ExcelProperty(value = "小区名称")
    private String estatename;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;


}

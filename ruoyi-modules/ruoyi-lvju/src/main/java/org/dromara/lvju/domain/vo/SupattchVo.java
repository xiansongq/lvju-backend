package org.dromara.lvju.domain.vo;

import org.dromara.lvju.domain.Supattch;
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
 * 供应商资质证明材料视图对象 lv_supattch
 *
 * @author xsQian
 * @date 2023-12-18
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Supattch.class)
public class SupattchVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;

    /**
     * 用户ID
     */
    @ExcelProperty(value = "用户ID")
    private Long userid;

    /**
     * 附件编号
     */
    @ExcelProperty(value = "附件编号")
    private String supplierid;

    /**
     * 附件名称
     */
    @ExcelProperty(value = "附件名称")
    private String name;

    /**
     * 文件路径
     */
    @ExcelProperty(value = "文件路径")
    private String path;

    /**
     * 文件类型/多种类型存放在字典
     */
    @ExcelProperty(value = "文件类型/多种类型存放在字典", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "lvjv_supfile_type")
    private Long stype;

    /**
     * 是否删除
     */
    @ExcelProperty(value = "是否删除", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "lvju_delete_type")
    private Long ideleted;


}

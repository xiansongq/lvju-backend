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
 * @date 2024-01-05
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
     * ossId
     */
    @ExcelProperty(value = "ossId")
    private Long ossId;

    /**
     * 文件名称
     */
    @ExcelProperty(value = "文件名称")
    private String typeName;

    /**
     * 文件类型值
     */
    @ExcelProperty(value = "文件类型值")
    private Integer typeValue;

    /**
     * 是否删除
     */
    @ExcelProperty(value = "是否删除", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "lvju_delete_type")
    private Integer ideleted;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;


}

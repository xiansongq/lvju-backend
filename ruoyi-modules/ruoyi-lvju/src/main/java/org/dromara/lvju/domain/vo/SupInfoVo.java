package org.dromara.lvju.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 供应商资质文件视图
 *
 * @author xsQian
 * @date 2024-01-05
 */
@Data
@ExcelIgnoreUnannotated
public class SupInfoVo implements Serializable {


    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;
    /**
     * 文件类型名称
     */
    @ExcelProperty(value = "文件名称")
    private String typeName;

    /*
     * 文件值
     * */
    @ExcelProperty(value = "文件值")
    private Integer typeValue;

    /*
     * 状态
     * */
    @ExcelProperty(value = "状态")
    private String status;

    /*
     * 文件ossId
     * */
    @ExcelProperty(value = "文件ossId")
    private Long ossId;

    /*
     * 上传时间
     * */
    @ExcelProperty(value = "创建时间")
    private Date createTime;

}

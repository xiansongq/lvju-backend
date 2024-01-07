package org.dromara.lvju.domain.vo;

import org.dromara.lvju.domain.Tempfile;
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
 * 模板文件信息管理视图对象 lvju_tempfile
 *
 * @author xsQian
 * @date 2024-01-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Tempfile.class)
public class TempfileVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;

    /**
     * 模板名称
     */
    @ExcelProperty(value = "模板名称")
    private String tempName;

    /**
     * 文件存储id
     */
    @ExcelProperty(value = "文件存储id")
    private Long ossId;

    /**
     * 法人/个人
     */
    @ExcelProperty(value = "法人/个人", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "lvju_agent_type")
    private Integer agentType;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;


}

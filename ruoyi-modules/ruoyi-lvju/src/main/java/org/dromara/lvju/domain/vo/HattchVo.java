package org.dromara.lvju.domain.vo;

import org.dromara.lvju.domain.Hattch;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.system.domain.vo.SysOssVo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * 房源附件信息视图对象 lv_hattch
 *
 * @author xsQian
 * @date 2023-12-24
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Hattch.class)
public class HattchVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private String id;

    /*
    * oosId
    * */
    @ExcelProperty(value = "oosId")
    private long ossId;


    /**
     * 房源id
     */
    @ExcelProperty(value = "房源id")
    private Long houseid;


    /**
     * 文件类型 0图片 1视频
     */
    @ExcelProperty(value = "文件类型 0图片 1视频", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "lvju_housefile_type")
    private String htype;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;


    /*旧路径*/
    private String oldPath;

    private SysOssVo ossInfo;

}

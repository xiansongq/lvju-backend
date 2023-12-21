package org.dromara.lvju.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import org.dromara.lvju.domain.Room;
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
 * 房间信息管理视图对象 lv_room
 *
 * @author xsQian
 * @date 2023-12-21
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Room.class)
public class RoomVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;

    /**
     * 房源id
     */
    @ExcelProperty(value = "房源id")
    private Long houseid;

    /**
     * 楼栋
     */
    @ExcelProperty(value = "楼栋")
    private String houseno;

    /**
     * 楼层
     */
    @ExcelProperty(value = "楼层")
    private String houselv;

    /**
     * 房号
     */
    @ExcelProperty(value = "房号")
    private String roomno;

    /**
     * 房型
     */
    @ExcelProperty(value = "房型")
    private String apartment;

    /**
     * 面积
     */
    @ExcelProperty(value = "面积")
    private String area;

    /**
     * 房间状态
     */
    @ExcelProperty(value = "房间状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "lvju_room_status")
    private Long status;

    /**
     * 房间描述
     */
    @ExcelProperty(value = "房间描述")
    @TableField(value = "`describe`")

    private String describe;

    /**
     * 房间关键字
     */
    @ExcelProperty(value = "房间关键字")
    @TableField(value = "`keys`")

    private String keys;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remarks;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;


}

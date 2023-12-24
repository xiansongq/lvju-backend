package org.dromara.lvju.domain.vo;


import com.alibaba.excel.annotation.ExcelProperty;

import java.io.Serializable;

/**
 * 文件信息对象
 *
 * @author xsQian
 * @date 2023-12-19
 */
public class FileVo implements Serializable {

    int code=200;
    String msg="上传成功";
    @ExcelProperty(value = "文件名称")
    String filename;

    @ExcelProperty(value = "文件类型")
    String filetype;

    @ExcelProperty(value = "文件路径")
    String filepath;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void error(int code,String msg){
        this.code=code;
        this.msg=msg;
    }
}

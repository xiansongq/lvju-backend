package org.dromara.lvju.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.util.ObjectUtil;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.dromara.common.core.config.RuoYiConfig;
import org.dromara.common.core.domain.R;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.web.core.BaseController;
import org.dromara.lvju.domain.vo.FileVo;

import org.dromara.lvju.utils.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
/*
 * 文件管理控制类
 *
 * @author xiansongQ
 * */

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/lvju/file")
public class FileController extends BaseController {

    @SaCheckPermission("lvju:file:upload")
    @Log(title = "文件上传", businessType = BusinessType.INSERT)
    @PostMapping(value = "/upload")
    public R<FileVo> upload(@RequestPart("file") MultipartFile file) {
        if (ObjectUtil.isNull(file)) {
            return R.fail("上传文件不能为空");
        }
       return R.ok(FileUtils.upload("D:/upload/",file));
    }
}

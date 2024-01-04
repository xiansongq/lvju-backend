package org.dromara.lvju.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.dromara.common.core.config.RuoYiConfig;
import org.dromara.common.core.constant.Constants;
import org.dromara.common.core.exception.file.FileNameLengthLimitExceededException;
import org.dromara.common.core.exception.file.FileSizeLimitExceededException;


import org.dromara.common.core.utils.DateUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.core.utils.file.MimeTypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


/**
 * 文件上传工具类
 *
 * @author ruoyi
 */

@RequiredArgsConstructor
@Service
public class FileUtils {
    /**
     * 默认大小 50M
     */
    public static final long DEFAULT_MAX_SIZE = 50 * 1024 * 1024;

    /**
     * 默认的文件名最大长度 100
     */
    public static final int DEFAULT_FILE_NAME_LENGTH = 100;

    @Autowired
    public static RuoYiConfig ruoYiConfig;

    /**
     * 默认上传的地址
     */

//    @Value("${ruoyi.profile}")
    private static String defaultBaseDir="D:/ruoyi/uploadPath";

    public static void setDefaultBaseDir(String defaultBaseDir) {
        FileUtils.defaultBaseDir = defaultBaseDir;
    }

    public static String getDefaultBaseDir() {
        return defaultBaseDir;
    }


    /**
     * 以默认配置进行文件上传
     *
     * @param file 上传的文件
     * @return 文件名称
     * @throws Exception
     */
    public static final String upload(MultipartFile file) throws IOException {
        try {
            return upload(getDefaultBaseDir(), file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * 根据文件路径上传
     *
     * @param baseDir 相对应用的基目录
     * @param file    上传的文件
     * @return 文件名称
     * @throws IOException
     */
    public static final String upload(String baseDir, MultipartFile file) throws IOException {
        try {
            return upload(baseDir, file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * 文件上传
     *
     * @param baseDir          相对应用的基目录
     * @param file             上传的文件
     * @param allowedExtension 上传文件类型
     * @return 返回上传成功的文件名
     * @throws FileSizeLimitExceededException       如果超出最大大小
     * @throws FileNameLengthLimitExceededException 文件名太长
     * @throws IOException                          比如读写文件出错时
     */
    public static final String upload(String baseDir, MultipartFile file, String[] allowedExtension)
        throws FileSizeLimitExceededException, IOException, FileNameLengthLimitExceededException {
        int fileNamelength = Objects.requireNonNull(file.getOriginalFilename()).length();
        if (fileNamelength > FileUtils.DEFAULT_FILE_NAME_LENGTH) {
            throw new FileNameLengthLimitExceededException(FileUtils.DEFAULT_FILE_NAME_LENGTH);
        }

//        assertAllowed(file, allowedExtension);

        String fileName = extractFilename(file);

        String absPath = getAbsoluteFile(baseDir, fileName).getAbsolutePath();
        file.transferTo(Paths.get(absPath));
        return getPathFileName(baseDir, baseDir, fileName);
    }

    /**
     * 编码文件名
     */
    public static final String extractFilename(MultipartFile file) {
        return StringUtils.format("{}/{}_{}.{}", DateUtils.datePath(),
            FilenameUtils.getBaseName(file.getOriginalFilename()), UUID.randomUUID(), getExtension(file));
    }

    public static final File getAbsoluteFile(String uploadDir, String fileName) throws IOException {
        File desc = new File(uploadDir + File.separator + fileName);

        if (!desc.exists()) {
            if (!desc.getParentFile().exists()) {
                desc.getParentFile().mkdirs();
            }
        }
        return desc;
    }

    public static final String getPathFileName(String BaseDir, String uploadDir, String fileName) throws IOException {

        int dirLastIndex = getDefaultBaseDir().length() + 1;

        String currentDir = StringUtils.substring(uploadDir, dirLastIndex);

        return Constants.RESOURCE_PREFIX + "/" + currentDir + "/" + fileName;
    }


    /**
     * 判断MIME类型是否是允许的MIME类型
     *
     * @param extension
     * @param allowedExtension
     * @return
     */
    public static final boolean isAllowedExtension(String extension, String[] allowedExtension) {
        for (String str : allowedExtension) {
            if (str.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取文件名的后缀
     *
     * @param file 表单文件
     * @return 后缀名
     */
    public static final String getExtension(MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (StringUtils.isEmpty(extension)) {
            extension = MimeTypeUtils.getExtension(Objects.requireNonNull(file.getContentType()));
        }
        return extension;
    }

    public static int delFile(String path) {
        File file = new File(path);
        /*判断文件是否存在*/
        if (file.exists()) {
            /*存在直接删除*/
            try {
                file.delete();
            } catch (Exception e) {
                /*删除失败*/
                return 0;
            }
        }
        return 1;
    }
}

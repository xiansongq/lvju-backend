package org.dromara.lvju.utils;

import org.dromara.lvju.domain.vo.FileVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;



public class FileUtils {
    private FileUtils() {
        // Prevent instantiation
    }
    public static String getName(MultipartFile file) {
        return file.getOriginalFilename();
    }

    public  static String getFileType(MultipartFile file) {
        String orginname = getName(file);
        return orginname.substring(orginname.lastIndexOf(".") + 1);


    }

    public static String  getUuidName(MultipartFile file) {
        return UUID.randomUUID() + "." + getFileType(file);
    }

    public static FileVo upload(String BasePath, MultipartFile file) {
        /* 判断BasePath 是否存在 */
        FileVo fileVo = new FileVo();
        Path path = Paths.get(BasePath);
        if (!path.toFile().exists()) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                fileVo.error(500, "文件夹创建失败");
                return fileVo;
            }
        }
        String uuidName = getUuidName(file);
        String paths = BasePath + uuidName;
        try {
            file.transferTo(Path.of(paths));
        } catch (IOException e) {
            fileVo.error(500, "文件上传失败");
            return fileVo;
        }
        fileVo.setFilename(getName(file));
        fileVo.setFiletype(getFileType(file));
        fileVo.setFilepath(paths);
        return fileVo;
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

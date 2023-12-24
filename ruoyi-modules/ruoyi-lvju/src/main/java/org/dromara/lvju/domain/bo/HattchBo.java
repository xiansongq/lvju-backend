package org.dromara.lvju.domain.bo;

import org.dromara.lvju.domain.Hattch;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 房源附件信息业务对象 lv_hattch
 *
 * @author xsQian
 * @date 2023-12-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = Hattch.class, reverseConvertGenerate = false)
public class HattchBo extends BaseEntity {

    /**
     * id
     */
    @NotBlank(message = "id不能为空", groups = { EditGroup.class })
    private String id;

    /**
     * 房源id
     */
    @NotNull(message = "房源id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long houseid;

    /**
     * 原始名称
     */
    @NotBlank(message = "原始名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 保存路径
     */
    @NotBlank(message = "保存路径不能为空", groups = { AddGroup.class, EditGroup.class })
    private String path;

    /**
     * 文件类型 0图片 1视频
     */
    @NotBlank(message = "文件类型 0图片 1视频不能为空", groups = { AddGroup.class, EditGroup.class })
    private String htype;

    /*旧路径*/
    private String oldPath;


}

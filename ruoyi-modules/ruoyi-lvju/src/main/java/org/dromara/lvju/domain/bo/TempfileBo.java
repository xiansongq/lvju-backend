package org.dromara.lvju.domain.bo;

import org.dromara.lvju.domain.Tempfile;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 模板文件信息管理业务对象 lvju_tempfile
 *
 * @author xsQian
 * @date 2024-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = Tempfile.class, reverseConvertGenerate = false)
public class TempfileBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 模板名称
     */
    @NotBlank(message = "模板名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String tempName;

    /**
     * 文件存储id
     */
    @NotNull(message = "文件存储id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long ossId;

    /**
     * 法人/个人
     */
    @NotNull(message = "法人/个人不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer agentType;


}

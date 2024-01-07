package org.dromara.lvju.service;

import org.dromara.lvju.domain.Tempfile;
import org.dromara.lvju.domain.vo.TempfileVo;
import org.dromara.lvju.domain.bo.TempfileBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 模板文件信息管理Service接口
 *
 * @author xsQian
 * @date 2024-01-07
 */
public interface ITempfileService {

    /**
     * 查询模板文件信息管理
     */
    TempfileVo queryById(Long id);

    /**
     * 查询模板文件信息管理列表
     */
    TableDataInfo<TempfileVo> queryPageList(TempfileBo bo, PageQuery pageQuery);

    /**
     * 查询模板文件信息管理列表
     */
    List<TempfileVo> queryList(TempfileBo bo);

    /**
     * 新增模板文件信息管理
     */
    Boolean insertByBo(TempfileBo bo);

    /**
     * 修改模板文件信息管理
     */
    Boolean updateByBo(TempfileBo bo);

    /**
     * 校验并批量删除模板文件信息管理信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}

package org.dromara.lvju.service;

import org.dromara.lvju.domain.Estate;
import org.dromara.lvju.domain.vo.EstateVo;
import org.dromara.lvju.domain.bo.EstateBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 小区信息操作Service接口
 *
 * @author xsQian
 * @date 2023-12-19
 */
public interface IEstateService {

    /**
     * 查询小区信息操作
     */
    EstateVo queryById(Long id);

    /**
     * 查询小区信息操作列表
     */
    TableDataInfo<EstateVo> queryPageList(EstateBo bo, PageQuery pageQuery);

    /**
     * 查询小区信息操作列表
     */
    List<EstateVo> queryList(EstateBo bo);

    /**
     * 新增小区信息操作
     */
    Boolean insertByBo(EstateBo bo);

    /**
     * 修改小区信息操作
     */
    Boolean updateByBo(EstateBo bo);

    /**
     * 校验并批量删除小区信息操作信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}

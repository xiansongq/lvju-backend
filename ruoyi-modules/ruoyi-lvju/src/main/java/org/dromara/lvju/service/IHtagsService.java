package org.dromara.lvju.service;

import org.dromara.lvju.domain.Htags;
import org.dromara.lvju.domain.vo.HtagsVo;
import org.dromara.lvju.domain.bo.HtagsBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 房源标签信息Service接口
 *
 * @author seky
 * @date 2024-01-02
 */
public interface IHtagsService {

    /**
     * 查询房源标签信息
     */
    HtagsVo queryById(String id);

    /**
     * 查询房源标签信息列表
     */
    TableDataInfo<HtagsVo> queryPageList(HtagsBo bo, PageQuery pageQuery);

    /**
     * 查询房源标签信息列表
     */
    List<HtagsVo> queryList(HtagsBo bo);

    /**
     * 新增房源标签信息
     */
    Boolean insertByBo(HtagsBo bo);

    /**
     * 修改房源标签信息
     */
    Boolean updateByBo(HtagsBo bo);

    /**
     * 校验并批量删除房源标签信息信息
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);
}

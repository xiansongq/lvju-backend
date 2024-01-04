package org.dromara.lvju.service;

import org.dromara.lvju.domain.Hnotices;
import org.dromara.lvju.domain.vo.HnoticesVo;
import org.dromara.lvju.domain.bo.HnoticesBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 房源预定须知Service接口
 *
 * @author seky
 * @date 2024-01-02
 */
public interface IHnoticesService {

    /**
     * 查询房源预定须知
     */
    HnoticesVo queryById(String id);

    /**
     * 查询房源预定须知列表
     */
    TableDataInfo<HnoticesVo> queryPageList(HnoticesBo bo, PageQuery pageQuery);

    /**
     * 查询房源预定须知列表
     */
    List<HnoticesVo> queryList(HnoticesBo bo);

    /**
     * 新增房源预定须知
     */
    Boolean insertByBo(HnoticesBo bo);

    /**
     * 修改房源预定须知
     */
    Boolean updateByBo(HnoticesBo bo);

    /**
     * 校验并批量删除房源预定须知信息
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);
}

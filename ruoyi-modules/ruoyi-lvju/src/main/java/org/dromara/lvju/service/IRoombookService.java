package org.dromara.lvju.service;

import org.dromara.lvju.domain.Roombook;
import org.dromara.lvju.domain.vo.RoombookVo;
import org.dromara.lvju.domain.bo.RoombookBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 房间订单信息Service接口
 *
 * @author seky
 * @date 2024-01-02
 */
public interface IRoombookService {

    /**
     * 查询房间订单信息
     */
    RoombookVo queryById(String id);

    /**
     * 查询房间订单信息列表
     */
    TableDataInfo<RoombookVo> queryPageList(RoombookBo bo, PageQuery pageQuery);

    /**
     * 查询房间订单信息列表
     */
    List<RoombookVo> queryList(RoombookBo bo);

    /**
     * 新增房间订单信息
     */
    Boolean insertByBo(RoombookBo bo);

    /**
     * 修改房间订单信息
     */
    Boolean updateByBo(RoombookBo bo);

    /**
     * 校验并批量删除房间订单信息信息
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);
}

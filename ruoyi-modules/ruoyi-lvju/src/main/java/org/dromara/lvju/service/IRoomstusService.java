package org.dromara.lvju.service;

import org.dromara.lvju.domain.Roomstus;
import org.dromara.lvju.domain.vo.RoomstusVo;
import org.dromara.lvju.domain.bo.RoomstusBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 房间预定信息Service接口
 *
 * @author seky
 * @date 2024-01-02
 */
public interface IRoomstusService {

    /**
     * 查询房间预定信息
     */
    RoomstusVo queryById(String id);

    /**
     * 查询房间预定信息列表
     */
    TableDataInfo<RoomstusVo> queryPageList(RoomstusBo bo, PageQuery pageQuery);

    /**
     * 查询房间预定信息列表
     */
    List<RoomstusVo> queryList(RoomstusBo bo);

    /**
     * 新增房间预定信息
     */
    Boolean insertByBo(RoomstusBo bo);

    /**
     * 修改房间预定信息
     */
    Boolean updateByBo(RoomstusBo bo);

    /**
     * 校验并批量删除房间预定信息信息
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);
}

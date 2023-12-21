package org.dromara.lvju.service;

import org.dromara.lvju.domain.Room;
import org.dromara.lvju.domain.vo.RoomVo;
import org.dromara.lvju.domain.bo.RoomBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 房间信息管理Service接口
 *
 * @author xsQian
 * @date 2023-12-21
 */
public interface IRoomService {

    /**
     * 查询房间信息管理
     */
    RoomVo queryById(Long id);

    /**
     * 查询房间信息管理列表
     */
    TableDataInfo<RoomVo> queryPageList(RoomBo bo, PageQuery pageQuery);

    /**
     * 查询房间信息管理列表
     */
    List<RoomVo> queryList(RoomBo bo);

    /**
     * 新增房间信息管理
     */
    Boolean insertByBo(RoomBo bo);

    /**
     * 修改房间信息管理
     */
    Boolean updateByBo(RoomBo bo);

    /**
     * 校验并批量删除房间信息管理信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}

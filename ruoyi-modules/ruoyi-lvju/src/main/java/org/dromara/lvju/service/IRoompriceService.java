package org.dromara.lvju.service;

import org.dromara.lvju.domain.Roomprice;
import org.dromara.lvju.domain.vo.RoompriceVo;
import org.dromara.lvju.domain.bo.RoompriceBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 房间价格信息Service接口
 *
 * @author seky
 * @date 2024-01-02
 */
public interface IRoompriceService {

    /**
     * 查询房间价格信息
     */
    RoompriceVo queryById(String id);

    /**
     * 查询房间价格信息列表
     */
    TableDataInfo<RoompriceVo> queryPageList(RoompriceBo bo, PageQuery pageQuery);

    /**
     * 查询房间价格信息列表
     */
    List<RoompriceVo> queryList(RoompriceBo bo);

    /**
     * 新增房间价格信息
     */
    Boolean insertByBo(RoompriceBo bo);

    /**
     * 修改房间价格信息
     */
    Boolean updateByBo(RoompriceBo bo);

    /**
     * 校验并批量删除房间价格信息信息
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);
}

package org.dromara.lvju.service;

import org.dromara.lvju.domain.Bsroom;
import org.dromara.lvju.domain.vo.BsroomVo;
import org.dromara.lvju.domain.bo.BsroomBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 房间详细信息Service接口
 *
 * @author seky
 * @date 2024-01-02
 */
public interface IBsroomService {

    /**
     * 查询房间详细信息
     */
    BsroomVo queryById(String id);

    /**
     * 查询房间详细信息列表
     */
    TableDataInfo<BsroomVo> queryPageList(BsroomBo bo, PageQuery pageQuery);

    /**
     * 查询房间详细信息列表
     */
    List<BsroomVo> queryList(BsroomBo bo);

    /**
     * 新增房间详细信息
     */
    Boolean insertByBo(BsroomBo bo);

    /**
     * 修改房间详细信息
     */
    Boolean updateByBo(BsroomBo bo);

    /**
     * 校验并批量删除房间详细信息信息
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);
}

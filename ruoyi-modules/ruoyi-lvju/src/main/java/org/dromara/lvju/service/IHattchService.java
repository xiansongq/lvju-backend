package org.dromara.lvju.service;

import org.dromara.lvju.domain.Hattch;
import org.dromara.lvju.domain.vo.HattchVo;
import org.dromara.lvju.domain.bo.HattchBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 房源附件信息Service接口
 *
 * @author xsQian
 * @date 2023-12-24
 */
public interface IHattchService {

    /**
     * 查询房源附件信息
     */
    HattchVo queryById(String id);

    /**
     * 查询房源附件信息列表
     */
    TableDataInfo<HattchVo> queryPageList(HattchBo bo, PageQuery pageQuery);

    /**
     * 查询房源附件信息列表
     */
    List<HattchVo> queryList(HattchBo bo);

    /**
     * 新增房源附件信息
     */
    Boolean insertByBo(HattchBo bo);

    /**
     * 修改房源附件信息
     */
    Boolean updateByBo(HattchBo bo);

    /**
     * 校验并批量删除房源附件信息信息
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);
}

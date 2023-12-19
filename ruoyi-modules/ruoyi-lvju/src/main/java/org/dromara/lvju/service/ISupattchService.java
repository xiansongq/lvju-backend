package org.dromara.lvju.service;

import org.dromara.lvju.domain.vo.SupattchVo;
import org.dromara.lvju.domain.bo.SupattchBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 供应商资质证明材料Service接口
 *
 * @author xsQian
 * @date 2023-12-18
 */
public interface ISupattchService {

    /**
     * 查询供应商资质证明材料
     */
    SupattchVo queryById(Long id);

    /**
     * 查询供应商资质证明材料列表
     */
    TableDataInfo<SupattchVo> queryPageList(SupattchBo bo, PageQuery pageQuery);

    /**
     * 查询供应商资质证明材料列表
     */
    List<SupattchVo> queryList(SupattchBo bo);

    /**
     * 新增供应商资质证明材料
     */
    Boolean insertByBo(SupattchBo bo);

    /**
     * 修改供应商资质证明材料
     */
    Boolean updateByBo(SupattchBo bo);

    /**
     * 校验并批量删除供应商资质证明材料信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /*根据uerid查询列表数据*/

    List<SupattchVo> queryByUserid(Long bo);
}

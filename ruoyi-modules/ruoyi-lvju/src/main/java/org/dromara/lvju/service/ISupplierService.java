package org.dromara.lvju.service;

import org.dromara.lvju.domain.Supplier;
import org.dromara.lvju.domain.vo.SupplierVo;
import org.dromara.lvju.domain.bo.SupplierBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 供应商信息Service接口
 *
 * @author xsQian
 * @date 2023-12-19
 */
public interface ISupplierService {

    /**
     * 查询供应商信息
     */
    SupplierVo queryById(Long id);

    /**
     * 查询供应商信息列表
     */
    TableDataInfo<SupplierVo> queryPageList(SupplierBo bo, PageQuery pageQuery);

    /**
     * 查询供应商信息列表
     */
    List<SupplierVo> queryList(SupplierBo bo);

    /**
     * 新增供应商信息
     */
    Boolean insertByBo(SupplierBo bo);

    /**
     * 修改供应商信息
     */
    Boolean updateByBo(SupplierBo bo);

    /**
     * 校验并批量删除供应商信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}

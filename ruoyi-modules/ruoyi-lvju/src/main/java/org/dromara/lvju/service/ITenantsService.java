package org.dromara.lvju.service;

import org.dromara.lvju.domain.Tenants;
import org.dromara.lvju.domain.vo.TenantsVo;
import org.dromara.lvju.domain.bo.TenantsBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 租客用户信息Service接口
 *
 * @author seky
 * @date 2024-01-04
 */
public interface ITenantsService {

    /**
     * 查询租客用户信息
     */
    TenantsVo queryById(String id);

    /**
     * 查询租客用户信息列表
     */
    TableDataInfo<TenantsVo> queryPageList(TenantsBo bo, PageQuery pageQuery);

    /**
     * 查询租客用户信息列表
     */
    List<TenantsVo> queryList(TenantsBo bo);

    /**
     * 新增租客用户信息
     */
    Boolean insertByBo(TenantsBo bo);

    /**
     * 修改租客用户信息
     */
    Boolean updateByBo(TenantsBo bo);

    /**
     * 校验并批量删除租客用户信息信息
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);
}

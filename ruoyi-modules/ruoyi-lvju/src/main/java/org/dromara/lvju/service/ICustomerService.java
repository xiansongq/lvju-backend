package org.dromara.lvju.service;

import org.dromara.lvju.domain.Customer;
import org.dromara.lvju.domain.vo.CustomerVo;
import org.dromara.lvju.domain.bo.CustomerBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 客户信息管理Service接口
 *
 * @author seky
 * @date 2024-01-02
 */
public interface ICustomerService {

    /**
     * 查询客户信息管理
     */
    CustomerVo queryById(String id);

    /**
     * 查询客户信息管理列表
     */
    TableDataInfo<CustomerVo> queryPageList(CustomerBo bo, PageQuery pageQuery);

    /**
     * 查询客户信息管理列表
     */
    List<CustomerVo> queryList(CustomerBo bo);

    /**
     * 新增客户信息管理
     */
    Boolean insertByBo(CustomerBo bo);

    /**
     * 修改客户信息管理
     */
    Boolean updateByBo(CustomerBo bo);

    /**
     * 校验并批量删除客户信息管理信息
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);
}

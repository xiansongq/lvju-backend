package org.dromara.lvju.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.lvju.domain.bo.CustomerBo;
import org.dromara.lvju.domain.vo.CustomerVo;
import org.dromara.lvju.domain.Customer;
import org.dromara.lvju.mapper.CustomerMapper;
import org.dromara.lvju.service.ICustomerService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 客户信息管理Service业务层处理
 *
 * @author seky
 * @date 2024-01-02
 */
@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements ICustomerService {

    private final CustomerMapper baseMapper;

    /**
     * 查询客户信息管理
     */
    @Override
    public CustomerVo queryById(String id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询客户信息管理列表
     */
    @Override
    public TableDataInfo<CustomerVo> queryPageList(CustomerBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Customer> lqw = buildQueryWrapper(bo);
        Page<CustomerVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询客户信息管理列表
     */
    @Override
    public List<CustomerVo> queryList(CustomerBo bo) {
        LambdaQueryWrapper<Customer> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Customer> buildQueryWrapper(CustomerBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Customer> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getSname()), Customer::getSname, bo.getSname());
        lqw.like(StringUtils.isNotBlank(bo.getSdnum()), Customer::getSdnum, bo.getSdnum());
        lqw.eq(StringUtils.isNotBlank(bo.getSsex()), Customer::getSsex, bo.getSsex());
        lqw.eq(StringUtils.isNotBlank(bo.getIphone()), Customer::getIphone, bo.getIphone());
        return lqw;
    }

    /**
     * 新增客户信息管理
     */
    @Override
    public Boolean insertByBo(CustomerBo bo) {
        Customer add = MapstructUtils.convert(bo, Customer.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改客户信息管理
     */
    @Override
    public Boolean updateByBo(CustomerBo bo) {
        Customer update = MapstructUtils.convert(bo, Customer.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Customer entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除客户信息管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}

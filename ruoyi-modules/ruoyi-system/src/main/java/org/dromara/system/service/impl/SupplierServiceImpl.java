package org.dromara.system.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.system.domain.bo.SupplierBo;
import org.dromara.system.domain.vo.SupplierVo;
import org.dromara.system.domain.Supplier;
import org.dromara.system.mapper.SupplierMapper;
import org.dromara.system.service.ISupplierService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 供应商信息Service业务层处理
 *
 * @author xsQian
 * @date 2023-12-19
 */
@RequiredArgsConstructor
@Service
public class SupplierServiceImpl implements ISupplierService {

    private final SupplierMapper baseMapper;

    /**
     * 查询供应商信息
     */
    @Override
    public SupplierVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询供应商信息列表
     */
    @Override
    public TableDataInfo<SupplierVo> queryPageList(SupplierBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Supplier> lqw = buildQueryWrapper(bo);
        Page<SupplierVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询供应商信息列表
     */
    @Override
    public List<SupplierVo> queryList(SupplierBo bo) {
        LambdaQueryWrapper<Supplier> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Supplier> buildQueryWrapper(SupplierBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Supplier> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getUserid()), Supplier::getUserid, bo.getUserid());
        lqw.eq(StringUtils.isNotBlank(bo.getCompany()), Supplier::getCompany, bo.getCompany());
        lqw.like(StringUtils.isNotBlank(bo.getName()), Supplier::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getSdnum()), Supplier::getSdnum, bo.getSdnum());
        lqw.eq(StringUtils.isNotBlank(bo.getIphone()), Supplier::getIphone, bo.getIphone());
        lqw.eq(bo.getStype() != null, Supplier::getStype, bo.getStype());
        lqw.eq(bo.getAgentType()!=null, Supplier::getAgentType, bo.getAgentType());

        return lqw;
    }

    /**
     * 新增供应商信息
     */
    @Override
    public Boolean insertByBo(SupplierBo bo) {
        Supplier add = MapstructUtils.convert(bo, Supplier.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改供应商信息
     */
    @Override
    public Boolean updateByBo(SupplierBo bo) {
        Supplier update = MapstructUtils.convert(bo, Supplier.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Supplier entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除供应商信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}

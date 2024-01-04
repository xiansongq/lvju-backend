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
import org.dromara.lvju.domain.bo.TenantsBo;
import org.dromara.lvju.domain.vo.TenantsVo;
import org.dromara.lvju.domain.Tenants;
import org.dromara.lvju.mapper.TenantsMapper;
import org.dromara.lvju.service.ITenantsService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 租客用户信息Service业务层处理
 *
 * @author seky
 * @date 2024-01-04
 */
@RequiredArgsConstructor
@Service
public class TenantsServiceImpl implements ITenantsService {

    private final TenantsMapper baseMapper;

    /**
     * 查询租客用户信息
     */
    @Override
    public TenantsVo queryById(String id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询租客用户信息列表
     */
    @Override
    public TableDataInfo<TenantsVo> queryPageList(TenantsBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Tenants> lqw = buildQueryWrapper(bo);
        Page<TenantsVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询租客用户信息列表
     */
    @Override
    public List<TenantsVo> queryList(TenantsBo bo) {
        LambdaQueryWrapper<Tenants> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Tenants> buildQueryWrapper(TenantsBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Tenants> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getRoomstusid()), Tenants::getRoomstusid, bo.getRoomstusid());
        lqw.like(StringUtils.isNotBlank(bo.getName()), Tenants::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getSdno()), Tenants::getSdno, bo.getSdno());
        lqw.eq(StringUtils.isNotBlank(bo.getIphone()), Tenants::getIphone, bo.getIphone());
        return lqw;
    }

    /**
     * 新增租客用户信息
     */
    @Override
    public Boolean insertByBo(TenantsBo bo) {
        Tenants add = MapstructUtils.convert(bo, Tenants.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改租客用户信息
     */
    @Override
    public Boolean updateByBo(TenantsBo bo) {
        Tenants update = MapstructUtils.convert(bo, Tenants.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Tenants entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除租客用户信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}

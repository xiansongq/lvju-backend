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
import org.dromara.lvju.domain.bo.HnoticesBo;
import org.dromara.lvju.domain.vo.HnoticesVo;
import org.dromara.lvju.domain.Hnotices;
import org.dromara.lvju.mapper.HnoticesMapper;
import org.dromara.lvju.service.IHnoticesService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 房源预定须知Service业务层处理
 *
 * @author seky
 * @date 2024-01-02
 */
@RequiredArgsConstructor
@Service
public class HnoticesServiceImpl implements IHnoticesService {

    private final HnoticesMapper baseMapper;

    /**
     * 查询房源预定须知
     */
    @Override
    public HnoticesVo queryById(String id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询房源预定须知列表
     */
    @Override
    public TableDataInfo<HnoticesVo> queryPageList(HnoticesBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Hnotices> lqw = buildQueryWrapper(bo);
        Page<HnoticesVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询房源预定须知列表
     */
    @Override
    public List<HnoticesVo> queryList(HnoticesBo bo) {
        LambdaQueryWrapper<Hnotices> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Hnotices> buildQueryWrapper(HnoticesBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Hnotices> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getHouseid()), Hnotices::getHouseid, bo.getHouseid());
        lqw.like(StringUtils.isNotBlank(bo.getName()), Hnotices::getName, bo.getName());
        lqw.eq(bo.getNtype() != null, Hnotices::getNtype, bo.getNtype());
        return lqw;
    }

    /**
     * 新增房源预定须知
     */
    @Override
    public Boolean insertByBo(HnoticesBo bo) {
        Hnotices add = MapstructUtils.convert(bo, Hnotices.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改房源预定须知
     */
    @Override
    public Boolean updateByBo(HnoticesBo bo) {
        Hnotices update = MapstructUtils.convert(bo, Hnotices.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Hnotices entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除房源预定须知
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}

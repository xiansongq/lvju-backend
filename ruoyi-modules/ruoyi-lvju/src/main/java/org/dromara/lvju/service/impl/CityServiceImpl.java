package org.dromara.lvju.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.lvju.domain.bo.CityBo;
import org.dromara.lvju.domain.vo.CityVo;
import org.dromara.lvju.domain.City;
import org.dromara.lvju.mapper.CityMapper;
import org.dromara.lvju.service.ICityService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 城市信息管理Service业务层处理
 *
 * @author xsQian
 * @date 2023-12-19
 */
@RequiredArgsConstructor
@Service
public class CityServiceImpl implements ICityService {

    private final CityMapper baseMapper;

    /**
     * 查询城市信息管理
     */
    @Override
    public CityVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }


    /**
     * 查询城市信息管理列表
     */
    @Override
    public List<CityVo> queryList(CityBo bo) {
        LambdaQueryWrapper<City> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<City> buildQueryWrapper(CityBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<City> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getName()), City::getName, bo.getName());
        lqw.eq(bo.getIshot() != null, City::getIshot, bo.getIshot());
        lqw.eq(bo.getParentid() != null, City::getParentid, bo.getParentid());
        lqw.eq(bo.getLogtude() != null, City::getLogtude, bo.getLogtude());
        lqw.eq(bo.getLatttude() != null, City::getLatttude, bo.getLatttude());
        return lqw;
    }

    /**
     * 新增城市信息管理
     */
    @Override
    public Boolean insertByBo(CityBo bo) {
        City add = MapstructUtils.convert(bo, City.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改城市信息管理
     */
    @Override
    public Boolean updateByBo(CityBo bo) {
        City update = MapstructUtils.convert(bo, City.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(City entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除城市信息管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}

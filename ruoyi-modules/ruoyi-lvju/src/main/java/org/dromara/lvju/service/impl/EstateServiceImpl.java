package org.dromara.lvju.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.val;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.lvju.domain.City;
import org.dromara.lvju.domain.vo.CityVo;
import org.dromara.lvju.domain.vo.HouseVo;
import org.dromara.lvju.mapper.CityMapper;
import org.springframework.stereotype.Service;
import org.dromara.lvju.domain.bo.EstateBo;
import org.dromara.lvju.domain.vo.EstateVo;
import org.dromara.lvju.domain.Estate;
import org.dromara.lvju.mapper.EstateMapper;
import org.dromara.lvju.service.IEstateService;

import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 小区信息操作Service业务层处理
 *
 * @author xsQian
 * @date 2023-12-19
 */
@RequiredArgsConstructor
@Service
public class EstateServiceImpl implements IEstateService {

    private final EstateMapper baseMapper;
    private final CityMapper cityMapper;

    /**
     * 根据城市id 获取城市名称
     *
     * @param vo
     */
    private void addCityName(EstateVo vo) {

        CityVo ossInfo = cityMapper.selectVoById(vo.getCityid());
        Optional.ofNullable(ossInfo).ifPresent(e -> vo.setCityname(ossInfo.getName()));
    }

    /**
     * 批量查询城市名称并添加
     *
     * @param pages
     */

    private void addCityNames(Page<EstateVo> pages) {
        // 提取oosid 方便批量查询
        List<Long> ids = pages.getRecords().stream().map(EstateVo::getCityid).collect(Collectors.toList());
        // 批量查询
        List<CityVo> ossVoList = cityMapper.selectVoList(Wrappers.lambdaQuery(City.class).in(City::getId, ids));
        //构造map 方便实体关系的映射
        Map<Long, String> ossInfoMap = ossVoList.stream()
            .collect(Collectors.toMap(CityVo::getId, CityVo::getName));
        // 添加补充的信息
        for(EstateVo vo: pages.getRecords()){
            vo.setCityname(ossInfoMap.get(vo.getCityid()));
        }
    }

    /**
     * 批量查询 城市名称并添加
     *
     * @param pages
     */

    private void addCityNames(List<EstateVo> pages) {
        // 提取oosid 方便批量查询
        List<Long> ids = pages.stream().map(EstateVo::getCityid).collect(Collectors.toList());
        // 批量查询
        List<CityVo> ossVoList = cityMapper.selectVoList(Wrappers.lambdaQuery(City.class).in(City::getId, ids));
        //构造map 方便实体关系的映射
        Map<Long, String> ossInfoMap = ossVoList.stream()
            .collect(Collectors.toMap(CityVo::getId, CityVo::getName));
        // 添加补充的信息
        pages.forEach(e -> {
            e.setCityname(ossInfoMap.get(e.getCityid()));
        });
    }

    /**
     * 查询小区信息操作
     */
    @Override
    public EstateVo queryById(Long id) {
        EstateVo estateVo= baseMapper.selectVoById(id);;
        Optional.ofNullable(estateVo).ifPresent(this::addCityName);

        return estateVo;
    }

    /**
     * 查询小区信息操作列表
     */
    @Override
    public TableDataInfo<EstateVo> queryPageList(EstateBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Estate> lqw = buildQueryWrapper(bo);
        Page<EstateVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        if(result.getRecords().size()>0){
            addCityNames(result);
        }
        return TableDataInfo.build(result);
    }

    /**
     * 查询小区信息操作列表
     */
    @Override
    public List<EstateVo> queryList(EstateBo bo) {
        LambdaQueryWrapper<Estate> lqw = buildQueryWrapper(bo);
        List<EstateVo> estateVoList=baseMapper.selectVoList(lqw);
        if(estateVoList.size()>0){
            addCityNames(estateVoList);
        }
        return estateVoList;
    }

    private LambdaQueryWrapper<Estate> buildQueryWrapper(EstateBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Estate> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getName()), Estate::getName, bo.getName());
        lqw.eq(bo.getCityid() != null, Estate::getCityid, bo.getCityid());
        lqw.eq(bo.getLogtude() != null, Estate::getLogtude, bo.getLogtude());
        lqw.eq(bo.getLattude() != null, Estate::getLattude, bo.getLattude());
        lqw.eq(bo.getCreateTime() != null, Estate::getCreateTime, bo.getCreateTime());

        return lqw;
    }

    /**
     * 新增小区信息操作
     */
    @Override
    public Boolean insertByBo(EstateBo bo) {
        Estate add = MapstructUtils.convert(bo, Estate.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改小区信息操作
     */
    @Override
    public Boolean updateByBo(EstateBo bo) {
        Estate update = MapstructUtils.convert(bo, Estate.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Estate entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除小区信息操作
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}

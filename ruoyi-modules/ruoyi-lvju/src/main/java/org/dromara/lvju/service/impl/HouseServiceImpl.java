package org.dromara.lvju.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.lvju.domain.City;
import org.dromara.lvju.domain.Estate;
import org.dromara.lvju.domain.bo.EstateBo;
import org.dromara.lvju.domain.vo.CityVo;
import org.dromara.lvju.domain.vo.EstateVo;
import org.dromara.lvju.domain.vo.HattchVo;
import org.dromara.lvju.mapper.CityMapper;
import org.dromara.lvju.mapper.EstateMapper;
import org.dromara.system.domain.SysOss;
import org.dromara.system.domain.vo.SysOssVo;
import org.springframework.stereotype.Service;
import org.dromara.lvju.domain.bo.HouseBo;
import org.dromara.lvju.domain.vo.HouseVo;
import org.dromara.lvju.domain.House;
import org.dromara.lvju.mapper.HouseMapper;
import org.dromara.lvju.service.IHouseService;

import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 房源信息表Service业务层处理
 *
 * @author xsQian
 * @date 2023-12-19
 */
@RequiredArgsConstructor
@Service
public class HouseServiceImpl implements IHouseService {

    private final HouseMapper baseMapper;
    private final CityMapper cityMapper;
    private final EstateMapper estateMapper;

    /**
     * 添加城市名称
     *
     * @param vo
     */
    private void addCityName(HouseVo vo) {

        CityVo ossInfo = cityMapper.selectVoById(vo.getCityid());
        Optional.ofNullable(ossInfo).ifPresent(e -> vo.setCityname(ossInfo.getName()));
    }

    /**
     * 批量查询城市名称并添加
     *
     * @param pages
     */

    private void addCityNames(Page<HouseVo> pages) {
        // 提取oosid 方便批量查询
        List<Long> oosIds = pages.getRecords().stream().map(HouseVo::getCityid).collect(Collectors.toList());
        // 批量查询
        List<CityVo> ossVoList = cityMapper.selectVoList(Wrappers.lambdaQuery(City.class).in(City::getId, oosIds));
        //构造map 方便实体关系的映射
        Map<Long, String> ossInfoMap = ossVoList.stream()
            .collect(Collectors.toMap(CityVo::getId, CityVo::getName));
        // 添加补充的信息

        pages.convert(e -> {
            Optional<String> ossInfo = Optional.ofNullable(ossInfoMap.get(e.getCityid()));
            ossInfo.ifPresent(e::setCityname);
            return e;
        });
    }

    /**
     * 批量查询 城市名称并添加
     *
     * @param pages
     */

    private void addCityNames(List<HouseVo> pages) {
        // 提取oosid 方便批量查询
        List<Long> oosIds = pages.stream().map(HouseVo::getCityid).collect(Collectors.toList());
        // 批量查询
        List<CityVo> ossVoList = cityMapper.selectVoList(Wrappers.lambdaQuery(City.class).in(City::getId, oosIds));
        //构造map 方便实体关系的映射
        Map<Long, String> ossInfoMap = ossVoList.stream()
            .collect(Collectors.toMap(CityVo::getId, CityVo::getName));
        // 添加补充的信息
        pages.forEach(e -> {
            e.setCityname(ossInfoMap.get(e.getCityid()));
        });
    }


    /**
     * 添加小区名称
     *
     * @param vo
     */
    private void addEstateName(HouseVo vo) {

        EstateVo ossInfo = estateMapper.selectVoById(vo.getEstateid());
        Optional.ofNullable(ossInfo).ifPresent(e -> vo.setEstatename(ossInfo.getName()));
    }

    /**
     * 批量查询小区名称名称并添加
     *
     * @param pages
     */

    private void addEstateNames(Page<HouseVo> pages) {
        // 提取oosid 方便批量查询
        List<Long> oosIds = pages.getRecords().stream().map(HouseVo::getEstateid).collect(Collectors.toList());
        // 批量查询
        List<EstateVo> ossVoList = estateMapper.selectVoList(Wrappers.lambdaQuery(Estate.class).in(Estate::getId, oosIds));
        //构造map 方便实体关系的映射
        Map<Long, String> ossInfoMap = ossVoList.stream()
            .collect(Collectors.toMap(EstateVo::getId, EstateVo::getName));
        // 添加补充的信息
        for (HouseVo vo : pages.getRecords()) {
            vo.setEstatename(ossInfoMap.get(vo.getEstateid()));
        }
    }

    /**
     * 批量查询小区名称名称并添加
     *
     * @param pages
     */

    private void addEstateNames(List<HouseVo> pages) {
        // 提取oosid 方便批量查询
        List<Long> oosIds = pages.stream().map(HouseVo::getEstateid).collect(Collectors.toList());
        // 批量查询
        List<EstateVo> ossVoList = estateMapper.selectVoList(Wrappers.lambdaQuery(Estate.class).in(Estate::getId, oosIds));
        //构造map 方便实体关系的映射
        Map<Long, String> ossInfoMap = ossVoList.stream()
            .collect(Collectors.toMap(EstateVo::getId, EstateVo::getName));
        // 添加补充的信息
        pages.forEach(e -> {
            e.setEstatename(ossInfoMap.get(e.getEstateid()));
        });
    }

    /**
     * 查询房源信息表
     */
    @Override
    public HouseVo queryById(Long id) {
        HouseVo houseVo = baseMapper.selectVoById(id);
        /*判断是否为空*/
        Optional.ofNullable(houseVo).ifPresent(this::addCityName);
        Optional.ofNullable(houseVo).ifPresent(this::addEstateName);
        return houseVo;
    }


    /**
     * 查询房源信息表列表
     */
    @Override
    public TableDataInfo<HouseVo> queryPageList(HouseBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<House> lqw = buildQueryWrapper(bo);
        Page<HouseVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        if (result.getRecords().size() > 0) {
            addCityNames(result);
            addEstateNames(result);
        }
        return TableDataInfo.build(result);
    }

    /**
     * 查询房源信息表列表
     */
    @Override
    public List<HouseVo> queryList(HouseBo bo) {
        LambdaQueryWrapper<House> lqw = buildQueryWrapper(bo);
        List<HouseVo> voList = baseMapper.selectVoList(lqw);
        if (voList.size() > 0) {
            addCityNames(voList);
            addEstateNames(voList);
        }
        return voList;
    }

    private LambdaQueryWrapper<House> buildQueryWrapper(HouseBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<House> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getUserid() != null, House::getUserid, bo.getUserid());
        lqw.eq(bo.getSupplierid() != null, House::getSupplierid, bo.getSupplierid());
        lqw.like(StringUtils.isNotBlank(bo.getName()), House::getName, bo.getName());
        lqw.eq(bo.getCityid() != null, House::getCityid, bo.getCityid());
        lqw.eq(bo.getEstateid() != null, House::getEstateid, bo.getEstateid());
        lqw.eq(StringUtils.isNotBlank(bo.getDescribe()), House::getDescribe, bo.getDescribe());
        lqw.eq(StringUtils.isNotBlank(bo.getBusdescribe()), House::getBusdescribe, bo.getBusdescribe());
        lqw.eq(StringUtils.isNotBlank(bo.getAddress()), House::getAddress, bo.getAddress());
        lqw.eq(bo.getNum() != null, House::getNum, bo.getNum());
        return lqw;
    }

    /**
     * 新增房源信息表
     */
    @Override
    public Boolean insertByBo(HouseBo bo) {
        House add = MapstructUtils.convert(bo, House.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改房源信息表
     */
    @Override
    public Boolean updateByBo(HouseBo bo) {
        House update = MapstructUtils.convert(bo, House.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(House entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除房源信息表
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}

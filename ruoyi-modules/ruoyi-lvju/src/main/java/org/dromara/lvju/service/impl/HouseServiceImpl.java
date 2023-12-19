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
import org.dromara.lvju.domain.bo.HouseBo;
import org.dromara.lvju.domain.vo.HouseVo;
import org.dromara.lvju.domain.House;
import org.dromara.lvju.mapper.HouseMapper;
import org.dromara.lvju.service.IHouseService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

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

    /**
     * 查询房源信息表
     */
    @Override
    public HouseVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询房源信息表列表
     */
    @Override
    public TableDataInfo<HouseVo> queryPageList(HouseBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<House> lqw = buildQueryWrapper(bo);
        Page<HouseVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询房源信息表列表
     */
    @Override
    public List<HouseVo> queryList(HouseBo bo) {
        LambdaQueryWrapper<House> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
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
    private void validEntityBeforeSave(House entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除房源信息表
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}

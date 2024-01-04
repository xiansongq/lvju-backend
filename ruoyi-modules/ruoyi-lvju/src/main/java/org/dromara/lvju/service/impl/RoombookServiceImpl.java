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
import org.dromara.lvju.domain.bo.RoombookBo;
import org.dromara.lvju.domain.vo.RoombookVo;
import org.dromara.lvju.domain.Roombook;
import org.dromara.lvju.mapper.RoombookMapper;
import org.dromara.lvju.service.IRoombookService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 房间订单信息Service业务层处理
 *
 * @author seky
 * @date 2024-01-02
 */
@RequiredArgsConstructor
@Service
public class RoombookServiceImpl implements IRoombookService {

    private final RoombookMapper baseMapper;

    /**
     * 查询房间订单信息
     */
    @Override
    public RoombookVo queryById(String id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询房间订单信息列表
     */
    @Override
    public TableDataInfo<RoombookVo> queryPageList(RoombookBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Roombook> lqw = buildQueryWrapper(bo);
        Page<RoombookVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询房间订单信息列表
     */
    @Override
    public List<RoombookVo> queryList(RoombookBo bo) {
        LambdaQueryWrapper<Roombook> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Roombook> buildQueryWrapper(RoombookBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Roombook> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getRoomid()), Roombook::getRoomid, bo.getRoomid());
        lqw.eq(StringUtils.isNotBlank(bo.getCustomerid()), Roombook::getCustomerid, bo.getCustomerid());
        lqw.eq(StringUtils.isNotBlank(bo.getSupplierid()), Roombook::getSupplierid, bo.getSupplierid());
        lqw.eq(StringUtils.isNotBlank(bo.getPrice()), Roombook::getPrice, bo.getPrice());
        lqw.eq(StringUtils.isNotBlank(bo.getStartdate()), Roombook::getStartdate, bo.getStartdate());
        lqw.eq(StringUtils.isNotBlank(bo.getEnddate()), Roombook::getEnddate, bo.getEnddate());
        return lqw;
    }

    /**
     * 新增房间订单信息
     */
    @Override
    public Boolean insertByBo(RoombookBo bo) {
        Roombook add = MapstructUtils.convert(bo, Roombook.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改房间订单信息
     */
    @Override
    public Boolean updateByBo(RoombookBo bo) {
        Roombook update = MapstructUtils.convert(bo, Roombook.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Roombook entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除房间订单信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}

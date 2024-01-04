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
import org.dromara.lvju.domain.bo.RoompriceBo;
import org.dromara.lvju.domain.vo.RoompriceVo;
import org.dromara.lvju.domain.Roomprice;
import org.dromara.lvju.mapper.RoompriceMapper;
import org.dromara.lvju.service.IRoompriceService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 房间价格信息Service业务层处理
 *
 * @author seky
 * @date 2024-01-02
 */
@RequiredArgsConstructor
@Service
public class RoompriceServiceImpl implements IRoompriceService {

    private final RoompriceMapper baseMapper;

    /**
     * 查询房间价格信息
     */
    @Override
    public RoompriceVo queryById(String id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询房间价格信息列表
     */
    @Override
    public TableDataInfo<RoompriceVo> queryPageList(RoompriceBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Roomprice> lqw = buildQueryWrapper(bo);
        Page<RoompriceVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询房间价格信息列表
     */
    @Override
    public List<RoompriceVo> queryList(RoompriceBo bo) {
        LambdaQueryWrapper<Roomprice> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Roomprice> buildQueryWrapper(RoompriceBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Roomprice> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getRoomid()), Roomprice::getRoomid, bo.getRoomid());
        lqw.eq(StringUtils.isNotBlank(bo.getPrice()), Roomprice::getPrice, bo.getPrice());
        lqw.eq(StringUtils.isNotBlank(bo.getPdate()), Roomprice::getPdate, bo.getPdate());
        lqw.eq(bo.getIsvalid() != null, Roomprice::getIsvalid, bo.getIsvalid());
        return lqw;
    }

    /**
     * 新增房间价格信息
     */
    @Override
    public Boolean insertByBo(RoompriceBo bo) {
        Roomprice add = MapstructUtils.convert(bo, Roomprice.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改房间价格信息
     */
    @Override
    public Boolean updateByBo(RoompriceBo bo) {
        Roomprice update = MapstructUtils.convert(bo, Roomprice.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Roomprice entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除房间价格信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}

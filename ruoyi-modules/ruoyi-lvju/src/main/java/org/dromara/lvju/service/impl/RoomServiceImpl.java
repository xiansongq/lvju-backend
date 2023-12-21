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
import org.dromara.lvju.domain.bo.RoomBo;
import org.dromara.lvju.domain.vo.RoomVo;
import org.dromara.lvju.domain.Room;
import org.dromara.lvju.mapper.RoomMapper;
import org.dromara.lvju.service.IRoomService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 房间信息管理Service业务层处理
 *
 * @author xsQian
 * @date 2023-12-21
 */
@RequiredArgsConstructor
@Service
public class RoomServiceImpl implements IRoomService {

    private final RoomMapper baseMapper;

    /**
     * 查询房间信息管理
     */
    @Override
    public RoomVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询房间信息管理列表
     */
    @Override
    public TableDataInfo<RoomVo> queryPageList(RoomBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Room> lqw = buildQueryWrapper(bo);
        Page<RoomVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询房间信息管理列表
     */
    @Override
    public List<RoomVo> queryList(RoomBo bo) {
        LambdaQueryWrapper<Room> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Room> buildQueryWrapper(RoomBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Room> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getHouseid() != null, Room::getHouseid, bo.getHouseid());
        lqw.eq(StringUtils.isNotBlank(bo.getHouseno()), Room::getHouseno, bo.getHouseno());
        lqw.eq(StringUtils.isNotBlank(bo.getHouselv()), Room::getHouselv, bo.getHouselv());
        lqw.eq(StringUtils.isNotBlank(bo.getRoomno()), Room::getRoomno, bo.getRoomno());
        lqw.eq(StringUtils.isNotBlank(bo.getApartment()), Room::getApartment, bo.getApartment());
        lqw.eq(StringUtils.isNotBlank(bo.getArea()), Room::getArea, bo.getArea());
        lqw.eq(bo.getStatus() != null, Room::getStatus, bo.getStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getDescribe()), Room::getDescribe, bo.getDescribe());
        lqw.eq(StringUtils.isNotBlank(bo.getKeys()), Room::getKeys, bo.getKeys());
        lqw.eq(StringUtils.isNotBlank(bo.getRemarks()), Room::getRemarks, bo.getRemarks());
        return lqw;
    }

    /**
     * 新增房间信息管理
     */
    @Override
    public Boolean insertByBo(RoomBo bo) {
        Room add = MapstructUtils.convert(bo, Room.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改房间信息管理
     */
    @Override
    public Boolean updateByBo(RoomBo bo) {
        Room update = MapstructUtils.convert(bo, Room.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Room entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除房间信息管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}

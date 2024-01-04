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
import org.dromara.lvju.domain.bo.RoomstusBo;
import org.dromara.lvju.domain.vo.RoomstusVo;
import org.dromara.lvju.domain.Roomstus;
import org.dromara.lvju.mapper.RoomstusMapper;
import org.dromara.lvju.service.IRoomstusService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 房间预定信息Service业务层处理
 *
 * @author seky
 * @date 2024-01-02
 */
@RequiredArgsConstructor
@Service
public class RoomstusServiceImpl implements IRoomstusService {

    private final RoomstusMapper baseMapper;

    /**
     * 查询房间预定信息
     */
    @Override
    public RoomstusVo queryById(String id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询房间预定信息列表
     */
    @Override
    public TableDataInfo<RoomstusVo> queryPageList(RoomstusBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Roomstus> lqw = buildQueryWrapper(bo);
        Page<RoomstusVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询房间预定信息列表
     */
    @Override
    public List<RoomstusVo> queryList(RoomstusBo bo) {
        LambdaQueryWrapper<Roomstus> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Roomstus> buildQueryWrapper(RoomstusBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Roomstus> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getRoomid()), Roomstus::getRoomid, bo.getRoomid());
        lqw.eq(StringUtils.isNotBlank(bo.getCustomerid()), Roomstus::getCustomerid, bo.getCustomerid());
        lqw.eq(bo.getStatus() != null, Roomstus::getStatus, bo.getStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getStartdate()), Roomstus::getStartdate, bo.getStartdate());
        lqw.eq(StringUtils.isNotBlank(bo.getEnddate()), Roomstus::getEnddate, bo.getEnddate());
        lqw.eq(bo.getBookprice() != null, Roomstus::getBookprice, bo.getBookprice());
        return lqw;
    }

    /**
     * 新增房间预定信息
     */
    @Override
    public Boolean insertByBo(RoomstusBo bo) {
        Roomstus add = MapstructUtils.convert(bo, Roomstus.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改房间预定信息
     */
    @Override
    public Boolean updateByBo(RoomstusBo bo) {
        Roomstus update = MapstructUtils.convert(bo, Roomstus.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Roomstus entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除房间预定信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}

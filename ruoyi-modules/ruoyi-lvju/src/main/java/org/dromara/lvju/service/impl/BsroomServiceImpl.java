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
import org.dromara.lvju.domain.bo.BsroomBo;
import org.dromara.lvju.domain.vo.BsroomVo;
import org.dromara.lvju.domain.Bsroom;
import org.dromara.lvju.mapper.BsroomMapper;
import org.dromara.lvju.service.IBsroomService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 房间详细信息Service业务层处理
 *
 * @author seky
 * @date 2024-01-02
 */
@RequiredArgsConstructor
@Service
public class BsroomServiceImpl implements IBsroomService {

    private final BsroomMapper baseMapper;

    /**
     * 查询房间详细信息
     */
    @Override
    public BsroomVo queryById(String id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询房间详细信息列表
     */
    @Override
    public TableDataInfo<BsroomVo> queryPageList(BsroomBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Bsroom> lqw = buildQueryWrapper(bo);
        Page<BsroomVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询房间详细信息列表
     */
    @Override
    public List<BsroomVo> queryList(BsroomBo bo) {
        LambdaQueryWrapper<Bsroom> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Bsroom> buildQueryWrapper(BsroomBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Bsroom> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getRoomid()), Bsroom::getRoomid, bo.getRoomid());
        lqw.like(StringUtils.isNotBlank(bo.getName()), Bsroom::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getRtype()), Bsroom::getRtype, bo.getRtype());
        lqw.eq(StringUtils.isNotBlank(bo.getVal()), Bsroom::getVal, bo.getVal());
        return lqw;
    }

    /**
     * 新增房间详细信息
     */
    @Override
    public Boolean insertByBo(BsroomBo bo) {
        Bsroom add = MapstructUtils.convert(bo, Bsroom.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改房间详细信息
     */
    @Override
    public Boolean updateByBo(BsroomBo bo) {
        Bsroom update = MapstructUtils.convert(bo, Bsroom.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Bsroom entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除房间详细信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}

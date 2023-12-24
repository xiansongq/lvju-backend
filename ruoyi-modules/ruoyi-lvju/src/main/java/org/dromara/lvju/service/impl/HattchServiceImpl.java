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
import org.dromara.lvju.domain.bo.HattchBo;
import org.dromara.lvju.domain.vo.HattchVo;
import org.dromara.lvju.domain.Hattch;
import org.dromara.lvju.mapper.HattchMapper;
import org.dromara.lvju.service.IHattchService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 房源附件信息Service业务层处理
 *
 * @author xsQian
 * @date 2023-12-24
 */
@RequiredArgsConstructor
@Service
public class HattchServiceImpl implements IHattchService {

    private final HattchMapper baseMapper;

    /**
     * 查询房源附件信息
     */
    @Override
    public HattchVo queryById(String id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询房源附件信息列表
     */
    @Override
    public TableDataInfo<HattchVo> queryPageList(HattchBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Hattch> lqw = buildQueryWrapper(bo);
        Page<HattchVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询房源附件信息列表
     */
    @Override
    public List<HattchVo> queryList(HattchBo bo) {
        LambdaQueryWrapper<Hattch> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Hattch> buildQueryWrapper(HattchBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Hattch> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getHouseid() != null, Hattch::getHouseid, bo.getHouseid());
        lqw.like(StringUtils.isNotBlank(bo.getName()), Hattch::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getPath()), Hattch::getPath, bo.getPath());
        lqw.eq(StringUtils.isNotBlank(bo.getHtype()), Hattch::getHtype, bo.getHtype());
        return lqw;
    }

    /**
     * 新增房源附件信息
     */
    @Override
    public Boolean insertByBo(HattchBo bo) {
        Hattch add = MapstructUtils.convert(bo, Hattch.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改房源附件信息
     */
    @Override
    public Boolean updateByBo(HattchBo bo) {
        Hattch update = MapstructUtils.convert(bo, Hattch.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Hattch entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除房源附件信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}

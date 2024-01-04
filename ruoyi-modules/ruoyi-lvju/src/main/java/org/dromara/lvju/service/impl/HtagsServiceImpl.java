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
import org.dromara.lvju.domain.bo.HtagsBo;
import org.dromara.lvju.domain.vo.HtagsVo;
import org.dromara.lvju.domain.Htags;
import org.dromara.lvju.mapper.HtagsMapper;
import org.dromara.lvju.service.IHtagsService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 房源标签信息Service业务层处理
 *
 * @author seky
 * @date 2024-01-02
 */
@RequiredArgsConstructor
@Service
public class HtagsServiceImpl implements IHtagsService {

    private final HtagsMapper baseMapper;

    /**
     * 查询房源标签信息
     */
    @Override
    public HtagsVo queryById(String id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询房源标签信息列表
     */
    @Override
    public TableDataInfo<HtagsVo> queryPageList(HtagsBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Htags> lqw = buildQueryWrapper(bo);
        Page<HtagsVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询房源标签信息列表
     */
    @Override
    public List<HtagsVo> queryList(HtagsBo bo) {
        LambdaQueryWrapper<Htags> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Htags> buildQueryWrapper(HtagsBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Htags> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getHouseid()), Htags::getHouseid, bo.getHouseid());
        lqw.like(StringUtils.isNotBlank(bo.getName()), Htags::getName, bo.getName());
        lqw.eq(bo.getStatus() != null, Htags::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增房源标签信息
     */
    @Override
    public Boolean insertByBo(HtagsBo bo) {
        Htags add = MapstructUtils.convert(bo, Htags.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改房源标签信息
     */
    @Override
    public Boolean updateByBo(HtagsBo bo) {
        Htags update = MapstructUtils.convert(bo, Htags.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Htags entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除房源标签信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}

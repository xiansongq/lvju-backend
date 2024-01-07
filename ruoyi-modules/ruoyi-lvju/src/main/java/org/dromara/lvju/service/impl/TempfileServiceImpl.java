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
import org.dromara.lvju.domain.bo.TempfileBo;
import org.dromara.lvju.domain.vo.TempfileVo;
import org.dromara.lvju.domain.Tempfile;
import org.dromara.lvju.mapper.TempfileMapper;
import org.dromara.lvju.service.ITempfileService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 模板文件信息管理Service业务层处理
 *
 * @author xsQian
 * @date 2024-01-07
 */
@RequiredArgsConstructor
@Service
public class TempfileServiceImpl implements ITempfileService {

    private final TempfileMapper baseMapper;

    /**
     * 查询模板文件信息管理
     */
    @Override
    public TempfileVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询模板文件信息管理列表
     */
    @Override
    public TableDataInfo<TempfileVo> queryPageList(TempfileBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Tempfile> lqw = buildQueryWrapper(bo);
        Page<TempfileVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询模板文件信息管理列表
     */
    @Override
    public List<TempfileVo> queryList(TempfileBo bo) {
        LambdaQueryWrapper<Tempfile> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Tempfile> buildQueryWrapper(TempfileBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Tempfile> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getTempName()), Tempfile::getTempName, bo.getTempName());
        lqw.eq(bo.getOssId() != null, Tempfile::getOssId, bo.getOssId());
        lqw.eq(bo.getAgentType() != null, Tempfile::getAgentType, bo.getAgentType());
        return lqw;
    }

    /**
     * 新增模板文件信息管理
     */
    @Override
    public Boolean insertByBo(TempfileBo bo) {
        Tempfile add = MapstructUtils.convert(bo, Tempfile.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改模板文件信息管理
     */
    @Override
    public Boolean updateByBo(TempfileBo bo) {
        Tempfile update = MapstructUtils.convert(bo, Tempfile.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Tempfile entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除模板文件信息管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}

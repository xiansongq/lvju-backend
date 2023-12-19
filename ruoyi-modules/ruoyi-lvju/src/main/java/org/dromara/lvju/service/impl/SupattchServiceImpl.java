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
import org.dromara.lvju.domain.bo.SupattchBo;
import org.dromara.lvju.domain.vo.SupattchVo;
import org.dromara.lvju.domain.Supattch;
import org.dromara.lvju.mapper.SupattchMapper;
import org.dromara.lvju.service.ISupattchService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 供应商资质证明材料Service业务层处理
 *
 * @author xsQian
 * @date 2023-12-18
 */
@RequiredArgsConstructor
@Service
public class SupattchServiceImpl implements ISupattchService {

    private final SupattchMapper baseMapper;

    /**
     * 查询供应商资质证明材料
     */
    @Override
    public SupattchVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询供应商资质证明材料列表
     */
    @Override
    public TableDataInfo<SupattchVo> queryPageList(SupattchBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Supattch> lqw = buildQueryWrapper(bo);
        Page<SupattchVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询供应商资质证明材料列表
     */
    @Override
    public List<SupattchVo> queryList(SupattchBo bo) {
        LambdaQueryWrapper<Supattch> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Supattch> buildQueryWrapper(SupattchBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Supattch> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getUserid() != null, Supattch::getUserid, bo.getUserid());
        lqw.eq(StringUtils.isNotBlank(bo.getSupplierid()), Supattch::getSupplierid, bo.getSupplierid());
        lqw.like(StringUtils.isNotBlank(bo.getName()), Supattch::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getPath()), Supattch::getPath, bo.getPath());
        lqw.eq(bo.getStype() != null, Supattch::getStype, bo.getStype());
        lqw.eq(bo.getIdeleted() != null, Supattch::getIdeleted, bo.getIdeleted());
        return lqw;
    }

    /**
     * 新增供应商资质证明材料
     */
    @Override
    public Boolean insertByBo(SupattchBo bo) {
        Supattch add = MapstructUtils.convert(bo, Supattch.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /*根据Userid查询*/
    @Override
    public List<SupattchVo> queryByUserid(Long userid) {
        LambdaQueryWrapper<Supattch> lqw= Wrappers.lambdaQuery();
        lqw.eq(userid!=null,Supattch::getUserid,userid)
            .eq(Supattch::getIdeleted,0)
            .orderByDesc(Supattch::getStype);
        return baseMapper.selectVoList(lqw);
    }

    /**
     * 修改供应商资质证明材料
     */
    @Override
    public Boolean updateByBo(SupattchBo bo) {
        Supattch update = MapstructUtils.convert(bo, Supattch.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Supattch entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除供应商资质证明材料
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}

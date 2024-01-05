package org.dromara.lvju.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.lvju.domain.vo.SupInfoVo;
import org.dromara.system.domain.vo.SysDictDataVo;
import org.dromara.system.mapper.SysDictDataMapper;
import org.dromara.system.service.impl.SysDictTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.dromara.lvju.domain.bo.SupattchBo;
import org.dromara.lvju.domain.vo.SupattchVo;
import org.dromara.lvju.domain.Supattch;
import org.dromara.lvju.mapper.SupattchMapper;
import org.dromara.lvju.service.ISupattchService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 供应商资质证明材料Service业务层处理
 *
 * @author xsQian
 * @date 2024-01-05
 */
@RequiredArgsConstructor
@Service
public class SupattchServiceImpl implements ISupattchService {

    private final SupattchMapper baseMapper;

    private final SysDictDataMapper sysDictDataMapper;
    @Autowired
    private SysDictTypeServiceImpl service;

    /**
     * 查询供应商资质证明材料
     */
    @Override
    public SupattchVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /*
     * 查询供应商资质文件
     * */

    @Override
    public TableDataInfo<SupInfoVo> queryAll(SupattchBo bo, PageQuery pageQuery) {

        LambdaQueryWrapper<Supattch> lqw = buildQueryWrapper(bo);
        /*查询该用户所有的资质文件*/
        Page<SupattchVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        /*查询出对应的字典值*/
        List<SysDictDataVo> list = new ArrayList<SysDictDataVo>();
        if (bo.getPeopleType() == 1) {
            System.out.println(bo.getPeopleType());

            list = service.selectDictDataByType("lvjv_supfile_type");
        } else if (bo.getPeopleType() == 2) {
            System.out.println(bo.getPeopleType());
            list = service.selectDictDataByType("lvju_file_type");
            System.out.println(list.toString());

        }
        List<SupInfoVo> infoVoList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            SupInfoVo vo = new SupInfoVo();
            vo.setTypeName(list.get(i).getDictLabel());
            vo.setTypeValue(Integer.valueOf(list.get(i).getDictValue()));
            infoVoList.add(vo);
        }
        /*做匹配*/
        for (SupInfoVo vo : infoVoList) {
            int flag = 0;
            for (int i = 0; i < result.getRecords().size(); i++) {
                if (vo.getTypeValue().equals(result.getRecords().get(i).getTypeValue())) {
                    vo.setOssId(result.getRecords().get(i).getOssId());
                    vo.setCreateTime(result.getRecords().get(i).getCreateTime());
                    vo.setStatus("1");
                    vo.setId(result.getRecords().get(i).getId());
                    flag = 1;
                    break;
                }

            }
            if (flag == 0) {
                {
                    vo.setStatus("0");
                }
            }
        }
        return TableDataInfo.build(infoVoList);
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
        lqw.eq(bo.getOssId() != null, Supattch::getOssId, bo.getOssId());
        lqw.like(StringUtils.isNotBlank(bo.getTypeName()), Supattch::getTypeName, bo.getTypeName());
        lqw.eq(bo.getTypeValue() != null, Supattch::getTypeValue, bo.getTypeValue());
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
    private void validEntityBeforeSave(Supattch entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除供应商资质证明材料
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}

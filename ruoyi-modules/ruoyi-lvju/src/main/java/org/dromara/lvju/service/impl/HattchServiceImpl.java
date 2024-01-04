package org.dromara.lvju.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.system.domain.SysOss;
import org.dromara.system.domain.vo.SysOssVo;
import org.dromara.system.mapper.SysOssMapper;
import org.springframework.stereotype.Service;
import org.dromara.lvju.domain.bo.HattchBo;
import org.dromara.lvju.domain.vo.HattchVo;
import org.dromara.lvju.domain.Hattch;
import org.dromara.lvju.mapper.HattchMapper;
import org.dromara.lvju.service.IHattchService;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    private final SysOssMapper sysOssMapper;

    /*
     * 查询 oos对象信息
     * */

    private void addOosInfo(HattchVo vo) {
//        LambdaQueryWrapper<SysOss> wrapper=Wrappers.lambdaQuery(SysOss.class)
//            .eq(SysOss::getOssId,vo.getOosId());
        SysOssVo ossInfo = sysOssMapper.selectVoById(vo.getOssId());
        Optional.ofNullable(ossInfo).ifPresent(e -> vo.setOssInfo(ossInfo));
    }

    /**
     * 查询房源附件信息
     */
    @Override
    public HattchVo queryById(String id) {
        // 先查询 HattchVo的信息
        HattchVo hvo = baseMapper.selectVoById(id);
        // 判断hvo 不为空

        Optional.ofNullable(hvo).ifPresent(this::addOosInfo);
        return hvo;


    }

    /*批量查询 附件的OOS信息*/
    private void addOssInfo(Page<HattchVo> pages) {
        // 提取oosid 方便批量查询
        List<Long> oosIds = pages.getRecords().stream().map(HattchVo::getOssId).collect(Collectors.toList());
        // 批量查询
        List<SysOssVo> ossVoList = sysOssMapper.selectVoList(Wrappers.lambdaQuery(SysOss.class).in(SysOss::getOssId, oosIds));
        //构造map 方便实体关系的映射
        Map<Long, SysOssVo> ossInfoMap = ossVoList.stream()
            .collect(Collectors.toMap(SysOssVo::getOssId, Function.identity()));
        // 添加补充的信息

        pages.convert(e -> {
            Optional<SysOssVo> ossInfo = Optional.ofNullable(ossInfoMap.get(e.getOssId()));
            ossInfo.ifPresent(e::setOssInfo);
            return e;
        } );
    }

    /*批量查询 附件的OOS信息*/
    private void addOssInfo(List<HattchVo> pages) {
        // 提取oosid 方便批量查询
        List<Long> oosIds =pages.stream().map(HattchVo::getOssId).collect(Collectors.toList());
        // 批量查询
        List<SysOssVo> ossVoList = sysOssMapper.selectVoList(Wrappers.lambdaQuery(SysOss.class).in(SysOss::getOssId, oosIds));
        //构造map 方便实体关系的映射
        Map<Long, SysOssVo> ossInfoMap = ossVoList.stream()
            .collect(Collectors.toMap(SysOssVo::getOssId, Function.identity()));
        // 添加补充的信息

        pages.forEach(e -> {
            e.setOssInfo(ossInfoMap.get(e.getOssId()));
        });
    }
    /**
     * 查询房源附件信息列表
     */
    @Override
    public TableDataInfo<HattchVo> queryPageList(HattchBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Hattch> lqw = buildQueryWrapper(bo);
        Page<HattchVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        if (result.getRecords().size() > 0) {
            addOssInfo(result);
        }
        return TableDataInfo.build(result);
    }

    /**
     * 查询房源附件信息列表
     */
    @Override
    public List<HattchVo> queryList(HattchBo bo) {
        LambdaQueryWrapper<Hattch> lqw = buildQueryWrapper(bo);
        List<HattchVo> hattchVoList=baseMapper.selectVoList(lqw);
        if(hattchVoList.size()>0 ){
            addOssInfo(hattchVoList);
        }
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Hattch> buildQueryWrapper(HattchBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Hattch> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getHouseid() != null, Hattch::getOssId, bo.getHouseid());

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
    private void validEntityBeforeSave(Hattch entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除房源附件信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}

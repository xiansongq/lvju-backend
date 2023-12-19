package org.dromara.lvju.service;

import org.dromara.lvju.domain.City;
import org.dromara.lvju.domain.vo.CityVo;
import org.dromara.lvju.domain.bo.CityBo;

import java.util.Collection;
import java.util.List;

/**
 * 城市信息管理Service接口
 *
 * @author xsQian
 * @date 2023-12-19
 */
public interface ICityService {

    /**
     * 查询城市信息管理
     */
    CityVo queryById(Long id);


    /**
     * 查询城市信息管理列表
     */
    List<CityVo> queryList(CityBo bo);

    /**
     * 新增城市信息管理
     */
    Boolean insertByBo(CityBo bo);

    /**
     * 修改城市信息管理
     */
    Boolean updateByBo(CityBo bo);

    /**
     * 校验并批量删除城市信息管理信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}

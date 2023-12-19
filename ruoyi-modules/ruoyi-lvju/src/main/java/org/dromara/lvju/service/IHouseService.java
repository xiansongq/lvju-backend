package org.dromara.lvju.service;

import org.dromara.lvju.domain.House;
import org.dromara.lvju.domain.vo.HouseVo;
import org.dromara.lvju.domain.bo.HouseBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 房源信息表Service接口
 *
 * @author xsQian
 * @date 2023-12-19
 */
public interface IHouseService {

    /**
     * 查询房源信息表
     */
    HouseVo queryById(Long id);

    /**
     * 查询房源信息表列表
     */
    TableDataInfo<HouseVo> queryPageList(HouseBo bo, PageQuery pageQuery);

    /**
     * 查询房源信息表列表
     */
    List<HouseVo> queryList(HouseBo bo);

    /**
     * 新增房源信息表
     */
    Boolean insertByBo(HouseBo bo);

    /**
     * 修改房源信息表
     */
    Boolean updateByBo(HouseBo bo);

    /**
     * 校验并批量删除房源信息表信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}

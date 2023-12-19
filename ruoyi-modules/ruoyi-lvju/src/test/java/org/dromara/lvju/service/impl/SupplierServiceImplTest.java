package org.dromara.lvju.service.impl;

import jakarta.annotation.Resource;
import lombok.val;
import org.dromara.lvju.domain.bo.SupplierBo;
import org.dromara.lvju.mapper.SupplierMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SupplierServiceImplTest {

    @Resource
    SupplierMapper supplierMapper;
    @Test
    void queryById() {
        SupplierBo bo=new SupplierBo();
        bo.setId(1L);
        val supplierVo = supplierMapper.selectVoById(1L);
        System.out.println(supplierVo.toString());

    }

    @Test
    void queryPageList() {
    }

    @Test
    void queryList() {
    }
}

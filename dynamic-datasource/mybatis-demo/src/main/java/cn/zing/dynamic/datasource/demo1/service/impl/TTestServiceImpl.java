package cn.zing.dynamic.datasource.demo1.service.impl;

import cn.zing.dynamic.datasource.demo1.bean.TTest;
import cn.zing.dynamic.datasource.demo1.config.db.DBTypeEnum;
import cn.zing.dynamic.datasource.demo1.config.db.DSM;
import cn.zing.dynamic.datasource.demo1.mapper.TTestMapper;
import cn.zing.dynamic.datasource.demo1.service.TTestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TTestServiceImpl implements TTestService {

    @Resource
    TTestMapper tTestMapper;

    @DSM(DBTypeEnum.PG)
    @Override
    public TTest getTTest(String id) {
        return tTestMapper.getTTest(id);
    }
}

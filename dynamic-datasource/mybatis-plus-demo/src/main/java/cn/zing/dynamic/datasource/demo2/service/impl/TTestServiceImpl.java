package cn.zing.dynamic.datasource.demo2.service.impl;

import cn.zing.dynamic.datasource.demo2.bean.TTest;
import cn.zing.dynamic.datasource.demo2.mapper.TTestMapper;
import cn.zing.dynamic.datasource.demo2.service.TTestService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
@DS("pg")
public class TTestServiceImpl extends ServiceImpl<TTestMapper, TTest> implements TTestService{

}

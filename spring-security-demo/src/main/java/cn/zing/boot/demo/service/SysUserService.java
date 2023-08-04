package cn.zing.boot.demo.service;

import cn.zing.boot.demo.bean.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SysUserService extends IService<SysUser>{


    int updateBatch(List<SysUser> list);

    int updateBatchSelective(List<SysUser> list);

    int batchInsert(List<SysUser> list);

    int insertOrUpdate(SysUser record);

    int insertOrUpdateSelective(SysUser record);

}

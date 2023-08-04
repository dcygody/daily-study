package cn.zing.boot.demo.service.impl;

import cn.zing.boot.demo.bean.SysRole;
import cn.zing.boot.demo.mapper.SysRoleMapper;
import cn.zing.boot.demo.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService{

    @Resource
    SysRoleMapper roleMapper;

    @Override
    public int updateBatch(List<SysRole> list) {
        return baseMapper.updateBatch(list);
    }
    @Override
    public int updateBatchSelective(List<SysRole> list) {
        return baseMapper.updateBatchSelective(list);
    }
    @Override
    public int batchInsert(List<SysRole> list) {
        return baseMapper.batchInsert(list);
    }
    @Override
    public int insertOrUpdate(SysRole record) {
        return baseMapper.insertOrUpdate(record);
    }
    @Override
    public int insertOrUpdateSelective(SysRole record) {
        return baseMapper.insertOrUpdateSelective(record);
    }




    @Override
    public List<String> selectRoleNameByUserId(Long userId) {
        return roleMapper.selectRoleNameByUserId(userId);
    }
}

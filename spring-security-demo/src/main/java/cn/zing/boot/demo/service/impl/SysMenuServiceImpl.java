package cn.zing.boot.demo.service.impl;

import cn.zing.boot.demo.bean.SysMenu;
import cn.zing.boot.demo.mapper.SysMenuMapper;
import cn.zing.boot.demo.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService{

    @Resource
    SysMenuMapper menuMapper;

    @Override
    public int updateBatch(List<SysMenu> list) {
        return baseMapper.updateBatch(list);
    }
    @Override
    public int updateBatchSelective(List<SysMenu> list) {
        return baseMapper.updateBatchSelective(list);
    }
    @Override
    public int batchInsert(List<SysMenu> list) {
        return baseMapper.batchInsert(list);
    }
    @Override
    public int insertOrUpdate(SysMenu record) {
        return baseMapper.insertOrUpdate(record);
    }
    @Override
    public int insertOrUpdateSelective(SysMenu record) {
        return baseMapper.insertOrUpdateSelective(record);
    }

    @Override
    public List<String> selectPermsByUserId(Long userId) {
        return menuMapper.selectPermsByUserId(userId);
    }
}

package cn.zing.boot.demo.mapper;

import cn.zing.boot.demo.bean.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMenuMapper extends BaseMapper<SysMenu> {
    int updateBatch(List<SysMenu> list);

    int updateBatchSelective(List<SysMenu> list);

    int batchInsert(@Param("list") List<SysMenu> list);

    int insertOrUpdate(SysMenu record);

    int insertOrUpdateSelective(SysMenu record);

    List<String> selectPermsByUserId(Long userId);
}
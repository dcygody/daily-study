package cn.zing.dynamic.datasource.api.mapper;

import cn.zing.dynamic.datasource.api.bean.DcyAvatar;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DcyAvatarMapper extends BaseMapper<DcyAvatar> {

    boolean addAvatar(DcyAvatar avatar);

    List<DcyAvatar> avatarList(long now);
}
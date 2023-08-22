package cn.zing.dynamic.datasource.demo1.service.impl;

import cn.zing.dynamic.datasource.api.bean.DcyAvatar;
import cn.zing.dynamic.datasource.api.mapper.DcyAvatarMapper;
import cn.zing.dynamic.datasource.api.service.DcyAvatarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DcyAvatarServiceImpl extends ServiceImpl<DcyAvatarMapper, DcyAvatar> implements DcyAvatarService{

    @Resource
    DcyAvatarMapper dcyAvatarMapper;

    @Override
    public boolean addAvatar(DcyAvatar avatar) {
        return dcyAvatarMapper.addAvatar(avatar);
    }

    @Override
    public List<DcyAvatar> avatarList() {
        return dcyAvatarMapper.avatarList(System.currentTimeMillis());
    }
}

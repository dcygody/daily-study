package cn.zing.dynamic.datasource.api.service;

import cn.zing.dynamic.datasource.api.bean.DcyAvatar;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface DcyAvatarService extends IService<DcyAvatar>{


    boolean addAvatar(DcyAvatar avatar);

    List<DcyAvatar> avatarList();

    List<DcyAvatar> avatarListBatch();
}

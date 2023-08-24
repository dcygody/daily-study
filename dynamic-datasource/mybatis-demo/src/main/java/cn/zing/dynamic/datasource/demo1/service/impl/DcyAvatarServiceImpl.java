package cn.zing.dynamic.datasource.demo1.service.impl;

import cn.zing.dynamic.datasource.api.bean.CommonRequest;
import cn.zing.dynamic.datasource.api.bean.DcyAvatar;
import cn.zing.dynamic.datasource.api.mapper.DcyAvatarMapper;
import cn.zing.dynamic.datasource.api.service.DcyAvatarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.*;

@Service
public class DcyAvatarServiceImpl extends ServiceImpl<DcyAvatarMapper, DcyAvatar> implements DcyAvatarService{

    /**
     * 处理请求的队列
     */
    private final LinkedBlockingQueue<CommonRequest> requestQueue = new LinkedBlockingQueue<>();

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

    @Override
    public List<DcyAvatar> avatarListBatch() {
        // 请求id
        String threadId = UUID.randomUUID().toString();
        CommonRequest request = new CommonRequest();
        request.setThreadId(threadId);
        // 参数在这里封装
        Map<String, Object> params = new HashMap<>();
        request.setParams(params);
        CompletableFuture<Map<String, Object>> resultFuture = new CompletableFuture<>();
        request.setResultFuture(resultFuture);
        requestQueue.add(request);

        // 返回的数据列表
        List<DcyAvatar> list = null;
        try {
            Map<String, Object> map = resultFuture.get(5, TimeUnit.SECONDS);
            list = (List<DcyAvatar>) map.get("data");
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     * 初始化处理请求的队列
     */
    @PostConstruct
    public void init() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            int size = requestQueue.size();
            if (size == 0) {
                return;
            }

            List<CommonRequest> requests = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                CommonRequest request = requestQueue.poll();
                requests.add(request);
            }

            System.out.println("处理数量: " + size);

            List<Map<String, Object>> params = new ArrayList<>();
            for (CommonRequest request: requests) {
                Map<String, Object> param = new HashMap<>();
                param.put("threadId", request.getThreadId());
                param.put("param", request.getParams());
                params.add(param);
            }

            List<Map<String, Object>> repos = queryData(params);

            Map<String, Object> resMap = new HashMap<>();
            for (Map<String, Object> res: repos) {
                String threadId = res.get("threadId").toString();
                resMap.put(threadId, res);
            }

            for (CommonRequest request: requests) {
                Map<String, Object> result = (Map<String, Object>) resMap.get(request.getThreadId());
                CompletableFuture<Map<String, Object>> future = request.getResultFuture();
                future.complete(result);
            }

        }, 0, 10, TimeUnit.MILLISECONDS);
    }

    public List<Map<String, Object>> queryData(List<Map<String, Object>> params) {

        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> param: params) {
            Map<String, Object> map = new HashMap<>();
            String threadId = (String)param.get("threadId");
            // 查询参数
            Map<String, Object> pas = (Map<String, Object> )param.get("param");
            List<DcyAvatar> list = avatarList();
            map.put("threadId", threadId);
            map.put("data", list);
            result.add(map);
        }
        return result;
    }
}

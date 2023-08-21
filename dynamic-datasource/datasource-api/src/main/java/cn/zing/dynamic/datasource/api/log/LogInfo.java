package cn.zing.dynamic.datasource.api.log;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @description:
 * @author: dcy
 * @create: 2023-08-21 14:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogInfo {

    private Integer id;

    private String methodName;

    private String requestArgs;

    private String desc;

    private String responseArgs;

    private long startTime;

    private long endTime;

    public LogInfo(String methodName, String requestArgs, String desc, String responseArgs, long startTime, long endTime) {
        this.methodName = methodName;
        this.requestArgs = requestArgs;
        this.desc = desc;
        this.responseArgs = responseArgs;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "LogInfo{" +
                "id=" + id +
                ", methodName='" + methodName + '\'' +
                ", requestArgs='" + requestArgs + '\'' +
                ", desc='" + desc + '\'' +
                ", responseArgs='" + responseArgs + '\'' +
                ", startTime=" + LocalDateTime.ofInstant(Instant.ofEpochMilli(startTime), ZoneId.systemDefault()).toString() +
                ", endTime=" + LocalDateTime.ofInstant(Instant.ofEpochMilli(endTime), ZoneId.systemDefault()).toString() +
                '}';
    }
}



package cn.zing.boot;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

/**
 * @description: 为什么实现 ImportSelector?
 * @author: dcy
 * @create: 2023-07-24 21:30
 */
public class DcyImportSelector implements DeferredImportSelector {


    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        // 自动配置 读取META-INF下文件的信息  使用了java SPI
        ServiceLoader<AutoConfiguration> loader = ServiceLoader.load(AutoConfiguration.class);

        List<String> list = new ArrayList<>();

        for (AutoConfiguration configuration : loader) {
            list.add(configuration.getClass().getName());
        }

        return list.toArray(new String[0]);
    }
}



package cn.zing.boot;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * @description:
 * @author: dcy
 * @create: 2023-07-24 21:01
 */
public class DcyCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(DcyConditionOnClass.class.getName());

        String className = (String)annotationAttributes.get("value");
        try {
            context.getClassLoader().loadClass(className);
            return true;
        }catch (ClassNotFoundException e) {
            return false;
        }
    }
}



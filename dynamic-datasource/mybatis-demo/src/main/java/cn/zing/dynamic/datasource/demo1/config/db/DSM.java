package cn.zing.dynamic.datasource.demo1.config.db;

import java.lang.annotation.*;

/**
 * 数据源
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DSM {

    DBTypeEnum value() default DBTypeEnum.PG;
}

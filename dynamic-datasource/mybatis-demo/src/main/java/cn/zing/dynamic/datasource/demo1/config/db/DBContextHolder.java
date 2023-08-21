package cn.zing.dynamic.datasource.demo1.config.db;

/**
 * @description:
 * @author: dcy
 * @create: 2023-08-17 17:45
 */
public class DBContextHolder {

    private static final ThreadLocal<DBTypeEnum> contextHolder = new ThreadLocal<>();


    public static void set(DBTypeEnum dbType) {
        System.out.println("设置数据源---->" + dbType.name());
        contextHolder.set(dbType);
    }

    public static DBTypeEnum get() {
        return contextHolder.get();
    }

}




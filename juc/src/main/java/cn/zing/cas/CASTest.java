package cn.zing.cas;

import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 以 compareAndSwapInt 为例，Unsafe 的 compareAndSwapInt 方法接收 4 个参数，
 * 分别是：对象实例、内存偏移量、字段期望值、字段新值。该方法会针对指定对象实例中的相应偏移量的字段执行 CAS 操作。
 * @author: dcy
 * @create: 2023-07-27 21:40
 */
public class CASTest {

    public static void main(String[] args) {
        Entity entity = new Entity();
        Unsafe unsafe = UnsafeFactory.getUnsafe();

        long offset = UnsafeFactory.getFieldOffset(unsafe, Entity.class, "x");
        System.out.println(offset);
        boolean successful;

        // 4个参数分别是：对象实例、字段的内存偏移量、字段期望值、字段更新值
        successful = unsafe.compareAndSwapInt(entity, offset, 0, 3);
        System.out.println(successful + "\t" + entity.x);

        successful = unsafe.compareAndSwapInt(entity, offset, 3, 5);
        System.out.println(successful + "\t" + entity.x);

        successful = unsafe.compareAndSwapInt(entity, offset, 3, 8);
        System.out.println(successful + "\t" + entity.x);
    }
}

class Entity {
    int x;
}



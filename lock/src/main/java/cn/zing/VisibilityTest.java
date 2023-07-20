package cn.zing;

/**
 * @description:
 * @author: dcy
 * @create: 2023-07-18 14:04
 */
public class VisibilityTest {

    /**
     * 不加 volatile, 永远无法跳出循环
     */
    private boolean flag = true;

    public void refresh() {

        flag = false;
        System.out.println(Thread.currentThread().getName() + "修改flag");
    }

    public void load() {
        System.out.println(Thread.currentThread().getName() + "开始执行.....");
        int i = 0;
        while (flag) {
            i++;
            //TODO  业务逻辑
            // 使用 yield 可以跳出循环
//            Thread.yield();
            // 也能跳出循环 synchronized
//            System.out.println(i);
            // 如果睡眠一下会刷新主内存,保存可见性
            shortWait(1000000);
        }
        System.out.println(Thread.currentThread().getName() + "跳出循环: i=" + i);
    }

    public static void main(String[] args) throws InterruptedException {
        VisibilityTest test = new VisibilityTest();

        // 线程threadA模拟数据加载场景
        Thread threadA = new Thread(test::load, "threadA");
        threadA.start();

        // 让threadA执行一会儿
        Thread.sleep(1000);
        // 线程threadB通过flag控制threadA的执行时间
        Thread threadB = new Thread(test::refresh, "threadB");
        threadB.start();

    }


    public static void shortWait(long interval) {
        long start = System.nanoTime();
        long end;
        do {
            end = System.nanoTime();
        } while (start + interval >= end);
    }
}



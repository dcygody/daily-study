package cn.zing;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description: 读写锁
 * @author: dcy
 * @create: 2023-07-17 20:12
 */
@Slf4j
public class ReadWriteLockTest1 {

    private Object data;
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock r = readWriteLock.readLock();
    private final Lock w = readWriteLock.writeLock();


    private void read() {
        log.debug("准备获取读锁");
        r.lock();
        try {
            // TODO 业务
            log.debug("获取读锁, 读数据......");
            data = "read";
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            r.unlock();
            log.debug("释放读锁......");
        }
    }

    private void write() {
        log.debug("准备获取写锁...");
        w.lock();
        try {
            // TODO 业务
            log.debug("获取写锁, 写数据......");
            data = "write";
            Thread.sleep(3000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            w.unlock();
            log.debug("释放写锁......");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteLockTest1 test = new ReadWriteLockTest1();
        // 测试 读读, 读写, 写读, 写写
//        new Thread(test::read, "thread1").start();
//        Thread.sleep(10);
//        new Thread(test::read, "thread2").start();
//        65536
        System.out.println(1 >>> 65536);
    }
}



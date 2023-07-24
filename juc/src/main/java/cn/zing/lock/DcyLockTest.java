package cn.zing.lock;

import java.util.Objects;

/**
 * @description:
 * @author: dcy
 * @create: 2023-07-22 21:09
 */
public class DcyLockTest {
    private final DcyLock dcyLock = new DcyLock();


    public void t1() {
        dcyLock.lock();
        try {
            System.out.println("t1在执行。。。。。");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            dcyLock.unlock();
        }
    }

    public void t2() {
        dcyLock.lock();
        try {
            System.out.println("t2在执行。。。。。");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dcyLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DcyLockTest dcyLockTest = new DcyLockTest();
        new Thread(dcyLockTest::t1, "T1").start();
        Thread.sleep(500);
        new Thread(dcyLockTest::t2, "T1").start();
    }
}



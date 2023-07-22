package cn.zing.lock;

import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;

/**
 * @description: 自己简单实现一把锁
 * @author: dcy
 * @create: 2023-07-22 21:03
 */
public class DcyLock extends AbstractQueuedLongSynchronizer {

    @Override
    protected boolean tryAcquire(long arg) {
        // CAS 加锁 0-1
        if (compareAndSetState(0, 1)) {
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;
    }

    @Override
    protected boolean tryRelease(long arg) {

        setExclusiveOwnerThread(null);
        setState(0);
        return true;
    }


    public void lock() {
        acquire(1);
    }

    public boolean tryLock() {
        return tryAcquire(1);
    }

    public void unlock() {
        release(1);
    }

    public boolean isLocked() {
        return isHeldExclusively();
    }
}



package cn.sdu.icat.stirm.service.impl;

import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * 线程池工具类
 *
 * @author icatzfd
 * Created on 2020/9/24 15:09.
 */
@Service
public class ThreadPoolService {

    private static final int DEFAULT_CORE_SIZE = 2;
    private static final int MAX_QUEUE_SIZE = 5;
    private volatile static ThreadPoolExecutor executor;

    private ThreadPoolService() {

    }

    //双重校验锁 实现的单例模式
    public ThreadPoolExecutor getExecutor() {
        if (executor == null) {
            synchronized (ThreadPoolService.class) {
                if (executor == null) {
                    executor = new ThreadPoolExecutor(DEFAULT_CORE_SIZE,
                            MAX_QUEUE_SIZE,
                            3,
                            TimeUnit.SECONDS,
                            new LinkedBlockingQueue<>(3),
                            Executors.defaultThreadFactory(),
                            new ThreadPoolExecutor.DiscardPolicy());
                }
            }
        }
        return executor;
    }

    public void execute(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        executor.execute(runnable);
    }


}

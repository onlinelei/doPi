package win.suroot.dopi.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TestThread {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newCachedThreadPool();
        CyclicBarrier barrier = new CyclicBarrier(10, new Runnable() {
            @Override
            public void run() {
                Thread t = Thread.currentThread();
                System.out.println("----------------我执行了---------------------------" + t.getName() + "线程ID:" + t.getId());
            }
        });
        CountDownLatch count = new CountDownLatch(10);

        List<Future<String>> submits = new ArrayList<>(16);
        for (int i = 0; i < 10; i++) {
            CallableDemo call = new CallableDemo(barrier, count);
            Future<String> submit = pool.submit(call);
            submits.add(submit);
        }
        pool.shutdown();

        System.out.println("到达 countDownLatch 阻塞点");
        count.await();
        System.out.println("到达 countDownLatch 阻塞点 执行其它任务");

        for (Future<String> submit : submits) {
            System.out.println("执行结果：" + submit.get());
        }
        Thread t = Thread.currentThread();
        System.out.println(t.getName() + "线程ID:" + t.getId() + "执行完毕");


    }
}

class CallableDemo implements Callable<String> {

    private final CyclicBarrier barrier;
    private final CountDownLatch count;

    public CallableDemo(CyclicBarrier barrier, CountDownLatch count) {
        this.barrier = barrier;
        this.count = count;
    }

    @Override
    public String call() throws Exception {
        int init = (int) (Math.random() * 100);
        int result = init;
        Thread t = Thread.currentThread();
        for (int i = 0; i < 100; i++) {
            result += i;
            Thread.sleep((long) (Math.random() * 100));
            System.out.println(t.getName() + "线程ID:" + t.getId() + "执行第：" + i + " 次");
        }
        count.countDown();

        System.out.println(t.getName() + "线程ID:" + t.getId() + "到达第一个检查点");
        barrier.await();

        Thread.sleep((long) (Math.random() * 10000));
        System.out.println(t.getName() + "线程ID:" + t.getId() + "到达第二个检查点");
        barrier.await();

        Thread.sleep((long) (Math.random() * 10000));
        System.out.println(t.getName() + "线程ID:" + t.getId() + "到达第三个检查点");
        barrier.await();

        return t.getName() + "线程ID:" + t.getId() + "; 初始值：" + init + "执行结果：" + result;
    }

}

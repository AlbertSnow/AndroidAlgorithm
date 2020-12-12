package com.albert.snow.algorithm;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class TestSynchronous {

    static class ShareData {
        public int data = 0;
    }

    @Test
    public void synchronousQueueImplementTest() {
        final ExecutorService executor = Executors.newCachedThreadPool();

        final ShareData shareData = new ShareData();
        final SynchronousQueue<ShareData> queue = new SynchronousQueue<>();

        Runnable producer = new Runnable() {
            @Override
            public void run() {
                shareData.data = ThreadLocalRandom
                        .current()
                        .nextInt();
                try {
                    queue.put(shareData);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Produce element over --------" + shareData.data);
            }
        };

        Runnable consumer = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Consumer share element ++++++ " + queue.take());
                    executor.shutdown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        executor.execute(consumer);
        executor.execute(producer);

        try {
            executor.awaitTermination(100000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Program over ______________-___________" + shareData.data);
    }



    @Test
    public void countDownImpelmentTest() {
        final ExecutorService executor = Executors.newCachedThreadPool();
        final CountDownLatch latch = new CountDownLatch(1);

        final ShareData shareData = new ShareData();
        Runnable producer = new Runnable() {
            @Override
            public void run() {
                shareData.data = ThreadLocalRandom
                        .current()
                        .nextInt();
                latch.countDown();
                System.out.println("Produce element over --------" + shareData.data);
            }
        };

        Runnable consumer = new Runnable() {
            @Override
            public void run() {
                try {
                    latch.await();
                    System.out.println("Consumer share element ++++++ " + shareData.data);
                    executor.shutdown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        executor.execute(consumer);
        executor.execute(producer);

        try {
            executor.awaitTermination(100000, TimeUnit.MILLISECONDS);
            assert  latch.getCount() == 0;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Program over ______________-___________" + shareData.data);
    }

}

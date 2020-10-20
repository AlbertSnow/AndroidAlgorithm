package com.albert.snow.algorithm;
import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class ReferenceTest {
    private final static ReferenceQueue<Object> queue = new ReferenceQueue<>();

    @Test
    public void testMain() {
        Person p1 = new Person("小明");
        Person p2 = new Person("小花");
        PhantomRef a1 = new PhantomRef(p1, "dog");
        PhantomRef a2 = new PhantomRef(p2, "cat");
        p1 = null;
        p2 = null;

        printReferenceQueue(queue);
        Runtime.getRuntime().gc();
        waitMoment(2000); // 给gc点时间收集，有时gc收集速度很快，可以不用加这句代码，我只不过是保险起见
        printReferenceQueue(queue);
    }

    static class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    static class PhantomRef extends SoftReference<Object> {
        private String name;
        public PhantomRef(Person referent, String name) {
            super(referent, queue);
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }

    private static void waitMoment(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private static void printReferenceQueue(ReferenceQueue<Object> rq) {
        int size = 0;
        Object obj;
        while ( ( obj = rq.poll() ) != null ) {
            PhantomRef a = (PhantomRef) obj;
            System.out.println(a.getName());
            size++;
        }
        System.out.println("引用队列大小为： " + size);
    }
}
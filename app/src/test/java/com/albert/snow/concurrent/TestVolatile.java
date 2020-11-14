package com.albert.snow.concurrent;

public class TestVolatile {

    static volatile boolean flag;

    public static void main(String[] args) {
        new Thread1().start();
        new Thread2().start();
        new Thread3().start();
    }

    static class Thread1 extends Thread {
        @Override
        public void run() {
            while (true) {
                if (flag) {
                    flag = false;
                    System.out.println("Thread1 set flag to false");
                }
            }
        }
    }

    static class Thread2 extends Thread {
        @Override
        public void run() {
            while (true) {
                if (!flag) {
                    flag = true;
                    System.out.println("Thread2 set flag to true");
                }
            }
        }
    }

    static class Thread3 extends Thread {
        @Override
        public void run() {
            while (true) {
                flag = true;

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}
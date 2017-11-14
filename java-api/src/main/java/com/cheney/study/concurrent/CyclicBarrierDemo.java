package com.cheney.study.concurrent;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier：等待所有线程准备好了再执行。可以执行多次，
 * 但是在等待其他线程时，如果有一个线程中断了，死了等情况，CyclicBarrier会抛出InterruptedException，
 * 因为所有线程不可能都准备好。
 */
public class CyclicBarrierDemo {
    public static class Soldier implements Runnable {
        private String soldierName;
        private final CyclicBarrier cyclicBarrier;

        public Soldier(String soldierName, CyclicBarrier cyclicBarrier) {
            this.soldierName = soldierName;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                //等待所有士兵到齐
                cyclicBarrier.await();//第一次执行
                doWork();
                //等待所有士兵完成工作
                cyclicBarrier.await();//第二次执行  可以多次执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        /**
         * 模拟任务执行时间。。。
         */
        private void doWork() {
            try {
                //模拟
                Thread.sleep(Math.abs(new Random().nextInt() % 10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(soldierName + "：任务完成！");
        }
    }

    /**
     * 当所有Soldier执行完后执行一下，及士兵所有都报到或完成任务后执行。
     */
    public static class BarrierRun implements Runnable {
        private boolean flag;
        private int N;

        public BarrierRun(boolean flag, int n) {
            this.flag = flag;
            N = n;
        }

        @Override
        public void run() {
            if (flag) {
                System.out.println("司令：【士兵" + N + "个，完成任务！】");
            } else {
                System.out.println("司令：【士兵" + N + "个，集合完毕！");
                flag = true;
            }
        }
    }

    public static void main(String[] args) {
        final int N = 10;
        Thread[] allSoldier = new Thread[N];
        boolean flag = false;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(N, new BarrierRun(flag, N));
        //设置屏障点，主要是为了执行这个方法
        System.out.println("开始集合队伍：");
        for (int i = 0; i < N; ++i) {
            System.out.println("士兵" + i + "报道！");
            allSoldier[i] = new Thread(new Soldier("士兵" + i, cyclicBarrier));
            allSoldier[i].start();
            //模拟报错：中断异常-》cyclicBarrier.await();会抛出中断异常，只要有一个线程中断了，await()就会抛出中断异常。因为
            //此时不可能所有线程都执行完毕。
           /* if (i == 5) {
                allSoldier[0].interrupt();
            }*/
        }
    }
}

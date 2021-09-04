package com.xushibo.studyjdkframework.juc.sychronized;

/**
 * <p>哲学家就餐问题</p>
 *  <li> 5名哲学家</li>
 *  <li> 5把筷子</li>
 *  <p>同时吃饭会发生什么</p>
 *  <p>结果：共享资源竞争且会死锁!</p>
 * @author ken1993
 * @date 20210903
 *
 * */
public class PhilosopherEatProblem {

    public static void main (String[] args){

        Chopsticks c1 = new Chopsticks();
        Chopsticks c2 = new Chopsticks();
        Chopsticks c3 = new Chopsticks();
        Chopsticks c4 = new Chopsticks();
        Chopsticks c5 = new Chopsticks();

        Philosopher p1 = new Philosopher("p1",1,c1,c2);
        Philosopher p2 = new Philosopher("p2",2,c2,c3);
        Philosopher p3 = new Philosopher("p3",3,c3,c4);
        Philosopher p4 = new Philosopher("p4",4,c4,c5);
        Philosopher p5 = new Philosopher("p5",5,c5,c1);

        new Thread(p1).start();
        new Thread(p2).start();
        new Thread(p3).start();
        new Thread(p4).start();
        new Thread(p5).start();

    }


    /**
     * <p>哲学家</p>
     *
     * **/
    public static class Philosopher implements Runnable{

        /**哲学家的座位*/
        private int index;
        private String name;
        private Chopsticks left;
        private Chopsticks right;

        public Philosopher(String name,int index, Chopsticks left, Chopsticks right) {
            this.index = index;
            this.name = name;
            this.left = left;
            this.right = right;
        }
        @Override
        public void run() {


            synchronized (left) {
                try {
                    Thread.sleep(1000 + index);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (right) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("第" + index + "位的" + name + "哲学家吃完了");
                }
            }
        }

    }

}

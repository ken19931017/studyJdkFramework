package com.xushibo.studyjdkframework.juc.sychronized;

/**
 * <p>哲学家</p>
 *
 * **/
public class PhilosopherForSolution implements Runnable{

    /**哲学家的座位*/
    private int index;
    private String name;
    /**左边的筷子*/
    private Chopsticks left;
    /**右边的筷子*/
    private Chopsticks right;

    public PhilosopherForSolution(String name,int index, Chopsticks left, Chopsticks right) {
        this.index = index;
        this.name = name;
        this.left = left;
        this.right = right;
    }
    @Override
    public void run() {
        /**分奇偶两类*/
        if(index%2==0){
            synchronized (left){
                try {
                    Thread.sleep(1000+index);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (right){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("第"+index+"位的"+name+"哲学家吃完了");
                }
            }

        }else{
            synchronized (right){
                try {
                    Thread.sleep(1000+index);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (left){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("第"+index+"位的"+name+"哲学家吃完了");
                }
            }
        }


    }
}
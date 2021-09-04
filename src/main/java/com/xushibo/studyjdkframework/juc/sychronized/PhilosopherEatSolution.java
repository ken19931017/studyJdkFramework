package com.xushibo.studyjdkframework.juc.sychronized;

public class PhilosopherEatSolution {

    public static void main (String[] args){

        Chopsticks c1 = new Chopsticks();
        Chopsticks c2 = new Chopsticks();
        Chopsticks c3 = new Chopsticks();
        Chopsticks c4 = new Chopsticks();
        Chopsticks c5 = new Chopsticks();

        PhilosopherForSolution p1 = new PhilosopherForSolution("p1",1,c1,c2);
        PhilosopherForSolution p2 = new PhilosopherForSolution("p2",2,c2,c3);
        PhilosopherForSolution p3 = new PhilosopherForSolution("p3",3,c3,c4);
        PhilosopherForSolution p4 = new PhilosopherForSolution("p4",4,c4,c5);
        PhilosopherForSolution p5 = new PhilosopherForSolution("p5",5,c5,c1);

        new Thread(p1).start();
        new Thread(p2).start();
        new Thread(p3).start();
        new Thread(p4).start();
        new Thread(p5).start();

    }

}

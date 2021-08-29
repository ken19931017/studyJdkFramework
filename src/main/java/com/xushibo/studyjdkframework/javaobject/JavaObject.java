package com.xushibo.studyjdkframework.javaobject;

import org.openjdk.jol.info.ClassLayout;

/**
 * <p> java的对象</p>
 * <li>构成</li>
 *
 * @author ken1993
 * @since 20210829
 *
 * */
public class JavaObject {

    public static class Test{

    }


    public static void main(String[] args){

        Test test = new Test();

        System.out.println(ClassLayout.parseInstance(test).toPrintable());

    }

}

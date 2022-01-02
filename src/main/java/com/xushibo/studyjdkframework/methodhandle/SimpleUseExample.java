package com.xushibo.studyjdkframework.methodhandle;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * <P> 方法句柄的简单使用 </P>
 *  <P>
 *      获取 rcvr对象的hashcode方法句柄后反射调用。
 *  </P>
 *  <p>
 *      本测试代码来自《Java 技术手册》11.6 方法句柄
 *  </p>
 * **/
public class SimpleUseExample {

    public static void main (String[] args) throws NoSuchMethodException, IllegalAccessException {
        // 初始化测试对象
        Object rcvr = "a";
        // 方法类型 int
        MethodType mt= MethodType.methodType(int.class);
        // 寻找上下文
        MethodHandles.Lookup l = MethodHandles.lookup();
        // 创建测试对象的方法句柄
        MethodHandle mh= l.findVirtual(rcvr.getClass(),"hashCode",mt);
        int ret;
        try {
            //反射调用
            ret= (int) mh.invoke(rcvr);
            System.out.println(ret);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }


}

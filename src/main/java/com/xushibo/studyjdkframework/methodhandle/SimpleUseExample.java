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
        Object rcvr = "a";
        MethodType mt= MethodType.methodType(int.class);
        MethodHandles.Lookup l = MethodHandles.lookup();
        MethodHandle mh= l.findVirtual(rcvr.getClass(),"hashCode",mt);
        int ret;
        try {
            ret= (int) mh.invoke(rcvr);
            System.out.println(ret);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }


}

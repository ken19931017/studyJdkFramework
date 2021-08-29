# 概述

java文件不是对象，class字节码文件不是对象，它是对象的静态描述，当它被加载到jvm后，应该是作为对象的类型存储在方法区的一个地方。它会被真实的对象（new出来的）指向，其指针作为对象的一部分存在。

# 对象的构成

![](java%E5%AF%B9%E8%B1%A1.assets/java%E5%AF%B9%E8%B1%A1%20(2)-1630245375048.jpg)



# 样例代码

```java
public class JavaObject {

    public static class Test{

    }


    public static void main(String[] args){

        Test test = new Test();

        System.out.println(ClassLayout.parseInstance(test).toPrintable());

    }

}
```

```xml
<dependency>
   <groupId>org.openjdk.jol</groupId>
   <artifactId>jol-core</artifactId>
   <version>0.16</version>
</dependency>
```

# 测试效果

![image-20210829215703010](java%E5%AF%B9%E8%B1%A1.assets/image-20210829215703010-1630245425499.png)

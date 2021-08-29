# 概述

java文件不是对象，class字节码文件不是对象，它是对象的静态描述，当它被加载到jvm后，应该是作为对象的类型存储在方法区的一个地方。它会被真实的对象（new出来的）指向，其指针作为对象的一部分存在。

# 对象的构成

![java对象 (2)](C:\Users\ken1993\Downloads\java对象 (2).jpg)



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

![image-20210829214430806](C:\Users\ken1993\AppData\Roaming\Typora\typora-user-images\image-20210829214430806.png)

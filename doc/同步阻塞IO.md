# 同步阻塞IO

##  用户态，内核态，磁盘转换



![img](%E5%90%8C%E6%AD%A5%E9%98%BB%E5%A1%9EIO.assets/20170921202852767)



## 字节流的读取和写入

![Simple byte stream input and output.](%E5%90%8C%E6%AD%A5%E9%98%BB%E5%A1%9EIO.assets/byteStream.gif)



## 字符流的读取和写入

The Java platform stores character values using Unicode conventions. Character stream I/O automatically translates this internal format to and from the local character set. In Western locales, the local character set is usually an 8-bit superset of ASCII.

For most applications, I/O with character streams is no more complicated than I/O with byte streams. Input and output done with stream classes automatically translates to and from the local character set. A program that uses character streams in place of byte streams automatically adapts to the local character set and is ready for internationalization — all without extra effort by the programmer.

If internationalization isn't a priority, you can simply use the character stream classes without paying much attention to character set issues. Later, if internationalization becomes a priority, your program can be adapted without extensive recoding. See the [Internationalization](https://docs.oracle.com/javase/tutorial/i18n/index.html) trail for more information.



## 网络同步阻塞IO

![img](%E5%90%8C%E6%AD%A5%E9%98%BB%E5%A1%9EIO.assets/da7e1ecfd3c3ee0263b8892342dbc629.png)
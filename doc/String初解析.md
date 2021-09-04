# String初解析

## 心得

字符串在java的应用里是非常常见的存在。它经常被用来作为表示一些字面量。所谓字面量，就是你一看就知道它是什么。所以字符串和其他的基础类型一起组成了基础数据类型。虽然它是对象引用。字符串的底层是char数组，到了java9则变成了字节数组。因为char是占用两个字节的长度。而大部分的字符值实际用不了这么长。故在后续的版本，Oracle的工程师进行优化。从这个变化也可以看出，重构随时随刻都有可能发生。

## 接口

```java
public interface CharSequence
```

该接口代表通常意义的字符序列。

java8后接口也可以有自己的默认方法实现。所以我们可以看到如下一个方法出现在该接口里

```java
/**
 * Returns a stream of code point values from this sequence.  Any surrogate
 * pairs encountered in the sequence are combined as if by {@linkplain
 * Character#toCodePoint Character.toCodePoint} and the result is passed
 * to the stream. Any other code units, including ordinary BMP characters,
 * unpaired surrogates, and undefined code units, are zero-extended to
 * {@code int} values which are then passed to the stream.
 *
 * <p>If the sequence is mutated while the stream is being read, the result
 * is undefined.
 *
 * @return an IntStream of Unicode code points from this sequence
 * @since 1.8
 */
public default IntStream codePoints() {
    class CodePointIterator implements PrimitiveIterator.OfInt {
        int cur = 0;

        @Override
        public void forEachRemaining(IntConsumer block) {
            final int length = length();
            int i = cur;
            try {
                while (i < length) {
                    char c1 = charAt(i++);
                    if (!Character.isHighSurrogate(c1) || i >= length) {
                        block.accept(c1);
                    } else {
                        char c2 = charAt(i);
                        if (Character.isLowSurrogate(c2)) {
                            i++;
                            block.accept(Character.toCodePoint(c1, c2));
                        } else {
                            block.accept(c1);
                        }
                    }
                }
            } finally {
                cur = i;
            }
        }

        public boolean hasNext() {
            return cur < length();
        }

        public int nextInt() {
            final int length = length();

            if (cur >= length) {
                throw new NoSuchElementException();
            }
            char c1 = charAt(cur++);
            if (Character.isHighSurrogate(c1) && cur < length) {
                char c2 = charAt(cur);
                if (Character.isLowSurrogate(c2)) {
                    cur++;
                    return Character.toCodePoint(c1, c2);
                }
            }
            return c1;
        }
    }

    return StreamSupport.intStream(() ->
            Spliterators.spliteratorUnknownSize(
                    new CodePointIterator(),
                    Spliterator.ORDERED),
            Spliterator.ORDERED,
            false);
}
```

注 ：在Java中，一个实际的完整的字符称作**代码点(codePoint)**。

Unicode是展示世界上所有语言中的所有字符的标准方案，他给所有的字符指定了一个数字用来表示该字符。但是Unicode并没有规定字符对应的二进制怎么存储。实际上Java采用的是UTF-16，基本字符(BMP)采用一个bit存储，增补字符采用俩个。所以，如果想保证完整的遍历字符串，要先获得它有几个码点。

## 方法

```java
/**
 * Returns the index within this string of the first occurrence of the
 * specified substring.
 *
 * <p>The returned index is the smallest value <i>k</i> for which:
 * <blockquote><pre>
 * this.startsWith(str, <i>k</i>)
 * </pre></blockquote>
 * If no such value of <i>k</i> exists, then {@code -1} is returned.
 *
 * @param   str   the substring to search for.
 * @return  the index of the first occurrence of the specified substring,
 *          or {@code -1} if there is no such occurrence.
 */
public int indexOf(String str) {
    return indexOf(str, 0);
}
```

jdk中，已经封装不少常用的算法。上面这个方法就是查询某个子串在主串中的起始位置。主要还是对于数组的操作，也就是建立在数组这个数据结构上的算法。

```java
/**
 * Code shared by String and StringBuffer to do searches. The
 * source is the character array being searched, and the target
 * is the string being searched for.
 *
 * @param   source       the characters being searched.
 * @param   sourceOffset offset of the source string.
 * @param   sourceCount  count of the source string.
 * @param   target       the characters being searched for.
 * @param   targetOffset offset of the target string.
 * @param   targetCount  count of the target string.
 * @param   fromIndex    the index to begin searching from.
 */
static int indexOf(char[] source, int sourceOffset, int sourceCount,
        char[] target, int targetOffset, int targetCount,
        int fromIndex) {
    if (fromIndex >= sourceCount) {
        return (targetCount == 0 ? sourceCount : -1);
    }
    if (fromIndex < 0) {
        fromIndex = 0;
    }
    if (targetCount == 0) {
        return fromIndex;
    }

    char first = target[targetOffset];
    int max = sourceOffset + (sourceCount - targetCount);

    for (int i = sourceOffset + fromIndex; i <= max; i++) {
        /* Look for first character. */
        if (source[i] != first) {
            while (++i <= max && source[i] != first);
        }

        /* Found first character, now look at the rest of v2 */
        if (i <= max) {
            int j = i + 1;
            int end = j + targetCount - 1;
            for (int k = targetOffset + 1; j < end && source[j]
                    == target[k]; j++, k++);

            if (j == end) {
                /* Found whole string. */
                return i - sourceOffset;
            }
        }
    }
    return -1;
}
```

所以我们可以看到，到了操作数组这种基础的数据结构的层次时，能做的优化空间的其实不多。一般操作基础的数据结构，也只能if-else,for-each的操作。不过而换个角度看，如果我们的数据结构抽象层次足够高，那么能优化的空间就会很多了。这也就是为什么可以在业务层做设计模式和DDD的一个原因。
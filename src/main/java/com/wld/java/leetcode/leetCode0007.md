## [7. 整数反转](https://leetcode-cn.com/problems/reverse-integer/)（简单）

给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

**示例 1:**

```
输入: 123
输出: 321
```

 **示例 2:**

```
输入: -123
输出: -321
```

**示例 3:**

```
输入: 120
输出: 21
```

**注意:**

假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231, 231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。

<br/>

### 答案：

#### 1，先转化为字符串

看到这道题可能我们最容易想到的是先把他转化为一个字符串，然后再进行反转，代码如下

```java
public int reverse(int x) {
    boolean negative = x < 0;
    StringBuilder stringBuilder = new StringBuilder(x + "");
    if (negative)
        stringBuilder.deleteCharAt(0);
    stringBuilder.reverse();
    long reverseDigit = Long.parseLong(stringBuilder.toString());
    if (reverseDigit > Integer.MAX_VALUE || reverseDigit < Integer.MIN_VALUE)
        return 0;
    if (negative)
        return (int) -reverseDigit;
    return (int) reverseDigit;
}
```

第3行是先把他转化为字符串；

第6行再对字符串进行反转；

第8-9行如果反转之后大于int表示的范围就返回0；

第10-11行是对符号的处理；

<br/>

#### 2，直接反转

上面这种效率实在是太低。下面我们就以数字1234为例画个图来看一下，如果不转化为字符串该怎么实现

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBFfgv6TKT7IoGmIscxUX4wsiavaKU2zugy8u9aKyuj13WiamAOLZTlEkc0FficZuGxP765YZFSqcjjzg/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

我们看到上一步的结果在下一步都会先乘以10，然后在加一个个位数就是当前的值，一直这样循环下去，直到全部反转为止。大家可能会怀疑，上面图中分析的是正数，如果是负数该怎么办，其实负数也是一样，大家可以自己画个图看一下。上面的图很容易理解，我们来看下代码

```java
public int reverse(int x) {
    long res = 0;
    while (x != 0) {
        res = res * 10 + x % 10;
        x /= 10;
    }
    return (int) res == res ? (int) res : 0;
}
```

注意这里的res是long类型，在第7行的时候，会把它转化为int类型，如果res的范围大于int类型表示的范围，转化之后是不相等的，直接返回0，如果在int类型表示的范围内，转化之后是相等的，返回转化后的值即可。





![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### [上一题（6. Z 字形变换）(中等)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/leetcode/leetCode0006.md)

#### [下一题（8. 字符串转换整数 (atoi)）(中等)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/leetcode/leetCode0008.md)
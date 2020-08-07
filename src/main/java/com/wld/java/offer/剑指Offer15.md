## [剑指 Offer 15. 二进制中1的个数](https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/)（简单）

请实现一个函数，输入一个整数，输出该数二进制表示中 1 的个数。例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。

**示例 1：**

```
输入：00000000000000000000000000001011
输出：3
解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
```

**示例 2：**

```
输入：00000000000000000000000010000000
输出：1
解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
```

**示例 3：**

```
输入：11111111111111111111111111111101
输出：31
解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
```

<br/>

### 答案：

java的18种写法，之前在公众号[数据结构和算法](https://img-blog.csdnimg.cn/20190515124616751.jpg)中分为3个系列专门写过，这里就不在细写了，我把答案全部列出来，因为太多，我只给一些简单的提示，如果不懂的可以看下前面写的那3个系列，有图文分析
[364，位1的个数系列（一）](https://mp.weixin.qq.com/s/wd3ZdWPtKS-b_4ReBCyfwQ)
[364，位1的个数系列（二）](https://mp.weixin.qq.com/s/f_AkvyBXi6sUC9OcU4Kw9g)
[364，位1的个数系列（三）](https://mp.weixin.qq.com/s/3VQ-hTAVAC9uOgP8QSIWOA)

**1，把n往右移32次，每次都和1进行与运算**

```java
public int hammingWeight(int n) {
    int count = 0;
    for (int i = 0; i < 32; i++) {
        if (((n >>> i) & 1) == 1) {
            count++;
        }
    }
    return count;
}
```

**2，原理和上面一样，做了一点优化**

```java
public int hammingWeight(int n) {
    int count = 0;
    while (n != 0) {
        count += n & 1;
        n = n >>> 1;
    }
    return count;
}
```

**3，1每次往左移一位，再和n进行与运算**

```java
public int hammingWeight(int n) {
    int count = 0;
    for (int i = 0; i < 32; i++) {
        if ((n & (1 << i)) != 0) {
            count++;
        }
    }
    return count;
}
```

**4，1每次往左移一位，把运算的结果在右移判断是否是1**

```java
public int hammingWeight(int i) {
    int count = 0;
    for (int j = 0; j < 32; j++) {
        if ((i & (1 << j)) >>> j == 1)
            count++;
    }
    return count;
}
```

**5，这个是最常见的，每次消去最右边的1，直到消完为止**

```java
public int hammingWeight(int n) {
    int count = 0;
    while (n != 0) {
        n &= n - 1;
        count++;
    }
    return count;
}
```

**6，把上面的改为递归**

```java
public int hammingWeight(int n) {
    return n == 0 ? 0 : 1 + hammingWeight(n & (n - 1));
}
```

**7，查表**

```java
public int hammingWeight(int i) {
    //table是0到15转化为二进制时1的个数
    int table[] = {0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4};
    int count = 0;
    while (i != 0) {//通过每4位计算一次，求出包含1的个数
        count += table[i & 0xf];
        i >>>= 4;
    }
    return count;
}
```


**8，每两位存储，使用加法（先运算再移位）**

```java
public int hammingWeight(int n) {
    n = ((n & 0xaaaaaaaa) >>> 1) + (n & 0x55555555);
    n = ((n & 0xcccccccc) >>> 2) + (n & 0x33333333);
    n = (((n & 0xf0f0f0f0) >>> 4) + (n & 0x0f0f0f0f));
    n = n + (n >>> 8);
    n = n +  (n >>> 16);
    return n & 63;
}
```


**9，每两位存储，使用加法（先移位再运算）**

```java
public int hammingWeight(int n) {
    n = ((n >>> 1) & 0x55555555) + (n & 0x55555555);
    n = ((n >>> 2) & 0x33333333) + (n & 0x33333333);
    n = (((n >>> 4) & 0x0f0f0f0f) + (n & 0x0f0f0f0f));
    n = n + (n >>> 8);
    n = n + (n >>> 16);
    return n & 63;
}
```


**10，和第8种思路差不多，只不过在最后几行计算的时候过滤的比较干净**

```java
public int hammingWeight(int n) {
    n = ((n & 0xaaaaaaaa) >>> 1) + (n & 0x55555555);
    n = ((n & 0xcccccccc) >>> 2) + (n & 0x33333333);
    n = (((n & 0xf0f0f0f0) >>> 4) + (n & 0x0f0f0f0f));
    n = (((n & 0xff00ff00) >>> 8) + (n & 0x00ff00ff));
    n = (((n & 0xffff0000) >>> 16) + (n & 0x0000ffff));
    return n;
}
```


**11，每4位存储，使用加法**

```java
public int hammingWeight(int n) {
    n = (n & 0x11111111) + ((n >>> 1) & 0x11111111) + ((n >>> 2) & 0x11111111) + ((n >>> 3) & 0x11111111);
    n = (((n & 0xf0f0f0f0) >>> 4) + (n & 0x0f0f0f0f));
    n = n + (n >>> 8);
    n = n + (n >>> 16);
    return n & 63;
}
```


**12，每3位存储，使用加法**

```java
public int hammingWeight(int n) {
    n = (n & 011111111111) + ((n >>> 1) & 011111111111) + ((n >>> 2) & 011111111111);
    n = ((n + (n >>> 3)) & 030707070707);
    n = ((n + (n >>> 6)) & 07700770077);
    n = ((n + (n >>> 12)) & 037700007777);
    return ((n + (n >>> 24))) & 63;
}
```


**13，每5位存储，使用加法**

```java
public int hammingWeight(int n) {
    n = (n & 0x42108421) + ((n >>> 1) & 0x42108421) + ((n >>> 2) & 0x42108421) + ((n >>> 3) & 0x42108421) + ((n >>> 4) & 0x42108421);
    n = ((n + (n >>> 5)) & 0xc1f07c1f);
    n = ((n + (n >>> 10) + (n >>> 20) + (n >>> 30)) & 63);
    return n;
}
```


**14，每两位存储，使用减法（先运算再移位）**

```java
public int hammingWeight(int i) {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
```


**15，每3位存储，使用减法**

```java
public int hammingWeight(int n) {
    n = n - ((n >>> 1) & 033333333333) - ((n >>> 2) & 011111111111);
    n = ((n + (n >>> 3)) & 030707070707);
    n = ((n + (n >>> 6)) & 07700770077);
    n = ((n + (n >>> 12)) & 037700007777);
    return ((n + (n >>> 24))) & 63;
}
```


**16，每4位存储，使用减法**

```java
public int hammingWeight(int n) {
    int tmp = n - ((n >>> 1) & 0x77777777) - ((n >>> 2) & 0x33333333) - ((n >>> 3) & 0x11111111);
    tmp = ((tmp + (tmp >>> 4)) & 0x0f0f0f0f);
    tmp = ((tmp + (tmp >>> 8)) & 0x00ff00ff);
    return ((tmp + (tmp >>> 16)) & 0x0000ffff) % 63;
}
```


**17，每5位存储，使用减法**

```java
public int hammingWeight(int n) {
    n = n - ((n >>> 1) & 0xdef7bdef) - ((n >>> 2) & 0xce739ce7) - ((n >>> 3) & 0xc6318c63) - ((n >>> 4) & 0x02108421);
    n = ((n + (n >>> 5)) & 0xc1f07c1f);
    n = ((n + (n >>> 10) + (n >>> 20) + (n >>> 30)) & 63);
    return n;
}
```


**18，每次消去最右边的1，可以参照第5种解法**

```java
public static int hammingWeight(int num) {
    int total = 0;
    while (num != 0) {
        num -= num & (-num);
        total++;
    }
    return total;
}
```



这题如果一直写下去，再写10种也没问题，如果上面的代码你都能看懂，你也会有和我一样的想法。但解这题的最终思路还是没变，所以再写下去也没有太大价值。上面有些写法其实也很鸡肋，这里只是告诉大家这样写也是可以实现的，虽然可能你永远都不这样去写。



![image.png](https://img-blog.csdnimg.cn/20200807155236311.png)

#### [上一题（14. 剪绳子-II）(中等)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer14-II.md)

#### [下一题（16. 数值的整数次方）(中等)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer16.md)